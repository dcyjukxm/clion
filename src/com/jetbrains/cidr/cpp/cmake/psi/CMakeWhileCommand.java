// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CMakeWhileCommand extends CMakeLoopCommand
{
    @Nullable
    CMakeBodyBlock getCMakeBodyBlock();
    
    @Nullable
    CMakeEndWhileCommand getCMakeEndWhileCommand();
    
    @NotNull
    CMakeWhileCommandCall getCMakeWhileCommandCall();
}
