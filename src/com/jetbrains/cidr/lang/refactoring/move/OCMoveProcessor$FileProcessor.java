// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;

@FunctionalInterface
private interface FileProcessor
{
    void process(@NotNull final VirtualFile p0, @NotNull final OCFile p1);
}
