// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.memory;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import java.util.EventListener;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0001\u0010\u0001 \u0000*\u00020\u00022\u00020\u0003J#\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\bH&¢\u0006\u0002\u0010\t¨\u0006\n" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Listener;", "R", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "Ljava/util/EventListener;", "regionSplit", "", "oldRegion", "newRegions", "", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;Ljava/util/List;)V", "cidr-debugger" })
public interface Listener<R extends Region> extends EventListener
{
    void regionSplit(@NotNull final R p0, @NotNull final List<? extends R> p1);
}
