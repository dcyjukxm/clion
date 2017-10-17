// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements EvaluateExpression_ResOrBuilder
{
    private int bitField0_;
    private CommonResponse commonResponse_;
    private SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder> commonResponseBuilder_;
    private Model.LLDBValue result_;
    private SingleFieldBuilder<Model.LLDBValue, Model.LLDBValue.Builder, Model.LLDBValueOrBuilder> resultBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$30400();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$30500().ensureFieldAccessorsInitialized((Class)EvaluateExpression_Res.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.commonResponse_ = CommonResponse.getDefaultInstance();
        this.result_ = Model.LLDBValue.getDefaultInstance();
        this.c();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.commonResponse_ = CommonResponse.getDefaultInstance();
        this.result_ = Model.LLDBValue.getDefaultInstance();
        this.c();
    }
    
    private void c() {
        try {
            if (EvaluateExpression_Res.access$30900()) {
                this.a();
                this.b();
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
                    if (this.resultBuilder_ == null) {
                        this.result_ = Model.LLDBValue.getDefaultInstance();
                        break Label_0074;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.resultBuilder_.clear();
        }
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return d().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$30400();
    }
    
    public EvaluateExpression_Res getDefaultInstanceForType() {
        return EvaluateExpression_Res.getDefaultInstance();
    }
    
    public EvaluateExpression_Res build() {
        final EvaluateExpression_Res buildPartial = this.buildPartial();
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
    
    public EvaluateExpression_Res buildPartial() {
        final EvaluateExpression_Res evaluateExpression_Res = new EvaluateExpression_Res((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        Label_0066: {
            try {
                if (this.commonResponseBuilder_ == null) {
                    EvaluateExpression_Res.access$31102(evaluateExpression_Res, this.commonResponse_);
                    break Label_0066;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            EvaluateExpression_Res.access$31102(evaluateExpression_Res, (CommonResponse)this.commonResponseBuilder_.build());
        }
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        Label_0115: {
            try {
                if (this.resultBuilder_ == null) {
                    EvaluateExpression_Res.access$31202(evaluateExpression_Res, this.result_);
                    break Label_0115;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            EvaluateExpression_Res.access$31202(evaluateExpression_Res, (Model.LLDBValue)this.resultBuilder_.build());
        }
        EvaluateExpression_Res.access$31302(evaluateExpression_Res, n);
        this.onBuilt();
        return evaluateExpression_Res;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof EvaluateExpression_Res) {
                return this.mergeFrom((EvaluateExpression_Res)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final EvaluateExpression_Res evaluateExpression_Res) {
        try {
            if (evaluateExpression_Res == EvaluateExpression_Res.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (evaluateExpression_Res.hasCommonResponse()) {
                this.mergeCommonResponse(evaluateExpression_Res.getCommonResponse());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (evaluateExpression_Res.hasResult()) {
                this.mergeResult(evaluateExpression_Res.getResult());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(evaluateExpression_Res.getUnknownFields());
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
            if (!this.hasResult()) {
                return true;
            }
            final Builder builder = this;
            final Model.LLDBValue lldbValue = builder.getResult();
            final boolean b = lldbValue.isInitialized();
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
            final Model.LLDBValue lldbValue = builder.getResult();
            final boolean b = lldbValue.isInitialized();
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
        EvaluateExpression_Res evaluateExpression_Res = null;
        try {
            evaluateExpression_Res = (EvaluateExpression_Res)EvaluateExpression_Res.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            evaluateExpression_Res = (EvaluateExpression_Res)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (evaluateExpression_Res != null) {
                    this.mergeFrom(evaluateExpression_Res);
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
        //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //     4: ifnonnull       78
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.bitField0_:I
        //    11: iconst_1       
        //    12: iand           
        //    13: iconst_1       
        //    14: if_icmpne       66
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    31: if_acmpeq       66
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    40: athrow         
        //    41: aload_0        
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder;
        //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    59: goto            71
        //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    65: athrow         
        //    66: aload_0        
        //    67: aload_1        
        //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.commonResponse_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CommonResponse;
        //    71: aload_0        
        //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.onChanged:()V
        //    75: goto            87
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.commonResponseBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //    82: aload_1        
        //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
        //    86: pop            
        //    87: aload_0        
        //    88: dup            
        //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.bitField0_:I
        //    92: iconst_1       
        //    93: ior            
        //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.bitField0_:I
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
        return (CommonResponse.Builder)this.a().getBuilder();
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
    
    private SingleFieldBuilder<CommonResponse, CommonResponse.Builder, CommonResponseOrBuilder> a() {
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
    
    public boolean hasResult() {
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
    
    public Model.LLDBValue getResult() {
        try {
            if (this.resultBuilder_ == null) {
                return this.result_;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (Model.LLDBValue)this.resultBuilder_.getMessage();
    }
    
    public Builder setResult(final Model.LLDBValue lldbValue) {
        Label_0051: {
            Label_0042: {
                Label_0018: {
                    try {
                        if (this.resultBuilder_ != null) {
                            break Label_0042;
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
                this.result_ = lldbValue;
                this.onChanged();
                break Label_0051;
            }
            this.resultBuilder_.setMessage((GeneratedMessage)lldbValue);
        }
        this.bitField0_ |= 0x2;
        return this;
    }
    
    public Builder setResult(final Model.LLDBValue.Builder builder) {
        Label_0038: {
            try {
                if (this.resultBuilder_ == null) {
                    this.result_ = builder.build();
                    this.onChanged();
                    break Label_0038;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.resultBuilder_.setMessage((GeneratedMessage)builder.build());
        }
        this.bitField0_ |= 0x2;
        return this;
    }
    
    public Builder mergeResult(final Model.LLDBValue p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.resultBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //     4: ifnonnull       78
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.bitField0_:I
        //    11: iconst_2       
        //    12: iand           
        //    13: iconst_2       
        //    14: if_icmpne       66
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.result_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue;
        //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue;
        //    31: if_acmpeq       66
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    40: athrow         
        //    41: aload_0        
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.result_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue;
        //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue$Builder;
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue$Builder;
        //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue;
        //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.result_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue;
        //    59: goto            71
        //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    65: athrow         
        //    66: aload_0        
        //    67: aload_1        
        //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.result_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBValue;
        //    71: aload_0        
        //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.onChanged:()V
        //    75: goto            87
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.resultBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //    82: aload_1        
        //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
        //    86: pop            
        //    87: aload_0        
        //    88: dup            
        //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.bitField0_:I
        //    92: iconst_2       
        //    93: ior            
        //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.bitField0_:I
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
    
    public Builder clearResult() {
        Label_0033: {
            try {
                if (this.resultBuilder_ == null) {
                    this.result_ = Model.LLDBValue.getDefaultInstance();
                    this.onChanged();
                    break Label_0033;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.resultBuilder_.clear();
        }
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Model.LLDBValue.Builder getResultBuilder() {
        this.bitField0_ |= 0x2;
        this.onChanged();
        return (Model.LLDBValue.Builder)this.b().getBuilder();
    }
    
    public Model.LLDBValueOrBuilder getResultOrBuilder() {
        try {
            if (this.resultBuilder_ != null) {
                return (Model.LLDBValueOrBuilder)this.resultBuilder_.getMessageOrBuilder();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.result_;
    }
    
    private SingleFieldBuilder<Model.LLDBValue, Model.LLDBValue.Builder, Model.LLDBValueOrBuilder> b() {
        try {
            if (this.resultBuilder_ == null) {
                this.resultBuilder_ = (SingleFieldBuilder<Model.LLDBValue, Model.LLDBValue.Builder, Model.LLDBValueOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.result_, this.getParentForChildren(), this.isClean());
                this.result_ = null;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.resultBuilder_;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
