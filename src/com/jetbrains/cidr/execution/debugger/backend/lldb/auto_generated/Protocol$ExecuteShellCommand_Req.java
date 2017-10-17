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

public static final class ExecuteShellCommand_Req extends GeneratedMessage implements ExecuteShellCommand_ReqOrBuilder
{
    private static final ExecuteShellCommand_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<ExecuteShellCommand_Req> PARSER;
    private int bitField0_;
    public static final int COMMAND_FIELD_NUMBER = 2;
    private Object command_;
    public static final int WORKING_DIR_FIELD_NUMBER = 3;
    private Object workingDir_;
    public static final int TIMEOUT_SECS_FIELD_NUMBER = 4;
    private int timeoutSecs_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private ExecuteShellCommand_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private ExecuteShellCommand_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static ExecuteShellCommand_Req getDefaultInstance() {
        return ExecuteShellCommand_Req.defaultInstance;
    }
    
    public ExecuteShellCommand_Req getDefaultInstanceForType() {
        return ExecuteShellCommand_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private ExecuteShellCommand_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 18: {
                        this.bitField0_ |= 0x1;
                        this.command_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 26: {
                        this.bitField0_ |= 0x2;
                        this.workingDir_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 32: {
                        this.bitField0_ |= 0x4;
                        this.timeoutSecs_ = codedInputStream.readInt32();
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
        return Protocol.access$62900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$63000().ensureFieldAccessorsInitialized((Class)ExecuteShellCommand_Req.class, (Class)Builder.class);
    }
    
    public Parser<ExecuteShellCommand_Req> getParserForType() {
        return ExecuteShellCommand_Req.PARSER;
    }
    
    public boolean hasCommand() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public String getCommand() {
        final Object command_ = this.command_;
        if (command_ instanceof String) {
            return (String)command_;
        }
        final ByteString byteString = (ByteString)command_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.command_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getCommandBytes() {
        final Object command_ = this.command_;
        if (command_ instanceof String) {
            return (ByteString)(this.command_ = ByteString.copyFromUtf8((String)command_));
        }
        return (ByteString)command_;
    }
    
    public boolean hasWorkingDir() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getWorkingDir() {
        final Object workingDir_ = this.workingDir_;
        if (workingDir_ instanceof String) {
            return (String)workingDir_;
        }
        final ByteString byteString = (ByteString)workingDir_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.workingDir_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getWorkingDirBytes() {
        final Object workingDir_ = this.workingDir_;
        if (workingDir_ instanceof String) {
            return (ByteString)(this.workingDir_ = ByteString.copyFromUtf8((String)workingDir_));
        }
        return (ByteString)workingDir_;
    }
    
    public boolean hasTimeoutSecs() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public int getTimeoutSecs() {
        return this.timeoutSecs_;
    }
    
    private void a() {
        this.command_ = "";
        this.workingDir_ = "";
        this.timeoutSecs_ = 0;
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasCommand()) {
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
                codedOutputStream.writeBytes(2, this.getCommandBytes());
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(3, this.getWorkingDirBytes());
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeInt32(4, this.timeoutSecs_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
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
            n += CodedOutputStream.computeBytesSize(2, this.getCommandBytes());
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(3, this.getWorkingDirBytes());
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeInt32Size(4, this.timeoutSecs_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static ExecuteShellCommand_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseFrom(byteString);
    }
    
    public static ExecuteShellCommand_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static ExecuteShellCommand_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseFrom(array);
    }
    
    public static ExecuteShellCommand_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static ExecuteShellCommand_Req parseFrom(final InputStream inputStream) throws IOException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseFrom(inputStream);
    }
    
    public static ExecuteShellCommand_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static ExecuteShellCommand_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static ExecuteShellCommand_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static ExecuteShellCommand_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static ExecuteShellCommand_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final ExecuteShellCommand_Req executeShellCommand_Req) {
        return newBuilder().mergeFrom(executeShellCommand_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        ExecuteShellCommand_Req.PARSER = (Parser<ExecuteShellCommand_Req>)new AbstractParser<ExecuteShellCommand_Req>() {
            public ExecuteShellCommand_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ExecuteShellCommand_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new ExecuteShellCommand_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements ExecuteShellCommand_ReqOrBuilder
    {
        private int bitField0_;
        private Object command_;
        private Object workingDir_;
        private int timeoutSecs_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$62900();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$63000().ensureFieldAccessorsInitialized((Class)ExecuteShellCommand_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.command_ = "";
            this.workingDir_ = "";
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.command_ = "";
            this.workingDir_ = "";
            this.b();
        }
        
        private void b() {
            if (ExecuteShellCommand_Req.alwaysUseFieldBuilders) {}
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            super.clear();
            this.command_ = "";
            this.bitField0_ &= 0xFFFFFFFE;
            this.workingDir_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            this.timeoutSecs_ = 0;
            this.bitField0_ &= 0xFFFFFFFB;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$62900();
        }
        
        public ExecuteShellCommand_Req getDefaultInstanceForType() {
            return ExecuteShellCommand_Req.getDefaultInstance();
        }
        
        public ExecuteShellCommand_Req build() {
            final ExecuteShellCommand_Req buildPartial = this.buildPartial();
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
        
        public ExecuteShellCommand_Req buildPartial() {
            final ExecuteShellCommand_Req executeShellCommand_Req = new ExecuteShellCommand_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            executeShellCommand_Req.command_ = this.command_;
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            executeShellCommand_Req.workingDir_ = this.workingDir_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            executeShellCommand_Req.timeoutSecs_ = this.timeoutSecs_;
            executeShellCommand_Req.bitField0_ = n;
            this.onBuilt();
            return executeShellCommand_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof ExecuteShellCommand_Req) {
                    return this.mergeFrom((ExecuteShellCommand_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final ExecuteShellCommand_Req executeShellCommand_Req) {
            try {
                if (executeShellCommand_Req == ExecuteShellCommand_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (executeShellCommand_Req.hasCommand()) {
                    this.bitField0_ |= 0x1;
                    this.command_ = executeShellCommand_Req.command_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (executeShellCommand_Req.hasWorkingDir()) {
                    this.bitField0_ |= 0x2;
                    this.workingDir_ = executeShellCommand_Req.workingDir_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (executeShellCommand_Req.hasTimeoutSecs()) {
                    this.setTimeoutSecs(executeShellCommand_Req.getTimeoutSecs());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            this.mergeUnknownFields(executeShellCommand_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasCommand()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ExecuteShellCommand_Req executeShellCommand_Req = null;
            try {
                executeShellCommand_Req = (ExecuteShellCommand_Req)ExecuteShellCommand_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                executeShellCommand_Req = (ExecuteShellCommand_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (executeShellCommand_Req != null) {
                        this.mergeFrom(executeShellCommand_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasCommand() {
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
        
        public String getCommand() {
            final Object command_ = this.command_;
            if (!(command_ instanceof String)) {
                return (String)(this.command_ = ((ByteString)command_).toStringUtf8());
            }
            return (String)command_;
        }
        
        public ByteString getCommandBytes() {
            final Object command_ = this.command_;
            if (command_ instanceof String) {
                return (ByteString)(this.command_ = ByteString.copyFromUtf8((String)command_));
            }
            return (ByteString)command_;
        }
        
        public Builder setCommand(final String command_) {
            try {
                if (command_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.command_ = command_;
            this.onChanged();
            return this;
        }
        
        public Builder clearCommand() {
            this.bitField0_ &= 0xFFFFFFFE;
            this.command_ = ExecuteShellCommand_Req.getDefaultInstance().getCommand();
            this.onChanged();
            return this;
        }
        
        public Builder setCommandBytes(final ByteString command_) {
            try {
                if (command_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x1;
            this.command_ = command_;
            this.onChanged();
            return this;
        }
        
        public boolean hasWorkingDir() {
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
        
        public String getWorkingDir() {
            final Object workingDir_ = this.workingDir_;
            if (!(workingDir_ instanceof String)) {
                return (String)(this.workingDir_ = ((ByteString)workingDir_).toStringUtf8());
            }
            return (String)workingDir_;
        }
        
        public ByteString getWorkingDirBytes() {
            final Object workingDir_ = this.workingDir_;
            if (workingDir_ instanceof String) {
                return (ByteString)(this.workingDir_ = ByteString.copyFromUtf8((String)workingDir_));
            }
            return (ByteString)workingDir_;
        }
        
        public Builder setWorkingDir(final String workingDir_) {
            try {
                if (workingDir_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.workingDir_ = workingDir_;
            this.onChanged();
            return this;
        }
        
        public Builder clearWorkingDir() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.workingDir_ = ExecuteShellCommand_Req.getDefaultInstance().getWorkingDir();
            this.onChanged();
            return this;
        }
        
        public Builder setWorkingDirBytes(final ByteString workingDir_) {
            try {
                if (workingDir_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.workingDir_ = workingDir_;
            this.onChanged();
            return this;
        }
        
        public boolean hasTimeoutSecs() {
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public int getTimeoutSecs() {
            return this.timeoutSecs_;
        }
        
        public Builder setTimeoutSecs(final int timeoutSecs_) {
            this.bitField0_ |= 0x4;
            this.timeoutSecs_ = timeoutSecs_;
            this.onChanged();
            return this;
        }
        
        public Builder clearTimeoutSecs() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.timeoutSecs_ = 0;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
