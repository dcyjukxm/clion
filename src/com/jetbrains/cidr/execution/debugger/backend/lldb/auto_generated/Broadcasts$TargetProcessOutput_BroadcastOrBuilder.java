// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface TargetProcessOutput_BroadcastOrBuilder extends MessageOrBuilder
{
    boolean hasText();
    
    String getText();
    
    ByteString getTextBytes();
    
    boolean hasOutputType();
    
    Model.OutputType getOutputType();
}
