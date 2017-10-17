// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.symbols.cpp.OCLabelSymbol;

public interface OCLabeledStatement extends OCStatement, OCLocalSymbolDeclarator<OCLabelSymbol>, PsiNameIdentifierOwner
{
    String getLabel();
    
    @Nullable
    PsiElement getLabelElement();
    
    OCStatement getStatement();
}
