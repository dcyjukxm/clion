// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.psi.impl.source.resolve.ResolveCache;

private static class MyResolver implements ResolveCache.AbstractResolver<OCReferenceElement, Pair<OCSymbol, Boolean>>
{
    private OCResolveContext myContext;
    
    public MyResolver(@NotNull final OCResolveContext myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl$MyResolver", "<init>"));
        }
        this.myContext = myContext;
    }
    
    @Override
    public Pair<OCSymbol, Boolean> resolve(@NotNull final OCReferenceElement ocReferenceElement, final boolean b) {
        try {
            if (ocReferenceElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl$MyResolver", "resolve"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCReferenceElementImpl ocReferenceElementImpl = (OCReferenceElementImpl)ocReferenceElement;
        final OCSymbol resolveToSymbol = ocReferenceElementImpl.resolveToSymbol(OCReferenceElementImpl.access$000(ocReferenceElementImpl, this.myContext), this.myContext, false, false, false);
        try {
            if (resolveToSymbol != null) {
                return (Pair<OCSymbol, Boolean>)new Pair((Object)resolveToSymbol, (Object)true);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Pair<OCSymbol, Boolean>)new Pair((Object)ocReferenceElementImpl.resolveToSymbol(null, this.myContext, false, false, false), (Object)false);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
