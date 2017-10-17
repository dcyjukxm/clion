// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.process.ProcessHandler;
import com.jetbrains.cidr.cpp.cmake.CMakeRunner;

class CMakeGenerator$2 implements CMakeRunner.Listener {
    final /* synthetic */ Parameters val$parameters;
    
    @Override
    public void processStarted(@NotNull final ProcessHandler processHandler) {
        try {
            if (processHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$2", "processStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.val$parameters.listener.attachTo(processHandler);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}