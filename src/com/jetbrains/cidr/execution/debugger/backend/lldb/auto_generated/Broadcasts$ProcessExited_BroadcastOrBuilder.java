// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ProcessExited_BroadcastOrBuilder extends MessageOrBuilder
{
    boolean hasExitCode();
    
    int getExitCode();
    
    boolean hasExitDescription();
    
    String getExitDescription();
    
    ByteString getExitDescriptionBytes();
}
