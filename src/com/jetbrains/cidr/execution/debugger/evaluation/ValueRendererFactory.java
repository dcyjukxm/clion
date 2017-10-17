// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.renderers.ValueRenderer;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface ValueRendererFactory
{
    public static final ExtensionPointName<ValueRendererFactory> EP_NAME = ExtensionPointName.create("cidr.debugger.valueRendererFactory");
    
    @Nullable
    ValueRenderer createRenderer(@NotNull final FactoryContext p0) throws ExecutionException, DebuggerCommandException;
    
    @NotNull
    default ValueRenderer createRenderer(@NotNull final EvaluationContext evaluationContext, @NotNull final CidrPhysicalValue cidrPhysicalValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluationContext", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory", "createRenderer"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
        try {
            if (cidrPhysicalValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "physicalValue", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory", "createRenderer"));
            }
        }
        catch (ExecutionException ex2) {
            throw a(ex2);
        }
        final LLValue var = cidrPhysicalValue.getVar();
        final LLValueData varData = cidrPhysicalValue.getVarData(evaluationContext);
        Label_0160: {
            ValueRenderer valueRenderer = null;
            Label_0125: {
                try {
                    if (var.isValid()) {
                        break Label_0160;
                    }
                    final CidrPhysicalValue cidrPhysicalValue2 = cidrPhysicalValue;
                    valueRenderer = new ValueRenderer(cidrPhysicalValue2);
                    if (valueRenderer == null) {
                        break Label_0125;
                    }
                    return valueRenderer;
                }
                catch (ExecutionException ex3) {
                    throw a(ex3);
                }
                try {
                    final CidrPhysicalValue cidrPhysicalValue2 = cidrPhysicalValue;
                    valueRenderer = new ValueRenderer(cidrPhysicalValue2);
                    if (valueRenderer == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory", "createRenderer"));
                    }
                }
                catch (ExecutionException ex4) {
                    throw a(ex4);
                }
            }
            return valueRenderer;
        }
        final CidrDebuggerSettings instance = CidrDebuggerSettings.getInstance();
        final FactoryContext factoryContext = new FactoryContext(instance, evaluationContext, cidrPhysicalValue, var, varData);
        if (instance.RENDERERS_ENABLED) {
            final ValueRendererFactory[] array = (ValueRendererFactory[])ValueRendererFactory.EP_NAME.getExtensions();
            for (int length = array.length, i = 0; i < length; ++i) {
                final ValueRenderer renderer = array[i].createRenderer(factoryContext);
                ValueRenderer valueRenderer2 = null;
                Label_0250: {
                    try {
                        if (renderer == null) {
                            continue;
                        }
                        valueRenderer2 = renderer;
                        if (valueRenderer2 == null) {
                            break Label_0250;
                        }
                        return valueRenderer2;
                    }
                    catch (ExecutionException ex5) {
                        throw a(ex5);
                    }
                    try {
                        valueRenderer2 = renderer;
                        if (valueRenderer2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory", "createRenderer"));
                        }
                    }
                    catch (ExecutionException ex6) {
                        throw a(ex6);
                    }
                }
                return valueRenderer2;
            }
        }
        ValueRenderer valueRenderer3;
        try {
            valueRenderer3 = new ValueRenderer(cidrPhysicalValue);
            if (valueRenderer3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/ValueRendererFactory", "createRenderer"));
            }
        }
        catch (ExecutionException ex7) {
            throw a(ex7);
        }
        return valueRenderer3;
    }
    
    default ExecutionException a(final ExecutionException ex) {
        return ex;
    }
    
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
}
