// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class ChangePrompt_Broadcast extends GeneratedMessage implements ChangePrompt_BroadcastOrBuilder
{
    private static final ChangePrompt_Broadcast defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ChangePrompt_Broadcast> PARSER;
    private int bitField0_;
    public static final int PROMPT_FIELD_NUMBER = 1;
    private Object prompt_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ChangePrompt_Broadcast(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ChangePrompt_Broadcast(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ChangePrompt_Broadcast getDefaultInstance() {
        return ChangePrompt_Broadcast.defaultInstance;
    }
    
    public ChangePrompt_Broadcast getDefaultInstanceForType() {
        return ChangePrompt_Broadcast.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ChangePrompt_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 10: {
                        this.bitField0_ |= 0x1;
                        this.prompt_ = codedInputStream.readBytes();
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
        return Broadcasts.access$5500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$5600().ensureFieldAccessorsInitialized((Class)ChangePrompt_Broadcast.class, (Class)Builder.class);
    }
    
    public Parser<ChangePrompt_Broadcast> getParserForType() {
        return ChangePrompt_Broadcast.PARSER;
    }
    
    public boolean hasPrompt() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getPrompt() {
        final Object prompt_ = this.prompt_;
        if (prompt_ instanceof String) {
            return (String)prompt_;
        }
        final ByteString byteString = (ByteString)prompt_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.prompt_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getPromptBytes() {
        final Object prompt_ = this.prompt_;
        if (prompt_ instanceof String) {
            return (ByteString)(this.prompt_ = ByteString.copyFromUtf8((String)prompt_));
        }
        return (ByteString)prompt_;
    }
    
    private void a() {
        this.prompt_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasPrompt()) {
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
                codedOutputStream.writeBytes(1, this.getPromptBytes());
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
            n += CodedOutputStream.computeBytesSize(1, this.getPromptBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static ChangePrompt_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(byteString);
    }
    
    public static ChangePrompt_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ChangePrompt_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(array);
    }
    
    public static ChangePrompt_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ChangePrompt_Broadcast parseFrom(final InputStream inputStream) throws IOException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(inputStream);
    }
    
    public static ChangePrompt_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ChangePrompt_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ChangePrompt_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ChangePrompt_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(codedInputStream);
    }
    
    public static ChangePrompt_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ChangePrompt_Broadcast changePrompt_Broadcast) {
        return newBuilder().mergeFrom(changePrompt_Broadcast);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ChangePrompt_Broadcast.PARSER = (Parser<ChangePrompt_Broadcast>)new AbstractParser<ChangePrompt_Broadcast>() {
            public ChangePrompt_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ChangePrompt_Broadcast(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ChangePrompt_Broadcast(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ChangePrompt_BroadcastOrBuilder
    {
        private int bitField0_;
        private Object prompt_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.access$5500();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.access$5600().ensureFieldAccessorsInitialized((Class)ChangePrompt_Broadcast.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.prompt_ = "";
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.prompt_ = "";
            this.a();
        }
        
        private void a() {
            if (ChangePrompt_Broadcast.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.prompt_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Broadcasts.access$5500();
        }
        
        public ChangePrompt_Broadcast getDefaultInstanceForType() {
            return ChangePrompt_Broadcast.getDefaultInstance();
        }
        
        public ChangePrompt_Broadcast build() {
            final ChangePrompt_Broadcast buildPartial = this.buildPartial();
            try {
                if (!buildPartial.isInitialized()) {
                    throw newUninitializedMessageException((Message)buildPartial);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return buildPartial;
        }
        
        public ChangePrompt_Broadcast buildPartial() {
            final ChangePrompt_Broadcast changePrompt_Broadcast = new ChangePrompt_Broadcast((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            changePrompt_Broadcast.prompt_ = this.prompt_;
            changePrompt_Broadcast.bitField0_ = n;
            this.onBuilt();
            return changePrompt_Broadcast;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof ChangePrompt_Broadcast) {
                    return this.mergeFrom((ChangePrompt_Broadcast)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ChangePrompt_Broadcast changePrompt_Broadcast) {
            try {
                if (changePrompt_Broadcast == ChangePrompt_Broadcast.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (changePrompt_Broadcast.hasPrompt()) {
                    this.bitField0_ |= 0x1;
                    this.prompt_ = changePrompt_Broadcast.prompt_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            this.mergeUnknownFields(changePrompt_Broadcast.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasPrompt()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ChangePrompt_Broadcast changePrompt_Broadcast = null;
            try {
                changePrompt_Broadcast = (ChangePrompt_Broadcast)ChangePrompt_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                changePrompt_Broadcast = (ChangePrompt_Broadcast)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (changePrompt_Broadcast != null) {
                        this.mergeFrom(changePrompt_Broadcast);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasPrompt() {
            try {
                if ((this.bitField0_ & 0x1) == 0x1) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public String getPrompt() {
            final Object prompt_ = this.prompt_;
            if (!(prompt_ instanceof String)) {
                return (String)(this.prompt_ = ((ByteString)prompt_).toStringUtf8());
            }
            return (String)prompt_;
        }
        
        public ByteString getPromptBytes() {
            final Object prompt_ = this.prompt_;
            if (prompt_ instanceof String) {
                return (ByteString)(this.prompt_ = ByteString.copyFromUtf8((String)prompt_));
            }
            return (ByteString)prompt_;
        }
        
        public Builder setPrompt(final String prompt_) {
            try {
                if (prompt_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.prompt_ = prompt_;
            this.onChanged();
            return this;
        }
        
        public Builder clearPrompt() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.prompt_ = ChangePrompt_Broadcast.getDefaultInstance().getPrompt();
            this.onChanged();
            return this;
        }
        
        public Builder setPromptBytes(final ByteString prompt_) {
            try {
                if (prompt_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.prompt_ = prompt_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
