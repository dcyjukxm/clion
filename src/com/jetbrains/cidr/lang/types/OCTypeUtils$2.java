// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;
import gnu.trove.TObjectHashingStrategy;

static final class OCTypeUtils$2 implements TObjectHashingStrategy<T> {
    public int computeHashCode(final T t) {
        return t.hashCode() * 31 + ((t instanceof OCSymbolWithSubstitution) ? ((OCSymbolWithSubstitution)t).getSubstitution().hashCode() : 0);
    }
    
    public boolean equals(final T t, final T t2) {
        return t.equals(t2) && (!(t instanceof OCSymbolWithSubstitution) || !(t2 instanceof OCSymbolWithSubstitution) || DeepEqual.equalObjects(((OCSymbolWithSubstitution)t).getSubstitution(), ((OCSymbolWithSubstitution)t2).getSubstitution()));
    }
}