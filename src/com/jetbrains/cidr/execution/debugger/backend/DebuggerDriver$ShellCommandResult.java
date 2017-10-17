// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.NotNull;

public static class ShellCommandResult
{
    private final String myOutput;
    private final int myStatus;
    private final int mySignal;
    
    public ShellCommandResult(@NotNull final String myOutput, final int myStatus, final int mySignal) {
        if (myOutput == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ShellCommandResult", "<init>"));
        }
        this.myOutput = myOutput;
        this.myStatus = myStatus;
        this.mySignal = mySignal;
    }
    
    @NotNull
    public String getOutput() {
        String myOutput;
        try {
            myOutput = this.myOutput;
            if (myOutput == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ShellCommandResult", "getOutput"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myOutput;
    }
    
    public int getStatus() {
        return this.myStatus;
    }
    
    public int getSignal() {
        return this.mySignal;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
