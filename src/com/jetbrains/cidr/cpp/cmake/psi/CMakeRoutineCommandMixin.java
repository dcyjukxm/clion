// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiNamedElement;

public interface CMakeRoutineCommandMixin extends CMakeElement, PsiNamedElement
{
    @Nullable
    CMakeArgument getFirstArgument();
    
    @NotNull
    CMakeArgument getFirstArgumentNotNull();
}
