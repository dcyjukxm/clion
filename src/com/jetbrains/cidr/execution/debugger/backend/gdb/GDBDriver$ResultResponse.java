// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;

private static class ResultResponse implements Response
{
    @NotNull
    private final GDBResponse.Record myRecord;
    @NotNull
    private final String myOutput;
    private final int myReceivedSignalCount;
    
    public ResultResponse(@NotNull final GDBResponse.Record myRecord, @NotNull final String myOutput, final int myReceivedSignalCount) {
        if (myRecord == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "record", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$ResultResponse", "<init>"));
        }
        if (myOutput == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$ResultResponse", "<init>"));
        }
        this.myRecord = myRecord;
        this.myOutput = myOutput;
        this.myReceivedSignalCount = myReceivedSignalCount;
    }
    
    @NotNull
    @Override
    public GDBResponse.Record getRecord() {
        GDBResponse.Record myRecord;
        try {
            myRecord = this.myRecord;
            if (myRecord == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$ResultResponse", "getRecord"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myRecord;
    }
    
    @NotNull
    @Override
    public String getOutput() {
        String myOutput;
        try {
            myOutput = this.myOutput;
            if (myOutput == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$ResultResponse", "getOutput"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myOutput;
    }
    
    @Override
    public int getReceivedSignalCount() {
        return this.myReceivedSignalCount;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
