// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetThreads_ReqOrBuilder
{
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$15300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$15400().ensureFieldAccessorsInitialized((Class)GetThreads_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (GetThreads_Req.access$15800()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$15300();
    }
    
    public GetThreads_Req getDefaultInstanceForType() {
        return GetThreads_Req.getDefaultInstance();
    }
    
    public GetThreads_Req build() {
        final GetThreads_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public GetThreads_Req buildPartial() {
        final GetThreads_Req getThreads_Req = new GetThreads_Req((GeneratedMessage.Builder)this);
        this.onBuilt();
        return getThreads_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof GetThreads_Req) {
            return this.mergeFrom((GetThreads_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetThreads_Req getThreads_Req) {
        if (getThreads_Req == GetThreads_Req.getDefaultInstance()) {
            return this;
        }
        this.mergeUnknownFields(getThreads_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetThreads_Req getThreads_Req = null;
        try {
            getThreads_Req = (GetThreads_Req)GetThreads_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getThreads_Req = (GetThreads_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getThreads_Req != null) {
                    this.mergeFrom(getThreads_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
