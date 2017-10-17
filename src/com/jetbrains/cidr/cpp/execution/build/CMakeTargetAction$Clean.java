// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.build;

import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.CidrBundle;

public static class Clean extends CMakeTargetAction
{
    public Clean() {
        super(CidrBundle.message("build.clean", new Object[0]));
    }
    
    @Override
    protected void doBuild(@NotNull final Project project, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction$Clean", "doBuild"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (buildAndRunConfigurations == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction$Clean", "doBuild"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        CMakeBuild.clean(project, buildAndRunConfigurations);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
