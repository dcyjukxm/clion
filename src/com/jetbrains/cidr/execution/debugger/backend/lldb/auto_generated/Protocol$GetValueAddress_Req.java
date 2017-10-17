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

public static final class GetValueAddress_Req extends GeneratedMessage implements GetValueAddress_ReqOrBuilder
{
    private static final GetValueAddress_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetValueAddress_Req> PARSER;
    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 2;
    private int id_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetValueAddress_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetValueAddress_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetValueAddress_Req getDefaultInstance() {
        return GetValueAddress_Req.defaultInstance;
    }
    
    public GetValueAddress_Req getDefaultInstanceForType() {
        return GetValueAddress_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetValueAddress_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$58900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$59000().ensureFieldAccessorsInitialized((Class)GetValueAddress_Req.class, (Class)Builder.class);
    }
    
    public Parser<GetValueAddress_Req> getParserForType() {
        return GetValueAddress_Req.PARSER;
    }
    
    public boolean hasId() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getId() {
        return this.id_;
    }
    
    private void a() {
        this.id_ = 0;
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
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetValueAddress_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseFrom(byteString);
    }
    
    public static GetValueAddress_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetValueAddress_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseFrom(array);
    }
    
    public static GetValueAddress_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetValueAddress_Req parseFrom(final InputStream inputStream) throws IOException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseFrom(inputStream);
    }
    
    public static GetValueAddress_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueAddress_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetValueAddress_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueAddress_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetValueAddress_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueAddress_Req)GetValueAddress_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetValueAddress_Req getValueAddress_Req) {
        return newBuilder().mergeFrom(getValueAddress_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetValueAddress_Req.PARSER = (Parser<GetValueAddress_Req>)new AbstractParser<GetValueAddress_Req>() {
            public GetValueAddress_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetValueAddress_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetValueAddress_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueAddress_ReqOrBuilder
    {
        private int bitField0_;
        private int id_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$58900();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$59000().ensureFieldAccessorsInitialized((Class)GetValueAddress_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (GetValueAddress_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.id_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$58900();
        }
        
        public GetValueAddress_Req getDefaultInstanceForType() {
            return GetValueAddress_Req.getDefaultInstance();
        }
        
        public GetValueAddress_Req build() {
            final GetValueAddress_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public GetValueAddress_Req buildPartial() {
            final GetValueAddress_Req getValueAddress_Req = new GetValueAddress_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            getValueAddress_Req.id_ = this.id_;
            getValueAddress_Req.bitField0_ = n;
            this.onBuilt();
            return getValueAddress_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof GetValueAddress_Req) {
                return this.mergeFrom((GetValueAddress_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetValueAddress_Req getValueAddress_Req) {
            if (getValueAddress_Req == GetValueAddress_Req.getDefaultInstance()) {
                return this;
            }
            if (getValueAddress_Req.hasId()) {
                this.setId(getValueAddress_Req.getId());
            }
            this.mergeUnknownFields(getValueAddress_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasId();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetValueAddress_Req getValueAddress_Req = null;
            try {
                getValueAddress_Req = (GetValueAddress_Req)GetValueAddress_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getValueAddress_Req = (GetValueAddress_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getValueAddress_Req != null) {
                        this.mergeFrom(getValueAddress_Req);
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
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
