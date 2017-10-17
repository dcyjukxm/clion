// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import java.util.List;
import com.google.protobuf.MessageOrBuilder;

public interface GetThreads_ResOrBuilder extends MessageOrBuilder
{
    boolean hasCommonResponse();
    
    CommonResponse getCommonResponse();
    
    CommonResponseOrBuilder getCommonResponseOrBuilder();
    
    List<Model.LLDBThread> getThreadList();
    
    Model.LLDBThread getThread(final int p0);
    
    int getThreadCount();
    
    List<? extends Model.LLDBThreadOrBuilder> getThreadOrBuilderList();
    
    Model.LLDBThreadOrBuilder getThreadOrBuilder(final int p0);
}
