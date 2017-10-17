// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCCategoryName extends OCElement, PsiNameIdentifierOwner
{
    @NotNull
    String getName();
}
