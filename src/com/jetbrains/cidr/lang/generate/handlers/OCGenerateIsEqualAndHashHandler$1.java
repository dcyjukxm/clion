// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

static final class OCGenerateIsEqualAndHashHandler$1 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    final /* synthetic */ PsiElement val$context;
    final /* synthetic */ OCType val$type;
    
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        final String s = "isEqualTo";
        if (ocMethodSymbol.getName().startsWith(s) && ocMethodSymbol.getName().length() > s.length() + 2 && ocMethodSymbol.getSelectors().size() == 1) {
            final OCDeclaratorSymbol parameter = ocMethodSymbol.getSelectors().get(0).getParameter();
            return parameter != null && parameter.getType().resolve(this.val$context.getContainingFile()).isCompatible(this.val$type, this.val$context);
        }
        return false;
    }
}