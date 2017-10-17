// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.ExpiredException;
import com.intellij.util.Consumer;

private class MyCommandProcessor implements Consumer<DebuggerCommand>
{
    public void consume(final DebuggerCommand debuggerCommand) {
        final ProcessHandler processHandler = CidrDebugProcess.access$500(CidrDebugProcess.this).getProcessHandler();
        Label_0045: {
            Label_0032: {
                try {
                    if (processHandler.isProcessTerminating()) {
                        break Label_0032;
                    }
                    final ProcessHandler processHandler2 = processHandler;
                    final boolean b = processHandler2.isProcessTerminated();
                    if (b) {
                        break Label_0032;
                    }
                    break Label_0045;
                }
                catch (ExpiredException ex) {
                    throw b(ex);
                }
                try {
                    final ProcessHandler processHandler2 = processHandler;
                    final boolean b = processHandler2.isProcessTerminated();
                    if (b) {
                        debuggerCommand.rejected("Process finished");
                        return;
                    }
                }
                catch (ExpiredException ex2) {
                    throw b(ex2);
                }
            }
            try {
                debuggerCommand.run(CidrDebugProcess.access$500(CidrDebugProcess.this));
            }
            catch (ExpiredException ex4) {}
            catch (ExecutionException ex3) {
                CidrDebugProcess.this.handleCommandException(CidrDebugProcess.access$500(CidrDebugProcess.this), debuggerCommand, ex3);
            }
        }
    }
    
    private static ExpiredException b(final ExpiredException ex) {
        return ex;
    }
}
