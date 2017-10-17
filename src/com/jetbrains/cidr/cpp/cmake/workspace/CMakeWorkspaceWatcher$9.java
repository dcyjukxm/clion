// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.io.File;
import com.intellij.util.NotNullFunction;

class CMakeWorkspaceWatcher$9 implements FileStamp.FileStampFunction {
    FileStampFunctionAdapter delegate = (FileStampFunctionAdapter)this.val$getFileDocumentStampFunction.fun((Object)this.val$newFile);
    final /* synthetic */ NotNullFunction val$getFileDocumentStampFunction;
    final /* synthetic */ File val$newFile;
    final /* synthetic */ FileStamp val$oldStamp;
    
    @Override
    public FileStamp getAlreadyCalculatedStamp() {
        try {
            if (this.delegate.useAlreadyCalculatedStamp) {
                return this.val$oldStamp;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public CMakeFile getCMakeFile(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$9", "getCMakeFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.delegate.getCMakeFile(project);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}