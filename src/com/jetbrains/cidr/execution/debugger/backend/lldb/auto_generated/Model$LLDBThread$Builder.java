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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBThreadOrBuilder
{
    private int bitField0_;
    private int id_;
    private Object name_;
    private Object queue_;
    private ThreadStopReasonInfo stopReasonInfo_;
    private SingleFieldBuilder<ThreadStopReasonInfo, ThreadStopReasonInfo.Builder, ThreadStopReasonInfoOrBuilder> stopReasonInfoBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$4600();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$4700().ensureFieldAccessorsInitialized((Class)LLDBThread.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.name_ = "";
        this.queue_ = "";
        this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
        this.c();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.queue_ = "";
        this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
        this.c();
    }
    
    private void c() {
        try {
            if (LLDBThread.access$5100()) {
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
        Label_0084: {
            try {
                super.clear();
                this.id_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.queue_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.stopReasonInfoBuilder_ == null) {
                    this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
                    break Label_0084;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.stopReasonInfoBuilder_.clear();
        }
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Model.access$4600();
    }
    
    public LLDBThread getDefaultInstanceForType() {
        return LLDBThread.getDefaultInstance();
    }
    
    public LLDBThread build() {
        final LLDBThread buildPartial = this.buildPartial();
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
    
    public LLDBThread buildPartial() {
        final LLDBThread lldbThread = new LLDBThread((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        LLDBThread.access$5302(lldbThread, this.id_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        LLDBThread.access$5402(lldbThread, this.name_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        LLDBThread.access$5502(lldbThread, this.queue_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        Label_0129: {
            try {
                if (this.stopReasonInfoBuilder_ == null) {
                    LLDBThread.access$5602(lldbThread, this.stopReasonInfo_);
                    break Label_0129;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            LLDBThread.access$5602(lldbThread, (ThreadStopReasonInfo)this.stopReasonInfoBuilder_.build());
        }
        LLDBThread.access$5702(lldbThread, n);
        this.onBuilt();
        return lldbThread;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof LLDBThread) {
                return this.mergeFrom((LLDBThread)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final LLDBThread lldbThread) {
        try {
            if (lldbThread == LLDBThread.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (lldbThread.hasId()) {
                this.setId(lldbThread.getId());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (lldbThread.hasName()) {
                this.bitField0_ |= 0x2;
                this.name_ = LLDBThread.access$5400(lldbThread);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (lldbThread.hasQueue()) {
                this.bitField0_ |= 0x4;
                this.queue_ = LLDBThread.access$5500(lldbThread);
                this.onChanged();
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (lldbThread.hasStopReasonInfo()) {
                this.mergeStopReasonInfo(lldbThread.getStopReasonInfo());
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        this.mergeUnknownFields(lldbThread.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasId()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasStopReasonInfo()) {
                return true;
            }
            final Builder builder = this;
            final ThreadStopReasonInfo threadStopReasonInfo = builder.getStopReasonInfo();
            final boolean b = threadStopReasonInfo.isInitialized();
            if (!b) {
                return false;
            }
            return true;
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            final Builder builder = this;
            final ThreadStopReasonInfo threadStopReasonInfo = builder.getStopReasonInfo();
            final boolean b = threadStopReasonInfo.isInitialized();
            if (!b) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        LLDBThread lldbThread = null;
        try {
            lldbThread = (LLDBThread)LLDBThread.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            lldbThread = (LLDBThread)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (lldbThread != null) {
                    this.mergeFrom(lldbThread);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasId() {
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
    
    public int getId() {
        return this.id_;
    }
    
    public Builder setId(final int id_) {
        this.bitField0_ |= 0x1;
        this.id_ = id_;
        this.onChanged();
        return this;
    }
    
    public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasName() {
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
    
    public String getName() {
        final Object name_ = this.name_;
        if (!(name_ instanceof String)) {
            return (String)(this.name_ = ((ByteString)name_).toStringUtf8());
        }
        return (String)name_;
    }
    
    public ByteString getNameBytes() {
        final Object name_ = this.name_;
        if (name_ instanceof String) {
            return (ByteString)(this.name_ = ByteString.copyFromUtf8((String)name_));
        }
        return (ByteString)name_;
    }
    
    public Builder setName(final String name_) {
        try {
            if (name_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.name_ = name_;
        this.onChanged();
        return this;
    }
    
    public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.name_ = LLDBThread.getDefaultInstance().getName();
        this.onChanged();
        return this;
    }
    
    public Builder setNameBytes(final ByteString name_) {
        try {
            if (name_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.name_ = name_;
        this.onChanged();
        return this;
    }
    
    public boolean hasQueue() {
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
    
    public String getQueue() {
        final Object queue_ = this.queue_;
        if (!(queue_ instanceof String)) {
            return (String)(this.queue_ = ((ByteString)queue_).toStringUtf8());
        }
        return (String)queue_;
    }
    
    public ByteString getQueueBytes() {
        final Object queue_ = this.queue_;
        if (queue_ instanceof String) {
            return (ByteString)(this.queue_ = ByteString.copyFromUtf8((String)queue_));
        }
        return (ByteString)queue_;
    }
    
    public Builder setQueue(final String queue_) {
        try {
            if (queue_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.queue_ = queue_;
        this.onChanged();
        return this;
    }
    
    public Builder clearQueue() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.queue_ = LLDBThread.getDefaultInstance().getQueue();
        this.onChanged();
        return this;
    }
    
    public Builder setQueueBytes(final ByteString queue_) {
        try {
            if (queue_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.queue_ = queue_;
        this.onChanged();
        return this;
    }
    
    public boolean hasStopReasonInfo() {
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
    
    public ThreadStopReasonInfo getStopReasonInfo() {
        try {
            if (this.stopReasonInfoBuilder_ == null) {
                return this.stopReasonInfo_;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (ThreadStopReasonInfo)this.stopReasonInfoBuilder_.getMessage();
    }
    
    public Builder setStopReasonInfo(final ThreadStopReasonInfo threadStopReasonInfo) {
        Label_0051: {
            Label_0042: {
                Label_0018: {
                    try {
                        if (this.stopReasonInfoBuilder_ != null) {
                            break Label_0042;
                        }
                        final ThreadStopReasonInfo threadStopReasonInfo2 = threadStopReasonInfo;
                        if (threadStopReasonInfo2 == null) {
                            break Label_0018;
                        }
                        break Label_0018;
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    try {
                        final ThreadStopReasonInfo threadStopReasonInfo2 = threadStopReasonInfo;
                        if (threadStopReasonInfo2 == null) {
                            throw new NullPointerException();
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                this.stopReasonInfo_ = threadStopReasonInfo;
                this.onChanged();
                break Label_0051;
            }
            this.stopReasonInfoBuilder_.setMessage((GeneratedMessage)threadStopReasonInfo);
        }
        this.bitField0_ |= 0x8;
        return this;
    }
    
    public Builder setStopReasonInfo(final ThreadStopReasonInfo.Builder builder) {
        Label_0038: {
            try {
                if (this.stopReasonInfoBuilder_ == null) {
                    this.stopReasonInfo_ = builder.build();
                    this.onChanged();
                    break Label_0038;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.stopReasonInfoBuilder_.setMessage((GeneratedMessage)builder.build());
        }
        this.bitField0_ |= 0x8;
        return this;
    }
    
    public Builder mergeStopReasonInfo(final ThreadStopReasonInfo p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfoBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //     4: ifnonnull       80
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.bitField0_:I
        //    11: bipush          8
        //    13: iand           
        //    14: bipush          8
        //    16: if_icmpne       68
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    25: athrow         
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfo_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
        //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
        //    33: if_acmpeq       68
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    42: athrow         
        //    43: aload_0        
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfo_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
        //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo$Builder;
        //    51: aload_1        
        //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo$Builder;
        //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
        //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfo_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
        //    61: goto            73
        //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    67: athrow         
        //    68: aload_0        
        //    69: aload_1        
        //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfo_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
        //    73: aload_0        
        //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.onChanged:()V
        //    77: goto            89
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfoBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //    84: aload_1        
        //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
        //    88: pop            
        //    89: aload_0        
        //    90: dup            
        //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.bitField0_:I
        //    94: bipush          8
        //    96: ior            
        //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.bitField0_:I
        //   100: aload_0        
        //   101: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      19     22     26     Ljava/lang/NullPointerException;
        //  7      36     39     43     Ljava/lang/NullPointerException;
        //  26     64     64     68     Ljava/lang/NullPointerException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    
    public Builder clearStopReasonInfo() {
        Label_0033: {
            try {
                if (this.stopReasonInfoBuilder_ == null) {
                    this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
                    this.onChanged();
                    break Label_0033;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.stopReasonInfoBuilder_.clear();
        }
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
    }
    
    public ThreadStopReasonInfo.Builder getStopReasonInfoBuilder() {
        this.bitField0_ |= 0x8;
        this.onChanged();
        return (ThreadStopReasonInfo.Builder)this.a().getBuilder();
    }
    
    public ThreadStopReasonInfoOrBuilder getStopReasonInfoOrBuilder() {
        try {
            if (this.stopReasonInfoBuilder_ != null) {
                return (ThreadStopReasonInfoOrBuilder)this.stopReasonInfoBuilder_.getMessageOrBuilder();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.stopReasonInfo_;
    }
    
    private SingleFieldBuilder<ThreadStopReasonInfo, ThreadStopReasonInfo.Builder, ThreadStopReasonInfoOrBuilder> a() {
        try {
            if (this.stopReasonInfoBuilder_ == null) {
                this.stopReasonInfoBuilder_ = (SingleFieldBuilder<ThreadStopReasonInfo, ThreadStopReasonInfo.Builder, ThreadStopReasonInfoOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.stopReasonInfo_, this.getParentForChildren(), this.isClean());
                this.stopReasonInfo_ = null;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.stopReasonInfoBuilder_;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
