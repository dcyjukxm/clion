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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueData_ReqOrBuilder
{
    private int bitField0_;
    private int id_;
    private int maxDescriptionLength_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$38900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$39000().ensureFieldAccessorsInitialized((Class)GetValueData_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (GetValueData_Req.access$39400()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.id_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.maxDescriptionLength_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$38900();
    }
    
    public GetValueData_Req getDefaultInstanceForType() {
        return GetValueData_Req.getDefaultInstance();
    }
    
    public GetValueData_Req build() {
        final GetValueData_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public GetValueData_Req buildPartial() {
        final GetValueData_Req getValueData_Req = new GetValueData_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        GetValueData_Req.access$39602(getValueData_Req, this.id_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        GetValueData_Req.access$39702(getValueData_Req, this.maxDescriptionLength_);
        GetValueData_Req.access$39802(getValueData_Req, n);
        this.onBuilt();
        return getValueData_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof GetValueData_Req) {
            return this.mergeFrom((GetValueData_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetValueData_Req getValueData_Req) {
        if (getValueData_Req == GetValueData_Req.getDefaultInstance()) {
            return this;
        }
        if (getValueData_Req.hasId()) {
            this.setId(getValueData_Req.getId());
        }
        if (getValueData_Req.hasMaxDescriptionLength()) {
            this.setMaxDescriptionLength(getValueData_Req.getMaxDescriptionLength());
        }
        this.mergeUnknownFields(getValueData_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasId() && this.hasMaxDescriptionLength();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetValueData_Req getValueData_Req = null;
        try {
            getValueData_Req = (GetValueData_Req)GetValueData_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getValueData_Req = (GetValueData_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getValueData_Req != null) {
                    this.mergeFrom(getValueData_Req);
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
    
    public boolean hasMaxDescriptionLength() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getMaxDescriptionLength() {
        return this.maxDescriptionLength_;
    }
    
    public Builder setMaxDescriptionLength(final int maxDescriptionLength_) {
        this.bitField0_ |= 0x2;
        this.maxDescriptionLength_ = maxDescriptionLength_;
        this.onChanged();
        return this;
    }
    
    public Builder clearMaxDescriptionLength() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.maxDescriptionLength_ = 0;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
