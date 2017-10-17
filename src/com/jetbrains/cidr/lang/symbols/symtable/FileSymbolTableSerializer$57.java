// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$57 extends FieldSerializer<OCCppReferenceType> {
    protected OCCppReferenceType create(final Kryo kryo, final Input input, final Class<OCCppReferenceType> clazz) {
        return new OCCppReferenceType();
    }
}