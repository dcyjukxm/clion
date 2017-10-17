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

public static final class AttachByName_Req extends GeneratedMessage implements AttachByName_ReqOrBuilder
{
    private static final AttachByName_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<AttachByName_Req> PARSER;
    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 2;
    private Object name_;
    public static final int TO_WAIT_FIELD_NUMBER = 3;
    private boolean toWait_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private AttachByName_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private AttachByName_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static AttachByName_Req getDefaultInstance() {
        return AttachByName_Req.defaultInstance;
    }
    
    public AttachByName_Req getDefaultInstanceForType() {
        return AttachByName_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private AttachByName_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.name_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.toWait_ = codedInputStream.readBool();
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
        return Protocol.access$5200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$5300().ensureFieldAccessorsInitialized((Class)AttachByName_Req.class, (Class)Builder.class);
    }
    
    public Parser<AttachByName_Req> getParserForType() {
        return AttachByName_Req.PARSER;
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
    
    public boolean hasToWait() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public boolean getToWait() {
        return this.toWait_;
    }
    
    private void a() {
        this.name_ = "";
        this.toWait_ = false;
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
        if (!this.hasToWait()) {
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
                codedOutputStream.writeBytes(2, this.getNameBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBool(3, this.toWait_);
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
            n += CodedOutputStream.computeBytesSize(2, this.getNameBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBoolSize(3, this.toWait_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static AttachByName_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseFrom(byteString);
    }
    
    public static AttachByName_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static AttachByName_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseFrom(array);
    }
    
    public static AttachByName_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static AttachByName_Req parseFrom(final InputStream inputStream) throws IOException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseFrom(inputStream);
    }
    
    public static AttachByName_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static AttachByName_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static AttachByName_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static AttachByName_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static AttachByName_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AttachByName_Req)AttachByName_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final AttachByName_Req attachByName_Req) {
        return newBuilder().mergeFrom(attachByName_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        AttachByName_Req.PARSER = (Parser<AttachByName_Req>)new AbstractParser<AttachByName_Req>() {
            public AttachByName_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AttachByName_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new AttachByName_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements AttachByName_ReqOrBuilder
    {
        private int bitField0_;
        private Object name_;
        private boolean toWait_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$5200();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$5300().ensureFieldAccessorsInitialized((Class)AttachByName_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.name_ = "";
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.a();
        }
        
        private void a() {
            if (AttachByName_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.toWait_ = false;
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$5200();
        }
        
        public AttachByName_Req getDefaultInstanceForType() {
            return AttachByName_Req.getDefaultInstance();
        }
        
        public AttachByName_Req build() {
            final AttachByName_Req buildPartial = this.buildPartial();
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
        
        public AttachByName_Req buildPartial() {
            final AttachByName_Req attachByName_Req = new AttachByName_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            attachByName_Req.name_ = this.name_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            attachByName_Req.toWait_ = this.toWait_;
            attachByName_Req.bitField0_ = n;
            this.onBuilt();
            return attachByName_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof AttachByName_Req) {
                    return this.mergeFrom((AttachByName_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final AttachByName_Req attachByName_Req) {
            try {
                if (attachByName_Req == AttachByName_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (attachByName_Req.hasName()) {
                    this.bitField0_ |= 0x1;
                    this.name_ = attachByName_Req.name_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (attachByName_Req.hasToWait()) {
                    this.setToWait(attachByName_Req.getToWait());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(attachByName_Req.getUnknownFields());
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
                if (!this.hasToWait()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            AttachByName_Req attachByName_Req = null;
            try {
                attachByName_Req = (AttachByName_Req)AttachByName_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                attachByName_Req = (AttachByName_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (attachByName_Req != null) {
                        this.mergeFrom(attachByName_Req);
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
            this.name_ = AttachByName_Req.getDefaultInstance().getName();
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
        
        public boolean hasToWait() {
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
        
        public boolean getToWait() {
            return this.toWait_;
        }
        
        public Builder setToWait(final boolean toWait_) {
            this.bitField0_ |= 0x2;
            this.toWait_ = toWait_;
            this.onChanged();
            return this;
        }
        
        public Builder clearToWait() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.toWait_ = false;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
