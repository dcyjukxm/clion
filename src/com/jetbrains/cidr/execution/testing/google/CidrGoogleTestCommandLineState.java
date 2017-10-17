// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.jetbrains.cidr.execution.testing.CidrTestScopeElement;
import com.intellij.openapi.ui.ComponentContainer;
import com.jetbrains.cidr.execution.testing.CidrRerunFailedTestsAction;
import com.intellij.execution.testframework.sm.runner.ui.SMTRunnerConsoleView;
import com.intellij.execution.Executor;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.testing.CidrTestScope;
import com.jetbrains.cidr.execution.testing.CidrLauncher;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.RunConfiguration;
import com.jetbrains.cidr.execution.testing.CidrTestCommandLineState;

public class CidrGoogleTestCommandLineState extends CidrTestCommandLineState
{
    public CidrGoogleTestCommandLineState(@NotNull final RunConfiguration runConfiguration, @NotNull final CidrLauncher cidrLauncher, @Nullable final CidrTestScope cidrTestScope, @NotNull final ExecutionEnvironment executionEnvironment, @NotNull final Executor executor) {
        if (runConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestCommandLineState", "<init>"));
        }
        if (cidrLauncher == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "launcher", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestCommandLineState", "<init>"));
        }
        if (executionEnvironment == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestCommandLineState", "<init>"));
        }
        if (executor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestCommandLineState", "<init>"));
        }
        super(runConfiguration, cidrLauncher, executionEnvironment, executor, cidrTestScope);
    }
    
    @NotNull
    @Override
    protected CidrRerunFailedTestsAction doCreateRerunFailedTestsAction(final SMTRunnerConsoleView smtRunnerConsoleView) {
        CidrGoogleTestRerunFailedTestsAction cidrGoogleTestRerunFailedTestsAction;
        try {
            cidrGoogleTestRerunFailedTestsAction = new CidrGoogleTestRerunFailedTestsAction((ComponentContainer)smtRunnerConsoleView);
            if (cidrGoogleTestRerunFailedTestsAction == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestCommandLineState", "doCreateRerunFailedTestsAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cidrGoogleTestRerunFailedTestsAction;
    }
    
    @NotNull
    @Override
    protected CidrTestScopeElement createTestScopeElement(@Nullable final String s, @Nullable final String s2) {
        CidrGoogleTestScopeElement cidrGoogleTestScopeElement;
        try {
            cidrGoogleTestScopeElement = new CidrGoogleTestScopeElement(s, s2, null, null);
            if (cidrGoogleTestScopeElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestCommandLineState", "createTestScopeElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cidrGoogleTestScopeElement;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
