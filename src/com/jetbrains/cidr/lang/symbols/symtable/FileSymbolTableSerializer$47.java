// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.esotericsoftware.kryo.Serializer;

class FileSymbolTableSerializer$47 extends Serializer<OCPunctuatorElementType> {
    public void write(final Kryo kryo, final Output output, final OCPunctuatorElementType ocPunctuatorElementType) {
        output.writeString(ocPunctuatorElementType.toString());
    }
    
    public OCPunctuatorElementType read(final Kryo kryo, final Input input, final Class<OCPunctuatorElementType> clazz) {
        return OCTokenTypes.ourNameToTokenType.get(input.readString());
    }
}