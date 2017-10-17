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

public static final class ConnectPlatform_Req extends GeneratedMessage implements ConnectPlatform_ReqOrBuilder
{
    private static final ConnectPlatform_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ConnectPlatform_Req> PARSER;
    private int bitField0_;
    public static final int PLATFORM_FIELD_NUMBER = 2;
    private Object platform_;
    public static final int URL_FIELD_NUMBER = 3;
    private Object url_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ConnectPlatform_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ConnectPlatform_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ConnectPlatform_Req getDefaultInstance() {
        return ConnectPlatform_Req.defaultInstance;
    }
    
    public ConnectPlatform_Req getDefaultInstanceForType() {
        return ConnectPlatform_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ConnectPlatform_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 18: {
                        this.bitField0_ |= 0x1;
                        this.platform_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 26: {
                        this.bitField0_ |= 0x2;
                        this.url_ = codedInputStream.readBytes();
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
        return Protocol.access$56300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$56400().ensureFieldAccessorsInitialized((Class)ConnectPlatform_Req.class, (Class)Builder.class);
    }
    
    public Parser<ConnectPlatform_Req> getParserForType() {
        return ConnectPlatform_Req.PARSER;
    }
    
    public boolean hasPlatform() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getPlatform() {
        final Object platform_ = this.platform_;
        if (platform_ instanceof String) {
            return (String)platform_;
        }
        final ByteString byteString = (ByteString)platform_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.platform_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getPlatformBytes() {
        final Object platform_ = this.platform_;
        if (platform_ instanceof String) {
            return (ByteString)(this.platform_ = ByteString.copyFromUtf8((String)platform_));
        }
        return (ByteString)platform_;
    }
    
    public boolean hasUrl() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getUrl() {
        final Object url_ = this.url_;
        if (url_ instanceof String) {
            return (String)url_;
        }
        final ByteString byteString = (ByteString)url_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.url_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getUrlBytes() {
        final Object url_ = this.url_;
        if (url_ instanceof String) {
            return (ByteString)(this.url_ = ByteString.copyFromUtf8((String)url_));
        }
        return (ByteString)url_;
    }
    
    private void a() {
        this.platform_ = "";
        this.url_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasPlatform()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasUrl()) {
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
                codedOutputStream.writeBytes(2, this.getPlatformBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(3, this.getUrlBytes());
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
            n += CodedOutputStream.computeBytesSize(2, this.getPlatformBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(3, this.getUrlBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static ConnectPlatform_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseFrom(byteString);
    }
    
    public static ConnectPlatform_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ConnectPlatform_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseFrom(array);
    }
    
    public static ConnectPlatform_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ConnectPlatform_Req parseFrom(final InputStream inputStream) throws IOException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseFrom(inputStream);
    }
    
    public static ConnectPlatform_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ConnectPlatform_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ConnectPlatform_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ConnectPlatform_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static ConnectPlatform_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ConnectPlatform_Req connectPlatform_Req) {
        return newBuilder().mergeFrom(connectPlatform_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ConnectPlatform_Req.PARSER = (Parser<ConnectPlatform_Req>)new AbstractParser<ConnectPlatform_Req>() {
            public ConnectPlatform_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ConnectPlatform_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ConnectPlatform_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ConnectPlatform_ReqOrBuilder
    {
        private int bitField0_;
        private Object platform_;
        private Object url_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$56300();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$56400().ensureFieldAccessorsInitialized((Class)ConnectPlatform_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.platform_ = "";
            this.url_ = "";
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.platform_ = "";
            this.url_ = "";
            this.a();
        }
        
        private void a() {
            if (ConnectPlatform_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.platform_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.url_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$56300();
        }
        
        public ConnectPlatform_Req getDefaultInstanceForType() {
            return ConnectPlatform_Req.getDefaultInstance();
        }
        
        public ConnectPlatform_Req build() {
            final ConnectPlatform_Req buildPartial = this.buildPartial();
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
        
        public ConnectPlatform_Req buildPartial() {
            final ConnectPlatform_Req connectPlatform_Req = new ConnectPlatform_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            connectPlatform_Req.platform_ = this.platform_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            connectPlatform_Req.url_ = this.url_;
            connectPlatform_Req.bitField0_ = n;
            this.onBuilt();
            return connectPlatform_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof ConnectPlatform_Req) {
                    return this.mergeFrom((ConnectPlatform_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ConnectPlatform_Req connectPlatform_Req) {
            try {
                if (connectPlatform_Req == ConnectPlatform_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (connectPlatform_Req.hasPlatform()) {
                    this.bitField0_ |= 0x1;
                    this.platform_ = connectPlatform_Req.platform_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (connectPlatform_Req.hasUrl()) {
                    this.bitField0_ |= 0x2;
                    this.url_ = connectPlatform_Req.url_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(connectPlatform_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasPlatform()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasUrl()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ConnectPlatform_Req connectPlatform_Req = null;
            try {
                connectPlatform_Req = (ConnectPlatform_Req)ConnectPlatform_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                connectPlatform_Req = (ConnectPlatform_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (connectPlatform_Req != null) {
                        this.mergeFrom(connectPlatform_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasPlatform() {
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
        
        public String getPlatform() {
            final Object platform_ = this.platform_;
            if (!(platform_ instanceof String)) {
                return (String)(this.platform_ = ((ByteString)platform_).toStringUtf8());
            }
            return (String)platform_;
        }
        
        public ByteString getPlatformBytes() {
            final Object platform_ = this.platform_;
            if (platform_ instanceof String) {
                return (ByteString)(this.platform_ = ByteString.copyFromUtf8((String)platform_));
            }
            return (ByteString)platform_;
        }
        
        public Builder setPlatform(final String platform_) {
            try {
                if (platform_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.platform_ = platform_;
            this.onChanged();
            return this;
        }
        
        public Builder clearPlatform() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.platform_ = ConnectPlatform_Req.getDefaultInstance().getPlatform();
            this.onChanged();
            return this;
        }
        
        public Builder setPlatformBytes(final ByteString platform_) {
            try {
                if (platform_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.platform_ = platform_;
            this.onChanged();
            return this;
        }
        
        public boolean hasUrl() {
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
        
        public String getUrl() {
            final Object url_ = this.url_;
            if (!(url_ instanceof String)) {
                return (String)(this.url_ = ((ByteString)url_).toStringUtf8());
            }
            return (String)url_;
        }
        
        public ByteString getUrlBytes() {
            final Object url_ = this.url_;
            if (url_ instanceof String) {
                return (ByteString)(this.url_ = ByteString.copyFromUtf8((String)url_));
            }
            return (ByteString)url_;
        }
        
        public Builder setUrl(final String url_) {
            try {
                if (url_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.url_ = url_;
            this.onChanged();
            return this;
        }
        
        public Builder clearUrl() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.url_ = ConnectPlatform_Req.getDefaultInstance().getUrl();
            this.onChanged();
            return this;
        }
        
        public Builder setUrlBytes(final ByteString url_) {
            try {
                if (url_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.url_ = url_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
