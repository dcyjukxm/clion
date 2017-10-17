// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import gnu.trove.TObjectHashingStrategy;

class OCSymbolReference$SymbolMembersProcessor$1 implements TObjectHashingStrategy<OCSymbol> {
    public int computeHashCode(final OCSymbol ocSymbol) {
        return ocSymbol.hashCode();
    }
    
    public boolean equals(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
        return ocSymbol == ocSymbol2;
    }
}