// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015" }, d2 = { "Lcom/jetbrains/cidr/lang/quickfixes/CVDecompositionResult;", "", "type", "Lcom/jetbrains/cidr/lang/types/OCType;", "cv", "Lcom/jetbrains/cidr/lang/types/CVQualifiers;", "(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/CVQualifiers;)V", "getCv", "()Lcom/jetbrains/cidr/lang/types/CVQualifiers;", "getType", "()Lcom/jetbrains/cidr/lang/types/OCType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "cidr-lang" })
public final class CVDecompositionResult
{
    @NotNull
    private final OCType type;
    @NotNull
    private final CVQualifiers cv;
    
    @NotNull
    public final OCType getType() {
        return this.type;
    }
    
    @NotNull
    public final CVQualifiers getCv() {
        return this.cv;
    }
    
    public CVDecompositionResult(@NotNull final OCType type, @NotNull final CVQualifiers cv) {
        Intrinsics.checkParameterIsNotNull((Object)type, "type");
        Intrinsics.checkParameterIsNotNull((Object)cv, "cv");
        this.type = type;
        this.cv = cv;
    }
    
    @NotNull
    public final OCType component1() {
        return this.type;
    }
    
    @NotNull
    public final CVQualifiers component2() {
        return this.cv;
    }
    
    @NotNull
    public final CVDecompositionResult copy(@NotNull final OCType ocType, @NotNull final CVQualifiers cvQualifiers) {
        Intrinsics.checkParameterIsNotNull((Object)ocType, "type");
        Intrinsics.checkParameterIsNotNull((Object)cvQualifiers, "cv");
        return new CVDecompositionResult(ocType, cvQualifiers);
    }
    
    @Override
    public String toString() {
        return "CVDecompositionResult(type=" + this.type + ", cv=" + this.cv + ")";
    }
    
    @Override
    public int hashCode() {
        final OCType type = this.type;
        final int n = ((type != null) ? type.hashCode() : 0) * 31;
        final CVQualifiers cv = this.cv;
        return n + ((cv != null) ? cv.hashCode() : 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof CVDecompositionResult) {
                final CVDecompositionResult cvDecompositionResult = (CVDecompositionResult)o;
                if (Intrinsics.areEqual((Object)this.type, (Object)cvDecompositionResult.type) && Intrinsics.areEqual((Object)this.cv, (Object)cvDecompositionResult.cv)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
