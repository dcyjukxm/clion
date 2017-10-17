// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;

public interface CMakeEndIfCommand extends CMakeCommand
{
    @NotNull
    CMakeEndIfCommandCall getCMakeEndIfCommandCall();
}
