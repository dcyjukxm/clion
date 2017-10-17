// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;

private static class Info
{
    @Nullable
    public final OCType leftType;
    @Nullable
    public final OCType rightType;
    @Nullable
    public final OCType customReturnType;
    
    private Info(@Nullable final OCType leftType, @Nullable final OCType rightType, @Nullable final OCType customReturnType) {
        this.leftType = leftType;
        this.rightType = rightType;
        this.customReturnType = customReturnType;
    }
}
