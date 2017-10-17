// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;

public interface OCTypeOwner
{
    @Nullable
    OCType getResolvedType(@NotNull final OCResolveContext p0);
}
