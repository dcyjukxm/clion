// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchPath;
import java.util.List;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0006H\u00d6\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0014" }, d2 = { "Lcom/jetbrains/cidr/toolchains/MSVCCompiler$HeaderSearchPathsAndDefines;", "", "headersSearchPaths", "", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPath;", "defines", "", "(Ljava/util/List;Ljava/util/List;)V", "getDefines", "()Ljava/util/List;", "getHeadersSearchPaths", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "cidr-common" })
public static final class HeaderSearchPathsAndDefines
{
    @NotNull
    private final List<HeadersSearchPath> headersSearchPaths;
    @NotNull
    private final List<String> defines;
    
    @NotNull
    public final List<HeadersSearchPath> getHeadersSearchPaths() {
        return this.headersSearchPaths;
    }
    
    @NotNull
    public final List<String> getDefines() {
        return this.defines;
    }
    
    public HeaderSearchPathsAndDefines(@NotNull final List<? extends HeadersSearchPath> headersSearchPaths, @NotNull final List<String> defines) {
        Intrinsics.checkParameterIsNotNull((Object)headersSearchPaths, "headersSearchPaths");
        Intrinsics.checkParameterIsNotNull((Object)defines, "defines");
        this.headersSearchPaths = (List<HeadersSearchPath>)headersSearchPaths;
        this.defines = defines;
    }
    
    @NotNull
    public final List<HeadersSearchPath> component1() {
        return this.headersSearchPaths;
    }
    
    @NotNull
    public final List<String> component2() {
        return this.defines;
    }
    
    @NotNull
    public final HeaderSearchPathsAndDefines copy(@NotNull final List<? extends HeadersSearchPath> list, @NotNull final List<String> list2) {
        Intrinsics.checkParameterIsNotNull((Object)list, "headersSearchPaths");
        Intrinsics.checkParameterIsNotNull((Object)list2, "defines");
        return new HeaderSearchPathsAndDefines(list, list2);
    }
    
    @Override
    public String toString() {
        return "HeaderSearchPathsAndDefines(headersSearchPaths=" + this.headersSearchPaths + ", defines=" + this.defines + ")";
    }
    
    @Override
    public int hashCode() {
        final List<HeadersSearchPath> headersSearchPaths = this.headersSearchPaths;
        final int n = ((headersSearchPaths != null) ? headersSearchPaths.hashCode() : 0) * 31;
        final List<String> defines = this.defines;
        return n + ((defines != null) ? defines.hashCode() : 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof HeaderSearchPathsAndDefines) {
                final HeaderSearchPathsAndDefines headerSearchPathsAndDefines = (HeaderSearchPathsAndDefines)o;
                if (Intrinsics.areEqual((Object)this.headersSearchPaths, (Object)headerSearchPathsAndDefines.headersSearchPaths) && Intrinsics.areEqual((Object)this.defines, (Object)headerSearchPathsAndDefines.defines)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
