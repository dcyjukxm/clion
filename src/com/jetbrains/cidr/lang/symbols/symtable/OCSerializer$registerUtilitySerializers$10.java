// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.util.containers.HashSet;
import kotlin.jvm.internal.Intrinsics;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.esotericsoftware.kryo.io.Input;
import org.jetbrains.annotations.NotNull;
import com.esotericsoftware.kryo.Kryo;
import kotlin.Metadata;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\nH\u0014¨\u0006\u000b" }, d2 = { "com/jetbrains/cidr/lang/symbols/symtable/OCSerializer$registerUtilitySerializers$10", "Lcom/esotericsoftware/kryo/serializers/CollectionSerializer;", "()V", "create", "", "kryo", "Lcom/esotericsoftware/kryo/Kryo;", "input", "Lcom/esotericsoftware/kryo/io/Input;", "type", "Ljava/lang/Class;", "cidr-lang" })
public static final class OCSerializer$registerUtilitySerializers$10 extends CollectionSerializer {
    @NotNull
    protected Collection<?> create(@NotNull final Kryo kryo, @Nullable final Input input, @NotNull final Class<Collection<?>> clazz) {
        Intrinsics.checkParameterIsNotNull((Object)kryo, "kryo");
        Intrinsics.checkParameterIsNotNull((Object)clazz, "type");
        return (Collection<?>)new HashSet();
    }
}