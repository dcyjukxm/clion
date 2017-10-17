// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.project.CidrRootsSynchronizer;

private static class MyRootsSynchronizer extends CidrRootsSynchronizer
{
    public MyRootsSynchronizer(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$MyRootsSynchronizer", "<init>"));
        }
        super(project, CMakeWorkspace.access$800());
    }
}
