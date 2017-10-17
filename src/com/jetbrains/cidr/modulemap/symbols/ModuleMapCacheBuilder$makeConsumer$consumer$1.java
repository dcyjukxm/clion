// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import java.util.concurrent.atomic.AtomicInteger;
import com.intellij.openapi.progress.ProgressIndicator;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.intellij.util.Consumer;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005" }, d2 = { "<anonymous>", "", "it", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "kotlin.jvm.PlatformType", "consume" })
static final class ModuleMapCacheBuilder$makeConsumer$consumer$1<T> implements Consumer<HeadersSearchRoot> {
    public final void consume(final HeadersSearchRoot headersSearchRoot) {
        headersSearchRoot.collectDeclaredModules();
        this.$indicator.setFraction(this.$processedRootsCount.incrementAndGet() / this.$allRootsCount.get());
    }
}