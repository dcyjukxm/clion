// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$4 extends FieldSerializer<OCQualifiedNameWithArguments> {
    protected OCQualifiedNameWithArguments create(final Kryo kryo, final Input input, final Class<OCQualifiedNameWithArguments> clazz) {
        return new OCQualifiedNameWithArguments();
    }
}