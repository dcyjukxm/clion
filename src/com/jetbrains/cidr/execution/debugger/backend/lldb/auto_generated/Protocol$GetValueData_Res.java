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
import com.google.protobuf.ByteString;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class GetValueData_Res extends GeneratedMessage implements GetValueData_ResOrBuilder
{
    private static final GetValueData_Res defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetValueData_Res> PARSER;
    private int bitField0_;
    public static final int COMMON_RESPONSE_FIELD_NUMBER = 1;
    private CommonResponse commonResponse_;
    public static final int VALUE_FIELD_NUMBER = 2;
    private Model.LLDBValueData value_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetValueData_Res(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetValueData_Res(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetValueData_Res getDefaultInstance() {
        return GetValueData_Res.defaultInstance;
    }
    
    public GetValueData_Res getDefaultInstanceForType() {
        return GetValueData_Res.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetValueData_Res(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    case 18: {
                        Model.LLDBValueData.Builder builder3 = null;
                        if ((this.bitField0_ & 0x2) == 0x2) {
                            builder3 = this.value_.toBuilder();
                        }
                        try {
                            this.value_ = (Model.LLDBValueData)codedInputStream.readMessage((Parser)Model.LLDBValueData.PARSER, extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom(this.value_);
                                this.value_ = builder3.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex2) {
                            throw a((IOException)ex2);
                        }
                        this.bitField0_ |= 0x2;
                        continue;
                    }
                }
            }
        }
        catch (InvalidProtocolBufferException ex3) {
            throw ex3.setUnfinishedMessage((MessageLite)this);
        }
        catch (IOException ex4) {
            throw new InvalidProtocolBufferException(ex4.getMessage()).setUnfinishedMessage((MessageLite)this);
        }
        finally {
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$39900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$40000().ensureFieldAccessorsInitialized((Class)GetValueData_Res.class, (Class)Builder.class);
    }
    
    public Parser<GetValueData_Res> getParserForType() {
        return GetValueData_Res.PARSER;
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
    
    public boolean hasValue() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public Model.LLDBValueData getValue() {
        return this.value_;
    }
    
    public Model.LLDBValueDataOrBuilder getValueOrBuilder() {
        return this.value_;
    }
    
    private void a() {
        this.commonResponse_ = CommonResponse.getDefaultInstance();
        this.value_ = Model.LLDBValueData.getDefaultInstance();
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
        if (!this.getCommonResponse().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasValue() && !this.getValue().isInitialized()) {
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
                codedOutputStream.writeMessage(2, (MessageLite)this.value_);
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
            n += CodedOutputStream.computeMessageSize(1, (MessageLite)this.commonResponse_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeMessageSize(2, (MessageLite)this.value_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetValueData_Res parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseFrom(byteString);
    }
    
    public static GetValueData_Res parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetValueData_Res parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseFrom(array);
    }
    
    public static GetValueData_Res parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetValueData_Res parseFrom(final InputStream inputStream) throws IOException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseFrom(inputStream);
    }
    
    public static GetValueData_Res parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueData_Res parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetValueData_Res parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueData_Res parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetValueData_Res parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueData_Res)GetValueData_Res.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return d();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetValueData_Res getValueData_Res) {
        return newBuilder().mergeFrom(getValueData_Res);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetValueData_Res.PARSER = (Parser<GetValueData_Res>)new AbstractParser<GetValueData_Res>() {
            public GetValueData_Res parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetValueData_Res(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetValueData_Res(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueData_ResOrBuilder
    {
        private int bitField0_;
        private CommonResponse commonResponse_;
        private SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder> commonResponseBuilder_;
        private Model.LLDBValueData value_;
        private SingleFieldBuilder<Model.LLDBValueData, Model.LLDBValueData.Builder, Model.LLDBValueDataOrBuilder> valueBuilder_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$39900();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$40000().ensureFieldAccessorsInitialized((Class)GetValueData_Res.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.commonResponse_ = CommonResponse.getDefaultInstance();
            this.value_ = Model.LLDBValueData.getDefaultInstance();
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.commonResponse_ = CommonResponse.getDefaultInstance();
            this.value_ = Model.LLDBValueData.getDefaultInstance();
            this.a();
        }
        
        private void a() {
            try {
                if (GetValueData_Res.alwaysUseFieldBuilders) {
                    this.b();
                    this.c();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
        }
        
        private static Builder d() {
            return new Builder();
        }
        
        public Builder clear() {
            Label_0074: {
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
                    try {
                        this.bitField0_ &= 0xFFFFFFFE;
                        if (this.valueBuilder_ == null) {
                            this.value_ = Model.LLDBValueData.getDefaultInstance();
                            break Label_0074;
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                this.valueBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Builder clone() {
            return d().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$39900();
        }
        
        public GetValueData_Res getDefaultInstanceForType() {
            return GetValueData_Res.getDefaultInstance();
        }
        
        public GetValueData_Res build() {
            final GetValueData_Res buildPartial = this.buildPartial();
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
        
        public GetValueData_Res buildPartial() {
            final GetValueData_Res getValueData_Res = new GetValueData_Res((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            Label_0066: {
                try {
                    if (this.commonResponseBuilder_ == null) {
                        getValueData_Res.commonResponse_ = this.commonResponse_;
                        break Label_0066;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                getValueData_Res.commonResponse_ = (CommonResponse)this.commonResponseBuilder_.build();
            }
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            Label_0115: {
                try {
                    if (this.valueBuilder_ == null) {
                        getValueData_Res.value_ = this.value_;
                        break Label_0115;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                getValueData_Res.value_ = (Model.LLDBValueData)this.valueBuilder_.build();
            }
            getValueData_Res.bitField0_ = n;
            this.onBuilt();
            return getValueData_Res;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof GetValueData_Res) {
                    return this.mergeFrom((GetValueData_Res)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetValueData_Res getValueData_Res) {
            try {
                if (getValueData_Res == GetValueData_Res.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (getValueData_Res.hasCommonResponse()) {
                    this.mergeCommonResponse(getValueData_Res.getCommonResponse());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (getValueData_Res.hasValue()) {
                    this.mergeValue(getValueData_Res.getValue());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            this.mergeUnknownFields(getValueData_Res.getUnknownFields());
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
                if (!this.getCommonResponse().isInitialized()) {
                    return false;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.hasValue()) {
                    return true;
                }
                final Builder builder = this;
                final Model.LLDBValueData lldbValueData = builder.getValue();
                final boolean b = lldbValueData.isInitialized();
                if (!b) {
                    return false;
                }
                return true;
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                final Builder builder = this;
                final Model.LLDBValueData lldbValueData = builder.getValue();
                final boolean b = lldbValueData.isInitialized();
                if (!b) {
                    return false;
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetValueData_Res getValueData_Res = null;
            try {
                getValueData_Res = (GetValueData_Res)GetValueData_Res.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getValueData_Res = (GetValueData_Res)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getValueData_Res != null) {
                        this.mergeFrom(getValueData_Res);
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
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.bitField0_:I
            //    11: iconst_1       
            //    12: iand           
            //    13: iconst_1       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.bitField0_:I
            //    92: iconst_1       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.bitField0_:I
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
            return (CommonResponse.Builder)this.b().getBuilder();
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
        
        private SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder> b() {
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
        
        public boolean hasValue() {
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
        
        public Model.LLDBValueData getValue() {
            try {
                if (this.valueBuilder_ == null) {
                    return this.value_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Model.LLDBValueData)this.valueBuilder_.getMessage();
        }
        
        public Builder setValue(final Model.LLDBValueData lldbValueData) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.valueBuilder_ != null) {
                                break Label_0042;
                            }
                            final Model.LLDBValueData lldbValueData2 = lldbValueData;
                            if (lldbValueData2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Model.LLDBValueData lldbValueData2 = lldbValueData;
                            if (lldbValueData2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.value_ = lldbValueData;
                    this.onChanged();
                    break Label_0051;
                }
                this.valueBuilder_.setMessage((GeneratedMessage)lldbValueData);
            }
            this.bitField0_ |= 0x2;
            return this;
        }
        
        public Builder setValue(final Model.LLDBValueData.Builder builder) {
            Label_0038: {
                try {
                    if (this.valueBuilder_ == null) {
                        this.value_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.valueBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x2;
            return this;
        }
        
        public Builder mergeValue(final Model.LLDBValueData p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.valueBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.bitField0_:I
            //    11: iconst_2       
            //    12: iand           
            //    13: iconst_2       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.value_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.value_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.value_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.value_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValueData;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.valueBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.bitField0_:I
            //    92: iconst_2       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.bitField0_:I
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
        
        public Builder clearValue() {
            Label_0033: {
                try {
                    if (this.valueBuilder_ == null) {
                        this.value_ = Model.LLDBValueData.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.valueBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Model.LLDBValueData.Builder getValueBuilder() {
            this.bitField0_ |= 0x2;
            this.onChanged();
            return (Model.LLDBValueData.Builder)this.c().getBuilder();
        }
        
        public Model.LLDBValueDataOrBuilder getValueOrBuilder() {
            try {
                if (this.valueBuilder_ != null) {
                    return (Model.LLDBValueDataOrBuilder)this.valueBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.value_;
        }
        
        private SingleFieldBuilder<Model.LLDBValueData, Model.LLDBValueData.Builder, Model.LLDBValueDataOrBuilder> c() {
            try {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = (SingleFieldBuilder<Model.LLDBValueData, Model.LLDBValueData.Builder, Model.LLDBValueDataOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.value_, this.getParentForChildren(), this.isClean());
                    this.value_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.valueBuilder_;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
