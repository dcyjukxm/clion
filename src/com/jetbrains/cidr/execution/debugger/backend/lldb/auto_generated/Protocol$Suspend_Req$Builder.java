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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements Suspend_ReqOrBuilder
{
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$13700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$13800().ensureFieldAccessorsInitialized((Class)Suspend_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (Suspend_Req.access$14200()) {}
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
        return Protocol.access$13700();
    }
    
    public Suspend_Req getDefaultInstanceForType() {
        return Suspend_Req.getDefaultInstance();
    }
    
    public Suspend_Req build() {
        final Suspend_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public Suspend_Req buildPartial() {
        final Suspend_Req suspend_Req = new Suspend_Req((GeneratedMessage.Builder)this);
        this.onBuilt();
        return suspend_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof Suspend_Req) {
            return this.mergeFrom((Suspend_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final Suspend_Req suspend_Req) {
        if (suspend_Req == Suspend_Req.getDefaultInstance()) {
            return this;
        }
        this.mergeUnknownFields(suspend_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Suspend_Req suspend_Req = null;
        try {
            suspend_Req = (Suspend_Req)Suspend_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            suspend_Req = (Suspend_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (suspend_Req != null) {
                    this.mergeFrom(suspend_Req);
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
