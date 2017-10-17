// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import java.util.Collections;
import java.util.Iterator;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import java.util.LinkedHashSet;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.MultiMap;

static class PCHCache
{
    private final MultiMap<VirtualFile, OCResolveConfiguration> pch2configs;
    
    public PCHCache(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache", "<init>"));
        }
        this.pch2configs = new MultiMap<VirtualFile, OCResolveConfiguration>() {
            @NotNull
            protected Collection<OCResolveConfiguration> createCollection() {
                LinkedHashSet linkedHashSet;
                try {
                    linkedHashSet = ContainerUtil.newLinkedHashSet();
                    if (linkedHashSet == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache$1", "createCollection"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return (Collection<OCResolveConfiguration>)linkedHashSet;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
        for (final OCResolveConfiguration ocResolveConfiguration : OCWorkspaceManager.getWorkspace(project).getConfigurations()) {
            final Iterator<VirtualFile> iterator2 = ocResolveConfiguration.getPrecompiledHeaders().iterator();
            while (iterator2.hasNext()) {
                this.pch2configs.putValue((Object)iterator2.next(), (Object)ocResolveConfiguration);
            }
        }
    }
    
    public Collection<OCResolveConfiguration> getConfigurations(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pch", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache", "getConfigurations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Collections.unmodifiableCollection((Collection<? extends OCResolveConfiguration>)this.pch2configs.get((Object)virtualFile));
    }
    
    public boolean isPCH(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache", "isPCH"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.pch2configs.containsKey((Object)virtualFile);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
