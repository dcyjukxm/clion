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

public static final class GetChildrenCount_Req extends GeneratedMessage implements GetChildrenCount_ReqOrBuilder
{
    private static final GetChildrenCount_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetChildrenCount_Req> PARSER;
    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 2;
    private int id_;
    public static final int EXPRESSION_FIELD_NUMBER = 3;
    private Object expression_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetChildrenCount_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetChildrenCount_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetChildrenCount_Req getDefaultInstance() {
        return GetChildrenCount_Req.defaultInstance;
    }
    
    public GetChildrenCount_Req getDefaultInstanceForType() {
        return GetChildrenCount_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetChildrenCount_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$42900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$43000().ensureFieldAccessorsInitialized((Class)GetChildrenCount_Req.class, (Class)Builder.class);
    }
    
    public Parser<GetChildrenCount_Req> getParserForType() {
        return GetChildrenCount_Req.PARSER;
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
    
    private void a() {
        this.id_ = 0;
        this.expression_ = "";
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
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetChildrenCount_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseFrom(byteString);
    }
    
    public static GetChildrenCount_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetChildrenCount_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseFrom(array);
    }
    
    public static GetChildrenCount_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetChildrenCount_Req parseFrom(final InputStream inputStream) throws IOException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseFrom(inputStream);
    }
    
    public static GetChildrenCount_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetChildrenCount_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetChildrenCount_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetChildrenCount_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetChildrenCount_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetChildrenCount_Req getChildrenCount_Req) {
        return newBuilder().mergeFrom(getChildrenCount_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetChildrenCount_Req.PARSER = (Parser<GetChildrenCount_Req>)new AbstractParser<GetChildrenCount_Req>() {
            public GetChildrenCount_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetChildrenCount_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetChildrenCount_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetChildrenCount_ReqOrBuilder
    {
        private int bitField0_;
        private int id_;
        private Object expression_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$42900();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$43000().ensureFieldAccessorsInitialized((Class)GetChildrenCount_Req.class, (Class)Builder.class);
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
            if (GetChildrenCount_Req.alwaysUseFieldBuilders) {}
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
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$42900();
        }
        
        public GetChildrenCount_Req getDefaultInstanceForType() {
            return GetChildrenCount_Req.getDefaultInstance();
        }
        
        public GetChildrenCount_Req build() {
            final GetChildrenCount_Req buildPartial = this.buildPartial();
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
        
        public GetChildrenCount_Req buildPartial() {
            final GetChildrenCount_Req getChildrenCount_Req = new GetChildrenCount_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            getChildrenCount_Req.id_ = this.id_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            getChildrenCount_Req.expression_ = this.expression_;
            getChildrenCount_Req.bitField0_ = n;
            this.onBuilt();
            return getChildrenCount_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof GetChildrenCount_Req) {
                    return this.mergeFrom((GetChildrenCount_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetChildrenCount_Req getChildrenCount_Req) {
            try {
                if (getChildrenCount_Req == GetChildrenCount_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (getChildrenCount_Req.hasId()) {
                    this.setId(getChildrenCount_Req.getId());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (getChildrenCount_Req.hasExpression()) {
                    this.bitField0_ |= 0x2;
                    this.expression_ = getChildrenCount_Req.expression_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(getChildrenCount_Req.getUnknownFields());
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
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetChildrenCount_Req getChildrenCount_Req = null;
            try {
                getChildrenCount_Req = (GetChildrenCount_Req)GetChildrenCount_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getChildrenCount_Req = (GetChildrenCount_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getChildrenCount_Req != null) {
                        this.mergeFrom(getChildrenCount_Req);
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
            this.expression_ = GetChildrenCount_Req.getDefaultInstance().getExpression();
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
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
