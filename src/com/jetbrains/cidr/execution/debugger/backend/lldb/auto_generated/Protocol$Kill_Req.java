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

public static final class Kill_Req extends GeneratedMessage implements Kill_ReqOrBuilder
{
    private static final Kill_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<Kill_Req> PARSER;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private Kill_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private Kill_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static Kill_Req getDefaultInstance() {
        return Kill_Req.defaultInstance;
    }
    
    public Kill_Req getDefaultInstanceForType() {
        return Kill_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private Kill_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$9600();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$9700().ensureFieldAccessorsInitialized((Class)Kill_Req.class, (Class)Builder.class);
    }
    
    public Parser<Kill_Req> getParserForType() {
        return Kill_Req.PARSER;
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
    
    public static Kill_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (Kill_Req)Kill_Req.PARSER.parseFrom(byteString);
    }
    
    public static Kill_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Kill_Req)Kill_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static Kill_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (Kill_Req)Kill_Req.PARSER.parseFrom(array);
    }
    
    public static Kill_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Kill_Req)Kill_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static Kill_Req parseFrom(final InputStream inputStream) throws IOException {
        return (Kill_Req)Kill_Req.PARSER.parseFrom(inputStream);
    }
    
    public static Kill_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Kill_Req)Kill_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static Kill_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (Kill_Req)Kill_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static Kill_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Kill_Req)Kill_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static Kill_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (Kill_Req)Kill_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static Kill_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Kill_Req)Kill_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final Kill_Req kill_Req) {
        return newBuilder().mergeFrom(kill_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        Kill_Req.PARSER = (Parser<Kill_Req>)new AbstractParser<Kill_Req>() {
            public Kill_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Kill_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new Kill_Req(true)).a();
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements Kill_ReqOrBuilder
    {
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$9600();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$9700().ensureFieldAccessorsInitialized((Class)Kill_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (Kill_Req.alwaysUseFieldBuilders) {}
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
            return Protocol.access$9600();
        }
        
        public Kill_Req getDefaultInstanceForType() {
            return Kill_Req.getDefaultInstance();
        }
        
        public Kill_Req build() {
            final Kill_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public Kill_Req buildPartial() {
            final Kill_Req kill_Req = new Kill_Req((GeneratedMessage.Builder)this);
            this.onBuilt();
            return kill_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof Kill_Req) {
                return this.mergeFrom((Kill_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final Kill_Req kill_Req) {
            if (kill_Req == Kill_Req.getDefaultInstance()) {
                return this;
            }
            this.mergeUnknownFields(kill_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Kill_Req kill_Req = null;
            try {
                kill_Req = (Kill_Req)Kill_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                kill_Req = (Kill_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (kill_Req != null) {
                        this.mergeFrom(kill_Req);
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
