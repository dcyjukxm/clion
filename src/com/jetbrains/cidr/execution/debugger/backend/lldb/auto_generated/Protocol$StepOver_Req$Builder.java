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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements StepOver_ReqOrBuilder
{
    private int bitField0_;
    private int threadId_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$25600();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$25700().ensureFieldAccessorsInitialized((Class)StepOver_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (StepOver_Req.access$26100()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.threadId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$25600();
    }
    
    public StepOver_Req getDefaultInstanceForType() {
        return StepOver_Req.getDefaultInstance();
    }
    
    public StepOver_Req build() {
        final StepOver_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public StepOver_Req buildPartial() {
        final StepOver_Req stepOver_Req = new StepOver_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        StepOver_Req.access$26302(stepOver_Req, this.threadId_);
        StepOver_Req.access$26402(stepOver_Req, n);
        this.onBuilt();
        return stepOver_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof StepOver_Req) {
            return this.mergeFrom((StepOver_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final StepOver_Req stepOver_Req) {
        if (stepOver_Req == StepOver_Req.getDefaultInstance()) {
            return this;
        }
        if (stepOver_Req.hasThreadId()) {
            this.setThreadId(stepOver_Req.getThreadId());
        }
        this.mergeUnknownFields(stepOver_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasThreadId();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        StepOver_Req stepOver_Req = null;
        try {
            stepOver_Req = (StepOver_Req)StepOver_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            stepOver_Req = (StepOver_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (stepOver_Req != null) {
                    this.mergeFrom(stepOver_Req);
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
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
