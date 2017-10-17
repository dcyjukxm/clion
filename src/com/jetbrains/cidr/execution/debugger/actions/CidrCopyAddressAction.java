// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.actions;

import com.intellij.xdebugger.impl.ui.tree.nodes.XValueContainerNode;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.ide.CopyPasteManager;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import com.intellij.xdebugger.frame.XValue;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;
import com.intellij.xdebugger.impl.ui.tree.actions.XDebuggerTreeActionBase;

public class CidrCopyAddressAction extends XDebuggerTreeActionBase
{
    @Override
    protected boolean isEnabled(@NotNull final XValueNodeImpl xValueNodeImpl, @NotNull final AnActionEvent anActionEvent) {
        try {
            if (xValueNodeImpl == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/actions/CidrCopyAddressAction", "isEnabled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/execution/debugger/actions/CidrCopyAddressAction", "isEnabled"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (!super.isEnabled(xValueNodeImpl, anActionEvent)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final XValue xValue = xValueNodeImpl.getValueContainer();
        try {
            if (!(xValue instanceof CidrPhysicalValue)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (((CidrPhysicalValue)xValue).getShownAddress() != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return false;
    }
    
    @Override
    protected void perform(final XValueNodeImpl xValueNodeImpl, @NotNull final String s, final AnActionEvent anActionEvent) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeName", "com/jetbrains/cidr/execution/debugger/actions/CidrCopyAddressAction", "perform"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        CopyPasteManager.getInstance().setContents((Transferable)new StringSelection(StringUtil.notNullize(((XValueContainerNode<CidrPhysicalValue>)xValueNodeImpl).getValueContainer().getShownAddress())));
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
