// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.xdebugger.frame.XNamedValue;
import com.intellij.xdebugger.frame.XValueChildrenList;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.xdebugger.frame.XFullValueEvaluator;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import org.jetbrains.annotations.Nullable;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.renderers.ContainerValueRenderer;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.NotNull;

public class CidrDictionaryEntryValue extends CidrValue
{
    @NotNull
    private final CidrPhysicalValue myContainer;
    @NotNull
    private final CidrPhysicalValue myKey;
    @NotNull
    private final CidrPhysicalValue myObject;
    protected final int myIndex;
    
    public CidrDictionaryEntryValue(@NotNull final String s, @NotNull final CidrPhysicalValue myContainer, @NotNull final LLValue llValue, @NotNull final LLValue llValue2, final int myIndex) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayName", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "<init>"));
        }
        if (myContainer == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "<init>"));
        }
        if (llValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "<init>"));
        }
        if (llValue2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "object", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "<init>"));
        }
        super(s, myContainer.getProcess(), myContainer.getSourcePosition(), myContainer.getFrame());
        this.myContainer = myContainer;
        this.myIndex = myIndex;
        this.myKey = new CidrElementValue(llValue, "key", myContainer, this.myIndex, false);
        this.myObject = new CidrElementValue(llValue2, "object", myContainer, this.myIndex, false);
    }
    
    @NotNull
    public CidrPhysicalValue getObject() {
        CidrPhysicalValue myObject;
        try {
            myObject = this.myObject;
            if (myObject == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "getObject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return myObject;
    }
    
    public int getIndex() {
        return this.myIndex;
    }
    
    @Override
    protected XSourcePosition doComputePosition(@NotNull final XSourcePosition xSourcePosition) {
        try {
            if (xSourcePosition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "doComputePosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return this.myContainer.doComputePosition(xSourcePosition);
    }
    
    @NotNull
    @Override
    public String getEvaluationExpression(final boolean b) {
        String dictionaryEntryEvaluationExpression;
        try {
            dictionaryEntryEvaluationExpression = ((ContainerValueRenderer)this.myContainer.getPreparedRenderer()).getDictionaryEntryEvaluationExpression(this, b);
            if (dictionaryEntryEvaluationExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "getEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return dictionaryEntryEvaluationExpression;
    }
    
    @Override
    protected boolean doComputeMayHaveChildren(@NotNull final EvaluationContext evaluationContext) throws ExecutionException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "doComputeMayHaveChildren"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return true;
    }
    
    @Nullable
    @Override
    protected Icon doComputeIcon(@NotNull final EvaluationContext evaluationContext, final boolean b) throws ExecutionException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "doComputeIcon"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return AllIcons.Debugger.Value;
    }
    
    @Nullable
    @Override
    protected String doComputeType(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "doComputeType"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return null;
    }
    
    @NotNull
    @Override
    protected Pair<String, XFullValueEvaluator> doComputeValueAndEvaluator(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        Pair create;
        try {
            create = Pair.create((Object)(a((String)this.myKey.doComputeValueAndEvaluator(evaluationContext).first) + " -> " + a((String)this.myObject.doComputeValueAndEvaluator(evaluationContext).first)), (Object)null);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "doComputeValueAndEvaluator"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return (Pair<String, XFullValueEvaluator>)create;
    }
    
    @NotNull
    @Override
    public String getConsoleDescription(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "getConsoleDescription"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        String string;
        try {
            string = a(this.myKey.getConsoleDescription(evaluationContext)) + " -> " + a(this.myObject.getConsoleDescription(evaluationContext));
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "getConsoleDescription"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return string;
    }
    
    @NotNull
    private static String a(@NotNull final String s) throws ExecutionException, DebuggerCommandException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "shorten"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        String first;
        try {
            first = StringUtil.first(s, 100, true);
            if (first == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "shorten"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return first;
    }
    
    @Override
    protected void computeValueChildren(@NotNull final XCompositeNode xCompositeNode) {
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDictionaryEntryValue", "computeValueChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (xCompositeNode.isObsolete()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        final XValueChildrenList list = new XValueChildrenList(2);
        list.add((XNamedValue)this.myKey);
        list.add((XNamedValue)this.myObject);
        xCompositeNode.addChildren(list, true);
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
