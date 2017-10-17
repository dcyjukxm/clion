// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.cpp.OCLocalFunctionSymbol;

class FileSymbolTableSerializer$17 extends ProjectAndFileOwnerSerializer<OCLocalFunctionSymbol> {
    @Override
    public OCLocalFunctionSymbol createInstance(final Kryo kryo, final Input input, final Class clazz) {
        return new OCLocalFunctionSymbol();
    }
}