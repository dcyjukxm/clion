// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.Collection;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.List;

public static class RootsInfo
{
    @NotNull
    public final List<File> watchRoots;
    @NotNull
    public final List<File> contentRoots;
    @NotNull
    public final List<File> sourceFiles;
    @NotNull
    public final List<File> explicitSourceFolders;
    @NotNull
    public final List<File> explicitLibraryRoots;
    @NotNull
    public final List<File> explicitExcludeFolders;
    @NotNull
    public final HeaderSearchRoots headerSearchRoots;
    public boolean registerSystemHeaderRootUnderContentRootAsLibraries;
    
    public RootsInfo() {
        this.watchRoots = new ArrayList<File>();
        this.contentRoots = new ArrayList<File>();
        this.sourceFiles = new ArrayList<File>();
        this.explicitSourceFolders = new ArrayList<File>();
        this.explicitLibraryRoots = new ArrayList<File>();
        this.explicitExcludeFolders = new ArrayList<File>();
        this.headerSearchRoots = new HeaderSearchRoots();
        this.registerSystemHeaderRootUnderContentRootAsLibraries = true;
    }
    
    public void fillHeaderSearchRoots(@NotNull final Collection<? extends HeadersSearchRoot> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerRoots", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootsInfo", "fillHeaderSearchRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CidrRootsSynchronizer.collectHeaderSearchRoots(collection, this.headerSearchRoots);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
