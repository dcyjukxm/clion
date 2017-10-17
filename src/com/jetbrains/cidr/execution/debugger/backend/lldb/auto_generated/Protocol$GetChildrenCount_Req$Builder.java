// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetChildrenCount_ReqOrBuilder
{
    private int bitField0_;
    private int id_;
    private Object expression_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$42900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$43000().ensureFieldAccessorsInitialized((Class)GetChildrenCount_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.expression_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.expression_ = "";
        this.b();
    }
    
    private void b() {
        if (GetChildrenCount_Req.access$43400()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.id_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.expression_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$42900();
    }
    
    public GetChildrenCount_Req getDefaultInstanceForType() {
        return GetChildrenCount_Req.getDefaultInstance();
    }
    
    public GetChildrenCount_Req build() {
        final GetChildrenCount_Req buildPartial = this.buildPartial();
        try {
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return buildPartial;
    }
    
    public GetChildrenCount_Req buildPartial() {
        final GetChildrenCount_Req getChildrenCount_Req = new GetChildrenCount_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        GetChildrenCount_Req.access$43602(getChildrenCount_Req, this.id_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        GetChildrenCount_Req.access$43702(getChildrenCount_Req, this.expression_);
        GetChildrenCount_Req.access$43802(getChildrenCount_Req, n);
        this.onBuilt();
        return getChildrenCount_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof GetChildrenCount_Req) {
                return this.mergeFrom((GetChildrenCount_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetChildrenCount_Req getChildrenCount_Req) {
        try {
            if (getChildrenCount_Req == GetChildrenCount_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (getChildrenCount_Req.hasId()) {
                this.setId(getChildrenCount_Req.getId());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (getChildrenCount_Req.hasExpression()) {
                this.bitField0_ |= 0x2;
                this.expression_ = GetChildrenCount_Req.access$43700(getChildrenCount_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(getChildrenCount_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasId()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetChildrenCount_Req getChildrenCount_Req = null;
        try {
            getChildrenCount_Req = (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getChildrenCount_Req = (GetChildrenCount_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getChildrenCount_Req != null) {
                    this.mergeFrom(getChildrenCount_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasId() {
        try {
            if ((this.bitField0_ & 0x1) == 0x1) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
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
    
    public boolean hasExpression() {
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getExpression() {
        final Object expression_ = this.expression_;
        if (!(expression_ instanceof String)) {
            return (String)(this.expression_ = ((ByteString)expression_).toStringUtf8());
        }
        return (String)expression_;
    }
    
    public ByteString getExpressionBytes() {
        final Object expression_ = this.expression_;
        if (expression_ instanceof String) {
            return (ByteString)(this.expression_ = ByteString.copyFromUtf8((String)expression_));
        }
        return (ByteString)expression_;
    }
    
    public Builder setExpression(final String expression_) {
        try {
            if (expression_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.expression_ = expression_;
        this.onChanged();
        return this;
    }
    
    public Builder clearExpression() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.expression_ = GetChildrenCount_Req.getDefaultInstance().getExpression();
        this.onChanged();
        return this;
    }
    
    public Builder setExpressionBytes(final ByteString expression_) {
        try {
            if (expression_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.expression_ = expression_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
