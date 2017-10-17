// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.util.ArrayUtil;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCReference;

class OCQualifiedDesignatorImpl$1 implements OCReference {
    public PsiElement getElement() {
        return (PsiElement)OCQualifiedDesignatorImpl.this;
    }
    
    public TextRange getRangeInElement() {
        return OCElementUtil.getRangeInParent(OCQualifiedDesignatorImpl.this.getNode().getLastChildNode());
    }
    
    public PsiElement resolve() {
        return OCQualifiedDesignatorImpl.this.resolve();
    }
    
    @NotNull
    public String getCanonicalText() {
        String symbolName;
        try {
            symbolName = OCQualifiedDesignatorImpl.this.getSymbolName();
            if (symbolName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl$1", "getCanonicalText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return symbolName;
    }
    
    public PsiElement handleElementRename(final String name) throws IncorrectOperationException {
        return OCQualifiedDesignatorImpl.this.setName(name);
    }
    
    @Override
    public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl$1", "bindToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.getElement();
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl$1", "bindToElement"));
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
    
    public boolean isReferenceTo(final PsiElement psiElement) {
        try {
            if (!(psiElement instanceof OCSymbolDeclarator)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return Comparing.equal((Object)this.resolveToSymbol(), ((OCSymbolDeclarator)psiElement).getSymbol());
    }
    
    @NotNull
    public Object[] getVariants() {
        Object[] empty_OBJECT_ARRAY;
        try {
            empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
            if (empty_OBJECT_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl$1", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return empty_OBJECT_ARRAY;
    }
    
    public boolean isSoft() {
        return false;
    }
    
    public OCSymbol resolveToSymbol() {
        return OCQualifiedDesignatorImpl.this.resolveToSymbol();
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}