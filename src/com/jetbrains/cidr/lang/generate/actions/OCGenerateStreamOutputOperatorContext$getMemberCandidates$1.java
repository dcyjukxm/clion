// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.ArrayList;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.Processor;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005" }, d2 = { "<anonymous>", "", "field", "Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;", "kotlin.jvm.PlatformType", "process" })
static final class OCGenerateStreamOutputOperatorContext$getMemberCandidates$1<T> implements Processor<OCDeclaratorSymbol> {
    public final boolean process(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        if (Intrinsics.areEqual((Object)ocDeclaratorSymbol.getKind(), (Object)OCSymbolKind.STRUCT_FIELD) && !ocDeclaratorSymbol.isFriendOrStatic() && !OCCodeInsightUtil.isUnnamed(ocDeclaratorSymbol)) {
            this.$list.add(ocDeclaratorSymbol);
        }
        return true;
    }
}