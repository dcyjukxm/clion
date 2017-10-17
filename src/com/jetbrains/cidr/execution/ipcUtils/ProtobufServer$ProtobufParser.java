// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.ipcUtils;

import java.io.IOException;
import com.google.protobuf.GeneratedMessage;

public interface ProtobufParser<T extends GeneratedMessage>
{
    T parse(final byte[] p0) throws IOException;
    
    boolean decompose(final GeneratedMessage p0);
}
