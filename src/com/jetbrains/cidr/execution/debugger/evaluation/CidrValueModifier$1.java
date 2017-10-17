// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.frame.XValue;
import com.intellij.xdebugger.frame.XValueModifier;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;

class CidrValueModifier$1 implements XDebuggerEvaluator.XEvaluationCallback {
    final /* synthetic */ XValueModifier.XModificationCallback val$callback;
    final /* synthetic */ String val$origExpr;
    final /* synthetic */ String val$qualName;
    
    public void evaluated(@NotNull final XValue xValue) {
        try {
            if (xValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier$1", "evaluated"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.val$callback.valueModified();
    }
    
    public void errorOccurred(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorMessage", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier$1", "errorOccurred"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.val$callback.errorOccurred(CidrDebuggerBundle.message("debug.assign.error.cannotAssignExpression", this.val$origExpr, this.val$qualName) + ":\n" + s);
        CidrDebuggerLog.LOG.info(s);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}