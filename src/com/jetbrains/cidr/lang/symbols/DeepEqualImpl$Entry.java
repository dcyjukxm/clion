// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

private static class Entry
{
    private final Object t1;
    private final Object t2;
    State myPushedState;
    private int myHash;
    
    private Entry(final Object t1, final Object t2) {
        this.t1 = t1;
        this.t2 = t2;
    }
    
    @Override
    public int hashCode() {
        if (this.myHash == 0) {
            this.myHash = System.identityHashCode(this.t1) + System.identityHashCode(this.t2);
        }
        return this.myHash;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        final Entry entry = (Entry)o;
        return (entry.t1 == this.t1 && entry.t2 == this.t2) || (entry.t2 == this.t1 && entry.t1 == this.t2);
    }
}
