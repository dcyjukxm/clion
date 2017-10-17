// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.esotericsoftware.kryo.io.Input;
import org.jetbrains.annotations.Nullable;
import com.esotericsoftware.kryo.Kryo;
import kotlin.Metadata;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$StringSerializer;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nH\u0016¨\u0006\u000b" }, d2 = { "com/jetbrains/cidr/lang/symbols/symtable/OCSerializer$registerUtilitySerializers$1", "Lcom/esotericsoftware/kryo/serializers/DefaultSerializers$StringSerializer;", "()V", "read", "", "kryo", "Lcom/esotericsoftware/kryo/Kryo;", "input", "Lcom/esotericsoftware/kryo/io/Input;", "type", "Ljava/lang/Class;", "cidr-lang" })
public static final class OCSerializer$registerUtilitySerializers$1 extends DefaultSerializers$StringSerializer {
    @NotNull
    public String read(@Nullable final Kryo kryo, @NotNull final Input input, @Nullable final Class<String> clazz) {
        Intrinsics.checkParameterIsNotNull((Object)input, "input");
        final String intern = OCNamesInternary.intern(super.read(kryo, input, (Class)clazz));
        Intrinsics.checkExpressionValueIsNotNull((Object)intern, "OCNamesInternary.intern(\u2026.read(kryo, input, type))");
        return intern;
    }
}