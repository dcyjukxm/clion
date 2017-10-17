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

public static final class HandleSignal_Req extends GeneratedMessage implements HandleSignal_ReqOrBuilder
{
    private static final HandleSignal_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<HandleSignal_Req> PARSER;
    private int bitField0_;
    public static final int SIGNAL_FIELD_NUMBER = 2;
    private Object signal_;
    public static final int STOP_FIELD_NUMBER = 3;
    private boolean stop_;
    public static final int PASS_FIELD_NUMBER = 4;
    private boolean pass_;
    public static final int NOTIFY_FIELD_NUMBER = 5;
    private boolean notify_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private HandleSignal_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private HandleSignal_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static HandleSignal_Req getDefaultInstance() {
        return HandleSignal_Req.defaultInstance;
    }
    
    public HandleSignal_Req getDefaultInstanceForType() {
        return HandleSignal_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private HandleSignal_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.signal_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.stop_ = codedInputStream.readBool();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x4;
                        this.pass_ = codedInputStream.readBool();
                        continue;
                    }
                    case 40: {
                        this.bitField0_ |= 0x8;
                        this.notify_ = codedInputStream.readBool();
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
        return Protocol.access$60800();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$60900().ensureFieldAccessorsInitialized((Class)HandleSignal_Req.class, (Class)Builder.class);
    }
    
    public Parser<HandleSignal_Req> getParserForType() {
        return HandleSignal_Req.PARSER;
    }
    
    public boolean hasSignal() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getSignal() {
        final Object signal_ = this.signal_;
        if (signal_ instanceof String) {
            return (String)signal_;
        }
        final ByteString byteString = (ByteString)signal_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.signal_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getSignalBytes() {
        final Object signal_ = this.signal_;
        if (signal_ instanceof String) {
            return (ByteString)(this.signal_ = ByteString.copyFromUtf8((String)signal_));
        }
        return (ByteString)signal_;
    }
    
    public boolean hasStop() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public boolean getStop() {
        return this.stop_;
    }
    
    public boolean hasPass() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public boolean getPass() {
        return this.pass_;
    }
    
    public boolean hasNotify() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public boolean getNotify() {
        return this.notify_;
    }
    
    private void a() {
        this.signal_ = "";
        this.stop_ = false;
        this.pass_ = false;
        this.notify_ = false;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasSignal()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasStop()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasPass()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasNotify()) {
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
                codedOutputStream.writeBytes(2, this.getSignalBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBool(3, this.stop_);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeBool(4, this.pass_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeBool(5, this.notify_);
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
            n += CodedOutputStream.computeBytesSize(2, this.getSignalBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBoolSize(3, this.stop_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBoolSize(4, this.pass_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeBoolSize(5, this.notify_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static HandleSignal_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseFrom(byteString);
    }
    
    public static HandleSignal_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static HandleSignal_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseFrom(array);
    }
    
    public static HandleSignal_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static HandleSignal_Req parseFrom(final InputStream inputStream) throws IOException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseFrom(inputStream);
    }
    
    public static HandleSignal_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static HandleSignal_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static HandleSignal_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static HandleSignal_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static HandleSignal_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleSignal_Req)HandleSignal_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final HandleSignal_Req handleSignal_Req) {
        return newBuilder().mergeFrom(handleSignal_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        HandleSignal_Req.PARSER = (Parser<HandleSignal_Req>)new AbstractParser<HandleSignal_Req>() {
            public HandleSignal_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HandleSignal_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new HandleSignal_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements HandleSignal_ReqOrBuilder
    {
        private int bitField0_;
        private Object signal_;
        private boolean stop_;
        private boolean pass_;
        private boolean notify_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$60800();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$60900().ensureFieldAccessorsInitialized((Class)HandleSignal_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.signal_ = "";
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.signal_ = "";
            this.a();
        }
        
        private void a() {
            if (HandleSignal_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.signal_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.stop_ = false;
            this.bitField0_ &= 0xFFFFFFFD;
            this.pass_ = false;
            this.bitField0_ &= 0xFFFFFFFB;
            this.notify_ = false;
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$60800();
        }
        
        public HandleSignal_Req getDefaultInstanceForType() {
            return HandleSignal_Req.getDefaultInstance();
        }
        
        public HandleSignal_Req build() {
            final HandleSignal_Req buildPartial = this.buildPartial();
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
        
        public HandleSignal_Req buildPartial() {
            final HandleSignal_Req handleSignal_Req = new HandleSignal_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            handleSignal_Req.signal_ = this.signal_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            handleSignal_Req.stop_ = this.stop_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            handleSignal_Req.pass_ = this.pass_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            handleSignal_Req.notify_ = this.notify_;
            handleSignal_Req.bitField0_ = n;
            this.onBuilt();
            return handleSignal_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof HandleSignal_Req) {
                    return this.mergeFrom((HandleSignal_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final HandleSignal_Req handleSignal_Req) {
            try {
                if (handleSignal_Req == HandleSignal_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (handleSignal_Req.hasSignal()) {
                    this.bitField0_ |= 0x1;
                    this.signal_ = handleSignal_Req.signal_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (handleSignal_Req.hasStop()) {
                    this.setStop(handleSignal_Req.getStop());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (handleSignal_Req.hasPass()) {
                    this.setPass(handleSignal_Req.getPass());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (handleSignal_Req.hasNotify()) {
                    this.setNotify(handleSignal_Req.getNotify());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            this.mergeUnknownFields(handleSignal_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasSignal()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasStop()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.hasPass()) {
                    return false;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (!this.hasNotify()) {
                    return false;
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            HandleSignal_Req handleSignal_Req = null;
            try {
                handleSignal_Req = (HandleSignal_Req)HandleSignal_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                handleSignal_Req = (HandleSignal_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (handleSignal_Req != null) {
                        this.mergeFrom(handleSignal_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasSignal() {
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
        
        public String getSignal() {
            final Object signal_ = this.signal_;
            if (!(signal_ instanceof String)) {
                return (String)(this.signal_ = ((ByteString)signal_).toStringUtf8());
            }
            return (String)signal_;
        }
        
        public ByteString getSignalBytes() {
            final Object signal_ = this.signal_;
            if (signal_ instanceof String) {
                return (ByteString)(this.signal_ = ByteString.copyFromUtf8((String)signal_));
            }
            return (ByteString)signal_;
        }
        
        public Builder setSignal(final String signal_) {
            try {
                if (signal_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.signal_ = signal_;
            this.onChanged();
            return this;
        }
        
        public Builder clearSignal() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.signal_ = HandleSignal_Req.getDefaultInstance().getSignal();
            this.onChanged();
            return this;
        }
        
        public Builder setSignalBytes(final ByteString signal_) {
            try {
                if (signal_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.signal_ = signal_;
            this.onChanged();
            return this;
        }
        
        public boolean hasStop() {
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
        
        public boolean getStop() {
            return this.stop_;
        }
        
        public Builder setStop(final boolean stop_) {
            this.bitField0_ |= 0x2;
            this.stop_ = stop_;
            this.onChanged();
            return this;
        }
        
        public Builder clearStop() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.stop_ = false;
            this.onChanged();
            return this;
        }
        
        public boolean hasPass() {
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
        
        public boolean getPass() {
            return this.pass_;
        }
        
        public Builder setPass(final boolean pass_) {
            this.bitField0_ |= 0x4;
            this.pass_ = pass_;
            this.onChanged();
            return this;
        }
        
        public Builder clearPass() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.pass_ = false;
            this.onChanged();
            return this;
        }
        
        public boolean hasNotify() {
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
        
        public boolean getNotify() {
            return this.notify_;
        }
        
        public Builder setNotify(final boolean notify_) {
            this.bitField0_ |= 0x8;
            this.notify_ = notify_;
            this.onChanged();
            return this;
        }
        
        public Builder clearNotify() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.notify_ = false;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
