// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCFileTypeHelper
{
    public static final ExtensionPointName<OCFileTypeHelper> EP_NAME = ExtensionPointName.create("cidr.lang.fileTypeHelper");
    
    @Nullable
    FileType getFileType(@NotNull final VirtualFile p0);
    
    boolean isHeaderFile(@NotNull final String p0);
    
    boolean isSourceFile(@NotNull final String p0);
}
