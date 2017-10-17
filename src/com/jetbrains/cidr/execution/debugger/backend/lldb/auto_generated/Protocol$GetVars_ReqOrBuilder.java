// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.MessageOrBuilder;

public interface GetVars_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasThreadId();
    
    int getThreadId();
    
    boolean hasFrameIndex();
    
    int getFrameIndex();
    
    boolean hasStatics();
    
    boolean getStatics();
    
    boolean hasGlobals();
    
    boolean getGlobals();
}
