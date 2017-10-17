// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.editor.Document;

class CMakeWorkspaceWatcher$8$2 extends FileStampFunctionAdapter {
    final /* synthetic */ boolean val$checkDocumentText;
    final /* synthetic */ Document val$document;
    final /* synthetic */ VirtualFile val$vFile;
    
    @Nullable
    @Override
    public CMakeFile getCMakeFile(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$8$2", "getCMakeFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.val$checkDocumentText) {
                return FileStamp.createOrGetCMakeFile(project, this.val$document);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return FileStamp.createOrGetCMakeFile(project, this.val$vFile);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}