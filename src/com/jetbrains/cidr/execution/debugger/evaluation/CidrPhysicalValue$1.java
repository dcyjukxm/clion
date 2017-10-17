// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.renderers.ValueRenderer;
import com.jetbrains.cidr.execution.debugger.evaluation.renderers.CachedDebuggerResult;

class CidrPhysicalValue$1 implements CachedDebuggerResult.NotNullCalculator<ValueRenderer> {
    final /* synthetic */ EvaluationContext val$context;
    
    @NotNull
    @Override
    public ValueRenderer calculate() throws ExecutionException, DebuggerCommandException {
        ValueRenderer doCreateRenderer;
        try {
            doCreateRenderer = CidrPhysicalValue.this.doCreateRenderer(this.val$context);
            if (doCreateRenderer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue$1", "calculate"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
        return doCreateRenderer;
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}