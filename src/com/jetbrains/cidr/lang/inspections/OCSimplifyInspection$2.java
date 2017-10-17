// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.psi.PsiElementVisitor;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

static final class OCSimplifyInspection$2 extends SimplifyVisitor {
    @Override
    protected void simplify(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2, final Simplifier simplifier) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$2", "simplify"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement2 != null) {
                OCSimplifyInspection.getSimplifyFix(psiElement, psiElement2).invoke(psiElement.getProject(), (Editor)null, psiElement.getContainingFile());
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.visitElement(psiElement);
    }
    
    public void visitElement(final PsiElement psiElement) {
        final PsiElement[] children = psiElement.getChildren();
        for (int length = children.length, i = 0; i < length; ++i) {
            children[i].accept((PsiElementVisitor)this);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}