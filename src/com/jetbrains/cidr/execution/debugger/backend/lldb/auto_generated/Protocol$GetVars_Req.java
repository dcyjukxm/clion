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

public static final class GetVars_Req extends GeneratedMessage implements GetVars_ReqOrBuilder
{
    private static final GetVars_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetVars_Req> PARSER;
    private int bitField0_;
    public static final int THREAD_ID_FIELD_NUMBER = 2;
    private int threadId_;
    public static final int FRAME_INDEX_FIELD_NUMBER = 3;
    private int frameIndex_;
    public static final int STATICS_FIELD_NUMBER = 4;
    private boolean statics_;
    public static final int GLOBALS_FIELD_NUMBER = 5;
    private boolean globals_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetVars_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetVars_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetVars_Req getDefaultInstance() {
        return GetVars_Req.defaultInstance;
    }
    
    public GetVars_Req getDefaultInstanceForType() {
        return GetVars_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetVars_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.threadId_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.frameIndex_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x4;
                        this.statics_ = codedInputStream.readBool();
                        continue;
                    }
                    case 40: {
                        this.bitField0_ |= 0x8;
                        this.globals_ = codedInputStream.readBool();
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
        return Protocol.access$46000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$46100().ensureFieldAccessorsInitialized((Class)GetVars_Req.class, (Class)Builder.class);
    }
    
    public Parser<GetVars_Req> getParserForType() {
        return GetVars_Req.PARSER;
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
    
    public boolean hasStatics() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public boolean getStatics() {
        return this.statics_;
    }
    
    public boolean hasGlobals() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public boolean getGlobals() {
        return this.globals_;
    }
    
    private void a() {
        this.threadId_ = 0;
        this.frameIndex_ = 0;
        this.statics_ = false;
        this.globals_ = false;
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
        if (!this.hasStatics()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasGlobals()) {
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
                codedOutputStream.writeInt32(2, this.threadId_);
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
                codedOutputStream.writeBool(4, this.statics_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeBool(5, this.globals_);
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
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
            n += CodedOutputStream.computeInt32Size(2, this.threadId_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(3, this.frameIndex_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBoolSize(4, this.statics_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeBoolSize(5, this.globals_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetVars_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetVars_Req)GetVars_Req.PARSER.parseFrom(byteString);
    }
    
    public static GetVars_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetVars_Req)GetVars_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetVars_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetVars_Req)GetVars_Req.PARSER.parseFrom(array);
    }
    
    public static GetVars_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetVars_Req)GetVars_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetVars_Req parseFrom(final InputStream inputStream) throws IOException {
        return (GetVars_Req)GetVars_Req.PARSER.parseFrom(inputStream);
    }
    
    public static GetVars_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetVars_Req)GetVars_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetVars_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetVars_Req)GetVars_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetVars_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetVars_Req)GetVars_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetVars_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetVars_Req)GetVars_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetVars_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetVars_Req)GetVars_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetVars_Req getVars_Req) {
        return newBuilder().mergeFrom(getVars_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetVars_Req.PARSER = (Parser<GetVars_Req>)new AbstractParser<GetVars_Req>() {
            public GetVars_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetVars_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetVars_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetVars_ReqOrBuilder
    {
        private int bitField0_;
        private int threadId_;
        private int frameIndex_;
        private boolean statics_;
        private boolean globals_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$46000();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$46100().ensureFieldAccessorsInitialized((Class)GetVars_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (GetVars_Req.alwaysUseFieldBuilders) {}
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
            this.statics_ = false;
            this.bitField0_ &= 0xFFFFFFFB;
            this.globals_ = false;
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$46000();
        }
        
        public GetVars_Req getDefaultInstanceForType() {
            return GetVars_Req.getDefaultInstance();
        }
        
        public GetVars_Req build() {
            final GetVars_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public GetVars_Req buildPartial() {
            final GetVars_Req getVars_Req = new GetVars_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            getVars_Req.threadId_ = this.threadId_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            getVars_Req.frameIndex_ = this.frameIndex_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            getVars_Req.statics_ = this.statics_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            getVars_Req.globals_ = this.globals_;
            getVars_Req.bitField0_ = n;
            this.onBuilt();
            return getVars_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof GetVars_Req) {
                return this.mergeFrom((GetVars_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetVars_Req getVars_Req) {
            if (getVars_Req == GetVars_Req.getDefaultInstance()) {
                return this;
            }
            if (getVars_Req.hasThreadId()) {
                this.setThreadId(getVars_Req.getThreadId());
            }
            if (getVars_Req.hasFrameIndex()) {
                this.setFrameIndex(getVars_Req.getFrameIndex());
            }
            if (getVars_Req.hasStatics()) {
                this.setStatics(getVars_Req.getStatics());
            }
            if (getVars_Req.hasGlobals()) {
                this.setGlobals(getVars_Req.getGlobals());
            }
            this.mergeUnknownFields(getVars_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasThreadId() && this.hasFrameIndex() && this.hasStatics() && this.hasGlobals();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetVars_Req getVars_Req = null;
            try {
                getVars_Req = (GetVars_Req)GetVars_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getVars_Req = (GetVars_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getVars_Req != null) {
                        this.mergeFrom(getVars_Req);
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
        
        public boolean hasFrameIndex() {
            return (this.bitField0_ & 0x2) == 0x2;
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
        
        public boolean hasStatics() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public boolean getStatics() {
            return this.statics_;
        }
        
        public Builder setStatics(final boolean statics_) {
            this.bitField0_ |= 0x4;
            this.statics_ = statics_;
            this.onChanged();
            return this;
        }
        
        public Builder clearStatics() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.statics_ = false;
            this.onChanged();
            return this;
        }
        
        public boolean hasGlobals() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public boolean getGlobals() {
            return this.globals_;
        }
        
        public Builder setGlobals(final boolean globals_) {
            this.bitField0_ |= 0x8;
            this.globals_ = globals_;
            this.onChanged();
            return this;
        }
        
        public Builder clearGlobals() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.globals_ = false;
            this.onChanged();
            return this;
        }
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
