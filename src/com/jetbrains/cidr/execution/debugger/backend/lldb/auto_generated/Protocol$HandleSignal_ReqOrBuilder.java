// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface HandleSignal_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasSignal();
    
    String getSignal();
    
    ByteString getSignalBytes();
    
    boolean hasStop();
    
    boolean getStop();
    
    boolean hasPass();
    
    boolean getPass();
    
    boolean hasNotify();
    
    boolean getNotify();
}
