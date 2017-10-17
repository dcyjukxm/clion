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

public static final class GetValueChildren_Req extends GeneratedMessage implements GetValueChildren_ReqOrBuilder
{
    private static final GetValueChildren_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetValueChildren_Req> PARSER;
    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 2;
    private int id_;
    public static final int EXPRESSION_FIELD_NUMBER = 3;
    private Object expression_;
    public static final int OFFSET_FIELD_NUMBER = 4;
    private int offset_;
    public static final int COUNT_FIELD_NUMBER = 5;
    private int count_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetValueChildren_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetValueChildren_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetValueChildren_Req getDefaultInstance() {
        return GetValueChildren_Req.defaultInstance;
    }
    
    public GetValueChildren_Req getDefaultInstanceForType() {
        return GetValueChildren_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetValueChildren_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.id_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 26: {
                        this.bitField0_ |= 0x2;
                        this.expression_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x4;
                        this.offset_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 40: {
                        this.bitField0_ |= 0x8;
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
        return Protocol.access$35500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$35600().ensureFieldAccessorsInitialized((Class)GetValueChildren_Req.class, (Class)Builder.class);
    }
    
    public Parser<GetValueChildren_Req> getParserForType() {
        return GetValueChildren_Req.PARSER;
    }
    
    public boolean hasId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getId() {
        return this.id_;
    }
    
    public boolean hasExpression() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getExpression() {
        final Object expression_ = this.expression_;
        if (expression_ instanceof String) {
            return (String)expression_;
        }
        final ByteString byteString = (ByteString)expression_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.expression_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getExpressionBytes() {
        final Object expression_ = this.expression_;
        if (expression_ instanceof String) {
            return (ByteString)(this.expression_ = ByteString.copyFromUtf8((String)expression_));
        }
        return (ByteString)expression_;
    }
    
    public boolean hasOffset() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public int getOffset() {
        return this.offset_;
    }
    
    public boolean hasCount() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public int getCount() {
        return this.count_;
    }
    
    private void a() {
        this.id_ = 0;
        this.expression_ = "";
        this.offset_ = 0;
        this.count_ = 0;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasId()) {
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
                codedOutputStream.writeInt32(2, this.id_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(3, this.getExpressionBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeInt32(4, this.offset_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeInt32(5, this.count_);
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
            n += CodedOutputStream.computeInt32Size(2, this.id_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(3, this.getExpressionBytes());
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeInt32Size(4, this.offset_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeInt32Size(5, this.count_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetValueChildren_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseFrom(byteString);
    }
    
    public static GetValueChildren_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetValueChildren_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseFrom(array);
    }
    
    public static GetValueChildren_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetValueChildren_Req parseFrom(final InputStream inputStream) throws IOException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseFrom(inputStream);
    }
    
    public static GetValueChildren_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueChildren_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetValueChildren_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueChildren_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetValueChildren_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueChildren_Req)GetValueChildren_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetValueChildren_Req getValueChildren_Req) {
        return newBuilder().mergeFrom(getValueChildren_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetValueChildren_Req.PARSER = (Parser<GetValueChildren_Req>)new AbstractParser<GetValueChildren_Req>() {
            public GetValueChildren_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetValueChildren_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetValueChildren_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueChildren_ReqOrBuilder
    {
        private int bitField0_;
        private int id_;
        private Object expression_;
        private int offset_;
        private int count_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$35500();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$35600().ensureFieldAccessorsInitialized((Class)GetValueChildren_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.expression_ = "";
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.expression_ = "";
            this.b();
        }
        
        private void b() {
            if (GetValueChildren_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.id_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            this.expression_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            this.offset_ = 0;
            this.bitField0_ &= 0xFFFFFFFB;
            this.count_ = 0;
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$35500();
        }
        
        public GetValueChildren_Req getDefaultInstanceForType() {
            return GetValueChildren_Req.getDefaultInstance();
        }
        
        public GetValueChildren_Req build() {
            final GetValueChildren_Req buildPartial = this.buildPartial();
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
        
        public GetValueChildren_Req buildPartial() {
            final GetValueChildren_Req getValueChildren_Req = new GetValueChildren_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            getValueChildren_Req.id_ = this.id_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            getValueChildren_Req.expression_ = this.expression_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            getValueChildren_Req.offset_ = this.offset_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            getValueChildren_Req.count_ = this.count_;
            getValueChildren_Req.bitField0_ = n;
            this.onBuilt();
            return getValueChildren_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof GetValueChildren_Req) {
                    return this.mergeFrom((GetValueChildren_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetValueChildren_Req getValueChildren_Req) {
            try {
                if (getValueChildren_Req == GetValueChildren_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (getValueChildren_Req.hasId()) {
                    this.setId(getValueChildren_Req.getId());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (getValueChildren_Req.hasExpression()) {
                    this.bitField0_ |= 0x2;
                    this.expression_ = getValueChildren_Req.expression_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (getValueChildren_Req.hasOffset()) {
                    this.setOffset(getValueChildren_Req.getOffset());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (getValueChildren_Req.hasCount()) {
                    this.setCount(getValueChildren_Req.getCount());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            this.mergeUnknownFields(getValueChildren_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasId()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasOffset()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.hasCount()) {
                    return false;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetValueChildren_Req getValueChildren_Req = null;
            try {
                getValueChildren_Req = (GetValueChildren_Req)GetValueChildren_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getValueChildren_Req = (GetValueChildren_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getValueChildren_Req != null) {
                        this.mergeFrom(getValueChildren_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasId() {
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
        
        public int getId() {
            return this.id_;
        }
        
        public Builder setId(final int id_) {
            this.bitField0_ |= 0x1;
            this.id_ = id_;
            this.onChanged();
            return this;
        }
        
        public Builder clearId() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.id_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasExpression() {
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
        
        public String getExpression() {
            final Object expression_ = this.expression_;
            if (!(expression_ instanceof String)) {
                return (String)(this.expression_ = ((ByteString)expression_).toStringUtf8());
            }
            return (String)expression_;
        }
        
        public ByteString getExpressionBytes() {
            final Object expression_ = this.expression_;
            if (expression_ instanceof String) {
                return (ByteString)(this.expression_ = ByteString.copyFromUtf8((String)expression_));
            }
            return (ByteString)expression_;
        }
        
        public Builder setExpression(final String expression_) {
            try {
                if (expression_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.expression_ = expression_;
            this.onChanged();
            return this;
        }
        
        public Builder clearExpression() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.expression_ = GetValueChildren_Req.getDefaultInstance().getExpression();
            this.onChanged();
            return this;
        }
        
        public Builder setExpressionBytes(final ByteString expression_) {
            try {
                if (expression_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.expression_ = expression_;
            this.onChanged();
            return this;
        }
        
        public boolean hasOffset() {
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
        
        public int getOffset() {
            return this.offset_;
        }
        
        public Builder setOffset(final int offset_) {
            this.bitField0_ |= 0x4;
            this.offset_ = offset_;
            this.onChanged();
            return this;
        }
        
        public Builder clearOffset() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.offset_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasCount() {
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public int getCount() {
            return this.count_;
        }
        
        public Builder setCount(final int count_) {
            this.bitField0_ |= 0x8;
            this.count_ = count_;
            this.onChanged();
            return this;
        }
        
        public Builder clearCount() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.count_ = 0;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
