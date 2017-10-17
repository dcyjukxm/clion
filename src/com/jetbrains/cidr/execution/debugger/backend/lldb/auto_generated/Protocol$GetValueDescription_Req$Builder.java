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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueDescription_ReqOrBuilder
{
    private int bitField0_;
    private int id_;
    private int maxLength_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$40900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$41000().ensureFieldAccessorsInitialized((Class)GetValueDescription_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (GetValueDescription_Req.access$41400()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.id_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.maxLength_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$40900();
    }
    
    public GetValueDescription_Req getDefaultInstanceForType() {
        return GetValueDescription_Req.getDefaultInstance();
    }
    
    public GetValueDescription_Req build() {
        final GetValueDescription_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public GetValueDescription_Req buildPartial() {
        final GetValueDescription_Req getValueDescription_Req = new GetValueDescription_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        GetValueDescription_Req.access$41602(getValueDescription_Req, this.id_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        GetValueDescription_Req.access$41702(getValueDescription_Req, this.maxLength_);
        GetValueDescription_Req.access$41802(getValueDescription_Req, n);
        this.onBuilt();
        return getValueDescription_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof GetValueDescription_Req) {
            return this.mergeFrom((GetValueDescription_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetValueDescription_Req getValueDescription_Req) {
        if (getValueDescription_Req == GetValueDescription_Req.getDefaultInstance()) {
            return this;
        }
        if (getValueDescription_Req.hasId()) {
            this.setId(getValueDescription_Req.getId());
        }
        if (getValueDescription_Req.hasMaxLength()) {
            this.setMaxLength(getValueDescription_Req.getMaxLength());
        }
        this.mergeUnknownFields(getValueDescription_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasId() && this.hasMaxLength();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetValueDescription_Req getValueDescription_Req = null;
        try {
            getValueDescription_Req = (GetValueDescription_Req)GetValueDescription_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getValueDescription_Req = (GetValueDescription_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getValueDescription_Req != null) {
                    this.mergeFrom(getValueDescription_Req);
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
    
    public boolean hasMaxLength() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getMaxLength() {
        return this.maxLength_;
    }
    
    public Builder setMaxLength(final int maxLength_) {
        this.bitField0_ |= 0x2;
        this.maxLength_ = maxLength_;
        this.onChanged();
        return this;
    }
    
    public Builder clearMaxLength() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.maxLength_ = 0;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
