// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

@FunctionalInterface
public interface ArgumentsProcessor<E extends OCTypeOwner>
{
    boolean process(@NotNull final OCType p0, @Nullable final OCDeclaratorSymbol p1, @NotNull final OCType p2, @Nullable final E p3, final boolean p4);
}
