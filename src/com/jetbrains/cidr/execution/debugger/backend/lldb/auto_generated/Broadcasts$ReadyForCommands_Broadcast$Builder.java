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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ReadyForCommands_BroadcastOrBuilder
{
    private int bitField0_;
    private int ready_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$6400();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$6500().ensureFieldAccessorsInitialized((Class)ReadyForCommands_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (ReadyForCommands_Broadcast.access$6900()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.ready_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$6400();
    }
    
    public ReadyForCommands_Broadcast getDefaultInstanceForType() {
        return ReadyForCommands_Broadcast.getDefaultInstance();
    }
    
    public ReadyForCommands_Broadcast build() {
        final ReadyForCommands_Broadcast buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public ReadyForCommands_Broadcast buildPartial() {
        final ReadyForCommands_Broadcast readyForCommands_Broadcast = new ReadyForCommands_Broadcast((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        ReadyForCommands_Broadcast.access$7102(readyForCommands_Broadcast, this.ready_);
        ReadyForCommands_Broadcast.access$7202(readyForCommands_Broadcast, n);
        this.onBuilt();
        return readyForCommands_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof ReadyForCommands_Broadcast) {
            return this.mergeFrom((ReadyForCommands_Broadcast)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ReadyForCommands_Broadcast readyForCommands_Broadcast) {
        if (readyForCommands_Broadcast == ReadyForCommands_Broadcast.getDefaultInstance()) {
            return this;
        }
        if (readyForCommands_Broadcast.hasReady()) {
            this.setReady(readyForCommands_Broadcast.getReady());
        }
        this.mergeUnknownFields(readyForCommands_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return this.hasReady();
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ReadyForCommands_Broadcast readyForCommands_Broadcast = null;
        try {
            readyForCommands_Broadcast = (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            readyForCommands_Broadcast = (ReadyForCommands_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (readyForCommands_Broadcast != null) {
                    this.mergeFrom(readyForCommands_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    public boolean hasReady() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getReady() {
        return this.ready_;
    }
    
    public Builder setReady(final int ready_) {
        this.bitField0_ |= 0x1;
        this.ready_ = ready_;
        this.onChanged();
        return this;
    }
    
    public Builder clearReady() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.ready_ = 0;
        this.onChanged();
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
