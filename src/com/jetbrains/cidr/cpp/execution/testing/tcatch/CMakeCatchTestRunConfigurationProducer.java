// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing.tcatch;

import com.jetbrains.cidr.execution.CidrBuildConfiguration;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.execution.BuildTargetData;
import com.jetbrains.cidr.cpp.execution.testing.CMakeTestRunConfiguration;
import com.jetbrains.cidr.execution.ExecutableData;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.execution.testing.CMakeTestRunConfigurationProducerDelegate;
import com.jetbrains.cidr.execution.BuildTargetAndConfigurationData;
import java.util.List;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.execution.configurations.ConfigurationType;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.testing.tcatch.CidrCatchTestRunConfigurationProducer;

public class CMakeCatchTestRunConfigurationProducer extends CidrCatchTestRunConfigurationProducer<CMakeConfiguration, CMakeTarget, CMakeCatchTestRunConfiguration>
{
    public CMakeCatchTestRunConfigurationProducer() {
        super((ConfigurationType)CMakeCatchTestRunConfigurationType.getInstance());
    }
    
    @Nullable
    @Override
    protected BuildTargetAndConfigurationData extractTargetAndConfigurationFromCurrentSelection(@NotNull final Project project, @NotNull final CidrBuildConfigurationHelper<CMakeConfiguration, CMakeTarget> cidrBuildConfigurationHelper, @NotNull final List<CMakeTarget> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "extractTargetAndConfigurationFromCurrentSelection"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (cidrBuildConfigurationHelper == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "helper", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "extractTargetAndConfigurationFromCurrentSelection"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetsWithContext", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "extractTargetAndConfigurationFromCurrentSelection"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return CMakeTestRunConfigurationProducerDelegate.extractTargetAndConfigurationFromCurrentSelection(project, cidrBuildConfigurationHelper, list);
    }
    
    protected void setExecutableData(@NotNull final CMakeCatchTestRunConfiguration cMakeCatchTestRunConfiguration, @NotNull final ExecutableData executableData) {
        try {
            if (cMakeCatchTestRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newConfigurationWithTemplateDefaults", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "setExecutableData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (executableData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildTargetData", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "setExecutableData"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        CMakeTestRunConfigurationProducerDelegate.setExecutableData(cMakeCatchTestRunConfiguration, executableData);
    }
    
    @Nullable
    protected CMakeConfiguration getDefaultConfiguration(@NotNull final CMakeCatchTestRunConfiguration cMakeCatchTestRunConfiguration, @NotNull final CMakeTarget cMakeTarget) {
        try {
            if (cMakeCatchTestRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newConfigurationWithTemplateDefaults", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "getDefaultConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (cMakeTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "getDefaultConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return CMakeTestRunConfigurationProducerDelegate.getDefaultConfiguration(cMakeCatchTestRunConfiguration, cMakeTarget);
    }
    
    @Nullable
    @Override
    protected CMakeTarget findTarget(@NotNull final List<CMakeTarget> list, @NotNull final BuildTargetData buildTargetData) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetsWithContext", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "findTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (buildTargetData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "findTarget"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return CMakeTestRunConfigurationProducerDelegate.findTarget(list, buildTargetData);
    }
    
    @Nullable
    @Override
    protected CMakeTarget getTargetFromResolveConfiguration(@NotNull final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/execution/testing/tcatch/CMakeCatchTestRunConfigurationProducer", "getTargetFromResolveConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return CMakeTestRunConfigurationProducerDelegate.getTargetFromResolveConfiguration(ocResolveConfiguration);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
