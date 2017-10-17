// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$67 extends FieldSerializer<OCReferenceType> {
    protected OCReferenceType create(final Kryo kryo, final Input input, final Class<OCReferenceType> clazz) {
        return new OCReferenceType();
    }
}