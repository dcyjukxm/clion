// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.ResolveCache;

private static class ExprValueCategoryResolver implements ResolveCache.AbstractResolver<PsiReference, OCExprValueCategory>
{
    @Override
    public OCExprValueCategory resolve(@NotNull final PsiReference psiReference, final boolean b) {
        try {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$ExprValueCategoryResolver", "resolve"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCExprValueCategory.classify(((OCQualifiedExpression)psiReference.getElement()).getQualifier());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
