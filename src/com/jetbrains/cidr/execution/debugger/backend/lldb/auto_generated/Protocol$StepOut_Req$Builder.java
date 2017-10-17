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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements StepOut_ReqOrBuilder
{
    private int bitField0_;
    private int threadId_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$27400();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$27500().ensureFieldAccessorsInitialized((Class)StepOut_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (StepOut_Req.access$27900()) {}
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
        return Protocol.access$27400();
    }
    
    public StepOut_Req getDefaultInstanceForType() {
        return StepOut_Req.getDefaultInstance();
    }
    
    public StepOut_Req build() {
        final StepOut_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public StepOut_Req buildPartial() {
        final StepOut_Req stepOut_Req = new StepOut_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        StepOut_Req.access$28102(stepOut_Req, this.threadId_);
        StepOut_Req.access$28202(stepOut_Req, n);
        this.onBuilt();
        return stepOut_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof StepOut_Req) {
            return this.mergeFrom((StepOut_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final StepOut_Req stepOut_Req) {
        if (stepOut_Req == StepOut_Req.getDefaultInstance()) {
            return this;
        }
        if (stepOut_Req.hasThreadId()) {
            this.setThreadId(stepOut_Req.getThreadId());
        }
        this.mergeUnknownFields(stepOut_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasThreadId();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        StepOut_Req stepOut_Req = null;
        try {
            stepOut_Req = (StepOut_Req)StepOut_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            stepOut_Req = (StepOut_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (stepOut_Req != null) {
                    this.mergeFrom(stepOut_Req);
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
