// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiNamedElement;

public interface OCDefineDirective extends PsiNamedElement, PsiNameIdentifierOwner, OCElement, OCSymbolDeclarator<OCMacroSymbol>
{
    OCMacroParameterList getMacroParameters();
}
