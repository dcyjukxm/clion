// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.ByteString;
import java.util.List;
import com.google.protobuf.MessageOrBuilder;

public interface CommandLineOrBuilder extends MessageOrBuilder
{
    List<EnvParam> getEnvList();
    
    EnvParam getEnv(final int p0);
    
    int getEnvCount();
    
    List<? extends EnvParamOrBuilder> getEnvOrBuilderList();
    
    EnvParamOrBuilder getEnvOrBuilder(final int p0);
    
    boolean hasExePath();
    
    String getExePath();
    
    ByteString getExePathBytes();
    
    boolean hasWorkingDir();
    
    String getWorkingDir();
    
    ByteString getWorkingDirBytes();
    
    List<String> getParamList();
    
    int getParamCount();
    
    String getParam(final int p0);
    
    ByteString getParamBytes(final int p0);
    
    boolean hasStdinPath();
    
    String getStdinPath();
    
    ByteString getStdinPathBytes();
    
    boolean hasStdoutPath();
    
    String getStdoutPath();
    
    ByteString getStdoutPathBytes();
    
    boolean hasStderrPath();
    
    String getStderrPath();
    
    ByteString getStderrPathBytes();
}
