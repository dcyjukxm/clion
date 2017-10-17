// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CMakeFunctionCommand extends CMakeRoutine
{
    @Nullable
    CMakeBodyBlock getCMakeBodyBlock();
    
    @Nullable
    CMakeCommandArguments getCMakeCommandArguments();
    
    @Nullable
    CMakeEndFunctionCommand getCMakeEndFunctionCommand();
    
    @NotNull
    CMakeFunctionCommandCall getCMakeFunctionCommandCall();
}
