// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import java.util.List;
import com.google.protobuf.MessageOrBuilder;

public interface HandleCompletion_ResOrBuilder extends MessageOrBuilder
{
    boolean hasCommonResponse();
    
    CommonResponse getCommonResponse();
    
    CommonResponseOrBuilder getCommonResponseOrBuilder();
    
    List<String> getCompletionList();
    
    int getCompletionCount();
    
    String getCompletion(final int p0);
    
    ByteString getCompletionBytes(final int p0);
}
