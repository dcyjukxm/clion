// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$51 extends FieldSerializer<OCSymbolReference.TrueSymbolFilter> {
    protected OCSymbolReference.TrueSymbolFilter create(final Kryo kryo, final Input input, final Class<OCSymbolReference.TrueSymbolFilter> clazz) {
        return new OCSymbolReference.TrueSymbolFilter();
    }
    
    public OCSymbolReference.TrueSymbolFilter read(final Kryo kryo, final Input input, final Class<OCSymbolReference.TrueSymbolFilter> clazz) {
        return OCSymbolReference.SymbolFilter.NONE;
    }
}