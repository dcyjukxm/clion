// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.List;

public static class HeaderSearchRoots
{
    @NotNull
    public final List<File> systemHeaderRoots;
    @NotNull
    public final List<File> userHeaderRoots;
    @NotNull
    public final List<File> excludeRoots;
    
    public HeaderSearchRoots() {
        this.systemHeaderRoots = new ArrayList<File>();
        this.userHeaderRoots = new ArrayList<File>();
        this.excludeRoots = new ArrayList<File>();
    }
}
