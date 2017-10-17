// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.jetbrains.cidr.execution.debugger.evaluation.CidrDictionaryEntryValue;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrElementValue;
import java.util.ArrayList;
import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;

public abstract class ContainerValueRenderer extends ValueRenderer
{
    public ContainerValueRenderer(@NotNull final CidrPhysicalValue cidrPhysicalValue) {
        if (cidrPhysicalValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "<init>"));
        }
        super(cidrPhysicalValue);
    }
    
    @Override
    protected boolean shouldPrintChildrenConsoleDescription() {
        return true;
    }
    
    @Override
    protected void addChildrenTo(@NotNull final List<LLValue> list, @NotNull final EvaluationContext evaluationContext, @NotNull final XCompositeNode xCompositeNode, final boolean b) throws ExecutionException, DebuggerCommandException {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "children", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        final ArrayList<CidrElementValue> list2 = new ArrayList<CidrElementValue>(list.size());
        for (final LLValue llValue : list) {
            try {
                if (xCompositeNode.isObsolete()) {
                    return;
                }
            }
            catch (ExecutionException ex4) {
                throw c((Exception)ex4);
            }
            list2.add(this.nextElementValue(llValue, b));
        }
        CidrValue.addAllTo((Collection<CidrValue>)list2, xCompositeNode);
    }
    
    protected final CidrElementValue createElementValue(@NotNull final LLValue llValue, final int n, final boolean b) {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "createElementValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return new CidrElementValue(llValue, "[" + n + "]", this.myValue, n, b);
    }
    
    protected final CidrElementValue nextElementValue(@NotNull final LLValue llValue) {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "nextElementValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return this.nextElementValue(llValue, false);
    }
    
    protected final CidrElementValue nextElementValue(@NotNull final LLValue llValue, final boolean b) {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "nextElementValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return this.createElementValue(llValue, this.myCurrentIndex++, b);
    }
    
    protected final CidrDictionaryEntryValue createMapElementValue(final LLValue llValue, final LLValue llValue2, final int n) {
        return new CidrDictionaryEntryValue("[" + n + "]", this.myValue, llValue, llValue2, n);
    }
    
    protected final CidrDictionaryEntryValue nextMapElementValue(final LLValue llValue, final LLValue llValue2) {
        return this.createMapElementValue(llValue, llValue2, this.myCurrentIndex++);
    }
    
    @NotNull
    public String getDictionaryEntryEvaluationExpression(@NotNull final CidrDictionaryEntryValue cidrDictionaryEntryValue, final boolean b) {
        try {
            if (cidrDictionaryEntryValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "entry", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "getDictionaryEntryEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        String childEvaluationExpression;
        try {
            childEvaluationExpression = this.getChildEvaluationExpression(cidrDictionaryEntryValue.getObject(), b);
            if (childEvaluationExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ContainerValueRenderer", "getDictionaryEntryEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return childEvaluationExpression;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
