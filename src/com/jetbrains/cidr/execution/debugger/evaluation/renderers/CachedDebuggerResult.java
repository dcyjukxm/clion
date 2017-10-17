// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.util.Ref;

public class CachedDebuggerResult<T>
{
    private volatile Ref<T> myResult;
    private volatile ExecutionException myExecutionException;
    private volatile DebuggerCommandException myUserException;
    
    public CachedDebuggerResult() {
        this.myResult = null;
        this.myExecutionException = null;
        this.myUserException = null;
    }
    
    @Nullable
    public T getResultIfAvailable() {
        return (T)Ref.deref((Ref)this.myResult);
    }
    
    @NotNull
    public T getResult(final NotNullCalculator<T> notNullCalculator) throws ExecutionException, DebuggerCommandException {
        Object result;
        try {
            result = this.getResult((Calculator<Object>)notNullCalculator);
            if (result == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/CachedDebuggerResult", "getResult"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return (T)result;
    }
    
    @Nullable
    public T getResult(final Calculator<T> calculator) throws ExecutionException, DebuggerCommandException {
        try {
            if (this.myExecutionException != null) {
                throw this.myExecutionException;
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        try {
            if (this.myUserException != null) {
                throw this.myUserException;
            }
        }
        catch (DebuggerCommandException ex2) {
            throw b(ex2);
        }
        com.intellij.openapi.util.Ref<T> myResult = this.myResult;
        if (myResult == null) {
            try {
                myResult = (this.myResult = (com.intellij.openapi.util.Ref<T>)Ref.create((Object)calculator.calculate()));
            }
            catch (DebuggerCommandException myUserException) {
                throw this.myUserException = myUserException;
            }
            catch (ExecutionException myExecutionException) {
                throw this.myExecutionException = myExecutionException;
            }
        }
        return (T)myResult.get();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public interface NotNullCalculator<T> extends Calculator<T>
    {
        @NotNull
        T calculate() throws ExecutionException, DebuggerCommandException;
    }
    
    public interface Calculator<T>
    {
        @Nullable
        T calculate() throws ExecutionException, DebuggerCommandException;
    }
}
