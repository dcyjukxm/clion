// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiElement;

enum OCDeclarationKind$2
{
    @Override
    public boolean isKindOf(final PsiElement psiElement) {
        if (psiElement instanceof OCDeclaration) {
            final OCTypeElement typeElement = ((OCDeclaration)psiElement).getTypeElement();
            if (this.isKindOf((typeElement != null) ? typeElement.getFirstChild() : null)) {
                return true;
            }
        }
        return super.isKindOf(psiElement);
    }
}