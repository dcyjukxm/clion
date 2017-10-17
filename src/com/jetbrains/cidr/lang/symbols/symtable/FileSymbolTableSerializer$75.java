// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Output;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$75 extends FieldSerializer<OCSimpleTypeSubstitution> {
    protected OCSimpleTypeSubstitution create(final Kryo kryo, final Input input, final Class<OCSimpleTypeSubstitution> clazz) {
        return new OCSimpleTypeSubstitution();
    }
    
    public OCSimpleTypeSubstitution read(final Kryo kryo, final Input input, final Class<OCSimpleTypeSubstitution> clazz) {
        if (input.readBoolean()) {
            return OCTypeSubstitution.ID;
        }
        return (OCSimpleTypeSubstitution)super.read(kryo, input, (Class)clazz);
    }
    
    public void write(final Kryo kryo, final Output output, final OCSimpleTypeSubstitution ocSimpleTypeSubstitution) {
        final boolean b = ocSimpleTypeSubstitution == OCTypeSubstitution.ID;
        output.writeBoolean(b);
        if (!b) {
            super.write(kryo, output, (Object)ocSimpleTypeSubstitution);
        }
    }
}