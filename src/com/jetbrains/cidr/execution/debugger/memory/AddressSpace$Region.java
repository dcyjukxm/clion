// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.memory;

import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "", "isAllocated", "", "()Z", "range", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "getRange", "()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "cidr-debugger" })
public interface Region
{
    @NotNull
    AddressRange getRange();
    
    boolean isAllocated();
}
