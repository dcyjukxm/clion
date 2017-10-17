// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.memory;

import kotlin.ranges.ClosedRange$DefaultImpls;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
import kotlin.ranges.RangesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import kotlin.ranges.ClosedRange;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0000J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0002J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u0011\u0010\u0006\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\u0003\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001c" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "Lkotlin/ranges/ClosedRange;", "Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "start", "endInclusive", "(Lcom/jetbrains/cidr/execution/debugger/memory/Address;Lcom/jetbrains/cidr/execution/debugger/memory/Address;)V", "endCoerced", "getEndCoerced", "()Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "getEndInclusive", "getStart", "coerceIn", "range", "covers", "", "equals", "other", "", "hashCode", "", "headUntil", "address", "intersectWith", "intersects", "tailFrom", "toString", "", "Companion", "cidr-debugger" })
public final class AddressRange implements ClosedRange<Address>
{
    @NotNull
    private final Address start;
    @NotNull
    private final Address endInclusive;
    @JvmField
    @NotNull
    public static final AddressRange EMPTY;
    @JvmField
    @NotNull
    public static final AddressRange WHOLE;
    public static final Companion Companion;
    
    @NotNull
    public final Address getEndCoerced() {
        return this.getEndInclusive().plus(Address.Companion.getCoercing(1));
    }
    
    public final boolean covers(@NotNull final AddressRange addressRange) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        return addressRange.isEmpty() || (this.contains((Comparable)addressRange.getStart()) && this.contains((Comparable)addressRange.getEndInclusive()));
    }
    
    public final boolean intersects(@NotNull final AddressRange addressRange) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        return (!addressRange.isEmpty() && (this.contains((Comparable)addressRange.getStart()) || this.contains((Comparable)addressRange.getEndInclusive()))) || (!this.isEmpty() && (addressRange.contains((Comparable)this.getStart()) || addressRange.contains((Comparable)this.getEndInclusive())));
    }
    
    @NotNull
    public final AddressRange intersectWith(@NotNull final AddressRange addressRange) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        return this.intersects(addressRange) ? ((Address)RangesKt.coerceIn((Comparable)this.getStart(), (ClosedRange)addressRange)).rangeTo((Address)RangesKt.coerceIn((Comparable)this.getEndInclusive(), (ClosedRange)addressRange)) : AddressRange.EMPTY;
    }
    
    @NotNull
    public final AddressRange coerceIn(@NotNull final AddressRange addressRange) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        return this.intersectWith(addressRange);
    }
    
    @NotNull
    public final AddressRange headUntil(@NotNull final Address address) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        return (address.compareTo(this.getStart()) <= 0) ? AddressRange.EMPTY : this.getStart().rangeTo(address.minus(1));
    }
    
    @NotNull
    public final AddressRange tailFrom(@NotNull final Address address) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        return (address.compareTo(this.getEndInclusive()) > 0) ? AddressRange.EMPTY : address.rangeTo(this.getEndInclusive());
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return o instanceof AddressRange && ((this.isEmpty() && ((AddressRange)o).isEmpty()) || (Intrinsics.areEqual((Object)this.getStart(), (Object)((AddressRange)o).getStart()) && Intrinsics.areEqual((Object)this.getEndInclusive(), (Object)((AddressRange)o).getEndInclusive())));
    }
    
    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : (this.getStart().hashCode() ^ this.getEndInclusive().hashCode());
    }
    
    @NotNull
    @Override
    public String toString() {
        return "" + this.getStart() + ".." + this.getEndInclusive();
    }
    
    @NotNull
    public Address getStart() {
        return this.start;
    }
    
    @NotNull
    public Address getEndInclusive() {
        return this.endInclusive;
    }
    
    public AddressRange(@NotNull final Address start, @NotNull final Address endInclusive) {
        Intrinsics.checkParameterIsNotNull((Object)start, "start");
        Intrinsics.checkParameterIsNotNull((Object)endInclusive, "endInclusive");
        this.start = start;
        this.endInclusive = endInclusive;
    }
    
    static {
        Companion = new Companion(null);
        EMPTY = new AddressRange(Address.MIN_VALUE.plus(1), Address.MIN_VALUE);
        WHOLE = new AddressRange(Address.MIN_VALUE, Address.MAX_VALUE);
    }
    
    public boolean contains(@NotNull final Address address) {
        Intrinsics.checkParameterIsNotNull((Object)address, "value");
        return ClosedRange$DefaultImpls.contains((ClosedRange)this, (Comparable)address);
    }
    
    public boolean isEmpty() {
        return ClosedRange$DefaultImpls.isEmpty((ClosedRange)this);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange$Companion;", "", "()V", "EMPTY", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "WHOLE", "cidr-debugger" })
    public static final class Companion
    {
    }
}
