// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.interpreter;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;

public interface Listener
{
    void beforeCommand(@NotNull final CMakeCommand p0, @NotNull final CMakeScope p1);
    
    void onEnterIf();
    
    void onExitIf();
    
    boolean onEnterBranch(final int p0);
    
    void onExitBranch();
    
    void onEnterWhile();
    
    void onExitWhile();
    
    void onEnterForeach();
    
    void onExitForeach();
    
    boolean onEnterLoop(final int p0);
    
    void onExitLoop();
    
    void onEnterFunction();
    
    void onExitFunction();
    
    void onEnterMacro();
    
    void onExitMacro();
}
