// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.NotNull;

public interface FileFactory
{
    PsiFile createFileFromText(@NotNull final String p0, @Nullable final FileType p1, @NotNull final String p2);
}
