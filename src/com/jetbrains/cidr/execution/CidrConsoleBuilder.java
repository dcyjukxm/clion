// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.execution.testframework.TestConsoleProperties;
import com.intellij.execution.testframework.sm.SMTestRunnerConnectionUtil;
import com.intellij.execution.testframework.sm.runner.ui.SMTRunnerConsoleView;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.filters.Filter;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.execution.filters.TextConsoleBuilderImpl;

public class CidrConsoleBuilder extends TextConsoleBuilderImpl
{
    public CidrConsoleBuilder(@NotNull final Project project, @Nullable final CidrToolEnvironment cidrToolEnvironment, @Nullable final File file) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/CidrConsoleBuilder", "<init>"));
        }
        super(project);
        this.addFilter((Filter)new CidrPathConsoleFilter(project, cidrToolEnvironment, file));
    }
    
    protected ConsoleView createConsole(@NotNull final ConfigurationType configurationType, final SMTRunnerConsoleProperties smtRunnerConsoleProperties) {
        try {
            if (configurationType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/CidrConsoleBuilder", "createConsole"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final SMTRunnerConsoleView smtRunnerConsoleView = (SMTRunnerConsoleView)SMTestRunnerConnectionUtil.createConsole(configurationType.getId(), smtRunnerConsoleProperties);
        Disposer.register((Disposable)this.getProject(), (Disposable)smtRunnerConsoleView);
        smtRunnerConsoleView.getResultsViewer().getTreeView().getSelectionModel().setSelectionMode(4);
        return (ConsoleView)smtRunnerConsoleView;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
