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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetArraySlice_ReqOrBuilder
{
    private int bitField0_;
    private int valueId_;
    private int offset_;
    private int count_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$36700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$36800().ensureFieldAccessorsInitialized((Class)GetArraySlice_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (GetArraySlice_Req.access$37200()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.valueId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.offset_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.count_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$36700();
    }
    
    public GetArraySlice_Req getDefaultInstanceForType() {
        return GetArraySlice_Req.getDefaultInstance();
    }
    
    public GetArraySlice_Req build() {
        final GetArraySlice_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public GetArraySlice_Req buildPartial() {
        final GetArraySlice_Req getArraySlice_Req = new GetArraySlice_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        GetArraySlice_Req.access$37402(getArraySlice_Req, this.valueId_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        GetArraySlice_Req.access$37502(getArraySlice_Req, this.offset_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        GetArraySlice_Req.access$37602(getArraySlice_Req, this.count_);
        GetArraySlice_Req.access$37702(getArraySlice_Req, n);
        this.onBuilt();
        return getArraySlice_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof GetArraySlice_Req) {
            return this.mergeFrom((GetArraySlice_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetArraySlice_Req getArraySlice_Req) {
        if (getArraySlice_Req == GetArraySlice_Req.getDefaultInstance()) {
            return this;
        }
        if (getArraySlice_Req.hasValueId()) {
            this.setValueId(getArraySlice_Req.getValueId());
        }
        if (getArraySlice_Req.hasOffset()) {
            this.setOffset(getArraySlice_Req.getOffset());
        }
        if (getArraySlice_Req.hasCount()) {
            this.setCount(getArraySlice_Req.getCount());
        }
        this.mergeUnknownFields(getArraySlice_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasValueId() && this.hasOffset() && this.hasCount();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetArraySlice_Req getArraySlice_Req = null;
        try {
            getArraySlice_Req = (GetArraySlice_Req)GetArraySlice_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getArraySlice_Req = (GetArraySlice_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getArraySlice_Req != null) {
                    this.mergeFrom(getArraySlice_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    public boolean hasValueId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getValueId() {
        return this.valueId_;
    }
    
    public Builder setValueId(final int valueId_) {
        this.bitField0_ |= 0x1;
        this.valueId_ = valueId_;
        this.onChanged();
        return this;
    }
    
    public Builder clearValueId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.valueId_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasOffset() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getOffset() {
        return this.offset_;
    }
    
    public Builder setOffset(final int offset_) {
        this.bitField0_ |= 0x2;
        this.offset_ = offset_;
        this.onChanged();
        return this;
    }
    
    public Builder clearOffset() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.offset_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasCount() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public int getCount() {
        return this.count_;
    }
    
    public Builder setCount(final int count_) {
        this.bitField0_ |= 0x4;
        this.count_ = count_;
        this.onChanged();
        return this;
    }
    
    public Builder clearCount() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.count_ = 0;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
