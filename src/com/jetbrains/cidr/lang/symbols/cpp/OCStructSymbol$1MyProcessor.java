// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.intellij.util.Processor;

class 1MyProcessor implements Processor<OCFunctionSymbol>
{
    boolean wasConstructor;
    boolean wasDefaultConstructor;
    
    public boolean process(final OCFunctionSymbol ocFunctionSymbol) {
        this.wasConstructor = true;
        this.wasDefaultConstructor |= ocFunctionSymbol.canBeCalledWithoutArguments();
        return true;
    }
    
    public boolean hasDefaultConstructor() {
        return !this.wasConstructor || this.wasDefaultConstructor;
    }
}
