// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;

public static class FactoryContext
{
    @NotNull
    private final CidrDebuggerSettings mySettings;
    @NotNull
    private final EvaluationContext myEvaluationContext;
    @NotNull
    private final CidrPhysicalValue myPhysicalValue;
    @NotNull
    private final LLValue myLLValue;
    @NotNull
    private final LLValueData myLLValueData;
    
    public FactoryContext(@NotNull final CidrDebuggerSettings mySettings, @NotNull final EvaluationContext myEvaluationContext, @NotNull final CidrPhysicalValue myPhysicalValue, @NotNull final LLValue myLLValue, @NotNull final LLValueData myLLValueData) {
        if (mySettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "<init>"));
        }
        if (myEvaluationContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluationContext", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "<init>"));
        }
        if (myPhysicalValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "physicalValue", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "<init>"));
        }
        if (myLLValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "LLValue", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "<init>"));
        }
        if (myLLValueData == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "LLValueData", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "<init>"));
        }
        this.mySettings = mySettings;
        this.myEvaluationContext = myEvaluationContext;
        this.myPhysicalValue = myPhysicalValue;
        this.myLLValue = myLLValue;
        this.myLLValueData = myLLValueData;
    }
    
    @NotNull
    public CidrDebuggerSettings getSettings() {
        CidrDebuggerSettings mySettings;
        try {
            mySettings = this.mySettings;
            if (mySettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "getSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySettings;
    }
    
    @NotNull
    public EvaluationContext getEvaluationContext() {
        EvaluationContext myEvaluationContext;
        try {
            myEvaluationContext = this.myEvaluationContext;
            if (myEvaluationContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "getEvaluationContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myEvaluationContext;
    }
    
    @NotNull
    public CidrPhysicalValue getPhysicalValue() {
        CidrPhysicalValue myPhysicalValue;
        try {
            myPhysicalValue = this.myPhysicalValue;
            if (myPhysicalValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "getPhysicalValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myPhysicalValue;
    }
    
    @NotNull
    public LLValue getLLValue() {
        LLValue myLLValue;
        try {
            myLLValue = this.myLLValue;
            if (myLLValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "getLLValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLLValue;
    }
    
    @NotNull
    public LLValueData getLLValueData() {
        LLValueData myLLValueData;
        try {
            myLLValueData = this.myLLValueData;
            if (myLLValueData == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory$FactoryContext", "getLLValueData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLLValueData;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
