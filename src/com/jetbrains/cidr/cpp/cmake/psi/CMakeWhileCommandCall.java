// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;

public interface CMakeWhileCommandCall extends CMakeCommandMixin
{
    @NotNull
    CMakeWhileCommandCallName getCMakeWhileCommandCallName();
}
