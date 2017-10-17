// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.build;

import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import java.io.File;
import com.jetbrains.cidr.cpp.toolchains.CPPEnvironment;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeResolveConfiguration;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.jetbrains.cidr.execution.build.CidrBuild;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.build.ShowBuildConsoleInternalAction;

public class ShowCMakeBuildConsoleInternalAction extends ShowBuildConsoleInternalAction
{
    @Override
    protected void createConsole(final Project project, final CidrBuild.BuildContext buildContext) {
        CMakeEnvironment environment;
        try {
            final CMakeWorkspace instance = CMakeWorkspace.getInstance(project);
            environment = instance.getEnvironmentFor(instance.getConfigurations().get(0).getConfiguration());
        }
        catch (ExecutionException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        CMakeBuild.createBuildListenerAndConsole(project, environment, null, buildContext, true);
    }
}
