// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiElement;

enum OCDeclarationKind$4
{
    @Override
    public boolean isKindOf(final PsiElement psiElement) {
        return psiElement instanceof OCDeclaration && !OCDeclarationKind$4.Typedef.isKindOf(psiElement) && !OCDeclarationKind$4.Constant.isKindOf(psiElement) && !OCDeclarationKind$4.Function.isKindOf(psiElement) && !OCDeclarationKind$4.FunctionPredecl.isKindOf(psiElement);
    }
}