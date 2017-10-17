// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface DxParamId extends DxNamedElement
{
    @NotNull
    String getName();
    
    @NotNull
    PsiElement setName(final String p0);
    
    @Nullable
    PsiElement getNameIdentifier();
}
