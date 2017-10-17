// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$68 extends FieldSerializer<OCStructType> {
    protected OCStructType create(final Kryo kryo, final Input input, final Class<OCStructType> clazz) {
        return new OCStructType();
    }
}