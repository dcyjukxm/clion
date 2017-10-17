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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements HandleConsoleCommand_ReqOrBuilder
{
    private int bitField0_;
    private int threadId_;
    private int frameIndex_;
    private Object command_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$50200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$50300().ensureFieldAccessorsInitialized((Class)HandleConsoleCommand_Req.class, (Class)Builder.class);
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
        if (HandleConsoleCommand_Req.access$50700()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.threadId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.frameIndex_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.command_ = "";
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$50200();
    }
    
    public HandleConsoleCommand_Req getDefaultInstanceForType() {
        return HandleConsoleCommand_Req.getDefaultInstance();
    }
    
    public HandleConsoleCommand_Req build() {
        final HandleConsoleCommand_Req buildPartial = this.buildPartial();
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
    
    public HandleConsoleCommand_Req buildPartial() {
        final HandleConsoleCommand_Req handleConsoleCommand_Req = new HandleConsoleCommand_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        HandleConsoleCommand_Req.access$50902(handleConsoleCommand_Req, this.threadId_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        HandleConsoleCommand_Req.access$51002(handleConsoleCommand_Req, this.frameIndex_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        HandleConsoleCommand_Req.access$51102(handleConsoleCommand_Req, this.command_);
        HandleConsoleCommand_Req.access$51202(handleConsoleCommand_Req, n);
        this.onBuilt();
        return handleConsoleCommand_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof HandleConsoleCommand_Req) {
                return this.mergeFrom((HandleConsoleCommand_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final HandleConsoleCommand_Req handleConsoleCommand_Req) {
        try {
            if (handleConsoleCommand_Req == HandleConsoleCommand_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (handleConsoleCommand_Req.hasThreadId()) {
                this.setThreadId(handleConsoleCommand_Req.getThreadId());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (handleConsoleCommand_Req.hasFrameIndex()) {
                this.setFrameIndex(handleConsoleCommand_Req.getFrameIndex());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (handleConsoleCommand_Req.hasCommand()) {
                this.bitField0_ |= 0x4;
                this.command_ = HandleConsoleCommand_Req.access$51100(handleConsoleCommand_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        this.mergeUnknownFields(handleConsoleCommand_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasThreadId()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasFrameIndex()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.hasCommand()) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        HandleConsoleCommand_Req handleConsoleCommand_Req = null;
        try {
            handleConsoleCommand_Req = (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            handleConsoleCommand_Req = (HandleConsoleCommand_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (handleConsoleCommand_Req != null) {
                    this.mergeFrom(handleConsoleCommand_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasThreadId() {
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
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    public Builder setThreadId(final int threadId_) {
        this.bitField0_ |= 0x1;
        this.threadId_ = threadId_;
        this.onChanged();
        return this;
    }
    
    public Builder clearThreadId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.threadId_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasFrameIndex() {
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
    
    public int getFrameIndex() {
        return this.frameIndex_;
    }
    
    public Builder setFrameIndex(final int frameIndex_) {
        this.bitField0_ |= 0x2;
        this.frameIndex_ = frameIndex_;
        this.onChanged();
        return this;
    }
    
    public Builder clearFrameIndex() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.frameIndex_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasCommand() {
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
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
        this.bitField0_ |= 0x4;
        this.command_ = command_;
        this.onChanged();
        return this;
    }
    
    public Builder clearCommand() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.command_ = HandleConsoleCommand_Req.getDefaultInstance().getCommand();
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
        this.bitField0_ |= 0x4;
        this.command_ = command_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
