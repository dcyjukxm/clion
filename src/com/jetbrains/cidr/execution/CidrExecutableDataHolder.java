// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.Nullable;

public interface CidrExecutableDataHolder
{
    @Nullable
    ExecutableData getExecutableData();
    
    void setExecutableData(@Nullable final ExecutableData p0);
}
