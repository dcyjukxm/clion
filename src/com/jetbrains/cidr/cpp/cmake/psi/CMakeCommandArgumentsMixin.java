// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public interface CMakeCommandArgumentsMixin extends CMakeElement
{
    boolean canAppendArguments();
    
    void appendArgument(@NotNull final String p0);
    
    boolean isFunctionDeclarationParameters();
    
    boolean isEndFunctionParameters();
    
    boolean isMacroDeclarationParameters();
    
    boolean isEndMacroParameters();
    
    boolean isEndRoutineParameters();
    
    @Nullable
    CMakeArgument getFirstArgument();
    
    CMakeCommand getCommand();
    
    CMakeCommandName getCommandName();
}
