// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CommonResponseOrBuilder extends MessageOrBuilder
{
    boolean hasIsValid();
    
    boolean getIsValid();
    
    boolean hasErrorMessage();
    
    String getErrorMessage();
    
    ByteString getErrorMessageBytes();
}
