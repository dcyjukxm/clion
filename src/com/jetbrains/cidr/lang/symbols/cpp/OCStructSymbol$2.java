// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.intellij.util.CommonProcessors;

class OCStructSymbol$2 extends CommonProcessors.FindFirstProcessor<OCFunctionSymbol> {
    protected boolean accept(final OCFunctionSymbol ocFunctionSymbol) {
        return ocFunctionSymbol.canBeCalledWithoutArguments();
    }
}