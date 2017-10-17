// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.esotericsoftware.kryo.Serializer;

class FileSymbolTableSerializer$3 extends Serializer<OCQualifiedName> {
    public void write(final Kryo kryo, final Output output, final OCQualifiedName ocQualifiedName) {
        kryo.writeClassAndObject(output, (Object)ocQualifiedName.getQualifier());
        kryo.writeObjectOrNull(output, (Object)ocQualifiedName.getName(), (Class)String.class);
    }
    
    public OCQualifiedName read(final Kryo kryo, final Input input, final Class<OCQualifiedName> clazz) {
        return OCQualifiedName.interned((OCQualifiedName)kryo.readClassAndObject(input), (String)kryo.readObjectOrNull(input, (Class)String.class));
    }
}