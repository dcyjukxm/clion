// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import java.util.Collections;
import java.util.Set;
import java.util.Map;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.List;

private static class LibraryRootsAndFiles
{
    @NotNull
    final List<File> systemHeaderRoots;
    @NotNull
    final List<File> userHeaderRoots;
    @NotNull
    final List<File> headerExcludeRoots;
    @NotNull
    final List<VirtualFile> filesToIndex;
    @NotNull
    final Map<String, Set<VirtualFile>> config2pchFiles;
    @NotNull
    final Map<String, List<VirtualFile>> fileAndLang2pchFiles;
    @NotNull
    final Map<String, PrecompiledIncludeMap> config2includeMap;
    
    @NotNull
    public static LibraryRootsAndFiles empty() {
        LibraryRootsAndFiles libraryRootsAndFiles;
        try {
            libraryRootsAndFiles = new LibraryRootsAndFiles(Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyMap(), Collections.emptyMap(), Collections.emptyMap());
            if (libraryRootsAndFiles == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "empty"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return libraryRootsAndFiles;
    }
    
    public LibraryRootsAndFiles(@NotNull final List<File> list, @NotNull final List<File> list2, @NotNull final List<File> list3, @NotNull final List<VirtualFile> list4, @NotNull final Map<String, Set<VirtualFile>> map, @NotNull final Map<String, List<VirtualFile>> map2, @NotNull final Map<String, PrecompiledIncludeMap> map3) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "systemHeaderRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "userHeaderRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
        }
        if (list3 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerExcludeRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
        }
        if (list4 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filesToIndex", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
        }
        if (map == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config2pchFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
        }
        if (map2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileAndLang2pchFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
        }
        if (map3 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config2includeMap", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
        }
        this.systemHeaderRoots = Collections.unmodifiableList((List<? extends File>)list);
        this.userHeaderRoots = Collections.unmodifiableList((List<? extends File>)list2);
        this.headerExcludeRoots = Collections.unmodifiableList((List<? extends File>)list3);
        this.filesToIndex = Collections.unmodifiableList((List<? extends VirtualFile>)list4);
        this.config2pchFiles = Collections.unmodifiableMap((Map<? extends String, ? extends Set<VirtualFile>>)map);
        this.fileAndLang2pchFiles = Collections.unmodifiableMap((Map<? extends String, ? extends List<VirtualFile>>)map2);
        this.config2includeMap = Collections.unmodifiableMap((Map<? extends String, ? extends PrecompiledIncludeMap>)map3);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
