// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.MessageOrBuilder;

public interface GetFrames_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasThreadId();
    
    int getThreadId();
    
    boolean hasFromIndex();
    
    int getFromIndex();
    
    boolean hasCount();
    
    int getCount();
    
    boolean hasUntilValidLineEntry();
    
    boolean getUntilValidLineEntry();
}
