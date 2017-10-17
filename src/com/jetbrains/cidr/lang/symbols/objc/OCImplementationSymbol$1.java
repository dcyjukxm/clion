// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCImplementationSymbol$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ Condition val$condition;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return this.val$condition.value((Object)ocSymbol);
    }
}