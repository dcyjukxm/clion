// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.execution.util.ProgramParametersConfigurator;

class CMakeLauncher$2 extends ProgramParametersConfigurator {
    final /* synthetic */ String val$defaultWorkingDir;
    
    @NotNull
    @Override
    protected String getDefaultWorkingDir(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeLauncher$2", "getDefaultWorkingDir"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String val$defaultWorkingDir;
        try {
            val$defaultWorkingDir = this.val$defaultWorkingDir;
            if (val$defaultWorkingDir == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher$2", "getDefaultWorkingDir"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return val$defaultWorkingDir;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}