// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.jetbrains.cidr.execution.ExecutableData;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.openapi.util.Key;
import com.intellij.execution.impl.ExecutionManagerImpl;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.impl.RunConfigurationBeforeRunProviderDelegate;

public class CMakeRunConfigurationBeforeRunProviderDelegate implements RunConfigurationBeforeRunProviderDelegate
{
    @Override
    public void beforeRun(@NotNull final ExecutionEnvironment executionEnvironment) {
        try {
            if (executionEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationBeforeRunProviderDelegate", "beforeRun"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final RunProfile runProfile = executionEnvironment.getRunProfile();
        Label_0073: {
            try {
                if (!(runProfile instanceof CMakeAppRunConfiguration)) {
                    return;
                }
                final CMakeAppRunConfiguration cMakeAppRunConfiguration = (CMakeAppRunConfiguration)runProfile;
                final CMakeAppRunConfiguration cMakeAppRunConfiguration2 = cMakeAppRunConfiguration;
                final ExecutableData executableData = cMakeAppRunConfiguration2.getExecutableData();
                if (executableData == null) {
                    break Label_0073;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final CMakeAppRunConfiguration cMakeAppRunConfiguration = (CMakeAppRunConfiguration)runProfile;
                final CMakeAppRunConfiguration cMakeAppRunConfiguration2 = cMakeAppRunConfiguration;
                final ExecutableData executableData = cMakeAppRunConfiguration2.getExecutableData();
                if (executableData == null) {
                    executionEnvironment.putUserData((Key)ExecutionManagerImpl.EXECUTION_SKIP_RUN, (Object)true);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
