// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeResolveConfiguration;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.cpp.execution.CMakeBuildConfigurationHelper;
import com.jetbrains.cidr.execution.BuildTargetData;
import com.jetbrains.cidr.cpp.execution.CMakeRunConfigurationType;
import com.jetbrains.cidr.execution.ExecutableData;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;
import com.intellij.execution.RunManager;
import com.jetbrains.cidr.execution.BuildTargetAndConfigurationData;
import java.util.List;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public final class CMakeTestRunConfigurationProducerDelegate
{
    public static BuildTargetAndConfigurationData extractTargetAndConfigurationFromCurrentSelection(@NotNull final Project project, @NotNull final CidrBuildConfigurationHelper<CMakeConfiguration, CMakeTarget> cidrBuildConfigurationHelper, @NotNull final List<CMakeTarget> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "extractTargetAndConfigurationFromCurrentSelection"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cidrBuildConfigurationHelper == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "helper", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "extractTargetAndConfigurationFromCurrentSelection"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetsWithContext", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "extractTargetAndConfigurationFromCurrentSelection"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        CMakeTarget target = null;
        CMakeConfiguration buildConfiguration = null;
        final RunnerAndConfigurationSettings selectedConfiguration = RunManager.getInstance(project).getSelectedConfiguration();
        if (selectedConfiguration != null) {
            final RunConfiguration configuration = selectedConfiguration.getConfiguration();
            if (configuration instanceof CMakeAppRunConfiguration) {
                final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations = ((CMakeAppRunConfiguration)configuration).getBuildAndRunConfigurations();
                if (buildAndRunConfigurations != null) {
                    buildConfiguration = buildAndRunConfigurations.buildConfiguration;
                    target = buildConfiguration.getTarget();
                }
            }
        }
        return cidrBuildConfigurationHelper.findSimilarValidInTargets(buildConfiguration, target, list);
    }
    
    public static void setExecutableData(@NotNull final CMakeTestRunConfiguration cMakeTestRunConfiguration, @NotNull final ExecutableData executableData) {
        try {
            if (cMakeTestRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newConfigurationWithTemplateDefaults", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "setExecutableData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (executableData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildTargetData", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "setExecutableData"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        cMakeTestRunConfiguration.setExecutableData(executableData);
    }
    
    public static CMakeConfiguration getDefaultConfiguration(@NotNull final CMakeTestRunConfiguration cMakeTestRunConfiguration, @NotNull final CMakeTarget cMakeTarget) {
        try {
            if (cMakeTestRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newConfigurationWithTemplateDefaults", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "getDefaultConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "getDefaultConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return CMakeRunConfigurationType.getHelper(cMakeTestRunConfiguration.getProject()).getDefaultConfiguration(cMakeTarget);
    }
    
    public static CMakeTarget findTarget(@NotNull final List<CMakeTarget> list, @NotNull final BuildTargetData buildTargetData) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetsWithContext", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "findTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (buildTargetData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "findTarget"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return CMakeBuildConfigurationHelper.findTarget(list, buildTargetData);
    }
    
    @Nullable
    public static CMakeTarget getTargetFromResolveConfiguration(@NotNull final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/execution/testing/CMakeTestRunConfigurationProducerDelegate", "getTargetFromResolveConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveConfiguration instanceof CMakeResolveConfiguration) {
                return ((CMakeResolveConfiguration)ocResolveConfiguration).getTarget();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
