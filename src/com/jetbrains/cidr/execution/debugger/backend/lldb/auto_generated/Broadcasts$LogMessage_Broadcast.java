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
import com.google.protobuf.MessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class LogMessage_Broadcast extends GeneratedMessage implements LogMessage_BroadcastOrBuilder
{
    private static final LogMessage_Broadcast defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<LogMessage_Broadcast> PARSER;
    private int bitField0_;
    public static final int VERBOSITY_FIELD_NUMBER = 1;
    private Verbosity verbosity_;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    private Object message_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private LogMessage_Broadcast(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private LogMessage_Broadcast(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static LogMessage_Broadcast getDefaultInstance() {
        return LogMessage_Broadcast.defaultInstance;
    }
    
    public LogMessage_Broadcast getDefaultInstanceForType() {
        return LogMessage_Broadcast.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private LogMessage_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 8: {
                        final int enum1 = codedInputStream.readEnum();
                        final Verbosity value = Verbosity.valueOf(enum1);
                        try {
                            if (value == null) {
                                builder.mergeVarintField(1, enum1);
                                continue;
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x1;
                        this.verbosity_ = value;
                        continue;
                    }
                    case 18: {
                        this.bitField0_ |= 0x2;
                        this.message_ = codedInputStream.readBytes();
                        continue;
                    }
                }
            }
        }
        catch (InvalidProtocolBufferException ex2) {
            throw ex2.setUnfinishedMessage((MessageLite)this);
        }
        catch (IOException ex3) {
            throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
        }
        finally {
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$3700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$3800().ensureFieldAccessorsInitialized((Class)LogMessage_Broadcast.class, (Class)Builder.class);
    }
    
    public Parser<LogMessage_Broadcast> getParserForType() {
        return LogMessage_Broadcast.PARSER;
    }
    
    public boolean hasVerbosity() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public Verbosity getVerbosity() {
        return this.verbosity_;
    }
    
    public boolean hasMessage() {
        return (this.bitField0_ & 0x2) == 0x2;
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
        this.verbosity_ = Verbosity.VerbosityDebug;
        this.message_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasVerbosity()) {
            this.memoizedIsInitialized = 0;
            return false;
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
                codedOutputStream.writeEnum(1, this.verbosity_.getNumber());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(2, this.getMessageBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
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
            n += CodedOutputStream.computeEnumSize(1, this.verbosity_.getNumber());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(2, this.getMessageBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static LogMessage_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(byteString);
    }
    
    public static LogMessage_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static LogMessage_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(array);
    }
    
    public static LogMessage_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static LogMessage_Broadcast parseFrom(final InputStream inputStream) throws IOException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(inputStream);
    }
    
    public static LogMessage_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static LogMessage_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static LogMessage_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static LogMessage_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(codedInputStream);
    }
    
    public static LogMessage_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LogMessage_Broadcast)LogMessage_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final LogMessage_Broadcast logMessage_Broadcast) {
        return newBuilder().mergeFrom(logMessage_Broadcast);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        LogMessage_Broadcast.PARSER = (Parser<LogMessage_Broadcast>)new AbstractParser<LogMessage_Broadcast>() {
            public LogMessage_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LogMessage_Broadcast(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new LogMessage_Broadcast(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
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
            if (LogMessage_Broadcast.alwaysUseFieldBuilders) {}
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
            logMessage_Broadcast.verbosity_ = this.verbosity_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            logMessage_Broadcast.message_ = this.message_;
            logMessage_Broadcast.bitField0_ = n;
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
                    this.message_ = logMessage_Broadcast.message_;
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
}
