// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Document;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.util.Map;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;

class CMakeWorkspaceWatcher$4$1$1 extends FileStampFunctionAdapter {
    CMakeFile myCachedPsiFile;
    final /* synthetic */ Map.Entry val$eachEntry;
    
    @Nullable
    @Override
    public CMakeFile getCMakeFile(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$4$1$1", "getCMakeFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myCachedPsiFile != null) {
                return this.myCachedPsiFile;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.myCachedPsiFile = FileStamp.createOrGetCMakeFile(project, this.val$eachEntry.getValue());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}