// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

static final class OCArgumentsList$4 implements OCTypeOwner {
    final /* synthetic */ OCType val$underlyingType;
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context2", "com/jetbrains/cidr/lang/resolve/OCArgumentsList$4", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.val$underlyingType.resolve(ocResolveContext);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}