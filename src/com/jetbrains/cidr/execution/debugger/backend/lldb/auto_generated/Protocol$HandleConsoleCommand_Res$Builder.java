// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.GeneratedMessage;

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
            if (HandleConsoleCommand_Res.access$51800()) {
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
                    HandleConsoleCommand_Res.access$52002(handleConsoleCommand_Res, this.commonResponse_);
                    break Label_0066;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            HandleConsoleCommand_Res.access$52002(handleConsoleCommand_Res, (CommonResponse)this.commonResponseBuilder_.build());
        }
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        HandleConsoleCommand_Res.access$52102(handleConsoleCommand_Res, this.success_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        HandleConsoleCommand_Res.access$52202(handleConsoleCommand_Res, this.err_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        HandleConsoleCommand_Res.access$52302(handleConsoleCommand_Res, this.out_);
        HandleConsoleCommand_Res.access$52402(handleConsoleCommand_Res, n);
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
                this.err_ = HandleConsoleCommand_Res.access$52200(handleConsoleCommand_Res);
                this.onChanged();
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (handleConsoleCommand_Res.hasOut()) {
                this.bitField0_ |= 0x8;
                this.out_ = HandleConsoleCommand_Res.access$52300(handleConsoleCommand_Res);
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
