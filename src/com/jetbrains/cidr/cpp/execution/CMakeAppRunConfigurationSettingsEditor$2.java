// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.intellij.openapi.application.ReadAction;

class CMakeAppRunConfigurationSettingsEditor$2 extends ReadAction<CMakeConfiguration> {
    final /* synthetic */ String val$configurationName;
    
    protected void run(@NotNull final Result<CMakeConfiguration> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationSettingsEditor$2", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        result.setResult((Object)((CMakeBuildConfigurationHelper)CMakeAppRunConfigurationSettingsEditor.access$100(CMakeAppRunConfigurationSettingsEditor.this)).findConfiguration(CMakeAppRunConfigurationSettingsEditor.access$000(CMakeAppRunConfigurationSettingsEditor.this), this.val$configurationName));
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}