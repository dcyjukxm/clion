// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Collections;
import kotlin.jvm.internal.Intrinsics;
import java.io.File;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchPath;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Map;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B_\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0002\b\u00030\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001f\u0010\u0004\u001a\u0010\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015¨\u0006\u0019" }, d2 = { "Lcom/jetbrains/cidr/toolchains/CompilerInfo;", "", "defines", "", "features", "", "Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Type;", "headersSearchPaths", "", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPath;", "precompiledHeaders", "Ljava/io/File;", "includeMap", "Lcom/jetbrains/cidr/toolchains/PrecompiledInclude;", "warnings", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getDefines", "()Ljava/lang/String;", "getFeatures", "()Ljava/util/Map;", "getHeadersSearchPaths", "()Ljava/util/List;", "getIncludeMap", "getPrecompiledHeaders", "getWarnings", "cidr-common" })
public final class CompilerInfo
{
    @NotNull
    private final Map<OCCompilerFeatures.Type<?>, ?> features;
    @NotNull
    private final List<HeadersSearchPath> headersSearchPaths;
    @NotNull
    private final List<File> precompiledHeaders;
    @NotNull
    private final List<String> warnings;
    @NotNull
    private final List<PrecompiledInclude> includeMap;
    @NotNull
    private final String defines;
    
    @NotNull
    public final Map<OCCompilerFeatures.Type<?>, ?> getFeatures() {
        return this.features;
    }
    
    @NotNull
    public final List<HeadersSearchPath> getHeadersSearchPaths() {
        return this.headersSearchPaths;
    }
    
    @NotNull
    public final List<File> getPrecompiledHeaders() {
        return this.precompiledHeaders;
    }
    
    @NotNull
    public final List<String> getWarnings() {
        return this.warnings;
    }
    
    @NotNull
    public final List<PrecompiledInclude> getIncludeMap() {
        return this.includeMap;
    }
    
    @NotNull
    public final String getDefines() {
        return this.defines;
    }
    
    public CompilerInfo(@NotNull final String defines, @NotNull final Map<OCCompilerFeatures.Type<?>, ?> map, @NotNull final List<? extends HeadersSearchPath> list, @NotNull final List<? extends File> list2, @NotNull final List<PrecompiledInclude> list3, @NotNull final List<String> list4) {
        Intrinsics.checkParameterIsNotNull((Object)defines, "defines");
        Intrinsics.checkParameterIsNotNull((Object)map, "features");
        Intrinsics.checkParameterIsNotNull((Object)list, "headersSearchPaths");
        Intrinsics.checkParameterIsNotNull((Object)list2, "precompiledHeaders");
        Intrinsics.checkParameterIsNotNull((Object)list3, "includeMap");
        Intrinsics.checkParameterIsNotNull((Object)list4, "warnings");
        this.defines = defines;
        final Map<OCCompilerFeatures.Type<?>, ?> unmodifiableMap = Collections.unmodifiableMap((Map<? extends OCCompilerFeatures.Type<?>, ?>)map);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableMap, "unmodifiableMap(features)");
        this.features = unmodifiableMap;
        final List<HeadersSearchPath> unmodifiableList = Collections.unmodifiableList(list);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableList, "unmodifiableList(headersSearchPaths)");
        this.headersSearchPaths = unmodifiableList;
        final List<File> unmodifiableList2 = Collections.unmodifiableList(list2);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableList2, "unmodifiableList(precompiledHeaders)");
        this.precompiledHeaders = unmodifiableList2;
        final List<String> unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list4);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableList3, "unmodifiableList(warnings)");
        this.warnings = unmodifiableList3;
        final List<PrecompiledInclude> unmodifiableList4 = Collections.unmodifiableList((List<? extends PrecompiledInclude>)list3);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableList4, "unmodifiableList(includeMap)");
        this.includeMap = unmodifiableList4;
    }
    
    public CompilerInfo(@NotNull final String s, @NotNull final Map<OCCompilerFeatures.Type<?>, ?> map, @NotNull final List<? extends HeadersSearchPath> list, @NotNull final List<? extends File> list2, @NotNull final List<String> list3) {
        this(s, map, list, list2, null, list3, 16, null);
    }
}
