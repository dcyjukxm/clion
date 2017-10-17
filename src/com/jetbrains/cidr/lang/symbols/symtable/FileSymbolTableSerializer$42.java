// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;

class FileSymbolTableSerializer$42 extends ProjectAndFileOwnerSerializer<OCUsingSymbol> {
    @Override
    public OCUsingSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
        return new OCUsingSymbol();
    }
}