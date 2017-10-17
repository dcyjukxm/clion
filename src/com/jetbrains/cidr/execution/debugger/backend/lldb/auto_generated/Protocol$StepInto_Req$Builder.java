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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements StepInto_ReqOrBuilder
{
    private int bitField0_;
    private int threadId_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$23800();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$23900().ensureFieldAccessorsInitialized((Class)StepInto_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (StepInto_Req.access$24300()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.threadId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$23800();
    }
    
    public StepInto_Req getDefaultInstanceForType() {
        return StepInto_Req.getDefaultInstance();
    }
    
    public StepInto_Req build() {
        final StepInto_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public StepInto_Req buildPartial() {
        final StepInto_Req stepInto_Req = new StepInto_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        StepInto_Req.access$24502(stepInto_Req, this.threadId_);
        StepInto_Req.access$24602(stepInto_Req, n);
        this.onBuilt();
        return stepInto_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof StepInto_Req) {
            return this.mergeFrom((StepInto_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final StepInto_Req stepInto_Req) {
        if (stepInto_Req == StepInto_Req.getDefaultInstance()) {
            return this;
        }
        if (stepInto_Req.hasThreadId()) {
            this.setThreadId(stepInto_Req.getThreadId());
        }
        this.mergeUnknownFields(stepInto_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasThreadId();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        StepInto_Req stepInto_Req = null;
        try {
            stepInto_Req = (StepInto_Req)StepInto_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            stepInto_Req = (StepInto_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (stepInto_Req != null) {
                    this.mergeFrom(stepInto_Req);
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
