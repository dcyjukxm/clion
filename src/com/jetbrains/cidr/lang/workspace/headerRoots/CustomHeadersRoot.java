// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapFileImpl;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapSymbolBuilder;
import java.io.IOException;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapFileSymbol;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapInCollectionPathResolver;
import com.intellij.openapi.vfs.newvfs.impl.NullVirtualFile;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.search.PsiElementProcessor;
import java.util.Arrays;
import com.intellij.openapi.util.io.FileUtil;
import java.util.Collection;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;

public class CustomHeadersRoot extends HeadersSearchRoot
{
    @Nullable
    private final VirtualFile myRoot;
    @NotNull
    private final VirtualFile[] myFilesCache;
    private final int myHashCodeCache;
    
    public CustomHeadersRoot(@NotNull final Project project, @Nullable final VirtualFile myRoot, @NotNull final Collection<VirtualFile> collection) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/headerRoots/CustomHeadersRoot", "<init>"));
        }
        if (collection == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/workspace/headerRoots/CustomHeadersRoot", "<init>"));
        }
        super(project);
        this.myRoot = myRoot;
        Arrays.sort(this.myFilesCache = collection.toArray(new VirtualFile[collection.size()]), (virtualFile, virtualFile2) -> FileUtil.comparePaths(virtualFile.getPath(), virtualFile2.getPath()));
        this.myHashCodeCache = Arrays.hashCode(this.myFilesCache);
    }
    
    public VirtualFile getVirtualFile() {
        return this.myRoot;
    }
    
    public boolean processChildren(final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor) {
        return this.processCustomFileSet(psiElementProcessor, Arrays.asList(this.myFilesCache), false);
    }
    
    @NotNull
    @Override
    protected ModuleMapPathResolver createModuleMapPathResolver() {
        Object o = null;
        Label_0025: {
            try {
                if (this.myRoot == null) {
                    o = NullVirtualFile.INSTANCE;
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            o = this.myRoot;
        }
        final ModuleMapInCollectionPathResolver moduleMapInCollectionPathResolver = new ModuleMapInCollectionPathResolver((VirtualFile)o, Arrays.asList(this.myFilesCache));
        if (moduleMapInCollectionPathResolver == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/CustomHeadersRoot", "createModuleMapPathResolver"));
        }
        return moduleMapInCollectionPathResolver;
    }
    
    @Nullable
    @Override
    protected ModuleMapFileSymbol buildModuleMap() {
        VirtualFile virtualFile = (VirtualFile)ContainerUtil.find((Object[])this.myFilesCache, virtualFile -> FileUtil.pathsEqual("module.modulemap", virtualFile.getName()));
        if (virtualFile == null) {
            virtualFile = (VirtualFile)ContainerUtil.find((Object[])this.myFilesCache, virtualFile -> FileUtil.pathsEqual("module.map", virtualFile.getName()));
        }
        try {
            if (virtualFile == null) {
                return null;
            }
        }
        catch (IOException ex) {
            throw c(ex);
        }
        try {
            return new ModuleMapSymbolBuilder(this.myProject, this.createModuleMapPathResolver()).processFile(ModuleMapFileImpl.createFromText(VfsUtilCore.loadText(virtualFile), PsiManager.getInstance(this.myProject)));
        }
        catch (IOException ex2) {
            OCLog.LOG.error("Failed to read module map from " + virtualFile, (Throwable)ex2);
            return null;
        }
    }
    
    public String toString() {
        return this.getName() + ", size: " + this.myFilesCache.length;
    }
    
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final CustomHeadersRoot customHeadersRoot = this;
                final Class<? extends CustomHeadersRoot> clazz = customHeadersRoot.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final CustomHeadersRoot customHeadersRoot = this;
                final Class<? extends CustomHeadersRoot> clazz = customHeadersRoot.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        final CustomHeadersRoot customHeadersRoot2 = (CustomHeadersRoot)o;
        try {
            if (this.myHashCodeCache != customHeadersRoot2.myHashCodeCache) {
                return false;
            }
            final CustomHeadersRoot customHeadersRoot3 = this;
            final VirtualFile[] array = customHeadersRoot3.myFilesCache;
            final CustomHeadersRoot customHeadersRoot4 = customHeadersRoot2;
            final VirtualFile[] array2 = customHeadersRoot4.myFilesCache;
            final boolean b = Arrays.equals(array, array2);
            if (!b) {
                return false;
            }
            return true;
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        try {
            final CustomHeadersRoot customHeadersRoot3 = this;
            final VirtualFile[] array = customHeadersRoot3.myFilesCache;
            final CustomHeadersRoot customHeadersRoot4 = customHeadersRoot2;
            final VirtualFile[] array2 = customHeadersRoot4.myFilesCache;
            final boolean b = Arrays.equals(array, array2);
            if (!b) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        return true;
    }
    
    public int hashCode() {
        return this.myHashCodeCache;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
