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

public static final class StepInto_Req extends GeneratedMessage implements StepInto_ReqOrBuilder
{
    private static final StepInto_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<StepInto_Req> PARSER;
    private int bitField0_;
    public static final int THREAD_ID_FIELD_NUMBER = 2;
    private int threadId_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private StepInto_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private StepInto_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static StepInto_Req getDefaultInstance() {
        return StepInto_Req.defaultInstance;
    }
    
    public StepInto_Req getDefaultInstanceForType() {
        return StepInto_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private StepInto_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$23800();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$23900().ensureFieldAccessorsInitialized((Class)StepInto_Req.class, (Class)Builder.class);
    }
    
    public Parser<StepInto_Req> getParserForType() {
        return StepInto_Req.PARSER;
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
    
    public static StepInto_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (StepInto_Req)StepInto_Req.PARSER.parseFrom(byteString);
    }
    
    public static StepInto_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StepInto_Req)StepInto_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static StepInto_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (StepInto_Req)StepInto_Req.PARSER.parseFrom(array);
    }
    
    public static StepInto_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StepInto_Req)StepInto_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static StepInto_Req parseFrom(final InputStream inputStream) throws IOException {
        return (StepInto_Req)StepInto_Req.PARSER.parseFrom(inputStream);
    }
    
    public static StepInto_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepInto_Req)StepInto_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static StepInto_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (StepInto_Req)StepInto_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static StepInto_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepInto_Req)StepInto_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static StepInto_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (StepInto_Req)StepInto_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static StepInto_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StepInto_Req)StepInto_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final StepInto_Req stepInto_Req) {
        return newBuilder().mergeFrom(stepInto_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        StepInto_Req.PARSER = (Parser<StepInto_Req>)new AbstractParser<StepInto_Req>() {
            public StepInto_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StepInto_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new StepInto_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements StepInto_ReqOrBuilder
    {
        private int bitField0_;
        private int threadId_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$23800();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$23900().ensureFieldAccessorsInitialized((Class)StepInto_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (StepInto_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.threadId_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$23800();
        }
        
        public StepInto_Req getDefaultInstanceForType() {
            return StepInto_Req.getDefaultInstance();
        }
        
        public StepInto_Req build() {
            final StepInto_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public StepInto_Req buildPartial() {
            final StepInto_Req stepInto_Req = new StepInto_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            stepInto_Req.threadId_ = this.threadId_;
            stepInto_Req.bitField0_ = n;
            this.onBuilt();
            return stepInto_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof StepInto_Req) {
                return this.mergeFrom((StepInto_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final StepInto_Req stepInto_Req) {
            if (stepInto_Req == StepInto_Req.getDefaultInstance()) {
                return this;
            }
            if (stepInto_Req.hasThreadId()) {
                this.setThreadId(stepInto_Req.getThreadId());
            }
            this.mergeUnknownFields(stepInto_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasThreadId();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            StepInto_Req stepInto_Req = null;
            try {
                stepInto_Req = (StepInto_Req)StepInto_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                stepInto_Req = (StepInto_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (stepInto_Req != null) {
                        this.mergeFrom(stepInto_Req);
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
