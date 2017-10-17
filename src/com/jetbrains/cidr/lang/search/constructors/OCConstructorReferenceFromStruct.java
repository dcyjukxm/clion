// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.constructors;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCStruct;

public class OCConstructorReferenceFromStruct extends OCBaseConstructorReference
{
    public OCConstructorReferenceFromStruct(@NotNull final OCStruct ocStruct, @NotNull final OCSymbolDeclarator ocSymbolDeclarator, @NotNull final OCFunctionSymbol ocFunctionSymbol) {
        if (ocStruct == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/constructors/OCConstructorReferenceFromStruct", "<init>"));
        }
        if (ocSymbolDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "field", "com/jetbrains/cidr/lang/search/constructors/OCConstructorReferenceFromStruct", "<init>"));
        }
        if (ocFunctionSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetConstructor", "com/jetbrains/cidr/lang/search/constructors/OCConstructorReferenceFromStruct", "<init>"));
        }
        super((PsiElement)ocStruct, ocSymbolDeclarator, ocFunctionSymbol);
    }
    
    public TextRange getRangeInElement() {
        PsiElement psiElement = ((OCStruct)this.myElement).getNameIdentifier();
        if (psiElement == null) {
            psiElement = this.myElement.getFirstChild();
        }
        try {
            if (psiElement != null) {
                return TextRange.from(psiElement.getStartOffsetInParent(), psiElement.getTextLength());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return TextRange.EMPTY_RANGE;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
