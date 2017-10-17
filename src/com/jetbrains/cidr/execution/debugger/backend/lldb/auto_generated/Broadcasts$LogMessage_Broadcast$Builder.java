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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements LogMessage_BroadcastOrBuilder
{
    private int bitField0_;
    private Verbosity verbosity_;
    private Object message_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$3700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$3800().ensureFieldAccessorsInitialized((Class)LogMessage_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.verbosity_ = Verbosity.VerbosityDebug;
        this.message_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.verbosity_ = Verbosity.VerbosityDebug;
        this.message_ = "";
        this.b();
    }
    
    private void b() {
        if (LogMessage_Broadcast.access$4200()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.verbosity_ = Verbosity.VerbosityDebug;
        this.bitField0_ &= 0xFFFFFFFE;
        this.message_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$3700();
    }
    
    public LogMessage_Broadcast getDefaultInstanceForType() {
        return LogMessage_Broadcast.getDefaultInstance();
    }
    
    public LogMessage_Broadcast build() {
        final LogMessage_Broadcast buildPartial = this.buildPartial();
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
    
    public LogMessage_Broadcast buildPartial() {
        final LogMessage_Broadcast logMessage_Broadcast = new LogMessage_Broadcast((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        LogMessage_Broadcast.access$4402(logMessage_Broadcast, this.verbosity_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        LogMessage_Broadcast.access$4502(logMessage_Broadcast, this.message_);
        LogMessage_Broadcast.access$4602(logMessage_Broadcast, n);
        this.onBuilt();
        return logMessage_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof LogMessage_Broadcast) {
                return this.mergeFrom((LogMessage_Broadcast)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final LogMessage_Broadcast logMessage_Broadcast) {
        try {
            if (logMessage_Broadcast == LogMessage_Broadcast.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (logMessage_Broadcast.hasVerbosity()) {
                this.setVerbosity(logMessage_Broadcast.getVerbosity());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (logMessage_Broadcast.hasMessage()) {
                this.bitField0_ |= 0x2;
                this.message_ = LogMessage_Broadcast.access$4500(logMessage_Broadcast);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(logMessage_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasVerbosity()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasMessage()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        LogMessage_Broadcast logMessage_Broadcast = null;
        try {
            logMessage_Broadcast = (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            logMessage_Broadcast = (LogMessage_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (logMessage_Broadcast != null) {
                    this.mergeFrom(logMessage_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasVerbosity() {
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
    
    public Verbosity getVerbosity() {
        return this.verbosity_;
    }
    
    public Builder setVerbosity(final Verbosity verbosity_) {
        try {
            if (verbosity_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.verbosity_ = verbosity_;
        this.onChanged();
        return this;
    }
    
    public Builder clearVerbosity() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.verbosity_ = Verbosity.VerbosityDebug;
        this.onChanged();
        return this;
    }
    
    public boolean hasMessage() {
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
    
    public String getMessage() {
        final Object message_ = this.message_;
        if (!(message_ instanceof String)) {
            return (String)(this.message_ = ((ByteString)message_).toStringUtf8());
        }
        return (String)message_;
    }
    
    public ByteString getMessageBytes() {
        final Object message_ = this.message_;
        if (message_ instanceof String) {
            return (ByteString)(this.message_ = ByteString.copyFromUtf8((String)message_));
        }
        return (ByteString)message_;
    }
    
    public Builder setMessage(final String message_) {
        try {
            if (message_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.message_ = message_;
        this.onChanged();
        return this;
    }
    
    public Builder clearMessage() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.message_ = LogMessage_Broadcast.getDefaultInstance().getMessage();
        this.onChanged();
        return this;
    }
    
    public Builder setMessageBytes(final ByteString message_) {
        try {
            if (message_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.message_ = message_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
