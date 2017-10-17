// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$61 extends FieldSerializer<OCRealType> {
    protected OCRealType create(final Kryo kryo, final Input input, final Class<OCRealType> clazz) {
        return new OCRealType();
    }
}