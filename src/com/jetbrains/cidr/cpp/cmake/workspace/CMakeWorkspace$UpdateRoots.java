// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.jetbrains.cidr.project.CidrRootsSynchronizer;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.List;

private static class UpdateRoots
{
    @NotNull
    List<File> roots;
    @NotNull
    List<File> sourceFiles;
    @NotNull
    CidrRootsSynchronizer.RootsInfo rootsInfo;
    
    public UpdateRoots(@NotNull final List<File> roots, @NotNull final List<File> sourceFiles, @NotNull final CidrRootsSynchronizer.RootsInfo rootsInfo) {
        if (roots == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$UpdateRoots", "<init>"));
        }
        if (sourceFiles == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$UpdateRoots", "<init>"));
        }
        if (rootsInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootsInfo", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$UpdateRoots", "<init>"));
        }
        this.roots = roots;
        this.sourceFiles = sourceFiles;
        this.rootsInfo = rootsInfo;
    }
}
