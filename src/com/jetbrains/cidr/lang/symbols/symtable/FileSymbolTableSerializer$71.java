// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$71 extends FieldSerializer<OCArrayType> {
    protected OCArrayType create(final Kryo kryo, final Input input, final Class<OCArrayType> clazz) {
        return new OCArrayType();
    }
}