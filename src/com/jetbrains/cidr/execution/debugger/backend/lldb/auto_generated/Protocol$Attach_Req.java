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

public static final class Attach_Req extends GeneratedMessage implements Attach_ReqOrBuilder
{
    private static final Attach_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<Attach_Req> PARSER;
    private int bitField0_;
    public static final int PID_FIELD_NUMBER = 2;
    private int pid_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private Attach_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private Attach_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static Attach_Req getDefaultInstance() {
        return Attach_Req.defaultInstance;
    }
    
    public Attach_Req getDefaultInstanceForType() {
        return Attach_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private Attach_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.pid_ = codedInputStream.readInt32();
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
        return Protocol.access$7100();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$7200().ensureFieldAccessorsInitialized((Class)Attach_Req.class, (Class)Builder.class);
    }
    
    public Parser<Attach_Req> getParserForType() {
        return Attach_Req.PARSER;
    }
    
    public boolean hasPid() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getPid() {
        return this.pid_;
    }
    
    private void a() {
        this.pid_ = 0;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasPid()) {
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
                codedOutputStream.writeInt32(2, this.pid_);
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
            n += CodedOutputStream.computeInt32Size(2, this.pid_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static Attach_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (Attach_Req)Attach_Req.PARSER.parseFrom(byteString);
    }
    
    public static Attach_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Attach_Req)Attach_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static Attach_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (Attach_Req)Attach_Req.PARSER.parseFrom(array);
    }
    
    public static Attach_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Attach_Req)Attach_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static Attach_Req parseFrom(final InputStream inputStream) throws IOException {
        return (Attach_Req)Attach_Req.PARSER.parseFrom(inputStream);
    }
    
    public static Attach_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Attach_Req)Attach_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static Attach_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (Attach_Req)Attach_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static Attach_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Attach_Req)Attach_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static Attach_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (Attach_Req)Attach_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static Attach_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Attach_Req)Attach_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final Attach_Req attach_Req) {
        return newBuilder().mergeFrom(attach_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        Attach_Req.PARSER = (Parser<Attach_Req>)new AbstractParser<Attach_Req>() {
            public Attach_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Attach_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new Attach_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements Attach_ReqOrBuilder
    {
        private int bitField0_;
        private int pid_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$7100();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$7200().ensureFieldAccessorsInitialized((Class)Attach_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.b();
        }
        
        private void b() {
            if (Attach_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.pid_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$7100();
        }
        
        public Attach_Req getDefaultInstanceForType() {
            return Attach_Req.getDefaultInstance();
        }
        
        public Attach_Req build() {
            final Attach_Req buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public Attach_Req buildPartial() {
            final Attach_Req attach_Req = new Attach_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            attach_Req.pid_ = this.pid_;
            attach_Req.bitField0_ = n;
            this.onBuilt();
            return attach_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof Attach_Req) {
                return this.mergeFrom((Attach_Req)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final Attach_Req attach_Req) {
            if (attach_Req == Attach_Req.getDefaultInstance()) {
                return this;
            }
            if (attach_Req.hasPid()) {
                this.setPid(attach_Req.getPid());
            }
            this.mergeUnknownFields(attach_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return this.hasPid();
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Attach_Req attach_Req = null;
            try {
                attach_Req = (Attach_Req)Attach_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                attach_Req = (Attach_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (attach_Req != null) {
                        this.mergeFrom(attach_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b(ex2);
                }
            }
            return this;
        }
        
        public boolean hasPid() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getPid() {
            return this.pid_;
        }
        
        public Builder setPid(final int pid_) {
            this.bitField0_ |= 0x1;
            this.pid_ = pid_;
            this.onChanged();
            return this;
        }
        
        public Builder clearPid() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.pid_ = 0;
            this.onChanged();
            return this;
        }
        
        private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
            return ex;
        }
    }
}
