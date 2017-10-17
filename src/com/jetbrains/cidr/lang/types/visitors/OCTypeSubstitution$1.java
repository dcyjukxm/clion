// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import gnu.trove.TObjectHashingStrategy;

static final class OCTypeSubstitution$1 implements TObjectHashingStrategy<OCSimpleTypeSubstitution> {
    public int computeHashCode(final OCSimpleTypeSubstitution ocSimpleTypeSubstitution) {
        return ocSimpleTypeSubstitution.hashCode();
    }
    
    public boolean equals(final OCSimpleTypeSubstitution ocSimpleTypeSubstitution, final OCSimpleTypeSubstitution ocSimpleTypeSubstitution2) {
        return ocSimpleTypeSubstitution.equals(ocSimpleTypeSubstitution2);
    }
}