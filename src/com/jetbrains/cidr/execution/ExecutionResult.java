// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import com.intellij.execution.ExecutionException;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.concurrency.FutureResult;

public class ExecutionResult<T>
{
    private final FutureResult<T> myResult;
    
    public ExecutionResult() {
        this.myResult = (com.intellij.util.concurrency.FutureResult<T>)new FutureResult();
    }
    
    public void reset() {
        this.myResult.reset();
    }
    
    public void set(@Nullable final T t) {
        this.myResult.set((Object)t);
    }
    
    public void setException(final Throwable exception) {
        this.myResult.setException(exception);
    }
    
    public boolean isDone() {
        return this.myResult.isDone();
    }
    
    public T get() throws ExecutionException {
        try {
            return (T)this.myResult.get();
        }
        catch (InterruptedException ex) {
            throw new ExecutionException("Execution interrupted", (Throwable)ex);
        }
        catch (java.util.concurrent.ExecutionException ex2) {
            throw new ExecutionException(ex2.getMessage(), ex2.getCause());
        }
    }
    
    public T get(final long n, final TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        try {
            return (T)this.myResult.get(n, timeUnit);
        }
        catch (InterruptedException ex) {
            throw new ExecutionException("Execution interrupted", (Throwable)ex);
        }
        catch (java.util.concurrent.ExecutionException ex2) {
            throw new ExecutionException(ex2.getMessage(), ex2.getCause());
        }
    }
}
