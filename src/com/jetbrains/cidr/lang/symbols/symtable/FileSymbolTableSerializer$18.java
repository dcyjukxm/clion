// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;

class FileSymbolTableSerializer$18 extends ProjectAndFileOwnerSerializer<OCNamespaceSymbol> {
    @Override
    public OCNamespaceSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
        return new OCNamespaceSymbol();
    }
}