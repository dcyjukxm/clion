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

public static final class GetThreads_Req extends GeneratedMessage implements GetThreads_ReqOrBuilder
{
    private static final GetThreads_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetThreads_Req> PARSER;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetThreads_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetThreads_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetThreads_Req getDefaultInstance() {
        return GetThreads_Req.defaultInstance;
    }
    
    public GetThreads_Req getDefaultInstanceForType() {
        return GetThreads_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetThreads_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$15300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$15400().ensureFieldAccessorsInitialized((Class)GetThreads_Req.class, (Class)Builder.class);
    }
    
    public Parser<GetThreads_Req> getParserForType() {
        return GetThreads_Req.PARSER;
    }
    
    private void a() {
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }
    
    public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
        this.getSerializedSize();
        this.getUnknownFields().writeTo(codedOutputStream);
    }
    
    public int getSerializedSize() {
        final int memoizedSerializedSize = this.memoizedSerializedSize;
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        return this.memoizedSerializedSize = 0 + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetThreads_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseFrom(byteString);
    }
    
    public static GetThreads_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetThreads_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseFrom(array);
    }
    
    public static GetThreads_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetThreads_Req parseFrom(final InputStream inputStream) throws IOException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseFrom(inputStream);
    }
    
    public static GetThreads_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetThreads_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetThreads_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetThreads_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetThreads_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetThreads_Req)GetThreads_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetThreads_Req getThreads_Req) {
        return newBuilder().mergeFrom(getThreads_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetThreads_Req.PARSER = (Parser<GetThreads_Req>)new AbstractParser<GetThreads_Req>() {
            public GetThreads_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetThreads_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetThreads_Req(true)).a();
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetThreads_ReqOrBuilder
    {
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$15300();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$15400().ensureFieldAccessorsInitialized((Class)GetThreads_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (GetThreads_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$15300();
        }
        
        public GetThreads_Req getDefaultInstanceForType() {
            return GetThreads_Req.getDefaultInstance();
        }
        
        public GetThreads_Req build() {
            final GetThreads_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public GetThreads_Req buildPartial() {
            final GetThreads_Req getThreads_Req = new GetThreads_Req((GeneratedMessage.Builder)this);
            this.onBuilt();
            return getThreads_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof GetThreads_Req) {
                return this.mergeFrom((GetThreads_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetThreads_Req getThreads_Req) {
            if (getThreads_Req == GetThreads_Req.getDefaultInstance()) {
                return this;
            }
            this.mergeUnknownFields(getThreads_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetThreads_Req getThreads_Req = null;
            try {
                getThreads_Req = (GetThreads_Req)GetThreads_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getThreads_Req = (GetThreads_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getThreads_Req != null) {
                        this.mergeFrom(getThreads_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b(ex2);
                }
            }
            return this;
        }
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
