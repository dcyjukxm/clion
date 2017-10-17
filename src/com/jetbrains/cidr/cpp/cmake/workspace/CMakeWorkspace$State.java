// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.util.Function;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModelConfigurationData;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.CPPLog;
import java.util.Collections;
import com.jetbrains.cidr.toolchains.CompilerInfoCache;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import java.util.Map;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModel;
import org.jetbrains.annotations.Nullable;
import java.io.File;

private static class State
{
    @Nullable
    final File projectDir;
    final long settingsHash;
    @Nullable
    final CMakeModel model;
    @NotNull
    final Map<Integer, CMakeEnvironment> configEnvironments;
    @NotNull
    final Map<File, FileStamp> cmakeFilesWithStamps;
    @NotNull
    final List<File> sourceFiles;
    @NotNull
    final List<File> projectResourceFiles;
    @NotNull
    final LibraryRootsAndFiles libraryRootsAndFiles;
    @NotNull
    final Map<VirtualFile, List<CMakeResolveConfiguration>> sourceFilesToConfigurations;
    @NotNull
    final List<CMakeResolveConfiguration> configurations;
    @NotNull
    final CompilerInfoCache compilerInfoCache;
    
    public State(@Nullable final File file, final long n, @NotNull final Map<File, FileStamp> map) {
        if (map == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeFilesWithStamps", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        this(file, n, null, Collections.emptyMap(), map, Collections.emptyList(), Collections.emptyList(), LibraryRootsAndFiles.empty(), Collections.emptyMap(), Collections.emptyList(), new CompilerInfoCache());
    }
    
    private State(@Nullable final File projectDir, final long settingsHash, @Nullable final CMakeModel model, @NotNull final Map<Integer, CMakeEnvironment> map, @NotNull final Map<File, FileStamp> map2, @NotNull final List<File> list, @NotNull final List<File> list2, @NotNull final LibraryRootsAndFiles libraryRootsAndFiles, @NotNull final Map<VirtualFile, List<CMakeResolveConfiguration>> map3, @NotNull final List<CMakeResolveConfiguration> list3, @NotNull final CompilerInfoCache compilerInfoCache) {
        if (map == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configEnvironments", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        if (map2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeFilesWithStamps", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectResourceFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        if (libraryRootsAndFiles == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "libraryRootsAndFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        if (map3 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFilesToConfigurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        if (list3 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        if (compilerInfoCache == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerInfoCache", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
        }
        this.projectDir = projectDir;
        this.settingsHash = settingsHash;
        this.model = model;
        this.configEnvironments = Collections.unmodifiableMap((Map<? extends Integer, ? extends CMakeEnvironment>)map);
        final Logger log = CPPLog.LOG;
        while (true) {
            if (projectDir != null) {
                boolean b = false;
                Label_0393: {
                    try {
                        if (map2.containsKey(new File(projectDir, "CMakeLists.txt"))) {
                            b = true;
                            break Label_0393;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    b = false;
                }
                log.assertTrue(b);
                this.cmakeFilesWithStamps = Collections.unmodifiableMap((Map<? extends File, ? extends FileStamp>)map2);
                this.sourceFiles = Collections.unmodifiableList((List<? extends File>)list);
                this.projectResourceFiles = Collections.unmodifiableList((List<? extends File>)list2);
                this.libraryRootsAndFiles = libraryRootsAndFiles;
                this.sourceFilesToConfigurations = Collections.unmodifiableMap((Map<? extends VirtualFile, ? extends List<CMakeResolveConfiguration>>)map3);
                this.configurations = Collections.unmodifiableList((List<? extends CMakeResolveConfiguration>)list3);
                this.compilerInfoCache = compilerInfoCache;
                return;
            }
            continue;
        }
    }
    
    @NotNull
    private List<File> a() {
        List<File> emptyList = null;
        Label_0068: {
            List list2 = null;
            Label_0033: {
                try {
                    if (this.model == null) {
                        break Label_0068;
                    }
                    final State state = this;
                    final CMakeModel cMakeModel = state.model;
                    final List<CMakeModelConfigurationData> list = cMakeModel.getConfigurationData();
                    final Function function = cMakeModelConfigurationData -> cMakeModelConfigurationData.getGenerationDir();
                    list2 = ContainerUtil.map((Collection)list, function);
                    if (list2 == null) {
                        break Label_0033;
                    }
                    return (List<File>)list2;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final State state = this;
                    final CMakeModel cMakeModel = state.model;
                    final List<CMakeModelConfigurationData> list = cMakeModel.getConfigurationData();
                    final Function function = cMakeModelConfigurationData -> cMakeModelConfigurationData.getGenerationDir();
                    list2 = ContainerUtil.map((Collection)list, function);
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "getAllGenerationDirs"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return (List<File>)list2;
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "getAllGenerationDirs"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return emptyList;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
