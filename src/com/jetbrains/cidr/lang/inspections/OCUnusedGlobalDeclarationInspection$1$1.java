// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCUnusedGlobalDeclarationInspection$1$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ OCSymbol val$symbol;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return (this.val$symbol instanceof OCFunctionSymbol && this.val$symbol.isDefinition() && ocSymbol.isPredeclaration()) || (this.val$symbol instanceof OCDeclaratorSymbol && this.val$symbol.isPredeclaration() && ocSymbol.isDefinition()) || (ocSymbol instanceof OCDeclaratorSymbol && ((OCDeclaratorSymbol)ocSymbol).isExtern());
    }
}