// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

static final class OCNewExpressionCompletionContributor$InnerCompletionProvider$1 extends OCSmartCompletionContributor.TypeMatchingCondition<OCSymbol> {
    @Override
    protected OCType getSymbolType(final OCSymbol ocSymbol, final PsiElement psiElement) {
        return OCPointerType.to(super.getSymbolType(ocSymbol, psiElement));
    }
}