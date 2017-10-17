// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import com.google.protobuf.ByteString;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class GetValueData_Req extends GeneratedMessage implements GetValueData_ReqOrBuilder
{
    private static final GetValueData_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetValueData_Req> PARSER;
    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 2;
    private int id_;
    public static final int MAX_DESCRIPTION_LENGTH_FIELD_NUMBER = 3;
    private int maxDescriptionLength_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetValueData_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetValueData_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetValueData_Req getDefaultInstance() {
        return GetValueData_Req.defaultInstance;
    }
    
    public GetValueData_Req getDefaultInstanceForType() {
        return GetValueData_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetValueData_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 16: {
                        this.bitField0_ |= 0x1;
                        this.id_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 24: {
                        this.bitField0_ |= 0x2;
                        this.maxDescriptionLength_ = codedInputStream.readInt32();
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
        return Protocol.access$38900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$39000().ensureFieldAccessorsInitialized((Class)GetValueData_Req.class, (Class)Builder.class);
    }
    
    public Parser<GetValueData_Req> getParserForType() {
        return GetValueData_Req.PARSER;
    }
    
    public boolean hasId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getId() {
        return this.id_;
    }
    
    public boolean hasMaxDescriptionLength() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getMaxDescriptionLength() {
        return this.maxDescriptionLength_;
    }
    
    private void a() {
        this.id_ = 0;
        this.maxDescriptionLength_ = 0;
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
        if (!this.hasMaxDescriptionLength()) {
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
                codedOutputStream.writeInt32(2, this.id_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeInt32(3, this.maxDescriptionLength_);
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
            n += CodedOutputStream.computeInt32Size(2, this.id_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(3, this.maxDescriptionLength_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetValueData_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseFrom(byteString);
    }
    
    public static GetValueData_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetValueData_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseFrom(array);
    }
    
    public static GetValueData_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetValueData_Req parseFrom(final InputStream inputStream) throws IOException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseFrom(inputStream);
    }
    
    public static GetValueData_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueData_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetValueData_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueData_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetValueData_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueData_Req)GetValueData_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetValueData_Req getValueData_Req) {
        return newBuilder().mergeFrom(getValueData_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetValueData_Req.PARSER = (Parser<GetValueData_Req>)new AbstractParser<GetValueData_Req>() {
            public GetValueData_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetValueData_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetValueData_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueData_ReqOrBuilder
    {
        private int bitField0_;
        private int id_;
        private int maxDescriptionLength_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$38900();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$39000().ensureFieldAccessorsInitialized((Class)GetValueData_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (GetValueData_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.id_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            this.maxDescriptionLength_ = 0;
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$38900();
        }
        
        public GetValueData_Req getDefaultInstanceForType() {
            return GetValueData_Req.getDefaultInstance();
        }
        
        public GetValueData_Req build() {
            final GetValueData_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public GetValueData_Req buildPartial() {
            final GetValueData_Req getValueData_Req = new GetValueData_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            getValueData_Req.id_ = this.id_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            getValueData_Req.maxDescriptionLength_ = this.maxDescriptionLength_;
            getValueData_Req.bitField0_ = n;
            this.onBuilt();
            return getValueData_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof GetValueData_Req) {
                return this.mergeFrom((GetValueData_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetValueData_Req getValueData_Req) {
            if (getValueData_Req == GetValueData_Req.getDefaultInstance()) {
                return this;
            }
            if (getValueData_Req.hasId()) {
                this.setId(getValueData_Req.getId());
            }
            if (getValueData_Req.hasMaxDescriptionLength()) {
                this.setMaxDescriptionLength(getValueData_Req.getMaxDescriptionLength());
            }
            this.mergeUnknownFields(getValueData_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasId() && this.hasMaxDescriptionLength();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetValueData_Req getValueData_Req = null;
            try {
                getValueData_Req = (GetValueData_Req)GetValueData_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getValueData_Req = (GetValueData_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getValueData_Req != null) {
                        this.mergeFrom(getValueData_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b(ex2);
                }
            }
            return this;
        }
        
        public boolean hasId() {
            return (this.bitField0_ & 0x1) == 0x1;
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
        
        public boolean hasMaxDescriptionLength() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public int getMaxDescriptionLength() {
            return this.maxDescriptionLength_;
        }
        
        public Builder setMaxDescriptionLength(final int maxDescriptionLength_) {
            this.bitField0_ |= 0x2;
            this.maxDescriptionLength_ = maxDescriptionLength_;
            this.onChanged();
            return this;
        }
        
        public Builder clearMaxDescriptionLength() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.maxDescriptionLength_ = 0;
            this.onChanged();
            return this;
        }
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
