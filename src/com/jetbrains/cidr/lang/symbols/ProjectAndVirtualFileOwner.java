// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;

public interface ProjectAndVirtualFileOwner
{
    void init(@Nullable final Project p0, @Nullable final VirtualFile p1);
    
    @Nullable
    Project getProject();
    
    @Nullable
    VirtualFile getContainingFile();
}
