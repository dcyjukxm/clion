// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import java.io.Serializable;

public interface OCTypeArgument extends Serializable, DeepEqual.Equality
{
    String getNameForPresentation(final OCType.Presentation p0, @NotNull final OCResolveContext p1, final boolean p2, final int p3);
    
    boolean equals(final Object p0, @NotNull final OCResolveContext p1);
    
    boolean isVariadic();
}
