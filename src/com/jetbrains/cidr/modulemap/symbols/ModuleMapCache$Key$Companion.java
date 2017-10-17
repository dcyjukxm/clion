// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.jetbrains.cidr.lang.workspace.headerRoots.IncludedHeadersRoot;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.headerRoots.RealFramework;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0007¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCache$Key$Companion;", "", "()V", "create", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCache$Key;", "root", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot;", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/RealFramework;", "cidr-lang" })
public static final class Companion
{
    @JvmStatic
    @NotNull
    public final Key create(@NotNull final RealFramework realFramework) {
        Intrinsics.checkParameterIsNotNull((Object)realFramework, "root");
        return new Key(realFramework, null, null, null);
    }
    
    @JvmStatic
    @NotNull
    public final Key create(@NotNull final IncludedHeadersRoot includedHeadersRoot) {
        Intrinsics.checkParameterIsNotNull((Object)includedHeadersRoot, "root");
        return new Key(null, includedHeadersRoot, null, null);
    }
    
    @JvmStatic
    @NotNull
    public final Key create(@NotNull final HeadersSearchRoot headersSearchRoot) {
        Intrinsics.checkParameterIsNotNull((Object)headersSearchRoot, "root");
        if (headersSearchRoot instanceof RealFramework) {
            return this.create((RealFramework)headersSearchRoot);
        }
        if (headersSearchRoot instanceof IncludedHeadersRoot) {
            return this.create((IncludedHeadersRoot)headersSearchRoot);
        }
        return new Key(null, null, headersSearchRoot, null);
    }
}
