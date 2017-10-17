// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.jetbrains.cidr.execution.debugger.CidrEvaluator;
import com.intellij.xdebugger.frame.XValue;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import com.intellij.openapi.util.Expirable;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.xdebugger.XExpression;
import com.intellij.xdebugger.impl.breakpoints.XExpressionImpl;
import com.jetbrains.cidr.execution.debugger.CidrEvaluatorHelper;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.frame.XValueModifier;

public class CidrValueModifier extends XValueModifier
{
    @NotNull
    private final CidrPhysicalValue myValue;
    
    public CidrValueModifier(@NotNull final CidrPhysicalValue myValue) {
        if (myValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "<init>"));
        }
        this.myValue = myValue;
    }
    
    public void setValue(@NotNull final String s, @NotNull final XValueModifier.XModificationCallback xModificationCallback) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "setValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (xModificationCallback == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "setValue"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final IllegalArgumentException ex3;
        final IllegalArgumentException ex5;
        final CidrStackFrame cidrStackFrame;
        this.myValue.getProcess().postCommand(debuggerDriver -> {
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "lambda$setValue$0"));
                    throw ex3;
                }
            }
            catch (DebuggerCommandException ex4) {
                throw b(ex4);
            }
            try {
                if (xModificationCallback == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "lambda$setValue$0"));
                    throw ex5;
                }
            }
            catch (DebuggerCommandException ex6) {
                throw b(ex6);
            }
            try {
                this.myValue.getFrame();
                this.setValue(s, CidrEvaluatorHelper.getInstance().convertAndEvaluate(cidrStackFrame.getProcess(), debuggerDriver, (XExpression)XExpressionImpl.fromText(s), cidrStackFrame.getSourcePosition(), cidrStackFrame.getThreadId(), cidrStackFrame.getFrameIndex()), xModificationCallback, debuggerDriver);
            }
            catch (DebuggerCommandException ex7) {
                xModificationCallback.errorOccurred(CidrDebuggerBundle.message("debug.assign.error.cannotAssignExpression", s, this.myValue.getName()) + ":\n" + ex7.getMessage());
                CidrDebuggerLog.LOG.info(ex7.getMessage());
            }
        });
    }
    
    @NotNull
    public CidrPhysicalValue getValue() {
        CidrPhysicalValue myValue;
        try {
            myValue = this.myValue;
            if (myValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "getValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myValue;
    }
    
    protected void setValue(@NotNull final String s, @NotNull final Pair<LLValue, String> pair, @NotNull final XValueModifier.XModificationCallback xModificationCallback, @NotNull final DebuggerDriver debuggerDriver) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origExpr", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "setValue"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (pair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "valuePair", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "setValue"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (xModificationCallback == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "setValue"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier", "setValue"));
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
        final EvaluationContext evaluationContext = this.myValue.createEvaluationContext(debuggerDriver, null);
        final String evaluationExpression = this.myValue.getEvaluationExpression(true);
        final CidrEvaluator evaluator = this.myValue.getFrame().getEvaluator();
        try {
            if (evaluator == null) {
                xModificationCallback.errorOccurred(CidrDebuggerBundle.message("debug.assign.error.cannotAssignExpression", s, evaluationExpression));
                CidrDebuggerLog.LOG.info(this.myValue.getFrame().toString() + " has null evaluator");
                return;
            }
        }
        catch (ExecutionException ex5) {
            throw b((Exception)ex5);
        }
        evaluator.evaluate(evaluationExpression + "=" + evaluationContext.convertToRValue(this.myValue.getVarData(evaluationContext), pair), (XDebuggerEvaluator.XEvaluationCallback)new XDebuggerEvaluator.XEvaluationCallback() {
            public void evaluated(@NotNull final XValue xValue) {
                try {
                    if (xValue == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/debugger/evaluation/CidrValueModifier$1", "evaluated"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                xModificationCallback.valueModified();
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
                xModificationCallback.errorOccurred(CidrDebuggerBundle.message("debug.assign.error.cannotAssignExpression", s, evaluationExpression) + ":\n" + s);
                CidrDebuggerLog.LOG.info(s);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }, this.myValue.getFrame().getSourcePosition());
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
