// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing.google;

import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.jetbrains.cidr.execution.ExecutableData;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.BuildTargetData;
import com.jetbrains.cidr.cpp.execution.CMakeBuildConfigurationHelper;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestRunConfigurationEditor;
import com.intellij.openapi.options.SettingsEditor;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;
import com.intellij.execution.configurations.ConfigurationFactory;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import icons.CidrLangIcons;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestRunConfigurationData;
import com.jetbrains.cidr.cpp.execution.CMakeRunConfigurationType;

public class CMakeGoogleTestRunConfigurationType extends CMakeRunConfigurationType
{
    protected CMakeGoogleTestRunConfigurationType() {
        super("CMakeGoogleTestRunConfigurationType", CidrGoogleTestRunConfigurationData.GOOGLE_TEST, CidrGoogleTestRunConfigurationData.GOOGLE_TEST, CidrLangIcons.GoogleTest);
    }
    
    @NotNull
    @Override
    protected CMakeAppRunConfiguration createRunConfiguration(@NotNull final Project project, @NotNull final ConfigurationFactory configurationFactory) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (configurationFactory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        CMakeGoogleTestRunConfiguration cMakeGoogleTestRunConfiguration;
        try {
            cMakeGoogleTestRunConfiguration = new CMakeGoogleTestRunConfiguration(project, configurationFactory, "");
            if (cMakeGoogleTestRunConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return cMakeGoogleTestRunConfiguration;
    }
    
    @Override
    public SettingsEditor<? extends CMakeAppRunConfiguration> createEditor(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfigurationType", "createEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (SettingsEditor<? extends CMakeAppRunConfiguration>)new CidrGoogleTestRunConfigurationEditor<CMakeGoogleTestRunConfiguration, CMakeConfiguration, CMakeTarget, CMakeBuildConfigurationHelper>(project, CMakeRunConfigurationType.getHelper(project)) {
            protected void syncBuildAndExecute(@NotNull final CMakeGoogleTestRunConfiguration cMakeGoogleTestRunConfiguration, @Nullable final BuildTargetData buildTargetData) {
                try {
                    if (cMakeGoogleTestRunConfiguration == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfigurationType$1", "syncBuildAndExecute"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                ExecutableData executableData = null;
                Label_0065: {
                    try {
                        if (buildTargetData == null) {
                            executableData = null;
                            break Label_0065;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                    executableData = new ExecutableData(buildTargetData);
                }
                cMakeGoogleTestRunConfiguration.setExecutableData(executableData);
            }
            
            private static IllegalArgumentException c(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    @NotNull
    public static CMakeGoogleTestRunConfigurationType getInstance() {
        CMakeGoogleTestRunConfigurationType cMakeGoogleTestRunConfigurationType;
        try {
            cMakeGoogleTestRunConfigurationType = (CMakeGoogleTestRunConfigurationType)ConfigurationTypeUtil.findConfigurationType((Class)CMakeGoogleTestRunConfigurationType.class);
            if (cMakeGoogleTestRunConfigurationType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfigurationType", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cMakeGoogleTestRunConfigurationType;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
