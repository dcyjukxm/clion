// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.psi.PsiElement;
import com.esotericsoftware.kryo.io.Input;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCDelegatingSymbol;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;

class FileSymbolTableSerializer$21 extends Serializer {
    public void write(final Kryo kryo, final Output output, final Object o) {
        final OCSymbol rawDelegate = ((OCDelegatingSymbol)o).getRawDelegate();
        kryo.writeClass(output, (Class)rawDelegate.getClass());
        kryo.writeObject(output, (Object)rawDelegate);
    }
    
    public Object read(final Kryo kryo, final Input input, final Class clazz) {
        return new OCDelegatingSymbol((OCSymbol<PsiElement>)kryo.readObject(input, kryo.readClass(input).getType()));
    }
}