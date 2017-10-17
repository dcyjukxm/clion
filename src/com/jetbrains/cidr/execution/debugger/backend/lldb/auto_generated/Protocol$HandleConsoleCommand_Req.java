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

public static final class HandleConsoleCommand_Req extends GeneratedMessage implements HandleConsoleCommand_ReqOrBuilder
{
    private static final HandleConsoleCommand_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<HandleConsoleCommand_Req> PARSER;
    private int bitField0_;
    public static final int THREAD_ID_FIELD_NUMBER = 2;
    private int threadId_;
    public static final int FRAME_INDEX_FIELD_NUMBER = 3;
    private int frameIndex_;
    public static final int COMMAND_FIELD_NUMBER = 4;
    private Object command_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private HandleConsoleCommand_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private HandleConsoleCommand_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static HandleConsoleCommand_Req getDefaultInstance() {
        return HandleConsoleCommand_Req.defaultInstance;
    }
    
    public HandleConsoleCommand_Req getDefaultInstanceForType() {
        return HandleConsoleCommand_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private HandleConsoleCommand_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 16: {
                        this.bitField0_ |= 0x1;
                        this.threadId_ = codedInputStream.readUInt32();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.frameIndex_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 34: {
                        this.bitField0_ |= 0x4;
                        this.command_ = codedInputStream.readBytes();
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
        return Protocol.access$50200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$50300().ensureFieldAccessorsInitialized((Class)HandleConsoleCommand_Req.class, (Class)Builder.class);
    }
    
    public Parser<HandleConsoleCommand_Req> getParserForType() {
        return HandleConsoleCommand_Req.PARSER;
    }
    
    public boolean hasThreadId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    public boolean hasFrameIndex() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getFrameIndex() {
        return this.frameIndex_;
    }
    
    public boolean hasCommand() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public String getCommand() {
        final Object command_ = this.command_;
        if (command_ instanceof String) {
            return (String)command_;
        }
        final ByteString byteString = (ByteString)command_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.command_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getCommandBytes() {
        final Object command_ = this.command_;
        if (command_ instanceof String) {
            return (ByteString)(this.command_ = ByteString.copyFromUtf8((String)command_));
        }
        return (ByteString)command_;
    }
    
    private void a() {
        this.threadId_ = 0;
        this.frameIndex_ = 0;
        this.command_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasThreadId()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasFrameIndex()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasCommand()) {
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
                codedOutputStream.writeUInt32(2, this.threadId_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeInt32(3, this.frameIndex_);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeBytes(4, this.getCommandBytes());
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
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
            n += CodedOutputStream.computeUInt32Size(2, this.threadId_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(3, this.frameIndex_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBytesSize(4, this.getCommandBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static HandleConsoleCommand_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseFrom(byteString);
    }
    
    public static HandleConsoleCommand_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static HandleConsoleCommand_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseFrom(array);
    }
    
    public static HandleConsoleCommand_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static HandleConsoleCommand_Req parseFrom(final InputStream inputStream) throws IOException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseFrom(inputStream);
    }
    
    public static HandleConsoleCommand_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static HandleConsoleCommand_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static HandleConsoleCommand_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static HandleConsoleCommand_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static HandleConsoleCommand_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleConsoleCommand_Req)HandleConsoleCommand_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final HandleConsoleCommand_Req handleConsoleCommand_Req) {
        return newBuilder().mergeFrom(handleConsoleCommand_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        HandleConsoleCommand_Req.PARSER = (Parser<HandleConsoleCommand_Req>)new AbstractParser<HandleConsoleCommand_Req>() {
            public HandleConsoleCommand_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HandleConsoleCommand_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new HandleConsoleCommand_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
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
            if (HandleConsoleCommand_Req.alwaysUseFieldBuilders) {}
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
            handleConsoleCommand_Req.threadId_ = this.threadId_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            handleConsoleCommand_Req.frameIndex_ = this.frameIndex_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            handleConsoleCommand_Req.command_ = this.command_;
            handleConsoleCommand_Req.bitField0_ = n;
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
                    this.command_ = handleConsoleCommand_Req.command_;
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
}
