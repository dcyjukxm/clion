// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

class CMakeWorkspaceWatcher$8$1 extends FileStampFunctionAdapter {
    @Nullable
    @Override
    public CMakeFile getCMakeFile(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8$1", "getCMakeFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}