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

public static final class Suspend_Req extends GeneratedMessage implements Suspend_ReqOrBuilder
{
    private static final Suspend_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<Suspend_Req> PARSER;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private Suspend_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private Suspend_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static Suspend_Req getDefaultInstance() {
        return Suspend_Req.defaultInstance;
    }
    
    public Suspend_Req getDefaultInstanceForType() {
        return Suspend_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private Suspend_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$13700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$13800().ensureFieldAccessorsInitialized((Class)Suspend_Req.class, (Class)Builder.class);
    }
    
    public Parser<Suspend_Req> getParserForType() {
        return Suspend_Req.PARSER;
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
    
    public static Suspend_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (Suspend_Req)Suspend_Req.PARSER.parseFrom(byteString);
    }
    
    public static Suspend_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Suspend_Req)Suspend_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static Suspend_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (Suspend_Req)Suspend_Req.PARSER.parseFrom(array);
    }
    
    public static Suspend_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Suspend_Req)Suspend_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static Suspend_Req parseFrom(final InputStream inputStream) throws IOException {
        return (Suspend_Req)Suspend_Req.PARSER.parseFrom(inputStream);
    }
    
    public static Suspend_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Suspend_Req)Suspend_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static Suspend_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (Suspend_Req)Suspend_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static Suspend_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Suspend_Req)Suspend_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static Suspend_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (Suspend_Req)Suspend_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static Suspend_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Suspend_Req)Suspend_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final Suspend_Req suspend_Req) {
        return newBuilder().mergeFrom(suspend_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        Suspend_Req.PARSER = (Parser<Suspend_Req>)new AbstractParser<Suspend_Req>() {
            public Suspend_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Suspend_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new Suspend_Req(true)).a();
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements Suspend_ReqOrBuilder
    {
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$13700();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$13800().ensureFieldAccessorsInitialized((Class)Suspend_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (Suspend_Req.alwaysUseFieldBuilders) {}
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
            return Protocol.access$13700();
        }
        
        public Suspend_Req getDefaultInstanceForType() {
            return Suspend_Req.getDefaultInstance();
        }
        
        public Suspend_Req build() {
            final Suspend_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public Suspend_Req buildPartial() {
            final Suspend_Req suspend_Req = new Suspend_Req((GeneratedMessage.Builder)this);
            this.onBuilt();
            return suspend_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof Suspend_Req) {
                return this.mergeFrom((Suspend_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final Suspend_Req suspend_Req) {
            if (suspend_Req == Suspend_Req.getDefaultInstance()) {
                return this;
            }
            this.mergeUnknownFields(suspend_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Suspend_Req suspend_Req = null;
            try {
                suspend_Req = (Suspend_Req)Suspend_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                suspend_Req = (Suspend_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (suspend_Req != null) {
                        this.mergeFrom(suspend_Req);
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
