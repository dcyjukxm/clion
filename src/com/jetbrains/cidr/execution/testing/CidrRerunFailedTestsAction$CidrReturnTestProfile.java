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
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.LogFileOptions;
import com.intellij.execution.configurations.PredefinedLogFile;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunConfigurationBase;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.CidrRunProfile;
import com.intellij.execution.testframework.actions.AbstractRerunFailedTestsAction;

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
