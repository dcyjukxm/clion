// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiElement;

enum OCDeclarationKind$1
{
    @Override
    public boolean isKindOf(final PsiElement psiElement) {
        return psiElement instanceof OCDeclaration && ((OCDeclaration)psiElement).isTypedef();
    }
}