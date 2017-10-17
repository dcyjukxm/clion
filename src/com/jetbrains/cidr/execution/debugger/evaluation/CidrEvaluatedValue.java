// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.NotNull;

public class CidrEvaluatedValue extends CidrPhysicalValue
{
    @NotNull
    private final String myEvaluationExpression;
    
    public CidrEvaluatedValue(@NotNull final LLValue llValue, @NotNull final CidrDebugProcess cidrDebugProcess, @Nullable final XSourcePosition xSourcePosition, @NotNull final CidrStackFrame cidrStackFrame, @NotNull final String myEvaluationExpression) {
        if (llValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/CidrEvaluatedValue", "<init>"));
        }
        if (cidrDebugProcess == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/evaluation/CidrEvaluatedValue", "<init>"));
        }
        if (cidrStackFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/evaluation/CidrEvaluatedValue", "<init>"));
        }
        if (myEvaluationExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluationExpression", "com/jetbrains/cidr/execution/debugger/evaluation/CidrEvaluatedValue", "<init>"));
        }
        super(llValue, "result", cidrDebugProcess, xSourcePosition, cidrStackFrame);
        this.myEvaluationExpression = myEvaluationExpression;
    }
    
    @NotNull
    @Override
    public String getEvaluationExpression() {
        String myEvaluationExpression;
        try {
            myEvaluationExpression = this.myEvaluationExpression;
            if (myEvaluationExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrEvaluatedValue", "getEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myEvaluationExpression;
    }
    
    @Override
    protected XSourcePosition doComputePosition(@NotNull final XSourcePosition xSourcePosition) {
        try {
            if (xSourcePosition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/evaluation/CidrEvaluatedValue", "doComputePosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
