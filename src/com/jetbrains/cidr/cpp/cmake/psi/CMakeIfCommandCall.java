// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;

public interface CMakeIfCommandCall extends CMakeCommandMixin
{
    @NotNull
    CMakeIfCommandCallName getCMakeIfCommandCallName();
}
