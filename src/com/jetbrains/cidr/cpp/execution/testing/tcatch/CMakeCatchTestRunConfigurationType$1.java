// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing.tcatch;

import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.jetbrains.cidr.execution.ExecutableData;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.BuildTargetData;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.execution.CMakeBuildConfigurationHelper;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.testing.tcatch.CidrCatchTestRunConfigurationEditor;

class CMakeCatchTestRunConfigurationType$1 extends CidrCatchTestRunConfigurationEditor<CMakeCatchTestRunConfiguration, CMakeConfiguration, CMakeTarget, CMakeBuildConfigurationHelper> {
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
}