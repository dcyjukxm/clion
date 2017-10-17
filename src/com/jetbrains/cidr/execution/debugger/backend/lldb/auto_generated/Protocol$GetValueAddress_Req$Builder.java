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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueAddress_ReqOrBuilder
{
    private int bitField0_;
    private int id_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$58900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$59000().ensureFieldAccessorsInitialized((Class)GetValueAddress_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (GetValueAddress_Req.access$59400()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.id_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$58900();
    }
    
    public GetValueAddress_Req getDefaultInstanceForType() {
        return GetValueAddress_Req.getDefaultInstance();
    }
    
    public GetValueAddress_Req build() {
        final GetValueAddress_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public GetValueAddress_Req buildPartial() {
        final GetValueAddress_Req getValueAddress_Req = new GetValueAddress_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        GetValueAddress_Req.access$59602(getValueAddress_Req, this.id_);
        GetValueAddress_Req.access$59702(getValueAddress_Req, n);
        this.onBuilt();
        return getValueAddress_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof GetValueAddress_Req) {
            return this.mergeFrom((GetValueAddress_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetValueAddress_Req getValueAddress_Req) {
        if (getValueAddress_Req == GetValueAddress_Req.getDefaultInstance()) {
            return this;
        }
        if (getValueAddress_Req.hasId()) {
            this.setId(getValueAddress_Req.getId());
        }
        this.mergeUnknownFields(getValueAddress_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasId();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetValueAddress_Req getValueAddress_Req = null;
        try {
            getValueAddress_Req = (GetValueAddress_Req)GetValueAddress_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getValueAddress_Req = (GetValueAddress_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getValueAddress_Req != null) {
                    this.mergeFrom(getValueAddress_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    public boolean hasId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getId() {
        return this.id_;
    }
    
    public Builder setId(final int id_) {
        this.bitField0_ |= 0x1;
        this.id_ = id_;
        this.onChanged();
        return this;
    }
    
    public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
