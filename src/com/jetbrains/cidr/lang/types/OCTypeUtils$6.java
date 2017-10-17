// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import gnu.trove.TObjectHashingStrategy;

static final class OCTypeUtils$6 implements TObjectHashingStrategy<TKey> {
    public int computeHashCode(final TKey tKey) {
        return tKey.hashCode();
    }
    
    public boolean equals(final TKey tKey, final TKey tKey2) {
        return DeepEqual.equalObjects(tKey, tKey2);
    }
}