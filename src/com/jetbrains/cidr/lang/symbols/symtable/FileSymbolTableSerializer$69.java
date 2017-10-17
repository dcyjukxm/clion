// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.esotericsoftware.kryo.Serializer;

class FileSymbolTableSerializer$69 extends Serializer<OCVoidType> {
    public void write(final Kryo kryo, final Output output, final OCVoidType ocVoidType) {
        output.writeBoolean(ocVoidType.isConst());
        output.writeBoolean(ocVoidType.isVolatile());
    }
    
    public OCVoidType read(final Kryo kryo, final Input input, final Class<OCVoidType> clazz) {
        return OCVoidType.instance(input.readBoolean(), input.readBoolean());
    }
}