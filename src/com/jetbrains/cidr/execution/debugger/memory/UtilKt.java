// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.memory;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u0000\u0010\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u0002H\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00040\u0003H\u0086\b¢\u0006\u0002\u0010\u0005¨\u0006\u0006" }, d2 = { "check", "T", "block", "Lkotlin/Function1;", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "cidr-debugger" })
public final class UtilKt
{
    public static final <T> T check(final T t, @NotNull final Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull((Object)function1, "block");
        function1.invoke((Object)t);
        return t;
    }
}
