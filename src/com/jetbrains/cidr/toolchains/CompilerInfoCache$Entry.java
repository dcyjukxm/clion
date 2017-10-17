// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import java.util.Collections;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.List;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public static class Entry
{
    public static final Entry EMPTY;
    @NotNull
    public final String defines;
    @NotNull
    public final Map<OCCompilerFeatures.Type<?>, ?> features;
    @NotNull
    public final List<HeadersSearchRoot> headerSearchPaths;
    @NotNull
    public final List<VirtualFile> precompiledHeaders;
    @NotNull
    public final List<PrecompiledInclude> includeMap;
    @NotNull
    public final List<String> warnings;
    
    private Entry(@NotNull final String defines, @NotNull final Map<OCCompilerFeatures.Type<?>, ?> features, @NotNull final List<HeadersSearchRoot> headerSearchPaths, @NotNull final List<VirtualFile> precompiledHeaders, @NotNull final List<PrecompiledInclude> includeMap, @NotNull final List<String> warnings) {
        if (defines == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defines", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
        }
        if (features == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "features", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
        }
        if (headerSearchPaths == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerSearchPaths", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
        }
        if (precompiledHeaders == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "precompiledHeaders", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
        }
        if (includeMap == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includeMap", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
        }
        if (warnings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "warnings", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
        }
        this.defines = defines;
        this.features = features;
        this.headerSearchPaths = headerSearchPaths;
        this.precompiledHeaders = precompiledHeaders;
        this.includeMap = includeMap;
        this.warnings = warnings;
    }
    
    static {
        EMPTY = new Entry("", Collections.emptyMap(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }
}
