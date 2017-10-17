// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import java.util.List;
import com.google.protobuf.MessageOrBuilder;

public interface GetFrames_ResOrBuilder extends MessageOrBuilder
{
    boolean hasCommonResponse();
    
    CommonResponse getCommonResponse();
    
    CommonResponseOrBuilder getCommonResponseOrBuilder();
    
    List<Model.LLDBFrame> getFrameList();
    
    Model.LLDBFrame getFrame(final int p0);
    
    int getFrameCount();
    
    List<? extends Model.LLDBFrameOrBuilder> getFrameOrBuilderList();
    
    Model.LLDBFrameOrBuilder getFrameOrBuilder(final int p0);
    
    boolean hasHasMore();
    
    boolean getHasMore();
}
