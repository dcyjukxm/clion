// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.esotericsoftware.kryo.Serializer;

class FileSymbolTableSerializer$48 extends Serializer<OCElementType> {
    public void write(final Kryo kryo, final Output output, final OCElementType ocElementType) {
        output.writeString(ocElementType.toString());
    }
    
    public OCElementType read(final Kryo kryo, final Input input, final Class<OCElementType> clazz) {
        return OCTokenTypes.ourNameToTokenType.get(input.readString());
    }
}