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

public static final class DispatchInput_Req extends GeneratedMessage implements DispatchInput_ReqOrBuilder
{
    private static final DispatchInput_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<DispatchInput_Req> PARSER;
    private int bitField0_;
    public static final int INPUT_FIELD_NUMBER = 2;
    private Object input_;
    public static final int TARGET_FIELD_NUMBER = 3;
    private Model.DispatchTarget target_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private DispatchInput_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private DispatchInput_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static DispatchInput_Req getDefaultInstance() {
        return DispatchInput_Req.defaultInstance;
    }
    
    public DispatchInput_Req getDefaultInstanceForType() {
        return DispatchInput_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private DispatchInput_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.input_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 24: {
                        final int enum1 = codedInputStream.readEnum();
                        final Model.DispatchTarget value = Model.DispatchTarget.valueOf(enum1);
                        try {
                            if (value == null) {
                                builder.mergeVarintField(3, enum1);
                                continue;
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x2;
                        this.target_ = value;
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
        return Protocol.access$48300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$48400().ensureFieldAccessorsInitialized((Class)DispatchInput_Req.class, (Class)Builder.class);
    }
    
    public Parser<DispatchInput_Req> getParserForType() {
        return DispatchInput_Req.PARSER;
    }
    
    public boolean hasInput() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getInput() {
        final Object input_ = this.input_;
        if (input_ instanceof String) {
            return (String)input_;
        }
        final ByteString byteString = (ByteString)input_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.input_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getInputBytes() {
        final Object input_ = this.input_;
        if (input_ instanceof String) {
            return (ByteString)(this.input_ = ByteString.copyFromUtf8((String)input_));
        }
        return (ByteString)input_;
    }
    
    public boolean hasTarget() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public Model.DispatchTarget getTarget() {
        return this.target_;
    }
    
    private void a() {
        this.input_ = "";
        this.target_ = Model.DispatchTarget.DispatchTargetProcess;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasInput()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasTarget()) {
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
                codedOutputStream.writeBytes(2, this.getInputBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeEnum(3, this.target_.getNumber());
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
            n += CodedOutputStream.computeBytesSize(2, this.getInputBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeEnumSize(3, this.target_.getNumber());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static DispatchInput_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseFrom(byteString);
    }
    
    public static DispatchInput_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static DispatchInput_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseFrom(array);
    }
    
    public static DispatchInput_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static DispatchInput_Req parseFrom(final InputStream inputStream) throws IOException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseFrom(inputStream);
    }
    
    public static DispatchInput_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static DispatchInput_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static DispatchInput_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static DispatchInput_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static DispatchInput_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DispatchInput_Req)DispatchInput_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final DispatchInput_Req dispatchInput_Req) {
        return newBuilder().mergeFrom(dispatchInput_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        DispatchInput_Req.PARSER = (Parser<DispatchInput_Req>)new AbstractParser<DispatchInput_Req>() {
            public DispatchInput_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DispatchInput_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new DispatchInput_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements DispatchInput_ReqOrBuilder
    {
        private int bitField0_;
        private Object input_;
        private Model.DispatchTarget target_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$48300();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$48400().ensureFieldAccessorsInitialized((Class)DispatchInput_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.input_ = "";
            this.target_ = Model.DispatchTarget.DispatchTargetProcess;
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.input_ = "";
            this.target_ = Model.DispatchTarget.DispatchTargetProcess;
            this.a();
        }
        
        private void a() {
            if (DispatchInput_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.input_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.target_ = Model.DispatchTarget.DispatchTargetProcess;
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$48300();
        }
        
        public DispatchInput_Req getDefaultInstanceForType() {
            return DispatchInput_Req.getDefaultInstance();
        }
        
        public DispatchInput_Req build() {
            final DispatchInput_Req buildPartial = this.buildPartial();
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
        
        public DispatchInput_Req buildPartial() {
            final DispatchInput_Req dispatchInput_Req = new DispatchInput_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            dispatchInput_Req.input_ = this.input_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            dispatchInput_Req.target_ = this.target_;
            dispatchInput_Req.bitField0_ = n;
            this.onBuilt();
            return dispatchInput_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof DispatchInput_Req) {
                    return this.mergeFrom((DispatchInput_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final DispatchInput_Req dispatchInput_Req) {
            try {
                if (dispatchInput_Req == DispatchInput_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (dispatchInput_Req.hasInput()) {
                    this.bitField0_ |= 0x1;
                    this.input_ = dispatchInput_Req.input_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (dispatchInput_Req.hasTarget()) {
                    this.setTarget(dispatchInput_Req.getTarget());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(dispatchInput_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasInput()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasTarget()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            DispatchInput_Req dispatchInput_Req = null;
            try {
                dispatchInput_Req = (DispatchInput_Req)DispatchInput_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                dispatchInput_Req = (DispatchInput_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (dispatchInput_Req != null) {
                        this.mergeFrom(dispatchInput_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasInput() {
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
        
        public String getInput() {
            final Object input_ = this.input_;
            if (!(input_ instanceof String)) {
                return (String)(this.input_ = ((ByteString)input_).toStringUtf8());
            }
            return (String)input_;
        }
        
        public ByteString getInputBytes() {
            final Object input_ = this.input_;
            if (input_ instanceof String) {
                return (ByteString)(this.input_ = ByteString.copyFromUtf8((String)input_));
            }
            return (ByteString)input_;
        }
        
        public Builder setInput(final String input_) {
            try {
                if (input_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.input_ = input_;
            this.onChanged();
            return this;
        }
        
        public Builder clearInput() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.input_ = DispatchInput_Req.getDefaultInstance().getInput();
            this.onChanged();
            return this;
        }
        
        public Builder setInputBytes(final ByteString input_) {
            try {
                if (input_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.input_ = input_;
            this.onChanged();
            return this;
        }
        
        public boolean hasTarget() {
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
        
        public Model.DispatchTarget getTarget() {
            return this.target_;
        }
        
        public Builder setTarget(final Model.DispatchTarget target_) {
            try {
                if (target_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.target_ = target_;
            this.onChanged();
            return this;
        }
        
        public Builder clearTarget() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.target_ = Model.DispatchTarget.DispatchTargetProcess;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
