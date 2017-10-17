// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.project.Project;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.Intrinsics;
import java.util.Set;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import com.intellij.util.Processor;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005" }, d2 = { "<anonymous>", "", "it", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "kotlin.jvm.PlatformType", "process" })
static final class ModuleMapWalker$doProcess$actualProcessor$1<T> implements Processor<ModuleMapModuleSymbol> {
    public final boolean process(final ModuleMapModuleSymbol moduleMapModuleSymbol) {
        final ModuleMapWalker this$0 = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull((Object)moduleMapModuleSymbol, "it");
        return this$0.a(moduleMapModuleSymbol, this.$configuration, this.$processedModules);
    }
}