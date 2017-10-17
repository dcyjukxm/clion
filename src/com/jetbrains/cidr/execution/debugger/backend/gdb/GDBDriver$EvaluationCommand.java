// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;

protected abstract static class EvaluationCommand<T> implements SuspendedCommand<T>
{
    @NotNull
    private final String myExpression;
    
    public EvaluationCommand(@NotNull final String myExpression) {
        if (myExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$EvaluationCommand", "<init>"));
        }
        this.myExpression = myExpression;
    }
    
    @NotNull
    public String getExpression() {
        String myExpression;
        try {
            myExpression = this.myExpression;
            if (myExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$EvaluationCommand", "getExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myExpression;
    }
    
    @Override
    public long getTimeout() {
        return GDBDriver.access$1500();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
