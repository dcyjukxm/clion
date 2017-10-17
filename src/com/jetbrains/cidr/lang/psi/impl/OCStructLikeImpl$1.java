// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCStructLikeImpl$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ String val$symbolName;
    
    public boolean process(final OCSymbol ocSymbol) {
        return !(ocSymbol instanceof OCStructSymbol) || ocSymbol.getOffset() != OCStructLikeImpl.this.getTextOffset() || !Comparing.equal(this.val$symbolName, ocSymbol.getName()) || super.process((Object)ocSymbol);
    }
}