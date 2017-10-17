// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.AbstractParser;

static final class Broadcasts$CommandsInterpreter_Broadcast$1 extends AbstractParser<CommandsInterpreter_Broadcast> {
    public CommandsInterpreter_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new CommandsInterpreter_Broadcast(codedInputStream, extensionRegistryLite);
    }
}