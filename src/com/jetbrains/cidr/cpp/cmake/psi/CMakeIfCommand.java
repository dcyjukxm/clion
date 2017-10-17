// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface CMakeIfCommand extends CMakeCommand
{
    @NotNull
    List<CMakeBodyBlock> getCMakeBodyBlockList();
    
    @NotNull
    List<CMakeCommand> getCMakeCommandList();
    
    @NotNull
    CMakeIfCommandCall getCMakeIfCommandCall();
}
