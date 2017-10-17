// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public abstract class OCExpressionSurrounder extends OCSurrounder
{
    @Override
    public boolean isApplicable(@NotNull final PsiElement[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/editor/surround/OCExpressionSurrounder", "isApplicable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0072: {
            try {
                if (!super.isApplicable(array)) {
                    return false;
                }
                final OCExpressionSurrounder ocExpressionSurrounder = this;
                final PsiElement[] array2 = array;
                final int n = 0;
                final PsiElement psiElement = array2[n];
                final OCExpression ocExpression = (OCExpression)psiElement;
                final boolean b = ocExpressionSurrounder.isApplicable(ocExpression);
                if (b) {
                    break Label_0072;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCExpressionSurrounder ocExpressionSurrounder = this;
                final PsiElement[] array2 = array;
                final int n = 0;
                final PsiElement psiElement = array2[n];
                final OCExpression ocExpression = (OCExpression)psiElement;
                final boolean b = ocExpressionSurrounder.isApplicable(ocExpression);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public abstract boolean isApplicable(final OCExpression p0);
    
    public TextRange surroundElements(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiElement[] array) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/editor/surround/OCExpressionSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/surround/OCExpressionSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/editor/surround/OCExpressionSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return this.surroundExpression(project, editor, (OCExpression)array[0]);
    }
    
    public abstract TextRange surroundExpression(final Project p0, final Editor p1, final OCExpression p2) throws IncorrectOperationException;
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
