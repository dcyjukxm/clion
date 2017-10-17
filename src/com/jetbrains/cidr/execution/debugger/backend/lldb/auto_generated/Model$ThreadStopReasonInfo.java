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

public static final class ThreadStopReasonInfo extends GeneratedMessage implements ThreadStopReasonInfoOrBuilder
{
    private static final ThreadStopReasonInfo defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ThreadStopReasonInfo> PARSER;
    private int bitField0_;
    public static final int STOP_REASON_FIELD_NUMBER = 1;
    private ThreadStopReason stopReason_;
    public static final int STOP_DESCRIPTION_FIELD_NUMBER = 2;
    private Object stopDescription_;
    public static final int SIGNAL_FIELD_NUMBER = 3;
    private int signal_;
    public static final int SIGNAL_NAME_FIELD_NUMBER = 4;
    private Object signalName_;
    public static final int CODEPOINT_ID_FIELD_NUMBER = 5;
    private int codepointId_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ThreadStopReasonInfo(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ThreadStopReasonInfo(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ThreadStopReasonInfo getDefaultInstance() {
        return ThreadStopReasonInfo.defaultInstance;
    }
    
    public ThreadStopReasonInfo getDefaultInstanceForType() {
        return ThreadStopReasonInfo.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ThreadStopReasonInfo(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        final int enum1 = codedInputStream.readEnum();
                        final ThreadStopReason value = ThreadStopReason.valueOf(enum1);
                        try {
                            if (value == null) {
                                builder.mergeVarintField(1, enum1);
                                continue;
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x1;
                        this.stopReason_ = value;
                        continue;
                    }
                    case 18: {
                        this.bitField0_ |= 0x2;
                        this.stopDescription_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x4;
                        this.signal_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 34: {
                        this.bitField0_ |= 0x8;
                        this.signalName_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 40: {
                        this.bitField0_ |= 0x10;
                        this.codepointId_ = codedInputStream.readInt32();
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
        return Model.access$3300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$3400().ensureFieldAccessorsInitialized((Class)ThreadStopReasonInfo.class, (Class)Builder.class);
    }
    
    public Parser<ThreadStopReasonInfo> getParserForType() {
        return ThreadStopReasonInfo.PARSER;
    }
    
    public boolean hasStopReason() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public ThreadStopReason getStopReason() {
        return this.stopReason_;
    }
    
    public boolean hasStopDescription() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getStopDescription() {
        final Object stopDescription_ = this.stopDescription_;
        if (stopDescription_ instanceof String) {
            return (String)stopDescription_;
        }
        final ByteString byteString = (ByteString)stopDescription_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.stopDescription_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getStopDescriptionBytes() {
        final Object stopDescription_ = this.stopDescription_;
        if (stopDescription_ instanceof String) {
            return (ByteString)(this.stopDescription_ = ByteString.copyFromUtf8((String)stopDescription_));
        }
        return (ByteString)stopDescription_;
    }
    
    public boolean hasSignal() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public int getSignal() {
        return this.signal_;
    }
    
    public boolean hasSignalName() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public String getSignalName() {
        final Object signalName_ = this.signalName_;
        if (signalName_ instanceof String) {
            return (String)signalName_;
        }
        final ByteString byteString = (ByteString)signalName_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.signalName_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getSignalNameBytes() {
        final Object signalName_ = this.signalName_;
        if (signalName_ instanceof String) {
            return (ByteString)(this.signalName_ = ByteString.copyFromUtf8((String)signalName_));
        }
        return (ByteString)signalName_;
    }
    
    public boolean hasCodepointId() {
        return (this.bitField0_ & 0x10) == 0x10;
    }
    
    public int getCodepointId() {
        return this.codepointId_;
    }
    
    private void a() {
        this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
        this.stopDescription_ = "";
        this.signal_ = 0;
        this.signalName_ = "";
        this.codepointId_ = 0;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasStopReason()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasStopDescription()) {
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
                codedOutputStream.writeEnum(1, this.stopReason_.getNumber());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(2, this.getStopDescriptionBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeInt32(3, this.signal_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeBytes(4, this.getSignalNameBytes());
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
        }
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                codedOutputStream.writeInt32(5, this.codepointId_);
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
            n += CodedOutputStream.computeEnumSize(1, this.stopReason_.getNumber());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(2, this.getStopDescriptionBytes());
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeInt32Size(3, this.signal_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeBytesSize(4, this.getSignalNameBytes());
        }
        if ((this.bitField0_ & 0x10) == 0x10) {
            n += CodedOutputStream.computeInt32Size(5, this.codepointId_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static ThreadStopReasonInfo parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(byteString);
    }
    
    public static ThreadStopReasonInfo parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ThreadStopReasonInfo parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(array);
    }
    
    public static ThreadStopReasonInfo parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ThreadStopReasonInfo parseFrom(final InputStream inputStream) throws IOException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(inputStream);
    }
    
    public static ThreadStopReasonInfo parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ThreadStopReasonInfo parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ThreadStopReasonInfo parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ThreadStopReasonInfo parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(codedInputStream);
    }
    
    public static ThreadStopReasonInfo parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ThreadStopReasonInfo threadStopReasonInfo) {
        return newBuilder().mergeFrom(threadStopReasonInfo);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ThreadStopReasonInfo.PARSER = (Parser<ThreadStopReasonInfo>)new AbstractParser<ThreadStopReasonInfo>() {
            public ThreadStopReasonInfo parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ThreadStopReasonInfo(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ThreadStopReasonInfo(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ThreadStopReasonInfoOrBuilder
    {
        private int bitField0_;
        private ThreadStopReason stopReason_;
        private Object stopDescription_;
        private int signal_;
        private Object signalName_;
        private int codepointId_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.access$3300();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.access$3400().ensureFieldAccessorsInitialized((Class)ThreadStopReasonInfo.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
            this.stopDescription_ = "";
            this.signalName_ = "";
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
            this.stopDescription_ = "";
            this.signalName_ = "";
            this.b();
        }
        
        private void b() {
            if (ThreadStopReasonInfo.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
            this.bitField0_ &= 0xFFFFFFFE;
            this.stopDescription_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            this.signal_ = 0;
            this.bitField0_ &= 0xFFFFFFFB;
            this.signalName_ = "";
            this.bitField0_ &= 0xFFFFFFF7;
            this.codepointId_ = 0;
            this.bitField0_ &= 0xFFFFFFEF;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Model.access$3300();
        }
        
        public ThreadStopReasonInfo getDefaultInstanceForType() {
            return ThreadStopReasonInfo.getDefaultInstance();
        }
        
        public ThreadStopReasonInfo build() {
            final ThreadStopReasonInfo buildPartial = this.buildPartial();
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
        
        public ThreadStopReasonInfo buildPartial() {
            final ThreadStopReasonInfo threadStopReasonInfo = new ThreadStopReasonInfo((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            threadStopReasonInfo.stopReason_ = this.stopReason_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            threadStopReasonInfo.stopDescription_ = this.stopDescription_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            threadStopReasonInfo.signal_ = this.signal_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            threadStopReasonInfo.signalName_ = this.signalName_;
            if ((bitField0_ & 0x10) == 0x10) {
                n |= 0x10;
            }
            threadStopReasonInfo.codepointId_ = this.codepointId_;
            threadStopReasonInfo.bitField0_ = n;
            this.onBuilt();
            return threadStopReasonInfo;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof ThreadStopReasonInfo) {
                    return this.mergeFrom((ThreadStopReasonInfo)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ThreadStopReasonInfo threadStopReasonInfo) {
            try {
                if (threadStopReasonInfo == ThreadStopReasonInfo.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (threadStopReasonInfo.hasStopReason()) {
                    this.setStopReason(threadStopReasonInfo.getStopReason());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (threadStopReasonInfo.hasStopDescription()) {
                    this.bitField0_ |= 0x2;
                    this.stopDescription_ = threadStopReasonInfo.stopDescription_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (threadStopReasonInfo.hasSignal()) {
                    this.setSignal(threadStopReasonInfo.getSignal());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (threadStopReasonInfo.hasSignalName()) {
                    this.bitField0_ |= 0x8;
                    this.signalName_ = threadStopReasonInfo.signalName_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            try {
                if (threadStopReasonInfo.hasCodepointId()) {
                    this.setCodepointId(threadStopReasonInfo.getCodepointId());
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            this.mergeUnknownFields(threadStopReasonInfo.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasStopReason()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasStopDescription()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ThreadStopReasonInfo threadStopReasonInfo = null;
            try {
                threadStopReasonInfo = (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                threadStopReasonInfo = (ThreadStopReasonInfo)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (threadStopReasonInfo != null) {
                        this.mergeFrom(threadStopReasonInfo);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasStopReason() {
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
        
        public ThreadStopReason getStopReason() {
            return this.stopReason_;
        }
        
        public Builder setStopReason(final ThreadStopReason stopReason_) {
            try {
                if (stopReason_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.stopReason_ = stopReason_;
            this.onChanged();
            return this;
        }
        
        public Builder clearStopReason() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
            this.onChanged();
            return this;
        }
        
        public boolean hasStopDescription() {
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
        
        public String getStopDescription() {
            final Object stopDescription_ = this.stopDescription_;
            if (!(stopDescription_ instanceof String)) {
                return (String)(this.stopDescription_ = ((ByteString)stopDescription_).toStringUtf8());
            }
            return (String)stopDescription_;
        }
        
        public ByteString getStopDescriptionBytes() {
            final Object stopDescription_ = this.stopDescription_;
            if (stopDescription_ instanceof String) {
                return (ByteString)(this.stopDescription_ = ByteString.copyFromUtf8((String)stopDescription_));
            }
            return (ByteString)stopDescription_;
        }
        
        public Builder setStopDescription(final String stopDescription_) {
            try {
                if (stopDescription_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.stopDescription_ = stopDescription_;
            this.onChanged();
            return this;
        }
        
        public Builder clearStopDescription() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.stopDescription_ = ThreadStopReasonInfo.getDefaultInstance().getStopDescription();
            this.onChanged();
            return this;
        }
        
        public Builder setStopDescriptionBytes(final ByteString stopDescription_) {
            try {
                if (stopDescription_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.stopDescription_ = stopDescription_;
            this.onChanged();
            return this;
        }
        
        public boolean hasSignal() {
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
        
        public int getSignal() {
            return this.signal_;
        }
        
        public Builder setSignal(final int signal_) {
            this.bitField0_ |= 0x4;
            this.signal_ = signal_;
            this.onChanged();
            return this;
        }
        
        public Builder clearSignal() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.signal_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasSignalName() {
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
        
        public String getSignalName() {
            final Object signalName_ = this.signalName_;
            if (!(signalName_ instanceof String)) {
                return (String)(this.signalName_ = ((ByteString)signalName_).toStringUtf8());
            }
            return (String)signalName_;
        }
        
        public ByteString getSignalNameBytes() {
            final Object signalName_ = this.signalName_;
            if (signalName_ instanceof String) {
                return (ByteString)(this.signalName_ = ByteString.copyFromUtf8((String)signalName_));
            }
            return (ByteString)signalName_;
        }
        
        public Builder setSignalName(final String signalName_) {
            try {
                if (signalName_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x8;
            this.signalName_ = signalName_;
            this.onChanged();
            return this;
        }
        
        public Builder clearSignalName() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.signalName_ = ThreadStopReasonInfo.getDefaultInstance().getSignalName();
            this.onChanged();
            return this;
        }
        
        public Builder setSignalNameBytes(final ByteString signalName_) {
            try {
                if (signalName_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x8;
            this.signalName_ = signalName_;
            this.onChanged();
            return this;
        }
        
        public boolean hasCodepointId() {
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
        
        public int getCodepointId() {
            return this.codepointId_;
        }
        
        public Builder setCodepointId(final int codepointId_) {
            this.bitField0_ |= 0x10;
            this.codepointId_ = codepointId_;
            this.onChanged();
            return this;
        }
        
        public Builder clearCodepointId() {
            this.bitField0_ &= 0xFFFFFFEF;
            this.codepointId_ = 0;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
