// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.NotNull;

public interface Equality<T>
{
    boolean deepEqualStep(@NotNull final Comparator p0, @NotNull final T p1, @NotNull final T p2);
}
