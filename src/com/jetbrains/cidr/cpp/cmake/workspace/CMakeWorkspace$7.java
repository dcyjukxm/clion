// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.execution.process.ProcessHandler;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.model.CMakeMessage;
import com.jetbrains.cidr.cpp.cmake.model.CMakeListener;

class CMakeWorkspace$7 implements CMakeListener {
    final /* synthetic */ int val$configIndex;
    
    @Override
    public void message(@NotNull final CMakeMessage cMakeMessage) {
        try {
            if (cMakeMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$7", "message"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CMakeWorkspace.access$900(CMakeWorkspace.this).reportMessage(this.val$configIndex, cMakeMessage);
    }
    
    @Override
    public void attachTo(@NotNull final ProcessHandler processHandler) {
        try {
            if (processHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$7", "attachTo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CMakeWorkspace.access$900(CMakeWorkspace.this).attachConsoleToProcess(this.val$configIndex, processHandler);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}