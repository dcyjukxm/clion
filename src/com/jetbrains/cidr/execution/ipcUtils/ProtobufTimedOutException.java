// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.ipcUtils;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandTimedOutException;

public class ProtobufTimedOutException extends DebuggerCommandTimedOutException
{
    public ProtobufTimedOutException() {
        super("Protocol Timeout");
    }
}
