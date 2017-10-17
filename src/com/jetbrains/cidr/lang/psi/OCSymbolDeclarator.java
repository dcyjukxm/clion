// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCSymbolDeclarator<T extends OCSymbol> extends PsiElement
{
    @Nullable
    T getSymbol();
}
