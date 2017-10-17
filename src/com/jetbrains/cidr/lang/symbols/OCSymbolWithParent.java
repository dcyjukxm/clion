// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public interface OCSymbolWithParent<T extends PsiElement, P extends OCSymbol> extends OCSymbol<T>
{
    P getParent();
    
    @NotNull
    String getNameWithParent();
}
