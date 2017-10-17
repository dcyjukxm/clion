// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.intellij.openapi.util.Couple;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import java.util.regex.Matcher;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import java.util.regex.Pattern;

public abstract class NSContainerValueRenderer extends ContainerValueRenderer
{
    private static final Pattern DESCRIPTION_PATTERN;
    
    public NSContainerValueRenderer(@NotNull final CidrPhysicalValue cidrPhysicalValue) {
        if (cidrPhysicalValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSContainerValueRenderer", "<init>"));
        }
        super(cidrPhysicalValue);
    }
    
    @Override
    protected boolean ignoreDescription(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "description", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSContainerValueRenderer", "ignoreDescription"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        Label_0072: {
            try {
                if (super.ignoreDescription(s)) {
                    break Label_0072;
                }
                final Pattern pattern = NSContainerValueRenderer.DESCRIPTION_PATTERN;
                final String s2 = s;
                final Matcher matcher = pattern.matcher(s2);
                final boolean b = matcher.matches();
                if (!b) {
                    break Label_0072;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                final Pattern pattern = NSContainerValueRenderer.DESCRIPTION_PATTERN;
                final String s2 = s;
                final Matcher matcher = pattern.matcher(s2);
                final boolean b = matcher.matches();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    protected String doComputeFallbackValue(@NotNull final EvaluationContext evaluationContext, @NotNull final LLValue llValue, @NotNull final LLValueData llValueData) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSContainerValueRenderer", "doComputeFallbackValue"));
            }
        }
        catch (ExecutionException ex) {
            throw d((Exception)ex);
        }
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSContainerValueRenderer", "doComputeFallbackValue"));
            }
        }
        catch (ExecutionException ex2) {
            throw d((Exception)ex2);
        }
        try {
            if (llValueData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSContainerValueRenderer", "doComputeFallbackValue"));
            }
        }
        catch (ExecutionException ex3) {
            throw d((Exception)ex3);
        }
        final String type = this.getValue().getType();
        String s;
        if (StringUtil.containsIgnoreCase(type, "Dictionary")) {
            s = "key/value pair";
        }
        else if (StringUtil.contains((CharSequence)type, (CharSequence)"NS")) {
            s = "element";
        }
        else {
            s = "value";
        }
        final int intValue = this.computeChildrenCount(evaluationContext);
        String string;
        try {
            string = intValue + " " + StringUtil.pluralize(s, intValue);
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSContainerValueRenderer", "doComputeFallbackValue"));
            }
        }
        catch (ExecutionException ex4) {
            throw d((Exception)ex4);
        }
        return string;
    }
    
    @Override
    public Icon getIcon(final boolean b) {
        return AllIcons.Debugger.Db_array;
    }
    
    @Nullable
    protected static Couple<LLValue> getDictionaryEntryChildren(@NotNull final EvaluationContext evaluationContext, @NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSContainerValueRenderer", "getDictionaryEntryChildren"));
            }
        }
        catch (ExecutionException ex) {
            throw d((Exception)ex);
        }
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "entry", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSContainerValueRenderer", "getDictionaryEntryChildren"));
            }
        }
        catch (ExecutionException ex2) {
            throw d((Exception)ex2);
        }
        final DebuggerDriver.ResultList<LLValue> variableChildren = evaluationContext.getVariableChildren(llValue, 0, 2);
        Label_0123: {
            try {
                if (variableChildren.list.size() != 2) {
                    break Label_0123;
                }
                final DebuggerDriver.ResultList<LLValue> list = variableChildren;
                final boolean b = list.hasMore;
                if (!b) {
                    break Label_0123;
                }
                break Label_0123;
            }
            catch (ExecutionException ex3) {
                throw d((Exception)ex3);
            }
            try {
                final DebuggerDriver.ResultList<LLValue> list = variableChildren;
                final boolean b = list.hasMore;
                if (!b) {
                    return (Couple<LLValue>)Couple.of((Object)variableChildren.list.get(0), (Object)variableChildren.list.get(1));
                }
            }
            catch (ExecutionException ex4) {
                throw d((Exception)ex4);
            }
        }
        CidrDebuggerLog.LOG.warn("Unexpected dictionary layout");
        return null;
    }
    
    static {
        DESCRIPTION_PATTERN = Pattern.compile("@?\"?\\d+ ((values?)|(elements?)|(key/value pairs?))\"?");
    }
    
    private static Exception d(final Exception ex) {
        return ex;
    }
}
