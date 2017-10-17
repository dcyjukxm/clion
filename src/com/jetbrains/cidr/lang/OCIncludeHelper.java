// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.Nullable;
import com.intellij.util.Processor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCIncludeHelper
{
    public static final ExtensionPointName<OCIncludeHelper> EP_NAME = ExtensionPointName.create("cidr.lang.includeHelper");
    
    OCIncludeHelpers.ShowInCompletion showInCompletion(@NotNull final PsiFileSystemItem p0);
    
    boolean processContainingFramework(@NotNull final Project p0, @NotNull final VirtualFile p1, @NotNull final Processor<PsiFileSystemItem> p2);
    
    @Nullable
    VirtualFile findHeadersRoot(@NotNull final PsiFileSystemItem p0);
}
