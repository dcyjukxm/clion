// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCReference<T extends OCSymbol> extends PsiReference, OCResolvesToSymbol<T>
{
    PsiElement bindToSymbol(@NotNull final OCSymbol p0);
}
