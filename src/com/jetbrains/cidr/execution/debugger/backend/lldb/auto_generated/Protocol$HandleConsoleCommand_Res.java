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

public static final class HandleConsoleCommand_Res extends GeneratedMessage implements HandleConsoleCommand_ResOrBuilder
{
    private static final HandleConsoleCommand_Res defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<HandleConsoleCommand_Res> PARSER;
    private int bitField0_;
    public static final int COMMON_RESPONSE_FIELD_NUMBER = 1;
    private CommonResponse commonResponse_;
    public static final int SUCCESS_FIELD_NUMBER = 2;
    private int success_;
    public static final int ERR_FIELD_NUMBER = 3;
    private Object err_;
    public static final int OUT_FIELD_NUMBER = 4;
    private Object out_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private HandleConsoleCommand_Res(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private HandleConsoleCommand_Res(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static HandleConsoleCommand_Res getDefaultInstance() {
        return HandleConsoleCommand_Res.defaultInstance;
    }
    
    public HandleConsoleCommand_Res getDefaultInstanceForType() {
        return HandleConsoleCommand_Res.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private HandleConsoleCommand_Res(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        CommonResponse.Builder builder2 = null;
                        if ((this.bitField0_ & 0x1) == 0x1) {
                            builder2 = this.commonResponse_.toBuilder();
                        }
                        try {
                            this.commonResponse_ = (CommonResponse)codedInputStream.readMessage((Parser)CommonResponse.PARSER, extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.commonResponse_);
                                this.commonResponse_ = builder2.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x1;
                        continue;
                    }
                    case 16: {
                        this.bitField0_ |= 0x2;
                        this.success_ = codedInputStream.readInt32();
                        continue;
                    }
                    case 26: {
                        this.bitField0_ |= 0x4;
                        this.err_ = codedInputStream.readBytes();
                        continue;
                    }
                    case 34: {
                        this.bitField0_ |= 0x8;
                        this.out_ = codedInputStream.readBytes();
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
        return Protocol.access$51300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$51400().ensureFieldAccessorsInitialized((Class)HandleConsoleCommand_Res.class, (Class)Builder.class);
    }
    
    public Parser<HandleConsoleCommand_Res> getParserForType() {
        return HandleConsoleCommand_Res.PARSER;
    }
    
    public boolean hasCommonResponse() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public CommonResponse getCommonResponse() {
        return this.commonResponse_;
    }
    
    public CommonResponseOrBuilder getCommonResponseOrBuilder() {
        return this.commonResponse_;
    }
    
    public boolean hasSuccess() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public int getSuccess() {
        return this.success_;
    }
    
    public boolean hasErr() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public String getErr() {
        final Object err_ = this.err_;
        if (err_ instanceof String) {
            return (String)err_;
        }
        final ByteString byteString = (ByteString)err_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.err_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getErrBytes() {
        final Object err_ = this.err_;
        if (err_ instanceof String) {
            return (ByteString)(this.err_ = ByteString.copyFromUtf8((String)err_));
        }
        return (ByteString)err_;
    }
    
    public boolean hasOut() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public String getOut() {
        final Object out_ = this.out_;
        if (out_ instanceof String) {
            return (String)out_;
        }
        final ByteString byteString = (ByteString)out_;
        final String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.out_ = stringUtf8;
        }
        return stringUtf8;
    }
    
    public ByteString getOutBytes() {
        final Object out_ = this.out_;
        if (out_ instanceof String) {
            return (ByteString)(this.out_ = ByteString.copyFromUtf8((String)out_));
        }
        return (ByteString)out_;
    }
    
    private void a() {
        this.commonResponse_ = CommonResponse.getDefaultInstance();
        this.success_ = 0;
        this.err_ = "";
        this.out_ = "";
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (!this.hasCommonResponse()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.hasSuccess()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (!this.getCommonResponse().isInitialized()) {
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
                codedOutputStream.writeMessage(1, (MessageLite)this.commonResponse_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeInt32(2, this.success_);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeBytes(3, this.getErrBytes());
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeBytes(4, this.getOutBytes());
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
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
            n += CodedOutputStream.computeMessageSize(1, (MessageLite)this.commonResponse_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeInt32Size(2, this.success_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeBytesSize(3, this.getErrBytes());
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeBytesSize(4, this.getOutBytes());
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static HandleConsoleCommand_Res parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseFrom(byteString);
    }
    
    public static HandleConsoleCommand_Res parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static HandleConsoleCommand_Res parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseFrom(array);
    }
    
    public static HandleConsoleCommand_Res parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static HandleConsoleCommand_Res parseFrom(final InputStream inputStream) throws IOException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseFrom(inputStream);
    }
    
    public static HandleConsoleCommand_Res parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static HandleConsoleCommand_Res parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static HandleConsoleCommand_Res parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static HandleConsoleCommand_Res parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseFrom(codedInputStream);
    }
    
    public static HandleConsoleCommand_Res parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return a();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final HandleConsoleCommand_Res handleConsoleCommand_Res) {
        return newBuilder().mergeFrom(handleConsoleCommand_Res);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        HandleConsoleCommand_Res.PARSER = (Parser<HandleConsoleCommand_Res>)new AbstractParser<HandleConsoleCommand_Res>() {
            public HandleConsoleCommand_Res parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HandleConsoleCommand_Res(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new HandleConsoleCommand_Res(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements HandleConsoleCommand_ResOrBuilder
    {
        private int bitField0_;
        private CommonResponse commonResponse_;
        private SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder> commonResponseBuilder_;
        private int success_;
        private Object err_;
        private Object out_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$51300();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$51400().ensureFieldAccessorsInitialized((Class)HandleConsoleCommand_Res.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.commonResponse_ = CommonResponse.getDefaultInstance();
            this.err_ = "";
            this.out_ = "";
            this.b();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.commonResponse_ = CommonResponse.getDefaultInstance();
            this.err_ = "";
            this.out_ = "";
            this.b();
        }
        
        private void b() {
            try {
                if (HandleConsoleCommand_Res.alwaysUseFieldBuilders) {
                    this.c();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
        }
        
        private static Builder a() {
            return new Builder();
        }
        
        public Builder clear() {
            Label_0034: {
                try {
                    super.clear();
                    if (this.commonResponseBuilder_ == null) {
                        this.commonResponse_ = CommonResponse.getDefaultInstance();
                        break Label_0034;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.commonResponseBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFE;
            this.success_ = 0;
            this.bitField0_ &= 0xFFFFFFFD;
            this.err_ = "";
            this.bitField0_ &= 0xFFFFFFFB;
            this.out_ = "";
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Builder clone() {
            return a().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$51300();
        }
        
        public HandleConsoleCommand_Res getDefaultInstanceForType() {
            return HandleConsoleCommand_Res.getDefaultInstance();
        }
        
        public HandleConsoleCommand_Res build() {
            final HandleConsoleCommand_Res buildPartial = this.buildPartial();
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
        
        public HandleConsoleCommand_Res buildPartial() {
            final HandleConsoleCommand_Res handleConsoleCommand_Res = new HandleConsoleCommand_Res((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            Label_0066: {
                try {
                    if (this.commonResponseBuilder_ == null) {
                        handleConsoleCommand_Res.commonResponse_ = this.commonResponse_;
                        break Label_0066;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                handleConsoleCommand_Res.commonResponse_ = (CommonResponse)this.commonResponseBuilder_.build();
            }
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            handleConsoleCommand_Res.success_ = this.success_;
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            handleConsoleCommand_Res.err_ = this.err_;
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            handleConsoleCommand_Res.out_ = this.out_;
            handleConsoleCommand_Res.bitField0_ = n;
            this.onBuilt();
            return handleConsoleCommand_Res;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof HandleConsoleCommand_Res) {
                    return this.mergeFrom((HandleConsoleCommand_Res)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final HandleConsoleCommand_Res handleConsoleCommand_Res) {
            try {
                if (handleConsoleCommand_Res == HandleConsoleCommand_Res.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (handleConsoleCommand_Res.hasCommonResponse()) {
                    this.mergeCommonResponse(handleConsoleCommand_Res.getCommonResponse());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (handleConsoleCommand_Res.hasSuccess()) {
                    this.setSuccess(handleConsoleCommand_Res.getSuccess());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (handleConsoleCommand_Res.hasErr()) {
                    this.bitField0_ |= 0x4;
                    this.err_ = handleConsoleCommand_Res.err_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (handleConsoleCommand_Res.hasOut()) {
                    this.bitField0_ |= 0x8;
                    this.out_ = handleConsoleCommand_Res.out_;
                    this.onChanged();
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            this.mergeUnknownFields(handleConsoleCommand_Res.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            try {
                if (!this.hasCommonResponse()) {
                    return false;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (!this.hasSuccess()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.getCommonResponse().isInitialized()) {
                    return false;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            HandleConsoleCommand_Res handleConsoleCommand_Res = null;
            try {
                handleConsoleCommand_Res = (HandleConsoleCommand_Res)HandleConsoleCommand_Res.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                handleConsoleCommand_Res = (HandleConsoleCommand_Res)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (handleConsoleCommand_Res != null) {
                        this.mergeFrom(handleConsoleCommand_Res);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasCommonResponse() {
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
        
        public CommonResponse getCommonResponse() {
            try {
                if (this.commonResponseBuilder_ == null) {
                    return this.commonResponse_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (CommonResponse)this.commonResponseBuilder_.getMessage();
        }
        
        public Builder setCommonResponse(final CommonResponse commonResponse) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.commonResponseBuilder_ != null) {
                                break Label_0042;
                            }
                            final CommonResponse commonResponse2 = commonResponse;
                            if (commonResponse2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final CommonResponse commonResponse2 = commonResponse;
                            if (commonResponse2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.commonResponse_ = commonResponse;
                    this.onChanged();
                    break Label_0051;
                }
                this.commonResponseBuilder_.setMessage((GeneratedMessage)commonResponse);
            }
            this.bitField0_ |= 0x1;
            return this;
        }
        
        public Builder setCommonResponse(final CommonResponse.Builder builder) {
            Label_0038: {
                try {
                    if (this.commonResponseBuilder_ == null) {
                        this.commonResponse_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.commonResponseBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x1;
            return this;
        }
        
        public Builder mergeCommonResponse(final CommonResponse p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.bitField0_:I
            //    11: iconst_1       
            //    12: iand           
            //    13: iconst_1       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.bitField0_:I
            //    92: iconst_1       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.bitField0_:I
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
        
        public Builder clearCommonResponse() {
            Label_0033: {
                try {
                    if (this.commonResponseBuilder_ == null) {
                        this.commonResponse_ = CommonResponse.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.commonResponseBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public CommonResponse.Builder getCommonResponseBuilder() {
            this.bitField0_ |= 0x1;
            this.onChanged();
            return (CommonResponse.Builder)this.c().getBuilder();
        }
        
        public CommonResponseOrBuilder getCommonResponseOrBuilder() {
            try {
                if (this.commonResponseBuilder_ != null) {
                    return (CommonResponseOrBuilder)this.commonResponseBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.commonResponse_;
        }
        
        private SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder> c() {
            try {
                if (this.commonResponseBuilder_ == null) {
                    this.commonResponseBuilder_ = (SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.commonResponse_, this.getParentForChildren(), this.isClean());
                    this.commonResponse_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.commonResponseBuilder_;
        }
        
        public boolean hasSuccess() {
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
        
        public int getSuccess() {
            return this.success_;
        }
        
        public Builder setSuccess(final int success_) {
            this.bitField0_ |= 0x2;
            this.success_ = success_;
            this.onChanged();
            return this;
        }
        
        public Builder clearSuccess() {
            this.bitField0_ &= 0xFFFFFFFD;
            this.success_ = 0;
            this.onChanged();
            return this;
        }
        
        public boolean hasErr() {
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
        
        public String getErr() {
            final Object err_ = this.err_;
            if (!(err_ instanceof String)) {
                return (String)(this.err_ = ((ByteString)err_).toStringUtf8());
            }
            return (String)err_;
        }
        
        public ByteString getErrBytes() {
            final Object err_ = this.err_;
            if (err_ instanceof String) {
                return (ByteString)(this.err_ = ByteString.copyFromUtf8((String)err_));
            }
            return (ByteString)err_;
        }
        
        public Builder setErr(final String err_) {
            try {
                if (err_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x4;
            this.err_ = err_;
            this.onChanged();
            return this;
        }
        
        public Builder clearErr() {
            this.bitField0_ &= 0xFFFFFFFB;
            this.err_ = HandleConsoleCommand_Res.getDefaultInstance().getErr();
            this.onChanged();
            return this;
        }
        
        public Builder setErrBytes(final ByteString err_) {
            try {
                if (err_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x4;
            this.err_ = err_;
            this.onChanged();
            return this;
        }
        
        public boolean hasOut() {
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public String getOut() {
            final Object out_ = this.out_;
            if (!(out_ instanceof String)) {
                return (String)(this.out_ = ((ByteString)out_).toStringUtf8());
            }
            return (String)out_;
        }
        
        public ByteString getOutBytes() {
            final Object out_ = this.out_;
            if (out_ instanceof String) {
                return (ByteString)(this.out_ = ByteString.copyFromUtf8((String)out_));
            }
            return (ByteString)out_;
        }
        
        public Builder setOut(final String out_) {
            try {
                if (out_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x8;
            this.out_ = out_;
            this.onChanged();
            return this;
        }
        
        public Builder clearOut() {
            this.bitField0_ &= 0xFFFFFFF7;
            this.out_ = HandleConsoleCommand_Res.getDefaultInstance().getOut();
            this.onChanged();
            return this;
        }
        
        public Builder setOutBytes(final ByteString out_) {
            try {
                if (out_ == null) {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.bitField0_ |= 0x8;
            this.out_ = out_;
            this.onChanged();
            return this;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
