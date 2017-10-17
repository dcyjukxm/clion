// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.execution.configurations.RunProfileState;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationPerRunnerSettings;
import com.intellij.execution.configurations.ConfigurationInfoProvider;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.configurations.LogFileOptions;
import com.intellij.execution.configurations.PredefinedLogFile;
import java.util.ArrayList;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.jetbrains.cidr.execution.CidrRunProfile;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.intellij.execution.Executor;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.intellij.openapi.project.Project;
import java.util.Iterator;
import java.util.List;
import com.intellij.execution.testframework.TestFrameworkRunningModel;
import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.configurations.RunConfiguration;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.ComponentContainer;
import com.intellij.execution.testframework.actions.AbstractRerunFailedTestsAction;

public abstract class CidrRerunFailedTestsAction extends AbstractRerunFailedTestsAction
{
    public CidrRerunFailedTestsAction(@NotNull final ComponentContainer componentContainer) {
        if (componentContainer == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "componentContainer", "com/jetbrains/cidr/execution/testing/CidrRerunFailedTestsAction", "<init>"));
        }
        super(componentContainer);
    }
    
    @Nullable
    public MyRunProfile getRunProfile(@Nullable final ExecutionEnvironment executionEnvironment) {
        final TestFrameworkRunningModel model = this.getModel();
        try {
            if (model == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final RunConfiguration runConfiguration = (RunConfiguration)model.getProperties().getConfiguration();
        final List<AbstractTestProxy> failedTests = this.getFailedTests(runConfiguration.getProject());
        final CidrTestScope testScope = this.createTestScope();
        final Iterator<AbstractTestProxy> iterator = failedTests.iterator();
        while (iterator.hasNext()) {
            final CidrTestScopeElement element = this.getElement(iterator.next(), runConfiguration.getProject());
            try {
                if (element == null) {
                    continue;
                }
                testScope.add(element);
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ((CidrTestRunConfiguration)runConfiguration).createTestRunProfile(this, testScope);
    }
    
    protected abstract CidrTestScope createTestScope();
    
    @Nullable
    protected abstract CidrTestScopeElement getElement(@NotNull final AbstractTestProxy p0, final Project p1);
    
    @Nullable
    protected abstract CidrCommandLineState createState(@NotNull final CidrRunConfiguration p0, @NotNull final ExecutionEnvironment p1, @NotNull final Executor p2, @NotNull final CidrTestScope p3);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class CidrReturnTestProfile extends MyRunProfile implements CidrRunProfile
    {
        private final CidrTestScope myFailedTests;
        private final CidrRerunFailedTestsAction myTestsAction;
        
        public CidrReturnTestProfile(@NotNull final CidrRerunFailedTestsAction myTestsAction, final RunConfigurationBase runConfigurationBase, final CidrTestScope myFailedTests) {
            if (myTestsAction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/execution/testing/CidrRerunFailedTestsAction$CidrReturnTestProfile", "<init>"));
            }
            super(runConfigurationBase);
            this.myTestsAction = myTestsAction;
            this.myFailedTests = myFailedTests;
        }
        
        @Nullable
        @Override
        public CidrCommandLineState getState(@NotNull final Executor executor, @NotNull final ExecutionEnvironment executionEnvironment) throws ExecutionException {
            try {
                if (executor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/testing/CidrRerunFailedTestsAction$CidrReturnTestProfile", "getState"));
                }
            }
            catch (ExecutionException ex) {
                throw a(ex);
            }
            try {
                if (executionEnvironment == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/testing/CidrRerunFailedTestsAction$CidrReturnTestProfile", "getState"));
                }
            }
            catch (ExecutionException ex2) {
                throw a(ex2);
            }
            return this.myTestsAction.createState((CidrRunConfiguration)this.getPeer(), executionEnvironment, executor, this.myFailedTests);
        }
        
        private static ExecutionException a(final ExecutionException ex) {
            return ex;
        }
    }
}
