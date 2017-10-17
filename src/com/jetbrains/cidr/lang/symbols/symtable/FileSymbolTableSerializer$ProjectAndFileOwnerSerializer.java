// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;

public abstract class ProjectAndFileOwnerSerializer<T extends ProjectAndVirtualFileOwner> extends FieldSerializer<T>
{
    public ProjectAndFileOwnerSerializer(final Class<T> clazz) {
        super(FileSymbolTableSerializer.this.getKryo(), (Class)clazz);
    }
    
    public void write(final Kryo kryo, final Output output, final T t) {
        output.writeBoolean(t.getProject() != null);
        output.writeBoolean(t.getContainingFile() != null);
        super.write(kryo, output, (Object)t);
    }
    
    public final T create(final Kryo kryo, final Input input, final Class clazz) {
        final boolean boolean1 = input.readBoolean();
        final boolean boolean2 = input.readBoolean();
        final ProjectAndVirtualFileOwner instance = this.createInstance(kryo, input, clazz);
        instance.init(boolean1 ? FileSymbolTableSerializer.access$000(FileSymbolTableSerializer.this) : null, boolean2 ? FileSymbolTableSerializer.access$100(FileSymbolTableSerializer.this) : null);
        return (T)instance;
    }
    
    public abstract T createInstance(final Kryo p0, final Input p1, final Class p2);
}
