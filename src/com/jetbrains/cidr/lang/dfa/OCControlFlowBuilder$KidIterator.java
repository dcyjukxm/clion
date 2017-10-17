// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

protected interface KidIterator
{
    void skipLeaves();
    
    void accept(@Nullable final PsiElement p0);
    
    void skip(@Nullable final PsiElement p0);
    
    void acceptAll();
    
    void finish();
}
