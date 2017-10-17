// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Broadcasts;
import com.google.protobuf.GeneratedMessage;
import java.io.IOException;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Protocol;
import com.jetbrains.cidr.execution.ipcUtils.ProtobufServer;

class LLDBDriver$2 implements ProtobufServer.ProtobufParser<Protocol.CompositeResponse> {
    @Override
    public Protocol.CompositeResponse parse(final byte[] array) throws IOException {
        return Protocol.CompositeResponse.parseFrom(array);
    }
    
    @Override
    public boolean decompose(final GeneratedMessage generatedMessage) {
        return generatedMessage instanceof Protocol.CompositeResponse || generatedMessage instanceof Broadcasts.CompositeBroadcast;
    }
}