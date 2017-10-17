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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ExecuteShellCommand_ReqOrBuilder
{
    private int bitField0_;
    private Object command_;
    private Object workingDir_;
    private int timeoutSecs_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$62900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$63000().ensureFieldAccessorsInitialized((Class)ExecuteShellCommand_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.command_ = "";
        this.workingDir_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.command_ = "";
        this.workingDir_ = "";
        this.b();
    }
    
    private void b() {
        if (ExecuteShellCommand_Req.access$63400()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.command_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.workingDir_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.timeoutSecs_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$62900();
    }
    
    public ExecuteShellCommand_Req getDefaultInstanceForType() {
        return ExecuteShellCommand_Req.getDefaultInstance();
    }
    
    public ExecuteShellCommand_Req build() {
        final ExecuteShellCommand_Req buildPartial = this.buildPartial();
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
    
    public ExecuteShellCommand_Req buildPartial() {
        final ExecuteShellCommand_Req executeShellCommand_Req = new ExecuteShellCommand_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        ExecuteShellCommand_Req.access$63602(executeShellCommand_Req, this.command_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        ExecuteShellCommand_Req.access$63702(executeShellCommand_Req, this.workingDir_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        ExecuteShellCommand_Req.access$63802(executeShellCommand_Req, this.timeoutSecs_);
        ExecuteShellCommand_Req.access$63902(executeShellCommand_Req, n);
        this.onBuilt();
        return executeShellCommand_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof ExecuteShellCommand_Req) {
                return this.mergeFrom((ExecuteShellCommand_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ExecuteShellCommand_Req executeShellCommand_Req) {
        try {
            if (executeShellCommand_Req == ExecuteShellCommand_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (executeShellCommand_Req.hasCommand()) {
                this.bitField0_ |= 0x1;
                this.command_ = ExecuteShellCommand_Req.access$63600(executeShellCommand_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (executeShellCommand_Req.hasWorkingDir()) {
                this.bitField0_ |= 0x2;
                this.workingDir_ = ExecuteShellCommand_Req.access$63700(executeShellCommand_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (executeShellCommand_Req.hasTimeoutSecs()) {
                this.setTimeoutSecs(executeShellCommand_Req.getTimeoutSecs());
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        this.mergeUnknownFields(executeShellCommand_Req.getUnknownFields());
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
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ExecuteShellCommand_Req executeShellCommand_Req = null;
        try {
            executeShellCommand_Req = (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            executeShellCommand_Req = (ExecuteShellCommand_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (executeShellCommand_Req != null) {
                    this.mergeFrom(executeShellCommand_Req);
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
        this.command_ = ExecuteShellCommand_Req.getDefaultInstance().getCommand();
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
    
    public boolean hasWorkingDir() {
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
    
    public String getWorkingDir() {
        final Object workingDir_ = this.workingDir_;
        if (!(workingDir_ instanceof String)) {
            return (String)(this.workingDir_ = ((ByteString)workingDir_).toStringUtf8());
        }
        return (String)workingDir_;
    }
    
    public ByteString getWorkingDirBytes() {
        final Object workingDir_ = this.workingDir_;
        if (workingDir_ instanceof String) {
            return (ByteString)(this.workingDir_ = ByteString.copyFromUtf8((String)workingDir_));
        }
        return (ByteString)workingDir_;
    }
    
    public Builder setWorkingDir(final String workingDir_) {
        try {
            if (workingDir_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.workingDir_ = workingDir_;
        this.onChanged();
        return this;
    }
    
    public Builder clearWorkingDir() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.workingDir_ = ExecuteShellCommand_Req.getDefaultInstance().getWorkingDir();
        this.onChanged();
        return this;
    }
    
    public Builder setWorkingDirBytes(final ByteString workingDir_) {
        try {
            if (workingDir_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.workingDir_ = workingDir_;
        this.onChanged();
        return this;
    }
    
    public boolean hasTimeoutSecs() {
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
    
    public int getTimeoutSecs() {
        return this.timeoutSecs_;
    }
    
    public Builder setTimeoutSecs(final int timeoutSecs_) {
        this.bitField0_ |= 0x4;
        this.timeoutSecs_ = timeoutSecs_;
        this.onChanged();
        return this;
    }
    
    public Builder clearTimeoutSecs() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.timeoutSecs_ = 0;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
