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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements RemoveBreakpoint_ReqOrBuilder
{
    private int bitField0_;
    private int id_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$21000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$21100().ensureFieldAccessorsInitialized((Class)RemoveBreakpoint_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (RemoveBreakpoint_Req.access$21500()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.id_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$21000();
    }
    
    public RemoveBreakpoint_Req getDefaultInstanceForType() {
        return RemoveBreakpoint_Req.getDefaultInstance();
    }
    
    public RemoveBreakpoint_Req build() {
        final RemoveBreakpoint_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public RemoveBreakpoint_Req buildPartial() {
        final RemoveBreakpoint_Req removeBreakpoint_Req = new RemoveBreakpoint_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        RemoveBreakpoint_Req.access$21702(removeBreakpoint_Req, this.id_);
        RemoveBreakpoint_Req.access$21802(removeBreakpoint_Req, n);
        this.onBuilt();
        return removeBreakpoint_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof RemoveBreakpoint_Req) {
            return this.mergeFrom((RemoveBreakpoint_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final RemoveBreakpoint_Req removeBreakpoint_Req) {
        if (removeBreakpoint_Req == RemoveBreakpoint_Req.getDefaultInstance()) {
            return this;
        }
        if (removeBreakpoint_Req.hasId()) {
            this.setId(removeBreakpoint_Req.getId());
        }
        this.mergeUnknownFields(removeBreakpoint_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasId();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        RemoveBreakpoint_Req removeBreakpoint_Req = null;
        try {
            removeBreakpoint_Req = (RemoveBreakpoint_Req)RemoveBreakpoint_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            removeBreakpoint_Req = (RemoveBreakpoint_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (removeBreakpoint_Req != null) {
                    this.mergeFrom(removeBreakpoint_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    public boolean hasId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getId() {
        return this.id_;
    }
    
    public Builder setId(final int id_) {
        this.bitField0_ |= 0x1;
        this.id_ = id_;
        this.onChanged();
        return this;
    }
    
    public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
