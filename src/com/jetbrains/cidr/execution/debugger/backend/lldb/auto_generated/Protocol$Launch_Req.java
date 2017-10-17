// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class Launch_Req extends GeneratedMessage implements Launch_ReqOrBuilder
{
    private static final Launch_Req defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<Launch_Req> PARSER;
    private int bitField0_;
    public static final int COMMAND_LINE_FIELD_NUMBER = 2;
    private Model.CommandLine commandLine_;
    public static final int FD_PASSING_SERVICE_UNIX_SOCKET_FIELD_NUMBER = 3;
    private Object fdPassingServiceUnixSocket_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private Launch_Req(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private Launch_Req(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static Launch_Req getDefaultInstance() {
        return Launch_Req.defaultInstance;
    }
    
    public Launch_Req getDefaultInstanceForType() {
        return Launch_Req.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private Launch_Req(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        Model.CommandLine.Builder builder2 = null;
                        if ((this.bitField0_ & 0x1) == 0x1) {
                            builder2 = this.commandLine_.toBuilder();
                        }
                        try {
                            this.commandLine_ = (Model.CommandLine)codedInputStream.readMessage((Parser)Model.CommandLine.PARSER, extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.commandLine_);
                                this.commandLine_ = builder2.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x1;
                        continue;
                    }
                    case 26: {
                        this.bitField0_ |= 0x2;
                        this.fdPassingServiceUnixSocket_ = codedInputStream.readBytes();
                        continue;
                    }
                }
            }
        }
        catch (InvalidProtocolBufferException ex2) {
            throw ex2.setUnfinishedMessage((MessageLite)this);
        }
        catch (IOException ex3) {
            throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
        }
        finally {
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$3200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$3300().ensureFieldAccessorsInitialized((Class)Launch_Req.class, (Class)Builder.class);
    }
    
    public Parser<Launch_Req> getParserForType() {
        return Launch_Req.PARSER;
    }
    
    public boolean hasCommandLine() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public Model.CommandLine getCommandLine() {
        return this.commandLine_;
    }
    
    public Model.CommandLineOrBuilder getCommandLineOrBuilder() {
        return this.commandLine_;
    }
    
    public boolean hasFdPassingServiceUnixSocket() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public String getFdPassingServiceUnixSocket() {
        final Object fdPassingServiceUnixSocket_ = this.fdPassingServiceUnixSocket_;
        if (fdPassingServiceUnixSocket_ instanceof String) {
            return (String)fdPassingServiceUnixSocket_;
        }
        final ByteString byteString = (ByteString)fdPassingServiceUnixSocket_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.fdPassingServiceUnixSocket_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getFdPassingServiceUnixSocketBytes() {
        final Object fdPassingServiceUnixSocket_ = this.fdPassingServiceUnixSocket_;
        if (fdPassingServiceUnixSocket_ instanceof String) {
            return (ByteString)(this.fdPassingServiceUnixSocket_ = ByteString.copyFromUtf8((String)fdPassingServiceUnixSocket_));
        }
        return (ByteString)fdPassingServiceUnixSocket_;
    }
    
    private void a() {
        this.commandLine_ = Model.CommandLine.getDefaultInstance();
        this.fdPassingServiceUnixSocket_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasCommandLine()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.getCommandLine().isInitialized()) {
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
                codedOutputStream.writeMessage(2, (MessageLite)this.commandLine_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeBytes(3, this.getFdPassingServiceUnixSocketBytes());
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
            n += CodedOutputStream.computeMessageSize(2, (MessageLite)this.commandLine_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeBytesSize(3, this.getFdPassingServiceUnixSocketBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static Launch_Req parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (Launch_Req)Launch_Req.PARSER.parseFrom(byteString);
    }
    
    public static Launch_Req parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Launch_Req)Launch_Req.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static Launch_Req parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (Launch_Req)Launch_Req.PARSER.parseFrom(array);
    }
    
    public static Launch_Req parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Launch_Req)Launch_Req.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static Launch_Req parseFrom(final InputStream inputStream) throws IOException {
        return (Launch_Req)Launch_Req.PARSER.parseFrom(inputStream);
    }
    
    public static Launch_Req parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Launch_Req)Launch_Req.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static Launch_Req parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (Launch_Req)Launch_Req.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static Launch_Req parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Launch_Req)Launch_Req.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static Launch_Req parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (Launch_Req)Launch_Req.PARSER.parseFrom(codedInputStream);
    }
    
    public static Launch_Req parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Launch_Req)Launch_Req.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return b();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final Launch_Req launch_Req) {
        return newBuilder().mergeFrom(launch_Req);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        Launch_Req.PARSER = (Parser<Launch_Req>)new AbstractParser<Launch_Req>() {
            public Launch_Req parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Launch_Req(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new Launch_Req(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements Launch_ReqOrBuilder
    {
        private int bitField0_;
        private Model.CommandLine commandLine_;
        private SingleFieldBuilder<Model.CommandLine, Model.CommandLine.Builder, Model.CommandLineOrBuilder> commandLineBuilder_;
        private Object fdPassingServiceUnixSocket_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$3200();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$3300().ensureFieldAccessorsInitialized((Class)Launch_Req.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.commandLine_ = Model.CommandLine.getDefaultInstance();
            this.fdPassingServiceUnixSocket_ = "";
            this.c();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.commandLine_ = Model.CommandLine.getDefaultInstance();
            this.fdPassingServiceUnixSocket_ = "";
            this.c();
        }
        
        private void c() {
            try {
                if (Launch_Req.alwaysUseFieldBuilders) {
                    this.a();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
        }
        
        private static Builder b() {
            return new Builder();
        }
        
        public Builder clear() {
            Label_0034: {
                try {
                    super.clear();
                    if (this.commandLineBuilder_ == null) {
                        this.commandLine_ = Model.CommandLine.getDefaultInstance();
                        break Label_0034;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.commandLineBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFE;
            this.fdPassingServiceUnixSocket_ = "";
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return b().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$3200();
        }
        
        public Launch_Req getDefaultInstanceForType() {
            return Launch_Req.getDefaultInstance();
        }
        
        public Launch_Req build() {
            final Launch_Req buildPartial = this.buildPartial();
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
        
        public Launch_Req buildPartial() {
            final Launch_Req launch_Req = new Launch_Req((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            Label_0066: {
                try {
                    if (this.commandLineBuilder_ == null) {
                        launch_Req.commandLine_ = this.commandLine_;
                        break Label_0066;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                launch_Req.commandLine_ = (Model.CommandLine)this.commandLineBuilder_.build();
            }
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            launch_Req.fdPassingServiceUnixSocket_ = this.fdPassingServiceUnixSocket_;
            launch_Req.bitField0_ = n;
            this.onBuilt();
            return launch_Req;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof Launch_Req) {
                    return this.mergeFrom((Launch_Req)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final Launch_Req launch_Req) {
            try {
                if (launch_Req == Launch_Req.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (launch_Req.hasCommandLine()) {
                    this.mergeCommandLine(launch_Req.getCommandLine());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (launch_Req.hasFdPassingServiceUnixSocket()) {
                    this.bitField0_ |= 0x2;
                    this.fdPassingServiceUnixSocket_ = launch_Req.fdPassingServiceUnixSocket_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(launch_Req.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasCommandLine()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.getCommandLine().isInitialized()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Launch_Req launch_Req = null;
            try {
                launch_Req = (Launch_Req)Launch_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                launch_Req = (Launch_Req)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (launch_Req != null) {
                        this.mergeFrom(launch_Req);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasCommandLine() {
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
        
        public Model.CommandLine getCommandLine() {
            try {
                if (this.commandLineBuilder_ == null) {
                    return this.commandLine_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Model.CommandLine)this.commandLineBuilder_.getMessage();
        }
        
        public Builder setCommandLine(final Model.CommandLine commandLine) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.commandLineBuilder_ != null) {
                                break Label_0042;
                            }
                            final Model.CommandLine commandLine2 = commandLine;
                            if (commandLine2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Model.CommandLine commandLine2 = commandLine;
                            if (commandLine2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.commandLine_ = commandLine;
                    this.onChanged();
                    break Label_0051;
                }
                this.commandLineBuilder_.setMessage((GeneratedMessage)commandLine);
            }
            this.bitField0_ |= 0x1;
            return this;
        }
        
        public Builder setCommandLine(final Model.CommandLine.Builder builder) {
            Label_0038: {
                try {
                    if (this.commandLineBuilder_ == null) {
                        this.commandLine_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.commandLineBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x1;
            return this;
        }
        
        public Builder mergeCommandLine(final Model.CommandLine p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.commandLineBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.bitField0_:I
            //    11: iconst_1       
            //    12: iand           
            //    13: iconst_1       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.commandLine_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.commandLine_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.commandLine_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.commandLine_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.commandLineBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.bitField0_:I
            //    92: iconst_1       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.bitField0_:I
            //    97: aload_0        
            //    98: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      17     20     24     Ljava/lang/NullPointerException;
            //  7      34     37     41     Ljava/lang/NullPointerException;
            //  24     62     62     66     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearCommandLine() {
            Label_0033: {
                try {
                    if (this.commandLineBuilder_ == null) {
                        this.commandLine_ = Model.CommandLine.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.commandLineBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Model.CommandLine.Builder getCommandLineBuilder() {
            this.bitField0_ |= 0x1;
            this.onChanged();
            return (Model.CommandLine.Builder)this.a().getBuilder();
        }
        
        public Model.CommandLineOrBuilder getCommandLineOrBuilder() {
            try {
                if (this.commandLineBuilder_ != null) {
                    return (Model.CommandLineOrBuilder)this.commandLineBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.commandLine_;
        }
        
        private SingleFieldBuilder<Model.CommandLine, Model.CommandLine.Builder, Model.CommandLineOrBuilder> a() {
            try {
                if (this.commandLineBuilder_ == null) {
                    this.commandLineBuilder_ = (SingleFieldBuilder<Model.CommandLine, Model.CommandLine.Builder, Model.CommandLineOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.commandLine_, this.getParentForChildren(), this.isClean());
                    this.commandLine_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.commandLineBuilder_;
        }
        
        public boolean hasFdPassingServiceUnixSocket() {
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
        
        public String getFdPassingServiceUnixSocket() {
            final Object fdPassingServiceUnixSocket_ = this.fdPassingServiceUnixSocket_;
            if (!(fdPassingServiceUnixSocket_ instanceof String)) {
                return (String)(this.fdPassingServiceUnixSocket_ = ((ByteString)fdPassingServiceUnixSocket_).toStringUtf8());
            }
            return (String)fdPassingServiceUnixSocket_;
        }
        
        public ByteString getFdPassingServiceUnixSocketBytes() {
            final Object fdPassingServiceUnixSocket_ = this.fdPassingServiceUnixSocket_;
            if (fdPassingServiceUnixSocket_ instanceof String) {
                return (ByteString)(this.fdPassingServiceUnixSocket_ = ByteString.copyFromUtf8((String)fdPassingServiceUnixSocket_));
            }
            return (ByteString)fdPassingServiceUnixSocket_;
        }
        
        public Builder setFdPassingServiceUnixSocket(final String fdPassingServiceUnixSocket_) {
            try {
                if (fdPassingServiceUnixSocket_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.fdPassingServiceUnixSocket_ = fdPassingServiceUnixSocket_;
            this.onChanged();
            return this;
        }
        
        public Builder clearFdPassingServiceUnixSocket() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.fdPassingServiceUnixSocket_ = Launch_Req.getDefaultInstance().getFdPassingServiceUnixSocket();
            this.onChanged();
            return this;
        }
        
        public Builder setFdPassingServiceUnixSocketBytes(final ByteString fdPassingServiceUnixSocket_) {
            try {
                if (fdPassingServiceUnixSocket_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x2;
            this.fdPassingServiceUnixSocket_ = fdPassingServiceUnixSocket_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
