// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class CommandsInterpreter_Broadcast extends GeneratedMessage implements CommandsInterpreter_BroadcastOrBuilder
{
    private static final CommandsInterpreter_Broadcast defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<CommandsInterpreter_Broadcast> PARSER;
    private int bitField0_;
    public static final int MESSAGE_FIELD_NUMBER = 1;
    private Object message_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private CommandsInterpreter_Broadcast(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private CommandsInterpreter_Broadcast(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static CommandsInterpreter_Broadcast getDefaultInstance() {
        return CommandsInterpreter_Broadcast.defaultInstance;
    }
    
    public CommandsInterpreter_Broadcast getDefaultInstanceForType() {
        return CommandsInterpreter_Broadcast.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private CommandsInterpreter_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.a();
        final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
        try {
            int i = 0;
            while (i == 0) {
                final int tag = codedInputStream.readTag();
                switch (tag) {
                    case 0: {
                        i = 1;
                        continue;
                    }
                    default: {
                        if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                            i = 1;
                            continue;
                        }
                        continue;
                    }
                    case 10: {
                        this.bitField0_ |= 0x1;
                        this.message_ = codedInputStream.readBytes();
                        continue;
                    }
                }
            }
        }
        catch (InvalidProtocolBufferException ex) {
            throw ex.setUnfinishedMessage((MessageLite)this);
        }
        catch (IOException ex2) {
            throw new InvalidProtocolBufferException(ex2.getMessage()).setUnfinishedMessage((MessageLite)this);
        }
        finally {
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$7300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$7400().ensureFieldAccessorsInitialized((Class)CommandsInterpreter_Broadcast.class, (Class)Builder.class);
    }
    
    public Parser<CommandsInterpreter_Broadcast> getParserForType() {
        return CommandsInterpreter_Broadcast.PARSER;
    }
    
    public boolean hasMessage() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getMessage() {
        final Object message_ = this.message_;
        if (message_ instanceof String) {
            return (String)message_;
        }
        final ByteString byteString = (ByteString)message_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.message_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getMessageBytes() {
        final Object message_ = this.message_;
        if (message_ instanceof String) {
            return (ByteString)(this.message_ = ByteString.copyFromUtf8((String)message_));
        }
        return (ByteString)message_;
    }
    
    private void a() {
        this.message_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasMessage()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }
    
    public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
        try {
            this.getSerializedSize();
            if ((this.bitField0_ & 0x1) == 0x1) {
                codedOutputStream.writeBytes(1, this.getMessageBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        this.getUnknownFields().writeTo(codedOutputStream);
    }
    
    public int getSerializedSize() {
        final int memoizedSerializedSize = this.memoizedSerializedSize;
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        int n = 0;
        if ((this.bitField0_ & 0x1) == 0x1) {
            n += CodedOutputStream.computeBytesSize(1, this.getMessageBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static CommandsInterpreter_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(byteString);
    }
    
    public static CommandsInterpreter_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static CommandsInterpreter_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(array);
    }
    
    public static CommandsInterpreter_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static CommandsInterpreter_Broadcast parseFrom(final InputStream inputStream) throws IOException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(inputStream);
    }
    
    public static CommandsInterpreter_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static CommandsInterpreter_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static CommandsInterpreter_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static CommandsInterpreter_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(codedInputStream);
    }
    
    public static CommandsInterpreter_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommandsInterpreter_Broadcast)CommandsInterpreter_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final CommandsInterpreter_Broadcast commandsInterpreter_Broadcast) {
        return newBuilder().mergeFrom(commandsInterpreter_Broadcast);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        CommandsInterpreter_Broadcast.PARSER = (Parser<CommandsInterpreter_Broadcast>)new AbstractParser<CommandsInterpreter_Broadcast>() {
            public CommandsInterpreter_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CommandsInterpreter_Broadcast(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new CommandsInterpreter_Broadcast(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
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
            if (CommandsInterpreter_Broadcast.alwaysUseFieldBuilders) {}
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
            commandsInterpreter_Broadcast.message_ = this.message_;
            commandsInterpreter_Broadcast.bitField0_ = n;
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
                    this.message_ = commandsInterpreter_Broadcast.message_;
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
}
