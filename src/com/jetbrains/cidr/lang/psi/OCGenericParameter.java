// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCGenericParameterSymbol;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCGenericParameter extends OCElement, PsiNameIdentifierOwner, OCLocalSymbolDeclarator<OCGenericParameterSymbol>
{
    @Nullable
    OCGenericParameterSymbol getLocalSymbol();
}
