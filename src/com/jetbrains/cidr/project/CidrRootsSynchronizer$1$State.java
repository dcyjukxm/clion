// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.openapi.roots.ExcludeFolder;
import com.intellij.openapi.roots.SourceFolder;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.roots.ContentEntry;

class State
{
    @Nullable
    ContentEntry contentEntry;
    @Nullable
    SourceFolder sourceFolder;
    @Nullable
    ExcludeFolder excludeFolder;
    boolean isLibraryRoot;
    boolean isExplicitRoot;
    
    public State() {
    }
    
    public State(final State state) {
        this.contentEntry = state.contentEntry;
        this.sourceFolder = state.sourceFolder;
        this.excludeFolder = state.excludeFolder;
        this.isLibraryRoot = state.isLibraryRoot;
        this.isExplicitRoot = state.isExplicitRoot;
    }
}
