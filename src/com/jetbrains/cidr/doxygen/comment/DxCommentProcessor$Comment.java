// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.jetbrains.cidr.doxygen.psi.DxDocComment;
import com.jetbrains.cidr.doxygen.DoxygenUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiComment;
import java.util.List;

private static class Comment
{
    private int idx;
    private final List<PsiComment> comments;
    
    public Comment(@NotNull final List<PsiComment> comments) {
        if (comments == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comments", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment", "<init>"));
        }
        this.idx = 0;
        this.comments = comments;
    }
    
    @Nullable
    public PsiElement getFistChild() {
        return this.a(0);
    }
    
    @Nullable
    private PsiElement a(final int n) {
        if (n < this.comments.size()) {
            final DxDocComment convertToDoxygen = DoxygenUtil.convertToDoxygen(this.comments.get(n));
            try {
                if (convertToDoxygen != null) {
                    return convertToDoxygen.getFirstChild();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    public PsiElement nextSibling(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/comment/DxCommentProcessor$Comment", "nextSibling"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement nextSibling = psiElement.getNextSibling();
        try {
            if (nextSibling == null) {
                return this.a(++this.idx);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return nextSibling;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
