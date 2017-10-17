// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LLDBFrameOrBuilder extends MessageOrBuilder
{
    boolean hasNumber();
    
    int getNumber();
    
    boolean hasFunction();
    
    String getFunction();
    
    ByteString getFunctionBytes();
    
    boolean hasFile();
    
    String getFile();
    
    ByteString getFileBytes();
    
    boolean hasLine();
    
    int getLine();
    
    boolean hasPc();
    
    long getPc();
    
    boolean hasLanguage();
    
    Language getLanguage();
    
    boolean hasOptimized();
    
    boolean getOptimized();
}
