// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCCheckImplementedMethodsProcessor$1 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    final /* synthetic */ OCMethodSymbol val$intfMethodSymbol;
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        return ocMethodSymbol.isStatic() != this.val$intfMethodSymbol.isStatic() || super.process((Object)ocMethodSymbol);
    }
}