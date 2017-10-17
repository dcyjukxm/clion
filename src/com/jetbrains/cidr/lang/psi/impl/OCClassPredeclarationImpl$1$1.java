// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCClassPredeclarationImpl$1$1 extends CommonProcessors.CollectProcessor<OCSymbol> {
    final /* synthetic */ boolean val$isProtocol;
    
    public boolean process(final OCSymbol ocSymbol) {
        return (ocSymbol.getKind() != OCSymbolKind.INTERFACE && ocSymbol.getKind() != OCSymbolKind.PROTOCOL) || ocSymbol.isPredeclaration() || ((OCClassSymbol)ocSymbol).getCategoryName() != null || this.val$isProtocol != (ocSymbol.getKind() == OCSymbolKind.PROTOCOL) || super.process((Object)ocSymbol);
    }
}