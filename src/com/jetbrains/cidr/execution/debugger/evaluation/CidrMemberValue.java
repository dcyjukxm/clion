// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.frame.XValueModifier;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.NotNull;

public class CidrMemberValue extends CidrPhysicalValue
{
    @NotNull
    private final CidrPhysicalValue myParent;
    private final boolean myModifiable;
    
    public CidrMemberValue(@NotNull final LLValue llValue, @NotNull final CidrPhysicalValue myParent, final boolean myModifiable) {
        if (llValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue", "<init>"));
        }
        if (myParent == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue", "<init>"));
        }
        super(llValue, myParent.getProcess(), myParent.getSourcePosition(), myParent.getFrame());
        this.myParent = myParent;
        this.myModifiable = myModifiable;
    }
    
    public CidrMemberValue(@NotNull final LLValue llValue, @NotNull final String s, @NotNull final CidrPhysicalValue myParent, final boolean myModifiable) {
        if (llValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayName", "com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue", "<init>"));
        }
        if (myParent == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue", "<init>"));
        }
        super(llValue, s, myParent.getProcess(), myParent.getSourcePosition(), myParent.getFrame());
        this.myParent = myParent;
        this.myModifiable = myModifiable;
    }
    
    @NotNull
    public CidrPhysicalValue getParent() {
        CidrPhysicalValue myParent;
        try {
            myParent = this.myParent;
            if (myParent == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue", "getParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myParent;
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
            childEvaluationExpression = this.myParent.getPreparedRenderer().getChildEvaluationExpression(this, b);
            if (childEvaluationExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue", "getEvaluationExpression"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/evaluation/CidrMemberValue", "doComputePosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myParent.getTypesHelper().computeSourcePosition(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
