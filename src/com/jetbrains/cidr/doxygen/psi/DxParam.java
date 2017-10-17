// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public interface DxParam extends PsiElement
{
    @Nullable
    DxParamId getParamId();
    
    String getName();
}
