// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.parser.OCKeywordElementType;
import com.esotericsoftware.kryo.Serializer;

class FileSymbolTableSerializer$49 extends Serializer<OCKeywordElementType> {
    public void write(final Kryo kryo, final Output output, final OCKeywordElementType ocKeywordElementType) {
        output.writeString(ocKeywordElementType.toString());
    }
    
    public OCKeywordElementType read(final Kryo kryo, final Input input, final Class<OCKeywordElementType> clazz) {
        return OCTokenTypes.ourNameToTokenType.get(input.readString());
    }
}