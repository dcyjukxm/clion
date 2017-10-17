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

public static final class LLDBValueData extends GeneratedMessage implements LLDBValueDataOrBuilder
{
    private static final LLDBValueData defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<LLDBValueData> PARSER;
    private int bitField0_;
    public static final int VALUE_FIELD_NUMBER = 1;
    private Object value_;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    private Object description_;
    public static final int HAS_LONGER_DESCRIPTION_FIELD_NUMBER = 3;
    private boolean hasLongerDescription_;
    public static final int MAY_HAVE_CHILDREN_FIELD_NUMBER = 4;
    private boolean mayHaveChildren_;
    public static final int IS_SYNTHETIC_FIELD_NUMBER = 5;
    private boolean isSynthetic_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private LLDBValueData(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private LLDBValueData(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static LLDBValueData getDefaultInstance() {
        return LLDBValueData.defaultInstance;
    }
    
    public LLDBValueData getDefaultInstanceForType() {
        return LLDBValueData.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private LLDBValueData(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.value_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 18: {
                        this.bitField0_ |= 0x2;
                        this.description_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x4;
                        this.hasLongerDescription_ = codedInputStream.readBool();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x8;
                        this.mayHaveChildren_ = codedInputStream.readBool();
                        continue;
                    }
                    case 40: {
                        this.bitField0_ |= 0x10;
                        this.isSynthetic_ = codedInputStream.readBool();
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
        return Model.access$8500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$8600().ensureFieldAccessorsInitialized((Class)LLDBValueData.class, (Class)Builder.class);
    }
    
    public Parser<LLDBValueData> getParserForType() {
        return LLDBValueData.PARSER;
    }
    
    public boolean hasValue() {
        return (this.bitField0_ & 0x1) == 0x1;
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
    
    public boolean hasDescription() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getDescription() {
        final Object description_ = this.description_;
        if (description_ instanceof String) {
            return (String)description_;
        }
        final ByteString byteString = (ByteString)description_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.description_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getDescriptionBytes() {
        final Object description_ = this.description_;
        if (description_ instanceof String) {
            return (ByteString)(this.description_ = ByteString.copyFromUtf8((String)description_));
        }
        return (ByteString)description_;
    }
    
    public boolean hasHasLongerDescription() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public boolean getHasLongerDescription() {
        return this.hasLongerDescription_;
    }
    
    public boolean hasMayHaveChildren() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public boolean getMayHaveChildren() {
        return this.mayHaveChildren_;
    }
    
    public boolean hasIsSynthetic() {
        return (this.bitField0_ & 0x10) == 0x10;
    }
    
    public boolean getIsSynthetic() {
        return this.isSynthetic_;
    }
    
    private void a() {
        this.value_ = "";
        this.description_ = "";
        this.hasLongerDescription_ = false;
        this.mayHaveChildren_ = false;
        this.isSynthetic_ = false;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasValue()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasMayHaveChildren()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasIsSynthetic()) {
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
                codedOutputStream.writeBytes(1, this.getValueBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(2, this.getDescriptionBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeBool(3, this.hasLongerDescription_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeBool(4, this.mayHaveChildren_);
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
        }
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                codedOutputStream.writeBool(5, this.isSynthetic_);
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
            n += CodedOutputStream.computeBytesSize(1, this.getValueBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(2, this.getDescriptionBytes());
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBoolSize(3, this.hasLongerDescription_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeBoolSize(4, this.mayHaveChildren_);
        }
        if ((this.bitField0_ & 0x10) == 0x10) {
            n += CodedOutputStream.computeBoolSize(5, this.isSynthetic_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static LLDBValueData parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (LLDBValueData)LLDBValueData.PARSER.parseFrom(byteString);
    }
    
    public static LLDBValueData parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LLDBValueData)LLDBValueData.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static LLDBValueData parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (LLDBValueData)LLDBValueData.PARSER.parseFrom(array);
    }
    
    public static LLDBValueData parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LLDBValueData)LLDBValueData.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static LLDBValueData parseFrom(final InputStream inputStream) throws IOException {
        return (LLDBValueData)LLDBValueData.PARSER.parseFrom(inputStream);
    }
    
    public static LLDBValueData parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBValueData)LLDBValueData.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static LLDBValueData parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (LLDBValueData)LLDBValueData.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static LLDBValueData parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBValueData)LLDBValueData.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static LLDBValueData parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (LLDBValueData)LLDBValueData.PARSER.parseFrom(codedInputStream);
    }
    
    public static LLDBValueData parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBValueData)LLDBValueData.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final LLDBValueData lldbValueData) {
        return newBuilder().mergeFrom(lldbValueData);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        LLDBValueData.PARSER = (Parser<LLDBValueData>)new AbstractParser<LLDBValueData>() {
            public LLDBValueData parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LLDBValueData(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new LLDBValueData(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBValueDataOrBuilder
    {
        private int bitField0_;
        private Object value_;
        private Object description_;
        private boolean hasLongerDescription_;
        private boolean mayHaveChildren_;
        private boolean isSynthetic_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.access$8500();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.access$8600().ensureFieldAccessorsInitialized((Class)LLDBValueData.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.value_ = "";
            this.description_ = "";
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.value_ = "";
            this.description_ = "";
            this.b();
        }
        
        private void b() {
            if (LLDBValueData.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.value_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.description_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            this.hasLongerDescription_ = false;
            this.bitField0_ &= 0xFFFFFFFB;
            this.mayHaveChildren_ = false;
            this.bitField0_ &= 0xFFFFFFF7;
            this.isSynthetic_ = false;
            this.bitField0_ &= 0xFFFFFFEF;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Model.access$8500();
        }
        
        public LLDBValueData getDefaultInstanceForType() {
            return LLDBValueData.getDefaultInstance();
        }
        
        public LLDBValueData build() {
            final LLDBValueData buildPartial = this.buildPartial();
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
        
        public LLDBValueData buildPartial() {
            final LLDBValueData lldbValueData = new LLDBValueData((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            lldbValueData.value_ = this.value_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            lldbValueData.description_ = this.description_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            lldbValueData.hasLongerDescription_ = this.hasLongerDescription_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            lldbValueData.mayHaveChildren_ = this.mayHaveChildren_;
            if ((bitField0_ & 0x10) == 0x10) {
                n |= 0x10;
            }
            lldbValueData.isSynthetic_ = this.isSynthetic_;
            lldbValueData.bitField0_ = n;
            this.onBuilt();
            return lldbValueData;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof LLDBValueData) {
                    return this.mergeFrom((LLDBValueData)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final LLDBValueData lldbValueData) {
            try {
                if (lldbValueData == LLDBValueData.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (lldbValueData.hasValue()) {
                    this.bitField0_ |= 0x1;
                    this.value_ = lldbValueData.value_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (lldbValueData.hasDescription()) {
                    this.bitField0_ |= 0x2;
                    this.description_ = lldbValueData.description_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (lldbValueData.hasHasLongerDescription()) {
                    this.setHasLongerDescription(lldbValueData.getHasLongerDescription());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (lldbValueData.hasMayHaveChildren()) {
                    this.setMayHaveChildren(lldbValueData.getMayHaveChildren());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            try {
                if (lldbValueData.hasIsSynthetic()) {
                    this.setIsSynthetic(lldbValueData.getIsSynthetic());
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            this.mergeUnknownFields(lldbValueData.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasValue()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasMayHaveChildren()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.hasIsSynthetic()) {
                    return false;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            LLDBValueData lldbValueData = null;
            try {
                lldbValueData = (LLDBValueData)LLDBValueData.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                lldbValueData = (LLDBValueData)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (lldbValueData != null) {
                        this.mergeFrom(lldbValueData);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasValue() {
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
            this.bitField0_ |= 0x1;
            this.value_ = value_;
            this.onChanged();
            return this;
        }
        
        public Builder clearValue() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.value_ = LLDBValueData.getDefaultInstance().getValue();
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
            this.bitField0_ |= 0x1;
            this.value_ = value_;
            this.onChanged();
            return this;
        }
        
        public boolean hasDescription() {
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
        
        public String getDescription() {
            final Object description_ = this.description_;
            if (!(description_ instanceof String)) {
                return (String)(this.description_ = ((ByteString)description_).toStringUtf8());
            }
            return (String)description_;
        }
        
        public ByteString getDescriptionBytes() {
            final Object description_ = this.description_;
            if (description_ instanceof String) {
                return (ByteString)(this.description_ = ByteString.copyFromUtf8((String)description_));
            }
            return (ByteString)description_;
        }
        
        public Builder setDescription(final String description_) {
            try {
                if (description_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.description_ = description_;
            this.onChanged();
            return this;
        }
        
        public Builder clearDescription() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.description_ = LLDBValueData.getDefaultInstance().getDescription();
            this.onChanged();
            return this;
        }
        
        public Builder setDescriptionBytes(final ByteString description_) {
            try {
                if (description_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.description_ = description_;
            this.onChanged();
            return this;
        }
        
        public boolean hasHasLongerDescription() {
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
        
        public boolean getHasLongerDescription() {
            return this.hasLongerDescription_;
        }
        
        public Builder setHasLongerDescription(final boolean hasLongerDescription_) {
            this.bitField0_ |= 0x4;
            this.hasLongerDescription_ = hasLongerDescription_;
            this.onChanged();
            return this;
        }
        
        public Builder clearHasLongerDescription() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.hasLongerDescription_ = false;
            this.onChanged();
            return this;
        }
        
        public boolean hasMayHaveChildren() {
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
        
        public boolean getMayHaveChildren() {
            return this.mayHaveChildren_;
        }
        
        public Builder setMayHaveChildren(final boolean mayHaveChildren_) {
            this.bitField0_ |= 0x8;
            this.mayHaveChildren_ = mayHaveChildren_;
            this.onChanged();
            return this;
        }
        
        public Builder clearMayHaveChildren() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.mayHaveChildren_ = false;
            this.onChanged();
            return this;
        }
        
        public boolean hasIsSynthetic() {
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
        
        public boolean getIsSynthetic() {
            return this.isSynthetic_;
        }
        
        public Builder setIsSynthetic(final boolean isSynthetic_) {
            this.bitField0_ |= 0x10;
            this.isSynthetic_ = isSynthetic_;
            this.onChanged();
            return this;
        }
        
        public Builder clearIsSynthetic() {
            this.bitField0_ &= 0xFFFFFFEF;
            this.isSynthetic_ = false;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
