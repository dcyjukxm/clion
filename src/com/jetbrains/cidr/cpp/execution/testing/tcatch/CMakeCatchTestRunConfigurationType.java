// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing.tcatch;

import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.jetbrains.cidr.execution.ExecutableData;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.BuildTargetData;
import com.jetbrains.cidr.cpp.execution.CMakeBuildConfigurationHelper;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.testing.tcatch.CidrCatchTestRunConfigurationEditor;
import com.intellij.openapi.options.SettingsEditor;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;
import com.intellij.execution.configurations.ConfigurationFactory;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import icons.CidrLangIcons;
import com.jetbrains.cidr.execution.testing.tcatch.CidrCatchTestRunConfigurationData;
import com.jetbrains.cidr.cpp.execution.CMakeRunConfigurationType;

public class CMakeCatchTestRunConfigurationType extends CMakeRunConfigurationType
{
    protected CMakeCatchTestRunConfigurationType() {
        super("CMakeCatchTestRunConfigurationType", CidrCatchTestRunConfigurationData.FRAMEWORK_NAME, CidrCatchTestRunConfigurationData.FRAMEWORK_NAME, CidrLangIcons.CatchTest);
    }
    
    @NotNull
    @Override
    protected CMakeAppRunConfiguration createRunConfiguration(@NotNull final Project project, @NotNull final ConfigurationFactory configurationFactory) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (configurationFactory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        CMakeCatchTestRunConfiguration cMakeCatchTestRunConfiguration;
        try {
            cMakeCatchTestRunConfiguration = new CMakeCatchTestRunConfiguration(project, configurationFactory, "");
            if (cMakeCatchTestRunConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationType", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return cMakeCatchTestRunConfiguration;
    }
    
    @Override
    public SettingsEditor<? extends CMakeAppRunConfiguration> createEditor(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationType", "createEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (SettingsEditor<? extends CMakeAppRunConfiguration>)new CidrCatchTestRunConfigurationEditor<CMakeCatchTestRunConfiguration, CMakeConfiguration, CMakeTarget, CMakeBuildConfigurationHelper>(project, CMakeRunConfigurationType.getHelper(project)) {
            protected void syncBuildAndExecute(@NotNull final CMakeCatchTestRunConfiguration cMakeCatchTestRunConfiguration, @Nullable final BuildTargetData buildTargetData) {
                try {
                    if (cMakeCatchTestRunConfiguration == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationType$1", "syncBuildAndExecute"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
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
                        throw b(ex2);
                    }
                    executableData = new ExecutableData(buildTargetData);
                }
                cMakeCatchTestRunConfiguration.setExecutableData(executableData);
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    @NotNull
    public static CMakeCatchTestRunConfigurationType getInstance() {
        CMakeCatchTestRunConfigurationType cMakeCatchTestRunConfigurationType;
        try {
            cMakeCatchTestRunConfigurationType = (CMakeCatchTestRunConfigurationType)ConfigurationTypeUtil.findConfigurationType((Class)CMakeCatchTestRunConfigurationType.class);
            if (cMakeCatchTestRunConfigurationType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationType", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cMakeCatchTestRunConfigurationType;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
