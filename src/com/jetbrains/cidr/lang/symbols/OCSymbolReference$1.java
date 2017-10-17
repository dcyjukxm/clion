// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Set;
import com.intellij.util.CommonProcessors;

class OCSymbolReference$1 extends CommonProcessors.CollectProcessor<OCSymbol> {
    final /* synthetic */ Set val$set;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return this.val$set.add(ocSymbol);
    }
}