// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.psi.PsiElement;

public interface DxDocTag extends PsiElement
{
    @NotNull
    List<DxParam> getParamList();
    
    @NotNull
    String getName();
    
    @NotNull
    String getOriginalName();
    
    @NotNull
    List<String> getOptions();
}
