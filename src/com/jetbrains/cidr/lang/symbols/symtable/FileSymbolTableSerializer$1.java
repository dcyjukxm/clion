// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$1 extends FieldSerializer<ContextSignature> {
    protected ContextSignature create(final Kryo kryo, final Input input, final Class<ContextSignature> clazz) {
        return new ContextSignature();
    }
}