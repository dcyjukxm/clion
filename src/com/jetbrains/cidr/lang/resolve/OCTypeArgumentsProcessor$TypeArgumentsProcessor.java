// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.types.OCTypeArgument;

@FunctionalInterface
public interface TypeArgumentsProcessor
{
    boolean process(final OCTypeArgument p0, final OCTypeArgument p1);
}
