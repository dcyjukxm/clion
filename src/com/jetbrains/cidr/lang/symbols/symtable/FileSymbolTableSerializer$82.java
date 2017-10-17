// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.Collection;
import java.util.Collections;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.util.OCImmutableList;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$82 extends FieldSerializer<OCImmutableList> {
    public void write(final Kryo kryo, final Output output, final OCImmutableList list) {
        final boolean b = list.size() > 0;
        output.writeBoolean(b);
        if (b) {
            super.write(kryo, output, (Object)list);
        }
    }
    
    public OCImmutableList read(final Kryo kryo, final Input input, final Class<OCImmutableList> clazz) {
        return (OCImmutableList)(input.readBoolean() ? super.read(kryo, input, (Class)clazz) : OCImmutableList.emptyList());
    }
    
    protected OCImmutableList create(final Kryo kryo, final Input input, final Class<OCImmutableList> clazz) {
        return new OCImmutableList((Collection<T>)Collections.emptyList());
    }
}