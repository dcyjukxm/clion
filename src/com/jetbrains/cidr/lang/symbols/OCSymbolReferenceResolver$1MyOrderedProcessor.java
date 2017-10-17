// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;

class 1MyOrderedProcessor extends OCCommonProcessors.OrderedProcessor<OCSymbol>
{
    final /* synthetic */ Processor val$filteredProcessor;
    
    public 1MyOrderedProcessor(final Processor val$filteredProcessor) {
        this.val$filteredProcessor = val$filteredProcessor;
        super(val$filteredProcessor, new Condition[] { ocSymbol -> ocSymbol.getKind().isTypedefOrAlias(), ocSymbol -> !ocSymbol.isPredeclaration(), Conditions.alwaysTrue() });
    }
}
