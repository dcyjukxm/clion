// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.PsiElement;

enum OCDeclarationKind$9
{
    @Override
    public boolean isKindOf(final PsiElement psiElement) {
        return psiElement instanceof OCMethod && ((OCMethod)psiElement).isInstanceMethod();
    }
}