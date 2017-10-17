// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing.tcatch;

import com.intellij.execution.filters.TextConsoleBuilder;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.execution.testing.tcatch.CidrCatchTestCommandLineState;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.CommandLineState;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;
import com.jetbrains.cidr.cpp.execution.CMakeLauncher;

class CMakeCatchTestRunConfiguration$1 extends CMakeLauncher {
    @NotNull
    @Override
    protected GeneralCommandLine createCommandLine(@NotNull final CommandLineState commandLineState, @NotNull final BuildAndRunConfigurations buildAndRunConfigurations, @NotNull final CMakeEnvironment cMakeEnvironment, final boolean b) throws ExecutionException {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfiguration$1", "createCommandLine"));
            }
        }
        catch (ExecutionException ex) {
            throw d((Exception)ex);
        }
        try {
            if (buildAndRunConfigurations == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildAndRunConfigurations", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfiguration$1", "createCommandLine"));
            }
        }
        catch (ExecutionException ex2) {
            throw d((Exception)ex2);
        }
        try {
            if (cMakeEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfiguration$1", "createCommandLine"));
            }
        }
        catch (ExecutionException ex3) {
            throw d((Exception)ex3);
        }
        GeneralCommandLine prepareCommandLine;
        try {
            prepareCommandLine = CidrCatchTestCommandLineState.prepareCommandLine(commandLineState, super.createCommandLine(commandLineState, buildAndRunConfigurations, cMakeEnvironment, b));
            if (prepareCommandLine == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfiguration$1", "createCommandLine"));
            }
        }
        catch (ExecutionException ex4) {
            throw d((Exception)ex4);
        }
        return prepareCommandLine;
    }
    
    @Override
    public boolean usePty() {
        return false;
    }
    
    @NotNull
    @Override
    protected TextConsoleBuilder createConsoleBuilder(@NotNull final CommandLineState commandLineState, @NotNull final CidrToolEnvironment cidrToolEnvironment, @Nullable final File file) {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfiguration$1", "createConsoleBuilder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfiguration$1", "createConsoleBuilder"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        TextConsoleBuilder consoleBuilder;
        try {
            consoleBuilder = CidrCatchTestCommandLineState.createConsoleBuilder(this.getProject(), commandLineState, cidrToolEnvironment, file);
            if (consoleBuilder == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfiguration$1", "createConsoleBuilder"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        return consoleBuilder;
    }
    
    private static Exception d(final Exception ex) {
        return ex;
    }
}