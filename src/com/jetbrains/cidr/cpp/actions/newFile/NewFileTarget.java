// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;

public interface NewFileTarget
{
    boolean addFiles(final List<VirtualFile> p0);
    
    @NotNull
    String getAdditionalInfo();
}
