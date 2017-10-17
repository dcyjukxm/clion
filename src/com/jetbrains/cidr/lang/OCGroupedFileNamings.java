// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;

public class OCGroupedFileNamings
{
    @Nullable
    public static OCGroupedFileNaming getGroupedFileNaming(@Nullable final VirtualFile virtualFile) {
        if (virtualFile == null) {
            return null;
        }
        for (final OCGroupedFileNaming ocGroupedFileNaming : (OCGroupedFileNaming[])Extensions.getExtensions((ExtensionPointName)OCGroupedFileNaming.EP_NAME)) {
            if (ocGroupedFileNaming.isGroupedFile(virtualFile)) {
                return ocGroupedFileNaming;
            }
        }
        return null;
    }
}
