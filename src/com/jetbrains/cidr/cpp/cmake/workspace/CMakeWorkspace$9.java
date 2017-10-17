// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.util.NullableFunction;
import java.io.File;
import java.util.Map;

class CMakeWorkspace$9 implements FileStamp.FileStampFunction {
    final /* synthetic */ Map val$alreadyCalculatedStamps;
    final /* synthetic */ File val$each;
    final /* synthetic */ NullableFunction val$getVirtualFile;
    
    @Override
    public FileStamp getAlreadyCalculatedStamp() {
        return this.val$alreadyCalculatedStamps.get(this.val$each);
    }
    
    @Nullable
    @Override
    public CMakeFile getCMakeFile(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$9", "getCMakeFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final VirtualFile virtualFile = (VirtualFile)this.val$getVirtualFile.fun((Object)this.val$each);
        try {
            if (virtualFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return FileStamp.createOrGetCMakeFile(project, virtualFile);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}