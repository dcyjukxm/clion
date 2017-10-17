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

public static final class ValuesFilteringPolicy_Req extends GeneratedMessage implements ValuesFilteringPolicy_ReqOrBuilder
{
    private static final ValuesFilteringPolicy_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ValuesFilteringPolicy_Req> PARSER;
    private int bitField0_;
    public static final int FILTER_ENABLED_FIELD_NUMBER = 2;
    private int filterEnabled_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ValuesFilteringPolicy_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ValuesFilteringPolicy_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ValuesFilteringPolicy_Req getDefaultInstance() {
        return ValuesFilteringPolicy_Req.defaultInstance;
    }
    
    public ValuesFilteringPolicy_Req getDefaultInstanceForType() {
        return ValuesFilteringPolicy_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ValuesFilteringPolicy_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.filterEnabled_ = codedInputStream.readInt32();
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
        return Protocol.access$54500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$54600().ensureFieldAccessorsInitialized((Class)ValuesFilteringPolicy_Req.class, (Class)Builder.class);
    }
    
    public Parser<ValuesFilteringPolicy_Req> getParserForType() {
        return ValuesFilteringPolicy_Req.PARSER;
    }
    
    public boolean hasFilterEnabled() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getFilterEnabled() {
        return this.filterEnabled_;
    }
    
    private void a() {
        this.filterEnabled_ = 0;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasFilterEnabled()) {
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
                codedOutputStream.writeInt32(2, this.filterEnabled_);
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
            n += CodedOutputStream.computeInt32Size(2, this.filterEnabled_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static ValuesFilteringPolicy_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseFrom(byteString);
    }
    
    public static ValuesFilteringPolicy_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ValuesFilteringPolicy_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseFrom(array);
    }
    
    public static ValuesFilteringPolicy_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ValuesFilteringPolicy_Req parseFrom(final InputStream inputStream) throws IOException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseFrom(inputStream);
    }
    
    public static ValuesFilteringPolicy_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ValuesFilteringPolicy_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ValuesFilteringPolicy_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ValuesFilteringPolicy_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static ValuesFilteringPolicy_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req) {
        return newBuilder().mergeFrom(valuesFilteringPolicy_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ValuesFilteringPolicy_Req.PARSER = (Parser<ValuesFilteringPolicy_Req>)new AbstractParser<ValuesFilteringPolicy_Req>() {
            public ValuesFilteringPolicy_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ValuesFilteringPolicy_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ValuesFilteringPolicy_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ValuesFilteringPolicy_ReqOrBuilder
    {
        private int bitField0_;
        private int filterEnabled_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$54500();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$54600().ensureFieldAccessorsInitialized((Class)ValuesFilteringPolicy_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (ValuesFilteringPolicy_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.filterEnabled_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$54500();
        }
        
        public ValuesFilteringPolicy_Req getDefaultInstanceForType() {
            return ValuesFilteringPolicy_Req.getDefaultInstance();
        }
        
        public ValuesFilteringPolicy_Req build() {
            final ValuesFilteringPolicy_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public ValuesFilteringPolicy_Req buildPartial() {
            final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req = new ValuesFilteringPolicy_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            valuesFilteringPolicy_Req.filterEnabled_ = this.filterEnabled_;
            valuesFilteringPolicy_Req.bitField0_ = n;
            this.onBuilt();
            return valuesFilteringPolicy_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof ValuesFilteringPolicy_Req) {
                return this.mergeFrom((ValuesFilteringPolicy_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req) {
            if (valuesFilteringPolicy_Req == ValuesFilteringPolicy_Req.getDefaultInstance()) {
                return this;
            }
            if (valuesFilteringPolicy_Req.hasFilterEnabled()) {
                this.setFilterEnabled(valuesFilteringPolicy_Req.getFilterEnabled());
            }
            this.mergeUnknownFields(valuesFilteringPolicy_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasFilterEnabled();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ValuesFilteringPolicy_Req valuesFilteringPolicy_Req = null;
            try {
                valuesFilteringPolicy_Req = (ValuesFilteringPolicy_Req)ValuesFilteringPolicy_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                valuesFilteringPolicy_Req = (ValuesFilteringPolicy_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (valuesFilteringPolicy_Req != null) {
                        this.mergeFrom(valuesFilteringPolicy_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b(ex2);
                }
            }
            return this;
        }
        
        public boolean hasFilterEnabled() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getFilterEnabled() {
            return this.filterEnabled_;
        }
        
        public Builder setFilterEnabled(final int filterEnabled_) {
            this.bitField0_ |= 0x1;
            this.filterEnabled_ = filterEnabled_;
            this.onChanged();
            return this;
        }
        
        public Builder clearFilterEnabled() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.filterEnabled_ = 0;
            this.onChanged();
            return this;
        }
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
