// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0015" }, d2 = { "Lcom/jetbrains/cidr/toolchains/PrecompiledInclude;", "", "pchPath", "", "headerName", "isPCHCreation", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getHeaderName", "()Ljava/lang/String;", "()Z", "getPchPath", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "cidr-common" })
public final class PrecompiledInclude
{
    @NotNull
    private final String pchPath;
    @NotNull
    private final String headerName;
    private final boolean isPCHCreation;
    
    @NotNull
    public final String getPchPath() {
        return this.pchPath;
    }
    
    @NotNull
    public final String getHeaderName() {
        return this.headerName;
    }
    
    public final boolean isPCHCreation() {
        return this.isPCHCreation;
    }
    
    public PrecompiledInclude(@NotNull final String pchPath, @NotNull final String headerName, final boolean isPCHCreation) {
        Intrinsics.checkParameterIsNotNull((Object)pchPath, "pchPath");
        Intrinsics.checkParameterIsNotNull((Object)headerName, "headerName");
        this.pchPath = pchPath;
        this.headerName = headerName;
        this.isPCHCreation = isPCHCreation;
    }
    
    @NotNull
    public final String component1() {
        return this.pchPath;
    }
    
    @NotNull
    public final String component2() {
        return this.headerName;
    }
    
    public final boolean component3() {
        return this.isPCHCreation;
    }
    
    @NotNull
    public final PrecompiledInclude copy(@NotNull final String s, @NotNull final String s2, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)s, "pchPath");
        Intrinsics.checkParameterIsNotNull((Object)s2, "headerName");
        return new PrecompiledInclude(s, s2, b);
    }
    
    @Override
    public String toString() {
        return "PrecompiledInclude(pchPath=" + this.pchPath + ", headerName=" + this.headerName + ", isPCHCreation=" + this.isPCHCreation + ")";
    }
    
    @Override
    public int hashCode() {
        final String pchPath = this.pchPath;
        final int n = ((pchPath != null) ? pchPath.hashCode() : 0) * 31;
        final String headerName = this.headerName;
        final int n2 = (n + ((headerName != null) ? headerName.hashCode() : 0)) * 31;
        int isPCHCreation;
        if ((isPCHCreation = (this.isPCHCreation ? 1 : 0)) != 0) {
            isPCHCreation = 1;
        }
        return n2 + isPCHCreation;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof PrecompiledInclude) {
                final PrecompiledInclude precompiledInclude = (PrecompiledInclude)o;
                if (Intrinsics.areEqual((Object)this.pchPath, (Object)precompiledInclude.pchPath) && Intrinsics.areEqual((Object)this.headerName, (Object)precompiledInclude.headerName) && this.isPCHCreation == precompiledInclude.isPCHCreation) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
