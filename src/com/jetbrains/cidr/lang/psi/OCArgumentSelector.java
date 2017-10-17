// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface OCArgumentSelector extends OCElement
{
    @NotNull
    String getSelectorName();
    
    @Nullable
    PsiElement getSelectorIdentifier();
}
