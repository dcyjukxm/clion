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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements Detach_ReqOrBuilder
{
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$8000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$8100().ensureFieldAccessorsInitialized((Class)Detach_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (Detach_Req.access$8500()) {}
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
        return Protocol.access$8000();
    }
    
    public Detach_Req getDefaultInstanceForType() {
        return Detach_Req.getDefaultInstance();
    }
    
    public Detach_Req build() {
        final Detach_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public Detach_Req buildPartial() {
        final Detach_Req detach_Req = new Detach_Req((GeneratedMessage.Builder)this);
        this.onBuilt();
        return detach_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof Detach_Req) {
            return this.mergeFrom((Detach_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final Detach_Req detach_Req) {
        if (detach_Req == Detach_Req.getDefaultInstance()) {
            return this;
        }
        this.mergeUnknownFields(detach_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Detach_Req detach_Req = null;
        try {
            detach_Req = (Detach_Req)Detach_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            detach_Req = (Detach_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (detach_Req != null) {
                    this.mergeFrom(detach_Req);
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
