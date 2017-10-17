// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.jetbrains.cidr.lang.workspace.headerRoots.HeaderRoots;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;

public class OCResolveRootAndConfiguration
{
    private final VirtualFile myRootFile;
    @Nullable
    private final OCResolveConfiguration myConfiguration;
    private final OCLanguageKind myKind;
    
    public OCResolveRootAndConfiguration(@Nullable final OCResolveConfiguration myConfiguration, @NotNull final OCLanguageKind myKind) {
        if (myKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration", "<init>"));
        }
        this.myConfiguration = myConfiguration;
        this.myRootFile = null;
        this.myKind = myKind;
    }
    
    public OCResolveRootAndConfiguration(@Nullable final OCResolveConfiguration myConfiguration, @NotNull final VirtualFile myRootFile) {
        if (myRootFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootFile", "com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration", "<init>"));
        }
        this.myConfiguration = myConfiguration;
        this.myRootFile = myRootFile;
        this.myKind = null;
    }
    
    @Nullable
    public VirtualFile getRootFile() {
        return this.myRootFile;
    }
    
    @Nullable
    public Project getProject() {
        try {
            if (this.myConfiguration == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myConfiguration.getProject();
    }
    
    @Nullable
    public OCResolveConfiguration getConfiguration() {
        return this.myConfiguration;
    }
    
    public OCLanguageKind getKind() {
        return this.myKind;
    }
    
    @NotNull
    public HeaderRoots getLibraryHeadersRoots() {
        HeaderRoots libraryHeadersRoots = null;
        Label_0056: {
            HeaderRoots headerRoots = null;
            Label_0021: {
                try {
                    if (this.myConfiguration != null) {
                        break Label_0056;
                    }
                    headerRoots = HeaderRoots.EMPTY;
                    if (headerRoots == null) {
                        break Label_0021;
                    }
                    return headerRoots;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    headerRoots = HeaderRoots.EMPTY;
                    if (headerRoots == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration", "getLibraryHeadersRoots"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return headerRoots;
            try {
                libraryHeadersRoots = this.myConfiguration.getLibraryHeadersRoots(this);
                if (libraryHeadersRoots == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration", "getLibraryHeadersRoots"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return libraryHeadersRoots;
    }
    
    @NotNull
    public HeaderRoots getProjectHeadersRoots() {
        HeaderRoots projectHeadersRoots = null;
        Label_0056: {
            HeaderRoots headerRoots = null;
            Label_0021: {
                try {
                    if (this.myConfiguration != null) {
                        break Label_0056;
                    }
                    headerRoots = HeaderRoots.EMPTY;
                    if (headerRoots == null) {
                        break Label_0021;
                    }
                    return headerRoots;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    headerRoots = HeaderRoots.EMPTY;
                    if (headerRoots == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration", "getProjectHeadersRoots"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return headerRoots;
            try {
                projectHeadersRoots = this.myConfiguration.getProjectHeadersRoots();
                if (projectHeadersRoots == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration", "getProjectHeadersRoots"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return projectHeadersRoots;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
