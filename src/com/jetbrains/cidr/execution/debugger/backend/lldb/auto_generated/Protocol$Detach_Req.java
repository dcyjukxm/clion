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

public static final class Detach_Req extends GeneratedMessage implements Detach_ReqOrBuilder
{
    private static final Detach_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<Detach_Req> PARSER;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private Detach_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private Detach_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static Detach_Req getDefaultInstance() {
        return Detach_Req.defaultInstance;
    }
    
    public Detach_Req getDefaultInstanceForType() {
        return Detach_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private Detach_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$8000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$8100().ensureFieldAccessorsInitialized((Class)Detach_Req.class, (Class)Builder.class);
    }
    
    public Parser<Detach_Req> getParserForType() {
        return Detach_Req.PARSER;
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
    
    public static Detach_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (Detach_Req)Detach_Req.PARSER.parseFrom(byteString);
    }
    
    public static Detach_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Detach_Req)Detach_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static Detach_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (Detach_Req)Detach_Req.PARSER.parseFrom(array);
    }
    
    public static Detach_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Detach_Req)Detach_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static Detach_Req parseFrom(final InputStream inputStream) throws IOException {
        return (Detach_Req)Detach_Req.PARSER.parseFrom(inputStream);
    }
    
    public static Detach_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Detach_Req)Detach_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static Detach_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (Detach_Req)Detach_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static Detach_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Detach_Req)Detach_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static Detach_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (Detach_Req)Detach_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static Detach_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Detach_Req)Detach_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final Detach_Req detach_Req) {
        return newBuilder().mergeFrom(detach_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        Detach_Req.PARSER = (Parser<Detach_Req>)new AbstractParser<Detach_Req>() {
            public Detach_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Detach_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new Detach_Req(true)).a();
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements Detach_ReqOrBuilder
    {
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$8000();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$8100().ensureFieldAccessorsInitialized((Class)Detach_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (Detach_Req.alwaysUseFieldBuilders) {}
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
            return Protocol.access$8000();
        }
        
        public Detach_Req getDefaultInstanceForType() {
            return Detach_Req.getDefaultInstance();
        }
        
        public Detach_Req build() {
            final Detach_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public Detach_Req buildPartial() {
            final Detach_Req detach_Req = new Detach_Req((GeneratedMessage.Builder)this);
            this.onBuilt();
            return detach_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof Detach_Req) {
                return this.mergeFrom((Detach_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final Detach_Req detach_Req) {
            if (detach_Req == Detach_Req.getDefaultInstance()) {
                return this;
            }
            this.mergeUnknownFields(detach_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Detach_Req detach_Req = null;
            try {
                detach_Req = (Detach_Req)Detach_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                detach_Req = (Detach_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (detach_Req != null) {
                        this.mergeFrom(detach_Req);
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
