// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import gnu.trove.TObjectHashingStrategy;

class OCExpressionEvaluator$CachingEvaluator$1 implements TObjectHashingStrategy<Pair<OCSymbol, OCTypeSubstitution>> {
    public int computeHashCode(final Pair<OCSymbol, OCTypeSubstitution> pair) {
        return pair.hashCode();
    }
    
    public boolean equals(final Pair<OCSymbol, OCTypeSubstitution> pair, final Pair<OCSymbol, OCTypeSubstitution> pair2) {
        return DeepEqual.equalObjects(pair, pair2);
    }
}