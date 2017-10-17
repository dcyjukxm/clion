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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements EnvParamOrBuilder
{
    private int bitField0_;
    private Object name_;
    private Object value_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$800().ensureFieldAccessorsInitialized((Class)EnvParam.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.name_ = "";
        this.value_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.value_ = "";
        this.b();
    }
    
    private void b() {
        if (EnvParam.access$1200()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.value_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Model.access$700();
    }
    
    public EnvParam getDefaultInstanceForType() {
        return EnvParam.getDefaultInstance();
    }
    
    public EnvParam build() {
        final EnvParam buildPartial = this.buildPartial();
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
    
    public EnvParam buildPartial() {
        final EnvParam envParam = new EnvParam((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        EnvParam.access$1402(envParam, this.name_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        EnvParam.access$1502(envParam, this.value_);
        EnvParam.access$1602(envParam, n);
        this.onBuilt();
        return envParam;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof EnvParam) {
                return this.mergeFrom((EnvParam)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final EnvParam envParam) {
        try {
            if (envParam == EnvParam.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (envParam.hasName()) {
                this.bitField0_ |= 0x1;
                this.name_ = EnvParam.access$1400(envParam);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (envParam.hasValue()) {
                this.bitField0_ |= 0x2;
                this.value_ = EnvParam.access$1500(envParam);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(envParam.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasName()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasValue()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        EnvParam envParam = null;
        try {
            envParam = (EnvParam)EnvParam.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            envParam = (EnvParam)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (envParam != null) {
                    this.mergeFrom(envParam);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasName() {
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
    
    public String getName() {
        final Object name_ = this.name_;
        if (!(name_ instanceof String)) {
            return (String)(this.name_ = ((ByteString)name_).toStringUtf8());
        }
        return (String)name_;
    }
    
    public ByteString getNameBytes() {
        final Object name_ = this.name_;
        if (name_ instanceof String) {
            return (ByteString)(this.name_ = ByteString.copyFromUtf8((String)name_));
        }
        return (ByteString)name_;
    }
    
    public Builder setName(final String name_) {
        try {
            if (name_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.name_ = name_;
        this.onChanged();
        return this;
    }
    
    public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.name_ = EnvParam.getDefaultInstance().getName();
        this.onChanged();
        return this;
    }
    
    public Builder setNameBytes(final ByteString name_) {
        try {
            if (name_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.name_ = name_;
        this.onChanged();
        return this;
    }
    
    public boolean hasValue() {
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
    
    public String getValue() {
        final Object value_ = this.value_;
        if (!(value_ instanceof String)) {
            return (String)(this.value_ = ((ByteString)value_).toStringUtf8());
        }
        return (String)value_;
    }
    
    public ByteString getValueBytes() {
        final Object value_ = this.value_;
        if (value_ instanceof String) {
            return (ByteString)(this.value_ = ByteString.copyFromUtf8((String)value_));
        }
        return (ByteString)value_;
    }
    
    public Builder setValue(final String value_) {
        try {
            if (value_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.value_ = value_;
        this.onChanged();
        return this;
    }
    
    public Builder clearValue() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.value_ = EnvParam.getDefaultInstance().getValue();
        this.onChanged();
        return this;
    }
    
    public Builder setValueBytes(final ByteString value_) {
        try {
            if (value_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.value_ = value_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
