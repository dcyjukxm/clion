// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.openapi.util.Expirable;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.xdebugger.frame.XValueModifier;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValueModifier;

private class KeyedValueModifier extends CidrValueModifier
{
    public KeyedValueModifier(final KeyedValue keyedValue) {
        super(keyedValue);
    }
    
    @Override
    protected void setValue(@NotNull final String s, @NotNull final Pair<LLValue, String> pair, @NotNull final XValueModifier.XModificationCallback xModificationCallback, @NotNull final DebuggerDriver debuggerDriver) throws DebuggerCommandException, ExecutionException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origExpr", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValueModifier", "setValue"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
        try {
            if (pair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "valuePair", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValueModifier", "setValue"));
            }
        }
        catch (ExecutionException ex2) {
            throw a(ex2);
        }
        try {
            if (xModificationCallback == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValueModifier", "setValue"));
            }
        }
        catch (ExecutionException ex3) {
            throw a(ex3);
        }
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$KeyedValueModifier", "setValue"));
            }
        }
        catch (ExecutionException ex4) {
            throw a(ex4);
        }
        final EvaluationContext evaluationContext = this.getValue().createEvaluationContext(debuggerDriver, null);
        try {
            NSManagedObjectValueRenderer.this.invokeSetValue(pair, evaluationContext, ((KeyedValue)this.getValue()).getParent().getVarData(evaluationContext), this.getValue().getName());
            xModificationCallback.valueModified();
        }
        catch (ExecutionException ex5) {
            xModificationCallback.errorOccurred(CidrDebuggerBundle.message("debug.assign.error.cannotAssignExpression", s, this.getValue().getName()));
            CidrDebuggerLog.LOG.debug(ex5.getMessage());
        }
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}
