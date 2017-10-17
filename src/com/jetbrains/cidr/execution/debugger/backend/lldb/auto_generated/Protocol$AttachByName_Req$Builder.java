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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements AttachByName_ReqOrBuilder
{
    private int bitField0_;
    private Object name_;
    private boolean toWait_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$5200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$5300().ensureFieldAccessorsInitialized((Class)AttachByName_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.name_ = "";
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.a();
    }
    
    private void a() {
        if (AttachByName_Req.access$5700()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.toWait_ = false;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$5200();
    }
    
    public AttachByName_Req getDefaultInstanceForType() {
        return AttachByName_Req.getDefaultInstance();
    }
    
    public AttachByName_Req build() {
        final AttachByName_Req buildPartial = this.buildPartial();
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
    
    public AttachByName_Req buildPartial() {
        final AttachByName_Req attachByName_Req = new AttachByName_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        AttachByName_Req.access$5902(attachByName_Req, this.name_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        AttachByName_Req.access$6002(attachByName_Req, this.toWait_);
        AttachByName_Req.access$6102(attachByName_Req, n);
        this.onBuilt();
        return attachByName_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof AttachByName_Req) {
                return this.mergeFrom((AttachByName_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final AttachByName_Req attachByName_Req) {
        try {
            if (attachByName_Req == AttachByName_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (attachByName_Req.hasName()) {
                this.bitField0_ |= 0x1;
                this.name_ = AttachByName_Req.access$5900(attachByName_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (attachByName_Req.hasToWait()) {
                this.setToWait(attachByName_Req.getToWait());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(attachByName_Req.getUnknownFields());
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
            if (!this.hasToWait()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        AttachByName_Req attachByName_Req = null;
        try {
            attachByName_Req = (AttachByName_Req)AttachByName_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            attachByName_Req = (AttachByName_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (attachByName_Req != null) {
                    this.mergeFrom(attachByName_Req);
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
        this.name_ = AttachByName_Req.getDefaultInstance().getName();
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
    
    public boolean hasToWait() {
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
    
    public boolean getToWait() {
        return this.toWait_;
    }
    
    public Builder setToWait(final boolean toWait_) {
        this.bitField0_ |= 0x2;
        this.toWait_ = toWait_;
        this.onChanged();
        return this;
    }
    
    public Builder clearToWait() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.toWait_ = false;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
