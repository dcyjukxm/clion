// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.Nullable;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;

public interface Resolver extends Comparator
{
    void clearCaches();
    
     <T> void setEquality(@NotNull final Class<T> p0, @Nullable final Function<T, Equality<T>> p1);
}
