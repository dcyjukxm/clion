// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.ConfigurationFactory;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationTypeBase;

public abstract class CMakeRunConfigurationType extends ConfigurationTypeBase
{
    protected CMakeRunConfigurationType(@NotNull final String s, final String s2, final String s3, final Icon icon) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType", "<init>"));
        }
        super(s, s2, s3, icon);
        this.addFactory(this.getFactory());
    }
    
    @NotNull
    public ConfigurationFactory getFactory() {
        ConfigurationFactoryEx<CMakeAppRunConfiguration> configurationFactoryEx;
        try {
            configurationFactoryEx = new ConfigurationFactoryEx<CMakeAppRunConfiguration>(this) {
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
            };
            if (configurationFactoryEx == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType", "getFactory"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return configurationFactoryEx;
    }
    
    @NotNull
    protected abstract CMakeAppRunConfiguration createRunConfiguration(@NotNull final Project p0, @NotNull final ConfigurationFactory p1);
    
    public abstract SettingsEditor<? extends CMakeAppRunConfiguration> createEditor(@NotNull final Project p0);
    
    @NotNull
    public static CMakeBuildConfigurationHelper getHelper(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType", "getHelper"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CMakeBuildConfigurationHelper cMakeBuildConfigurationHelper;
        try {
            cMakeBuildConfigurationHelper = new CMakeBuildConfigurationHelper(project);
            if (cMakeBuildConfigurationHelper == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType", "getHelper"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return cMakeBuildConfigurationHelper;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
