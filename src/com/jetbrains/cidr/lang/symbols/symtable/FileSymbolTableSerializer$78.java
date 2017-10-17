// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.intellij.openapi.util.TextRange;
import com.esotericsoftware.kryo.Serializer;

class FileSymbolTableSerializer$78 extends Serializer<TextRange> {
    public void write(final Kryo kryo, final Output output, final TextRange textRange) {
        output.writeInt(textRange.getStartOffset(), true);
        output.writeInt(textRange.getEndOffset(), true);
    }
    
    public TextRange read(final Kryo kryo, final Input input, final Class<TextRange> clazz) {
        return new TextRange(input.readInt(true), input.readInt(true));
    }
}