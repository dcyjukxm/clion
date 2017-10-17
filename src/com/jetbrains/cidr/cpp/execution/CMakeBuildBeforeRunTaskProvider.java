// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.execution.build.CMakeBuild;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.WrappingRunConfiguration;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.execution.BeforeRunTask;
import com.intellij.execution.configurations.RunConfiguration;
import com.jetbrains.cidr.execution.CidrBuildBeforeRunTaskProvider;

public class CMakeBuildBeforeRunTaskProvider extends CidrBuildBeforeRunTaskProvider
{
    @Override
    public boolean canExecuteTask(final RunConfiguration runConfiguration, final BeforeRunTask beforeRunTask) {
        return runConfiguration instanceof CMakeAppRunConfiguration;
    }
    
    public boolean executeTask(final DataContext dataContext, final RunConfiguration runConfiguration, final ExecutionEnvironment executionEnvironment, final BeforeRunTask beforeRunTask) {
        RunConfiguration peer = null;
        Label_0024: {
            try {
                if (runConfiguration instanceof WrappingRunConfiguration) {
                    peer = ((WrappingRunConfiguration)runConfiguration).getPeer();
                    break Label_0024;
                }
            }
            catch (ExecutionException ex) {
                throw b(ex);
            }
            peer = runConfiguration;
        }
        final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations = ((CMakeAppRunConfiguration)peer).getBuildAndRunConfigurations();
        try {
            if (buildAndRunConfigurations == null) {
                return false;
            }
        }
        catch (ExecutionException ex2) {
            throw b(ex2);
        }
        final Project project = runConfiguration.getProject();
        try {
            return CMakeBuild.build(project, buildAndRunConfigurations).get();
        }
        catch (ExecutionException ex3) {
            return false;
        }
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}
