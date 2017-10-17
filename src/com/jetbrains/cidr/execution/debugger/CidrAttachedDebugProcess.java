// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.xdebugger.XDebugSession;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.RunParameters;

public abstract class CidrAttachedDebugProcess extends CidrDebugProcess
{
    public CidrAttachedDebugProcess(@NotNull final RunParameters runParameters, @NotNull final XDebugSession xDebugSession, @NotNull final TextConsoleBuilder textConsoleBuilder) throws ExecutionException {
        if (runParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/execution/debugger/CidrAttachedDebugProcess", "<init>"));
        }
        if (xDebugSession == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/debugger/CidrAttachedDebugProcess", "<init>"));
        }
        if (textConsoleBuilder == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consoleBuilder", "com/jetbrains/cidr/execution/debugger/CidrAttachedDebugProcess", "<init>"));
        }
        super(runParameters, xDebugSession, textConsoleBuilder);
    }
    
    @Override
    protected void doStart(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/CidrAttachedDebugProcess", "doStart"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        debuggerDriver.loadForAttach();
    }
    
    @Override
    protected void doLaunchTarget(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/CidrAttachedDebugProcess", "doLaunchTarget"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        debuggerDriver.attachTo(this.getPidToAttachTo());
    }
    
    protected abstract int getPidToAttachTo() throws ExecutionException;
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}
