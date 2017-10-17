// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.xdebugger.impl.ui.XDebugSessionData;
import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.XDebugProcess;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.xdebugger.impl.XDebugProcessConfiguratorStarter;

class CidrRunner$1 extends XDebugProcessConfiguratorStarter {
    final /* synthetic */ XDebugSessionListener[] val$listeners;
    final /* synthetic */ CidrCommandLineState val$state;
    final /* synthetic */ boolean val$muteBreakpoints;
    
    @NotNull
    public XDebugProcess start(@NotNull final XDebugSession xDebugSession) throws ExecutionException {
        try {
            if (xDebugSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/CidrRunner$1", "start"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final XDebugSessionListener[] val$listeners = this.val$listeners;
        for (int length = val$listeners.length, i = 0; i < length; ++i) {
            xDebugSession.addSessionListener(val$listeners[i]);
        }
        XDebugProcess startDebugProcess;
        try {
            startDebugProcess = this.val$state.startDebugProcess(xDebugSession);
            if (startDebugProcess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunner$1", "start"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return startDebugProcess;
    }
    
    @Override
    public void configure(final XDebugSessionData xDebugSessionData) {
        try {
            if (this.val$muteBreakpoints) {
                xDebugSessionData.setBreakpointsMuted(true);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}