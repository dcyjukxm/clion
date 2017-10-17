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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ValuesFilteringPolicy_ReqOrBuilder
{
    private int bitField0_;
    private int filterEnabled_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$54500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$54600().ensureFieldAccessorsInitialized((Class)ValuesFilteringPolicy_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (ValuesFilteringPolicy_Req.access$55000()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.filterEnabled_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$54500();
    }
    
    public ValuesFilteringPolicy_Req getDefaultInstanceForType() {
        return ValuesFilteringPolicy_Req.getDefaultInstance();
    }
    
    public ValuesFilteringPolicy_Req build() {
        final ValuesFilteringPolicy_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public ValuesFilteringPolicy_Req buildPartial() {
        final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req = new ValuesFilteringPolicy_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        ValuesFilteringPolicy_Req.access$55202(valuesFilteringPolicy_Req, this.filterEnabled_);
        ValuesFilteringPolicy_Req.access$55302(valuesFilteringPolicy_Req, n);
        this.onBuilt();
        return valuesFilteringPolicy_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof ValuesFilteringPolicy_Req) {
            return this.mergeFrom((ValuesFilteringPolicy_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req) {
        if (valuesFilteringPolicy_Req == ValuesFilteringPolicy_Req.getDefaultInstance()) {
            return this;
        }
        if (valuesFilteringPolicy_Req.hasFilterEnabled()) {
            this.setFilterEnabled(valuesFilteringPolicy_Req.getFilterEnabled());
        }
        this.mergeUnknownFields(valuesFilteringPolicy_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasFilterEnabled();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ValuesFilteringPolicy_Req valuesFilteringPolicy_Req = null;
        try {
            valuesFilteringPolicy_Req = (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            valuesFilteringPolicy_Req = (ValuesFilteringPolicy_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (valuesFilteringPolicy_Req != null) {
                    this.mergeFrom(valuesFilteringPolicy_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    public boolean hasFilterEnabled() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getFilterEnabled() {
        return this.filterEnabled_;
    }
    
    public Builder setFilterEnabled(final int filterEnabled_) {
        this.bitField0_ |= 0x1;
        this.filterEnabled_ = filterEnabled_;
        this.onChanged();
        return this;
    }
    
    public Builder clearFilterEnabled() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.filterEnabled_ = 0;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
