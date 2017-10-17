// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$70 extends FieldSerializer<OCAutoType> {
    public OCAutoType create(final Kryo kryo, final Input input, final Class<OCAutoType> clazz) {
        return new OCAutoType();
    }
}