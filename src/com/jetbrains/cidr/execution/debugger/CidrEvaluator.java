// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.util.Expirable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.xdebugger.evaluation.EvaluationMode;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrEvaluatedValue;
import com.intellij.xdebugger.XExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Key;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;

public abstract class CidrEvaluator extends XDebuggerEvaluator
{
    public static final Key THROW_ON_EVALUATION;
    @NotNull
    protected final CidrStackFrame myFrame;
    
    public CidrEvaluator(@NotNull final CidrStackFrame myFrame) {
        if (myFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/CidrEvaluator", "<init>"));
        }
        this.myFrame = myFrame;
    }
    
    @NotNull
    public CidrEvaluatedValue doEvaluate(@NotNull final DebuggerDriver debuggerDriver, @Nullable final XSourcePosition xSourcePosition, @NotNull final XExpression xExpression) throws ExecutionException, DebuggerCommandException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/CidrEvaluator", "doEvaluate"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        try {
            if (xExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/CidrEvaluator", "doEvaluate"));
            }
        }
        catch (ExecutionException ex2) {
            throw b(ex2);
        }
        ThrowInTest.doThrow((UserDataHolder)this.myFrame.getProcess(), CidrEvaluator.THROW_ON_EVALUATION);
        final EvaluationContext evaluationContext = this.createEvaluationContext(debuggerDriver);
        LLValue evaluate;
        String expression;
        if (xExpression.getMode() == EvaluationMode.EXPRESSION) {
            final Pair<LLValue, String> convertAndEvaluate = CidrEvaluatorHelper.getInstance().convertAndEvaluate(this.myFrame.getProcess(), debuggerDriver, xExpression, xSourcePosition, this.myFrame.getThreadId(), this.myFrame.getFrameIndex());
            evaluate = (LLValue)convertAndEvaluate.first;
            expression = (String)convertAndEvaluate.second;
        }
        else {
            evaluate = evaluationContext.evaluate(xExpression.getExpression());
            expression = xExpression.getExpression();
        }
        CidrEvaluatedValue cidrEvaluatedValue;
        try {
            cidrEvaluatedValue = new CidrEvaluatedValue(evaluate, this.myFrame.getProcess(), xSourcePosition, this.myFrame, expression);
            if (cidrEvaluatedValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrEvaluator", "doEvaluate"));
            }
        }
        catch (ExecutionException ex3) {
            throw b(ex3);
        }
        return cidrEvaluatedValue;
    }
    
    public boolean isCodeFragmentEvaluationSupported() {
        return this.myFrame.getProcess().driverSupportsCodeFragmentEvaluation();
    }
    
    protected EvaluationContext createEvaluationContext(final DebuggerDriver debuggerDriver) {
        return this.myFrame.getProcess().createEvaluationContext(debuggerDriver, null, this.myFrame);
    }
    
    static {
        THROW_ON_EVALUATION = Key.create("THROW_ON_EVALUATION");
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}
