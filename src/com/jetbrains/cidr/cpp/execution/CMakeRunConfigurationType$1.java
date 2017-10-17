// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configuration.ConfigurationFactoryEx;

class CMakeRunConfigurationType$1 extends ConfigurationFactoryEx<CMakeAppRunConfiguration> {
    @NotNull
    public RunConfiguration createTemplateConfiguration(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType$1", "createTemplateConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        CMakeAppRunConfiguration runConfiguration;
        try {
            runConfiguration = CMakeRunConfigurationType.this.createRunConfiguration(project, this);
            if (runConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType$1", "createTemplateConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (RunConfiguration)runConfiguration;
    }
    
    @Override
    public void onNewConfigurationCreated(@NotNull final CMakeAppRunConfiguration cMakeAppRunConfiguration) {
        try {
            if (cMakeAppRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType$1", "onNewConfigurationCreated"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        super.onNewConfigurationCreated(cMakeAppRunConfiguration);
        cMakeAppRunConfiguration.setupDefaultTargetAndExecutable();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}