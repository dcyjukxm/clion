// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import java.util.Iterator;
import com.intellij.psi.PsiElementResolveResult;
import java.util.ArrayList;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public abstract class OCPolyVariantReferenceImpl<T extends OCSymbol> implements OCPolyVariantReferenceWithContext<T>
{
    public PsiElement resolve() {
        final OCSymbol<PsiElement> resolveToSymbol = this.resolveToSymbol();
        try {
            if (resolveToSymbol != null) {
                return resolveToSymbol.locateDefinition();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    public T resolveToSymbol() {
        return (T)ContainerUtil.getFirstItem((List)this.resolveToSymbols());
    }
    
    @Nullable
    @Override
    public T resolveToSymbol(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/references/OCPolyVariantReferenceImpl", "resolveToSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (T)ContainerUtil.getFirstItem((List)this.resolveToSymbols(ocResolveContext));
    }
    
    @NotNull
    @Override
    public List<T> resolveToSymbols(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/references/OCPolyVariantReferenceImpl", "resolveToSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<T> resolveToSymbols;
        try {
            resolveToSymbols = this.resolveToSymbols();
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCPolyVariantReferenceImpl", "resolveToSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return resolveToSymbols;
    }
    
    public boolean isReferenceTo(final PsiElement psiElement) {
        try {
            if (!(psiElement instanceof OCSymbolDeclarator)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.resolveToSymbols().contains(((OCSymbolDeclarator)psiElement).getSymbol());
    }
    
    @NotNull
    public Object[] getVariants() {
        PsiReference[] empty_ARRAY;
        try {
            empty_ARRAY = OCPolyVariantReferenceImpl.EMPTY_ARRAY;
            if (empty_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCPolyVariantReferenceImpl", "getVariants"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return empty_ARRAY;
    }
    
    @NotNull
    public ResolveResult[] multiResolve(final boolean b) {
        final List<OCSymbol<PsiElement>> resolveToSymbols = this.resolveToSymbols();
        final ArrayList<PsiElementResolveResult> list = new ArrayList<PsiElementResolveResult>();
        final Iterator<OCSymbol<PsiElement>> iterator = resolveToSymbols.iterator();
        while (iterator.hasNext()) {
            final PsiElement locateDefinition = iterator.next().locateDefinition();
            try {
                if (locateDefinition == null) {
                    continue;
                }
                list.add(new PsiElementResolveResult(locateDefinition, true));
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        ResolveResult[] array;
        try {
            array = list.toArray(new ResolveResult[list.size()]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCPolyVariantReferenceImpl", "multiResolve"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return array;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
