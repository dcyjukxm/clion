// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.evaluation.renderers.CachedDebuggerResult;

class CidrPhysicalValue$2 implements CachedDebuggerResult.NotNullCalculator<LLValueData> {
    final /* synthetic */ EvaluationContext val$context;
    
    @NotNull
    @Override
    public LLValueData calculate() throws ExecutionException, DebuggerCommandException {
        LLValueData data;
        try {
            data = this.val$context.getData(CidrPhysicalValue.access$000(CidrPhysicalValue.this));
            if (data == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue$2", "calculate"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
        return data;
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}