// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import gnu.trove.THashSet;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRootProcessor;

static final class CMakeWorkspace$10 extends HeadersSearchRootProcessor {
    final /* synthetic */ THashSet val$filesToIndex;
    
    @Override
    public boolean process(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$10", "process"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!virtualFile.isDirectory()) {
                this.val$filesToIndex.add((Object)virtualFile);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}