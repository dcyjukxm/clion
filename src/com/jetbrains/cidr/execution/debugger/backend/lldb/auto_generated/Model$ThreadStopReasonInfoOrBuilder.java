// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ThreadStopReasonInfoOrBuilder extends MessageOrBuilder
{
    boolean hasStopReason();
    
    ThreadStopReason getStopReason();
    
    boolean hasStopDescription();
    
    String getStopDescription();
    
    ByteString getStopDescriptionBytes();
    
    boolean hasSignal();
    
    int getSignal();
    
    boolean hasSignalName();
    
    String getSignalName();
    
    ByteString getSignalNameBytes();
    
    boolean hasCodepointId();
    
    int getCodepointId();
}
