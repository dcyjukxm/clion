// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface OCNamespaceQualifiedNameOwner extends OCNamespaceQualifierOwner
{
    @Nullable
    String getName();
    
    @Nullable
    PsiElement getNameIdentifier();
}
