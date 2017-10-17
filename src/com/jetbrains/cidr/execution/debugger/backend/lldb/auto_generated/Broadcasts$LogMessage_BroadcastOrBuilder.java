// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LogMessage_BroadcastOrBuilder extends MessageOrBuilder
{
    boolean hasVerbosity();
    
    Verbosity getVerbosity();
    
    boolean hasMessage();
    
    String getMessage();
    
    ByteString getMessageBytes();
}
