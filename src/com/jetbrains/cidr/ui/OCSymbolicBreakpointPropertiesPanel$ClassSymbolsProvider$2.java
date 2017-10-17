// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$2 extends CommonProcessors.CollectProcessor<OCSymbol> {
    final /* synthetic */ Set val$set;
    
    public boolean accept(final OCSymbol ocSymbol) {
        return this.val$set.add(ocSymbol.getPresentableName());
    }
}