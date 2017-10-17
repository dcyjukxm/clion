// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.intellij.patterns.PatternCondition;

public static class IsStringLiteral extends PatternCondition<OCLiteralExpression>
{
    public IsStringLiteral() {
        super((String)null);
    }
    
    public boolean accepts(@NotNull final OCLiteralExpression ocLiteralExpression, final ProcessingContext processingContext) {
        try {
            if (ocLiteralExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "literal", "com/jetbrains/cidr/lang/resolve/references/OCResourceReferenceContributor$IsStringLiteral", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (OCElementUtil.getStringLiteral((PsiElement)ocLiteralExpression) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
