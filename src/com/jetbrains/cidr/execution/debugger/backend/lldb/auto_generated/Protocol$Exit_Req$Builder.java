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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements Exit_ReqOrBuilder
{
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$58200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$58300().ensureFieldAccessorsInitialized((Class)Exit_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.b();
    }
    
    private void b() {
        if (Exit_Req.access$58700()) {}
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
        return Protocol.access$58200();
    }
    
    public Exit_Req getDefaultInstanceForType() {
        return Exit_Req.getDefaultInstance();
    }
    
    public Exit_Req build() {
        final Exit_Req buildPartial = this.buildPartial();
        if (!buildPartial.isInitialized()) {
            throw newUninitializedMessageException((Message)buildPartial);
        }
        return buildPartial;
    }
    
    public Exit_Req buildPartial() {
        final Exit_Req exit_Req = new Exit_Req((GeneratedMessage.Builder)this);
        this.onBuilt();
        return exit_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        if (message instanceof Exit_Req) {
            return this.mergeFrom((Exit_Req)message);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final Exit_Req exit_Req) {
        if (exit_Req == Exit_Req.getDefaultInstance()) {
            return this;
        }
        this.mergeUnknownFields(exit_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Exit_Req exit_Req = null;
        try {
            exit_Req = (Exit_Req)Exit_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            exit_Req = (Exit_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (exit_Req != null) {
                    this.mergeFrom(exit_Req);
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
