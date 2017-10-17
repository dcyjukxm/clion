// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;

private abstract class AttachConnectCommand implements LoadingCommand
{
    @Override
    public void run() throws ExecutionException, DebuggerCommandException {
        GDBDriver.access$600(GDBDriver.this).down();
        try {
            this.attach();
            GDBDriver.access$700(GDBDriver.access$600(GDBDriver.this));
        }
        finally {
            GDBDriver.access$600(GDBDriver.this).up();
        }
        GDBDriver.this.doSelectWinbreakBinary();
        this.whenAttached();
        GDBDriver.this.sendRequestAndWaitForRunning("-exec-continue", new Object[0]);
    }
    
    protected abstract void attach() throws ExecutionException, DebuggerCommandException;
    
    protected abstract void whenAttached() throws ExecutionException, DebuggerCommandException;
}
