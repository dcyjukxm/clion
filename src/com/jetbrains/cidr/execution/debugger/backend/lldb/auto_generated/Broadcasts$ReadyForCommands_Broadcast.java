// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import com.google.protobuf.ByteString;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class ReadyForCommands_Broadcast extends GeneratedMessage implements ReadyForCommands_BroadcastOrBuilder
{
    private static final ReadyForCommands_Broadcast defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ReadyForCommands_Broadcast> PARSER;
    private int bitField0_;
    public static final int READY_FIELD_NUMBER = 1;
    private int ready_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ReadyForCommands_Broadcast(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ReadyForCommands_Broadcast(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ReadyForCommands_Broadcast getDefaultInstance() {
        return ReadyForCommands_Broadcast.defaultInstance;
    }
    
    public ReadyForCommands_Broadcast getDefaultInstanceForType() {
        return ReadyForCommands_Broadcast.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ReadyForCommands_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.bitField0_ |= 0x1;
                        this.ready_ = codedInputStream.readInt32();
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
        return Broadcasts.access$6400();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$6500().ensureFieldAccessorsInitialized((Class)ReadyForCommands_Broadcast.class, (Class)Builder.class);
    }
    
    public Parser<ReadyForCommands_Broadcast> getParserForType() {
        return ReadyForCommands_Broadcast.PARSER;
    }
    
    public boolean hasReady() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getReady() {
        return this.ready_;
    }
    
    private void a() {
        this.ready_ = 0;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasReady()) {
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
                codedOutputStream.writeInt32(1, this.ready_);
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
            n += CodedOutputStream.computeInt32Size(1, this.ready_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static ReadyForCommands_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(byteString);
    }
    
    public static ReadyForCommands_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ReadyForCommands_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(array);
    }
    
    public static ReadyForCommands_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ReadyForCommands_Broadcast parseFrom(final InputStream inputStream) throws IOException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(inputStream);
    }
    
    public static ReadyForCommands_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ReadyForCommands_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ReadyForCommands_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ReadyForCommands_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(codedInputStream);
    }
    
    public static ReadyForCommands_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ReadyForCommands_Broadcast readyForCommands_Broadcast) {
        return newBuilder().mergeFrom(readyForCommands_Broadcast);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ReadyForCommands_Broadcast.PARSER = (Parser<ReadyForCommands_Broadcast>)new AbstractParser<ReadyForCommands_Broadcast>() {
            public ReadyForCommands_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ReadyForCommands_Broadcast(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ReadyForCommands_Broadcast(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ReadyForCommands_BroadcastOrBuilder
    {
        private int bitField0_;
        private int ready_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.access$6400();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.access$6500().ensureFieldAccessorsInitialized((Class)ReadyForCommands_Broadcast.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (ReadyForCommands_Broadcast.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.ready_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Broadcasts.access$6400();
        }
        
        public ReadyForCommands_Broadcast getDefaultInstanceForType() {
            return ReadyForCommands_Broadcast.getDefaultInstance();
        }
        
        public ReadyForCommands_Broadcast build() {
            final ReadyForCommands_Broadcast buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public ReadyForCommands_Broadcast buildPartial() {
            final ReadyForCommands_Broadcast readyForCommands_Broadcast = new ReadyForCommands_Broadcast((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            readyForCommands_Broadcast.ready_ = this.ready_;
            readyForCommands_Broadcast.bitField0_ = n;
            this.onBuilt();
            return readyForCommands_Broadcast;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof ReadyForCommands_Broadcast) {
                return this.mergeFrom((ReadyForCommands_Broadcast)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ReadyForCommands_Broadcast readyForCommands_Broadcast) {
            if (readyForCommands_Broadcast == ReadyForCommands_Broadcast.getDefaultInstance()) {
                return this;
            }
            if (readyForCommands_Broadcast.hasReady()) {
                this.setReady(readyForCommands_Broadcast.getReady());
            }
            this.mergeUnknownFields(readyForCommands_Broadcast.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasReady();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ReadyForCommands_Broadcast readyForCommands_Broadcast = null;
            try {
                readyForCommands_Broadcast = (ReadyForCommands_Broadcast)ReadyForCommands_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                readyForCommands_Broadcast = (ReadyForCommands_Broadcast)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (readyForCommands_Broadcast != null) {
                        this.mergeFrom(readyForCommands_Broadcast);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b(ex2);
                }
            }
            return this;
        }
        
        public boolean hasReady() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getReady() {
            return this.ready_;
        }
        
        public Builder setReady(final int ready_) {
            this.bitField0_ |= 0x1;
            this.ready_ = ready_;
            this.onChanged();
            return this;
        }
        
        public Builder clearReady() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.ready_ = 0;
            this.onChanged();
            return this;
        }
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
