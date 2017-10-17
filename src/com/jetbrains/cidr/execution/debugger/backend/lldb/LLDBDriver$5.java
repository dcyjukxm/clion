// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import com.google.protobuf.GeneratedMessage;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Protocol;

class LLDBDriver$5 extends ThrowIfNotValid<Protocol.Launch_Res> {
    final /* synthetic */ Ref val$launchedPid;
    
    @Override
    public void consume(final Protocol.Launch_Res launch_Res) {
        super.consume(launch_Res);
        if (this.isValid()) {
            this.val$launchedPid.set((Object)launch_Res.getPid());
        }
    }
}