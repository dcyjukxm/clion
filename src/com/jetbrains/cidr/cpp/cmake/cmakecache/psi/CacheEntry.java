// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public interface CacheEntry extends CMakeCacheEntryMixin
{
    @NotNull
    EntryName getEntryName();
    
    @Nullable
    EntryType getEntryType();
    
    @Nullable
    EntryValue getEntryValue();
}
