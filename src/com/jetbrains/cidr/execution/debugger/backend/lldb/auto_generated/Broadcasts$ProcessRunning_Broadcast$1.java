// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.AbstractParser;

static final class Broadcasts$ProcessRunning_Broadcast$1 extends AbstractParser<ProcessRunning_Broadcast> {
    public ProcessRunning_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new ProcessRunning_Broadcast(codedInputStream, extensionRegistryLite);
    }
}