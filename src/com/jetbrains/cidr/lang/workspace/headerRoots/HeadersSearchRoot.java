// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import com.intellij.psi.PsiElement;
import java.util.Arrays;
import com.intellij.psi.search.SpecificNameItemProcessor;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.search.PsiElementProcessor;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import java.io.File;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.modulemap.psi.impl.ModuleMapFileImpl;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapSymbolBuilder;
import java.io.IOException;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapDiskCache;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapCache;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapFileSymbol;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import java.util.Collection;
import com.jetbrains.cidr.modulemap.ModuleMapModulesImpl;
import com.jetbrains.cidr.modulemap.ModuleMapModules;
import com.intellij.psi.PsiFileSystemItem;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.LocalFileSystem;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.psi.impl.SyntheticFileSystemItem;

public abstract class HeadersSearchRoot extends SyntheticFileSystemItem
{
    @Nullable
    public static HeadersSearchRoot createFromHeaderSearchPath(@NotNull final Project project, @NotNull final HeadersSearchPath headersSearchPath) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "createFromHeaderSearchPath"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (headersSearchPath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "searchPath", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "createFromHeaderSearchPath"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        final VirtualFile fileByIoFile = LocalFileSystem.getInstance().findFileByIoFile(headersSearchPath.getFile());
        try {
            if (fileByIoFile == null) {
                return null;
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        return createFromHeaderSearchPath(project, headersSearchPath, fileByIoFile);
    }
    
    @Nullable
    public static HeadersSearchRoot createFromHeaderSearchPath(@NotNull final Project project, @NotNull final HeadersSearchPath headersSearchPath, @NotNull final VirtualFile virtualFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "createFromHeaderSearchPath"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (headersSearchPath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "searchPath", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "createFromHeaderSearchPath"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "preparedVirtualFile", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "createFromHeaderSearchPath"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        try {
            if (!virtualFile.isDirectory()) {
                return null;
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        try {
            if (headersSearchPath.isFrameworksSearchPath()) {
                return new FrameworksSearchRoot(project, virtualFile);
            }
        }
        catch (IllegalStateException ex5) {
            throw b(ex5);
        }
        return new IncludedHeadersRoot(project, virtualFile, headersSearchPath.isRecursive(), headersSearchPath.isUserHeaders());
    }
    
    public HeadersSearchRoot(final Project project) {
        super(project);
    }
    
    public PsiFileSystemItem getParent() {
        return null;
    }
    
    @NotNull
    public ModuleMapModules collectDeclaredModules() {
        final ModuleMapModulesImpl moduleMapModulesImpl = new ModuleMapModulesImpl();
        ModuleMapModules moduleMapModules2 = null;
        Label_0046: {
            try {
                final ModuleMapFileSymbol moduleMapFileSymbol;
                final ModuleMapModules moduleMapModules;
                this.getPossibleModuleMapLocations().forEach(headersSearchRoot -> {
                    headersSearchRoot.getModuleMap();
                    try {
                        if (moduleMapFileSymbol != null) {
                            moduleMapModules.add(moduleMapFileSymbol.getAllModulesRecursively());
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw b(ex);
                    }
                    return;
                });
                if (moduleMapModulesImpl.isEmpty()) {
                    final ModuleMapModules empty;
                    moduleMapModules2 = (empty = ModuleMapModules.Companion.getEMPTY());
                    break Label_0046;
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            ModuleMapModules empty;
            moduleMapModules2 = (empty = moduleMapModulesImpl);
            try {
                if (empty == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "collectDeclaredModules"));
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        return moduleMapModules2;
    }
    
    @NotNull
    public Collection<? extends HeadersSearchRoot> getPossibleModuleMapLocations() {
        List<HeadersSearchRoot> singletonList;
        try {
            singletonList = Collections.singletonList(this);
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "getPossibleModuleMapLocations"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return singletonList;
    }
    
    @Nullable
    public ModuleMapFileSymbol getModuleMap() {
        final ModuleMapCache instance = ModuleMapCache.getInstance(this.myProject);
        final ModuleMapCache.Key create = ModuleMapCache.Key.create(this);
        ModuleMapFileSymbol moduleMapFileSymbol = instance.get(create);
        if (moduleMapFileSymbol == null) {
            final ModuleMapDiskCache instance2 = ModuleMapDiskCache.getInstance();
            ModuleMapFileSymbol moduleMapFileSymbol2 = instance2.getModuleMap(this);
            Label_0065: {
                try {
                    if (moduleMapFileSymbol2 != null) {
                        if (ModuleMapCache.shouldReloadCache()) {
                            break Label_0065;
                        }
                    }
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                moduleMapFileSymbol2 = this.buildModuleMap();
                instance2.cache(this, moduleMapFileSymbol2);
            }
            moduleMapFileSymbol = instance.cacheOrGet(create, moduleMapFileSymbol2);
        }
        try {
            if (moduleMapFileSymbol == ModuleMapFileSymbol.Companion.getEMPTY()) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return moduleMapFileSymbol;
    }
    
    @Nullable
    protected ModuleMapFileSymbol buildModuleMap() {
        final File moduleMapFile = this.findModuleMapFile();
        ModuleMapFileSymbol processFile = null;
        try {
            if (moduleMapFile == null || !moduleMapFile.isFile()) {
                return processFile;
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            processFile = new ModuleMapSymbolBuilder(this.myProject, this.createModuleMapPathResolver()).processFile(ModuleMapFileImpl.createFromText(FileUtil.loadFile(moduleMapFile), PsiManager.getInstance(this.myProject)));
        }
        catch (IOException ex2) {
            OCLog.LOG.info("IOException reading module map at path " + moduleMapFile.getPath(), (Throwable)ex2);
        }
        return processFile;
    }
    
    @NotNull
    protected ModuleMapPathResolver createModuleMapPathResolver() {
        throw new IllegalStateException("createModuleMapPathResolver() is not implemented for a HeadersSearchRoot with findModuleMapFile() implemented");
    }
    
    @Nullable
    protected File findModuleMapFile() {
        return null;
    }
    
    public boolean isUserHeaders() {
        return false;
    }
    
    protected boolean processUnder(@NotNull final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor, @Nullable final VirtualFile virtualFile) {
        try {
            if (psiElementProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "processUnder"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return this.processUnder(psiElementProcessor, virtualFile, null);
    }
    
    protected boolean processUnder(@NotNull final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor, @Nullable final VirtualFile virtualFile, @Nullable final Condition<VirtualFile> condition) {
        try {
            if (psiElementProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "processUnder"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return this.processUnder(psiElementProcessor, virtualFile, false, condition);
    }
    
    protected boolean processUnder(@NotNull final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor, @Nullable final VirtualFile virtualFile, final boolean b, @Nullable final Condition<VirtualFile> condition) {
        try {
            if (psiElementProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "processUnder"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (IncludedFilesProcessor.shouldSkip(virtualFile)) {
                return true;
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        Label_0101: {
            try {
                if (!(psiElementProcessor instanceof HeadersSearchRootProcessor)) {
                    break Label_0101;
                }
                final HeadersSearchRoot headersSearchRoot = this;
                final Project project = headersSearchRoot.myProject;
                final VirtualFile virtualFile2 = virtualFile;
                final Condition<VirtualFile> condition2 = condition;
                final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor2 = psiElementProcessor;
                final boolean b2 = IncludedFilesProcessor.processFile(project, virtualFile2, condition2, psiElementProcessor2);
                if (!b2) {
                    return false;
                }
                break Label_0101;
            }
            catch (IllegalStateException ex4) {
                throw b(ex4);
            }
            try {
                final HeadersSearchRoot headersSearchRoot = this;
                final Project project = headersSearchRoot.myProject;
                final VirtualFile virtualFile2 = virtualFile;
                final Condition<VirtualFile> condition2 = condition;
                final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor2 = psiElementProcessor;
                final boolean b2 = IncludedFilesProcessor.processFile(project, virtualFile2, condition2, psiElementProcessor2);
                if (!b2) {
                    return false;
                }
            }
            catch (IllegalStateException ex5) {
                throw b(ex5);
            }
            try {
                if (((HeadersSearchRootProcessor)psiElementProcessor).shouldProcessRootsOnly()) {
                    return true;
                }
            }
            catch (IllegalStateException ex6) {
                throw b(ex6);
            }
        }
        if (psiElementProcessor instanceof SpecificNameItemProcessor) {
            final VirtualFile child = virtualFile.findChild(((SpecificNameItemProcessor)psiElementProcessor).getName());
            Label_0168: {
                try {
                    if (child == null) {
                        break Label_0168;
                    }
                    final HeadersSearchRoot headersSearchRoot2 = this;
                    final Project project2 = headersSearchRoot2.myProject;
                    final VirtualFile virtualFile3 = child;
                    final Condition<VirtualFile> condition3 = condition;
                    final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor3 = psiElementProcessor;
                    final boolean b3 = IncludedFilesProcessor.processFile(project2, virtualFile3, condition3, psiElementProcessor3);
                    if (b3) {
                        break Label_0168;
                    }
                    return false;
                }
                catch (IllegalStateException ex7) {
                    throw b(ex7);
                }
                try {
                    final HeadersSearchRoot headersSearchRoot2 = this;
                    final Project project2 = headersSearchRoot2.myProject;
                    final VirtualFile virtualFile3 = child;
                    final Condition<VirtualFile> condition3 = condition;
                    final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor3 = psiElementProcessor;
                    final boolean b3 = IncludedFilesProcessor.processFile(project2, virtualFile3, condition3, psiElementProcessor3);
                    if (b3) {
                        return true;
                    }
                }
                catch (IllegalStateException ex8) {
                    throw b(ex8);
                }
            }
            return false;
        }
        final VirtualFile[] children = virtualFile.getChildren();
        Label_0215: {
            try {
                if (children == null) {
                    break Label_0215;
                }
                final HeadersSearchRoot headersSearchRoot3 = this;
                final Project project3 = headersSearchRoot3.myProject;
                final VirtualFile[] array = children;
                final List<VirtualFile> list = Arrays.asList(array);
                final Condition<VirtualFile> condition4 = condition;
                final boolean b5 = b;
                final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor4 = psiElementProcessor;
                final boolean b6 = IncludedFilesProcessor.processFiles(project3, list, condition4, b5, psiElementProcessor4);
                if (b6) {
                    break Label_0215;
                }
                return false;
            }
            catch (IllegalStateException ex9) {
                throw b(ex9);
            }
            try {
                final HeadersSearchRoot headersSearchRoot3 = this;
                final Project project3 = headersSearchRoot3.myProject;
                final VirtualFile[] array = children;
                final List<VirtualFile> list = Arrays.asList(array);
                final Condition<VirtualFile> condition4 = condition;
                final boolean b5 = b;
                final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor4 = psiElementProcessor;
                final boolean b6 = IncludedFilesProcessor.processFiles(project3, list, condition4, b5, psiElementProcessor4);
                if (b6) {
                    return true;
                }
            }
            catch (IllegalStateException ex10) {
                throw b(ex10);
            }
        }
        return false;
    }
    
    protected boolean processCustomFileSet(@NotNull final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor, @NotNull final Iterable<VirtualFile> iterable, final boolean b) {
        try {
            if (psiElementProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "processCustomFileSet"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (iterable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot", "processCustomFileSet"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return IncludedFilesProcessor.processFiles(this.myProject, iterable, null, b, psiElementProcessor);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
