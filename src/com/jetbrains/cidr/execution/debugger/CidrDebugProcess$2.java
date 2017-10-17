// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.execution.ExecutionException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;

class CidrDebugProcess$2 implements DebuggerCommand {
    final /* synthetic */ boolean val$detach;
    final /* synthetic */ Runnable val$terminateRunnable;
    
    @Override
    public void run(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$2", "run"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            Label_0067: {
                try {
                    if (this.val$detach) {
                        debuggerDriver.detach();
                        break Label_0067;
                    }
                }
                catch (ExecutionException ex2) {
                    throw b((Exception)ex2);
                }
                debuggerDriver.abort();
            }
        }
        finally {
            this.val$terminateRunnable.run();
        }
        debuggerDriver.checkErrors();
    }
    
    @Override
    public void rejected(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$2", "rejected"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.val$terminateRunnable.run();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}