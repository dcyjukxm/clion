// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing.google;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestConsoleProperties;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestCommandLineState;
import com.intellij.execution.ui.ConsoleView;
import java.io.File;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.openapi.project.Project;
import com.intellij.execution.configurations.CommandLineState;
import com.jetbrains.cidr.execution.CidrConsoleBuilder;

class CMakeGoogleTestRunConfiguration$1$1 extends CidrConsoleBuilder {
    final /* synthetic */ CommandLineState val$state;
    
    @NotNull
    @Override
    protected ConsoleView createConsole() {
        final CidrGoogleTestCommandLineState cidrGoogleTestCommandLineState = (CidrGoogleTestCommandLineState)this.val$state;
        ConsoleView console;
        try {
            console = this.createConsole(cidrGoogleTestCommandLineState.getConfiguration().getType(), new CidrGoogleTestConsoleProperties(cidrGoogleTestCommandLineState.getConfiguration(), cidrGoogleTestCommandLineState.getExecutor(), cidrGoogleTestCommandLineState.getExecutionTarget()));
            if (console == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1$1", "createConsole"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return console;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}