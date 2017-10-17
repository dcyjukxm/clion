// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.cpp.OCMacroParameterSymbol;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiNamedElement;

public interface OCMacroParameter extends OCElement, PsiNamedElement, PsiNameIdentifierOwner, OCSymbolDeclarator<OCMacroParameterSymbol>
{
}
