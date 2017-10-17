// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

static final class OCUnusedTemplateParameterInspection$2 extends CommonProcessors.FindProcessor<OCSymbol> {
    final /* synthetic */ OCSymbolWithQualifiedName val$finalParentSymbol;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol != this.val$finalParentSymbol;
    }
}