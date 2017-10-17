// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import kotlin.jvm.internal.Intrinsics;
import com.esotericsoftware.kryo.io.Output;
import org.jetbrains.annotations.NotNull;
import com.esotericsoftware.kryo.Kryo;
import kotlin.Metadata;
import com.esotericsoftware.kryo.Serializer;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J+\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0016¢\u0006\u0002\u0010\rJ%\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013R\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0014" }, d2 = { "Lcom/jetbrains/cidr/lang/symbols/symtable/OCSerializer$SingletonSerializer;", "T", "Lcom/esotericsoftware/kryo/Serializer;", "myInstance", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "read", "kryo", "Lcom/esotericsoftware/kryo/Kryo;", "input", "Lcom/esotericsoftware/kryo/io/Input;", "type", "Ljava/lang/Class;", "(Lcom/esotericsoftware/kryo/Kryo;Lcom/esotericsoftware/kryo/io/Input;Ljava/lang/Class;)Ljava/lang/Object;", "write", "", "output", "Lcom/esotericsoftware/kryo/io/Output;", "object", "(Lcom/esotericsoftware/kryo/Kryo;Lcom/esotericsoftware/kryo/io/Output;Ljava/lang/Object;)V", "cidr-lang" })
public static final class SingletonSerializer<T> extends Serializer<T>
{
    private final T myInstance;
    
    public void write(@NotNull final Kryo kryo, @NotNull final Output output, final T t) {
        Intrinsics.checkParameterIsNotNull((Object)kryo, "kryo");
        Intrinsics.checkParameterIsNotNull((Object)output, "output");
    }
    
    public T read(@NotNull final Kryo kryo, @NotNull final Input input, @NotNull final Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull((Object)kryo, "kryo");
        Intrinsics.checkParameterIsNotNull((Object)input, "input");
        Intrinsics.checkParameterIsNotNull((Object)clazz, "type");
        return this.myInstance;
    }
    
    public SingletonSerializer(final T myInstance) {
        this.myInstance = myInstance;
    }
}
