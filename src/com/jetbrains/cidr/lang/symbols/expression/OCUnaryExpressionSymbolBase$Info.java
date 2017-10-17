// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;

private static class Info
{
    @Nullable
    public final OCType opType;
    @Nullable
    public final OCType customReturnType;
    
    private Info(@Nullable final OCType opType, @Nullable final OCType customReturnType) {
        this.opType = opType;
        this.customReturnType = customReturnType;
    }
}
