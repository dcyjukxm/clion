// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$73 extends FieldSerializer<OCVariadicType> {
    protected OCVariadicType create(final Kryo kryo, final Input input, final Class<OCVariadicType> clazz) {
        return new OCVariadicType();
    }
}