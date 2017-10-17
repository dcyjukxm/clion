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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ProcessExited_BroadcastOrBuilder
{
    private int bitField0_;
    private int exitCode_;
    private Object exitDescription_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$1700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$1800().ensureFieldAccessorsInitialized((Class)ProcessExited_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.exitDescription_ = "";
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.exitDescription_ = "";
        this.a();
    }
    
    private void a() {
        if (ProcessExited_Broadcast.access$2200()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.exitCode_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.exitDescription_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$1700();
    }
    
    public ProcessExited_Broadcast getDefaultInstanceForType() {
        return ProcessExited_Broadcast.getDefaultInstance();
    }
    
    public ProcessExited_Broadcast build() {
        final ProcessExited_Broadcast buildPartial = this.buildPartial();
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
    
    public ProcessExited_Broadcast buildPartial() {
        final ProcessExited_Broadcast processExited_Broadcast = new ProcessExited_Broadcast((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        ProcessExited_Broadcast.access$2402(processExited_Broadcast, this.exitCode_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        ProcessExited_Broadcast.access$2502(processExited_Broadcast, this.exitDescription_);
        ProcessExited_Broadcast.access$2602(processExited_Broadcast, n);
        this.onBuilt();
        return processExited_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof ProcessExited_Broadcast) {
                return this.mergeFrom((ProcessExited_Broadcast)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ProcessExited_Broadcast processExited_Broadcast) {
        try {
            if (processExited_Broadcast == ProcessExited_Broadcast.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (processExited_Broadcast.hasExitCode()) {
                this.setExitCode(processExited_Broadcast.getExitCode());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (processExited_Broadcast.hasExitDescription()) {
                this.bitField0_ |= 0x2;
                this.exitDescription_ = ProcessExited_Broadcast.access$2500(processExited_Broadcast);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(processExited_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasExitCode()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ProcessExited_Broadcast processExited_Broadcast = null;
        try {
            processExited_Broadcast = (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            processExited_Broadcast = (ProcessExited_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (processExited_Broadcast != null) {
                    this.mergeFrom(processExited_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasExitCode() {
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
    
    public int getExitCode() {
        return this.exitCode_;
    }
    
    public Builder setExitCode(final int exitCode_) {
        this.bitField0_ |= 0x1;
        this.exitCode_ = exitCode_;
        this.onChanged();
        return this;
    }
    
    public Builder clearExitCode() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.exitCode_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasExitDescription() {
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
    
    public String getExitDescription() {
        final Object exitDescription_ = this.exitDescription_;
        if (!(exitDescription_ instanceof String)) {
            return (String)(this.exitDescription_ = ((ByteString)exitDescription_).toStringUtf8());
        }
        return (String)exitDescription_;
    }
    
    public ByteString getExitDescriptionBytes() {
        final Object exitDescription_ = this.exitDescription_;
        if (exitDescription_ instanceof String) {
            return (ByteString)(this.exitDescription_ = ByteString.copyFromUtf8((String)exitDescription_));
        }
        return (ByteString)exitDescription_;
    }
    
    public Builder setExitDescription(final String exitDescription_) {
        try {
            if (exitDescription_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.exitDescription_ = exitDescription_;
        this.onChanged();
        return this;
    }
    
    public Builder clearExitDescription() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.exitDescription_ = ProcessExited_Broadcast.getDefaultInstance().getExitDescription();
        this.onChanged();
        return this;
    }
    
    public Builder setExitDescriptionBytes(final ByteString exitDescription_) {
        try {
            if (exitDescription_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.exitDescription_ = exitDescription_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
