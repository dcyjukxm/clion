// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

public enum Lifetime
{
    STACK_FRAME("Stack Frame"), 
    PERSISTENT("Persistent");
    
    private String myDisplayText;
    
    private Lifetime(final String myDisplayText) {
        this.myDisplayText = myDisplayText;
    }
    
    @Override
    public String toString() {
        return this.myDisplayText;
    }
}
