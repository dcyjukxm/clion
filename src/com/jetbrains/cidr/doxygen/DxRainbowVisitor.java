// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.codeInsight.daemon.impl.HighlightVisitor;
import com.intellij.psi.PsiComment;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiDocCommentBase;
import com.jetbrains.cidr.doxygen.psi.DxNamedElement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.doxygen.psi.DxFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.daemon.RainbowVisitor;

public class DxRainbowVisitor extends RainbowVisitor
{
    @Override
    public boolean suitableForFile(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/doxygen/DxRainbowVisitor", "suitableForFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return psiFile instanceof DxFile;
    }
    
    @Override
    public void visit(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/DxRainbowVisitor", "visit"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (psiElement instanceof DxNamedElement) {
            final String name = ((DxNamedElement)psiElement).getName();
            if (name != null) {
                final PsiComment hostComment = DoxygenUtil.getHostComment(psiElement);
                if (hostComment instanceof PsiDocCommentBase) {
                    PsiElement psiElement2 = ((PsiDocCommentBase)hostComment).getOwner();
                    if (psiElement2 == null) {
                        psiElement2 = hostComment.getParent();
                    }
                    if (psiElement2 != null) {
                        this.addInfo(this.getInfo(psiElement2, psiElement, name, null));
                    }
                }
            }
        }
    }
    
    @NotNull
    @Override
    public HighlightVisitor clone() {
        DxRainbowVisitor dxRainbowVisitor;
        try {
            dxRainbowVisitor = new DxRainbowVisitor();
            if (dxRainbowVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxRainbowVisitor", "clone"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return dxRainbowVisitor;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
