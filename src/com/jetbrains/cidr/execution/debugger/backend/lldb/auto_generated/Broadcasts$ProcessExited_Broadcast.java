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

public static final class ProcessExited_Broadcast extends GeneratedMessage implements ProcessExited_BroadcastOrBuilder
{
    private static final ProcessExited_Broadcast defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ProcessExited_Broadcast> PARSER;
    private int bitField0_;
    public static final int EXIT_CODE_FIELD_NUMBER = 1;
    private int exitCode_;
    public static final int EXIT_DESCRIPTION_FIELD_NUMBER = 2;
    private Object exitDescription_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ProcessExited_Broadcast(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ProcessExited_Broadcast(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ProcessExited_Broadcast getDefaultInstance() {
        return ProcessExited_Broadcast.defaultInstance;
    }
    
    public ProcessExited_Broadcast getDefaultInstanceForType() {
        return ProcessExited_Broadcast.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ProcessExited_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 8: {
                        this.bitField0_ |= 0x1;
                        this.exitCode_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 18: {
                        this.bitField0_ |= 0x2;
                        this.exitDescription_ = codedInputStream.readBytes();
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
        return Broadcasts.access$1700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$1800().ensureFieldAccessorsInitialized((Class)ProcessExited_Broadcast.class, (Class)Builder.class);
    }
    
    public Parser<ProcessExited_Broadcast> getParserForType() {
        return ProcessExited_Broadcast.PARSER;
    }
    
    public boolean hasExitCode() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public int getExitCode() {
        return this.exitCode_;
    }
    
    public boolean hasExitDescription() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getExitDescription() {
        final Object exitDescription_ = this.exitDescription_;
        if (exitDescription_ instanceof String) {
            return (String)exitDescription_;
        }
        final ByteString byteString = (ByteString)exitDescription_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.exitDescription_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getExitDescriptionBytes() {
        final Object exitDescription_ = this.exitDescription_;
        if (exitDescription_ instanceof String) {
            return (ByteString)(this.exitDescription_ = ByteString.copyFromUtf8((String)exitDescription_));
        }
        return (ByteString)exitDescription_;
    }
    
    private void a() {
        this.exitCode_ = 0;
        this.exitDescription_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasExitCode()) {
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
                codedOutputStream.writeInt32(1, this.exitCode_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(2, this.getExitDescriptionBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
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
            n += CodedOutputStream.computeInt32Size(1, this.exitCode_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(2, this.getExitDescriptionBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static ProcessExited_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(byteString);
    }
    
    public static ProcessExited_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ProcessExited_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(array);
    }
    
    public static ProcessExited_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ProcessExited_Broadcast parseFrom(final InputStream inputStream) throws IOException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(inputStream);
    }
    
    public static ProcessExited_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ProcessExited_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ProcessExited_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ProcessExited_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(codedInputStream);
    }
    
    public static ProcessExited_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ProcessExited_Broadcast processExited_Broadcast) {
        return newBuilder().mergeFrom(processExited_Broadcast);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ProcessExited_Broadcast.PARSER = (Parser<ProcessExited_Broadcast>)new AbstractParser<ProcessExited_Broadcast>() {
            public ProcessExited_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProcessExited_Broadcast(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ProcessExited_Broadcast(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ProcessExited_BroadcastOrBuilder
    {
        private int bitField0_;
        private int exitCode_;
        private Object exitDescription_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.access$1700();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.access$1800().ensureFieldAccessorsInitialized((Class)ProcessExited_Broadcast.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.exitDescription_ = "";
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.exitDescription_ = "";
            this.a();
        }
        
        private void a() {
            if (ProcessExited_Broadcast.alwaysUseFieldBuilders) {}
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.exitCode_ = 0;
            this.bitField0_ &= 0xFFFFFFFE;
            this.exitDescription_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Broadcasts.access$1700();
        }
        
        public ProcessExited_Broadcast getDefaultInstanceForType() {
            return ProcessExited_Broadcast.getDefaultInstance();
        }
        
        public ProcessExited_Broadcast build() {
            final ProcessExited_Broadcast buildPartial = this.buildPartial();
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
        
        public ProcessExited_Broadcast buildPartial() {
            final ProcessExited_Broadcast processExited_Broadcast = new ProcessExited_Broadcast((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            processExited_Broadcast.exitCode_ = this.exitCode_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            processExited_Broadcast.exitDescription_ = this.exitDescription_;
            processExited_Broadcast.bitField0_ = n;
            this.onBuilt();
            return processExited_Broadcast;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof ProcessExited_Broadcast) {
                    return this.mergeFrom((ProcessExited_Broadcast)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ProcessExited_Broadcast processExited_Broadcast) {
            try {
                if (processExited_Broadcast == ProcessExited_Broadcast.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (processExited_Broadcast.hasExitCode()) {
                    this.setExitCode(processExited_Broadcast.getExitCode());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (processExited_Broadcast.hasExitDescription()) {
                    this.bitField0_ |= 0x2;
                    this.exitDescription_ = processExited_Broadcast.exitDescription_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(processExited_Broadcast.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasExitCode()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ProcessExited_Broadcast processExited_Broadcast = null;
            try {
                processExited_Broadcast = (ProcessExited_Broadcast)ProcessExited_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                processExited_Broadcast = (ProcessExited_Broadcast)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (processExited_Broadcast != null) {
                        this.mergeFrom(processExited_Broadcast);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasExitCode() {
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
        
        public int getExitCode() {
            return this.exitCode_;
        }
        
        public Builder setExitCode(final int exitCode_) {
            this.bitField0_ |= 0x1;
            this.exitCode_ = exitCode_;
            this.onChanged();
            return this;
        }
        
        public Builder clearExitCode() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.exitCode_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasExitDescription() {
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public String getExitDescription() {
            final Object exitDescription_ = this.exitDescription_;
            if (!(exitDescription_ instanceof String)) {
                return (String)(this.exitDescription_ = ((ByteString)exitDescription_).toStringUtf8());
            }
            return (String)exitDescription_;
        }
        
        public ByteString getExitDescriptionBytes() {
            final Object exitDescription_ = this.exitDescription_;
            if (exitDescription_ instanceof String) {
                return (ByteString)(this.exitDescription_ = ByteString.copyFromUtf8((String)exitDescription_));
            }
            return (ByteString)exitDescription_;
        }
        
        public Builder setExitDescription(final String exitDescription_) {
            try {
                if (exitDescription_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.exitDescription_ = exitDescription_;
            this.onChanged();
            return this;
        }
        
        public Builder clearExitDescription() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.exitDescription_ = ProcessExited_Broadcast.getDefaultInstance().getExitDescription();
            this.onChanged();
            return this;
        }
        
        public Builder setExitDescriptionBytes(final ByteString exitDescription_) {
            try {
                if (exitDescription_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.exitDescription_ = exitDescription_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
