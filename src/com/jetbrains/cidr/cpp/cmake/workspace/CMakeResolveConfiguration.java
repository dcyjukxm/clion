// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.jetbrains.cidr.lang.toolchains.CidrSwitchBuilder;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfigurationSettings;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerKind;
import com.jetbrains.cidr.toolchains.OCCompilerSettingsBackedByCompilerCache;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Arrays;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.jetbrains.cidr.lang.workspace.compiler.CidrCompilerResult;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeaderRoots;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCIncludeMap;
import java.util.List;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Set;
import java.util.Iterator;
import gnu.trove.THashMap;
import com.jetbrains.cidr.cpp.cmake.model.CMakeFileSettings;
import java.io.File;
import java.util.Map;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceUtil;
import java.util.Collections;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import com.jetbrains.cidr.toolchains.CompilerInfoCache;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.util.UserDataHolderBase;

public class CMakeResolveConfiguration extends UserDataHolderBase implements OCResolveConfiguration
{
    @NotNull
    private final Project myProject;
    @NotNull
    private final CMakeTarget myTarget;
    @NotNull
    private final CMakeConfiguration myConfig;
    @NotNull
    private final CidrToolEnvironment myEnvironment;
    @NotNull
    private final CompilerInfoCache myCompilerInfoCache;
    @NotNull
    private final CMakeCompilerSettings myCompilerSettings;
    @NotNull
    private final Collection<VirtualFile> mySources;
    
    public CMakeResolveConfiguration(@NotNull final Project myProject, @NotNull final CMakeTarget myTarget, @NotNull final CMakeConfiguration myConfig, @NotNull final CidrToolEnvironment myEnvironment, @NotNull final CompilerInfoCache myCompilerInfoCache, @NotNull final Collection<VirtualFile> collection) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "<init>"));
        }
        if (myTarget == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "<init>"));
        }
        if (myConfig == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "<init>"));
        }
        if (myEnvironment == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "<init>"));
        }
        if (myCompilerInfoCache == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerInfoCache", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "<init>"));
        }
        if (collection == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sources", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "<init>"));
        }
        this.myProject = myProject;
        this.myTarget = myTarget;
        this.myConfig = myConfig;
        this.myEnvironment = myEnvironment;
        this.myCompilerInfoCache = myCompilerInfoCache;
        this.myCompilerSettings = new CMakeCompilerSettings();
        this.mySources = Collections.unmodifiableCollection((Collection<? extends VirtualFile>)collection);
    }
    
    @NotNull
    public Project getProject() {
        Project myProject;
        try {
            myProject = this.myProject;
            if (myProject == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myProject;
    }
    
    @NotNull
    public CMakeConfiguration getConfiguration() {
        CMakeConfiguration myConfig;
        try {
            myConfig = this.myConfig;
            if (myConfig == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myConfig;
    }
    
    @NotNull
    public CMakeTarget getTarget() {
        CMakeTarget myTarget;
        try {
            myTarget = this.myTarget;
            if (myTarget == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTarget;
    }
    
    @NotNull
    public String getDisplayName(final boolean b) {
        String configurationDisplayName;
        try {
            configurationDisplayName = OCWorkspaceUtil.getConfigurationDisplayName(this.myTarget.getName(), this.myConfig.getName(), b);
            if (configurationDisplayName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getDisplayName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return configurationDisplayName;
    }
    
    @NotNull
    String getUniqueName() {
        String string;
        try {
            string = this.myConfig.getId() + "-" + this.myTarget.getName();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getUniqueName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @NotNull
    public Collection<VirtualFile> getSources() {
        Collection<VirtualFile> mySources;
        try {
            mySources = this.mySources;
            if (mySources == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getSources"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySources;
    }
    
    @NotNull
    Map<File, CMakeFileSettings> getFilesWithCustomSettings() {
        final THashMap tHashMap = new THashMap();
        for (final File file : this.myConfig.getSources()) {
            final CMakeFileSettings fileSettings = this.myConfig.getFileSettings(file);
            Label_0070: {
                try {
                    if (fileSettings == null) {
                        continue;
                    }
                    final CMakeFileSettings cMakeFileSettings = fileSettings;
                    final boolean b = cMakeFileSettings.hasCustomSettings();
                    if (b) {
                        break Label_0070;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CMakeFileSettings cMakeFileSettings = fileSettings;
                    final boolean b = cMakeFileSettings.hasCustomSettings();
                    if (!b) {
                        continue;
                    }
                    ((Map<File, CMakeFileSettings>)tHashMap).put(file, fileSettings);
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        THashMap tHashMap2;
        try {
            tHashMap2 = tHashMap;
            if (tHashMap2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getFilesWithCustomSettings"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (Map<File, CMakeFileSettings>)tHashMap2;
    }
    
    @NotNull
    public Set<VirtualFile> getPrecompiledHeaders() {
        Set<VirtualFile> pchFiles;
        try {
            pchFiles = CMakeWorkspace.getInstance(this.myProject).getPCHFiles(this);
            if (pchFiles == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getPrecompiledHeaders"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return pchFiles;
    }
    
    @NotNull
    public List<VirtualFile> getPrecompiledHeaders(@NotNull final OCLanguageKind ocLanguageKind, @NotNull final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getPrecompiledHeaders"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFile", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getPrecompiledHeaders"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String compilerKey = this.getCompilerSettings().getCompilerKey(ocLanguageKind, virtualFile);
        List<VirtualFile> pchFiles;
        try {
            pchFiles = CMakeWorkspace.getInstance(this.myProject).getPCHFiles(compilerKey);
            if (pchFiles == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getPrecompiledHeaders"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return pchFiles;
    }
    
    @NotNull
    public OCIncludeMap getIncludeMap() {
        OCIncludeMap includeMap;
        try {
            includeMap = CMakeWorkspace.getInstance(this.myProject).getIncludeMap(this);
            if (includeMap == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getIncludeMap"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return includeMap;
    }
    
    @Nullable
    List<VirtualFile> getPrecompiledHeadersInternal(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getPrecompiledHeadersInternal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CompilerInfoCache.Entry entry = this.myCompilerInfoCache.getCompilerInfoCache(this.myProject, this.myCompilerSettings, ocLanguageKind, virtualFile).getResult();
        try {
            if (entry != null) {
                return (List<VirtualFile>)ContainerUtil.nullize((Collection)entry.precompiledHeaders);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @NotNull
    public HeaderRoots getProjectHeadersRoots() {
        HeaderRoots headerRoots;
        try {
            headerRoots = new HeaderRoots(Collections.emptyList());
            if (headerRoots == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getProjectHeadersRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return headerRoots;
    }
    
    @NotNull
    public HeaderRoots getLibraryHeadersRoots(@NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration) {
        try {
            if (ocResolveRootAndConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerContext", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final HeaderRoots headerRoots = this.getLibraryHeadersRootsResult(ocResolveRootAndConfiguration).getResult();
        HeaderRoots headerRoots2 = null;
        Label_0073: {
            try {
                if (headerRoots != null) {
                    final HeaderRoots empty;
                    headerRoots2 = (empty = headerRoots);
                    break Label_0073;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            HeaderRoots empty;
            headerRoots2 = (empty = HeaderRoots.EMPTY);
            try {
                if (empty == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRoots"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return headerRoots2;
    }
    
    @NotNull
    protected CidrCompilerResult<HeaderRoots> getLibraryHeadersRootsResult(@NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration) {
        try {
            if (ocResolveRootAndConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerContext", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRootsResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final VirtualFile rootFile = ocResolveRootAndConfiguration.getRootFile();
        CidrCompilerResult<HeaderRoots> libraryHeadersRoots = null;
        Label_0107: {
            CidrCompilerResult<HeaderRoots> cidrCompilerResult2 = null;
            Label_0072: {
                try {
                    if (rootFile == null) {
                        break Label_0107;
                    }
                    final CMakeResolveConfiguration cMakeResolveConfiguration = this;
                    final VirtualFile virtualFile = rootFile;
                    final CidrCompilerResult<List<HeadersSearchRoot>> cidrCompilerResult = cMakeResolveConfiguration.a(virtualFile);
                    cidrCompilerResult2 = a(cidrCompilerResult);
                    if (cidrCompilerResult2 == null) {
                        break Label_0072;
                    }
                    return cidrCompilerResult2;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final CMakeResolveConfiguration cMakeResolveConfiguration = this;
                    final VirtualFile virtualFile = rootFile;
                    final CidrCompilerResult<List<HeadersSearchRoot>> cidrCompilerResult = cMakeResolveConfiguration.a(virtualFile);
                    cidrCompilerResult2 = a(cidrCompilerResult);
                    if (cidrCompilerResult2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRootsResult"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return cidrCompilerResult2;
            try {
                libraryHeadersRoots = this.getLibraryHeadersRoots(ocResolveRootAndConfiguration.getKind());
                if (libraryHeadersRoots == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRootsResult"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return libraryHeadersRoots;
    }
    
    @NotNull
    List<HeadersSearchRoot> getLibraryHeadersRoots(@Nullable final VirtualFile virtualFile) {
        final List<HeadersSearchRoot> list = this.a(virtualFile).getResult();
        List<HeadersSearchRoot> emptyList = null;
        Label_0065: {
            List<HeadersSearchRoot> list2 = null;
            Label_0030: {
                try {
                    if (list == null) {
                        break Label_0065;
                    }
                    list2 = list;
                    if (list2 == null) {
                        break Label_0030;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    list2 = list;
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRoots"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return list2;
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRoots"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return emptyList;
    }
    
    @NotNull
    private CidrCompilerResult<List<HeadersSearchRoot>> a(@Nullable final VirtualFile virtualFile) {
        final Pair<OCLanguageKind, File> languageAndFile = this.getLanguageAndFile(virtualFile);
        CidrCompilerResult<List<HeadersSearchRoot>> a;
        try {
            a = this.a((OCLanguageKind)languageAndFile.first, virtualFile);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRootsResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    public CidrCompilerResult<HeaderRoots> getLibraryHeadersRoots(@NotNull final OCLanguageKind ocLanguageKind) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CidrCompilerResult<HeaderRoots> a;
        try {
            a = a(this.a(ocLanguageKind, null));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRoots"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    @NotNull
    private static CidrCompilerResult<HeaderRoots> a(@NotNull final CidrCompilerResult<List<HeadersSearchRoot>> cidrCompilerResult) {
        try {
            if (cidrCompilerResult == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "toHeaderRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<HeadersSearchRoot> list = cidrCompilerResult.getResult();
        CidrCompilerResult<HeaderRoots> error = null;
        Label_0113: {
            CidrCompilerResult<HeaderRoots> cidrCompilerResult2 = null;
            Label_0078: {
                try {
                    if (list == null) {
                        break Label_0113;
                    }
                    final List<HeadersSearchRoot> list2 = list;
                    final HeaderRoots headerRoots = new HeaderRoots(list2);
                    cidrCompilerResult2 = CidrCompilerResult.create(headerRoots);
                    if (cidrCompilerResult2 == null) {
                        break Label_0078;
                    }
                    return cidrCompilerResult2;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final List<HeadersSearchRoot> list2 = list;
                    final HeaderRoots headerRoots = new HeaderRoots(list2);
                    cidrCompilerResult2 = CidrCompilerResult.create(headerRoots);
                    if (cidrCompilerResult2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "toHeaderRoots"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return cidrCompilerResult2;
            try {
                error = CidrCompilerResult.error(cidrCompilerResult.getError());
                if (error == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "toHeaderRoots"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return error;
    }
    
    @NotNull
    private CidrCompilerResult<List<HeadersSearchRoot>> a(@Nullable final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        Label_0056: {
            CidrCompilerResult<List<HeadersSearchRoot>> cidrCompilerResult = null;
            Label_0021: {
                try {
                    if (ocLanguageKind != null) {
                        break Label_0056;
                    }
                    final List<HeadersSearchRoot> list = Collections.emptyList();
                    cidrCompilerResult = CidrCompilerResult.create(list);
                    if (cidrCompilerResult == null) {
                        break Label_0021;
                    }
                    return cidrCompilerResult;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final List<HeadersSearchRoot> list = Collections.emptyList();
                    cidrCompilerResult = CidrCompilerResult.create(list);
                    if (cidrCompilerResult == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRootsResult"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return cidrCompilerResult;
        }
        final CidrCompilerResult<CompilerInfoCache.Entry> compilerInfoCache = this.myCompilerInfoCache.getCompilerInfoCache(this.myProject, this.myCompilerSettings, ocLanguageKind, virtualFile);
        final CompilerInfoCache.Entry entry = compilerInfoCache.getResult();
        CidrCompilerResult<Object> error = null;
        Label_0142: {
            CidrCompilerResult<List<HeadersSearchRoot>> cidrCompilerResult2 = null;
            Label_0107: {
                try {
                    if (entry == null) {
                        break Label_0142;
                    }
                    final CompilerInfoCache.Entry entry2 = entry;
                    final List<HeadersSearchRoot> list2 = entry2.headerSearchPaths;
                    cidrCompilerResult2 = CidrCompilerResult.create(list2);
                    if (cidrCompilerResult2 == null) {
                        break Label_0107;
                    }
                    return cidrCompilerResult2;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final CompilerInfoCache.Entry entry2 = entry;
                    final List<HeadersSearchRoot> list2 = entry2.headerSearchPaths;
                    cidrCompilerResult2 = CidrCompilerResult.create(list2);
                    if (cidrCompilerResult2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRootsResult"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return cidrCompilerResult2;
            try {
                error = CidrCompilerResult.error(compilerInfoCache.getError());
                if (error == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLibraryHeadersRootsResult"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return (CidrCompilerResult<List<HeadersSearchRoot>>)error;
    }
    
    @NotNull
    public Pair<OCLanguageKind, File> getLanguageAndFile(@Nullable final VirtualFile virtualFile) {
        OCLanguageKind languageKind = null;
        File file = null;
        if (virtualFile != null) {
            file = new File(virtualFile.getPath());
            final CMakeFileSettings fileSettings = this.myConfig.getFileSettings(file);
            if (fileSettings != null) {
                languageKind = fileSettings.getLanguageKind();
            }
        }
        if (languageKind == null) {
            for (final OCLanguageKind ocLanguageKind : ContainerUtil.iterateBackward((List)Arrays.asList(CLanguageKind.values()))) {
                if (this.myConfig.getSettings(ocLanguageKind) != null) {
                    languageKind = ocLanguageKind;
                    break;
                }
            }
        }
        Pair create;
        try {
            create = Pair.create((Object)languageKind, (Object)file);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getLanguageAndFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Pair<OCLanguageKind, File>)create;
    }
    
    @Nullable
    public OCLanguageKind getDeclaredLanguageKind(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFile", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getDeclaredLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CMakeFileSettings fileSettings = this.myConfig.getFileSettings(new File(virtualFile.getPath()));
        try {
            if (fileSettings == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return fileSettings.getLanguageKind();
    }
    
    @NotNull
    public OCLanguageKind getMaximumLanguageKind() {
        OCLanguageKind maximumLanguageKind;
        try {
            maximumLanguageKind = OCWorkspaceUtil.getMaximumLanguageKind(this.myConfig.getSupportedLanguages(), this.myProject);
            if (maximumLanguageKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getMaximumLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return maximumLanguageKind;
    }
    
    @NotNull
    public String getPreprocessorDefines(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getPreprocessorDefines"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String preprocessorDefines;
        try {
            preprocessorDefines = this.myCompilerSettings.getPreprocessorDefines(ocLanguageKind, virtualFile);
            if (preprocessorDefines == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getPreprocessorDefines"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return preprocessorDefines;
    }
    
    public Map<OCCompilerFeatures.Type<?>, ?> getCompilerFeatures(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getCompilerFeatures"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myCompilerSettings.getCompilerFeatures(ocLanguageKind, virtualFile);
    }
    
    @NotNull
    public OCCompilerSettings getCompilerSettings() {
        CMakeCompilerSettings myCompilerSettings;
        try {
            myCompilerSettings = this.myCompilerSettings;
            if (myCompilerSettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "getCompilerSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myCompilerSettings;
    }
    
    @Nullable
    public Object getIndexingCluster() {
        return null;
    }
    
    public int compareTo(@NotNull final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration", "compareTo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCWorkspaceUtil.compareConfigurations(this, ocResolveConfiguration);
    }
    
    public String toString() {
        return "CMakeConfiguration: " + this.getDisplayName(false);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private class CMakeCompilerSettings extends OCCompilerSettingsBackedByCompilerCache
    {
        @Nullable
        @Override
        public OCCompilerKind getCompiler(@NotNull final OCLanguageKind ocLanguageKind) {
            try {
                if (ocLanguageKind == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompiler"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            final CMakeConfigurationSettings settings = CMakeResolveConfiguration.this.myConfig.getSettings(ocLanguageKind);
            try {
                if (settings == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            return settings.getCompiler().getCompilerKind();
        }
        
        @Nullable
        @Override
        public File getCompilerExecutable(@NotNull final OCLanguageKind ocLanguageKind) {
            try {
                if (ocLanguageKind == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerExecutable"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            final CMakeConfigurationSettings settings = CMakeResolveConfiguration.this.myConfig.getSettings(ocLanguageKind);
            try {
                if (settings == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            return settings.getCompilerExecutable();
        }
        
        @NotNull
        @Override
        public File getCompilerWorkingDir() {
            File buildWorkingDir;
            try {
                buildWorkingDir = CMakeResolveConfiguration.this.myConfig.getBuildWorkingDir();
                if (buildWorkingDir == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerWorkingDir"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            return buildWorkingDir;
        }
        
        @NotNull
        @Override
        public CidrToolEnvironment getEnvironment() {
            CidrToolEnvironment access$200;
            try {
                access$200 = CMakeResolveConfiguration.this.myEnvironment;
                if (access$200 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getEnvironment"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            return access$200;
        }
        
        @NotNull
        @Override
        public CidrCompilerSwitches getCompilerSwitches(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
            try {
                if (ocLanguageKind == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerSwitches"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            CMakeConfiguration access$100 = null;
            File virtualToIoFile = null;
            Label_0068: {
                try {
                    access$100 = CMakeResolveConfiguration.this.myConfig;
                    if (virtualFile == null) {
                        virtualToIoFile = null;
                        break Label_0068;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                virtualToIoFile = VfsUtilCore.virtualToIoFile(virtualFile);
            }
            final List<String> combinedCompilerFlags = access$100.getCombinedCompilerFlags(ocLanguageKind, virtualToIoFile);
            CidrCompilerSwitches build;
            try {
                build = new CidrSwitchBuilder().addAllRaw(combinedCompilerFlags).build();
                if (build == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerSwitches"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            return build;
        }
        
        @NotNull
        @Override
        public CidrCompilerResult<CompilerInfoCache.Entry> getCompilerInfo(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
            try {
                if (ocLanguageKind == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerInfo"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            CidrCompilerResult<CompilerInfoCache.Entry> compilerInfoCache;
            try {
                compilerInfoCache = CMakeResolveConfiguration.this.myCompilerInfoCache.getCompilerInfoCache(CMakeResolveConfiguration.this.myProject, this, ocLanguageKind, virtualFile);
                if (compilerInfoCache == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerInfo"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            return compilerInfoCache;
        }
        
        private static IllegalArgumentException c(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
