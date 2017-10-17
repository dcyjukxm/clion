// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.constructors;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;

public class OCConstructorReferenceFromInitializer extends OCBaseConstructorReference
{
    public OCConstructorReferenceFromInitializer(final OCConstructorFieldInitializer ocConstructorFieldInitializer, final OCSymbolDeclarator ocSymbolDeclarator, final OCFunctionSymbol ocFunctionSymbol) {
        super((PsiElement)ocConstructorFieldInitializer, ocSymbolDeclarator, ocFunctionSymbol);
    }
    
    public TextRange getRangeInElement() {
        final PsiElement nameIdentifier = ((OCConstructorFieldInitializer)this.myElement).getReferenceElement().getNameIdentifier();
        return (nameIdentifier != null) ? TextRange.from(nameIdentifier.getStartOffsetInParent(), nameIdentifier.getTextLength()) : TextRange.EMPTY_RANGE;
    }
}
