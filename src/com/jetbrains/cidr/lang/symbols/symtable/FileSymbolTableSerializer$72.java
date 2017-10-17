// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$72 extends FieldSerializer<OCExpansionPackType> {
    protected OCExpansionPackType create(final Kryo kryo, final Input input, final Class<OCExpansionPackType> clazz) {
        return new OCExpansionPackType();
    }
}