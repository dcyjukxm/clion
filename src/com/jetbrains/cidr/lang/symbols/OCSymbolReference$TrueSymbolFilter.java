// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

public static class TrueSymbolFilter implements SymbolFilter
{
    @Override
    public boolean accept(final OCSymbol ocSymbol) {
        return true;
    }
    
    @Override
    public String toString() {
        return "*any symbol*";
    }
}
