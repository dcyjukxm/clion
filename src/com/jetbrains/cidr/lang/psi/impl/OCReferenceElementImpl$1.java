// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCReferenceElementImpl$1 extends CommonProcessors.CollectProcessor<OCSymbol> {
    final /* synthetic */ OCSymbolGroupContext val$symbolContext;
    
    public boolean process(final OCSymbol ocSymbol) {
        return (this.val$symbolContext != null && !this.val$symbolContext.isSuitableSymbol(ocSymbol)) || (ocSymbol instanceof OCClassSymbol && ((OCClassSymbol)ocSymbol).getCategoryName() != null) || super.process((Object)ocSymbol);
    }
}