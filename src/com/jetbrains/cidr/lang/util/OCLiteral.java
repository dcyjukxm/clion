// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;

public interface OCLiteral
{
    @NotNull
    OCType getType(@Nullable final PsiFile p0, final boolean p1);
}
