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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetVars_ReqOrBuilder
{
    private int bitField0_;
    private int threadId_;
    private int frameIndex_;
    private boolean statics_;
    private boolean globals_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$46000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$46100().ensureFieldAccessorsInitialized((Class)GetVars_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (GetVars_Req.access$46500()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.threadId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.frameIndex_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.statics_ = false;
        this.bitField0_ &= 0xFFFFFFFB;
        this.globals_ = false;
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$46000();
    }
    
    public GetVars_Req getDefaultInstanceForType() {
        return GetVars_Req.getDefaultInstance();
    }
    
    public GetVars_Req build() {
        final GetVars_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public GetVars_Req buildPartial() {
        final GetVars_Req getVars_Req = new GetVars_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        GetVars_Req.access$46702(getVars_Req, this.threadId_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        GetVars_Req.access$46802(getVars_Req, this.frameIndex_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        GetVars_Req.access$46902(getVars_Req, this.statics_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        GetVars_Req.access$47002(getVars_Req, this.globals_);
        GetVars_Req.access$47102(getVars_Req, n);
        this.onBuilt();
        return getVars_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof GetVars_Req) {
            return this.mergeFrom((GetVars_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetVars_Req getVars_Req) {
        if (getVars_Req == GetVars_Req.getDefaultInstance()) {
            return this;
        }
        if (getVars_Req.hasThreadId()) {
            this.setThreadId(getVars_Req.getThreadId());
        }
        if (getVars_Req.hasFrameIndex()) {
            this.setFrameIndex(getVars_Req.getFrameIndex());
        }
        if (getVars_Req.hasStatics()) {
            this.setStatics(getVars_Req.getStatics());
        }
        if (getVars_Req.hasGlobals()) {
            this.setGlobals(getVars_Req.getGlobals());
        }
        this.mergeUnknownFields(getVars_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasThreadId() && this.hasFrameIndex() && this.hasStatics() && this.hasGlobals();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetVars_Req getVars_Req = null;
        try {
            getVars_Req = (GetVars_Req)GetVars_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getVars_Req = (GetVars_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getVars_Req != null) {
                    this.mergeFrom(getVars_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    public boolean hasThreadId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    public Builder setThreadId(final int threadId_) {
        this.bitField0_ |= 0x1;
        this.threadId_ = threadId_;
        this.onChanged();
        return this;
    }
    
    public Builder clearThreadId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.threadId_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasFrameIndex() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getFrameIndex() {
        return this.frameIndex_;
    }
    
    public Builder setFrameIndex(final int frameIndex_) {
        this.bitField0_ |= 0x2;
        this.frameIndex_ = frameIndex_;
        this.onChanged();
        return this;
    }
    
    public Builder clearFrameIndex() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.frameIndex_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasStatics() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public boolean getStatics() {
        return this.statics_;
    }
    
    public Builder setStatics(final boolean statics_) {
        this.bitField0_ |= 0x4;
        this.statics_ = statics_;
        this.onChanged();
        return this;
    }
    
    public Builder clearStatics() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.statics_ = false;
        this.onChanged();
        return this;
    }
    
    public boolean hasGlobals() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public boolean getGlobals() {
        return this.globals_;
    }
    
    public Builder setGlobals(final boolean globals_) {
        this.bitField0_ |= 0x8;
        this.globals_ = globals_;
        this.onChanged();
        return this;
    }
    
    public Builder clearGlobals() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.globals_ = false;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
