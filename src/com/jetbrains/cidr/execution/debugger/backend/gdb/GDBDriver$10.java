// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.execution.ExecutionException;

class GDBDriver$10 extends ConsoleCommand<Object> {
    final /* synthetic */ String val$command;
    final /* synthetic */ long val$threadId;
    final /* synthetic */ int val$frameIndex;
    
    @Override
    public Object call() throws ExecutionException, DebuggerCommandException {
        String s;
        if (GDBDriver.this.isInPromptMode()) {
            s = this.val$command;
        }
        else {
            long n = this.val$threadId;
            int n2 = this.val$frameIndex;
            final StopPlace access$1200 = GDBDriver.access$1200(GDBDriver.this);
            Label_0075: {
                try {
                    if (n >= 0L || access$1200 == null) {
                        break Label_0075;
                    }
                }
                catch (ExecutionException ex) {
                    throw b(ex);
                }
                n = access$1200.thread.getId();
                n2 = access$1200.frame.getIndex();
            }
            final String trim = this.val$command.trim();
            try {
                if ("run".equals(trim)) {
                    GDBDriver.this.handleGDBOutput("Command '" + trim + "' is not supported.\n");
                    return null;
                }
            }
            catch (ExecutionException ex2) {
                throw b(ex2);
            }
            s = GDBDriver.createConsoleCommand(trim, n, n2);
        }
        GDBDriver.this.sendRequest("%s", s).waitFor(new GDBResponse.ResultRecord.Type[0]);
        return null;
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}