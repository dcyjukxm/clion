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
import com.google.protobuf.MessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class LLDBValue extends GeneratedMessage implements LLDBValueOrBuilder
{
    private static final LLDBValue defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<LLDBValue> PARSER;
    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    public static final int NAME_FIELD_NUMBER = 2;
    private Object name_;
    public static final int TYPE_FIELD_NUMBER = 3;
    private Object type_;
    public static final int TYPE_CLASS_FIELD_NUMBER = 4;
    private TypeClass typeClass_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private LLDBValue(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private LLDBValue(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static LLDBValue getDefaultInstance() {
        return LLDBValue.defaultInstance;
    }
    
    public LLDBValue getDefaultInstanceForType() {
        return LLDBValue.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private LLDBValue(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.id_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 18: {
                        this.bitField0_ |= 0x2;
                        this.name_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 26: {
                        this.bitField0_ |= 0x4;
                        this.type_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 32: {
                        final int enum1 = codedInputStream.readEnum();
                        final TypeClass value = TypeClass.valueOf(enum1);
                        try {
                            if (value == null) {
                                builder.mergeVarintField(4, enum1);
                                continue;
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x8;
                        this.typeClass_ = value;
                        continue;
                    }
                }
            }
        }
        catch (InvalidProtocolBufferException ex2) {
            throw ex2.setUnfinishedMessage((MessageLite)this);
        }
        catch (IOException ex3) {
            throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
        }
        finally {
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$7300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$7400().ensureFieldAccessorsInitialized((Class)LLDBValue.class, (Class)Builder.class);
    }
    
    public Parser<LLDBValue> getParserForType() {
        return LLDBValue.PARSER;
    }
    
    public boolean hasId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getId() {
        return this.id_;
    }
    
    public boolean hasName() {
        return (this.bitField0_ & 0x2) == 0x2;
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
    
    public boolean hasType() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public String getType() {
        final Object type_ = this.type_;
        if (type_ instanceof String) {
            return (String)type_;
        }
        final ByteString byteString = (ByteString)type_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.type_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getTypeBytes() {
        final Object type_ = this.type_;
        if (type_ instanceof String) {
            return (ByteString)(this.type_ = ByteString.copyFromUtf8((String)type_));
        }
        return (ByteString)type_;
    }
    
    public boolean hasTypeClass() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public TypeClass getTypeClass() {
        return this.typeClass_;
    }
    
    private void a() {
        this.id_ = 0;
        this.name_ = "";
        this.type_ = "";
        this.typeClass_ = TypeClass.TypeClassArray;
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
        if (!this.hasName()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasType()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasTypeClass()) {
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
                codedOutputStream.writeInt32(1, this.id_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(2, this.getNameBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeBytes(3, this.getTypeBytes());
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeEnum(4, this.typeClass_.getNumber());
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
            n += CodedOutputStream.computeInt32Size(1, this.id_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(2, this.getNameBytes());
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBytesSize(3, this.getTypeBytes());
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeEnumSize(4, this.typeClass_.getNumber());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static LLDBValue parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (LLDBValue)LLDBValue.PARSER.parseFrom(byteString);
    }
    
    public static LLDBValue parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LLDBValue)LLDBValue.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static LLDBValue parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (LLDBValue)LLDBValue.PARSER.parseFrom(array);
    }
    
    public static LLDBValue parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LLDBValue)LLDBValue.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static LLDBValue parseFrom(final InputStream inputStream) throws IOException {
        return (LLDBValue)LLDBValue.PARSER.parseFrom(inputStream);
    }
    
    public static LLDBValue parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBValue)LLDBValue.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static LLDBValue parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (LLDBValue)LLDBValue.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static LLDBValue parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBValue)LLDBValue.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static LLDBValue parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (LLDBValue)LLDBValue.PARSER.parseFrom(codedInputStream);
    }
    
    public static LLDBValue parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBValue)LLDBValue.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final LLDBValue lldbValue) {
        return newBuilder().mergeFrom(lldbValue);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        LLDBValue.PARSER = (Parser<LLDBValue>)new AbstractParser<LLDBValue>() {
            public LLDBValue parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LLDBValue(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new LLDBValue(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBValueOrBuilder
    {
        private int bitField0_;
        private int id_;
        private Object name_;
        private Object type_;
        private TypeClass typeClass_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.access$7300();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.access$7400().ensureFieldAccessorsInitialized((Class)LLDBValue.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.name_ = "";
            this.type_ = "";
            this.typeClass_ = TypeClass.TypeClassArray;
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.type_ = "";
            this.typeClass_ = TypeClass.TypeClassArray;
            this.b();
        }
        
        private void b() {
            if (LLDBValue.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.id_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            this.name_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            this.type_ = "";
            this.bitField0_ &= 0xFFFFFFFB;
            this.typeClass_ = TypeClass.TypeClassArray;
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Model.access$7300();
        }
        
        public LLDBValue getDefaultInstanceForType() {
            return LLDBValue.getDefaultInstance();
        }
        
        public LLDBValue build() {
            final LLDBValue buildPartial = this.buildPartial();
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
        
        public LLDBValue buildPartial() {
            final LLDBValue lldbValue = new LLDBValue((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            lldbValue.id_ = this.id_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            lldbValue.name_ = this.name_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            lldbValue.type_ = this.type_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            lldbValue.typeClass_ = this.typeClass_;
            lldbValue.bitField0_ = n;
            this.onBuilt();
            return lldbValue;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof LLDBValue) {
                    return this.mergeFrom((LLDBValue)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final LLDBValue lldbValue) {
            try {
                if (lldbValue == LLDBValue.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (lldbValue.hasId()) {
                    this.setId(lldbValue.getId());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (lldbValue.hasName()) {
                    this.bitField0_ |= 0x2;
                    this.name_ = lldbValue.name_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (lldbValue.hasType()) {
                    this.bitField0_ |= 0x4;
                    this.type_ = lldbValue.type_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (lldbValue.hasTypeClass()) {
                    this.setTypeClass(lldbValue.getTypeClass());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            this.mergeUnknownFields(lldbValue.getUnknownFields());
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
                if (!this.hasName()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.hasType()) {
                    return false;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (!this.hasTypeClass()) {
                    return false;
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            LLDBValue lldbValue = null;
            try {
                lldbValue = (LLDBValue)LLDBValue.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                lldbValue = (LLDBValue)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (lldbValue != null) {
                        this.mergeFrom(lldbValue);
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
        
        public boolean hasName() {
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
            this.bitField0_ |= 0x2;
            this.name_ = name_;
            this.onChanged();
            return this;
        }
        
        public Builder clearName() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.name_ = LLDBValue.getDefaultInstance().getName();
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
            this.bitField0_ |= 0x2;
            this.name_ = name_;
            this.onChanged();
            return this;
        }
        
        public boolean hasType() {
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
        
        public String getType() {
            final Object type_ = this.type_;
            if (!(type_ instanceof String)) {
                return (String)(this.type_ = ((ByteString)type_).toStringUtf8());
            }
            return (String)type_;
        }
        
        public ByteString getTypeBytes() {
            final Object type_ = this.type_;
            if (type_ instanceof String) {
                return (ByteString)(this.type_ = ByteString.copyFromUtf8((String)type_));
            }
            return (ByteString)type_;
        }
        
        public Builder setType(final String type_) {
            try {
                if (type_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x4;
            this.type_ = type_;
            this.onChanged();
            return this;
        }
        
        public Builder clearType() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.type_ = LLDBValue.getDefaultInstance().getType();
            this.onChanged();
            return this;
        }
        
        public Builder setTypeBytes(final ByteString type_) {
            try {
                if (type_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x4;
            this.type_ = type_;
            this.onChanged();
            return this;
        }
        
        public boolean hasTypeClass() {
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
        
        public TypeClass getTypeClass() {
            return this.typeClass_;
        }
        
        public Builder setTypeClass(final TypeClass typeClass_) {
            try {
                if (typeClass_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x8;
            this.typeClass_ = typeClass_;
            this.onChanged();
            return this;
        }
        
        public Builder clearTypeClass() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.typeClass_ = TypeClass.TypeClassArray;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
