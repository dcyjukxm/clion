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

public static final class TargetProcessOutput_Broadcast extends GeneratedMessage implements TargetProcessOutput_BroadcastOrBuilder
{
    private static final TargetProcessOutput_Broadcast defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<TargetProcessOutput_Broadcast> PARSER;
    private int bitField0_;
    public static final int TEXT_FIELD_NUMBER = 1;
    private Object text_;
    public static final int OUTPUT_TYPE_FIELD_NUMBER = 2;
    private Model.OutputType outputType_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private TargetProcessOutput_Broadcast(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private TargetProcessOutput_Broadcast(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static TargetProcessOutput_Broadcast getDefaultInstance() {
        return TargetProcessOutput_Broadcast.defaultInstance;
    }
    
    public TargetProcessOutput_Broadcast getDefaultInstanceForType() {
        return TargetProcessOutput_Broadcast.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private TargetProcessOutput_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.text_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 16: {
                        final int enum1 = codedInputStream.readEnum();
                        final Model.OutputType value = Model.OutputType.valueOf(enum1);
                        try {
                            if (value == null) {
                                builder.mergeVarintField(2, enum1);
                                continue;
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x2;
                        this.outputType_ = value;
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
        return Broadcasts.access$2700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$2800().ensureFieldAccessorsInitialized((Class)TargetProcessOutput_Broadcast.class, (Class)Builder.class);
    }
    
    public Parser<TargetProcessOutput_Broadcast> getParserForType() {
        return TargetProcessOutput_Broadcast.PARSER;
    }
    
    public boolean hasText() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getText() {
        final Object text_ = this.text_;
        if (text_ instanceof String) {
            return (String)text_;
        }
        final ByteString byteString = (ByteString)text_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.text_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getTextBytes() {
        final Object text_ = this.text_;
        if (text_ instanceof String) {
            return (ByteString)(this.text_ = ByteString.copyFromUtf8((String)text_));
        }
        return (ByteString)text_;
    }
    
    public boolean hasOutputType() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public Model.OutputType getOutputType() {
        return this.outputType_;
    }
    
    private void a() {
        this.text_ = "";
        this.outputType_ = Model.OutputType.OutputTypeStdout;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasText()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasOutputType()) {
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
                codedOutputStream.writeBytes(1, this.getTextBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeEnum(2, this.outputType_.getNumber());
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
            n += CodedOutputStream.computeBytesSize(1, this.getTextBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeEnumSize(2, this.outputType_.getNumber());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static TargetProcessOutput_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(byteString);
    }
    
    public static TargetProcessOutput_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static TargetProcessOutput_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(array);
    }
    
    public static TargetProcessOutput_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static TargetProcessOutput_Broadcast parseFrom(final InputStream inputStream) throws IOException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(inputStream);
    }
    
    public static TargetProcessOutput_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static TargetProcessOutput_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static TargetProcessOutput_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static TargetProcessOutput_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(codedInputStream);
    }
    
    public static TargetProcessOutput_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast) {
        return newBuilder().mergeFrom(targetProcessOutput_Broadcast);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        TargetProcessOutput_Broadcast.PARSER = (Parser<TargetProcessOutput_Broadcast>)new AbstractParser<TargetProcessOutput_Broadcast>() {
            public TargetProcessOutput_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TargetProcessOutput_Broadcast(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new TargetProcessOutput_Broadcast(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements TargetProcessOutput_BroadcastOrBuilder
    {
        private int bitField0_;
        private Object text_;
        private Model.OutputType outputType_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.access$2700();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.access$2800().ensureFieldAccessorsInitialized((Class)TargetProcessOutput_Broadcast.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.text_ = "";
            this.outputType_ = Model.OutputType.OutputTypeStdout;
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.text_ = "";
            this.outputType_ = Model.OutputType.OutputTypeStdout;
            this.a();
        }
        
        private void a() {
            if (TargetProcessOutput_Broadcast.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.text_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.outputType_ = Model.OutputType.OutputTypeStdout;
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Broadcasts.access$2700();
        }
        
        public TargetProcessOutput_Broadcast getDefaultInstanceForType() {
            return TargetProcessOutput_Broadcast.getDefaultInstance();
        }
        
        public TargetProcessOutput_Broadcast build() {
            final TargetProcessOutput_Broadcast buildPartial = this.buildPartial();
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
        
        public TargetProcessOutput_Broadcast buildPartial() {
            final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = new TargetProcessOutput_Broadcast((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            targetProcessOutput_Broadcast.text_ = this.text_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            targetProcessOutput_Broadcast.outputType_ = this.outputType_;
            targetProcessOutput_Broadcast.bitField0_ = n;
            this.onBuilt();
            return targetProcessOutput_Broadcast;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof TargetProcessOutput_Broadcast) {
                    return this.mergeFrom((TargetProcessOutput_Broadcast)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final TargetProcessOutput_Broadcast targetProcessOutput_Broadcast) {
            try {
                if (targetProcessOutput_Broadcast == TargetProcessOutput_Broadcast.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (targetProcessOutput_Broadcast.hasText()) {
                    this.bitField0_ |= 0x1;
                    this.text_ = targetProcessOutput_Broadcast.text_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (targetProcessOutput_Broadcast.hasOutputType()) {
                    this.setOutputType(targetProcessOutput_Broadcast.getOutputType());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(targetProcessOutput_Broadcast.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasText()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasOutputType()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            TargetProcessOutput_Broadcast targetProcessOutput_Broadcast = null;
            try {
                targetProcessOutput_Broadcast = (TargetProcessOutput_Broadcast)TargetProcessOutput_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                targetProcessOutput_Broadcast = (TargetProcessOutput_Broadcast)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (targetProcessOutput_Broadcast != null) {
                        this.mergeFrom(targetProcessOutput_Broadcast);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasText() {
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
        
        public String getText() {
            final Object text_ = this.text_;
            if (!(text_ instanceof String)) {
                return (String)(this.text_ = ((ByteString)text_).toStringUtf8());
            }
            return (String)text_;
        }
        
        public ByteString getTextBytes() {
            final Object text_ = this.text_;
            if (text_ instanceof String) {
                return (ByteString)(this.text_ = ByteString.copyFromUtf8((String)text_));
            }
            return (ByteString)text_;
        }
        
        public Builder setText(final String text_) {
            try {
                if (text_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.text_ = text_;
            this.onChanged();
            return this;
        }
        
        public Builder clearText() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.text_ = TargetProcessOutput_Broadcast.getDefaultInstance().getText();
            this.onChanged();
            return this;
        }
        
        public Builder setTextBytes(final ByteString text_) {
            try {
                if (text_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.text_ = text_;
            this.onChanged();
            return this;
        }
        
        public boolean hasOutputType() {
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
        
        public Model.OutputType getOutputType() {
            return this.outputType_;
        }
        
        public Builder setOutputType(final Model.OutputType outputType_) {
            try {
                if (outputType_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.outputType_ = outputType_;
            this.onChanged();
            return this;
        }
        
        public Builder clearOutputType() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.outputType_ = Model.OutputType.OutputTypeStdout;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
