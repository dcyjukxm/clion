// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.memory.Address;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/backend/LLInstruction;", "", "address", "Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "disassembly", "", "function", "functionOffset", "", "(Lcom/jetbrains/cidr/execution/debugger/memory/Address;Ljava/lang/String;Ljava/lang/String;I)V", "getAddress", "()Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "getDisassembly", "()Ljava/lang/String;", "getFunction", "getFunctionOffset", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "cidr-debugger" })
public final class LLInstruction
{
    @NotNull
    private final Address address;
    @NotNull
    private final String disassembly;
    @Nullable
    private final String function;
    private final int functionOffset;
    
    @NotNull
    public final Address getAddress() {
        return this.address;
    }
    
    @NotNull
    public final String getDisassembly() {
        return this.disassembly;
    }
    
    @Nullable
    public final String getFunction() {
        return this.function;
    }
    
    public final int getFunctionOffset() {
        return this.functionOffset;
    }
    
    public LLInstruction(@NotNull final Address address, @NotNull final String disassembly, @Nullable final String function, final int functionOffset) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        Intrinsics.checkParameterIsNotNull((Object)disassembly, "disassembly");
        this.address = address;
        this.disassembly = disassembly;
        this.function = function;
        this.functionOffset = functionOffset;
    }
    
    public LLInstruction(@NotNull final Address address, @NotNull final String s, @Nullable final String s2) {
        this(address, s, s2, 0, 8, null);
    }
    
    public LLInstruction(@NotNull final Address address, @NotNull final String s) {
        this(address, s, null, 0, 12, null);
    }
    
    @NotNull
    public final Address component1() {
        return this.address;
    }
    
    @NotNull
    public final String component2() {
        return this.disassembly;
    }
    
    @Nullable
    public final String component3() {
        return this.function;
    }
    
    public final int component4() {
        return this.functionOffset;
    }
    
    @NotNull
    public final LLInstruction copy(@NotNull final Address address, @NotNull final String s, @Nullable final String s2, final int n) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        Intrinsics.checkParameterIsNotNull((Object)s, "disassembly");
        return new LLInstruction(address, s, s2, n);
    }
    
    @Override
    public String toString() {
        return "LLInstruction(address=" + this.address + ", disassembly=" + this.disassembly + ", function=" + this.function + ", functionOffset=" + this.functionOffset + ")";
    }
    
    @Override
    public int hashCode() {
        final Address address = this.address;
        final int n = ((address != null) ? address.hashCode() : 0) * 31;
        final String disassembly = this.disassembly;
        final int n2 = (n + ((disassembly != null) ? disassembly.hashCode() : 0)) * 31;
        final String function = this.function;
        return (n2 + ((function != null) ? function.hashCode() : 0)) * 31 + Integer.hashCode(this.functionOffset);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof LLInstruction) {
                final LLInstruction llInstruction = (LLInstruction)o;
                if (Intrinsics.areEqual((Object)this.address, (Object)llInstruction.address) && Intrinsics.areEqual((Object)this.disassembly, (Object)llInstruction.disassembly) && Intrinsics.areEqual((Object)this.function, (Object)llInstruction.function) && this.functionOffset == llInstruction.functionOffset) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
