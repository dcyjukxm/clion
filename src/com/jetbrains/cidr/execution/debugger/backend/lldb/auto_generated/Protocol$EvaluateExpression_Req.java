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

public static final class EvaluateExpression_Req extends GeneratedMessage implements EvaluateExpression_ReqOrBuilder
{
    private static final EvaluateExpression_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<EvaluateExpression_Req> PARSER;
    private int bitField0_;
    public static final int EXPRESSION_FIELD_NUMBER = 2;
    private Object expression_;
    public static final int THREAD_ID_FIELD_NUMBER = 3;
    private int threadId_;
    public static final int FRAME_INDEX_FIELD_NUMBER = 4;
    private int frameIndex_;
    public static final int LANGUAGE_FIELD_NUMBER = 5;
    private Model.Language language_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private EvaluateExpression_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private EvaluateExpression_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static EvaluateExpression_Req getDefaultInstance() {
        return EvaluateExpression_Req.defaultInstance;
    }
    
    public EvaluateExpression_Req getDefaultInstanceForType() {
        return EvaluateExpression_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private EvaluateExpression_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.expression_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.threadId_ = codedInputStream.readUInt32();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x4;
                        this.frameIndex_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 40: {
                        final int enum1 = codedInputStream.readEnum();
                        final Model.Language value = Model.Language.valueOf(enum1);
                        try {
                            if (value == null) {
                                builder.mergeVarintField(5, enum1);
                                continue;
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x8;
                        this.language_ = value;
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
        return Protocol.access$29200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$29300().ensureFieldAccessorsInitialized((Class)EvaluateExpression_Req.class, (Class)Builder.class);
    }
    
    public Parser<EvaluateExpression_Req> getParserForType() {
        return EvaluateExpression_Req.PARSER;
    }
    
    public boolean hasExpression() {
        return (this.bitField0_ & 0x1) == 0x1;
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
    
    public boolean hasThreadId() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    public boolean hasFrameIndex() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public int getFrameIndex() {
        return this.frameIndex_;
    }
    
    public boolean hasLanguage() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public Model.Language getLanguage() {
        return this.language_;
    }
    
    private void a() {
        this.expression_ = "";
        this.threadId_ = 0;
        this.frameIndex_ = 0;
        this.language_ = Model.Language.LanguageUnknown;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasExpression()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasThreadId()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasFrameIndex()) {
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
                codedOutputStream.writeBytes(2, this.getExpressionBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeUInt32(3, this.threadId_);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeInt32(4, this.frameIndex_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeEnum(5, this.language_.getNumber());
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
            n += CodedOutputStream.computeBytesSize(2, this.getExpressionBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeUInt32Size(3, this.threadId_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeInt32Size(4, this.frameIndex_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeEnumSize(5, this.language_.getNumber());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static EvaluateExpression_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseFrom(byteString);
    }
    
    public static EvaluateExpression_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static EvaluateExpression_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseFrom(array);
    }
    
    public static EvaluateExpression_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static EvaluateExpression_Req parseFrom(final InputStream inputStream) throws IOException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseFrom(inputStream);
    }
    
    public static EvaluateExpression_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static EvaluateExpression_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static EvaluateExpression_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static EvaluateExpression_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static EvaluateExpression_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final EvaluateExpression_Req evaluateExpression_Req) {
        return newBuilder().mergeFrom(evaluateExpression_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        EvaluateExpression_Req.PARSER = (Parser<EvaluateExpression_Req>)new AbstractParser<EvaluateExpression_Req>() {
            public EvaluateExpression_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new EvaluateExpression_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new EvaluateExpression_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements EvaluateExpression_ReqOrBuilder
    {
        private int bitField0_;
        private Object expression_;
        private int threadId_;
        private int frameIndex_;
        private Model.Language language_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$29200();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$29300().ensureFieldAccessorsInitialized((Class)EvaluateExpression_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.expression_ = "";
            this.language_ = Model.Language.LanguageUnknown;
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.expression_ = "";
            this.language_ = Model.Language.LanguageUnknown;
            this.b();
        }
        
        private void b() {
            if (EvaluateExpression_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.expression_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.threadId_ = 0;
            this.bitField0_ &= 0xFFFFFFFD;
            this.frameIndex_ = 0;
            this.bitField0_ &= 0xFFFFFFFB;
            this.language_ = Model.Language.LanguageUnknown;
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$29200();
        }
        
        public EvaluateExpression_Req getDefaultInstanceForType() {
            return EvaluateExpression_Req.getDefaultInstance();
        }
        
        public EvaluateExpression_Req build() {
            final EvaluateExpression_Req buildPartial = this.buildPartial();
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
        
        public EvaluateExpression_Req buildPartial() {
            final EvaluateExpression_Req evaluateExpression_Req = new EvaluateExpression_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            evaluateExpression_Req.expression_ = this.expression_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            evaluateExpression_Req.threadId_ = this.threadId_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            evaluateExpression_Req.frameIndex_ = this.frameIndex_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            evaluateExpression_Req.language_ = this.language_;
            evaluateExpression_Req.bitField0_ = n;
            this.onBuilt();
            return evaluateExpression_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof EvaluateExpression_Req) {
                    return this.mergeFrom((EvaluateExpression_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final EvaluateExpression_Req evaluateExpression_Req) {
            try {
                if (evaluateExpression_Req == EvaluateExpression_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (evaluateExpression_Req.hasExpression()) {
                    this.bitField0_ |= 0x1;
                    this.expression_ = evaluateExpression_Req.expression_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (evaluateExpression_Req.hasThreadId()) {
                    this.setThreadId(evaluateExpression_Req.getThreadId());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (evaluateExpression_Req.hasFrameIndex()) {
                    this.setFrameIndex(evaluateExpression_Req.getFrameIndex());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (evaluateExpression_Req.hasLanguage()) {
                    this.setLanguage(evaluateExpression_Req.getLanguage());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            this.mergeUnknownFields(evaluateExpression_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasExpression()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasThreadId()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.hasFrameIndex()) {
                    return false;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            EvaluateExpression_Req evaluateExpression_Req = null;
            try {
                evaluateExpression_Req = (EvaluateExpression_Req)EvaluateExpression_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                evaluateExpression_Req = (EvaluateExpression_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (evaluateExpression_Req != null) {
                        this.mergeFrom(evaluateExpression_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasExpression() {
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
            this.bitField0_ |= 0x1;
            this.expression_ = expression_;
            this.onChanged();
            return this;
        }
        
        public Builder clearExpression() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.expression_ = EvaluateExpression_Req.getDefaultInstance().getExpression();
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
            this.bitField0_ |= 0x1;
            this.expression_ = expression_;
            this.onChanged();
            return this;
        }
        
        public boolean hasThreadId() {
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
        
        public int getThreadId() {
            return this.threadId_;
        }
        
        public Builder setThreadId(final int threadId_) {
            this.bitField0_ |= 0x2;
            this.threadId_ = threadId_;
            this.onChanged();
            return this;
        }
        
        public Builder clearThreadId() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.threadId_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasFrameIndex() {
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
        
        public int getFrameIndex() {
            return this.frameIndex_;
        }
        
        public Builder setFrameIndex(final int frameIndex_) {
            this.bitField0_ |= 0x4;
            this.frameIndex_ = frameIndex_;
            this.onChanged();
            return this;
        }
        
        public Builder clearFrameIndex() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.frameIndex_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasLanguage() {
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
        
        public Model.Language getLanguage() {
            return this.language_;
        }
        
        public Builder setLanguage(final Model.Language language_) {
            try {
                if (language_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x8;
            this.language_ = language_;
            this.onChanged();
            return this;
        }
        
        public Builder clearLanguage() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.language_ = Model.Language.LanguageUnknown;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
