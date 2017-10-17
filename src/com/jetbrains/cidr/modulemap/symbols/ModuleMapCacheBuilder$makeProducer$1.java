// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import org.jetbrains.annotations.Nullable;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import java.util.ArrayList;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.intellij.util.Producer;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002" }, d2 = { "<anonymous>", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "produce" })
static final class ModuleMapCacheBuilder$makeProducer$1<T> implements Producer<HeadersSearchRoot> {
    @Nullable
    public final HeadersSearchRoot produce() {
        final ArrayList $pool = this.$pool;
        Intrinsics.checkExpressionValueIsNotNull((Object)$pool, "pool");
        synchronized ($pool) {
            if (!this.$pool.isEmpty()) {
                return this.$pool.remove(this.$pool.size() - 1);
            }
            final Unit instance = Unit.INSTANCE;
        }
        return null;
    }
}