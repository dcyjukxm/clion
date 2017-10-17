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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ConnectPlatform_ReqOrBuilder
{
    private int bitField0_;
    private Object platform_;
    private Object url_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$56300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$56400().ensureFieldAccessorsInitialized((Class)ConnectPlatform_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.platform_ = "";
        this.url_ = "";
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.platform_ = "";
        this.url_ = "";
        this.a();
    }
    
    private void a() {
        if (ConnectPlatform_Req.access$56800()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.platform_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.url_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$56300();
    }
    
    public ConnectPlatform_Req getDefaultInstanceForType() {
        return ConnectPlatform_Req.getDefaultInstance();
    }
    
    public ConnectPlatform_Req build() {
        final ConnectPlatform_Req buildPartial = this.buildPartial();
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
    
    public ConnectPlatform_Req buildPartial() {
        final ConnectPlatform_Req connectPlatform_Req = new ConnectPlatform_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        ConnectPlatform_Req.access$57002(connectPlatform_Req, this.platform_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        ConnectPlatform_Req.access$57102(connectPlatform_Req, this.url_);
        ConnectPlatform_Req.access$57202(connectPlatform_Req, n);
        this.onBuilt();
        return connectPlatform_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof ConnectPlatform_Req) {
                return this.mergeFrom((ConnectPlatform_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ConnectPlatform_Req connectPlatform_Req) {
        try {
            if (connectPlatform_Req == ConnectPlatform_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (connectPlatform_Req.hasPlatform()) {
                this.bitField0_ |= 0x1;
                this.platform_ = ConnectPlatform_Req.access$57000(connectPlatform_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (connectPlatform_Req.hasUrl()) {
                this.bitField0_ |= 0x2;
                this.url_ = ConnectPlatform_Req.access$57100(connectPlatform_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(connectPlatform_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasPlatform()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasUrl()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ConnectPlatform_Req connectPlatform_Req = null;
        try {
            connectPlatform_Req = (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            connectPlatform_Req = (ConnectPlatform_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (connectPlatform_Req != null) {
                    this.mergeFrom(connectPlatform_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasPlatform() {
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
    
    public String getPlatform() {
        final Object platform_ = this.platform_;
        if (!(platform_ instanceof String)) {
            return (String)(this.platform_ = ((ByteString)platform_).toStringUtf8());
        }
        return (String)platform_;
    }
    
    public ByteString getPlatformBytes() {
        final Object platform_ = this.platform_;
        if (platform_ instanceof String) {
            return (ByteString)(this.platform_ = ByteString.copyFromUtf8((String)platform_));
        }
        return (ByteString)platform_;
    }
    
    public Builder setPlatform(final String platform_) {
        try {
            if (platform_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.platform_ = platform_;
        this.onChanged();
        return this;
    }
    
    public Builder clearPlatform() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.platform_ = ConnectPlatform_Req.getDefaultInstance().getPlatform();
        this.onChanged();
        return this;
    }
    
    public Builder setPlatformBytes(final ByteString platform_) {
        try {
            if (platform_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.platform_ = platform_;
        this.onChanged();
        return this;
    }
    
    public boolean hasUrl() {
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
    
    public String getUrl() {
        final Object url_ = this.url_;
        if (!(url_ instanceof String)) {
            return (String)(this.url_ = ((ByteString)url_).toStringUtf8());
        }
        return (String)url_;
    }
    
    public ByteString getUrlBytes() {
        final Object url_ = this.url_;
        if (url_ instanceof String) {
            return (ByteString)(this.url_ = ByteString.copyFromUtf8((String)url_));
        }
        return (ByteString)url_;
    }
    
    public Builder setUrl(final String url_) {
        try {
            if (url_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.url_ = url_;
        this.onChanged();
        return this;
    }
    
    public Builder clearUrl() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.url_ = ConnectPlatform_Req.getDefaultInstance().getUrl();
        this.onChanged();
        return this;
    }
    
    public Builder setUrlBytes(final ByteString url_) {
        try {
            if (url_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.url_ = url_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
