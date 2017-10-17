// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Set;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$4 extends CommonProcessors.CollectProcessor<OCSymbol> {
    final /* synthetic */ PrefixMatcher val$prefixMatcher;
    final /* synthetic */ String val$text;
    final /* synthetic */ Set val$set;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        if ((ocSymbol instanceof OCFunctionSymbol || ClassSymbolsProvider.access$300(ocSymbol)) && this.val$prefixMatcher.prefixMatches(((OCSymbolWithQualifiedName)ocSymbol).getNameWithParent())) {
            final OCQualifiedName resolvedQualifiedName = ((OCSymbolWithQualifiedName)ocSymbol).getResolvedQualifiedName();
            if (resolvedQualifiedName != null) {
                final String canonicalName = resolvedQualifiedName.getCanonicalName(true);
                return ClassSymbolsProvider.this.createPrefixMatcher(this.val$text).prefixMatches(canonicalName) && this.val$set.add(canonicalName);
            }
        }
        return false;
    }
}