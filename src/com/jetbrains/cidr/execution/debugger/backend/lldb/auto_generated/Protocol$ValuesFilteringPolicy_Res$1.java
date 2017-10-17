// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.AbstractParser;

static final class Protocol$ValuesFilteringPolicy_Res$1 extends AbstractParser<ValuesFilteringPolicy_Res> {
    public ValuesFilteringPolicy_Res parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return new ValuesFilteringPolicy_Res(codedInputStream, extensionRegistryLite);
    }
}