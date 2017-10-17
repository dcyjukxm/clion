// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.CommonProcessors;

class OCGenerateGetterSetterContext$1 extends CommonProcessors.FindFirstProcessor<OCFunctionSymbol> {
    protected boolean accept(final OCFunctionSymbol ocFunctionSymbol) {
        return ocFunctionSymbol.getParameterSymbols().isEmpty();
    }
}