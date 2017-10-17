// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Set;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$7 extends CommonProcessors.CollectProcessor<OCSymbol> {
    final /* synthetic */ PrefixMatcher val$prefixMatcher;
    final /* synthetic */ Set val$set;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol instanceof OCMethodSymbol && this.val$prefixMatcher.prefixMatches(ocSymbol.getPresentableName()) && this.val$set.add(ocSymbol.getPresentableName());
    }
}