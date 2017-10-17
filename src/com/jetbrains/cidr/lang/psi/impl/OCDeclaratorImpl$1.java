// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCDeclaratorImpl$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ String val$name;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol.getOffset() == OCDeclaratorImpl.this.getTextOffset() && Comparing.equal(this.val$name, ocSymbol.getName());
    }
}