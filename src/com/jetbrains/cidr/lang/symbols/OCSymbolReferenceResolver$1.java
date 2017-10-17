// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.util.CommonProcessors;

class OCSymbolReferenceResolver$1 extends CommonProcessors.CollectProcessor<OCSymbol> {
    protected boolean accept(final OCSymbol ocSymbol) {
        return !ocSymbol.isPredeclaration();
    }
}