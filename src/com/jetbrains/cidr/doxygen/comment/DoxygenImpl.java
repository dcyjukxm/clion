// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.jetbrains.cidr.doxygen.DoxygenUtil;
import com.intellij.psi.PsiComment;
import java.util.List;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenCommentGroup;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.documentation.doxygen.api.Doxygen;

public class DoxygenImpl extends Doxygen
{
    @Override
    public DoxygenCommentGroup getCommentGroup(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/comment/DoxygenImpl", "getCommentGroup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new DxCommentProcessor(psiElement).getCommentGroup();
    }
    
    @NotNull
    @Override
    public List<PsiComment> getCommentScope(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/comment/DoxygenImpl", "getCommentScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<PsiComment> commentScope;
        try {
            commentScope = DxCommentProcessorUtil.getCommentScope(psiElement);
            if (commentScope == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DoxygenImpl", "getCommentScope"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return commentScope;
    }
    
    @Override
    public boolean isDoxygenComment(@NotNull final PsiComment psiComment) {
        try {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/comment/DoxygenImpl", "isDoxygenComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return DoxygenUtil.isDoxygenComment(psiComment);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
