// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCEscapedValuesChecker$1 extends OCRecursiveVisitor {
    final /* synthetic */ PsiElement val$codeFragment;
    final /* synthetic */ List val$result;
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
        if (ocBlockExpression != this.val$codeFragment && OCEscapedValuesChecker.this.isGoodWrite((PsiElement)ocBlockExpression)) {
            final PsiElement psiElement = (PsiElement)ContainerUtil.getFirstItem((List)OCCodeInsightUtil.getScopeAndKind((PsiElement)ocBlockExpression).getFirst());
            if (psiElement != null && OCEscapedValuesChecker.access$000((PsiElement)ocBlockExpression, psiElement.getTextRange())) {
                this.val$result.add(ocBlockExpression);
            }
        }
    }
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
        if (resolveToSymbol instanceof OCDeclaratorSymbol && resolveToSymbol.getKind() == OCSymbolKind.LOCAL_VARIABLE && resolveToSymbol.getResolvedType() instanceof OCArrayType && !((OCDeclaratorSymbol)resolveToSymbol).isFriendOrStatic() && OCEscapedValuesChecker.access$000((PsiElement)ocReferenceExpression, null)) {
            this.val$result.add(ocReferenceExpression);
        }
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        super.visitUnaryExpression(ocUnaryExpression);
        if (OCEscapedValuesChecker.this.isGoodWrite((PsiElement)ocUnaryExpression) && OCEscapedValuesChecker.access$000((PsiElement)ocUnaryExpression, null)) {
            this.val$result.add(ocUnaryExpression);
        }
    }
}