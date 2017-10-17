// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import java.util.List;
import com.intellij.openapi.util.Pair;
import com.intellij.util.containers.SLRUMap;

class GDBVarsCache$1 extends SLRUMap<Pair<String, Integer>, GDBResponse.Record> {
    final /* synthetic */ Delegate val$delegate;
    
    protected void onDropFromCache(final Pair<String, Integer> pair, final GDBResponse.Record record) {
        if (this.val$delegate != null) {
            this.val$delegate.onDrop(record);
        }
        final List<Boolean> list = GDBVarsCache.access$000(GDBVarsCache.this).get(pair.first);
        final List<Boolean> list2 = GDBVarsCache.access$100(GDBVarsCache.this).get(pair.first);
        list.set((int)pair.second, false);
        if (list2 != null) {
            final int intValue = (int)pair.second;
            if (list2.size() > intValue) {
                list2.set(intValue, false);
            }
        }
        super.onDropFromCache((Object)pair, (Object)record);
    }
}