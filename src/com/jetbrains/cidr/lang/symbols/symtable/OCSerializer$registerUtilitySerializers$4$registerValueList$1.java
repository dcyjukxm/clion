// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.MostlySingularMultiMap$ValueList;
import java.util.Collection;
import com.esotericsoftware.kryo.io.Input;
import org.jetbrains.annotations.Nullable;
import com.esotericsoftware.kryo.Kryo;
import kotlin.Metadata;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0016\u0010\n\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0018\u00010\u000bH\u0014¨\u0006\f" }, d2 = { "com/jetbrains/cidr/lang/symbols/symtable/OCSerializer$registerUtilitySerializers$4$registerValueList$1", "Lcom/esotericsoftware/kryo/serializers/CollectionSerializer;", "()V", "create", "", "", "kryo", "Lcom/esotericsoftware/kryo/Kryo;", "input", "Lcom/esotericsoftware/kryo/io/Input;", "type", "Ljava/lang/Class;", "cidr-lang" })
public static final class OCSerializer$registerUtilitySerializers$4$registerValueList$1 extends CollectionSerializer {
    @NotNull
    protected Collection<Object> create(@Nullable final Kryo kryo, @Nullable final Input input, @Nullable final Class<Collection<Object>> clazz) {
        return (Collection<Object>)new MostlySingularMultiMap$ValueList();
    }
}