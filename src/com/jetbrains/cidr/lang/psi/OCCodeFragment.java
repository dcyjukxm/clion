// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiCodeFragment;

public interface OCCodeFragment extends OCFile, PsiCodeFragment
{
    Condition<OCSymbol> getCompletionFilter();
    
    void setCompletionFilter(final Condition<OCSymbol> p0);
    
    void setContext(final PsiElement p0);
}
