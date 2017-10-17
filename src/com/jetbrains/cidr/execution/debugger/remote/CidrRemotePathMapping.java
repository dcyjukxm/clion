// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.remote;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemotePathMapping;", "", "remote", "", "local", "(Ljava/lang/String;Ljava/lang/String;)V", "getLocal", "()Ljava/lang/String;", "setLocal", "(Ljava/lang/String;)V", "getRemote", "setRemote", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "cidr-debugger" })
public final class CidrRemotePathMapping
{
    @NotNull
    private String remote;
    @NotNull
    private String local;
    
    @NotNull
    public final String getRemote() {
        return this.remote;
    }
    
    public final void setRemote(@NotNull final String remote) {
        Intrinsics.checkParameterIsNotNull((Object)remote, "<set-?>");
        this.remote = remote;
    }
    
    @NotNull
    public final String getLocal() {
        return this.local;
    }
    
    public final void setLocal(@NotNull final String local) {
        Intrinsics.checkParameterIsNotNull((Object)local, "<set-?>");
        this.local = local;
    }
    
    public CidrRemotePathMapping(@NotNull final String remote, @NotNull final String local) {
        Intrinsics.checkParameterIsNotNull((Object)remote, "remote");
        Intrinsics.checkParameterIsNotNull((Object)local, "local");
        this.remote = remote;
        this.local = local;
    }
    
    @NotNull
    public final String component1() {
        return this.remote;
    }
    
    @NotNull
    public final String component2() {
        return this.local;
    }
    
    @NotNull
    public final CidrRemotePathMapping copy(@NotNull final String s, @NotNull final String s2) {
        Intrinsics.checkParameterIsNotNull((Object)s, "remote");
        Intrinsics.checkParameterIsNotNull((Object)s2, "local");
        return new CidrRemotePathMapping(s, s2);
    }
    
    @Override
    public String toString() {
        return "CidrRemotePathMapping(remote=" + this.remote + ", local=" + this.local + ")";
    }
    
    @Override
    public int hashCode() {
        final String remote = this.remote;
        final int n = ((remote != null) ? remote.hashCode() : 0) * 31;
        final String local = this.local;
        return n + ((local != null) ? local.hashCode() : 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof CidrRemotePathMapping) {
                final CidrRemotePathMapping cidrRemotePathMapping = (CidrRemotePathMapping)o;
                if (Intrinsics.areEqual((Object)this.remote, (Object)cidrRemotePathMapping.remote) && Intrinsics.areEqual((Object)this.local, (Object)cidrRemotePathMapping.local)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
