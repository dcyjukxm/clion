// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions;

import kotlin.comparisons.ComparisonsKt;
import kotlin.Pair;
import com.intellij.openapi.util.TextRange;
import kotlin.Metadata;
import java.util.Comparator;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007" }, d2 = { "<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I" })
public static final class OCRemoveBodiesAndComments$actionPerformed$$inlined$sortBy$1<T> implements Comparator<T> {
    @Override
    public final int compare(final T t, final T t2) {
        return ComparisonsKt.compareValues((Comparable)((TextRange)((Pair)t).getFirst()).getStartOffset(), (Comparable)((TextRange)((Pair)t2).getFirst()).getStartOffset());
    }
}