// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;

class ValueRenderer$3 implements CachedDebuggerResult.NotNullCalculator<Boolean> {
    final /* synthetic */ EvaluationContext val$context;
    final /* synthetic */ ValueRenderer this$0;
    
    @NotNull
    @Override
    public Boolean calculate() throws ExecutionException, DebuggerCommandException {
        final LLValueData varData = ValueRenderer.this.myValue.getVarData(this.val$context);
        Boolean value = null;
        Label_0054: {
            Label_0042: {
                try {
                    if (varData.isNullPointer()) {
                        break Label_0042;
                    }
                    final CachedDebuggerResult.NotNullCalculator<Boolean> notNullCalculator = this;
                    final ValueRenderer valueRenderer = notNullCalculator.this$0;
                    final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                    final boolean b = cidrPhysicalValue.isSwiftVoid();
                    if (b) {
                        break Label_0042;
                    }
                    break Label_0042;
                }
                catch (ExecutionException ex) {
                    throw a(ex);
                }
                try {
                    final CachedDebuggerResult.NotNullCalculator<Boolean> notNullCalculator = this;
                    final ValueRenderer valueRenderer = notNullCalculator.this$0;
                    final CidrPhysicalValue cidrPhysicalValue = valueRenderer.myValue;
                    final boolean b = cidrPhysicalValue.isSwiftVoid();
                    if (b) {
                        final boolean mayHaveChildren = false;
                        break Label_0054;
                    }
                }
                catch (ExecutionException ex2) {
                    throw a(ex2);
                }
            }
            final boolean mayHaveChildren = varData.mayHaveChildren();
            try {
                value = mayHaveChildren;
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$3", "calculate"));
                }
            }
            catch (ExecutionException ex3) {
                throw a(ex3);
            }
        }
        return value;
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}