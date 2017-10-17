// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import gnu.trove.TObjectHashingStrategy;

static final class OCTypeUtils$1 implements TObjectHashingStrategy<T> {
    public int computeHashCode(final T t) {
        return t.hashCode();
    }
    
    public boolean equals(final T t, final T t2) {
        return DeepEqual.equalObjects(t, t2);
    }
}