// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CMakeForeachCommand extends CMakeLoopCommand
{
    @Nullable
    CMakeBodyBlock getCMakeBodyBlock();
    
    @Nullable
    CMakeEndForeachCommand getCMakeEndForeachCommand();
    
    @NotNull
    CMakeForeachCommandCall getCMakeForeachCommandCall();
}
