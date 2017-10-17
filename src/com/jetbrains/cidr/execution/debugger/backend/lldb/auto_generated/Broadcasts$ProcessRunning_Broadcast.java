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

public static final class ProcessRunning_Broadcast extends GeneratedMessage implements ProcessRunning_BroadcastOrBuilder
{
    private static final ProcessRunning_Broadcast defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ProcessRunning_Broadcast> PARSER;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ProcessRunning_Broadcast(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ProcessRunning_Broadcast(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ProcessRunning_Broadcast getDefaultInstance() {
        return ProcessRunning_Broadcast.defaultInstance;
    }
    
    public ProcessRunning_Broadcast getDefaultInstanceForType() {
        return ProcessRunning_Broadcast.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ProcessRunning_Broadcast(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
        return Broadcasts.access$1000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$1100().ensureFieldAccessorsInitialized((Class)ProcessRunning_Broadcast.class, (Class)Builder.class);
    }
    
    public Parser<ProcessRunning_Broadcast> getParserForType() {
        return ProcessRunning_Broadcast.PARSER;
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
    
    public static ProcessRunning_Broadcast parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(byteString);
    }
    
    public static ProcessRunning_Broadcast parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ProcessRunning_Broadcast parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(array);
    }
    
    public static ProcessRunning_Broadcast parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ProcessRunning_Broadcast parseFrom(final InputStream inputStream) throws IOException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(inputStream);
    }
    
    public static ProcessRunning_Broadcast parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ProcessRunning_Broadcast parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ProcessRunning_Broadcast parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ProcessRunning_Broadcast parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(codedInputStream);
    }
    
    public static ProcessRunning_Broadcast parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ProcessRunning_Broadcast processRunning_Broadcast) {
        return newBuilder().mergeFrom(processRunning_Broadcast);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ProcessRunning_Broadcast.PARSER = (Parser<ProcessRunning_Broadcast>)new AbstractParser<ProcessRunning_Broadcast>() {
            public ProcessRunning_Broadcast parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProcessRunning_Broadcast(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ProcessRunning_Broadcast(true)).a();
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ProcessRunning_BroadcastOrBuilder
    {
        public static final Descriptors.Descriptor getDescriptor() {
            return Broadcasts.access$1000();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Broadcasts.access$1100().ensureFieldAccessorsInitialized((Class)ProcessRunning_Broadcast.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.a();
        }
        
        private void a() {
            if (ProcessRunning_Broadcast.alwaysUseFieldBuilders) {}
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
            return Broadcasts.access$1000();
        }
        
        public ProcessRunning_Broadcast getDefaultInstanceForType() {
            return ProcessRunning_Broadcast.getDefaultInstance();
        }
        
        public ProcessRunning_Broadcast build() {
            final ProcessRunning_Broadcast buildPartial = this.buildPartial();
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
            return buildPartial;
        }
        
        public ProcessRunning_Broadcast buildPartial() {
            final ProcessRunning_Broadcast processRunning_Broadcast = new ProcessRunning_Broadcast((GeneratedMessage.Builder)this);
            this.onBuilt();
            return processRunning_Broadcast;
        }
        
        public Builder mergeFrom(final Message message) {
            if (message instanceof ProcessRunning_Broadcast) {
                return this.mergeFrom((ProcessRunning_Broadcast)message);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ProcessRunning_Broadcast processRunning_Broadcast) {
            if (processRunning_Broadcast == ProcessRunning_Broadcast.getDefaultInstance()) {
                return this;
            }
            this.mergeUnknownFields(processRunning_Broadcast.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ProcessRunning_Broadcast processRunning_Broadcast = null;
            try {
                processRunning_Broadcast = (ProcessRunning_Broadcast)ProcessRunning_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                processRunning_Broadcast = (ProcessRunning_Broadcast)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (processRunning_Broadcast != null) {
                        this.mergeFrom(processRunning_Broadcast);
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
