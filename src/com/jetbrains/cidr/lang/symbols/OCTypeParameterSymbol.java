// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeArgument;

public interface OCTypeParameterSymbol<T extends OCTypeArgument> extends DeepEqual.Equality
{
    String getName();
    
    @Nullable
    T getDefaultValue();
    
    boolean isVariadic();
}
