// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.psi.PsiElement;

public interface DxDocComment extends PsiElement
{
    @NotNull
    List<DxDocTag> getDocTagList();
    
    @NotNull
    List<String> getOptions();
}
