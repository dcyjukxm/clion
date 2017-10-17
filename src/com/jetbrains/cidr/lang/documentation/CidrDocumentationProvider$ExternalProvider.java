// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public interface ExternalProvider
{
    void addExternalDoc(@NotNull final PsiElement p0, @Nullable final PsiElement p1, @NotNull final StringBuilder p2);
}
