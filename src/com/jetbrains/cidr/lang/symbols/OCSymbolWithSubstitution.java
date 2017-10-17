// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;

public interface OCSymbolWithSubstitution
{
    @NotNull
    OCTypeSubstitution getSubstitution();
}
