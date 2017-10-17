// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import java.util.Collection;
import java.util.ArrayList;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import java.util.Collections;
import com.google.protobuf.Descriptors;
import com.google.protobuf.RepeatedFieldBuilder;
import java.util.List;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements GetThreads_ResOrBuilder
{
    private int bitField0_;
    private CommonResponse commonResponse_;
    private SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder> commonResponseBuilder_;
    private List<Model.LLDBThread> thread_;
    private RepeatedFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder> threadBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$16000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$16100().ensureFieldAccessorsInitialized((Class)GetThreads_Res.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.commonResponse_ = CommonResponse.getDefaultInstance();
        this.thread_ = Collections.emptyList();
        this.d();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.commonResponse_ = CommonResponse.getDefaultInstance();
        this.thread_ = Collections.emptyList();
        this.d();
    }
    
    private void d() {
        try {
            if (GetThreads_Res.access$16500()) {
                this.b();
                this.e();
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
            try {
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.threadBuilder_ == null) {
                    this.thread_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
        }
        this.threadBuilder_.clear();
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$16000();
    }
    
    public GetThreads_Res getDefaultInstanceForType() {
        return GetThreads_Res.getDefaultInstance();
    }
    
    public GetThreads_Res build() {
        final GetThreads_Res buildPartial = this.buildPartial();
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
    
    public GetThreads_Res buildPartial() {
        final GetThreads_Res getThreads_Res = new GetThreads_Res((GeneratedMessage.Builder)this);
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
                                GetThreads_Res.access$16702(getThreads_Res, this.commonResponse_);
                                break Label_0066;
                            }
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        GetThreads_Res.access$16702(getThreads_Res, (CommonResponse)this.commonResponseBuilder_.build());
                        try {
                            if (this.threadBuilder_ != null) {
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
                            this.thread_ = Collections.unmodifiableList((List<? extends Model.LLDBThread>)this.thread_);
                            this.bitField0_ &= 0xFFFFFFFD;
                        }
                    }
                    catch (NullPointerException ex3) {
                        throw b(ex3);
                    }
                }
                GetThreads_Res.access$16802(getThreads_Res, this.thread_);
                break Label_0143;
            }
            GetThreads_Res.access$16802(getThreads_Res, this.threadBuilder_.build());
        }
        GetThreads_Res.access$16902(getThreads_Res, n);
        this.onBuilt();
        return getThreads_Res;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof GetThreads_Res) {
                return this.mergeFrom((GetThreads_Res)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final GetThreads_Res p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;
        //     4: if_acmpne       13
        //     7: aload_0        
        //     8: areturn        
        //     9: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    12: athrow         
        //    13: aload_1        
        //    14: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.hasCommonResponse:()Z
        //    17: ifeq            36
        //    20: aload_0        
        //    21: aload_1        
        //    22: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.getCommonResponse:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    25: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.mergeCommonResponse:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder;
        //    28: pop            
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    35: athrow         
        //    36: aload_0        
        //    37: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.threadBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //    40: ifnonnull       132
        //    43: aload_1        
        //    44: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.access$16800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;)Ljava/util/List;
        //    47: invokeinterface java/util/List.isEmpty:()Z
        //    52: ifne            236
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    61: athrow         
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.thread_:Ljava/util/List;
        //    66: invokeinterface java/util/List.isEmpty:()Z
        //    71: ifeq            107
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    80: athrow         
        //    81: aload_0        
        //    82: aload_1        
        //    83: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.access$16800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;)Ljava/util/List;
        //    86: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.thread_:Ljava/util/List;
        //    89: aload_0        
        //    90: aload_0        
        //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.bitField0_:I
        //    94: bipush          -3
        //    96: iand           
        //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.bitField0_:I
        //   100: goto            125
        //   103: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   106: athrow         
        //   107: aload_0        
        //   108: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.c:()V
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.thread_:Ljava/util/List;
        //   115: aload_1        
        //   116: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.access$16800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;)Ljava/util/List;
        //   119: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   124: pop            
        //   125: aload_0        
        //   126: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.onChanged:()V
        //   129: goto            236
        //   132: aload_1        
        //   133: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.access$16800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;)Ljava/util/List;
        //   136: invokeinterface java/util/List.isEmpty:()Z
        //   141: ifne            236
        //   144: aload_0        
        //   145: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.threadBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   148: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.isEmpty:()Z
        //   151: ifeq            224
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   160: athrow         
        //   161: aload_0        
        //   162: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.threadBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   165: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.dispose:()V
        //   168: aload_0        
        //   169: aconst_null    
        //   170: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.threadBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   173: aload_0        
        //   174: aload_1        
        //   175: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.access$16800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;)Ljava/util/List;
        //   178: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.thread_:Ljava/util/List;
        //   181: aload_0        
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.bitField0_:I
        //   186: bipush          -3
        //   188: iand           
        //   189: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.bitField0_:I
        //   192: aload_0        
        //   193: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.access$17000:()Z
        //   196: ifeq            217
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   205: athrow         
        //   206: aload_0        
        //   207: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.e:()Lcom/google/protobuf/RepeatedFieldBuilder;
        //   210: goto            218
        //   213: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   216: athrow         
        //   217: aconst_null    
        //   218: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.threadBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   221: goto            236
        //   224: aload_0        
        //   225: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.threadBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   228: aload_1        
        //   229: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.access$16800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;)Ljava/util/List;
        //   232: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.addAllMessages:(Ljava/lang/Iterable;)Lcom/google/protobuf/RepeatedFieldBuilder;
        //   235: pop            
        //   236: aload_0        
        //   237: aload_1        
        //   238: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.getUnknownFields:()Lcom/google/protobuf/UnknownFieldSet;
        //   241: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.mergeUnknownFields:(Lcom/google/protobuf/UnknownFieldSet;)Lcom/google/protobuf/GeneratedMessage$Builder;
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
                    if (n >= this.getThreadCount()) {
                        break;
                    }
                    final Builder builder = this;
                    final int n2 = n;
                    final Model.LLDBThread lldbThread = builder.getThread(n2);
                    final boolean b = lldbThread.isInitialized();
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
                    final Model.LLDBThread lldbThread = builder.getThread(n2);
                    final boolean b = lldbThread.isInitialized();
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
        GetThreads_Res getThreads_Res = null;
        try {
            getThreads_Res = (GetThreads_Res)GetThreads_Res.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            getThreads_Res = (GetThreads_Res)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (getThreads_Res != null) {
                    this.mergeFrom(getThreads_Res);
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
        //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //     4: ifnonnull       78
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.bitField0_:I
        //    11: iconst_1       
        //    12: iand           
        //    13: iconst_1       
        //    14: if_icmpne       66
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    31: if_acmpeq       66
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    40: athrow         
        //    41: aload_0        
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
        //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    59: goto            71
        //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    65: athrow         
        //    66: aload_0        
        //    67: aload_1        
        //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    71: aload_0        
        //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.onChanged:()V
        //    75: goto            87
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //    82: aload_1        
        //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
        //    86: pop            
        //    87: aload_0        
        //    88: dup            
        //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.bitField0_:I
        //    92: iconst_1       
        //    93: ior            
        //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.bitField0_:I
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
    
    private void c() {
        try {
            if ((this.bitField0_ & 0x2) != 0x2) {
                this.thread_ = new ArrayList<Model.LLDBThread>(this.thread_);
                this.bitField0_ |= 0x2;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
    }
    
    public List<Model.LLDBThread> getThreadList() {
        try {
            if (this.threadBuilder_ == null) {
                return Collections.unmodifiableList((List<? extends Model.LLDBThread>)this.thread_);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (List<Model.LLDBThread>)this.threadBuilder_.getMessageList();
    }
    
    public int getThreadCount() {
        try {
            if (this.threadBuilder_ == null) {
                return this.thread_.size();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.threadBuilder_.getCount();
    }
    
    public Model.LLDBThread getThread(final int n) {
        try {
            if (this.threadBuilder_ == null) {
                return this.thread_.get(n);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (Model.LLDBThread)this.threadBuilder_.getMessage(n);
    }
    
    public Builder setThread(final int n, final Model.LLDBThread lldbThread) {
        Label_0053: {
            Label_0018: {
                try {
                    if (this.threadBuilder_ != null) {
                        break Label_0053;
                    }
                    final Model.LLDBThread lldbThread2 = lldbThread;
                    if (lldbThread2 == null) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    final Model.LLDBThread lldbThread2 = lldbThread;
                    if (lldbThread2 == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.c();
            this.thread_.set(n, lldbThread);
            this.onChanged();
            return this;
        }
        this.threadBuilder_.setMessage(n, (GeneratedMessage)lldbThread);
        return this;
    }
    
    public Builder setThread(final int n, final Model.LLDBThread.Builder builder) {
        try {
            if (this.threadBuilder_ == null) {
                this.c();
                this.thread_.set(n, builder.build());
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.threadBuilder_.setMessage(n, (GeneratedMessage)builder.build());
        return this;
    }
    
    public Builder addThread(final Model.LLDBThread lldbThread) {
        Label_0052: {
            Label_0018: {
                try {
                    if (this.threadBuilder_ != null) {
                        break Label_0052;
                    }
                    final Model.LLDBThread lldbThread2 = lldbThread;
                    if (lldbThread2 == null) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    final Model.LLDBThread lldbThread2 = lldbThread;
                    if (lldbThread2 == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.c();
            this.thread_.add(lldbThread);
            this.onChanged();
            return this;
        }
        this.threadBuilder_.addMessage((GeneratedMessage)lldbThread);
        return this;
    }
    
    public Builder addThread(final int n, final Model.LLDBThread lldbThread) {
        Label_0052: {
            Label_0018: {
                try {
                    if (this.threadBuilder_ != null) {
                        break Label_0052;
                    }
                    final Model.LLDBThread lldbThread2 = lldbThread;
                    if (lldbThread2 == null) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    final Model.LLDBThread lldbThread2 = lldbThread;
                    if (lldbThread2 == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.c();
            this.thread_.add(n, lldbThread);
            this.onChanged();
            return this;
        }
        this.threadBuilder_.addMessage(n, (GeneratedMessage)lldbThread);
        return this;
    }
    
    public Builder addThread(final Model.LLDBThread.Builder builder) {
        try {
            if (this.threadBuilder_ == null) {
                this.c();
                this.thread_.add(builder.build());
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.threadBuilder_.addMessage((GeneratedMessage)builder.build());
        return this;
    }
    
    public Builder addThread(final int n, final Model.LLDBThread.Builder builder) {
        try {
            if (this.threadBuilder_ == null) {
                this.c();
                this.thread_.add(n, builder.build());
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.threadBuilder_.addMessage(n, (GeneratedMessage)builder.build());
        return this;
    }
    
    public Builder addAllThread(final Iterable<? extends Model.LLDBThread> iterable) {
        try {
            if (this.threadBuilder_ == null) {
                this.c();
                GeneratedMessage.Builder.addAll((Iterable)iterable, (Collection)this.thread_);
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.threadBuilder_.addAllMessages((Iterable)iterable);
        return this;
    }
    
    public Builder clearThread() {
        try {
            if (this.threadBuilder_ == null) {
                this.thread_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFD;
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.threadBuilder_.clear();
        return this;
    }
    
    public Builder removeThread(final int n) {
        try {
            if (this.threadBuilder_ == null) {
                this.c();
                this.thread_.remove(n);
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.threadBuilder_.remove(n);
        return this;
    }
    
    public Model.LLDBThread.Builder getThreadBuilder(final int n) {
        return (Model.LLDBThread.Builder)this.e().getBuilder(n);
    }
    
    public Model.LLDBThreadOrBuilder getThreadOrBuilder(final int n) {
        try {
            if (this.threadBuilder_ == null) {
                return this.thread_.get(n);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (Model.LLDBThreadOrBuilder)this.threadBuilder_.getMessageOrBuilder(n);
    }
    
    public List<? extends Model.LLDBThreadOrBuilder> getThreadOrBuilderList() {
        try {
            if (this.threadBuilder_ != null) {
                return (List<? extends Model.LLDBThreadOrBuilder>)this.threadBuilder_.getMessageOrBuilderList();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return Collections.unmodifiableList((List<? extends Model.LLDBThreadOrBuilder>)this.thread_);
    }
    
    public Model.LLDBThread.Builder addThreadBuilder() {
        return (Model.LLDBThread.Builder)this.e().addBuilder((GeneratedMessage)Model.LLDBThread.getDefaultInstance());
    }
    
    public Model.LLDBThread.Builder addThreadBuilder(final int n) {
        return (Model.LLDBThread.Builder)this.e().addBuilder(n, (GeneratedMessage)Model.LLDBThread.getDefaultInstance());
    }
    
    public List<Model.LLDBThread.Builder> getThreadBuilderList() {
        return (List<Model.LLDBThread.Builder>)this.e().getBuilderList();
    }
    
    private RepeatedFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder> e() {
        Builder builder = null;
        RepeatedFieldBuilder threadBuilder_ = null;
        List<Model.LLDBThread> list = null;
        boolean b = false;
        Label_0042: {
            Label_0033: {
                try {
                    if (this.threadBuilder_ != null) {
                        return this.threadBuilder_;
                    }
                    builder = this;
                    threadBuilder_ = new(com.google.protobuf.RepeatedFieldBuilder.class);
                    final Builder builder2 = this;
                    list = builder2.thread_;
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
                    threadBuilder_ = new(com.google.protobuf.RepeatedFieldBuilder.class);
                    final Builder builder2 = this;
                    list = builder2.thread_;
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
        builder.threadBuilder_ = (RepeatedFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder>)threadBuilder_;
        this.thread_ = null;
        return this.threadBuilder_;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
