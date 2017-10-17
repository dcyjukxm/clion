// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.vfs.VirtualFile;

private static final class OCBuildFileDescriptor
{
    private final VirtualFile myFile;
    private final OCResolveConfiguration myConfiguration;
    private final OCLanguageKind myLanguageKind;
    
    private OCBuildFileDescriptor(final OCResolveConfiguration myConfiguration, final VirtualFile myFile, final OCLanguageKind myLanguageKind) {
        this.myConfiguration = myConfiguration;
        this.myFile = myFile;
        this.myLanguageKind = myLanguageKind;
    }
}
