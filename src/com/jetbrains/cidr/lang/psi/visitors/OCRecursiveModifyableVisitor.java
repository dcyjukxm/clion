// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.visitors;

import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class OCRecursiveModifyableVisitor extends OCVisitor
{
    public void visitElement(final PsiElement psiElement) {
        super.visitElement(psiElement);
        final PsiElement[] children = psiElement.getChildren();
        for (int length = children.length, i = 0; i < length; ++i) {
            children[i].accept((PsiElementVisitor)this);
        }
    }
}
