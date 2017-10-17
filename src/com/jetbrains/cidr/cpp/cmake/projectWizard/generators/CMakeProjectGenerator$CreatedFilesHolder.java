// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators;

import com.intellij.openapi.vfs.VirtualFile;

private static class CreatedFilesHolder
{
    private final VirtualFile cMakeFile;
    private final VirtualFile[] sourceFiles;
    
    private CreatedFilesHolder(final VirtualFile cMakeFile, final VirtualFile[] sourceFiles) {
        this.cMakeFile = cMakeFile;
        this.sourceFiles = sourceFiles;
    }
}
