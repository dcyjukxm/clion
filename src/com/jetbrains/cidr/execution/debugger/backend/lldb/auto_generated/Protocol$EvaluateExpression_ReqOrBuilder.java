// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface EvaluateExpression_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasExpression();
    
    String getExpression();
    
    ByteString getExpressionBytes();
    
    boolean hasThreadId();
    
    int getThreadId();
    
    boolean hasFrameIndex();
    
    int getFrameIndex();
    
    boolean hasLanguage();
    
    Model.Language getLanguage();
}
