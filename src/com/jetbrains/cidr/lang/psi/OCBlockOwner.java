// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public interface OCBlockOwner extends PsiElement
{
    @Nullable
    PsiElement getOpeningBrace();
    
    @Nullable
    PsiElement getClosingBrace();
}
