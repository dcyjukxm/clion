// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.lang.Language;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;

public class OCSymbolHolderVirtualPsiElement extends OCSymbolHolderBase<OCSymbol> implements OCSymbolDeclarator, OCElement
{
    public OCSymbolHolderVirtualPsiElement(@NotNull final OCSymbol ocSymbol) {
        if (ocSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement", "<init>"));
        }
        super(ocSymbol);
    }
    
    @Override
    public OCFile getContainingOCFile() {
        return this.mySymbol.getContainingOCFile();
    }
    
    @Override
    public String getTextWithMacros() {
        return "";
    }
    
    @Override
    public TextRange getRangeWithMacros() {
        return new TextRange(this.mySymbol.getOffset(), this.mySymbol.getOffset());
    }
    
    @Override
    public boolean isEmpty() {
        return true;
    }
    
    @Override
    public long getComplexOffset() {
        return 0L;
    }
    
    @Nullable
    public static PsiElement create(final OCSymbol ocSymbol) {
        try {
            if (ocSymbol.getContainingPsiFile() != null) {
                return (PsiElement)new OCSymbolHolderVirtualPsiElement(ocSymbol);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    @Override
    public Language getLanguage() {
        OCLanguage instance;
        try {
            instance = OCLanguage.getInstance();
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement", "getLanguage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return instance;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
