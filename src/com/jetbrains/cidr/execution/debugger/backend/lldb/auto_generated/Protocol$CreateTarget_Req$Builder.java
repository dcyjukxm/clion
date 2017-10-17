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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements CreateTarget_ReqOrBuilder
{
    private int bitField0_;
    private Object exePath_;
    private Object arch_;
    private Object remoteExePath_;
    private Object platform_;
    private Object platformSdkRoot_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$1000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$1100().ensureFieldAccessorsInitialized((Class)CreateTarget_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.exePath_ = "";
        this.arch_ = "";
        this.remoteExePath_ = "";
        this.platform_ = "";
        this.platformSdkRoot_ = "";
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.exePath_ = "";
        this.arch_ = "";
        this.remoteExePath_ = "";
        this.platform_ = "";
        this.platformSdkRoot_ = "";
        this.a();
    }
    
    private void a() {
        if (CreateTarget_Req.access$1500()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.exePath_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.arch_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.remoteExePath_ = "";
        this.bitField0_ &= 0xFFFFFFFB;
        this.platform_ = "";
        this.bitField0_ &= 0xFFFFFFF7;
        this.platformSdkRoot_ = "";
        this.bitField0_ &= 0xFFFFFFEF;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$1000();
    }
    
    public CreateTarget_Req getDefaultInstanceForType() {
        return CreateTarget_Req.getDefaultInstance();
    }
    
    public CreateTarget_Req build() {
        final CreateTarget_Req buildPartial = this.buildPartial();
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
    
    public CreateTarget_Req buildPartial() {
        final CreateTarget_Req createTarget_Req = new CreateTarget_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        CreateTarget_Req.access$1702(createTarget_Req, this.exePath_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        CreateTarget_Req.access$1802(createTarget_Req, this.arch_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        CreateTarget_Req.access$1902(createTarget_Req, this.remoteExePath_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        CreateTarget_Req.access$2002(createTarget_Req, this.platform_);
        if ((bitField0_ & 0x10) == 0x10) {
            n |= 0x10;
        }
        CreateTarget_Req.access$2102(createTarget_Req, this.platformSdkRoot_);
        CreateTarget_Req.access$2202(createTarget_Req, n);
        this.onBuilt();
        return createTarget_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof CreateTarget_Req) {
                return this.mergeFrom((CreateTarget_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final CreateTarget_Req createTarget_Req) {
        try {
            if (createTarget_Req == CreateTarget_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (createTarget_Req.hasExePath()) {
                this.bitField0_ |= 0x1;
                this.exePath_ = CreateTarget_Req.access$1700(createTarget_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (createTarget_Req.hasArch()) {
                this.bitField0_ |= 0x2;
                this.arch_ = CreateTarget_Req.access$1800(createTarget_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (createTarget_Req.hasRemoteExePath()) {
                this.bitField0_ |= 0x4;
                this.remoteExePath_ = CreateTarget_Req.access$1900(createTarget_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (createTarget_Req.hasPlatform()) {
                this.bitField0_ |= 0x8;
                this.platform_ = CreateTarget_Req.access$2000(createTarget_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        try {
            if (createTarget_Req.hasPlatformSdkRoot()) {
                this.bitField0_ |= 0x10;
                this.platformSdkRoot_ = CreateTarget_Req.access$2100(createTarget_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex6) {
            throw b(ex6);
        }
        this.mergeUnknownFields(createTarget_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        CreateTarget_Req createTarget_Req = null;
        try {
            createTarget_Req = (CreateTarget_Req)CreateTarget_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            createTarget_Req = (CreateTarget_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (createTarget_Req != null) {
                    this.mergeFrom(createTarget_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasExePath() {
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
    
    public String getExePath() {
        final Object exePath_ = this.exePath_;
        if (!(exePath_ instanceof String)) {
            return (String)(this.exePath_ = ((ByteString)exePath_).toStringUtf8());
        }
        return (String)exePath_;
    }
    
    public ByteString getExePathBytes() {
        final Object exePath_ = this.exePath_;
        if (exePath_ instanceof String) {
            return (ByteString)(this.exePath_ = ByteString.copyFromUtf8((String)exePath_));
        }
        return (ByteString)exePath_;
    }
    
    public Builder setExePath(final String exePath_) {
        try {
            if (exePath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.exePath_ = exePath_;
        this.onChanged();
        return this;
    }
    
    public Builder clearExePath() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.exePath_ = CreateTarget_Req.getDefaultInstance().getExePath();
        this.onChanged();
        return this;
    }
    
    public Builder setExePathBytes(final ByteString exePath_) {
        try {
            if (exePath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.exePath_ = exePath_;
        this.onChanged();
        return this;
    }
    
    public boolean hasArch() {
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
    
    public String getArch() {
        final Object arch_ = this.arch_;
        if (!(arch_ instanceof String)) {
            return (String)(this.arch_ = ((ByteString)arch_).toStringUtf8());
        }
        return (String)arch_;
    }
    
    public ByteString getArchBytes() {
        final Object arch_ = this.arch_;
        if (arch_ instanceof String) {
            return (ByteString)(this.arch_ = ByteString.copyFromUtf8((String)arch_));
        }
        return (ByteString)arch_;
    }
    
    public Builder setArch(final String arch_) {
        try {
            if (arch_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.arch_ = arch_;
        this.onChanged();
        return this;
    }
    
    public Builder clearArch() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.arch_ = CreateTarget_Req.getDefaultInstance().getArch();
        this.onChanged();
        return this;
    }
    
    public Builder setArchBytes(final ByteString arch_) {
        try {
            if (arch_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.arch_ = arch_;
        this.onChanged();
        return this;
    }
    
    public boolean hasRemoteExePath() {
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
    
    public String getRemoteExePath() {
        final Object remoteExePath_ = this.remoteExePath_;
        if (!(remoteExePath_ instanceof String)) {
            return (String)(this.remoteExePath_ = ((ByteString)remoteExePath_).toStringUtf8());
        }
        return (String)remoteExePath_;
    }
    
    public ByteString getRemoteExePathBytes() {
        final Object remoteExePath_ = this.remoteExePath_;
        if (remoteExePath_ instanceof String) {
            return (ByteString)(this.remoteExePath_ = ByteString.copyFromUtf8((String)remoteExePath_));
        }
        return (ByteString)remoteExePath_;
    }
    
    public Builder setRemoteExePath(final String remoteExePath_) {
        try {
            if (remoteExePath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.remoteExePath_ = remoteExePath_;
        this.onChanged();
        return this;
    }
    
    public Builder clearRemoteExePath() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.remoteExePath_ = CreateTarget_Req.getDefaultInstance().getRemoteExePath();
        this.onChanged();
        return this;
    }
    
    public Builder setRemoteExePathBytes(final ByteString remoteExePath_) {
        try {
            if (remoteExePath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.remoteExePath_ = remoteExePath_;
        this.onChanged();
        return this;
    }
    
    public boolean hasPlatform() {
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
        this.bitField0_ |= 0x8;
        this.platform_ = platform_;
        this.onChanged();
        return this;
    }
    
    public Builder clearPlatform() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.platform_ = CreateTarget_Req.getDefaultInstance().getPlatform();
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
        this.bitField0_ |= 0x8;
        this.platform_ = platform_;
        this.onChanged();
        return this;
    }
    
    public boolean hasPlatformSdkRoot() {
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getPlatformSdkRoot() {
        final Object platformSdkRoot_ = this.platformSdkRoot_;
        if (!(platformSdkRoot_ instanceof String)) {
            return (String)(this.platformSdkRoot_ = ((ByteString)platformSdkRoot_).toStringUtf8());
        }
        return (String)platformSdkRoot_;
    }
    
    public ByteString getPlatformSdkRootBytes() {
        final Object platformSdkRoot_ = this.platformSdkRoot_;
        if (platformSdkRoot_ instanceof String) {
            return (ByteString)(this.platformSdkRoot_ = ByteString.copyFromUtf8((String)platformSdkRoot_));
        }
        return (ByteString)platformSdkRoot_;
    }
    
    public Builder setPlatformSdkRoot(final String platformSdkRoot_) {
        try {
            if (platformSdkRoot_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x10;
        this.platformSdkRoot_ = platformSdkRoot_;
        this.onChanged();
        return this;
    }
    
    public Builder clearPlatformSdkRoot() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.platformSdkRoot_ = CreateTarget_Req.getDefaultInstance().getPlatformSdkRoot();
        this.onChanged();
        return this;
    }
    
    public Builder setPlatformSdkRootBytes(final ByteString platformSdkRoot_) {
        try {
            if (platformSdkRoot_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x10;
        this.platformSdkRoot_ = platformSdkRoot_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
