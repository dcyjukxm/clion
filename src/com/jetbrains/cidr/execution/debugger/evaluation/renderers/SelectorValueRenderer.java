// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.frame.XFullValueEvaluator;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.evaluation.ValueRendererFactory;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;

public class SelectorValueRenderer extends ValueRenderer
{
    public SelectorValueRenderer(@NotNull final CidrPhysicalValue cidrPhysicalValue) {
        if (cidrPhysicalValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/SelectorValueRenderer", "<init>"));
        }
        super(cidrPhysicalValue);
    }
    
    @Nullable
    public static ValueRenderer createIfSelector(@NotNull final ValueRendererFactory.FactoryContext factoryContext) {
        try {
            if (factoryContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/SelectorValueRenderer", "createIfSelector"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        ValueRenderer valueRenderer = null;
        if (factoryContext.getLLValueData().isValidPointer() && isSelectorType(factoryContext.getLLValue().getType())) {
            valueRenderer = new SelectorValueRenderer(factoryContext.getPhysicalValue());
        }
        return valueRenderer;
    }
    
    @NotNull
    @Override
    protected Pair<String, XFullValueEvaluator> doComputeValueAndEvaluator(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/SelectorValueRenderer", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        String s = this.getValue().getVarData(evaluationContext).getDescription();
        Pair create = null;
        Label_0127: {
            try {
                if (!StringUtil.isEmptyOrSpaces(s)) {
                    if (!StringUtil.unquoteString(s).isEmpty()) {
                        break Label_0127;
                    }
                }
            }
            catch (ExecutionException ex2) {
                throw c((Exception)ex2);
            }
            s = evaluationContext.evaluateData("(char *)sel_getName((void *)" + evaluationContext.getData(this.myValue.getVar()).getPointer() + ")").getPresentableValue();
            try {
                create = Pair.create((Object)s, (Object)null);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/SelectorValueRenderer", "doComputeValueAndEvaluator"));
                }
            }
            catch (ExecutionException ex3) {
                throw c((Exception)ex3);
            }
        }
        return (Pair<String, XFullValueEvaluator>)create;
    }
    
    public static boolean isSelectorType(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/SelectorValueRenderer", "isSelectorType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0069: {
            try {
                if (s.endsWith("objc_selector *")) {
                    break Label_0069;
                }
                final String s2 = s;
                final String s3 = "SEL";
                final boolean b = s2.equals(s3);
                if (b) {
                    break Label_0069;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final String s2 = s;
                final String s3 = "SEL";
                final boolean b = s2.equals(s3);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return false;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
