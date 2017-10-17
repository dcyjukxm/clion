// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.resolve;

import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.resolve.ResolveCache;

class CMakeCommandReference$1 implements ResolveCache.PolyVariantResolver<CMakeCommandReference> {
    @NotNull
    @Override
    public ResolveResult[] resolve(@NotNull final CMakeCommandReference cMakeCommandReference, final boolean b) {
        try {
            if (cMakeCommandReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference$1", "resolve"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ResolveResult[] access$000;
        try {
            access$000 = CMakeCommandReference.access$000(cMakeCommandReference);
            if (access$000 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference$1", "resolve"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return access$000;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}