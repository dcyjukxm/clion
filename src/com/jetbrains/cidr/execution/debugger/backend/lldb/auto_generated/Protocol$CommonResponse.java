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

public static final class CommonResponse extends GeneratedMessage implements CommonResponseOrBuilder
{
    private static final CommonResponse defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<CommonResponse> PARSER;
    private int bitField0_;
    public static final int IS_VALID_FIELD_NUMBER = 1;
    private boolean isValid_;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
    private Object errorMessage_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private CommonResponse(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private CommonResponse(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static CommonResponse getDefaultInstance() {
        return CommonResponse.defaultInstance;
    }
    
    public CommonResponse getDefaultInstanceForType() {
        return CommonResponse.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private CommonResponse(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.isValid_ = codedInputStream.readBool();
                        continue;
                    }
                    case 18: {
                        this.bitField0_ |= 0x2;
                        this.errorMessage_ = codedInputStream.readBytes();
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
        return Protocol.access$000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$100().ensureFieldAccessorsInitialized((Class)CommonResponse.class, (Class)Builder.class);
    }
    
    public Parser<CommonResponse> getParserForType() {
        return CommonResponse.PARSER;
    }
    
    public boolean hasIsValid() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public boolean getIsValid() {
        return this.isValid_;
    }
    
    public boolean hasErrorMessage() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getErrorMessage() {
        final Object errorMessage_ = this.errorMessage_;
        if (errorMessage_ instanceof String) {
            return (String)errorMessage_;
        }
        final ByteString byteString = (ByteString)errorMessage_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.errorMessage_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getErrorMessageBytes() {
        final Object errorMessage_ = this.errorMessage_;
        if (errorMessage_ instanceof String) {
            return (ByteString)(this.errorMessage_ = ByteString.copyFromUtf8((String)errorMessage_));
        }
        return (ByteString)errorMessage_;
    }
    
    private void a() {
        this.isValid_ = false;
        this.errorMessage_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasIsValid()) {
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
                codedOutputStream.writeBool(1, this.isValid_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(2, this.getErrorMessageBytes());
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
            n += CodedOutputStream.computeBoolSize(1, this.isValid_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(2, this.getErrorMessageBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static CommonResponse parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (CommonResponse)CommonResponse.PARSER.parseFrom(byteString);
    }
    
    public static CommonResponse parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommonResponse)CommonResponse.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static CommonResponse parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (CommonResponse)CommonResponse.PARSER.parseFrom(array);
    }
    
    public static CommonResponse parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommonResponse)CommonResponse.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static CommonResponse parseFrom(final InputStream inputStream) throws IOException {
        return (CommonResponse)CommonResponse.PARSER.parseFrom(inputStream);
    }
    
    public static CommonResponse parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommonResponse)CommonResponse.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static CommonResponse parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (CommonResponse)CommonResponse.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static CommonResponse parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommonResponse)CommonResponse.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static CommonResponse parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (CommonResponse)CommonResponse.PARSER.parseFrom(codedInputStream);
    }
    
    public static CommonResponse parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommonResponse)CommonResponse.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final CommonResponse commonResponse) {
        return newBuilder().mergeFrom(commonResponse);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        CommonResponse.PARSER = (Parser<CommonResponse>)new AbstractParser<CommonResponse>() {
            public CommonResponse parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CommonResponse(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new CommonResponse(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements CommonResponseOrBuilder
    {
        private int bitField0_;
        private boolean isValid_;
        private Object errorMessage_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$000();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$100().ensureFieldAccessorsInitialized((Class)CommonResponse.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.errorMessage_ = "";
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.errorMessage_ = "";
            this.a();
        }
        
        private void a() {
            if (CommonResponse.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.isValid_ = false;
            this.bitField0_ &= 0xFFFFFFFE;
            this.errorMessage_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$000();
        }
        
        public CommonResponse getDefaultInstanceForType() {
            return CommonResponse.getDefaultInstance();
        }
        
        public CommonResponse build() {
            final CommonResponse buildPartial = this.buildPartial();
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
        
        public CommonResponse buildPartial() {
            final CommonResponse commonResponse = new CommonResponse((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            commonResponse.isValid_ = this.isValid_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            commonResponse.errorMessage_ = this.errorMessage_;
            commonResponse.bitField0_ = n;
            this.onBuilt();
            return commonResponse;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof CommonResponse) {
                    return this.mergeFrom((CommonResponse)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final CommonResponse commonResponse) {
            try {
                if (commonResponse == CommonResponse.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (commonResponse.hasIsValid()) {
                    this.setIsValid(commonResponse.getIsValid());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (commonResponse.hasErrorMessage()) {
                    this.bitField0_ |= 0x2;
                    this.errorMessage_ = commonResponse.errorMessage_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(commonResponse.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasIsValid()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CommonResponse commonResponse = null;
            try {
                commonResponse = (CommonResponse)CommonResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                commonResponse = (CommonResponse)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (commonResponse != null) {
                        this.mergeFrom(commonResponse);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasIsValid() {
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
        
        public boolean getIsValid() {
            return this.isValid_;
        }
        
        public Builder setIsValid(final boolean isValid_) {
            this.bitField0_ |= 0x1;
            this.isValid_ = isValid_;
            this.onChanged();
            return this;
        }
        
        public Builder clearIsValid() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.isValid_ = false;
            this.onChanged();
            return this;
        }
        
        public boolean hasErrorMessage() {
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
        
        public String getErrorMessage() {
            final Object errorMessage_ = this.errorMessage_;
            if (!(errorMessage_ instanceof String)) {
                return (String)(this.errorMessage_ = ((ByteString)errorMessage_).toStringUtf8());
            }
            return (String)errorMessage_;
        }
        
        public ByteString getErrorMessageBytes() {
            final Object errorMessage_ = this.errorMessage_;
            if (errorMessage_ instanceof String) {
                return (ByteString)(this.errorMessage_ = ByteString.copyFromUtf8((String)errorMessage_));
            }
            return (ByteString)errorMessage_;
        }
        
        public Builder setErrorMessage(final String errorMessage_) {
            try {
                if (errorMessage_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.errorMessage_ = errorMessage_;
            this.onChanged();
            return this;
        }
        
        public Builder clearErrorMessage() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.errorMessage_ = CommonResponse.getDefaultInstance().getErrorMessage();
            this.onChanged();
            return this;
        }
        
        public Builder setErrorMessageBytes(final ByteString errorMessage_) {
            try {
                if (errorMessage_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.errorMessage_ = errorMessage_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
