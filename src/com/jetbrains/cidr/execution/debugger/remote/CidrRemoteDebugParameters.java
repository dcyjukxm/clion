// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.remote;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import java.util.Iterator;
import kotlin.text.StringsKt;
import java.util.ArrayList;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J7\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011¨\u0006#" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;", "", "remoteCommand", "", "symbolFile", "sysroot", "pathMappings", "", "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemotePathMapping;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getPathMappings", "()Ljava/util/List;", "setPathMappings", "(Ljava/util/List;)V", "getRemoteCommand", "()Ljava/lang/String;", "setRemoteCommand", "(Ljava/lang/String;)V", "getSymbolFile", "setSymbolFile", "getSysroot", "setSysroot", "component1", "component2", "component3", "component4", "copy", "driverPathMapping", "Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$PathMapping;", "equals", "", "other", "hashCode", "", "toString", "cidr-debugger" })
public final class CidrRemoteDebugParameters
{
    @NotNull
    private String remoteCommand;
    @NotNull
    private String symbolFile;
    @NotNull
    private String sysroot;
    @NotNull
    private List<CidrRemotePathMapping> pathMappings;
    
    @NotNull
    public final List<DebuggerDriver.PathMapping> driverPathMapping() {
        final List<CidrRemotePathMapping> list = this.pathMappings;
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (final CidrRemotePathMapping cidrRemotePathMapping : list) {
            final String s = cidrRemotePathMapping.getRemote();
            DebuggerDriver.PathMapping pathMapping2 = null;
            DebuggerDriver.PathMapping pathMapping = null;
            Label_0146: {
                if (s == null || StringsKt.isBlank((CharSequence)s)) {
                    final String s2 = cidrRemotePathMapping.getLocal();
                    if (s2 == null || StringsKt.isBlank((CharSequence)s2)) {
                        pathMapping = (pathMapping2 = null);
                        break Label_0146;
                    }
                }
                pathMapping = (pathMapping2 = new DebuggerDriver.PathMapping(cidrRemotePathMapping.getRemote(), cidrRemotePathMapping.getLocal()));
            }
            if (pathMapping2 != null) {
                list2.add(pathMapping);
            }
        }
        return (List<DebuggerDriver.PathMapping>)list2;
    }
    
    @NotNull
    public final String getRemoteCommand() {
        return this.remoteCommand;
    }
    
    public final void setRemoteCommand(@NotNull final String remoteCommand) {
        Intrinsics.checkParameterIsNotNull((Object)remoteCommand, "<set-?>");
        this.remoteCommand = remoteCommand;
    }
    
    @NotNull
    public final String getSymbolFile() {
        return this.symbolFile;
    }
    
    public final void setSymbolFile(@NotNull final String symbolFile) {
        Intrinsics.checkParameterIsNotNull((Object)symbolFile, "<set-?>");
        this.symbolFile = symbolFile;
    }
    
    @NotNull
    public final String getSysroot() {
        return this.sysroot;
    }
    
    public final void setSysroot(@NotNull final String sysroot) {
        Intrinsics.checkParameterIsNotNull((Object)sysroot, "<set-?>");
        this.sysroot = sysroot;
    }
    
    @NotNull
    public final List<CidrRemotePathMapping> getPathMappings() {
        return this.pathMappings;
    }
    
    public final void setPathMappings(@NotNull final List<CidrRemotePathMapping> pathMappings) {
        Intrinsics.checkParameterIsNotNull((Object)pathMappings, "<set-?>");
        this.pathMappings = pathMappings;
    }
    
    public CidrRemoteDebugParameters(@NotNull final String remoteCommand, @NotNull final String symbolFile, @NotNull final String sysroot, @NotNull final List<CidrRemotePathMapping> pathMappings) {
        Intrinsics.checkParameterIsNotNull((Object)remoteCommand, "remoteCommand");
        Intrinsics.checkParameterIsNotNull((Object)symbolFile, "symbolFile");
        Intrinsics.checkParameterIsNotNull((Object)sysroot, "sysroot");
        Intrinsics.checkParameterIsNotNull((Object)pathMappings, "pathMappings");
        this.remoteCommand = remoteCommand;
        this.symbolFile = symbolFile;
        this.sysroot = sysroot;
        this.pathMappings = pathMappings;
    }
    
    public CidrRemoteDebugParameters() {
        this(null, null, null, null, 15, null);
    }
    
    @NotNull
    public final String component1() {
        return this.remoteCommand;
    }
    
    @NotNull
    public final String component2() {
        return this.symbolFile;
    }
    
    @NotNull
    public final String component3() {
        return this.sysroot;
    }
    
    @NotNull
    public final List<CidrRemotePathMapping> component4() {
        return this.pathMappings;
    }
    
    @NotNull
    public final CidrRemoteDebugParameters copy(@NotNull final String s, @NotNull final String s2, @NotNull final String s3, @NotNull final List<CidrRemotePathMapping> list) {
        Intrinsics.checkParameterIsNotNull((Object)s, "remoteCommand");
        Intrinsics.checkParameterIsNotNull((Object)s2, "symbolFile");
        Intrinsics.checkParameterIsNotNull((Object)s3, "sysroot");
        Intrinsics.checkParameterIsNotNull((Object)list, "pathMappings");
        return new CidrRemoteDebugParameters(s, s2, s3, list);
    }
    
    @Override
    public String toString() {
        return "CidrRemoteDebugParameters(remoteCommand=" + this.remoteCommand + ", symbolFile=" + this.symbolFile + ", sysroot=" + this.sysroot + ", pathMappings=" + this.pathMappings + ")";
    }
    
    @Override
    public int hashCode() {
        final String remoteCommand = this.remoteCommand;
        final int n = ((remoteCommand != null) ? remoteCommand.hashCode() : 0) * 31;
        final String symbolFile = this.symbolFile;
        final int n2 = (n + ((symbolFile != null) ? symbolFile.hashCode() : 0)) * 31;
        final String sysroot = this.sysroot;
        final int n3 = (n2 + ((sysroot != null) ? sysroot.hashCode() : 0)) * 31;
        final List<CidrRemotePathMapping> pathMappings = this.pathMappings;
        return n3 + ((pathMappings != null) ? pathMappings.hashCode() : 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof CidrRemoteDebugParameters) {
                final CidrRemoteDebugParameters cidrRemoteDebugParameters = (CidrRemoteDebugParameters)o;
                if (Intrinsics.areEqual((Object)this.remoteCommand, (Object)cidrRemoteDebugParameters.remoteCommand) && Intrinsics.areEqual((Object)this.symbolFile, (Object)cidrRemoteDebugParameters.symbolFile) && Intrinsics.areEqual((Object)this.sysroot, (Object)cidrRemoteDebugParameters.sysroot) && Intrinsics.areEqual((Object)this.pathMappings, (Object)cidrRemoteDebugParameters.pathMappings)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
