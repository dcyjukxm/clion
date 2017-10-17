// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.execution.testframework.TestFrameworkRunningModel;
import com.intellij.openapi.util.Getter;
import com.intellij.execution.testframework.sm.runner.ui.SMTRunnerConsoleView;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.configurations.RunConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.Executor;
import com.jetbrains.cidr.execution.CidrCommandLineState;

public abstract class CidrTestCommandLineState extends CidrCommandLineState
{
    @NotNull
    private final Executor myExecutor;
    @NotNull
    protected final RunConfiguration myConfiguration;
    @Nullable
    protected final CidrTestScope myFailedTests;
    
    public CidrTestCommandLineState(@NotNull final RunConfiguration myConfiguration, @NotNull final CidrLauncher cidrLauncher, @NotNull final ExecutionEnvironment executionEnvironment, @NotNull final Executor myExecutor, @Nullable final CidrTestScope myFailedTests) {
        if (myConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "<init>"));
        }
        if (cidrLauncher == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "launcher", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "<init>"));
        }
        if (executionEnvironment == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "<init>"));
        }
        if (myExecutor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "<init>"));
        }
        super(executionEnvironment, cidrLauncher);
        this.myConfiguration = myConfiguration;
        this.myExecutor = myExecutor;
        this.myFailedTests = myFailedTests;
    }
    
    @NotNull
    public Executor getExecutor() {
        Executor myExecutor;
        try {
            myExecutor = this.myExecutor;
            if (myExecutor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "getExecutor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myExecutor;
    }
    
    @NotNull
    public RunConfiguration getConfiguration() {
        RunConfiguration myConfiguration;
        try {
            myConfiguration = this.myConfiguration;
            if (myConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "getConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myConfiguration;
    }
    
    @NotNull
    public CidrRerunFailedTestsAction createRerunFailedTestsAction(@NotNull final ExecutionConsole executionConsole) {
        try {
            if (executionConsole == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "console", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "createRerunFailedTestsAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final SMTRunnerConsoleView smtRunnerConsoleView = (SMTRunnerConsoleView)executionConsole;
        final CidrRerunFailedTestsAction doCreateRerunFailedTestsAction = this.doCreateRerunFailedTestsAction(smtRunnerConsoleView);
        CidrRerunFailedTestsAction cidrRerunFailedTestsAction;
        try {
            doCreateRerunFailedTestsAction.init(smtRunnerConsoleView.getProperties());
            doCreateRerunFailedTestsAction.setModelProvider((Getter<TestFrameworkRunningModel>)(() -> smtRunnerConsoleView.getResultsViewer()));
            cidrRerunFailedTestsAction = doCreateRerunFailedTestsAction;
            if (cidrRerunFailedTestsAction == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "createRerunFailedTestsAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return cidrRerunFailedTestsAction;
    }
    
    @NotNull
    public CidrTestScope testScope() {
        Label_0057: {
            CidrTestScope cidrTestScope = null;
            Label_0022: {
                try {
                    if (this.myFailedTests == null) {
                        break Label_0057;
                    }
                    final CidrTestCommandLineState cidrTestCommandLineState = this;
                    cidrTestScope = cidrTestCommandLineState.myFailedTests;
                    if (cidrTestScope == null) {
                        break Label_0022;
                    }
                    return cidrTestScope;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CidrTestCommandLineState cidrTestCommandLineState = this;
                    cidrTestScope = cidrTestCommandLineState.myFailedTests;
                    if (cidrTestScope == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "testScope"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return cidrTestScope;
        }
        final CidrTestRunConfigurationData testData = ((CidrTestRunConfiguration)this.myConfiguration).getTestData();
        final String notNullize = StringUtil.notNullize(testData.getTestPattern());
        String string = null;
        Label_0125: {
            Label_0102: {
                try {
                    if (testData.getTestMode() != CidrScopeInfo.Mode.PATTERN) {
                        break Label_0102;
                    }
                    final String s = notNullize;
                    final boolean b = s.isEmpty();
                    if (!b) {
                        break Label_0102;
                    }
                    break Label_0102;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final String s = notNullize;
                    final boolean b = s.isEmpty();
                    if (!b) {
                        string = notNullize;
                        break Label_0125;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            string = this.createTestScopeElement(testData.getTestSuite(), testData.getTestName()).toString();
        }
        final String s2 = string;
        CidrTestScope cidrTestScope2;
        try {
            cidrTestScope2 = new CidrTestScope(s2);
            if (cidrTestScope2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestCommandLineState", "testScope"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return cidrTestScope2;
    }
    
    @NotNull
    protected abstract CidrRerunFailedTestsAction doCreateRerunFailedTestsAction(final SMTRunnerConsoleView p0);
    
    @NotNull
    protected abstract CidrTestScopeElement createTestScopeElement(@Nullable final String p0, @Nullable final String p1);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
