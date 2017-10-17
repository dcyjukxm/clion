// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveModifyableVisitor;

static final class OCBindUtil$4 extends OCRecursiveModifyableVisitor {
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        if (OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocQualifiedExpression)) {
            return;
        }
        super.visitQualifiedExpression(ocQualifiedExpression);
        final OCExpression qualifier = ocQualifiedExpression.getQualifier();
        final OCSymbol resolveToSymbol = ocQualifiedExpression.resolveToSymbol();
        final PsiElement copy = ocQualifiedExpression.copy();
        if (resolveToSymbol instanceof OCSymbolWithParent && qualifier instanceof OCReferenceExpression && ((OCReferenceExpression)qualifier).isSelfSuperOrThis()) {
            final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)OCChangeUtil.replaceHandlingMacros((PsiElement)ocQualifiedExpression, (PsiElement)OCElementFactory.expressionFromText(resolveToSymbol.getName(), (PsiElement)ocQualifiedExpression));
            if (!OCBindUtil.access$500(ocReferenceExpression.getReferenceElement().getReference(), resolveToSymbol, false)) {
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocReferenceExpression, copy);
            }
        }
    }
}