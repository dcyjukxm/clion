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

public static final class EnvParam extends GeneratedMessage implements EnvParamOrBuilder
{
    private static final EnvParam defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<EnvParam> PARSER;
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private Object name_;
    public static final int VALUE_FIELD_NUMBER = 2;
    private Object value_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private EnvParam(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private EnvParam(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static EnvParam getDefaultInstance() {
        return EnvParam.defaultInstance;
    }
    
    public EnvParam getDefaultInstanceForType() {
        return EnvParam.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private EnvParam(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 10: {
                        this.bitField0_ |= 0x1;
                        this.name_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 18: {
                        this.bitField0_ |= 0x2;
                        this.value_ = codedInputStream.readBytes();
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
        return Model.access$700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$800().ensureFieldAccessorsInitialized((Class)EnvParam.class, (Class)Builder.class);
    }
    
    public Parser<EnvParam> getParserForType() {
        return EnvParam.PARSER;
    }
    
    public boolean hasName() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getName() {
        final Object name_ = this.name_;
        if (name_ instanceof String) {
            return (String)name_;
        }
        final ByteString byteString = (ByteString)name_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.name_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getNameBytes() {
        final Object name_ = this.name_;
        if (name_ instanceof String) {
            return (ByteString)(this.name_ = ByteString.copyFromUtf8((String)name_));
        }
        return (ByteString)name_;
    }
    
    public boolean hasValue() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getValue() {
        final Object value_ = this.value_;
        if (value_ instanceof String) {
            return (String)value_;
        }
        final ByteString byteString = (ByteString)value_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.value_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getValueBytes() {
        final Object value_ = this.value_;
        if (value_ instanceof String) {
            return (ByteString)(this.value_ = ByteString.copyFromUtf8((String)value_));
        }
        return (ByteString)value_;
    }
    
    private void a() {
        this.name_ = "";
        this.value_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasName()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasValue()) {
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
                codedOutputStream.writeBytes(1, this.getNameBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(2, this.getValueBytes());
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
            n += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(2, this.getValueBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static EnvParam parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (EnvParam)EnvParam.PARSER.parseFrom(byteString);
    }
    
    public static EnvParam parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EnvParam)EnvParam.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static EnvParam parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (EnvParam)EnvParam.PARSER.parseFrom(array);
    }
    
    public static EnvParam parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EnvParam)EnvParam.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static EnvParam parseFrom(final InputStream inputStream) throws IOException {
        return (EnvParam)EnvParam.PARSER.parseFrom(inputStream);
    }
    
    public static EnvParam parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EnvParam)EnvParam.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static EnvParam parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (EnvParam)EnvParam.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static EnvParam parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EnvParam)EnvParam.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static EnvParam parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (EnvParam)EnvParam.PARSER.parseFrom(codedInputStream);
    }
    
    public static EnvParam parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EnvParam)EnvParam.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final EnvParam envParam) {
        return newBuilder().mergeFrom(envParam);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        EnvParam.PARSER = (Parser<EnvParam>)new AbstractParser<EnvParam>() {
            public EnvParam parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new EnvParam(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new EnvParam(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements EnvParamOrBuilder
    {
        private int bitField0_;
        private Object name_;
        private Object value_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.access$700();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.access$800().ensureFieldAccessorsInitialized((Class)EnvParam.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.name_ = "";
            this.value_ = "";
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.value_ = "";
            this.b();
        }
        
        private void b() {
            if (EnvParam.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.value_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Model.access$700();
        }
        
        public EnvParam getDefaultInstanceForType() {
            return EnvParam.getDefaultInstance();
        }
        
        public EnvParam build() {
            final EnvParam buildPartial = this.buildPartial();
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
        
        public EnvParam buildPartial() {
            final EnvParam envParam = new EnvParam((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            envParam.name_ = this.name_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            envParam.value_ = this.value_;
            envParam.bitField0_ = n;
            this.onBuilt();
            return envParam;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof EnvParam) {
                    return this.mergeFrom((EnvParam)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final EnvParam envParam) {
            try {
                if (envParam == EnvParam.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (envParam.hasName()) {
                    this.bitField0_ |= 0x1;
                    this.name_ = envParam.name_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (envParam.hasValue()) {
                    this.bitField0_ |= 0x2;
                    this.value_ = envParam.value_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(envParam.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasName()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasValue()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            EnvParam envParam = null;
            try {
                envParam = (EnvParam)EnvParam.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                envParam = (EnvParam)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (envParam != null) {
                        this.mergeFrom(envParam);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasName() {
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
        
        public String getName() {
            final Object name_ = this.name_;
            if (!(name_ instanceof String)) {
                return (String)(this.name_ = ((ByteString)name_).toStringUtf8());
            }
            return (String)name_;
        }
        
        public ByteString getNameBytes() {
            final Object name_ = this.name_;
            if (name_ instanceof String) {
                return (ByteString)(this.name_ = ByteString.copyFromUtf8((String)name_));
            }
            return (ByteString)name_;
        }
        
        public Builder setName(final String name_) {
            try {
                if (name_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.name_ = name_;
            this.onChanged();
            return this;
        }
        
        public Builder clearName() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.name_ = EnvParam.getDefaultInstance().getName();
            this.onChanged();
            return this;
        }
        
        public Builder setNameBytes(final ByteString name_) {
            try {
                if (name_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.name_ = name_;
            this.onChanged();
            return this;
        }
        
        public boolean hasValue() {
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
        
        public String getValue() {
            final Object value_ = this.value_;
            if (!(value_ instanceof String)) {
                return (String)(this.value_ = ((ByteString)value_).toStringUtf8());
            }
            return (String)value_;
        }
        
        public ByteString getValueBytes() {
            final Object value_ = this.value_;
            if (value_ instanceof String) {
                return (ByteString)(this.value_ = ByteString.copyFromUtf8((String)value_));
            }
            return (ByteString)value_;
        }
        
        public Builder setValue(final String value_) {
            try {
                if (value_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.value_ = value_;
            this.onChanged();
            return this;
        }
        
        public Builder clearValue() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.value_ = EnvParam.getDefaultInstance().getValue();
            this.onChanged();
            return this;
        }
        
        public Builder setValueBytes(final ByteString value_) {
            try {
                if (value_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.value_ = value_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
