// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.frame.XValueModifier;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.NotNull;

public class CidrElementValue extends CidrPhysicalValue
{
    @NotNull
    private final CidrPhysicalValue myContainer;
    private int myIndex;
    private final boolean myModifiable;
    
    public CidrElementValue(@NotNull final LLValue llValue, @NotNull final String s, @NotNull final CidrPhysicalValue myContainer, final int myIndex, final boolean myModifiable) {
        if (llValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayName", "com/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue", "<init>"));
        }
        if (myContainer == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue", "<init>"));
        }
        super(llValue, s, myContainer.getProcess(), myContainer.getSourcePosition(), myContainer.getFrame());
        this.myContainer = myContainer;
        this.myIndex = myIndex;
        this.myModifiable = myModifiable;
    }
    
    public int getIndex() {
        return this.myIndex;
    }
    
    @NotNull
    public CidrPhysicalValue getContainer() {
        CidrPhysicalValue myContainer;
        try {
            myContainer = this.myContainer;
            if (myContainer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue", "getContainer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myContainer;
    }
    
    public XValueModifier getModifier() {
        try {
            if (this.myModifiable) {
                return new CidrValueModifier(this);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    @Override
    public String getEvaluationExpression(final boolean b) {
        String childEvaluationExpression;
        try {
            childEvaluationExpression = this.myContainer.getPreparedRenderer().getChildEvaluationExpression(this, b);
            if (childEvaluationExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue", "getEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return childEvaluationExpression;
    }
    
    @Override
    protected XSourcePosition doComputePosition(@NotNull final XSourcePosition xSourcePosition) {
        try {
            if (xSourcePosition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/evaluation/CidrElementValue", "doComputePosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myContainer.doComputePosition(xSourcePosition);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
