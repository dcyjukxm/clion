// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

@FunctionalInterface
public interface SymbolFilter
{
    public static final TrueSymbolFilter NONE = new TrueSymbolFilter();
    
    boolean accept(final OCSymbol p0);
}
