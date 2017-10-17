// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;

public interface OCWorkspace
{
    @NotNull
    Collection<VirtualFile> getLibraryFilesToBuildSymbols();
    
    boolean areFromSameProject(@Nullable final VirtualFile p0, @Nullable final VirtualFile p1);
    
    boolean areFromSamePackage(@Nullable final VirtualFile p0, @Nullable final VirtualFile p1);
    
    boolean isInSDK(@Nullable final VirtualFile p0);
    
    boolean isFromWrongSDK(@NotNull final OCSymbol p0, @Nullable final VirtualFile p1);
    
    @NotNull
    List<? extends OCResolveConfiguration> getConfigurations();
    
    @NotNull
    List<? extends OCResolveConfiguration> getConfigurationsForFile(@Nullable final VirtualFile p0);
}
