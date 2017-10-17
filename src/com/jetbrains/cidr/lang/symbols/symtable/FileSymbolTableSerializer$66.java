// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$66 extends FieldSerializer<OCBlockPointerType> {
    protected OCBlockPointerType create(final Kryo kryo, final Input input, final Class<OCBlockPointerType> clazz) {
        return new OCBlockPointerType();
    }
}