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

public static final class StepOut_Req extends GeneratedMessage implements StepOut_ReqOrBuilder
{
    private static final StepOut_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<StepOut_Req> PARSER;
    private int bitField0_;
    public static final int THREAD_ID_FIELD_NUMBER = 2;
    private int threadId_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private StepOut_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private StepOut_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static StepOut_Req getDefaultInstance() {
        return StepOut_Req.defaultInstance;
    }
    
    public StepOut_Req getDefaultInstanceForType() {
        return StepOut_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private StepOut_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$27400();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$27500().ensureFieldAccessorsInitialized((Class)StepOut_Req.class, (Class)Builder.class);
    }
    
    public Parser<StepOut_Req> getParserForType() {
        return StepOut_Req.PARSER;
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
    
    public static StepOut_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (StepOut_Req)StepOut_Req.PARSER.parseFrom(byteString);
    }
    
    public static StepOut_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StepOut_Req)StepOut_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static StepOut_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (StepOut_Req)StepOut_Req.PARSER.parseFrom(array);
    }
    
    public static StepOut_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StepOut_Req)StepOut_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static StepOut_Req parseFrom(final InputStream inputStream) throws IOException {
        return (StepOut_Req)StepOut_Req.PARSER.parseFrom(inputStream);
    }
    
    public static StepOut_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepOut_Req)StepOut_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static StepOut_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (StepOut_Req)StepOut_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static StepOut_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepOut_Req)StepOut_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static StepOut_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (StepOut_Req)StepOut_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static StepOut_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepOut_Req)StepOut_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final StepOut_Req stepOut_Req) {
        return newBuilder().mergeFrom(stepOut_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        StepOut_Req.PARSER = (Parser<StepOut_Req>)new AbstractParser<StepOut_Req>() {
            public StepOut_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StepOut_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new StepOut_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements StepOut_ReqOrBuilder
    {
        private int bitField0_;
        private int threadId_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$27400();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$27500().ensureFieldAccessorsInitialized((Class)StepOut_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (StepOut_Req.alwaysUseFieldBuilders) {}
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
            return Protocol.access$27400();
        }
        
        public StepOut_Req getDefaultInstanceForType() {
            return StepOut_Req.getDefaultInstance();
        }
        
        public StepOut_Req build() {
            final StepOut_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public StepOut_Req buildPartial() {
            final StepOut_Req stepOut_Req = new StepOut_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            stepOut_Req.threadId_ = this.threadId_;
            stepOut_Req.bitField0_ = n;
            this.onBuilt();
            return stepOut_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof StepOut_Req) {
                return this.mergeFrom((StepOut_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final StepOut_Req stepOut_Req) {
            if (stepOut_Req == StepOut_Req.getDefaultInstance()) {
                return this;
            }
            if (stepOut_Req.hasThreadId()) {
                this.setThreadId(stepOut_Req.getThreadId());
            }
            this.mergeUnknownFields(stepOut_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasThreadId();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            StepOut_Req stepOut_Req = null;
            try {
                stepOut_Req = (StepOut_Req)StepOut_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                stepOut_Req = (StepOut_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (stepOut_Req != null) {
                        this.mergeFrom(stepOut_Req);
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
