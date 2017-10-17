// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTableSerializer;
import com.jetbrains.cidr.lang.symbols.symtable.SerializerProvider;

public class CLanguageKindSerializer implements SerializerProvider
{
    @Override
    public void registerSerializers(final FileSymbolTableSerializer fileSymbolTableSerializer) {
        fileSymbolTableSerializer.getKryo().register((Class)CLanguageKind.class, (Serializer)new DefaultSerializers.EnumSerializer((Class)CLanguageKind.class));
    }
    
    @Override
    public int getVersion() {
        return 0;
    }
}
