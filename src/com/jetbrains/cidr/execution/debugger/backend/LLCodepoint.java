// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

public class LLCodepoint
{
    private final int myId;
    
    public LLCodepoint(final int myId) {
        this.myId = myId;
    }
    
    public int getId() {
        return this.myId;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && this.myId == ((LLCodepoint)o).myId);
    }
    
    @Override
    public int hashCode() {
        return this.myId;
    }
}
