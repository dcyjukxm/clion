// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.Nullable;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.util.ThrowableConvertor;
import org.jetbrains.annotations.NotNull;

protected class Request
{
    @NotNull
    private final String myCommand;
    private boolean mySuppressOutputEvent;
    private boolean mySuppressRunningEvent;
    private boolean mySuppressTargetFinishedEvent;
    private boolean mySuppressRunningResult;
    @Nullable
    private ThrowableConvertor<StopPlace, Boolean, ExecutionException> myOnSteppingFinished;
    
    public Request(final String myCommand) {
        if (myCommand == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$Request", "<init>"));
        }
        this.myCommand = myCommand;
    }
    
    @NotNull
    public Communication send() throws ExecutionException {
        final Communication communication = new Communication(this.myCommand, this.mySuppressOutputEvent, this.mySuppressRunningEvent, this.mySuppressTargetFinishedEvent, this.mySuppressRunningResult, this.myOnSteppingFinished);
        Communication communication2;
        try {
            GDBDriver.access$1602(GDBDriver.this, communication);
            communication.initiate();
            communication2 = communication;
            if (communication2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$Request", "send"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return communication2;
    }
    
    @NotNull
    public String getCommand() {
        String myCommand;
        try {
            myCommand = this.myCommand;
            if (myCommand == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$Request", "getCommand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myCommand;
    }
    
    public Request suppressAll() {
        return this.suppressOutputEvent().suppressRunningEvent().suppressRunningResult();
    }
    
    public Request suppressOutputEvent() {
        return this.suppressOutputEvent(true);
    }
    
    public Request suppressOutputEvent(final boolean mySuppressOutputEvent) {
        this.mySuppressOutputEvent = mySuppressOutputEvent;
        return this;
    }
    
    public Request suppressRunningEvent() {
        return this.suppressRunningEvent(true);
    }
    
    public Request suppressRunningEvent(final boolean mySuppressRunningEvent) {
        this.mySuppressRunningEvent = mySuppressRunningEvent;
        return this;
    }
    
    public Request suppressTargetFinishedEvent() {
        return this.suppressTargetFinishedEvent(true);
    }
    
    public Request suppressTargetFinishedEvent(final boolean mySuppressTargetFinishedEvent) {
        this.mySuppressTargetFinishedEvent = mySuppressTargetFinishedEvent;
        return this;
    }
    
    public Request suppressRunningResult() {
        return this.suppressRunningResult(true);
    }
    
    public Request suppressRunningResult(final boolean mySuppressRunningResult) {
        this.mySuppressRunningResult = mySuppressRunningResult;
        return this;
    }
    
    public Request onSteppingFinished(@Nullable final ThrowableConvertor<StopPlace, Boolean, ExecutionException> myOnSteppingFinished) {
        this.myOnSteppingFinished = myOnSteppingFinished;
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
