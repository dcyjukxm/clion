// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface CMakeBodyBlock extends CMakeElement
{
    @NotNull
    List<CMakeCommand> getCMakeCommandList();
    
    @NotNull
    List<CMakeRoutine> getCMakeRoutineList();
}
