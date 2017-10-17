// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.MessageOrBuilder;

public interface EvaluateExpression_ResOrBuilder extends MessageOrBuilder
{
    boolean hasCommonResponse();
    
    CommonResponse getCommonResponse();
    
    CommonResponseOrBuilder getCommonResponseOrBuilder();
    
    boolean hasResult();
    
    Model.LLDBValue getResult();
    
    Model.LLDBValueOrBuilder getResultOrBuilder();
}
