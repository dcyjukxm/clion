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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements Initialized_MessageOrBuilder
{
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$100().ensureFieldAccessorsInitialized((Class)Initialized_Message.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (Initialized_Message.access$500()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Model.access$000();
    }
    
    public Initialized_Message getDefaultInstanceForType() {
        return Initialized_Message.getDefaultInstance();
    }
    
    public Initialized_Message build() {
        final Initialized_Message buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public Initialized_Message buildPartial() {
        final Initialized_Message initialized_Message = new Initialized_Message((GeneratedMessage.Builder)this);
        this.onBuilt();
        return initialized_Message;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof Initialized_Message) {
            return this.mergeFrom((Initialized_Message)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final Initialized_Message initialized_Message) {
        if (initialized_Message == Initialized_Message.getDefaultInstance()) {
            return this;
        }
        this.mergeUnknownFields(initialized_Message.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Initialized_Message initialized_Message = null;
        try {
            initialized_Message = (Initialized_Message)Initialized_Message.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            initialized_Message = (Initialized_Message)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (initialized_Message != null) {
                    this.mergeFrom(initialized_Message);
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
