// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface CMakeArgument extends CMakeArgumentMixin
{
    @Nullable
    CMakeLiteral getCMakeLiteral();
    
    @Nullable
    PsiElement getBracketArgEnd();
    
    @Nullable
    PsiElement getBracketArgStart();
}
