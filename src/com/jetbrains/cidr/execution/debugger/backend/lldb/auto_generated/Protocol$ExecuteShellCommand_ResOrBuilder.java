// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ExecuteShellCommand_ResOrBuilder extends MessageOrBuilder
{
    boolean hasCommonResponse();
    
    CommonResponse getCommonResponse();
    
    CommonResponseOrBuilder getCommonResponseOrBuilder();
    
    boolean hasOutput();
    
    String getOutput();
    
    ByteString getOutputBytes();
    
    boolean hasStatus();
    
    int getStatus();
    
    boolean hasSignal();
    
    int getSignal();
}
