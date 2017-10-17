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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements Attach_ReqOrBuilder
{
    private int bitField0_;
    private int pid_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$7100();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$7200().ensureFieldAccessorsInitialized((Class)Attach_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (Attach_Req.access$7600()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.pid_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$7100();
    }
    
    public Attach_Req getDefaultInstanceForType() {
        return Attach_Req.getDefaultInstance();
    }
    
    public Attach_Req build() {
        final Attach_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public Attach_Req buildPartial() {
        final Attach_Req attach_Req = new Attach_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        Attach_Req.access$7802(attach_Req, this.pid_);
        Attach_Req.access$7902(attach_Req, n);
        this.onBuilt();
        return attach_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof Attach_Req) {
            return this.mergeFrom((Attach_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final Attach_Req attach_Req) {
        if (attach_Req == Attach_Req.getDefaultInstance()) {
            return this;
        }
        if (attach_Req.hasPid()) {
            this.setPid(attach_Req.getPid());
        }
        this.mergeUnknownFields(attach_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasPid();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Attach_Req attach_Req = null;
        try {
            attach_Req = (Attach_Req)Attach_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            attach_Req = (Attach_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (attach_Req != null) {
                    this.mergeFrom(attach_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    public boolean hasPid() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getPid() {
        return this.pid_;
    }
    
    public Builder setPid(final int pid_) {
        this.bitField0_ |= 0x1;
        this.pid_ = pid_;
        this.onChanged();
        return this;
    }
    
    public Builder clearPid() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.pid_ = 0;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
