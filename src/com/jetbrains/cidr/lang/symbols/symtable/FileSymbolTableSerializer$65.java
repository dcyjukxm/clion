// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$65 extends FieldSerializer<OCPointerType> {
    protected OCPointerType create(final Kryo kryo, final Input input, final Class<OCPointerType> clazz) {
        return new OCPointerType();
    }
}