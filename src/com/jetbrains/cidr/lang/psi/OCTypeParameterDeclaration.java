// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCTypeParameterDeclaration extends OCElement, PsiNameIdentifierOwner, OCLocalSymbolDeclarator<OCTypeParameterTypeSymbol>
{
    String getName();
}
