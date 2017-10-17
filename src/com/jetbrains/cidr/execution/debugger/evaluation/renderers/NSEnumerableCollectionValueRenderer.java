// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.jetbrains.cidr.execution.debugger.evaluation.CidrValue;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;

public class NSEnumerableCollectionValueRenderer extends NSEnumerableValueRenderer
{
    private final NSCollectionValueRenderer.Kind myKind;
    private volatile LLValue myEnumerator;
    
    public NSEnumerableCollectionValueRenderer(@NotNull final CidrPhysicalValue cidrPhysicalValue, @NotNull final NSCollectionValueRenderer.Kind myKind) {
        if (cidrPhysicalValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableCollectionValueRenderer", "<init>"));
        }
        if (myKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableCollectionValueRenderer", "<init>"));
        }
        super(cidrPhysicalValue);
        this.myKind = myKind;
    }
    
    @Nullable
    @Override
    protected Integer doComputeChildrenCount(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableCollectionValueRenderer", "doComputeChildrenCount"));
            }
        }
        catch (ExecutionException ex) {
            throw e((Exception)ex);
        }
        try {
            if (this.myValue.getVar().getType().matches("__NS.+0 \\*")) {
                return 0;
            }
        }
        catch (ExecutionException ex2) {
            throw e((Exception)ex2);
        }
        return (int)this.messageSendToSelfData("count", "unsigned int", evaluationContext).intValue();
    }
    
    @Nullable
    @Override
    protected CidrValue nextChild(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableCollectionValueRenderer", "nextChild"));
            }
        }
        catch (ExecutionException ex) {
            throw e((Exception)ex);
        }
        try {
            if (!evaluationContext.getData(this.myEnumerator).isValidPointer()) {
                return null;
            }
        }
        catch (ExecutionException ex2) {
            throw e((Exception)ex2);
        }
        final LLValue messageSend = evaluationContext.messageSend(this.myEnumerator, "nextObject");
        CidrValue cidrValue;
        if (this.myKind == NSCollectionValueRenderer.Kind.DICTIONARY) {
            cidrValue = this.nextMapElementValue(messageSend, this.messageSendToSelf("objectForKey:" + EvaluationContext.cast(evaluationContext.getData(messageSend).getPointer(), "id"), "id", evaluationContext));
        }
        else {
            cidrValue = this.nextElementValue(messageSend);
        }
        return cidrValue;
    }
    
    @Override
    protected void willExpand(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableCollectionValueRenderer", "willExpand"));
            }
        }
        catch (ExecutionException ex) {
            throw e((Exception)ex);
        }
        try {
            if (this.myKind == NSCollectionValueRenderer.Kind.DICTIONARY) {
                this.myEnumerator = evaluationContext.messageSend(this.myValue.getVar(), "keyEnumerator");
                return;
            }
        }
        catch (ExecutionException ex2) {
            throw e((Exception)ex2);
        }
        this.myEnumerator = evaluationContext.messageSend(this.myValue.getVar(), "objectEnumerator");
    }
    
    @NotNull
    @Override
    public String getChildEvaluationExpression(@NotNull final CidrPhysicalValue cidrPhysicalValue, final boolean b) {
        try {
            if (cidrPhysicalValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableCollectionValueRenderer", "getChildEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        String childEvaluationExpression;
        try {
            childEvaluationExpression = NSCollectionValueRenderer.getChildEvaluationExpression(this.myValue, this.myKind, cidrPhysicalValue, b);
            if (childEvaluationExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableCollectionValueRenderer", "getChildEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw e(ex2);
        }
        return childEvaluationExpression;
    }
    
    private static Exception e(final Exception ex) {
        return ex;
    }
}
