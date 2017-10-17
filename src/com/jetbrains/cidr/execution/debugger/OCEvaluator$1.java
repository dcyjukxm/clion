// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.frame.XValue;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import com.intellij.xdebugger.XExpression;
import com.intellij.xdebugger.XSourcePosition;

class OCEvaluator$1 implements CidrDebugProcess.DebuggerUIUpdateCommand {
    final /* synthetic */ XSourcePosition val$expressionPosition;
    final /* synthetic */ XExpression val$expression;
    final /* synthetic */ XDebuggerEvaluator.XEvaluationCallback val$callback;
    
    @Override
    public void run(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/OCEvaluator$1", "run"));
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        XSourcePosition xSourcePosition = null;
        Label_0072: {
            try {
                if (this.val$expressionPosition == null) {
                    xSourcePosition = OCEvaluator.this.myFrame.getSourcePosition();
                    break Label_0072;
                }
            }
            catch (DebuggerCommandException ex2) {
                throw b(ex2);
            }
            xSourcePosition = this.val$expressionPosition;
        }
        final XSourcePosition xSourcePosition2 = xSourcePosition;
        try {
            this.val$callback.evaluated((XValue)OCEvaluator.this.doEvaluate(debuggerDriver, xSourcePosition2, this.val$expression));
        }
        catch (DebuggerCommandException ex3) {
            this.val$callback.errorOccurred(ex3.getMessage());
        }
        catch (ExecutionException ex4) {
            this.val$callback.errorOccurred(CidrDebuggerUtil.getExceptionMessage((Exception)ex4));
            throw ex4;
        }
    }
    
    @Override
    public void rejected(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/execution/debugger/OCEvaluator$1", "rejected"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.val$callback.errorOccurred(s);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}