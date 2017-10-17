// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import gnu.trove.TObjectHashingStrategy;

static final class OCTypeUtils$4 implements TObjectHashingStrategy<OCTypeParameterSymbol> {
    public int computeHashCode(final OCTypeParameterSymbol ocTypeParameterSymbol) {
        return ocTypeParameterSymbol.hashCode();
    }
    
    public boolean equals(final OCTypeParameterSymbol ocTypeParameterSymbol, final OCTypeParameterSymbol ocTypeParameterSymbol2) {
        return DeepEqual.equalObjects(ocTypeParameterSymbol, ocTypeParameterSymbol2);
    }
}