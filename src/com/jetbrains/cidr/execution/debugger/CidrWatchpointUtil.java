// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import java.awt.Rectangle;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import javax.swing.JComponent;
import com.intellij.xdebugger.impl.ui.DebuggerUIUtil;
import java.awt.Point;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XBreakpointType;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.xdebugger.XDebuggerManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrWatchpointType;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.jetbrains.cidr.execution.debugger.backend.LLWatchpoint;
import com.intellij.xdebugger.frame.XValue;

public class CidrWatchpointUtil
{
    @Nullable
    public static XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> addWatchpoint(final XValue xValue, final LLWatchpoint.AccessType accessType, final LLWatchpoint.Lifetime lifetime) {
        try {
            if (!(xValue instanceof CidrPhysicalValue)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrPhysicalValue cidrPhysicalValue = (CidrPhysicalValue)xValue;
        final CidrDebugProcess process = ((CidrPhysicalValue)xValue).getProcess();
        final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties = new CidrWatchpointType.CidrWatchpointProperties();
        CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties2 = null;
        LLWatchpoint.Lifetime lifetime2 = null;
        Label_0101: {
            try {
                cidrWatchpointProperties.setDebugProcess(process);
                cidrWatchpointProperties.setOriginalPosition(process.getSession().getCurrentPosition());
                cidrWatchpointProperties.setAccessType(accessType);
                cidrWatchpointProperties.setLLValue(cidrPhysicalValue.getVar());
                cidrWatchpointProperties.setExpr(cidrPhysicalValue.getEvaluationExpression());
                cidrWatchpointProperties2 = cidrWatchpointProperties;
                if (process.supportsWatchpointLifetime()) {
                    lifetime2 = lifetime;
                    break Label_0101;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            lifetime2 = null;
        }
        cidrWatchpointProperties2.setLifetime(lifetime2);
        return addWatchpoint(process.getProject(), cidrWatchpointProperties);
    }
    
    public static XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> addWatchpoint(@NotNull final Project project, @Nullable final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/CidrWatchpointUtil", "addWatchpoint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (XBreakpoint<CidrWatchpointType.CidrWatchpointProperties>)new WriteAction<XBreakpoint<CidrWatchpointType.CidrWatchpointProperties>>() {
            final /* synthetic */ XDebuggerManager val$debuggerManager = XDebuggerManager.getInstance(project);
            
            protected void run(@NotNull final Result<XBreakpoint<CidrWatchpointType.CidrWatchpointProperties>> result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "res", "com/jetbrains/cidr/execution/debugger/CidrWatchpointUtil$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                result.setResult((Object)this.val$debuggerManager.getBreakpointManager().addBreakpoint((XBreakpointType)XBreakpointType.EXTENSION_POINT_NAME.findExtension((Class)CidrWatchpointType.class), (XBreakpointProperties)cidrWatchpointProperties));
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute().getResultObject();
    }
    
    public static void showWatchpointEditor(final XValueNodeImpl xValueNodeImpl, final XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> xBreakpoint, final Project project) {
        final XDebuggerTree tree = xValueNodeImpl.getTree();
        final Rectangle rowBounds = tree.getRowBounds(tree.getSelectionRows()[0]);
        DebuggerUIUtil.showXBreakpointEditorBalloon(project, new Point((int)rowBounds.getCenterX(), (int)rowBounds.getCenterY()), (JComponent)tree, false, xBreakpoint);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
