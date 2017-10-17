// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

@FunctionalInterface
public interface BaseClassProcessor
{
    boolean process(final OCSymbol p0, final OCVisibility p1);
}
