// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AttachByName_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasName();
    
    String getName();
    
    ByteString getNameBytes();
    
    boolean hasToWait();
    
    boolean getToWait();
}
