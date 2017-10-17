// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;

public static class ResolveResult
{
    @NotNull
    public final Kind kind;
    @Nullable
    public final OCType type;
    
    public ResolveResult(@NotNull final Kind kind, @Nullable final OCType type) {
        if (kind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$ResolveResult", "<init>"));
        }
        this.kind = kind;
        this.type = type;
    }
}
