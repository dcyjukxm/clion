// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNamedElement;

public interface CMakeCommandNameMixin extends CMakeElement, PsiNamedElement
{
    @NotNull
    String getName();
}
