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

public static final class HandleCompletion_Req extends GeneratedMessage implements HandleCompletion_ReqOrBuilder
{
    private static final HandleCompletion_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<HandleCompletion_Req> PARSER;
    private int bitField0_;
    public static final int COMMAND_FIELD_NUMBER = 2;
    private Object command_;
    public static final int POS_FIELD_NUMBER = 3;
    private int pos_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private HandleCompletion_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private HandleCompletion_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static HandleCompletion_Req getDefaultInstance() {
        return HandleCompletion_Req.defaultInstance;
    }
    
    public HandleCompletion_Req getDefaultInstanceForType() {
        return HandleCompletion_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private HandleCompletion_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 18: {
                        this.bitField0_ |= 0x1;
                        this.command_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.pos_ = codedInputStream.readInt32();
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
        return Protocol.access$52500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$52600().ensureFieldAccessorsInitialized((Class)HandleCompletion_Req.class, (Class)Builder.class);
    }
    
    public Parser<HandleCompletion_Req> getParserForType() {
        return HandleCompletion_Req.PARSER;
    }
    
    public boolean hasCommand() {
        return (this.bitField0_ & 0x1) == 0x1;
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
    
    public boolean hasPos() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getPos() {
        return this.pos_;
    }
    
    private void a() {
        this.command_ = "";
        this.pos_ = 0;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasCommand()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasPos()) {
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
                codedOutputStream.writeBytes(2, this.getCommandBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeInt32(3, this.pos_);
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
            n += CodedOutputStream.computeBytesSize(2, this.getCommandBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(3, this.pos_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static HandleCompletion_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseFrom(byteString);
    }
    
    public static HandleCompletion_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static HandleCompletion_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseFrom(array);
    }
    
    public static HandleCompletion_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static HandleCompletion_Req parseFrom(final InputStream inputStream) throws IOException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseFrom(inputStream);
    }
    
    public static HandleCompletion_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static HandleCompletion_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static HandleCompletion_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static HandleCompletion_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static HandleCompletion_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleCompletion_Req)HandleCompletion_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final HandleCompletion_Req handleCompletion_Req) {
        return newBuilder().mergeFrom(handleCompletion_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        HandleCompletion_Req.PARSER = (Parser<HandleCompletion_Req>)new AbstractParser<HandleCompletion_Req>() {
            public HandleCompletion_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HandleCompletion_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new HandleCompletion_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
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
            if (HandleCompletion_Req.alwaysUseFieldBuilders) {}
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
            handleCompletion_Req.command_ = this.command_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            handleCompletion_Req.pos_ = this.pos_;
            handleCompletion_Req.bitField0_ = n;
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
                    this.command_ = handleCompletion_Req.command_;
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
}
