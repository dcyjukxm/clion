// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCFileGlobalSymbols$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ OCSymbolKind val$kind;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol.getKind() == this.val$kind && !ocSymbol.isPredeclaration();
    }
}