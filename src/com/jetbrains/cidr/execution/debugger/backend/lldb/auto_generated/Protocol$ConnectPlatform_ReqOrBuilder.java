// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ConnectPlatform_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasPlatform();
    
    String getPlatform();
    
    ByteString getPlatformBytes();
    
    boolean hasUrl();
    
    String getUrl();
    
    ByteString getUrlBytes();
}
