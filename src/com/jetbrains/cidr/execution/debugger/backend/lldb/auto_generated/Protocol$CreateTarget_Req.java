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

public static final class CreateTarget_Req extends GeneratedMessage implements CreateTarget_ReqOrBuilder
{
    private static final CreateTarget_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<CreateTarget_Req> PARSER;
    private int bitField0_;
    public static final int EXE_PATH_FIELD_NUMBER = 2;
    private Object exePath_;
    public static final int ARCH_FIELD_NUMBER = 3;
    private Object arch_;
    public static final int REMOTE_EXE_PATH_FIELD_NUMBER = 4;
    private Object remoteExePath_;
    public static final int PLATFORM_FIELD_NUMBER = 5;
    private Object platform_;
    public static final int PLATFORM_SDK_ROOT_FIELD_NUMBER = 6;
    private Object platformSdkRoot_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private CreateTarget_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private CreateTarget_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static CreateTarget_Req getDefaultInstance() {
        return CreateTarget_Req.defaultInstance;
    }
    
    public CreateTarget_Req getDefaultInstanceForType() {
        return CreateTarget_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private CreateTarget_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.exePath_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 26: {
                        this.bitField0_ |= 0x2;
                        this.arch_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 34: {
                        this.bitField0_ |= 0x4;
                        this.remoteExePath_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 42: {
                        this.bitField0_ |= 0x8;
                        this.platform_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 50: {
                        this.bitField0_ |= 0x10;
                        this.platformSdkRoot_ = codedInputStream.readBytes();
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
        return Protocol.access$1000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$1100().ensureFieldAccessorsInitialized((Class)CreateTarget_Req.class, (Class)Builder.class);
    }
    
    public Parser<CreateTarget_Req> getParserForType() {
        return CreateTarget_Req.PARSER;
    }
    
    public boolean hasExePath() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getExePath() {
        final Object exePath_ = this.exePath_;
        if (exePath_ instanceof String) {
            return (String)exePath_;
        }
        final ByteString byteString = (ByteString)exePath_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.exePath_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getExePathBytes() {
        final Object exePath_ = this.exePath_;
        if (exePath_ instanceof String) {
            return (ByteString)(this.exePath_ = ByteString.copyFromUtf8((String)exePath_));
        }
        return (ByteString)exePath_;
    }
    
    public boolean hasArch() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getArch() {
        final Object arch_ = this.arch_;
        if (arch_ instanceof String) {
            return (String)arch_;
        }
        final ByteString byteString = (ByteString)arch_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.arch_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getArchBytes() {
        final Object arch_ = this.arch_;
        if (arch_ instanceof String) {
            return (ByteString)(this.arch_ = ByteString.copyFromUtf8((String)arch_));
        }
        return (ByteString)arch_;
    }
    
    public boolean hasRemoteExePath() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public String getRemoteExePath() {
        final Object remoteExePath_ = this.remoteExePath_;
        if (remoteExePath_ instanceof String) {
            return (String)remoteExePath_;
        }
        final ByteString byteString = (ByteString)remoteExePath_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.remoteExePath_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getRemoteExePathBytes() {
        final Object remoteExePath_ = this.remoteExePath_;
        if (remoteExePath_ instanceof String) {
            return (ByteString)(this.remoteExePath_ = ByteString.copyFromUtf8((String)remoteExePath_));
        }
        return (ByteString)remoteExePath_;
    }
    
    public boolean hasPlatform() {
        return (this.bitField0_ & 0x8) == 0x8;
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
    
    public boolean hasPlatformSdkRoot() {
        return (this.bitField0_ & 0x10) == 0x10;
    }
    
    public String getPlatformSdkRoot() {
        final Object platformSdkRoot_ = this.platformSdkRoot_;
        if (platformSdkRoot_ instanceof String) {
            return (String)platformSdkRoot_;
        }
        final ByteString byteString = (ByteString)platformSdkRoot_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.platformSdkRoot_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getPlatformSdkRootBytes() {
        final Object platformSdkRoot_ = this.platformSdkRoot_;
        if (platformSdkRoot_ instanceof String) {
            return (ByteString)(this.platformSdkRoot_ = ByteString.copyFromUtf8((String)platformSdkRoot_));
        }
        return (ByteString)platformSdkRoot_;
    }
    
    private void a() {
        this.exePath_ = "";
        this.arch_ = "";
        this.remoteExePath_ = "";
        this.platform_ = "";
        this.platformSdkRoot_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }
    
    public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
        try {
            this.getSerializedSize();
            if ((this.bitField0_ & 0x1) == 0x1) {
                codedOutputStream.writeBytes(2, this.getExePathBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(3, this.getArchBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeBytes(4, this.getRemoteExePathBytes());
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeBytes(5, this.getPlatformBytes());
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
        }
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                codedOutputStream.writeBytes(6, this.getPlatformSdkRootBytes());
            }
        }
        catch (IOException ex5) {
            throw a(ex5);
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
            n += CodedOutputStream.computeBytesSize(2, this.getExePathBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(3, this.getArchBytes());
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBytesSize(4, this.getRemoteExePathBytes());
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeBytesSize(5, this.getPlatformBytes());
        }
        if ((this.bitField0_ & 0x10) == 0x10) {
            n += CodedOutputStream.computeBytesSize(6, this.getPlatformSdkRootBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static CreateTarget_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseFrom(byteString);
    }
    
    public static CreateTarget_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static CreateTarget_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseFrom(array);
    }
    
    public static CreateTarget_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static CreateTarget_Req parseFrom(final InputStream inputStream) throws IOException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseFrom(inputStream);
    }
    
    public static CreateTarget_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static CreateTarget_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static CreateTarget_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static CreateTarget_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static CreateTarget_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateTarget_Req)CreateTarget_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final CreateTarget_Req createTarget_Req) {
        return newBuilder().mergeFrom(createTarget_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        CreateTarget_Req.PARSER = (Parser<CreateTarget_Req>)new AbstractParser<CreateTarget_Req>() {
            public CreateTarget_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CreateTarget_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new CreateTarget_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements CreateTarget_ReqOrBuilder
    {
        private int bitField0_;
        private Object exePath_;
        private Object arch_;
        private Object remoteExePath_;
        private Object platform_;
        private Object platformSdkRoot_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$1000();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$1100().ensureFieldAccessorsInitialized((Class)CreateTarget_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.exePath_ = "";
            this.arch_ = "";
            this.remoteExePath_ = "";
            this.platform_ = "";
            this.platformSdkRoot_ = "";
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.exePath_ = "";
            this.arch_ = "";
            this.remoteExePath_ = "";
            this.platform_ = "";
            this.platformSdkRoot_ = "";
            this.a();
        }
        
        private void a() {
            if (CreateTarget_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.exePath_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.arch_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            this.remoteExePath_ = "";
            this.bitField0_ &= 0xFFFFFFFB;
            this.platform_ = "";
            this.bitField0_ &= 0xFFFFFFF7;
            this.platformSdkRoot_ = "";
            this.bitField0_ &= 0xFFFFFFEF;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$1000();
        }
        
        public CreateTarget_Req getDefaultInstanceForType() {
            return CreateTarget_Req.getDefaultInstance();
        }
        
        public CreateTarget_Req build() {
            final CreateTarget_Req buildPartial = this.buildPartial();
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
        
        public CreateTarget_Req buildPartial() {
            final CreateTarget_Req createTarget_Req = new CreateTarget_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            createTarget_Req.exePath_ = this.exePath_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            createTarget_Req.arch_ = this.arch_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            createTarget_Req.remoteExePath_ = this.remoteExePath_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            createTarget_Req.platform_ = this.platform_;
            if ((bitField0_ & 0x10) == 0x10) {
                n |= 0x10;
            }
            createTarget_Req.platformSdkRoot_ = this.platformSdkRoot_;
            createTarget_Req.bitField0_ = n;
            this.onBuilt();
            return createTarget_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof CreateTarget_Req) {
                    return this.mergeFrom((CreateTarget_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final CreateTarget_Req createTarget_Req) {
            try {
                if (createTarget_Req == CreateTarget_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (createTarget_Req.hasExePath()) {
                    this.bitField0_ |= 0x1;
                    this.exePath_ = createTarget_Req.exePath_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (createTarget_Req.hasArch()) {
                    this.bitField0_ |= 0x2;
                    this.arch_ = createTarget_Req.arch_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (createTarget_Req.hasRemoteExePath()) {
                    this.bitField0_ |= 0x4;
                    this.remoteExePath_ = createTarget_Req.remoteExePath_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (createTarget_Req.hasPlatform()) {
                    this.bitField0_ |= 0x8;
                    this.platform_ = createTarget_Req.platform_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            try {
                if (createTarget_Req.hasPlatformSdkRoot()) {
                    this.bitField0_ |= 0x10;
                    this.platformSdkRoot_ = createTarget_Req.platformSdkRoot_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            this.mergeUnknownFields(createTarget_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CreateTarget_Req createTarget_Req = null;
            try {
                createTarget_Req = (CreateTarget_Req)CreateTarget_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                createTarget_Req = (CreateTarget_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (createTarget_Req != null) {
                        this.mergeFrom(createTarget_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasExePath() {
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
        
        public String getExePath() {
            final Object exePath_ = this.exePath_;
            if (!(exePath_ instanceof String)) {
                return (String)(this.exePath_ = ((ByteString)exePath_).toStringUtf8());
            }
            return (String)exePath_;
        }
        
        public ByteString getExePathBytes() {
            final Object exePath_ = this.exePath_;
            if (exePath_ instanceof String) {
                return (ByteString)(this.exePath_ = ByteString.copyFromUtf8((String)exePath_));
            }
            return (ByteString)exePath_;
        }
        
        public Builder setExePath(final String exePath_) {
            try {
                if (exePath_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.exePath_ = exePath_;
            this.onChanged();
            return this;
        }
        
        public Builder clearExePath() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.exePath_ = CreateTarget_Req.getDefaultInstance().getExePath();
            this.onChanged();
            return this;
        }
        
        public Builder setExePathBytes(final ByteString exePath_) {
            try {
                if (exePath_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.exePath_ = exePath_;
            this.onChanged();
            return this;
        }
        
        public boolean hasArch() {
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
        
        public String getArch() {
            final Object arch_ = this.arch_;
            if (!(arch_ instanceof String)) {
                return (String)(this.arch_ = ((ByteString)arch_).toStringUtf8());
            }
            return (String)arch_;
        }
        
        public ByteString getArchBytes() {
            final Object arch_ = this.arch_;
            if (arch_ instanceof String) {
                return (ByteString)(this.arch_ = ByteString.copyFromUtf8((String)arch_));
            }
            return (ByteString)arch_;
        }
        
        public Builder setArch(final String arch_) {
            try {
                if (arch_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.arch_ = arch_;
            this.onChanged();
            return this;
        }
        
        public Builder clearArch() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.arch_ = CreateTarget_Req.getDefaultInstance().getArch();
            this.onChanged();
            return this;
        }
        
        public Builder setArchBytes(final ByteString arch_) {
            try {
                if (arch_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.arch_ = arch_;
            this.onChanged();
            return this;
        }
        
        public boolean hasRemoteExePath() {
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
        
        public String getRemoteExePath() {
            final Object remoteExePath_ = this.remoteExePath_;
            if (!(remoteExePath_ instanceof String)) {
                return (String)(this.remoteExePath_ = ((ByteString)remoteExePath_).toStringUtf8());
            }
            return (String)remoteExePath_;
        }
        
        public ByteString getRemoteExePathBytes() {
            final Object remoteExePath_ = this.remoteExePath_;
            if (remoteExePath_ instanceof String) {
                return (ByteString)(this.remoteExePath_ = ByteString.copyFromUtf8((String)remoteExePath_));
            }
            return (ByteString)remoteExePath_;
        }
        
        public Builder setRemoteExePath(final String remoteExePath_) {
            try {
                if (remoteExePath_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x4;
            this.remoteExePath_ = remoteExePath_;
            this.onChanged();
            return this;
        }
        
        public Builder clearRemoteExePath() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.remoteExePath_ = CreateTarget_Req.getDefaultInstance().getRemoteExePath();
            this.onChanged();
            return this;
        }
        
        public Builder setRemoteExePathBytes(final ByteString remoteExePath_) {
            try {
                if (remoteExePath_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x4;
            this.remoteExePath_ = remoteExePath_;
            this.onChanged();
            return this;
        }
        
        public boolean hasPlatform() {
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
            this.bitField0_ |= 0x8;
            this.platform_ = platform_;
            this.onChanged();
            return this;
        }
        
        public Builder clearPlatform() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.platform_ = CreateTarget_Req.getDefaultInstance().getPlatform();
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
            this.bitField0_ |= 0x8;
            this.platform_ = platform_;
            this.onChanged();
            return this;
        }
        
        public boolean hasPlatformSdkRoot() {
            try {
                if ((this.bitField0_ & 0x10) == 0x10) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public String getPlatformSdkRoot() {
            final Object platformSdkRoot_ = this.platformSdkRoot_;
            if (!(platformSdkRoot_ instanceof String)) {
                return (String)(this.platformSdkRoot_ = ((ByteString)platformSdkRoot_).toStringUtf8());
            }
            return (String)platformSdkRoot_;
        }
        
        public ByteString getPlatformSdkRootBytes() {
            final Object platformSdkRoot_ = this.platformSdkRoot_;
            if (platformSdkRoot_ instanceof String) {
                return (ByteString)(this.platformSdkRoot_ = ByteString.copyFromUtf8((String)platformSdkRoot_));
            }
            return (ByteString)platformSdkRoot_;
        }
        
        public Builder setPlatformSdkRoot(final String platformSdkRoot_) {
            try {
                if (platformSdkRoot_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x10;
            this.platformSdkRoot_ = platformSdkRoot_;
            this.onChanged();
            return this;
        }
        
        public Builder clearPlatformSdkRoot() {
            this.bitField0_ &= 0xFFFFFFEF;
            this.platformSdkRoot_ = CreateTarget_Req.getDefaultInstance().getPlatformSdkRoot();
            this.onChanged();
            return this;
        }
        
        public Builder setPlatformSdkRootBytes(final ByteString platformSdkRoot_) {
            try {
                if (platformSdkRoot_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x10;
            this.platformSdkRoot_ = platformSdkRoot_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
