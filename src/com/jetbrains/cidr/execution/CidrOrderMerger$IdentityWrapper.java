// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

private static class IdentityWrapper
{
    final Object wrappee;
    
    public IdentityWrapper(final Object wrappee) {
        this.wrappee = wrappee;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this.wrappee == ((IdentityWrapper)o).wrappee;
    }
    
    @Override
    public int hashCode() {
        return System.identityHashCode(this.wrappee);
    }
}
