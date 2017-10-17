// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010'\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012>\u0010\u0002\u001a:\u0012,\u0012*\u0012\u000e\b\u0001\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0014\u0012\u000e\b\u0001\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0003H\n¢\u0006\u0002\b\u0007" }, d2 = { "<anonymous>", "", "it", "", "Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Type;", "", "kotlin.jvm.PlatformType", "invoke" })
static final class ShowCompilerInfoForFile$actionPerformed$2 extends Lambda implements Function1<Map.Entry<OCCompilerFeatures.Type<?>, ?>, String> {
    public static final ShowCompilerInfoForFile$actionPerformed$2 INSTANCE;
    
    @NotNull
    public final String invoke(@NotNull final Map.Entry<OCCompilerFeatures.Type<?>, ?> entry) {
        Intrinsics.checkParameterIsNotNull((Object)entry, "it");
        return new StringBuilder().append('\t').append(entry.getKey()).append('=').append(entry.getValue()).toString();
    }
    
    static {
        ShowCompilerInfoForFile$actionPerformed$2.INSTANCE = new ShowCompilerInfoForFile$actionPerformed$2();
    }
}