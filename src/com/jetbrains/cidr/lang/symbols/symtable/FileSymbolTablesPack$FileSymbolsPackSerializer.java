// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.Iterator;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

static class FileSymbolsPackSerializer extends FieldSerializer<FileSymbolTablesPack>
{
    public FileSymbolsPackSerializer(final Kryo kryo) {
        super(kryo, (Class)FileSymbolTablesPack.class);
    }
    
    protected FileSymbolTablesPack create(final Kryo kryo, final Input input, final Class<FileSymbolTablesPack> clazz) {
        return new FileSymbolTablesPack();
    }
    
    public FileSymbolTablesPack read(final Kryo kryo, final Input input, final Class<FileSymbolTablesPack> clazz) {
        final FileSymbolTablesPack fileSymbolTablesPack = (FileSymbolTablesPack)super.read(kryo, input, (Class)clazz);
        synchronized (fileSymbolTablesPack.getTablesAccessLock()) {
            final Iterator iterator = FileSymbolTablesPack.access$100(fileSymbolTablesPack).iterator();
            while (iterator.hasNext()) {
                FileSymbolTablesPack.access$200(iterator.next());
            }
        }
        return fileSymbolTablesPack;
    }
}
