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

public static final class Initialized_Message extends GeneratedMessage implements Initialized_MessageOrBuilder
{
    private static final Initialized_Message defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<Initialized_Message> PARSER;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private Initialized_Message(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private Initialized_Message(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static Initialized_Message getDefaultInstance() {
        return Initialized_Message.defaultInstance;
    }
    
    public Initialized_Message getDefaultInstanceForType() {
        return Initialized_Message.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private Initialized_Message(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Model.access$000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$100().ensureFieldAccessorsInitialized((Class)Initialized_Message.class, (Class)Builder.class);
    }
    
    public Parser<Initialized_Message> getParserForType() {
        return Initialized_Message.PARSER;
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
    
    public static Initialized_Message parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (Initialized_Message)Initialized_Message.PARSER.parseFrom(byteString);
    }
    
    public static Initialized_Message parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Initialized_Message)Initialized_Message.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static Initialized_Message parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (Initialized_Message)Initialized_Message.PARSER.parseFrom(array);
    }
    
    public static Initialized_Message parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Initialized_Message)Initialized_Message.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static Initialized_Message parseFrom(final InputStream inputStream) throws IOException {
        return (Initialized_Message)Initialized_Message.PARSER.parseFrom(inputStream);
    }
    
    public static Initialized_Message parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Initialized_Message)Initialized_Message.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static Initialized_Message parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (Initialized_Message)Initialized_Message.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static Initialized_Message parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Initialized_Message)Initialized_Message.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static Initialized_Message parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (Initialized_Message)Initialized_Message.PARSER.parseFrom(codedInputStream);
    }
    
    public static Initialized_Message parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Initialized_Message)Initialized_Message.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final Initialized_Message initialized_Message) {
        return newBuilder().mergeFrom(initialized_Message);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        Initialized_Message.PARSER = (Parser<Initialized_Message>)new AbstractParser<Initialized_Message>() {
            public Initialized_Message parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Initialized_Message(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new Initialized_Message(true)).a();
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements Initialized_MessageOrBuilder
    {
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.access$000();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.access$100().ensureFieldAccessorsInitialized((Class)Initialized_Message.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (Initialized_Message.alwaysUseFieldBuilders) {}
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
            return Model.access$000();
        }
        
        public Initialized_Message getDefaultInstanceForType() {
            return Initialized_Message.getDefaultInstance();
        }
        
        public Initialized_Message build() {
            final Initialized_Message buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public Initialized_Message buildPartial() {
            final Initialized_Message initialized_Message = new Initialized_Message((GeneratedMessage.Builder)this);
            this.onBuilt();
            return initialized_Message;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof Initialized_Message) {
                return this.mergeFrom((Initialized_Message)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final Initialized_Message initialized_Message) {
            if (initialized_Message == Initialized_Message.getDefaultInstance()) {
                return this;
            }
            this.mergeUnknownFields(initialized_Message.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Initialized_Message initialized_Message = null;
            try {
                initialized_Message = (Initialized_Message)Initialized_Message.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                initialized_Message = (Initialized_Message)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (initialized_Message != null) {
                        this.mergeFrom(initialized_Message);
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
