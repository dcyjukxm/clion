// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.util.OCNumber;
import com.esotericsoftware.kryo.Serializer;

class FileSymbolTableSerializer$79 extends Serializer<OCNumber> {
    public void write(final Kryo kryo, final Output output, final OCNumber ocNumber) {
        output.writeByte(ocNumber.getSizeInBytes());
        output.writeBoolean(ocNumber.isSigned());
        final byte[] byteArray = ocNumber.toByteArray();
        output.writeByte(byteArray.length);
        if (byteArray.length > 0) {
            output.write(byteArray);
        }
    }
    
    public OCNumber read(final Kryo kryo, final Input input, final Class<OCNumber> clazz) {
        final byte byte1 = input.readByte();
        final boolean boolean1 = input.readBoolean();
        final byte[] array = new byte[input.readByte()];
        input.readBytes(array);
        return new OCNumber(array, byte1, boolean1);
    }
}