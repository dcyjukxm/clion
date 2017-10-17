// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$58 extends FieldSerializer<OCFunctionType> {
    protected OCFunctionType create(final Kryo kryo, final Input input, final Class<OCFunctionType> clazz) {
        return new OCFunctionType();
    }
}