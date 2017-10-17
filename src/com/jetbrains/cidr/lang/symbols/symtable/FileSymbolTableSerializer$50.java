// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbolImpl;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$50 extends FieldSerializer<OCMethodSymbolImpl.SelectorPartSymbolImpl> {
    protected OCMethodSymbolImpl.SelectorPartSymbolImpl create(final Kryo kryo, final Input input, final Class<OCMethodSymbolImpl.SelectorPartSymbolImpl> clazz) {
        return new OCMethodSymbolImpl.SelectorPartSymbolImpl();
    }
}