// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import java.util.Collection;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import com.google.protobuf.ByteString;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import java.util.Collections;
import com.google.protobuf.MessageLite;
import java.util.ArrayList;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import java.util.List;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class GetValueChildren_Res extends GeneratedMessage implements GetValueChildren_ResOrBuilder
{
    private static final GetValueChildren_Res defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<GetValueChildren_Res> PARSER;
    private int bitField0_;
    public static final int COMMON_RESPONSE_FIELD_NUMBER = 1;
    private CommonResponse commonResponse_;
    public static final int VALUE_FIELD_NUMBER = 2;
    private List<Model.LLDBValue> value_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private GetValueChildren_Res(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private GetValueChildren_Res(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static GetValueChildren_Res getDefaultInstance() {
        return GetValueChildren_Res.defaultInstance;
    }
    
    public GetValueChildren_Res getDefaultInstanceForType() {
        return GetValueChildren_Res.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private GetValueChildren_Res(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.a();
        int n = 0;
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
                        if ((n & 0x2) != 0x2) {
                            this.value_ = new ArrayList<Model.LLDBValue>();
                            n |= 0x2;
                        }
                        this.value_.add((Model.LLDBValue)codedInputStream.readMessage((Parser)Model.LLDBValue.PARSER, extensionRegistryLite));
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
            try {
                if ((n & 0x2) == 0x2) {
                    this.value_ = Collections.unmodifiableList((List<? extends Model.LLDBValue>)this.value_);
                }
            }
            catch (InvalidProtocolBufferException ex4) {
                throw a((IOException)ex4);
            }
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$44900();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$45000().ensureFieldAccessorsInitialized((Class)GetValueChildren_Res.class, (Class)Builder.class);
    }
    
    public Parser<GetValueChildren_Res> getParserForType() {
        return GetValueChildren_Res.PARSER;
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
    
    public List<Model.LLDBValue> getValueList() {
        return this.value_;
    }
    
    public List<? extends Model.LLDBValueOrBuilder> getValueOrBuilderList() {
        return this.value_;
    }
    
    public int getValueCount() {
        return this.value_.size();
    }
    
    public Model.LLDBValue getValue(final int n) {
        return this.value_.get(n);
    }
    
    public Model.LLDBValueOrBuilder getValueOrBuilder(final int n) {
        return this.value_.get(n);
    }
    
    private void a() {
        this.commonResponse_ = CommonResponse.getDefaultInstance();
        this.value_ = Collections.emptyList();
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
        for (int i = 0; i < this.getValueCount(); ++i) {
            if (!this.getValue(i).isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
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
        int i = 0;
        try {
            while (i < this.value_.size()) {
                codedOutputStream.writeMessage(2, (MessageLite)this.value_.get(i));
                ++i;
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
        for (int i = 0; i < this.value_.size(); ++i) {
            n += CodedOutputStream.computeMessageSize(2, (MessageLite)this.value_.get(i));
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static GetValueChildren_Res parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseFrom(byteString);
    }
    
    public static GetValueChildren_Res parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static GetValueChildren_Res parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseFrom(array);
    }
    
    public static GetValueChildren_Res parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static GetValueChildren_Res parseFrom(final InputStream inputStream) throws IOException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseFrom(inputStream);
    }
    
    public static GetValueChildren_Res parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueChildren_Res parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static GetValueChildren_Res parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static GetValueChildren_Res parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseFrom(codedInputStream);
    }
    
    public static GetValueChildren_Res parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetValueChildren_Res)GetValueChildren_Res.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return d();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final GetValueChildren_Res getValueChildren_Res) {
        return newBuilder().mergeFrom(getValueChildren_Res);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        GetValueChildren_Res.PARSER = (Parser<GetValueChildren_Res>)new AbstractParser<GetValueChildren_Res>() {
            public GetValueChildren_Res parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GetValueChildren_Res(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new GetValueChildren_Res(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetValueChildren_ResOrBuilder
    {
        private int bitField0_;
        private CommonResponse commonResponse_;
        private SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder> commonResponseBuilder_;
        private List<Model.LLDBValue> value_;
        private RepeatedFieldBuilder<Model.LLDBValue, Model.LLDBValue.Builder, Model.LLDBValueOrBuilder> valueBuilder_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$44900();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$45000().ensureFieldAccessorsInitialized((Class)GetValueChildren_Res.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.commonResponse_ = CommonResponse.getDefaultInstance();
            this.value_ = Collections.emptyList();
            this.a();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.commonResponse_ = CommonResponse.getDefaultInstance();
            this.value_ = Collections.emptyList();
            this.a();
        }
        
        private void a() {
            try {
                if (GetValueChildren_Res.alwaysUseFieldBuilders) {
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
                        this.value_ = Collections.emptyList();
                        this.bitField0_ &= 0xFFFFFFFD;
                        return this;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.valueBuilder_.clear();
            return this;
        }
        
        public Builder clone() {
            return d().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$44900();
        }
        
        public GetValueChildren_Res getDefaultInstanceForType() {
            return GetValueChildren_Res.getDefaultInstance();
        }
        
        public GetValueChildren_Res build() {
            final GetValueChildren_Res buildPartial = this.buildPartial();
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
        
        public GetValueChildren_Res buildPartial() {
            final GetValueChildren_Res getValueChildren_Res = new GetValueChildren_Res((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            int n = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            Label_0143: {
                Label_0131: {
                    Label_0090: {
                        Label_0066: {
                            try {
                                if (this.commonResponseBuilder_ == null) {
                                    getValueChildren_Res.commonResponse_ = this.commonResponse_;
                                    break Label_0066;
                                }
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            getValueChildren_Res.commonResponse_ = (CommonResponse)this.commonResponseBuilder_.build();
                            try {
                                if (this.valueBuilder_ != null) {
                                    break Label_0131;
                                }
                                final Builder builder = this;
                                final int n2 = builder.bitField0_;
                                final int n3 = 2;
                                final int n4 = n2 & n3;
                                final int n5 = 2;
                                if (n4 == n5) {
                                    break Label_0090;
                                }
                                break Label_0090;
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        try {
                            final Builder builder = this;
                            final int n2 = builder.bitField0_;
                            final int n3 = 2;
                            final int n4 = n2 & n3;
                            final int n5 = 2;
                            if (n4 == n5) {
                                this.value_ = Collections.unmodifiableList((List<? extends Model.LLDBValue>)this.value_);
                                this.bitField0_ &= 0xFFFFFFFD;
                            }
                        }
                        catch (NullPointerException ex3) {
                            throw b(ex3);
                        }
                    }
                    getValueChildren_Res.value_ = this.value_;
                    break Label_0143;
                }
                getValueChildren_Res.value_ = (List<Model.LLDBValue>)this.valueBuilder_.build();
            }
            getValueChildren_Res.bitField0_ = n;
            this.onBuilt();
            return getValueChildren_Res;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof GetValueChildren_Res) {
                    return this.mergeFrom((GetValueChildren_Res)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final GetValueChildren_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;
            //     4: if_acmpne       13
            //     7: aload_0        
            //     8: areturn        
            //     9: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    12: athrow         
            //    13: aload_1        
            //    14: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.hasCommonResponse:()Z
            //    17: ifeq            36
            //    20: aload_0        
            //    21: aload_1        
            //    22: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.getCommonResponse:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    25: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.mergeCommonResponse:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder;
            //    28: pop            
            //    29: goto            36
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    35: athrow         
            //    36: aload_0        
            //    37: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.valueBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
            //    40: ifnonnull       132
            //    43: aload_1        
            //    44: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.access$45700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;)Ljava/util/List;
            //    47: invokeinterface java/util/List.isEmpty:()Z
            //    52: ifne            236
            //    55: goto            62
            //    58: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    61: athrow         
            //    62: aload_0        
            //    63: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.value_:Ljava/util/List;
            //    66: invokeinterface java/util/List.isEmpty:()Z
            //    71: ifeq            107
            //    74: goto            81
            //    77: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    80: athrow         
            //    81: aload_0        
            //    82: aload_1        
            //    83: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.access$45700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;)Ljava/util/List;
            //    86: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.value_:Ljava/util/List;
            //    89: aload_0        
            //    90: aload_0        
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.bitField0_:I
            //    94: bipush          -3
            //    96: iand           
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.bitField0_:I
            //   100: goto            125
            //   103: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   106: athrow         
            //   107: aload_0        
            //   108: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.e:()V
            //   111: aload_0        
            //   112: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.value_:Ljava/util/List;
            //   115: aload_1        
            //   116: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.access$45700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;)Ljava/util/List;
            //   119: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
            //   124: pop            
            //   125: aload_0        
            //   126: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.onChanged:()V
            //   129: goto            236
            //   132: aload_1        
            //   133: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.access$45700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;)Ljava/util/List;
            //   136: invokeinterface java/util/List.isEmpty:()Z
            //   141: ifne            236
            //   144: aload_0        
            //   145: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.valueBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
            //   148: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.isEmpty:()Z
            //   151: ifeq            224
            //   154: goto            161
            //   157: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   160: athrow         
            //   161: aload_0        
            //   162: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.valueBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
            //   165: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.dispose:()V
            //   168: aload_0        
            //   169: aconst_null    
            //   170: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.valueBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
            //   173: aload_0        
            //   174: aload_1        
            //   175: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.access$45700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;)Ljava/util/List;
            //   178: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.value_:Ljava/util/List;
            //   181: aload_0        
            //   182: aload_0        
            //   183: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.bitField0_:I
            //   186: bipush          -3
            //   188: iand           
            //   189: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.bitField0_:I
            //   192: aload_0        
            //   193: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.access$45900:()Z
            //   196: ifeq            217
            //   199: goto            206
            //   202: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   205: athrow         
            //   206: aload_0        
            //   207: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.c:()Lcom/google/protobuf/RepeatedFieldBuilder;
            //   210: goto            218
            //   213: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   216: athrow         
            //   217: aconst_null    
            //   218: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.valueBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
            //   221: goto            236
            //   224: aload_0        
            //   225: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.valueBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
            //   228: aload_1        
            //   229: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.access$45700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;)Ljava/util/List;
            //   232: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.addAllMessages:(Ljava/lang/Iterable;)Lcom/google/protobuf/RepeatedFieldBuilder;
            //   235: pop            
            //   236: aload_0        
            //   237: aload_1        
            //   238: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.getUnknownFields:()Lcom/google/protobuf/UnknownFieldSet;
            //   241: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.mergeUnknownFields:(Lcom/google/protobuf/UnknownFieldSet;)Lcom/google/protobuf/GeneratedMessage$Builder;
            //   244: pop            
            //   245: aload_0        
            //   246: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      9      9      13     Ljava/lang/NullPointerException;
            //  13     29     32     36     Ljava/lang/NullPointerException;
            //  36     55     58     62     Ljava/lang/NullPointerException;
            //  43     74     77     81     Ljava/lang/NullPointerException;
            //  62     103    103    107    Ljava/lang/NullPointerException;
            //  132    154    157    161    Ljava/lang/NullPointerException;
            //  144    199    202    206    Ljava/lang/NullPointerException;
            //  161    213    213    217    Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
            int n = 0;
            while (true) {
                Label_0063: {
                    try {
                        if (n >= this.getValueCount()) {
                            break;
                        }
                        final Builder builder = this;
                        final int n2 = n;
                        final Model.LLDBValue lldbValue = builder.getValue(n2);
                        final boolean b = lldbValue.isInitialized();
                        if (!b) {
                            return false;
                        }
                        break Label_0063;
                    }
                    catch (NullPointerException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final Builder builder = this;
                        final int n2 = n;
                        final Model.LLDBValue lldbValue = builder.getValue(n2);
                        final boolean b = lldbValue.isInitialized();
                        if (!b) {
                            return false;
                        }
                    }
                    catch (NullPointerException ex4) {
                        throw b(ex4);
                    }
                }
                ++n;
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            GetValueChildren_Res getValueChildren_Res = null;
            try {
                getValueChildren_Res = (GetValueChildren_Res)GetValueChildren_Res.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                getValueChildren_Res = (GetValueChildren_Res)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (getValueChildren_Res != null) {
                        this.mergeFrom(getValueChildren_Res);
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
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.bitField0_:I
            //    11: iconst_1       
            //    12: iand           
            //    13: iconst_1       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.bitField0_:I
            //    92: iconst_1       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.bitField0_:I
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
        
        private void e() {
            try {
                if ((this.bitField0_ & 0x2) != 0x2) {
                    this.value_ = new ArrayList<Model.LLDBValue>(this.value_);
                    this.bitField0_ |= 0x2;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
        }
        
        public List<Model.LLDBValue> getValueList() {
            try {
                if (this.valueBuilder_ == null) {
                    return Collections.unmodifiableList((List<? extends Model.LLDBValue>)this.value_);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (List<Model.LLDBValue>)this.valueBuilder_.getMessageList();
        }
        
        public int getValueCount() {
            try {
                if (this.valueBuilder_ == null) {
                    return this.value_.size();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.valueBuilder_.getCount();
        }
        
        public Model.LLDBValue getValue(final int n) {
            try {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(n);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Model.LLDBValue)this.valueBuilder_.getMessage(n);
        }
        
        public Builder setValue(final int n, final Model.LLDBValue lldbValue) {
            Label_0053: {
                Label_0018: {
                    try {
                        if (this.valueBuilder_ != null) {
                            break Label_0053;
                        }
                        final Model.LLDBValue lldbValue2 = lldbValue;
                        if (lldbValue2 == null) {
                            break Label_0018;
                        }
                        break Label_0018;
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    try {
                        final Model.LLDBValue lldbValue2 = lldbValue;
                        if (lldbValue2 == null) {
                            throw new NullPointerException();
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                this.e();
                this.value_.set(n, lldbValue);
                this.onChanged();
                return this;
            }
            this.valueBuilder_.setMessage(n, (GeneratedMessage)lldbValue);
            return this;
        }
        
        public Builder setValue(final int n, final Model.LLDBValue.Builder builder) {
            try {
                if (this.valueBuilder_ == null) {
                    this.e();
                    this.value_.set(n, builder.build());
                    this.onChanged();
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.valueBuilder_.setMessage(n, (GeneratedMessage)builder.build());
            return this;
        }
        
        public Builder addValue(final Model.LLDBValue lldbValue) {
            Label_0052: {
                Label_0018: {
                    try {
                        if (this.valueBuilder_ != null) {
                            break Label_0052;
                        }
                        final Model.LLDBValue lldbValue2 = lldbValue;
                        if (lldbValue2 == null) {
                            break Label_0018;
                        }
                        break Label_0018;
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    try {
                        final Model.LLDBValue lldbValue2 = lldbValue;
                        if (lldbValue2 == null) {
                            throw new NullPointerException();
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                this.e();
                this.value_.add(lldbValue);
                this.onChanged();
                return this;
            }
            this.valueBuilder_.addMessage((GeneratedMessage)lldbValue);
            return this;
        }
        
        public Builder addValue(final int n, final Model.LLDBValue lldbValue) {
            Label_0052: {
                Label_0018: {
                    try {
                        if (this.valueBuilder_ != null) {
                            break Label_0052;
                        }
                        final Model.LLDBValue lldbValue2 = lldbValue;
                        if (lldbValue2 == null) {
                            break Label_0018;
                        }
                        break Label_0018;
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    try {
                        final Model.LLDBValue lldbValue2 = lldbValue;
                        if (lldbValue2 == null) {
                            throw new NullPointerException();
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                this.e();
                this.value_.add(n, lldbValue);
                this.onChanged();
                return this;
            }
            this.valueBuilder_.addMessage(n, (GeneratedMessage)lldbValue);
            return this;
        }
        
        public Builder addValue(final Model.LLDBValue.Builder builder) {
            try {
                if (this.valueBuilder_ == null) {
                    this.e();
                    this.value_.add(builder.build());
                    this.onChanged();
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.valueBuilder_.addMessage((GeneratedMessage)builder.build());
            return this;
        }
        
        public Builder addValue(final int n, final Model.LLDBValue.Builder builder) {
            try {
                if (this.valueBuilder_ == null) {
                    this.e();
                    this.value_.add(n, builder.build());
                    this.onChanged();
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.valueBuilder_.addMessage(n, (GeneratedMessage)builder.build());
            return this;
        }
        
        public Builder addAllValue(final Iterable<? extends Model.LLDBValue> iterable) {
            try {
                if (this.valueBuilder_ == null) {
                    this.e();
                    GeneratedMessage.Builder.addAll((Iterable)iterable, (Collection)this.value_);
                    this.onChanged();
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.valueBuilder_.addAllMessages((Iterable)iterable);
            return this;
        }
        
        public Builder clearValue() {
            try {
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.valueBuilder_.clear();
            return this;
        }
        
        public Builder removeValue(final int n) {
            try {
                if (this.valueBuilder_ == null) {
                    this.e();
                    this.value_.remove(n);
                    this.onChanged();
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.valueBuilder_.remove(n);
            return this;
        }
        
        public Model.LLDBValue.Builder getValueBuilder(final int n) {
            return (Model.LLDBValue.Builder)this.c().getBuilder(n);
        }
        
        public Model.LLDBValueOrBuilder getValueOrBuilder(final int n) {
            try {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(n);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Model.LLDBValueOrBuilder)this.valueBuilder_.getMessageOrBuilder(n);
        }
        
        public List<? extends Model.LLDBValueOrBuilder> getValueOrBuilderList() {
            try {
                if (this.valueBuilder_ != null) {
                    return (List<? extends Model.LLDBValueOrBuilder>)this.valueBuilder_.getMessageOrBuilderList();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return Collections.unmodifiableList((List<? extends Model.LLDBValueOrBuilder>)this.value_);
        }
        
        public Model.LLDBValue.Builder addValueBuilder() {
            return (Model.LLDBValue.Builder)this.c().addBuilder((GeneratedMessage)Model.LLDBValue.getDefaultInstance());
        }
        
        public Model.LLDBValue.Builder addValueBuilder(final int n) {
            return (Model.LLDBValue.Builder)this.c().addBuilder(n, (GeneratedMessage)Model.LLDBValue.getDefaultInstance());
        }
        
        public List<Model.LLDBValue.Builder> getValueBuilderList() {
            return (List<Model.LLDBValue.Builder>)this.c().getBuilderList();
        }
        
        private RepeatedFieldBuilder<Model.LLDBValue, Model.LLDBValue.Builder, Model.LLDBValueOrBuilder> c() {
            Builder builder = null;
            RepeatedFieldBuilder valueBuilder_ = null;
            List<Model.LLDBValue> list = null;
            boolean b = false;
            Label_0042: {
                Label_0033: {
                    try {
                        if (this.valueBuilder_ != null) {
                            return this.valueBuilder_;
                        }
                        builder = this;
                        valueBuilder_ = new(com.google.protobuf.RepeatedFieldBuilder.class);
                        final Builder builder2 = this;
                        list = builder2.value_;
                        final Builder builder3 = this;
                        final int n = builder3.bitField0_;
                        final int n2 = 2;
                        final int n3 = n & n2;
                        final int n4 = 2;
                        if (n3 == n4) {
                            break Label_0033;
                        }
                        break Label_0033;
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    try {
                        builder = this;
                        valueBuilder_ = new(com.google.protobuf.RepeatedFieldBuilder.class);
                        final Builder builder2 = this;
                        list = builder2.value_;
                        final Builder builder3 = this;
                        final int n = builder3.bitField0_;
                        final int n2 = 2;
                        final int n3 = n & n2;
                        final int n4 = 2;
                        if (n3 == n4) {
                            b = true;
                            break Label_0042;
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                b = false;
            }
            new RepeatedFieldBuilder((List)list, b, this.getParentForChildren(), this.isClean());
            builder.valueBuilder_ = (RepeatedFieldBuilder<Model.LLDBValue, Model.LLDBValue.Builder, Model.LLDBValueOrBuilder>)valueBuilder_;
            this.value_ = null;
            return this.valueBuilder_;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
