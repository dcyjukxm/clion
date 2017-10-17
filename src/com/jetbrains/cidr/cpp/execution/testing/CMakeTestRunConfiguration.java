// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.jetbrains.cidr.execution.testing.CidrRerunFailedTestsAction;
import com.jetbrains.cidr.execution.testing.CidrTestScope;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.Executor;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.execution.configurations.ConfigurationFactory;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationData;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfiguration;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;

public abstract class CMakeTestRunConfiguration extends CMakeAppRunConfiguration implements CidrTestRunConfiguration
{
    private CidrTestRunConfigurationData myTestData;
    
    public CMakeTestRunConfiguration(@NotNull final Project project, @NotNull final ConfigurationFactory configurationFactory, @NotNull final String s, @NotNull final CidrTestRunConfigurationData myTestData) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfiguration", "<init>"));
        }
        if (configurationFactory == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfiguration", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfiguration", "<init>"));
        }
        if (myTestData == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testData", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfiguration", "<init>"));
        }
        super(project, configurationFactory, s);
        this.myTestData = myTestData;
    }
    
    @NotNull
    @Override
    public CidrTestRunConfigurationData getTestData() {
        CidrTestRunConfigurationData myTestData;
        try {
            myTestData = this.myTestData;
            if (myTestData == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfiguration", "getTestData"));
            }
        }
        catch (InvalidDataException ex) {
            throw b(ex);
        }
        return myTestData;
    }
    
    @Override
    public CidrCommandLineState getState(@NotNull final Executor executor, @NotNull final ExecutionEnvironment executionEnvironment) {
        try {
            if (executor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfiguration", "getState"));
            }
        }
        catch (InvalidDataException ex) {
            throw b(ex);
        }
        try {
            if (executionEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfiguration", "getState"));
            }
        }
        catch (InvalidDataException ex2) {
            throw b(ex2);
        }
        return this.createState(executionEnvironment, executor, null);
    }
    
    @NotNull
    @Override
    public CidrRerunFailedTestsAction.CidrReturnTestProfile createTestRunProfile(final CidrRerunFailedTestsAction cidrRerunFailedTestsAction, final CidrTestScope cidrTestScope) {
        CidrRerunFailedTestsAction.CidrReturnTestProfile cidrReturnTestProfile;
        try {
            cidrReturnTestProfile = new CidrRerunFailedTestsAction.CidrReturnTestProfile(cidrRerunFailedTestsAction, (RunConfigurationBase)this, cidrTestScope);
            if (cidrReturnTestProfile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfiguration", "createTestRunProfile"));
            }
        }
        catch (InvalidDataException ex) {
            throw b(ex);
        }
        return cidrReturnTestProfile;
    }
    
    @Override
    public String suggestedName() {
        return this.myTestData.suggestedName(this.suggestNameForTarget());
    }
    
    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        super.checkConfiguration();
        this.myTestData.checkData();
    }
    
    @Override
    public void readExternal(final Element element) throws InvalidDataException {
        super.readExternal(element);
        this.myTestData.readExternal(element);
    }
    
    @Override
    public void writeExternal(final Element element) throws WriteExternalException {
        super.writeExternal(element);
        this.myTestData.writeExternal(element);
    }
    
    @Override
    public RunConfiguration clone() {
        final CMakeTestRunConfiguration cMakeTestRunConfiguration = (CMakeTestRunConfiguration)super.clone();
        cMakeTestRunConfiguration.myTestData = this.myTestData.clone();
        return (RunConfiguration)cMakeTestRunConfiguration;
    }
    
    private static InvalidDataException b(final InvalidDataException ex) {
        return ex;
    }
}
