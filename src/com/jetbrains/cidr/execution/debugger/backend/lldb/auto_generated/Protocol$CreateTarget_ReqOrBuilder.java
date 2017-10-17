// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CreateTarget_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasExePath();
    
    String getExePath();
    
    ByteString getExePathBytes();
    
    boolean hasArch();
    
    String getArch();
    
    ByteString getArchBytes();
    
    boolean hasRemoteExePath();
    
    String getRemoteExePath();
    
    ByteString getRemoteExePathBytes();
    
    boolean hasPlatform();
    
    String getPlatform();
    
    ByteString getPlatformBytes();
    
    boolean hasPlatformSdkRoot();
    
    String getPlatformSdkRoot();
    
    ByteString getPlatformSdkRootBytes();
}
