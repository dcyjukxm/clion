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

public static final class GetFrames_Req extends GeneratedMessage implements GetFrames_ReqOrBuilder
{
    private static final GetFrames_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetFrames_Req> PARSER;
    private int bitField0_;
    public static final int THREAD_ID_FIELD_NUMBER = 2;
    private int threadId_;
    public static final int FROM_INDEX_FIELD_NUMBER = 3;
    private int fromIndex_;
    public static final int COUNT_FIELD_NUMBER = 4;
    private int count_;
    public static final int UNTIL_VALID_LINE_ENTRY_FIELD_NUMBER = 5;
    private boolean untilValidLineEntry_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetFrames_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetFrames_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetFrames_Req getDefaultInstance() {
        return GetFrames_Req.defaultInstance;
    }
    
    public GetFrames_Req getDefaultInstanceForType() {
        return GetFrames_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetFrames_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.fromIndex_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x4;
                        this.count_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 40: {
                        this.bitField0_ |= 0x8;
                        this.untilValidLineEntry_ = codedInputStream.readBool();
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
        return Protocol.access$17100();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$17200().ensureFieldAccessorsInitialized((Class)GetFrames_Req.class, (Class)Builder.class);
    }
    
    public Parser<GetFrames_Req> getParserForType() {
        return GetFrames_Req.PARSER;
    }
    
    public boolean hasThreadId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    public boolean hasFromIndex() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getFromIndex() {
        return this.fromIndex_;
    }
    
    public boolean hasCount() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public int getCount() {
        return this.count_;
    }
    
    public boolean hasUntilValidLineEntry() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public boolean getUntilValidLineEntry() {
        return this.untilValidLineEntry_;
    }
    
    private void a() {
        this.threadId_ = 0;
        this.fromIndex_ = 0;
        this.count_ = 0;
        this.untilValidLineEntry_ = false;
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
        if (!this.hasFromIndex()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasCount()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasUntilValidLineEntry()) {
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
                codedOutputStream.writeInt32(3, this.fromIndex_);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeInt32(4, this.count_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeBool(5, this.untilValidLineEntry_);
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
            n += CodedOutputStream.computeUInt32Size(2, this.threadId_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(3, this.fromIndex_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeInt32Size(4, this.count_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeBoolSize(5, this.untilValidLineEntry_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetFrames_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseFrom(byteString);
    }
    
    public static GetFrames_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetFrames_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseFrom(array);
    }
    
    public static GetFrames_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetFrames_Req parseFrom(final InputStream inputStream) throws IOException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseFrom(inputStream);
    }
    
    public static GetFrames_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetFrames_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetFrames_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetFrames_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetFrames_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetFrames_Req)GetFrames_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetFrames_Req getFrames_Req) {
        return newBuilder().mergeFrom(getFrames_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetFrames_Req.PARSER = (Parser<GetFrames_Req>)new AbstractParser<GetFrames_Req>() {
            public GetFrames_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetFrames_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetFrames_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetFrames_ReqOrBuilder
    {
        private int bitField0_;
        private int threadId_;
        private int fromIndex_;
        private int count_;
        private boolean untilValidLineEntry_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$17100();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$17200().ensureFieldAccessorsInitialized((Class)GetFrames_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (GetFrames_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.threadId_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            this.fromIndex_ = 0;
            this.bitField0_ &= 0xFFFFFFFD;
            this.count_ = 0;
            this.bitField0_ &= 0xFFFFFFFB;
            this.untilValidLineEntry_ = false;
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$17100();
        }
        
        public GetFrames_Req getDefaultInstanceForType() {
            return GetFrames_Req.getDefaultInstance();
        }
        
        public GetFrames_Req build() {
            final GetFrames_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public GetFrames_Req buildPartial() {
            final GetFrames_Req getFrames_Req = new GetFrames_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            getFrames_Req.threadId_ = this.threadId_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            getFrames_Req.fromIndex_ = this.fromIndex_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            getFrames_Req.count_ = this.count_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            getFrames_Req.untilValidLineEntry_ = this.untilValidLineEntry_;
            getFrames_Req.bitField0_ = n;
            this.onBuilt();
            return getFrames_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof GetFrames_Req) {
                return this.mergeFrom((GetFrames_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetFrames_Req getFrames_Req) {
            if (getFrames_Req == GetFrames_Req.getDefaultInstance()) {
                return this;
            }
            if (getFrames_Req.hasThreadId()) {
                this.setThreadId(getFrames_Req.getThreadId());
            }
            if (getFrames_Req.hasFromIndex()) {
                this.setFromIndex(getFrames_Req.getFromIndex());
            }
            if (getFrames_Req.hasCount()) {
                this.setCount(getFrames_Req.getCount());
            }
            if (getFrames_Req.hasUntilValidLineEntry()) {
                this.setUntilValidLineEntry(getFrames_Req.getUntilValidLineEntry());
            }
            this.mergeUnknownFields(getFrames_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasThreadId() && this.hasFromIndex() && this.hasCount() && this.hasUntilValidLineEntry();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetFrames_Req getFrames_Req = null;
            try {
                getFrames_Req = (GetFrames_Req)GetFrames_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getFrames_Req = (GetFrames_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getFrames_Req != null) {
                        this.mergeFrom(getFrames_Req);
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
        
        public boolean hasFromIndex() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public int getFromIndex() {
            return this.fromIndex_;
        }
        
        public Builder setFromIndex(final int fromIndex_) {
            this.bitField0_ |= 0x2;
            this.fromIndex_ = fromIndex_;
            this.onChanged();
            return this;
        }
        
        public Builder clearFromIndex() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.fromIndex_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasCount() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public int getCount() {
            return this.count_;
        }
        
        public Builder setCount(final int count_) {
            this.bitField0_ |= 0x4;
            this.count_ = count_;
            this.onChanged();
            return this;
        }
        
        public Builder clearCount() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.count_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasUntilValidLineEntry() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public boolean getUntilValidLineEntry() {
            return this.untilValidLineEntry_;
        }
        
        public Builder setUntilValidLineEntry(final boolean untilValidLineEntry_) {
            this.bitField0_ |= 0x8;
            this.untilValidLineEntry_ = untilValidLineEntry_;
            this.onChanged();
            return this;
        }
        
        public Builder clearUntilValidLineEntry() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.untilValidLineEntry_ = false;
            this.onChanged();
            return this;
        }
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
