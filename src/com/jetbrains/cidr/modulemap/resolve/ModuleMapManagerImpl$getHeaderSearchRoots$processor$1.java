// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004" }, d2 = { "<anonymous>", "", "it", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "invoke" })
static final class ModuleMapManagerImpl$getHeaderSearchRoots$processor$1 extends Lambda implements Function1<HeadersSearchRoot, Unit> {
    public final void invoke(@NotNull final HeadersSearchRoot headersSearchRoot) {
        Intrinsics.checkParameterIsNotNull((Object)headersSearchRoot, "it");
        this.$headerRoots.addAll(headersSearchRoot.getPossibleModuleMapLocations());
    }
}