// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

interface FileStampFunction
{
    @Nullable
    FileStamp getAlreadyCalculatedStamp();
    
    @Nullable
    CMakeFile getCMakeFile(@NotNull final Project p0);
}
