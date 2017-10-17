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

public static final class GetArraySlice_Req extends GeneratedMessage implements GetArraySlice_ReqOrBuilder
{
    private static final GetArraySlice_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetArraySlice_Req> PARSER;
    private int bitField0_;
    public static final int VALUE_ID_FIELD_NUMBER = 2;
    private int valueId_;
    public static final int OFFSET_FIELD_NUMBER = 3;
    private int offset_;
    public static final int COUNT_FIELD_NUMBER = 4;
    private int count_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetArraySlice_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetArraySlice_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetArraySlice_Req getDefaultInstance() {
        return GetArraySlice_Req.defaultInstance;
    }
    
    public GetArraySlice_Req getDefaultInstanceForType() {
        return GetArraySlice_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetArraySlice_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.valueId_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.offset_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x4;
                        this.count_ = codedInputStream.readInt32();
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
        return Protocol.access$36700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$36800().ensureFieldAccessorsInitialized((Class)GetArraySlice_Req.class, (Class)Builder.class);
    }
    
    public Parser<GetArraySlice_Req> getParserForType() {
        return GetArraySlice_Req.PARSER;
    }
    
    public boolean hasValueId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getValueId() {
        return this.valueId_;
    }
    
    public boolean hasOffset() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getOffset() {
        return this.offset_;
    }
    
    public boolean hasCount() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public int getCount() {
        return this.count_;
    }
    
    private void a() {
        this.valueId_ = 0;
        this.offset_ = 0;
        this.count_ = 0;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasValueId()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasOffset()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasCount()) {
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
                codedOutputStream.writeInt32(2, this.valueId_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeInt32(3, this.offset_);
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
        this.getUnknownFields().writeTo(codedOutputStream);
    }
    
    public int getSerializedSize() {
        final int memoizedSerializedSize = this.memoizedSerializedSize;
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        int n = 0;
        if ((this.bitField0_ & 0x1) == 0x1) {
            n += CodedOutputStream.computeInt32Size(2, this.valueId_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(3, this.offset_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeInt32Size(4, this.count_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetArraySlice_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseFrom(byteString);
    }
    
    public static GetArraySlice_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetArraySlice_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseFrom(array);
    }
    
    public static GetArraySlice_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetArraySlice_Req parseFrom(final InputStream inputStream) throws IOException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseFrom(inputStream);
    }
    
    public static GetArraySlice_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetArraySlice_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetArraySlice_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetArraySlice_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetArraySlice_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetArraySlice_Req)GetArraySlice_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetArraySlice_Req getArraySlice_Req) {
        return newBuilder().mergeFrom(getArraySlice_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetArraySlice_Req.PARSER = (Parser<GetArraySlice_Req>)new AbstractParser<GetArraySlice_Req>() {
            public GetArraySlice_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetArraySlice_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetArraySlice_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetArraySlice_ReqOrBuilder
    {
        private int bitField0_;
        private int valueId_;
        private int offset_;
        private int count_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$36700();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$36800().ensureFieldAccessorsInitialized((Class)GetArraySlice_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (GetArraySlice_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.valueId_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            this.offset_ = 0;
            this.bitField0_ &= 0xFFFFFFFD;
            this.count_ = 0;
            this.bitField0_ &= 0xFFFFFFFB;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$36700();
        }
        
        public GetArraySlice_Req getDefaultInstanceForType() {
            return GetArraySlice_Req.getDefaultInstance();
        }
        
        public GetArraySlice_Req build() {
            final GetArraySlice_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public GetArraySlice_Req buildPartial() {
            final GetArraySlice_Req getArraySlice_Req = new GetArraySlice_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            getArraySlice_Req.valueId_ = this.valueId_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            getArraySlice_Req.offset_ = this.offset_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            getArraySlice_Req.count_ = this.count_;
            getArraySlice_Req.bitField0_ = n;
            this.onBuilt();
            return getArraySlice_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof GetArraySlice_Req) {
                return this.mergeFrom((GetArraySlice_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetArraySlice_Req getArraySlice_Req) {
            if (getArraySlice_Req == GetArraySlice_Req.getDefaultInstance()) {
                return this;
            }
            if (getArraySlice_Req.hasValueId()) {
                this.setValueId(getArraySlice_Req.getValueId());
            }
            if (getArraySlice_Req.hasOffset()) {
                this.setOffset(getArraySlice_Req.getOffset());
            }
            if (getArraySlice_Req.hasCount()) {
                this.setCount(getArraySlice_Req.getCount());
            }
            this.mergeUnknownFields(getArraySlice_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasValueId() && this.hasOffset() && this.hasCount();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetArraySlice_Req getArraySlice_Req = null;
            try {
                getArraySlice_Req = (GetArraySlice_Req)GetArraySlice_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getArraySlice_Req = (GetArraySlice_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getArraySlice_Req != null) {
                        this.mergeFrom(getArraySlice_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b(ex2);
                }
            }
            return this;
        }
        
        public boolean hasValueId() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getValueId() {
            return this.valueId_;
        }
        
        public Builder setValueId(final int valueId_) {
            this.bitField0_ |= 0x1;
            this.valueId_ = valueId_;
            this.onChanged();
            return this;
        }
        
        public Builder clearValueId() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.valueId_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasOffset() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public int getOffset() {
            return this.offset_;
        }
        
        public Builder setOffset(final int offset_) {
            this.bitField0_ |= 0x2;
            this.offset_ = offset_;
            this.onChanged();
            return this;
        }
        
        public Builder clearOffset() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.offset_ = 0;
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
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
