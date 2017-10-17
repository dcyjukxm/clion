// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.resolve.references.OCPolyVariantReferenceImpl;

class OCCppNamespaceQualifierImpl$2 extends OCPolyVariantReferenceImpl<OCSymbol> {
    @NotNull
    @Override
    public List<OCSymbol> resolveToSymbols() {
        List<OCSymbol> resolveToSymbols;
        try {
            resolveToSymbols = OCCppNamespaceQualifierImpl.this.resolveToSymbols();
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return resolveToSymbols;
    }
    
    @NotNull
    @Override
    public List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        List<OCSymbol> resolveToSymbols;
        try {
            resolveToSymbols = OCCppNamespaceQualifierImpl.this.resolveToSymbols(ocResolveContext);
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return resolveToSymbols;
    }
    
    public PsiElement getElement() {
        return (PsiElement)OCCppNamespaceQualifierImpl.this;
    }
    
    public TextRange getRangeInElement() {
        return OCCppNamespaceQualifierImpl.this.getRangeInElement();
    }
    
    @NotNull
    public String getCanonicalText() {
        final String name = OCCppNamespaceQualifierImpl.this.getName();
        String s = null;
        Label_0022: {
            try {
                if (name != null) {
                    final String s2;
                    s = (s2 = name);
                    break Label_0022;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            String s2;
            s = (s2 = "");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "getCanonicalText"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    public PsiElement handleElementRename(final String name) throws IncorrectOperationException {
        return OCCppNamespaceQualifierImpl.this.setName(name);
    }
    
    @Override
    public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "bindToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final List resolveToSymbols = ((OCElementWithReferenceBase<OCPolyVariantReference>)OCCppNamespaceQualifierImpl.this).getReference().resolveToSymbols();
        try {
            if (resolveToSymbols.contains(ocSymbol)) {
                return (PsiElement)OCCppNamespaceQualifierImpl.this;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                return (PsiElement)OCCppNamespaceQualifierImpl.this;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final OCQualifiedName resolvedQualifiedName = ((OCSymbolWithQualifiedName)ocSymbol).getResolvedQualifiedName();
        try {
            if (resolvedQualifiedName != null) {
                OCBindUtil.setShortestPossibleName(resolvedQualifiedName, OCCppNamespaceQualifierImpl.this, (OCSymbolWithQualifiedName)ocSymbol);
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return (PsiElement)OCCppNamespaceQualifierImpl.this;
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "bindToElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
        try {
            if (symbol != null) {
                return this.bindToSymbol(symbol);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return psiElement;
    }
    
    public boolean isSoft() {
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}