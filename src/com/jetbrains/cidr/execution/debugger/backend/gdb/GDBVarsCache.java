// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import java.util.Iterator;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import com.intellij.util.containers.HashMap;
import com.intellij.openapi.util.Pair;
import com.intellij.util.containers.SLRUMap;
import java.util.List;
import java.util.Map;

public class GDBVarsCache
{
    private final Map<String, List<Boolean>> myAliveVars;
    private final Map<String, List<Boolean>> myReservedVars;
    private final SLRUMap<Pair<String, Integer>, GDBResponse.Record> myVarsCache;
    
    public GDBVarsCache(final Delegate delegate) {
        this.myAliveVars = (Map<String, List<Boolean>>)new HashMap();
        this.myReservedVars = (Map<String, List<Boolean>>)new HashMap();
        this.myVarsCache = new SLRUMap<Pair<String, Integer>, GDBResponse.Record>(200, 500) {
            protected void onDropFromCache(final Pair<String, Integer> pair, final GDBResponse.Record record) {
                if (delegate != null) {
                    delegate.onDrop(record);
                }
                final List<Boolean> list = GDBVarsCache.this.myAliveVars.get(pair.first);
                final List<Boolean> list2 = GDBVarsCache.this.myReservedVars.get(pair.first);
                list.set((int)pair.second, false);
                if (list2 != null) {
                    final int intValue = (int)pair.second;
                    if (list2.size() > intValue) {
                        list2.set(intValue, false);
                    }
                }
                super.onDropFromCache((Object)pair, (Object)record);
            }
        };
    }
    
    void putIntoCache(final String s, final GDBResponse.Record record) {
        List<Boolean> list = this.myAliveVars.get(s);
        List<Boolean> list2 = this.myReservedVars.get(s);
        if (list != null) {
            assert list2 != null && list2.size() == list.size();
            for (int i = 0; i < list.size(); ++i) {
                if (!list.get(i)) {
                    this.myVarsCache.put((Object)new Pair((Object)s, (Object)i), (Object)record);
                    list.set(i, true);
                    list2.set(i, true);
                    return;
                }
            }
        }
        if (list == null) {
            list = new ArrayList<Boolean>();
        }
        if (list2 == null) {
            list2 = new ArrayList<Boolean>();
        }
        list.add(true);
        list2.add(true);
        this.myVarsCache.put((Object)new Pair((Object)s, (Object)(list.size() - 1)), (Object)record);
        this.myAliveVars.put(s, list);
        this.myReservedVars.put(s, list2);
    }
    
    @Nullable
    GDBResponse.Record getFromCache(final String s) {
        final List<Boolean> list = this.myAliveVars.get(s);
        final List<Boolean> list2 = this.myReservedVars.get(s);
        if (list != null) {
            assert list2 != null && list2.size() == list.size();
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i) && !list2.get(i)) {
                    final GDBResponse.Record record = (GDBResponse.Record)this.myVarsCache.get((Object)new Pair((Object)s, (Object)i));
                    if (record != null) {
                        list2.set(i, true);
                    }
                    return record;
                }
            }
        }
        return null;
    }
    
    void freeVariablesToReuse() {
        final Iterator<String> iterator = this.myReservedVars.keySet().iterator();
        while (iterator.hasNext()) {
            final List<Boolean> list = this.myReservedVars.get(iterator.next());
            for (int i = 0; i < list.size(); ++i) {
                list.set(i, false);
            }
        }
    }
    
    public interface Delegate
    {
        void onDrop(final GDBResponse.Record p0);
    }
}
