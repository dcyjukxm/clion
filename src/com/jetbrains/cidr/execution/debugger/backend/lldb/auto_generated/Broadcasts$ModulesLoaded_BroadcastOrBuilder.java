// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import java.util.List;
import com.google.protobuf.MessageOrBuilder;

public interface ModulesLoaded_BroadcastOrBuilder extends MessageOrBuilder
{
    List<String> getModulesList();
    
    int getModulesCount();
    
    String getModules(final int p0);
    
    ByteString getModulesBytes(final int p0);
}
