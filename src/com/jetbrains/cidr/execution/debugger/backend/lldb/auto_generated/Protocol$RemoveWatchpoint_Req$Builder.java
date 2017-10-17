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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements RemoveWatchpoint_ReqOrBuilder
{
    private int bitField0_;
    private int id_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$32700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$32800().ensureFieldAccessorsInitialized((Class)RemoveWatchpoint_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (RemoveWatchpoint_Req.access$33200()) {}
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
        return Protocol.access$32700();
    }
    
    public RemoveWatchpoint_Req getDefaultInstanceForType() {
        return RemoveWatchpoint_Req.getDefaultInstance();
    }
    
    public RemoveWatchpoint_Req build() {
        final RemoveWatchpoint_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public RemoveWatchpoint_Req buildPartial() {
        final RemoveWatchpoint_Req removeWatchpoint_Req = new RemoveWatchpoint_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        RemoveWatchpoint_Req.access$33402(removeWatchpoint_Req, this.id_);
        RemoveWatchpoint_Req.access$33502(removeWatchpoint_Req, n);
        this.onBuilt();
        return removeWatchpoint_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof RemoveWatchpoint_Req) {
            return this.mergeFrom((RemoveWatchpoint_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final RemoveWatchpoint_Req removeWatchpoint_Req) {
        if (removeWatchpoint_Req == RemoveWatchpoint_Req.getDefaultInstance()) {
            return this;
        }
        if (removeWatchpoint_Req.hasId()) {
            this.setId(removeWatchpoint_Req.getId());
        }
        this.mergeUnknownFields(removeWatchpoint_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasId();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        RemoveWatchpoint_Req removeWatchpoint_Req = null;
        try {
            removeWatchpoint_Req = (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            removeWatchpoint_Req = (RemoveWatchpoint_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (removeWatchpoint_Req != null) {
                    this.mergeFrom(removeWatchpoint_Req);
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
