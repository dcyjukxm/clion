// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerFatalException;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import java.io.IOException;
import com.intellij.util.Consumer;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Protocol;
import com.jetbrains.cidr.execution.ipcUtils.ProtobufServer;

class LLDBDriver$3 extends ProtobufServer<Protocol.CompositeResponse> {
    @Override
    protected void handleIOException(final IOException exception) {
        CidrDebuggerLog.LOG.warn((Throwable)exception);
        if (!LLDBDriver.access$000(LLDBDriver.this).isDone()) {
            LLDBDriver.access$000(LLDBDriver.this).setException(exception);
        }
        else {
            LLDBDriver.access$100(LLDBDriver.this, new DebuggerFatalException(exception));
        }
    }
}