// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015" }, d2 = { "Lcom/jetbrains/cidr/lang/quickfixes/CastStub;", "", "castExpr", "Lcom/jetbrains/cidr/lang/psi/OCCastExpression;", "operand", "Lcom/jetbrains/cidr/lang/psi/OCExpression;", "(Lcom/jetbrains/cidr/lang/psi/OCCastExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)V", "getCastExpr", "()Lcom/jetbrains/cidr/lang/psi/OCCastExpression;", "getOperand", "()Lcom/jetbrains/cidr/lang/psi/OCExpression;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "cidr-lang" })
public final class CastStub
{
    @NotNull
    private final OCCastExpression castExpr;
    @NotNull
    private final OCExpression operand;
    
    @NotNull
    public final OCCastExpression getCastExpr() {
        return this.castExpr;
    }
    
    @NotNull
    public final OCExpression getOperand() {
        return this.operand;
    }
    
    public CastStub(@NotNull final OCCastExpression castExpr, @NotNull final OCExpression operand) {
        Intrinsics.checkParameterIsNotNull((Object)castExpr, "castExpr");
        Intrinsics.checkParameterIsNotNull((Object)operand, "operand");
        this.castExpr = castExpr;
        this.operand = operand;
    }
    
    @NotNull
    public final OCCastExpression component1() {
        return this.castExpr;
    }
    
    @NotNull
    public final OCExpression component2() {
        return this.operand;
    }
    
    @NotNull
    public final CastStub copy(@NotNull final OCCastExpression ocCastExpression, @NotNull final OCExpression ocExpression) {
        Intrinsics.checkParameterIsNotNull((Object)ocCastExpression, "castExpr");
        Intrinsics.checkParameterIsNotNull((Object)ocExpression, "operand");
        return new CastStub(ocCastExpression, ocExpression);
    }
    
    @Override
    public String toString() {
        return "CastStub(castExpr=" + this.castExpr + ", operand=" + this.operand + ")";
    }
    
    @Override
    public int hashCode() {
        final OCCastExpression castExpr = this.castExpr;
        final int n = ((castExpr != null) ? castExpr.hashCode() : 0) * 31;
        final OCExpression operand = this.operand;
        return n + ((operand != null) ? operand.hashCode() : 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof CastStub) {
                final CastStub castStub = (CastStub)o;
                if (Intrinsics.areEqual((Object)this.castExpr, (Object)castStub.castExpr) && Intrinsics.areEqual((Object)this.operand, (Object)castStub.operand)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
