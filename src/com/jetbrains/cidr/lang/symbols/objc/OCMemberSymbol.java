// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;

public interface OCMemberSymbol extends OCSymbolWithParent<PsiElement, OCClassSymbol>
{
    @Nullable
    OCMemberSymbol getAssociatedSymbol();
    
    boolean isStatic();
}
