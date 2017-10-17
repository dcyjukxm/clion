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

public static final class Exit_Req extends GeneratedMessage implements Exit_ReqOrBuilder
{
    private static final Exit_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<Exit_Req> PARSER;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private Exit_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private Exit_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static Exit_Req getDefaultInstance() {
        return Exit_Req.defaultInstance;
    }
    
    public Exit_Req getDefaultInstanceForType() {
        return Exit_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private Exit_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$58200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$58300().ensureFieldAccessorsInitialized((Class)Exit_Req.class, (Class)Builder.class);
    }
    
    public Parser<Exit_Req> getParserForType() {
        return Exit_Req.PARSER;
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
    
    public static Exit_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (Exit_Req)Exit_Req.PARSER.parseFrom(byteString);
    }
    
    public static Exit_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Exit_Req)Exit_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static Exit_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (Exit_Req)Exit_Req.PARSER.parseFrom(array);
    }
    
    public static Exit_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Exit_Req)Exit_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static Exit_Req parseFrom(final InputStream inputStream) throws IOException {
        return (Exit_Req)Exit_Req.PARSER.parseFrom(inputStream);
    }
    
    public static Exit_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Exit_Req)Exit_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static Exit_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (Exit_Req)Exit_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static Exit_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Exit_Req)Exit_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static Exit_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (Exit_Req)Exit_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static Exit_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Exit_Req)Exit_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final Exit_Req exit_Req) {
        return newBuilder().mergeFrom(exit_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        Exit_Req.PARSER = (Parser<Exit_Req>)new AbstractParser<Exit_Req>() {
            public Exit_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Exit_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new Exit_Req(true)).a();
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements Exit_ReqOrBuilder
    {
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$58200();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$58300().ensureFieldAccessorsInitialized((Class)Exit_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (Exit_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$58200();
        }
        
        public Exit_Req getDefaultInstanceForType() {
            return Exit_Req.getDefaultInstance();
        }
        
        public Exit_Req build() {
            final Exit_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public Exit_Req buildPartial() {
            final Exit_Req exit_Req = new Exit_Req((GeneratedMessage.Builder)this);
            this.onBuilt();
            return exit_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof Exit_Req) {
                return this.mergeFrom((Exit_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final Exit_Req exit_Req) {
            if (exit_Req == Exit_Req.getDefaultInstance()) {
                return this;
            }
            this.mergeUnknownFields(exit_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Exit_Req exit_Req = null;
            try {
                exit_Req = (Exit_Req)Exit_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                exit_Req = (Exit_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (exit_Req != null) {
                        this.mergeFrom(exit_Req);
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
