// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;
import com.intellij.execution.ui.ConsoleView;
import java.io.File;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.openapi.project.Project;
import com.intellij.execution.configurations.CommandLineState;
import com.jetbrains.cidr.execution.CidrConsoleBuilder;

static final class CidrCatchTestCommandLineState$2 extends CidrConsoleBuilder {
    final /* synthetic */ CommandLineState val$state;
    
    @NotNull
    @Override
    protected ConsoleView createConsole() {
        final CidrCatchTestCommandLineState cidrCatchTestCommandLineState = (CidrCatchTestCommandLineState)this.val$state;
        ConsoleView console;
        try {
            console = this.createConsole(cidrCatchTestCommandLineState.getConfiguration().getType(), new CidrCatchTestConsoleProperties(cidrCatchTestCommandLineState.getConfiguration(), cidrCatchTestCommandLineState.getExecutor(), cidrCatchTestCommandLineState.getExecutionTarget()));
            if (console == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState$2", "createConsole"));
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