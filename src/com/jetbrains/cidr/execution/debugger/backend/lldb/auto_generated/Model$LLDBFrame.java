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

public static final class LLDBFrame extends GeneratedMessage implements LLDBFrameOrBuilder
{
    private static final LLDBFrame defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<LLDBFrame> PARSER;
    private int bitField0_;
    public static final int NUMBER_FIELD_NUMBER = 1;
    private int number_;
    public static final int FUNCTION_FIELD_NUMBER = 2;
    private Object function_;
    public static final int FILE_FIELD_NUMBER = 3;
    private Object file_;
    public static final int LINE_FIELD_NUMBER = 4;
    private int line_;
    public static final int PC_FIELD_NUMBER = 5;
    private long pc_;
    public static final int LANGUAGE_FIELD_NUMBER = 6;
    private Language language_;
    public static final int OPTIMIZED_FIELD_NUMBER = 7;
    private boolean optimized_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private LLDBFrame(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private LLDBFrame(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static LLDBFrame getDefaultInstance() {
        return LLDBFrame.defaultInstance;
    }
    
    public LLDBFrame getDefaultInstanceForType() {
        return LLDBFrame.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private LLDBFrame(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.number_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 18: {
                        this.bitField0_ |= 0x2;
                        this.function_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 26: {
                        this.bitField0_ |= 0x4;
                        this.file_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x8;
                        this.line_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 40: {
                        this.bitField0_ |= 0x10;
                        this.pc_ = codedInputStream.readInt64();
                        continue;
                    }
                    case 48: {
                        final int enum1 = codedInputStream.readEnum();
                        final Language value = Language.valueOf(enum1);
                        try {
                            if (value == null) {
                                builder.mergeVarintField(6, enum1);
                                continue;
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x20;
                        this.language_ = value;
                        continue;
                    }
                    case 56: {
                        this.bitField0_ |= 0x40;
                        this.optimized_ = codedInputStream.readBool();
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
        return Model.access$5800();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$5900().ensureFieldAccessorsInitialized((Class)LLDBFrame.class, (Class)Builder.class);
    }
    
    public Parser<LLDBFrame> getParserForType() {
        return LLDBFrame.PARSER;
    }
    
    public boolean hasNumber() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getNumber() {
        return this.number_;
    }
    
    public boolean hasFunction() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getFunction() {
        final Object function_ = this.function_;
        if (function_ instanceof String) {
            return (String)function_;
        }
        final ByteString byteString = (ByteString)function_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.function_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getFunctionBytes() {
        final Object function_ = this.function_;
        if (function_ instanceof String) {
            return (ByteString)(this.function_ = ByteString.copyFromUtf8((String)function_));
        }
        return (ByteString)function_;
    }
    
    public boolean hasFile() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public String getFile() {
        final Object file_ = this.file_;
        if (file_ instanceof String) {
            return (String)file_;
        }
        final ByteString byteString = (ByteString)file_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.file_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getFileBytes() {
        final Object file_ = this.file_;
        if (file_ instanceof String) {
            return (ByteString)(this.file_ = ByteString.copyFromUtf8((String)file_));
        }
        return (ByteString)file_;
    }
    
    public boolean hasLine() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public int getLine() {
        return this.line_;
    }
    
    public boolean hasPc() {
        return (this.bitField0_ & 0x10) == 0x10;
    }
    
    public long getPc() {
        return this.pc_;
    }
    
    public boolean hasLanguage() {
        return (this.bitField0_ & 0x20) == 0x20;
    }
    
    public Language getLanguage() {
        return this.language_;
    }
    
    public boolean hasOptimized() {
        return (this.bitField0_ & 0x40) == 0x40;
    }
    
    public boolean getOptimized() {
        return this.optimized_;
    }
    
    private void a() {
        this.number_ = 0;
        this.function_ = "";
        this.file_ = "";
        this.line_ = 0;
        this.pc_ = 0L;
        this.language_ = Language.LanguageUnknown;
        this.optimized_ = false;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasNumber()) {
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
                codedOutputStream.writeInt32(1, this.number_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(2, this.getFunctionBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeBytes(3, this.getFileBytes());
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeInt32(4, this.line_);
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
        }
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                codedOutputStream.writeInt64(5, this.pc_);
            }
        }
        catch (IOException ex5) {
            throw a(ex5);
        }
        try {
            if ((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream.writeEnum(6, this.language_.getNumber());
            }
        }
        catch (IOException ex6) {
            throw a(ex6);
        }
        try {
            if ((this.bitField0_ & 0x40) == 0x40) {
                codedOutputStream.writeBool(7, this.optimized_);
            }
        }
        catch (IOException ex7) {
            throw a(ex7);
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
            n += CodedOutputStream.computeInt32Size(1, this.number_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(2, this.getFunctionBytes());
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBytesSize(3, this.getFileBytes());
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeInt32Size(4, this.line_);
        }
        if ((this.bitField0_ & 0x10) == 0x10) {
            n += CodedOutputStream.computeInt64Size(5, this.pc_);
        }
        if ((this.bitField0_ & 0x20) == 0x20) {
            n += CodedOutputStream.computeEnumSize(6, this.language_.getNumber());
        }
        if ((this.bitField0_ & 0x40) == 0x40) {
            n += CodedOutputStream.computeBoolSize(7, this.optimized_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static LLDBFrame parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (LLDBFrame)LLDBFrame.PARSER.parseFrom(byteString);
    }
    
    public static LLDBFrame parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LLDBFrame)LLDBFrame.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static LLDBFrame parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (LLDBFrame)LLDBFrame.PARSER.parseFrom(array);
    }
    
    public static LLDBFrame parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LLDBFrame)LLDBFrame.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static LLDBFrame parseFrom(final InputStream inputStream) throws IOException {
        return (LLDBFrame)LLDBFrame.PARSER.parseFrom(inputStream);
    }
    
    public static LLDBFrame parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBFrame)LLDBFrame.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static LLDBFrame parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (LLDBFrame)LLDBFrame.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static LLDBFrame parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBFrame)LLDBFrame.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static LLDBFrame parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (LLDBFrame)LLDBFrame.PARSER.parseFrom(codedInputStream);
    }
    
    public static LLDBFrame parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LLDBFrame)LLDBFrame.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final LLDBFrame lldbFrame) {
        return newBuilder().mergeFrom(lldbFrame);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        LLDBFrame.PARSER = (Parser<LLDBFrame>)new AbstractParser<LLDBFrame>() {
            public LLDBFrame parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LLDBFrame(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new LLDBFrame(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBFrameOrBuilder
    {
        private int bitField0_;
        private int number_;
        private Object function_;
        private Object file_;
        private int line_;
        private long pc_;
        private Language language_;
        private boolean optimized_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.access$5800();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.access$5900().ensureFieldAccessorsInitialized((Class)LLDBFrame.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.function_ = "";
            this.file_ = "";
            this.language_ = Language.LanguageUnknown;
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.function_ = "";
            this.file_ = "";
            this.language_ = Language.LanguageUnknown;
            this.a();
        }
        
        private void a() {
            if (LLDBFrame.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.number_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            this.function_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            this.file_ = "";
            this.bitField0_ &= 0xFFFFFFFB;
            this.line_ = 0;
            this.bitField0_ &= 0xFFFFFFF7;
            this.pc_ = 0L;
            this.bitField0_ &= 0xFFFFFFEF;
            this.language_ = Language.LanguageUnknown;
            this.bitField0_ &= 0xFFFFFFDF;
            this.optimized_ = false;
            this.bitField0_ &= 0xFFFFFFBF;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Model.access$5800();
        }
        
        public LLDBFrame getDefaultInstanceForType() {
            return LLDBFrame.getDefaultInstance();
        }
        
        public LLDBFrame build() {
            final LLDBFrame buildPartial = this.buildPartial();
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
        
        public LLDBFrame buildPartial() {
            final LLDBFrame lldbFrame = new LLDBFrame((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            lldbFrame.number_ = this.number_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            lldbFrame.function_ = this.function_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            lldbFrame.file_ = this.file_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            lldbFrame.line_ = this.line_;
            if ((bitField0_ & 0x10) == 0x10) {
                n |= 0x10;
            }
            lldbFrame.pc_ = this.pc_;
            if ((bitField0_ & 0x20) == 0x20) {
                n |= 0x20;
            }
            lldbFrame.language_ = this.language_;
            if ((bitField0_ & 0x40) == 0x40) {
                n |= 0x40;
            }
            lldbFrame.optimized_ = this.optimized_;
            lldbFrame.bitField0_ = n;
            this.onBuilt();
            return lldbFrame;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof LLDBFrame) {
                    return this.mergeFrom((LLDBFrame)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final LLDBFrame lldbFrame) {
            try {
                if (lldbFrame == LLDBFrame.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (lldbFrame.hasNumber()) {
                    this.setNumber(lldbFrame.getNumber());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (lldbFrame.hasFunction()) {
                    this.bitField0_ |= 0x2;
                    this.function_ = lldbFrame.function_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (lldbFrame.hasFile()) {
                    this.bitField0_ |= 0x4;
                    this.file_ = lldbFrame.file_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (lldbFrame.hasLine()) {
                    this.setLine(lldbFrame.getLine());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            try {
                if (lldbFrame.hasPc()) {
                    this.setPc(lldbFrame.getPc());
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            try {
                if (lldbFrame.hasLanguage()) {
                    this.setLanguage(lldbFrame.getLanguage());
                }
            }
            catch (NullPointerException ex7) {
                throw b(ex7);
            }
            try {
                if (lldbFrame.hasOptimized()) {
                    this.setOptimized(lldbFrame.getOptimized());
                }
            }
            catch (NullPointerException ex8) {
                throw b(ex8);
            }
            this.mergeUnknownFields(lldbFrame.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasNumber()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            LLDBFrame lldbFrame = null;
            try {
                lldbFrame = (LLDBFrame)LLDBFrame.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                lldbFrame = (LLDBFrame)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (lldbFrame != null) {
                        this.mergeFrom(lldbFrame);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasNumber() {
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
        
        public int getNumber() {
            return this.number_;
        }
        
        public Builder setNumber(final int number_) {
            this.bitField0_ |= 0x1;
            this.number_ = number_;
            this.onChanged();
            return this;
        }
        
        public Builder clearNumber() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.number_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasFunction() {
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
        
        public String getFunction() {
            final Object function_ = this.function_;
            if (!(function_ instanceof String)) {
                return (String)(this.function_ = ((ByteString)function_).toStringUtf8());
            }
            return (String)function_;
        }
        
        public ByteString getFunctionBytes() {
            final Object function_ = this.function_;
            if (function_ instanceof String) {
                return (ByteString)(this.function_ = ByteString.copyFromUtf8((String)function_));
            }
            return (ByteString)function_;
        }
        
        public Builder setFunction(final String function_) {
            try {
                if (function_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.function_ = function_;
            this.onChanged();
            return this;
        }
        
        public Builder clearFunction() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.function_ = LLDBFrame.getDefaultInstance().getFunction();
            this.onChanged();
            return this;
        }
        
        public Builder setFunctionBytes(final ByteString function_) {
            try {
                if (function_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.function_ = function_;
            this.onChanged();
            return this;
        }
        
        public boolean hasFile() {
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
        
        public String getFile() {
            final Object file_ = this.file_;
            if (!(file_ instanceof String)) {
                return (String)(this.file_ = ((ByteString)file_).toStringUtf8());
            }
            return (String)file_;
        }
        
        public ByteString getFileBytes() {
            final Object file_ = this.file_;
            if (file_ instanceof String) {
                return (ByteString)(this.file_ = ByteString.copyFromUtf8((String)file_));
            }
            return (ByteString)file_;
        }
        
        public Builder setFile(final String file_) {
            try {
                if (file_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x4;
            this.file_ = file_;
            this.onChanged();
            return this;
        }
        
        public Builder clearFile() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.file_ = LLDBFrame.getDefaultInstance().getFile();
            this.onChanged();
            return this;
        }
        
        public Builder setFileBytes(final ByteString file_) {
            try {
                if (file_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x4;
            this.file_ = file_;
            this.onChanged();
            return this;
        }
        
        public boolean hasLine() {
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
        
        public int getLine() {
            return this.line_;
        }
        
        public Builder setLine(final int line_) {
            this.bitField0_ |= 0x8;
            this.line_ = line_;
            this.onChanged();
            return this;
        }
        
        public Builder clearLine() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.line_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasPc() {
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
        
        public long getPc() {
            return this.pc_;
        }
        
        public Builder setPc(final long pc_) {
            this.bitField0_ |= 0x10;
            this.pc_ = pc_;
            this.onChanged();
            return this;
        }
        
        public Builder clearPc() {
            this.bitField0_ &= 0xFFFFFFEF;
            this.pc_ = 0L;
            this.onChanged();
            return this;
        }
        
        public boolean hasLanguage() {
            try {
                if ((this.bitField0_ & 0x20) == 0x20) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public Language getLanguage() {
            return this.language_;
        }
        
        public Builder setLanguage(final Language language_) {
            try {
                if (language_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x20;
            this.language_ = language_;
            this.onChanged();
            return this;
        }
        
        public Builder clearLanguage() {
            this.bitField0_ &= 0xFFFFFFDF;
            this.language_ = Language.LanguageUnknown;
            this.onChanged();
            return this;
        }
        
        public boolean hasOptimized() {
            try {
                if ((this.bitField0_ & 0x40) == 0x40) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public boolean getOptimized() {
            return this.optimized_;
        }
        
        public Builder setOptimized(final boolean optimized_) {
            this.bitField0_ |= 0x40;
            this.optimized_ = optimized_;
            this.onChanged();
            return this;
        }
        
        public Builder clearOptimized() {
            this.bitField0_ &= 0xFFFFFFBF;
            this.optimized_ = false;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
