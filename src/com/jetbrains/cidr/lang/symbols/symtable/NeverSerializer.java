// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.jetbrains.cidr.lang.OCLog;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

public class NeverSerializer extends FieldSerializer<Object>
{
    public NeverSerializer(final Kryo kryo, final Class clazz) {
        super(kryo, clazz);
    }
    
    public void write(final Kryo kryo, final Output output, final Object o) {
        OCLog.LOG.error("should never serialize objects of class " + o.getClass());
        super.write(kryo, output, o);
    }
    
    public Object read(final Kryo kryo, final Input input, final Class<Object> clazz) {
        OCLog.LOG.error("should never deserialize objects of class " + clazz);
        return super.read(kryo, input, (Class)clazz);
    }
}
