// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

class BadConversionSequence
{
    @Nullable
    private OCTypeOwner FromExpr;
    private FailureKind Kind;
    private OCType FromTy;
    private OCType ToTy;
    
    public BadConversionSequence(final FailureKind failureKind, @Nullable final OCTypeOwner fromExpr, final OCType ocType, final OCType ocType2) {
        this(failureKind, ocType, ocType2);
        this.FromExpr = fromExpr;
    }
    
    public BadConversionSequence(final FailureKind kind, final OCType fromType, final OCType toType) {
        this.Kind = kind;
        this.FromExpr = null;
        this.setFromType(fromType);
        this.setToType(toType);
    }
    
    public OCType getFromType() {
        return this.FromTy;
    }
    
    public OCType getToType() {
        return this.ToTy;
    }
    
    public void setFromExpr(final OCExpression fromExpr) {
        this.FromExpr = fromExpr;
        this.setFromType(fromExpr.getType());
    }
    
    public void setFromType(final OCType fromTy) {
        this.FromTy = fromTy;
    }
    
    public void setToType(final OCType toTy) {
        this.ToTy = toTy;
    }
    
    enum FailureKind
    {
        no_conversion, 
        unrelated_class, 
        bad_qualifiers, 
        lvalue_ref_to_rvalue, 
        rvalue_ref_to_lvalue;
    }
}
