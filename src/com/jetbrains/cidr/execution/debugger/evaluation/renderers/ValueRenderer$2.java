// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.xdebugger.frame.XFullValueEvaluator;

class ValueRenderer$2 extends XFullValueEvaluator {
    final /* synthetic */ LLValue val$value;
    
    public void startEvaluation(@NotNull final XFullValueEvaluator.XFullValueEvaluationCallback xFullValueEvaluationCallback) {
        try {
            if (xFullValueEvaluationCallback == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$2", "startEvaluation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final LLValue val$value;
        final IllegalArgumentException ex2;
        final IllegalArgumentException ex4;
        ValueRenderer.this.getValue().getProcess().postCommand(debuggerDriver -> {
            val$value = this.val$value;
            try {
                if (xFullValueEvaluationCallback == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$2", "lambda$startEvaluation$0"));
                    throw ex2;
                }
            }
            catch (DebuggerCommandException ex3) {
                throw b(ex3);
            }
            try {
                if (val$value == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$2", "lambda$startEvaluation$0"));
                    throw ex4;
                }
            }
            catch (DebuggerCommandException ex5) {
                throw b(ex5);
            }
            try {
                if (xFullValueEvaluationCallback.isObsolete()) {
                    return;
                }
            }
            catch (DebuggerCommandException ex6) {
                throw b(ex6);
            }
            try {
                xFullValueEvaluationCallback.evaluated(StringUtil.notNullize(debuggerDriver.getDescription(val$value, 10485760)));
            }
            catch (DebuggerCommandException ex7) {
                xFullValueEvaluationCallback.errorOccurred(ex7.getMessage());
            }
            catch (ExecutionException ex8) {
                xFullValueEvaluationCallback.errorOccurred(CidrDebuggerUtil.getExceptionMessage((Exception)ex8));
                throw ex8;
            }
        });
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}