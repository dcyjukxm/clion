// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Map;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeaderRoots;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Set;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolder;

public interface OCResolveConfiguration extends UserDataHolder, Comparable<OCResolveConfiguration>
{
    @NotNull
    Project getProject();
    
    @NotNull
    String getDisplayName(final boolean p0);
    
    @NotNull
    Collection<VirtualFile> getSources();
    
    @NotNull
    Set<VirtualFile> getPrecompiledHeaders();
    
    @NotNull
    List<VirtualFile> getPrecompiledHeaders(@NotNull final OCLanguageKind p0, @NotNull final VirtualFile p1);
    
    @NotNull
    OCIncludeMap getIncludeMap();
    
    @Nullable
    OCLanguageKind getDeclaredLanguageKind(@NotNull final VirtualFile p0);
    
    @Deprecated
    @NotNull
    OCLanguageKind getMaximumLanguageKind();
    
    @NotNull
    HeaderRoots getProjectHeadersRoots();
    
    @NotNull
    HeaderRoots getLibraryHeadersRoots(@NotNull final OCResolveRootAndConfiguration p0);
    
    @NotNull
    String getPreprocessorDefines(@NotNull final OCLanguageKind p0, @Nullable final VirtualFile p1);
    
    Map<OCCompilerFeatures.Type<?>, ?> getCompilerFeatures(@NotNull final OCLanguageKind p0, @Nullable final VirtualFile p1);
    
    @NotNull
    OCCompilerSettings getCompilerSettings();
    
    @Nullable
    Object getIndexingCluster();
}
