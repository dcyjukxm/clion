// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.intellij.openapi.util.Pair;
import gnu.trove.TObjectHashingStrategy;

static final class OCTypeUtils$3 implements TObjectHashingStrategy<Pair<OCSymbolReference, OCTypeSubstitution>> {
    public int computeHashCode(final Pair<OCSymbolReference, OCTypeSubstitution> pair) {
        return pair.hashCode();
    }
    
    public boolean equals(final Pair<OCSymbolReference, OCTypeSubstitution> pair, final Pair<OCSymbolReference, OCTypeSubstitution> pair2) {
        return DeepEqual.equalObjects(pair, pair2);
    }
}