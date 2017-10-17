// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCCheckImplementedMethodsProcessor$2 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    final /* synthetic */ OCMethodSymbol val$intfMethodSymbol;
    final /* synthetic */ OCPropertySymbol val$property;
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        return ocMethodSymbol == this.val$intfMethodSymbol || ocMethodSymbol.getGeneratedFromProperty() != this.val$property || super.process((Object)ocMethodSymbol);
    }
}