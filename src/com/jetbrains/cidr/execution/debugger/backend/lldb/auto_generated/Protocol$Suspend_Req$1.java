// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.AbstractParser;

static final class Protocol$Suspend_Req$1 extends AbstractParser<Suspend_Req> {
    public Suspend_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new Suspend_Req(codedInputStream, extensionRegistryLite);
    }
}