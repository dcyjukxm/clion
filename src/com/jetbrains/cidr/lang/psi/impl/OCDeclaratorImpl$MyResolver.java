// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.impl.source.resolve.ResolveCache;

private static class MyResolver implements ResolveCache.AbstractResolver<MyReference, OCSymbol>
{
    @Override
    public OCSymbol resolve(@NotNull final MyReference myReference, final boolean b) {
        try {
            if (myReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyResolver", "resolve"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myReference.doResolveToSymbol();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
