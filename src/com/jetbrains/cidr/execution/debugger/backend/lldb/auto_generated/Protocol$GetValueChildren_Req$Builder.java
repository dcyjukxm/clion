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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueChildren_ReqOrBuilder
{
    private int bitField0_;
    private int id_;
    private Object expression_;
    private int offset_;
    private int count_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$35500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$35600().ensureFieldAccessorsInitialized((Class)GetValueChildren_Req.class, (Class)Builder.class);
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
        if (GetValueChildren_Req.access$36000()) {}
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
        this.offset_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.count_ = 0;
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$35500();
    }
    
    public GetValueChildren_Req getDefaultInstanceForType() {
        return GetValueChildren_Req.getDefaultInstance();
    }
    
    public GetValueChildren_Req build() {
        final GetValueChildren_Req buildPartial = this.buildPartial();
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
    
    public GetValueChildren_Req buildPartial() {
        final GetValueChildren_Req getValueChildren_Req = new GetValueChildren_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        GetValueChildren_Req.access$36202(getValueChildren_Req, this.id_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        GetValueChildren_Req.access$36302(getValueChildren_Req, this.expression_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        GetValueChildren_Req.access$36402(getValueChildren_Req, this.offset_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        GetValueChildren_Req.access$36502(getValueChildren_Req, this.count_);
        GetValueChildren_Req.access$36602(getValueChildren_Req, n);
        this.onBuilt();
        return getValueChildren_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof GetValueChildren_Req) {
                return this.mergeFrom((GetValueChildren_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetValueChildren_Req getValueChildren_Req) {
        try {
            if (getValueChildren_Req == GetValueChildren_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (getValueChildren_Req.hasId()) {
                this.setId(getValueChildren_Req.getId());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (getValueChildren_Req.hasExpression()) {
                this.bitField0_ |= 0x2;
                this.expression_ = GetValueChildren_Req.access$36300(getValueChildren_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (getValueChildren_Req.hasOffset()) {
                this.setOffset(getValueChildren_Req.getOffset());
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (getValueChildren_Req.hasCount()) {
                this.setCount(getValueChildren_Req.getCount());
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        this.mergeUnknownFields(getValueChildren_Req.getUnknownFields());
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
        try {
            if (!this.hasOffset()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.hasCount()) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetValueChildren_Req getValueChildren_Req = null;
        try {
            getValueChildren_Req = (GetValueChildren_Req)GetValueChildren_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getValueChildren_Req = (GetValueChildren_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getValueChildren_Req != null) {
                    this.mergeFrom(getValueChildren_Req);
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
        this.expression_ = GetValueChildren_Req.getDefaultInstance().getExpression();
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
    
    public boolean hasOffset() {
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public int getOffset() {
        return this.offset_;
    }
    
    public Builder setOffset(final int offset_) {
        this.bitField0_ |= 0x4;
        this.offset_ = offset_;
        this.onChanged();
        return this;
    }
    
    public Builder clearOffset() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.offset_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasCount() {
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public int getCount() {
        return this.count_;
    }
    
    public Builder setCount(final int count_) {
        this.bitField0_ |= 0x8;
        this.count_ = count_;
        this.onChanged();
        return this;
    }
    
    public Builder clearCount() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.count_ = 0;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
