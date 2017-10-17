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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetFrames_ReqOrBuilder
{
    private int bitField0_;
    private int threadId_;
    private int fromIndex_;
    private int count_;
    private boolean untilValidLineEntry_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$17100();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$17200().ensureFieldAccessorsInitialized((Class)GetFrames_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (GetFrames_Req.access$17600()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.threadId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.fromIndex_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.count_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.untilValidLineEntry_ = false;
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$17100();
    }
    
    public GetFrames_Req getDefaultInstanceForType() {
        return GetFrames_Req.getDefaultInstance();
    }
    
    public GetFrames_Req build() {
        final GetFrames_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public GetFrames_Req buildPartial() {
        final GetFrames_Req getFrames_Req = new GetFrames_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        GetFrames_Req.access$17802(getFrames_Req, this.threadId_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        GetFrames_Req.access$17902(getFrames_Req, this.fromIndex_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        GetFrames_Req.access$18002(getFrames_Req, this.count_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        GetFrames_Req.access$18102(getFrames_Req, this.untilValidLineEntry_);
        GetFrames_Req.access$18202(getFrames_Req, n);
        this.onBuilt();
        return getFrames_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof GetFrames_Req) {
            return this.mergeFrom((GetFrames_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetFrames_Req getFrames_Req) {
        if (getFrames_Req == GetFrames_Req.getDefaultInstance()) {
            return this;
        }
        if (getFrames_Req.hasThreadId()) {
            this.setThreadId(getFrames_Req.getThreadId());
        }
        if (getFrames_Req.hasFromIndex()) {
            this.setFromIndex(getFrames_Req.getFromIndex());
        }
        if (getFrames_Req.hasCount()) {
            this.setCount(getFrames_Req.getCount());
        }
        if (getFrames_Req.hasUntilValidLineEntry()) {
            this.setUntilValidLineEntry(getFrames_Req.getUntilValidLineEntry());
        }
        this.mergeUnknownFields(getFrames_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasThreadId() && this.hasFromIndex() && this.hasCount() && this.hasUntilValidLineEntry();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        GetFrames_Req getFrames_Req = null;
        try {
            getFrames_Req = (GetFrames_Req)GetFrames_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getFrames_Req = (GetFrames_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getFrames_Req != null) {
                    this.mergeFrom(getFrames_Req);
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
    
    public boolean hasFromIndex() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getFromIndex() {
        return this.fromIndex_;
    }
    
    public Builder setFromIndex(final int fromIndex_) {
        this.bitField0_ |= 0x2;
        this.fromIndex_ = fromIndex_;
        this.onChanged();
        return this;
    }
    
    public Builder clearFromIndex() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.fromIndex_ = 0;
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
    
    public boolean hasUntilValidLineEntry() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public boolean getUntilValidLineEntry() {
        return this.untilValidLineEntry_;
    }
    
    public Builder setUntilValidLineEntry(final boolean untilValidLineEntry_) {
        this.bitField0_ |= 0x8;
        this.untilValidLineEntry_ = untilValidLineEntry_;
        this.onChanged();
        return this;
    }
    
    public Builder clearUntilValidLineEntry() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.untilValidLineEntry_ = false;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
