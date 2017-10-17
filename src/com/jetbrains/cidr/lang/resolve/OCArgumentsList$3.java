// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

static final class OCArgumentsList$3 implements OCTypeOwner {
    final /* synthetic */ OCExpressionSymbol val$symbol;
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList$3", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.val$symbol.getResolvedType(ocResolveContext);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}