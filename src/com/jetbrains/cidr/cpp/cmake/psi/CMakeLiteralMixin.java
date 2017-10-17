// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiNamedElement;

public interface CMakeLiteralMixin extends CMakeElement, PsiNamedElement
{
    CMakeArgument getArgument();
    
    boolean equalByText(final CMakeLiteralMixin p0);
}
