// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AddWatchpoint_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasValueId();
    
    int getValueId();
    
    boolean hasToResolveLocation();
    
    int getToResolveLocation();
    
    boolean hasRead();
    
    int getRead();
    
    boolean hasWrite();
    
    int getWrite();
    
    boolean hasCondition();
    
    String getCondition();
    
    ByteString getConditionBytes();
}
