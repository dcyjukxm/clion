// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCPropertyAttribute extends OCElement, PsiNameIdentifierOwner
{
    @Nullable
    String getValue();
    
    @Nullable
    PsiElement getValueElement();
    
    void setValue(final String p0);
    
    @Nullable
    PsiElement getColon();
    
    @Nullable
    OCProperty getParentProperty();
}
