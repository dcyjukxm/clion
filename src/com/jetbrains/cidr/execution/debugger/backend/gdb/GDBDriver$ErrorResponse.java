// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ExecutionException;
import com.intellij.util.NotNullProducer;

private static class ErrorResponse implements Response
{
    @NotNull
    private final NotNullProducer<ExecutionException> myErrorSupplier;
    
    public ErrorResponse(@Nullable final Throwable t) {
        this((NotNullProducer<ExecutionException>)(() -> new ExecutionException(t)));
    }
    
    public ErrorResponse(@Nullable final String s, @Nullable final Throwable t) {
        this((NotNullProducer<ExecutionException>)(() -> new ExecutionException(s, t)));
    }
    
    public ErrorResponse(@NotNull final NotNullProducer<ExecutionException> myErrorSupplier) {
        if (myErrorSupplier == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorSupplier", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$ErrorResponse", "<init>"));
        }
        this.myErrorSupplier = myErrorSupplier;
    }
    
    @NotNull
    public ExecutionException getError() {
        ExecutionException ex;
        try {
            ex = (ExecutionException)this.myErrorSupplier.produce();
            if (ex == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$ErrorResponse", "getError"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ex;
    }
    
    @NotNull
    @Override
    public GDBResponse.Record getRecord() throws ExecutionException {
        throw this.getError();
    }
    
    @NotNull
    @Override
    public String getOutput() throws ExecutionException {
        throw this.getError();
    }
    
    @Override
    public void checkError() throws ExecutionException {
        throw this.getError();
    }
    
    @Override
    public int getReceivedSignalCount() throws ExecutionException {
        throw this.getError();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
