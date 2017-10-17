// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing.google;

import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.jetbrains.cidr.execution.ExecutableData;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.BuildTargetData;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.execution.CMakeBuildConfigurationHelper;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestRunConfigurationEditor;

class CMakeGoogleTestRunConfigurationType$1 extends CidrGoogleTestRunConfigurationEditor<CMakeGoogleTestRunConfiguration, CMakeConfiguration, CMakeTarget, CMakeBuildConfigurationHelper> {
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
}