// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.find.findUsages.FindUsagesHandler;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;

public class OCFindUsagesHandlerFactory extends FindUsagesHandlerFactory
{
    @Override
    public boolean canFindUsages(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandlerFactory", "canFindUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!psiElement.isValid() || !new OCFindUsagesProvider().canFindUsagesFor(psiElement)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return true;
        b = false;
        return b;
    }
    
    @Override
    public FindUsagesHandler createFindUsagesHandler(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandlerFactory", "createFindUsagesHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.canFindUsages(psiElement)) {
                return new OCFindUsagesHandler(a(psiElement)) {};
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    private static PsiElement a(final PsiElement psiElement) {
        try {
            if (!(psiElement instanceof OCSymbolDeclarator)) {
                return psiElement;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
        Label_0049: {
            try {
                if (symbol == null) {
                    return psiElement;
                }
                final OCSymbol ocSymbol = symbol;
                final boolean b = ocSymbol.isDefinition();
                if (!b) {
                    return psiElement;
                }
                break Label_0049;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbol ocSymbol = symbol;
                final boolean b = ocSymbol.isDefinition();
                if (!b) {
                    return psiElement;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCSymbol associatedSymbol = symbol.getAssociatedSymbol();
        try {
            if (associatedSymbol == null) {
                return psiElement;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final PsiElement locateDefinition = associatedSymbol.locateDefinition();
        try {
            if (locateDefinition != null) {
                return locateDefinition;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return psiElement;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
