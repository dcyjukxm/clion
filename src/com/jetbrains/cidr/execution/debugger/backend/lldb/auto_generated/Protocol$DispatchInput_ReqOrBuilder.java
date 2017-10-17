// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface DispatchInput_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasInput();
    
    String getInput();
    
    ByteString getInputBytes();
    
    boolean hasTarget();
    
    Model.DispatchTarget getTarget();
}
