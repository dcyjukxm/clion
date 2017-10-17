// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.toolchains.CidrToolSet;

protected static class ValidationResult
{
    @Nullable
    public final CidrToolSet toolSet;
    
    public ValidationResult(@Nullable final CidrToolSet toolSet) {
        this.toolSet = toolSet;
    }
}
