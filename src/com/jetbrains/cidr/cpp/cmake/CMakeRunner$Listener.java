// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.process.ProcessHandler;

public interface Listener
{
    default void processStarted(@NotNull final ProcessHandler processHandler) {
        try {
            if (processHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/CMakeRunner$Listener", "processStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
