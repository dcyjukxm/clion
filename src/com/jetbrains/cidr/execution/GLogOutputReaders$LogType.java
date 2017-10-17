// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.NotNull;

public enum LogType
{
    INFO("INFO"), 
    WARNING("WARNING"), 
    ERROR("ERROR"), 
    FATAL("FATAL");
    
    private final String myName;
    
    private LogType(final String myName) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/GLogOutputReaders$LogType", "<init>"));
        }
        super(s, n);
        this.myName = myName;
    }
    
    @Override
    public String toString() {
        return this.myName;
    }
}
