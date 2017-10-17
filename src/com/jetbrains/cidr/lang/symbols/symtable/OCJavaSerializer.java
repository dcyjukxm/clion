// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.io.InputStream;
import java.io.ObjectInputStream;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryo.KryoException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import com.intellij.openapi.util.Pair;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;

public class OCJavaSerializer extends Serializer
{
    public void write(final Kryo kryo, final Output output, final Object o) {
        try {
            final ObjectMap graphContext = kryo.getGraphContext();
            final Pair create = Pair.create((Object)this, (Object)output);
            ObjectOutputStream objectOutputStream = (ObjectOutputStream)graphContext.get((Object)create);
            if (objectOutputStream == null) {
                objectOutputStream = new ObjectOutputStream((OutputStream)output);
                graphContext.put((Object)create, (Object)objectOutputStream);
            }
            objectOutputStream.writeObject(o);
            objectOutputStream.flush();
        }
        catch (Exception ex) {
            throw new KryoException("Error during Java serialization.", (Throwable)ex);
        }
    }
    
    public Object read(final Kryo kryo, final Input input, final Class clazz) {
        try {
            final ObjectMap graphContext = kryo.getGraphContext();
            final Pair create = Pair.create((Object)this, (Object)input);
            ObjectInputStream objectInputStream = (ObjectInputStream)graphContext.get((Object)create);
            if (objectInputStream == null) {
                objectInputStream = new ObjectInputStream((InputStream)input);
                graphContext.put((Object)create, (Object)objectInputStream);
            }
            return objectInputStream.readObject();
        }
        catch (Exception ex) {
            throw new KryoException("Error during Java deserialization.", (Throwable)ex);
        }
    }
}
