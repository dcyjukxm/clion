// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import gnu.trove.TObjectHashingStrategy;

static final class OCTypeUtils$5 implements TObjectHashingStrategy<OCType> {
    public int computeHashCode(final OCType ocType) {
        return ocType.hashCode();
    }
    
    public boolean equals(final OCType ocType, final OCType ocType2) {
        return DeepEqual.equalObjects(ocType, ocType2);
    }
}