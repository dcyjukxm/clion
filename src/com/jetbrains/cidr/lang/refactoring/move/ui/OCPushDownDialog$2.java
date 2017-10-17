// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import java.util.ArrayList;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.intellij.openapi.util.Computable;

class OCPushDownDialog$2 implements Computable<List<OCSymbol>> {
    final /* synthetic */ Computable val$inheritanceFinder;
    
    public List<OCSymbol> compute() {
        return new ArrayList<OCSymbol>((Collection<? extends OCSymbol>)this.val$inheritanceFinder.compute());
    }
}