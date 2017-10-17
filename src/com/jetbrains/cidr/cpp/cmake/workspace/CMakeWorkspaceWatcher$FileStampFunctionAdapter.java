// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

private abstract static class FileStampFunctionAdapter
{
    final boolean useAlreadyCalculatedStamp;
    
    public FileStampFunctionAdapter(final boolean useAlreadyCalculatedStamp) {
        this.useAlreadyCalculatedStamp = useAlreadyCalculatedStamp;
    }
    
    @Nullable
    public abstract CMakeFile getCMakeFile(@NotNull final Project p0);
}
