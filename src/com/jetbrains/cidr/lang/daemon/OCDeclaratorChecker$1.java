// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCDeclaratorChecker$1 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    final /* synthetic */ OCPropertySymbol val$property;
    
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        return ocMethodSymbol.getGeneratedFromProperty() == this.val$property;
    }
}