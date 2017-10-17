// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ExecuteShellCommand_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasCommand();
    
    String getCommand();
    
    ByteString getCommandBytes();
    
    boolean hasWorkingDir();
    
    String getWorkingDir();
    
    ByteString getWorkingDirBytes();
    
    boolean hasTimeoutSecs();
    
    int getTimeoutSecs();
}
