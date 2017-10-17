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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements CommonResponseOrBuilder
{
    private int bitField0_;
    private boolean isValid_;
    private Object errorMessage_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$100().ensureFieldAccessorsInitialized((Class)CommonResponse.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.errorMessage_ = "";
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.errorMessage_ = "";
        this.a();
    }
    
    private void a() {
        if (CommonResponse.access$500()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.isValid_ = false;
        this.bitField0_ &= 0xFFFFFFFE;
        this.errorMessage_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$000();
    }
    
    public CommonResponse getDefaultInstanceForType() {
        return CommonResponse.getDefaultInstance();
    }
    
    public CommonResponse build() {
        final CommonResponse buildPartial = this.buildPartial();
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
    
    public CommonResponse buildPartial() {
        final CommonResponse commonResponse = new CommonResponse((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        CommonResponse.access$702(commonResponse, this.isValid_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        CommonResponse.access$802(commonResponse, this.errorMessage_);
        CommonResponse.access$902(commonResponse, n);
        this.onBuilt();
        return commonResponse;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof CommonResponse) {
                return this.mergeFrom((CommonResponse)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final CommonResponse commonResponse) {
        try {
            if (commonResponse == CommonResponse.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (commonResponse.hasIsValid()) {
                this.setIsValid(commonResponse.getIsValid());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (commonResponse.hasErrorMessage()) {
                this.bitField0_ |= 0x2;
                this.errorMessage_ = CommonResponse.access$800(commonResponse);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(commonResponse.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasIsValid()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        CommonResponse commonResponse = null;
        try {
            commonResponse = (CommonResponse)CommonResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            commonResponse = (CommonResponse)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (commonResponse != null) {
                    this.mergeFrom(commonResponse);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasIsValid() {
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
    
    public boolean getIsValid() {
        return this.isValid_;
    }
    
    public Builder setIsValid(final boolean isValid_) {
        this.bitField0_ |= 0x1;
        this.isValid_ = isValid_;
        this.onChanged();
        return this;
    }
    
    public Builder clearIsValid() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.isValid_ = false;
        this.onChanged();
        return this;
    }
    
    public boolean hasErrorMessage() {
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
    
    public String getErrorMessage() {
        final Object errorMessage_ = this.errorMessage_;
        if (!(errorMessage_ instanceof String)) {
            return (String)(this.errorMessage_ = ((ByteString)errorMessage_).toStringUtf8());
        }
        return (String)errorMessage_;
    }
    
    public ByteString getErrorMessageBytes() {
        final Object errorMessage_ = this.errorMessage_;
        if (errorMessage_ instanceof String) {
            return (ByteString)(this.errorMessage_ = ByteString.copyFromUtf8((String)errorMessage_));
        }
        return (ByteString)errorMessage_;
    }
    
    public Builder setErrorMessage(final String errorMessage_) {
        try {
            if (errorMessage_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.errorMessage_ = errorMessage_;
        this.onChanged();
        return this;
    }
    
    public Builder clearErrorMessage() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.errorMessage_ = CommonResponse.getDefaultInstance().getErrorMessage();
        this.onChanged();
        return this;
    }
    
    public Builder setErrorMessageBytes(final ByteString errorMessage_) {
        try {
            if (errorMessage_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.errorMessage_ = errorMessage_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
