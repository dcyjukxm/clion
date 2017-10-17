// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface HandleConsoleCommand_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasThreadId();
    
    int getThreadId();
    
    boolean hasFrameIndex();
    
    int getFrameIndex();
    
    boolean hasCommand();
    
    String getCommand();
    
    ByteString getCommandBytes();
}
