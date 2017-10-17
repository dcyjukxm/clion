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

public static final class StepOver_Req extends GeneratedMessage implements StepOver_ReqOrBuilder
{
    private static final StepOver_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<StepOver_Req> PARSER;
    private int bitField0_;
    public static final int THREAD_ID_FIELD_NUMBER = 2;
    private int threadId_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private StepOver_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private StepOver_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static StepOver_Req getDefaultInstance() {
        return StepOver_Req.defaultInstance;
    }
    
    public StepOver_Req getDefaultInstanceForType() {
        return StepOver_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private StepOver_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$25600();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$25700().ensureFieldAccessorsInitialized((Class)StepOver_Req.class, (Class)Builder.class);
    }
    
    public Parser<StepOver_Req> getParserForType() {
        return StepOver_Req.PARSER;
    }
    
    public boolean hasThreadId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    private void a() {
        this.threadId_ = 0;
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
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static StepOver_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (StepOver_Req)StepOver_Req.PARSER.parseFrom(byteString);
    }
    
    public static StepOver_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StepOver_Req)StepOver_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static StepOver_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (StepOver_Req)StepOver_Req.PARSER.parseFrom(array);
    }
    
    public static StepOver_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StepOver_Req)StepOver_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static StepOver_Req parseFrom(final InputStream inputStream) throws IOException {
        return (StepOver_Req)StepOver_Req.PARSER.parseFrom(inputStream);
    }
    
    public static StepOver_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepOver_Req)StepOver_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static StepOver_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (StepOver_Req)StepOver_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static StepOver_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepOver_Req)StepOver_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static StepOver_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (StepOver_Req)StepOver_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static StepOver_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepOver_Req)StepOver_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final StepOver_Req stepOver_Req) {
        return newBuilder().mergeFrom(stepOver_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        StepOver_Req.PARSER = (Parser<StepOver_Req>)new AbstractParser<StepOver_Req>() {
            public StepOver_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StepOver_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new StepOver_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements StepOver_ReqOrBuilder
    {
        private int bitField0_;
        private int threadId_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$25600();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$25700().ensureFieldAccessorsInitialized((Class)StepOver_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (StepOver_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.threadId_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$25600();
        }
        
        public StepOver_Req getDefaultInstanceForType() {
            return StepOver_Req.getDefaultInstance();
        }
        
        public StepOver_Req build() {
            final StepOver_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public StepOver_Req buildPartial() {
            final StepOver_Req stepOver_Req = new StepOver_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            stepOver_Req.threadId_ = this.threadId_;
            stepOver_Req.bitField0_ = n;
            this.onBuilt();
            return stepOver_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof StepOver_Req) {
                return this.mergeFrom((StepOver_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final StepOver_Req stepOver_Req) {
            if (stepOver_Req == StepOver_Req.getDefaultInstance()) {
                return this;
            }
            if (stepOver_Req.hasThreadId()) {
                this.setThreadId(stepOver_Req.getThreadId());
            }
            this.mergeUnknownFields(stepOver_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasThreadId();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            StepOver_Req stepOver_Req = null;
            try {
                stepOver_Req = (StepOver_Req)StepOver_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                stepOver_Req = (StepOver_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (stepOver_Req != null) {
                        this.mergeFrom(stepOver_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b(ex2);
                }
            }
            return this;
        }
        
        public boolean hasThreadId() {
            return (this.bitField0_ & 0x1) == 0x1;
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
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
