// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.intellij.ui.SimpleTextAttributes;
import javax.swing.Icon;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.frame.XDebuggerTreeNodeHyperlink;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValue;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.frame.XValueChildrenList;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.intellij.xdebugger.frame.XCompositeNode;

private static class ConsoleDescriptionNode implements XCompositeNode
{
    private final StringBuilder myBuilder;
    private final EvaluationContext myContext;
    
    public ConsoleDescriptionNode(final StringBuilder myBuilder, final EvaluationContext myContext) {
        this.myBuilder = myBuilder;
        this.myContext = myContext;
    }
    
    public void addChildren(@NotNull final XValueChildrenList list, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "children", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "addChildren"));
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        for (int i = 0; i < list.size(); ++i) {
            this.myBuilder.append("\t").append(list.getName(i)).append(" = ");
            try {
                this.myBuilder.append(((CidrValue)list.getValue(i)).getConsoleDescription(this.myContext).replace("\n", "\n\t"));
            }
            catch (DebuggerCommandException ex2) {
                this.setErrorMessage(ex2.getMessage());
            }
            catch (ExecutionException ex3) {
                throw new ExecutionRuntimeException(ex3);
            }
            this.myBuilder.append("\n");
        }
    }
    
    public void tooManyChildren(final int n) {
        try {
            this.myBuilder.append("\t...");
            if (n > 0) {
                this.myBuilder.append(n).append(" more\n");
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
    }
    
    public void setErrorMessage(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorMessage", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "setErrorMessage"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        ValueRenderer.access$000(this.myBuilder, s);
    }
    
    public void setErrorMessage(@NotNull final String errorMessage, @Nullable final XDebuggerTreeNodeHyperlink xDebuggerTreeNodeHyperlink) {
        try {
            if (errorMessage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorMessage", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "setErrorMessage"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        this.setErrorMessage(errorMessage);
    }
    
    public void setMessage(@NotNull final String s, @Nullable final Icon icon, @NotNull final SimpleTextAttributes simpleTextAttributes, @Nullable final XDebuggerTreeNodeHyperlink xDebuggerTreeNodeHyperlink) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "setMessage"));
            }
        }
        catch (ExecutionRuntimeException ex) {
            throw b(ex);
        }
        try {
            if (simpleTextAttributes == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$ConsoleDescriptionNode", "setMessage"));
            }
        }
        catch (ExecutionRuntimeException ex2) {
            throw b(ex2);
        }
    }
    
    public boolean isObsolete() {
        return false;
    }
    
    public void setAlreadySorted(final boolean b) {
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
