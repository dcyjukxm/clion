// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

static final class OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$6 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol instanceof OCFunctionSymbol || ocSymbol instanceof OCNamespaceSymbol;
    }
}