// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LLDBValueDataOrBuilder extends MessageOrBuilder
{
    boolean hasValue();
    
    String getValue();
    
    ByteString getValueBytes();
    
    boolean hasDescription();
    
    String getDescription();
    
    ByteString getDescriptionBytes();
    
    boolean hasHasLongerDescription();
    
    boolean getHasLongerDescription();
    
    boolean hasMayHaveChildren();
    
    boolean getMayHaveChildren();
    
    boolean hasIsSynthetic();
    
    boolean getIsSynthetic();
}
