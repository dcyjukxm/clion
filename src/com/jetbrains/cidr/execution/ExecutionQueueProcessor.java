// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.util.ThrowableRunnable;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.Callable;
import com.intellij.util.Consumer;
import com.intellij.util.concurrency.QueueProcessor;

public class ExecutionQueueProcessor extends QueueProcessor<Runnable>
{
    public ExecutionQueueProcessor() {
        super((Consumer)new QueueProcessor.RunnableConsumer());
    }
    
    public <T> ExecutionResult<T> submit(@NotNull final Callable<T> callable) {
        try {
            if (callable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callable", "com/jetbrains/cidr/execution/ExecutionQueueProcessor", "submit"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ExecutionResult<T> executionResult = new ExecutionResult<T>();
        final IllegalArgumentException ex2;
        final ExecutionResult<T> executionResult2;
        this.add(() -> {
            try {
                if (callable == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callable", "com/jetbrains/cidr/execution/ExecutionQueueProcessor", "lambda$submit$0"));
                    throw ex2;
                }
            }
            catch (Throwable t) {
                throw a(t);
            }
            try {
                executionResult2.set(callable.call());
            }
            catch (Throwable exception) {
                executionResult2.setException(exception);
            }
            return;
        });
        return executionResult;
    }
    
    public <T> ExecutionResult<T> submit(@NotNull final ThrowableRunnable<? extends Exception> throwableRunnable, final T t) {
        try {
            if (throwableRunnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/execution/ExecutionQueueProcessor", "submit"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final IllegalArgumentException ex2;
        return this.submit(() -> {
            try {
                if (throwableRunnable == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/execution/ExecutionQueueProcessor", "lambda$submit$1"));
                    throw ex2;
                }
            }
            catch (Exception ex3) {
                throw a(ex3);
            }
            throwableRunnable.run();
            return t;
        });
    }
    
    public ExecutionResult<?> submit(@NotNull final ThrowableRunnable<? extends Exception> throwableRunnable) {
        try {
            if (throwableRunnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/execution/ExecutionQueueProcessor", "submit"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.submit(throwableRunnable, (Object)null);
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}
