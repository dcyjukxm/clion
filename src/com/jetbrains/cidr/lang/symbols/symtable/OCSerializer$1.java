// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.esotericsoftware.kryo.Kryo;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0006H\u0016¢\u0006\u0002\u0010\u0007¨\u0006\b" }, d2 = { "com/jetbrains/cidr/lang/symbols/symtable/OCSerializer$1", "Lcom/esotericsoftware/kryo/Kryo;", "(Lcom/jetbrains/cidr/lang/symbols/symtable/OCSerializer;)V", "newInstance", "T", "type", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "cidr-lang" })
public static final class OCSerializer$1 extends Kryo {
    final /* synthetic */ OCSerializer this$0;
    
    public <T> T newInstance(@NotNull final Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull((Object)clazz, "type");
        this.this$0.LOG.error("Serializing default type; please add a custom serializer with overridden create() method for " + clazz);
        return (T)super.newInstance((Class)clazz);
    }
}