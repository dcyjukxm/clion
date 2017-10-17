// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.actions;

import com.jetbrains.cidr.execution.debugger.breakpoints.CidrWatchpointType;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.jetbrains.cidr.execution.debugger.CidrWatchpointUtil;
import com.jetbrains.cidr.execution.debugger.backend.LLWatchpoint;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrMemberValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrLocalValue;
import com.intellij.xdebugger.frame.XValue;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.xdebugger.impl.ui.tree.actions.XDebuggerTreeActionBase;

public class CidrAddWatchpointAction extends XDebuggerTreeActionBase
{
    @Override
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        final Project eventProject = getEventProject(anActionEvent);
        XDebugSession currentSession = null;
        Label_0029: {
            try {
                if (eventProject == null) {
                    currentSession = null;
                    break Label_0029;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            currentSession = XDebuggerManager.getInstance(eventProject).getCurrentSession();
        }
        final XDebugSession xDebugSession = currentSession;
        XDebugProcess debugProcess = null;
        Label_0048: {
            try {
                if (xDebugSession == null) {
                    debugProcess = null;
                    break Label_0048;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            debugProcess = xDebugSession.getDebugProcess();
        }
        final XDebugProcess xDebugProcess = debugProcess;
        Presentation presentation = null;
        boolean enabled = false;
        Label_0101: {
            Label_0092: {
                try {
                    anActionEvent.getPresentation().setVisible(xDebugProcess instanceof CidrDebugProcess);
                    presentation = anActionEvent.getPresentation();
                    if (!(xDebugProcess instanceof CidrDebugProcess)) {
                        break Label_0092;
                    }
                    final XDebugProcess xDebugProcess2 = xDebugProcess;
                    final CidrDebugProcess cidrDebugProcess = (CidrDebugProcess)xDebugProcess2;
                    final boolean b = cidrDebugProcess.supportsWatchpoints();
                    if (b) {
                        break Label_0092;
                    }
                    break Label_0092;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    final XDebugProcess xDebugProcess2 = xDebugProcess;
                    final CidrDebugProcess cidrDebugProcess = (CidrDebugProcess)xDebugProcess2;
                    final boolean b = cidrDebugProcess.supportsWatchpoints();
                    if (b) {
                        enabled = true;
                        break Label_0101;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            enabled = false;
        }
        presentation.setEnabled(enabled);
    }
    
    @Override
    protected boolean isEnabled(@NotNull final XValueNodeImpl xValueNodeImpl, @NotNull final AnActionEvent anActionEvent) {
        try {
            if (xValueNodeImpl == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/actions/CidrAddWatchpointAction", "isEnabled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/execution/debugger/actions/CidrAddWatchpointAction", "isEnabled"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final XValue xValue = xValueNodeImpl.getValueContainer();
        Label_0117: {
            try {
                if (xValue instanceof CidrLocalValue) {
                    break Label_0117;
                }
                final XValue xValue2 = xValue;
                final boolean b = xValue2 instanceof CidrMemberValue;
                if (b) {
                    break Label_0117;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final XValue xValue2 = xValue;
                final boolean b = xValue2 instanceof CidrMemberValue;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    @Override
    protected void perform(final XValueNodeImpl xValueNodeImpl, @NotNull final String s, final AnActionEvent anActionEvent) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeName", "com/jetbrains/cidr/execution/debugger/actions/CidrAddWatchpointAction", "perform"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> addWatchpoint = CidrWatchpointUtil.addWatchpoint(xValueNodeImpl.getValueContainer(), LLWatchpoint.AccessType.WRITE, LLWatchpoint.Lifetime.STACK_FRAME);
        try {
            if (addWatchpoint != null) {
                CidrWatchpointUtil.showWatchpointEditor(xValueNodeImpl, addWatchpoint, getEventProject(anActionEvent));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
