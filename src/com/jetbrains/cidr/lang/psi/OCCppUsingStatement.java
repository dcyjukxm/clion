// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCCppUsingStatement extends OCElement, PsiNameIdentifierOwner, OCLocalSymbolDeclarator<OCSymbol>
{
    boolean isNamespaceUsing();
}
