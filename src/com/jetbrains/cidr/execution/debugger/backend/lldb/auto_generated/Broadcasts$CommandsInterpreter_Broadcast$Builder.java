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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements CommandsInterpreter_BroadcastOrBuilder
{
    private int bitField0_;
    private Object message_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$7300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$7400().ensureFieldAccessorsInitialized((Class)CommandsInterpreter_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.message_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.message_ = "";
        this.b();
    }
    
    private void b() {
        if (CommandsInterpreter_Broadcast.access$7800()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.message_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$7300();
    }
    
    public CommandsInterpreter_Broadcast getDefaultInstanceForType() {
        return CommandsInterpreter_Broadcast.getDefaultInstance();
    }
    
    public CommandsInterpreter_Broadcast build() {
        final CommandsInterpreter_Broadcast buildPartial = this.buildPartial();
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
    
    public CommandsInterpreter_Broadcast buildPartial() {
        final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast = new CommandsInterpreter_Broadcast((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        CommandsInterpreter_Broadcast.access$8002(commandsInterpreter_Broadcast, this.message_);
        CommandsInterpreter_Broadcast.access$8102(commandsInterpreter_Broadcast, n);
        this.onBuilt();
        return commandsInterpreter_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof CommandsInterpreter_Broadcast) {
                return this.mergeFrom((CommandsInterpreter_Broadcast)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast) {
        try {
            if (commandsInterpreter_Broadcast == CommandsInterpreter_Broadcast.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (commandsInterpreter_Broadcast.hasMessage()) {
                this.bitField0_ |= 0x1;
                this.message_ = CommandsInterpreter_Broadcast.access$8000(commandsInterpreter_Broadcast);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        this.mergeUnknownFields(commandsInterpreter_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasMessage()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        CommandsInterpreter_Broadcast commandsInterpreter_Broadcast = null;
        try {
            commandsInterpreter_Broadcast = (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            commandsInterpreter_Broadcast = (CommandsInterpreter_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (commandsInterpreter_Broadcast != null) {
                    this.mergeFrom(commandsInterpreter_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasMessage() {
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
        this.bitField0_ |= 0x1;
        this.message_ = message_;
        this.onChanged();
        return this;
    }
    
    public Builder clearMessage() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.message_ = CommandsInterpreter_Broadcast.getDefaultInstance().getMessage();
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
        this.bitField0_ |= 0x1;
        this.message_ = message_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
