// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005" }, d2 = { "<anonymous>", "", "it", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "kotlin.jvm.PlatformType", "invoke" })
static final class ShowCompilerInfoForFile$actionPerformed$4 extends Lambda implements Function1<HeadersSearchRoot, String> {
    public static final ShowCompilerInfoForFile$actionPerformed$4 INSTANCE;
    
    @NotNull
    public final String invoke(final HeadersSearchRoot headersSearchRoot) {
        return '\t' + headersSearchRoot.getClass().getSimpleName() + "" + (headersSearchRoot.isUserHeaders() ? "(user)" : "") + ": " + headersSearchRoot.getVirtualFile().getPath();
    }
    
    static {
        ShowCompilerInfoForFile$actionPerformed$4.INSTANCE = new ShowCompilerInfoForFile$actionPerformed$4();
    }
}