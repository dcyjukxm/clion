// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;

class GDBDriver$6 extends AttachConnectCommand {
    final /* synthetic */ String val$connectionString;
    
    @Override
    protected void attach() throws ExecutionException, DebuggerCommandException {
        GDBDriver.this.sendRequest("-target-select remote %s", this.val$connectionString).waitFor(GDBResponse.ResultRecord.Type.connected);
    }
    
    @Override
    protected void whenAttached() throws ExecutionException, DebuggerCommandException {
        GDBDriver.access$500(GDBDriver.this, this.val$connectionString);
    }
}