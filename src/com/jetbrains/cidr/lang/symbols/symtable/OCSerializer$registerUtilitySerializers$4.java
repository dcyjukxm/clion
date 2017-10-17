// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.Serializer;
import com.intellij.util.containers.MostlySingularMultiMap$ValueList;
import com.esotericsoftware.kryo.Kryo;
import kotlin.Metadata;
import com.intellij.util.containers.MostlySingularMultiMap;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006" }, d2 = { "com/jetbrains/cidr/lang/symbols/symtable/OCSerializer$registerUtilitySerializers$4", "Lcom/intellij/util/containers/MostlySingularMultiMap;", "", "(Lcom/esotericsoftware/kryo/Kryo;)V", "registerValueList", "", "cidr-lang" })
public static final class OCSerializer$registerUtilitySerializers$4 extends MostlySingularMultiMap<Object, Object> {
    public final void registerValueList() {
        this.$kryo.register((Class)MostlySingularMultiMap$ValueList.class, (Serializer)new OCSerializer$registerUtilitySerializers$4$registerValueList.OCSerializer$registerUtilitySerializers$4$registerValueList$1());
    }
}