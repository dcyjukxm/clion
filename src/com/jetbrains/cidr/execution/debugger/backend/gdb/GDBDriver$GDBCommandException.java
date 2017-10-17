// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;

private static class GDBCommandException extends DebuggerCommandException
{
    @NotNull
    private final Response myResponse;
    
    public GDBCommandException(@NotNull final Response myResponse, @NotNull final String s) {
        if (myResponse == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "response", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$GDBCommandException", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$GDBCommandException", "<init>"));
        }
        super(s);
        this.myResponse = myResponse;
    }
    
    @NotNull
    public Response getResponse() {
        Response myResponse;
        try {
            myResponse = this.myResponse;
            if (myResponse == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$GDBCommandException", "getResponse"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myResponse;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
