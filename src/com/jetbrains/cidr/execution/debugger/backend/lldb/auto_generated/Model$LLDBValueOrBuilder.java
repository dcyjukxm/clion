// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LLDBValueOrBuilder extends MessageOrBuilder
{
    boolean hasId();
    
    int getId();
    
    boolean hasName();
    
    String getName();
    
    ByteString getNameBytes();
    
    boolean hasType();
    
    String getType();
    
    ByteString getTypeBytes();
    
    boolean hasTypeClass();
    
    TypeClass getTypeClass();
}
