// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Map;
import com.intellij.util.containers.MostlySingularMultiMap;
import org.jetbrains.annotations.Nullable;

public interface Comparator
{
     <T> boolean equalObjects(@Nullable final Equality<T> p0, @Nullable final Equality<T> p1);
    
    boolean equalIterable(@Nullable final Iterable p0, @Nullable final Iterable p1);
    
    boolean equalMultiMaps(@Nullable final MostlySingularMultiMap p0, @Nullable final MostlySingularMultiMap p1);
    
    boolean equalMaps(@Nullable final Map p0, @Nullable final Map p1);
    
    boolean equalObjects(@Nullable final Object p0, @Nullable final Object p1);
}
