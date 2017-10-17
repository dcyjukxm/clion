// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.memory;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u000f" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Companion;", "", "()V", "DEFAULT_RANGE_BYTES_AFTER", "", "getDEFAULT_RANGE_BYTES_AFTER", "()J", "DEFAULT_RANGE_BYTES_GROWTH", "getDEFAULT_RANGE_BYTES_GROWTH", "rangeForAddress", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "address", "Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "grow", "", "cidr-debugger" })
public static final class Companion
{
    public final long getDEFAULT_RANGE_BYTES_AFTER() {
        return AddressSpace.access$getDEFAULT_RANGE_BYTES_AFTER$cp();
    }
    
    public final long getDEFAULT_RANGE_BYTES_GROWTH() {
        return AddressSpace.access$getDEFAULT_RANGE_BYTES_GROWTH$cp();
    }
    
    @NotNull
    public final AddressRange rangeForAddress(@NotNull final Address address, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        return address.rangeTo(address.plus(Address.Companion.getCoercing(this.getDEFAULT_RANGE_BYTES_AFTER() + (b ? this.getDEFAULT_RANGE_BYTES_GROWTH() : 0L) - 1)));
    }
}
