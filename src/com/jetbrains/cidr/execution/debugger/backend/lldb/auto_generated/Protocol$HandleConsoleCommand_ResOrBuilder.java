// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface HandleConsoleCommand_ResOrBuilder extends MessageOrBuilder
{
    boolean hasCommonResponse();
    
    CommonResponse getCommonResponse();
    
    CommonResponseOrBuilder getCommonResponseOrBuilder();
    
    boolean hasSuccess();
    
    int getSuccess();
    
    boolean hasErr();
    
    String getErr();
    
    ByteString getErrBytes();
    
    boolean hasOut();
    
    String getOut();
    
    ByteString getOutBytes();
}
