// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

private abstract static class MyVisitor extends OCRecursiveVisitor
{
    protected boolean replaceAndVisit(@NotNull final OCExpression ocExpression, @NotNull final String s) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$MyVisitor", "replaceAndVisit"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "converted", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$MyVisitor", "replaceAndVisit"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        super.visitParenthesizedExpression(replace(ocExpression, s));
        return true;
    }
    
    @Nullable
    protected static OCParenthesizedExpression replace(@NotNull final OCExpression ocExpression, @NotNull final String s) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$MyVisitor", "replace"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "converted", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$MyVisitor", "replace"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCExpression expressionFromText = OCElementFactory.expressionFromText("(" + s + ")", (PsiElement)ocExpression, false);
        try {
            if (expressionFromText == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (OCParenthesizedExpression)ocExpression.replace((PsiElement)expressionFromText);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
