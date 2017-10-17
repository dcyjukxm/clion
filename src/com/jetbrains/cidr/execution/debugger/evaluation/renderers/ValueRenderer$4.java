// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;

class ValueRenderer$4 implements CachedDebuggerResult.Calculator<Integer> {
    final /* synthetic */ EvaluationContext val$context;
    final /* synthetic */ ValueRenderer this$0;
    
    @Nullable
    @Override
    public Integer calculate() throws ExecutionException, DebuggerCommandException {
        final LLValueData varData = ValueRenderer.this.myValue.getVarData(this.val$context);
        Label_0042: {
            try {
                if (varData.isNullPointer()) {
                    break Label_0042;
                }
                final CachedDebuggerResult.Calculator<Integer> calculator = this;
                final ValueRenderer valueRenderer = calculator.this$0;
                final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                final boolean b = cidrPhysicalValue.isSwiftVoid();
                if (b) {
                    break Label_0042;
                }
                return ValueRenderer.this.doComputeChildrenCount(this.val$context);
            }
            catch (ExecutionException ex) {
                throw a(ex);
            }
            try {
                final CachedDebuggerResult.Calculator<Integer> calculator = this;
                final ValueRenderer valueRenderer = calculator.this$0;
                final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                final boolean b = cidrPhysicalValue.isSwiftVoid();
                if (b) {
                    return new Integer(0);
                }
            }
            catch (ExecutionException ex2) {
                throw a(ex2);
            }
        }
        return ValueRenderer.this.doComputeChildrenCount(this.val$context);
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}