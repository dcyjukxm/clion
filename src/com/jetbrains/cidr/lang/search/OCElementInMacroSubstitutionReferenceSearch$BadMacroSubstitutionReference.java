// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.util.IncorrectOperationException;
import com.intellij.util.ArrayUtil;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.intellij.psi.PsiReferenceBase;

public static class BadMacroSubstitutionReference extends PsiReferenceBase
{
    private OCMacroSymbol myMacroSymbol;
    
    public BadMacroSubstitutionReference(@NotNull final PsiElement psiElement, final OCMacroSymbol myMacroSymbol) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$BadMacroSubstitutionReference", "<init>"));
        }
        super(psiElement, new TextRange(0, psiElement.getTextLength()));
        this.myMacroSymbol = myMacroSymbol;
    }
    
    public PsiElement resolve() {
        return null;
    }
    
    @NotNull
    public Object[] getVariants() {
        Object[] empty_OBJECT_ARRAY;
        try {
            empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
            if (empty_OBJECT_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$BadMacroSubstitutionReference", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return empty_OBJECT_ARRAY;
    }
    
    public OCMacroSymbol getMacroSymbol() {
        return this.myMacroSymbol;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        return this.myElement;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
