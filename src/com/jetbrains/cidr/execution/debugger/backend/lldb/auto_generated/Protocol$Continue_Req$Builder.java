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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements Continue_ReqOrBuilder
{
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$12100();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$12200().ensureFieldAccessorsInitialized((Class)Continue_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (Continue_Req.access$12600()) {}
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
        return Protocol.access$12100();
    }
    
    public Continue_Req getDefaultInstanceForType() {
        return Continue_Req.getDefaultInstance();
    }
    
    public Continue_Req build() {
        final Continue_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public Continue_Req buildPartial() {
        final Continue_Req continue_Req = new Continue_Req((GeneratedMessage.Builder)this);
        this.onBuilt();
        return continue_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof Continue_Req) {
            return this.mergeFrom((Continue_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final Continue_Req continue_Req) {
        if (continue_Req == Continue_Req.getDefaultInstance()) {
            return this;
        }
        this.mergeUnknownFields(continue_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Continue_Req continue_Req = null;
        try {
            continue_Req = (Continue_Req)Continue_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            continue_Req = (Continue_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (continue_Req != null) {
                    this.mergeFrom(continue_Req);
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
