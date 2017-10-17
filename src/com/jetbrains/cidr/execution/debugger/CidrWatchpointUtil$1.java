// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XBreakpointType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.xdebugger.XDebuggerManager;
import com.jetbrains.cidr.execution.debugger.breakpoints.CidrWatchpointType;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.openapi.application.WriteAction;

static final class CidrWatchpointUtil$1 extends WriteAction<XBreakpoint<CidrWatchpointType.CidrWatchpointProperties>> {
    final /* synthetic */ XDebuggerManager val$debuggerManager;
    final /* synthetic */ CidrWatchpointType.CidrWatchpointProperties val$properties;
    
    protected void run(@NotNull final Result<XBreakpoint<CidrWatchpointType.CidrWatchpointProperties>> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "res", "com/jetbrains/cidr/execution/debugger/CidrWatchpointUtil$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        result.setResult((Object)this.val$debuggerManager.getBreakpointManager().addBreakpoint((XBreakpointType)XBreakpointType.EXTENSION_POINT_NAME.findExtension((Class)CidrWatchpointType.class), (XBreakpointProperties)this.val$properties));
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}