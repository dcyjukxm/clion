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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ProcessRunning_BroadcastOrBuilder
{
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$1000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$1100().ensureFieldAccessorsInitialized((Class)ProcessRunning_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.a();
    }
    
    private void a() {
        if (ProcessRunning_Broadcast.access$1500()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$1000();
    }
    
    public ProcessRunning_Broadcast getDefaultInstanceForType() {
        return ProcessRunning_Broadcast.getDefaultInstance();
    }
    
    public ProcessRunning_Broadcast build() {
        final ProcessRunning_Broadcast buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public ProcessRunning_Broadcast buildPartial() {
        final ProcessRunning_Broadcast processRunning_Broadcast = new ProcessRunning_Broadcast((GeneratedMessage.Builder)this);
        this.onBuilt();
        return processRunning_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof ProcessRunning_Broadcast) {
            return this.mergeFrom((ProcessRunning_Broadcast)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ProcessRunning_Broadcast processRunning_Broadcast) {
        if (processRunning_Broadcast == ProcessRunning_Broadcast.getDefaultInstance()) {
            return this;
        }
        this.mergeUnknownFields(processRunning_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ProcessRunning_Broadcast processRunning_Broadcast = null;
        try {
            processRunning_Broadcast = (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            processRunning_Broadcast = (ProcessRunning_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (processRunning_Broadcast != null) {
                    this.mergeFrom(processRunning_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b(ex2);
            }
        }
        return this;
    }
    
    private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
        return ex;
    }
}
