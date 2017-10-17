// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;

private static class MapElement
{
    @NotNull
    private final LLValue myKey;
    @NotNull
    private final LLValue myValue;
    
    public MapElement(@NotNull final LLValue myKey, @NotNull final LLValue myValue) {
        if (myKey == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$MapElement", "<init>"));
        }
        if (myValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$MapElement", "<init>"));
        }
        this.myKey = myKey;
        this.myValue = myValue;
    }
    
    @NotNull
    public LLValue getKey() {
        LLValue myKey;
        try {
            myKey = this.myKey;
            if (myKey == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$MapElement", "getKey"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myKey;
    }
    
    @NotNull
    public LLValue getValue() {
        LLValue myValue;
        try {
            myValue = this.myValue;
            if (myValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$MapElement", "getValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myValue;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
