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

public static final class RemoveWatchpoint_Req extends GeneratedMessage implements RemoveWatchpoint_ReqOrBuilder
{
    private static final RemoveWatchpoint_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<RemoveWatchpoint_Req> PARSER;
    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 2;
    private int id_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private RemoveWatchpoint_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private RemoveWatchpoint_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static RemoveWatchpoint_Req getDefaultInstance() {
        return RemoveWatchpoint_Req.defaultInstance;
    }
    
    public RemoveWatchpoint_Req getDefaultInstanceForType() {
        return RemoveWatchpoint_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private RemoveWatchpoint_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Protocol.access$32700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$32800().ensureFieldAccessorsInitialized((Class)RemoveWatchpoint_Req.class, (Class)Builder.class);
    }
    
    public Parser<RemoveWatchpoint_Req> getParserForType() {
        return RemoveWatchpoint_Req.PARSER;
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
    
    public static RemoveWatchpoint_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseFrom(byteString);
    }
    
    public static RemoveWatchpoint_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static RemoveWatchpoint_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseFrom(array);
    }
    
    public static RemoveWatchpoint_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static RemoveWatchpoint_Req parseFrom(final InputStream inputStream) throws IOException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseFrom(inputStream);
    }
    
    public static RemoveWatchpoint_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static RemoveWatchpoint_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static RemoveWatchpoint_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static RemoveWatchpoint_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static RemoveWatchpoint_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final RemoveWatchpoint_Req removeWatchpoint_Req) {
        return newBuilder().mergeFrom(removeWatchpoint_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        RemoveWatchpoint_Req.PARSER = (Parser<RemoveWatchpoint_Req>)new AbstractParser<RemoveWatchpoint_Req>() {
            public RemoveWatchpoint_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RemoveWatchpoint_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new RemoveWatchpoint_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements RemoveWatchpoint_ReqOrBuilder
    {
        private int bitField0_;
        private int id_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$32700();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$32800().ensureFieldAccessorsInitialized((Class)RemoveWatchpoint_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (RemoveWatchpoint_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.id_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$32700();
        }
        
        public RemoveWatchpoint_Req getDefaultInstanceForType() {
            return RemoveWatchpoint_Req.getDefaultInstance();
        }
        
        public RemoveWatchpoint_Req build() {
            final RemoveWatchpoint_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public RemoveWatchpoint_Req buildPartial() {
            final RemoveWatchpoint_Req removeWatchpoint_Req = new RemoveWatchpoint_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            removeWatchpoint_Req.id_ = this.id_;
            removeWatchpoint_Req.bitField0_ = n;
            this.onBuilt();
            return removeWatchpoint_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof RemoveWatchpoint_Req) {
                return this.mergeFrom((RemoveWatchpoint_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final RemoveWatchpoint_Req removeWatchpoint_Req) {
            if (removeWatchpoint_Req == RemoveWatchpoint_Req.getDefaultInstance()) {
                return this;
            }
            if (removeWatchpoint_Req.hasId()) {
                this.setId(removeWatchpoint_Req.getId());
            }
            this.mergeUnknownFields(removeWatchpoint_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasId();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            RemoveWatchpoint_Req removeWatchpoint_Req = null;
            try {
                removeWatchpoint_Req = (RemoveWatchpoint_Req)RemoveWatchpoint_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                removeWatchpoint_Req = (RemoveWatchpoint_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (removeWatchpoint_Req != null) {
                        this.mergeFrom(removeWatchpoint_Req);
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
