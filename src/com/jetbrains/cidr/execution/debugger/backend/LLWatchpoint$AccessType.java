// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.NotNull;

public enum AccessType
{
    READ("-r", "Read", "hw-rwpt"), 
    WRITE("", "Write", "wpt"), 
    ANY("-a", "Any", "hw-awpt");
    
    private String myParam;
    private String myDisplayText;
    private String myTupleKey;
    
    private AccessType(final String myParam, final String myDisplayText, final String myTupleKey) {
        this.myParam = myParam;
        this.myDisplayText = myDisplayText;
        this.myTupleKey = myTupleKey;
    }
    
    @NotNull
    public String getParamString() {
        String myParam;
        try {
            myParam = this.myParam;
            if (myParam == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLWatchpoint$AccessType", "getParamString"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myParam;
    }
    
    @Override
    public String toString() {
        return this.myDisplayText;
    }
    
    public String getTupleKey() {
        return this.myTupleKey;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
