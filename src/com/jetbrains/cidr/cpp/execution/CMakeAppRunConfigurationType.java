// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.execution.configurations.ConfigurationFactory;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.CidrBundle;

public class CMakeAppRunConfigurationType extends CMakeRunConfigurationType
{
    protected CMakeAppRunConfigurationType() {
        super("CMakeRunConfiguration", CidrBundle.message("run.configuration.name", new Object[0]), CidrBundle.message("run.configuration.description", new Object[0]), AllIcons.RunConfigurations.Application);
    }
    
    @NotNull
    @Override
    protected CMakeAppRunConfiguration createRunConfiguration(@NotNull final Project project, @NotNull final ConfigurationFactory configurationFactory) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (configurationFactory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        CMakeAppRunConfiguration cMakeAppRunConfiguration;
        try {
            cMakeAppRunConfiguration = new CMakeAppRunConfiguration(project, configurationFactory, "");
            if (cMakeAppRunConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return cMakeAppRunConfiguration;
    }
    
    @Override
    public SettingsEditor<? extends CMakeAppRunConfiguration> createEditor(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationType", "createEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (SettingsEditor<? extends CMakeAppRunConfiguration>)new CMakeAppRunConfigurationSettingsEditor(project, CMakeRunConfigurationType.getHelper(project));
    }
    
    @NotNull
    public static CMakeAppRunConfigurationType getInstance() {
        CMakeAppRunConfigurationType cMakeAppRunConfigurationType;
        try {
            cMakeAppRunConfigurationType = (CMakeAppRunConfigurationType)ConfigurationTypeUtil.findConfigurationType((Class)CMakeAppRunConfigurationType.class);
            if (cMakeAppRunConfigurationType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationType", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cMakeAppRunConfigurationType;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
