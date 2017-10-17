// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.openapi.vfs.VirtualFile;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.headerRoots.RealFramework;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRootProcessor;

static final class CidrRootsSynchronizer$3 extends HeadersSearchRootProcessor {
    final /* synthetic */ HeaderSearchRoots val$result;
    final /* synthetic */ HeadersSearchRoot val$each;
    
    @Override
    public boolean shouldProcessRootsOnly() {
        return true;
    }
    
    @Override
    public boolean processFramework(@NotNull final RealFramework realFramework) {
        try {
            if (realFramework == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "framework", "com/jetbrains/cidr/project/CidrRootsSynchronizer$3", "processFramework"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.val$result.excludeRoots.add(new File(realFramework.getVirtualFile().getPath()));
        return true;
    }
    
    @Override
    public boolean process(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/project/CidrRootsSynchronizer$3", "process"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0068: {
            try {
                if (!virtualFile.isDirectory()) {
                    return true;
                }
                final HeadersSearchRootProcessor headersSearchRootProcessor = this;
                final HeadersSearchRoot headersSearchRoot = headersSearchRootProcessor.val$each;
                final boolean b = headersSearchRoot.isUserHeaders();
                if (b) {
                    break Label_0068;
                }
                break Label_0068;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final HeadersSearchRootProcessor headersSearchRootProcessor = this;
                final HeadersSearchRoot headersSearchRoot = headersSearchRootProcessor.val$each;
                final boolean b = headersSearchRoot.isUserHeaders();
                if (b) {
                    this.val$result.userHeaderRoots.add(new File(virtualFile.getPath()));
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        this.val$result.systemHeaderRoots.add(new File(virtualFile.getPath()));
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}