// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements HandleCompletion_ReqOrBuilder
{
    private int bitField0_;
    private Object command_;
    private int pos_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$52500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$52600().ensureFieldAccessorsInitialized((Class)HandleCompletion_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.command_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.command_ = "";
        this.b();
    }
    
    private void b() {
        if (HandleCompletion_Req.access$53000()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.command_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.pos_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$52500();
    }
    
    public HandleCompletion_Req getDefaultInstanceForType() {
        return HandleCompletion_Req.getDefaultInstance();
    }
    
    public HandleCompletion_Req build() {
        final HandleCompletion_Req buildPartial = this.buildPartial();
        try {
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return buildPartial;
    }
    
    public HandleCompletion_Req buildPartial() {
        final HandleCompletion_Req handleCompletion_Req = new HandleCompletion_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        HandleCompletion_Req.access$53202(handleCompletion_Req, this.command_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        HandleCompletion_Req.access$53302(handleCompletion_Req, this.pos_);
        HandleCompletion_Req.access$53402(handleCompletion_Req, n);
        this.onBuilt();
        return handleCompletion_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof HandleCompletion_Req) {
                return this.mergeFrom((HandleCompletion_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final HandleCompletion_Req handleCompletion_Req) {
        try {
            if (handleCompletion_Req == HandleCompletion_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (handleCompletion_Req.hasCommand()) {
                this.bitField0_ |= 0x1;
                this.command_ = HandleCompletion_Req.access$53200(handleCompletion_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (handleCompletion_Req.hasPos()) {
                this.setPos(handleCompletion_Req.getPos());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(handleCompletion_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasCommand()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasPos()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        HandleCompletion_Req handleCompletion_Req = null;
        try {
            handleCompletion_Req = (HandleCompletion_Req)HandleCompletion_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            handleCompletion_Req = (HandleCompletion_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (handleCompletion_Req != null) {
                    this.mergeFrom(handleCompletion_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasCommand() {
        try {
            if ((this.bitField0_ & 0x1) == 0x1) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getCommand() {
        final Object command_ = this.command_;
        if (!(command_ instanceof String)) {
            return (String)(this.command_ = ((ByteString)command_).toStringUtf8());
        }
        return (String)command_;
    }
    
    public ByteString getCommandBytes() {
        final Object command_ = this.command_;
        if (command_ instanceof String) {
            return (ByteString)(this.command_ = ByteString.copyFromUtf8((String)command_));
        }
        return (ByteString)command_;
    }
    
    public Builder setCommand(final String command_) {
        try {
            if (command_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.command_ = command_;
        this.onChanged();
        return this;
    }
    
    public Builder clearCommand() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.command_ = HandleCompletion_Req.getDefaultInstance().getCommand();
        this.onChanged();
        return this;
    }
    
    public Builder setCommandBytes(final ByteString command_) {
        try {
            if (command_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.command_ = command_;
        this.onChanged();
        return this;
    }
    
    public boolean hasPos() {
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public int getPos() {
        return this.pos_;
    }
    
    public Builder setPos(final int pos_) {
        this.bitField0_ |= 0x2;
        this.pos_ = pos_;
        this.onChanged();
        return this;
    }
    
    public Builder clearPos() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.pos_ = 0;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
