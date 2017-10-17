// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.psi.PsiElement;

enum OCDeclarationKind$6
{
    @Override
    public boolean isKindOf(final PsiElement psiElement) {
        return psiElement instanceof OCFunctionDeclaration && !OCDeclarationKind$6.Function.isKindOf(psiElement);
    }
}