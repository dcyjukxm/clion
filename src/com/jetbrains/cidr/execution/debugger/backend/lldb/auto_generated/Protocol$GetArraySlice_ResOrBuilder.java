// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import java.util.List;
import com.google.protobuf.MessageOrBuilder;

public interface GetArraySlice_ResOrBuilder extends MessageOrBuilder
{
    boolean hasCommonResponse();
    
    CommonResponse getCommonResponse();
    
    CommonResponseOrBuilder getCommonResponseOrBuilder();
    
    List<Model.LLDBValue> getValueList();
    
    Model.LLDBValue getValue(final int p0);
    
    int getValueCount();
    
    List<? extends Model.LLDBValueOrBuilder> getValueOrBuilderList();
    
    Model.LLDBValueOrBuilder getValueOrBuilder(final int p0);
}
