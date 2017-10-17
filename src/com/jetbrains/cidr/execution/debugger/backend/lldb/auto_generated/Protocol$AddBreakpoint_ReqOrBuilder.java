// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AddBreakpoint_ReqOrBuilder extends MessageOrBuilder
{
    boolean hasFile();
    
    String getFile();
    
    ByteString getFileBytes();
    
    boolean hasLine();
    
    int getLine();
    
    boolean hasCondition();
    
    String getCondition();
    
    ByteString getConditionBytes();
    
    boolean hasSymbolPattern();
    
    String getSymbolPattern();
    
    ByteString getSymbolPatternBytes();
    
    boolean hasModuleName();
    
    String getModuleName();
    
    ByteString getModuleNameBytes();
    
    boolean hasThreadId();
    
    int getThreadId();
    
    boolean hasRegexp();
    
    boolean getRegexp();
}
