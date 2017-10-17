// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016" }, d2 = { "Lcom/jetbrains/cidr/lang/quickfixes/RefTypes;", "", "old", "Lcom/jetbrains/cidr/lang/types/OCType;", "new", "isPointer", "", "(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Z)V", "()Z", "getNew", "()Lcom/jetbrains/cidr/lang/types/OCType;", "getOld", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "cidr-lang" })
public final class RefTypes
{
    @NotNull
    private final OCType old;
    @NotNull
    private final OCType new;
    private final boolean isPointer;
    
    @NotNull
    public final OCType getOld() {
        return this.old;
    }
    
    @NotNull
    public final OCType getNew() {
        return this.new;
    }
    
    public final boolean isPointer() {
        return this.isPointer;
    }
    
    public RefTypes(@NotNull final OCType old, @NotNull final OCType new1, final boolean isPointer) {
        Intrinsics.checkParameterIsNotNull((Object)old, "old");
        Intrinsics.checkParameterIsNotNull((Object)new1, "new");
        this.old = old;
        this.new = new1;
        this.isPointer = isPointer;
    }
    
    @NotNull
    public final OCType component1() {
        return this.old;
    }
    
    @NotNull
    public final OCType component2() {
        return this.new;
    }
    
    public final boolean component3() {
        return this.isPointer;
    }
    
    @NotNull
    public final RefTypes copy(@NotNull final OCType ocType, @NotNull final OCType ocType2, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)ocType, "old");
        Intrinsics.checkParameterIsNotNull((Object)ocType2, "new");
        return new RefTypes(ocType, ocType2, b);
    }
    
    @Override
    public String toString() {
        return "RefTypes(old=" + this.old + ", new=" + this.new + ", isPointer=" + this.isPointer + ")";
    }
    
    @Override
    public int hashCode() {
        final OCType old = this.old;
        final int n = ((old != null) ? old.hashCode() : 0) * 31;
        final OCType new1 = this.new;
        final int n2 = (n + ((new1 != null) ? new1.hashCode() : 0)) * 31;
        int isPointer;
        if ((isPointer = (this.isPointer ? 1 : 0)) != 0) {
            isPointer = 1;
        }
        return n2 + isPointer;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof RefTypes) {
                final RefTypes refTypes = (RefTypes)o;
                if (Intrinsics.areEqual((Object)this.old, (Object)refTypes.old) && Intrinsics.areEqual((Object)this.new, (Object)refTypes.new) && this.isPointer == refTypes.isPointer) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
