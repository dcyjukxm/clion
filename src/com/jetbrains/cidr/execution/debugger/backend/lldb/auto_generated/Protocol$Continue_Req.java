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

public static final class Continue_Req extends GeneratedMessage implements Continue_ReqOrBuilder
{
    private static final Continue_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<Continue_Req> PARSER;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private Continue_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private Continue_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static Continue_Req getDefaultInstance() {
        return Continue_Req.defaultInstance;
    }
    
    public Continue_Req getDefaultInstanceForType() {
        return Continue_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private Continue_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$12100();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$12200().ensureFieldAccessorsInitialized((Class)Continue_Req.class, (Class)Builder.class);
    }
    
    public Parser<Continue_Req> getParserForType() {
        return Continue_Req.PARSER;
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
    
    public static Continue_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (Continue_Req)Continue_Req.PARSER.parseFrom(byteString);
    }
    
    public static Continue_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Continue_Req)Continue_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static Continue_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (Continue_Req)Continue_Req.PARSER.parseFrom(array);
    }
    
    public static Continue_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Continue_Req)Continue_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static Continue_Req parseFrom(final InputStream inputStream) throws IOException {
        return (Continue_Req)Continue_Req.PARSER.parseFrom(inputStream);
    }
    
    public static Continue_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Continue_Req)Continue_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static Continue_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (Continue_Req)Continue_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static Continue_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Continue_Req)Continue_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static Continue_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (Continue_Req)Continue_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static Continue_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Continue_Req)Continue_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final Continue_Req continue_Req) {
        return newBuilder().mergeFrom(continue_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        Continue_Req.PARSER = (Parser<Continue_Req>)new AbstractParser<Continue_Req>() {
            public Continue_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Continue_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new Continue_Req(true)).a();
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements Continue_ReqOrBuilder
    {
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$12100();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$12200().ensureFieldAccessorsInitialized((Class)Continue_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (Continue_Req.alwaysUseFieldBuilders) {}
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
            return Protocol.access$12100();
        }
        
        public Continue_Req getDefaultInstanceForType() {
            return Continue_Req.getDefaultInstance();
        }
        
        public Continue_Req build() {
            final Continue_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public Continue_Req buildPartial() {
            final Continue_Req continue_Req = new Continue_Req((GeneratedMessage.Builder)this);
            this.onBuilt();
            return continue_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof Continue_Req) {
                return this.mergeFrom((Continue_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final Continue_Req continue_Req) {
            if (continue_Req == Continue_Req.getDefaultInstance()) {
                return this;
            }
            this.mergeUnknownFields(continue_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Continue_Req continue_Req = null;
            try {
                continue_Req = (Continue_Req)Continue_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                continue_Req = (Continue_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (continue_Req != null) {
                        this.mergeFrom(continue_Req);
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
