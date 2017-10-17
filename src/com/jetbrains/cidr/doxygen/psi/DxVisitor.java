// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElementVisitor;

public class DxVisitor extends PsiElementVisitor
{
    public void visitDocComment(@NotNull final DxDocComment dxDocComment) {
        try {
            if (dxDocComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/doxygen/psi/DxVisitor", "visitDocComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)dxDocComment);
    }
    
    public void visitDocTag(@NotNull final DxDocTag dxDocTag) {
        try {
            if (dxDocTag == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/doxygen/psi/DxVisitor", "visitDocTag"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)dxDocTag);
    }
    
    public void visitParam(@NotNull final DxParam dxParam) {
        try {
            if (dxParam == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/doxygen/psi/DxVisitor", "visitParam"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)dxParam);
    }
    
    public void visitParamId(@NotNull final DxParamId dxParamId) {
        try {
            if (dxParamId == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/doxygen/psi/DxVisitor", "visitParamId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitNamedElement(dxParamId);
    }
    
    public void visitNamedElement(@NotNull final DxNamedElement dxNamedElement) {
        try {
            if (dxNamedElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/doxygen/psi/DxVisitor", "visitNamedElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)dxNamedElement);
    }
    
    public void visitPsiElement(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/doxygen/psi/DxVisitor", "visitPsiElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement(psiElement);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
