// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface Launch_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasCommandLine();
    
    Model.CommandLine getCommandLine();
    
    Model.CommandLineOrBuilder getCommandLineOrBuilder();
    
    boolean hasFdPassingServiceUnixSocket();
    
    String getFdPassingServiceUnixSocket();
    
    ByteString getFdPassingServiceUnixSocketBytes();
}
