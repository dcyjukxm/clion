// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import java.io.File;

private static class RootItem
{
    @NotNull
    final File file;
    @NotNull
    final RootKind kind;
    
    public RootItem(@NotNull final File file, @NotNull final RootKind kind) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem", "<init>"));
        }
        if (kind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem", "<init>"));
        }
        this.kind = kind;
        this.file = file;
    }
    
    @NotNull
    String getUrl() {
        String pathToUrl;
        try {
            pathToUrl = VfsUtilCore.pathToUrl(FileUtil.toSystemIndependentName(this.file.getPath()));
            if (pathToUrl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem", "getUrl"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return pathToUrl;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
