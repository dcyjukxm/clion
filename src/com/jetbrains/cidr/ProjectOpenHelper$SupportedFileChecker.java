// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;

public interface SupportedFileChecker
{
    boolean isSupportedFile(@NotNull final VirtualFile p0);
    
    boolean isSupportedFile(@NotNull final String p0);
}
