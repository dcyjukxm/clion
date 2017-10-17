// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.util.SystemInfo;

class GDBDriver$5 extends AttachConnectCommand {
    final /* synthetic */ int val$pid;
    
    @Override
    protected void attach() throws ExecutionException, DebuggerCommandException {
        GDBDriver.this.buildRequest("-target-attach %d", this.val$pid).suppressRunningEvent(SystemInfo.isWindows).send().waitFor(GDBResponse.ResultRecord.Type.done, GDBResponse.ResultRecord.Type.running);
    }
    
    @Override
    protected void whenAttached() throws ExecutionException, DebuggerCommandException {
        GDBDriver.access$400(GDBDriver.this, this.val$pid);
    }
}