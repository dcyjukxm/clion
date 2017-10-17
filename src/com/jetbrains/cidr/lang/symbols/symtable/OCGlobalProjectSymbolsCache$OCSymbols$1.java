// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import gnu.trove.TObjectHashingStrategy;

class OCGlobalProjectSymbolsCache$OCSymbols$1 implements TObjectHashingStrategy<OCSymbol> {
    public int computeHashCode(final OCSymbol ocSymbol) {
        return System.identityHashCode(ocSymbol);
    }
    
    public boolean equals(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
        return ocSymbol == ocSymbol2;
    }
}