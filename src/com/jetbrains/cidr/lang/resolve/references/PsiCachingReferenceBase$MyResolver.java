// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.ResolveCache;

private static class MyResolver implements ResolveCache.Resolver
{
    private static final MyResolver INSTANCE;
    
    @Nullable
    @Override
    public PsiElement resolve(@NotNull final PsiReference psiReference, final boolean b) {
        try {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/resolve/references/PsiCachingReferenceBase$MyResolver", "resolve"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ((PsiCachingReferenceBase)psiReference).resolveInner();
    }
    
    static {
        INSTANCE = new MyResolver();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
