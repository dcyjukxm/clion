// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.psi.PsiPolyVariantReference;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCPolyVariantReference<T extends OCSymbol> extends PsiPolyVariantReference
{
    @NotNull
    List<T> resolveToSymbols();
    
    PsiElement bindToSymbol(@NotNull final OCSymbol p0);
}
