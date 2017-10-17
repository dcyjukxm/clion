// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

static final class OCObjectTypeContext$2 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    final /* synthetic */ int val$argumentsCnt;
    final /* synthetic */ List val$argumentTypes;
    final /* synthetic */ PsiFile val$context;
    
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        if (ocMethodSymbol.isVararg()) {
            return false;
        }
        final List<OCMethodSymbol.SelectorPartSymbol> selectors = ocMethodSymbol.getSelectors();
        if (selectors.size() < this.val$argumentsCnt) {
            return false;
        }
        for (int i = 0; i < this.val$argumentsCnt; ++i) {
            final OCType ocType = this.val$argumentTypes.get(i);
            final OCDeclaratorSymbol parameter = selectors.get(i).getParameter();
            if (ocType != null && parameter != null && !parameter.getType().resolve(this.val$context).isCompatible(ocType, (PsiElement)this.val$context)) {
                return false;
            }
        }
        return true;
    }
}