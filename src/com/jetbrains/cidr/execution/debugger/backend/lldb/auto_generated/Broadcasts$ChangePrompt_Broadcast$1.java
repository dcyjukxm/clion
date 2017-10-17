// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.AbstractParser;

static final class Broadcasts$ChangePrompt_Broadcast$1 extends AbstractParser<ChangePrompt_Broadcast> {
    public ChangePrompt_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new ChangePrompt_Broadcast(codedInputStream, extensionRegistryLite);
    }
}