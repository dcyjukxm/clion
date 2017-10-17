// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCGroupedFileNaming
{
    public static final ExtensionPointName<OCGroupedFileNaming> EP_NAME = ExtensionPointName.create("cidr.lang.groupedFileNaming");
    
    boolean isGroupedFile(@NotNull final VirtualFile p0);
    
    @NotNull
    String getBaseName(@NotNull final String p0);
}
