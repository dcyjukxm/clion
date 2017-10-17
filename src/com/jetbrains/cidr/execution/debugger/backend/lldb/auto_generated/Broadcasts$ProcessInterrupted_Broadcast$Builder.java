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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ProcessInterrupted_BroadcastOrBuilder
{
    private int bitField0_;
    private Model.LLDBThread thread_;
    private SingleFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder> threadBuilder_;
    private Model.LLDBFrame frame_;
    private SingleFieldBuilder<Model.LLDBFrame, Model.LLDBFrame.Builder, Model.LLDBFrameOrBuilder> frameBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$000();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$100().ensureFieldAccessorsInitialized((Class)ProcessInterrupted_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.thread_ = Model.LLDBThread.getDefaultInstance();
        this.frame_ = Model.LLDBFrame.getDefaultInstance();
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.thread_ = Model.LLDBThread.getDefaultInstance();
        this.frame_ = Model.LLDBFrame.getDefaultInstance();
        this.a();
    }
    
    private void a() {
        try {
            if (ProcessInterrupted_Broadcast.access$500()) {
                this.d();
                this.c();
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
        Label_0074: {
            Label_0034: {
                try {
                    super.clear();
                    if (this.threadBuilder_ == null) {
                        this.thread_ = Model.LLDBThread.getDefaultInstance();
                        break Label_0034;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.threadBuilder_.clear();
                try {
                    this.bitField0_ &= 0xFFFFFFFE;
                    if (this.frameBuilder_ == null) {
                        this.frame_ = Model.LLDBFrame.getDefaultInstance();
                        break Label_0074;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.frameBuilder_.clear();
        }
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$000();
    }
    
    public ProcessInterrupted_Broadcast getDefaultInstanceForType() {
        return ProcessInterrupted_Broadcast.getDefaultInstance();
    }
    
    public ProcessInterrupted_Broadcast build() {
        final ProcessInterrupted_Broadcast buildPartial = this.buildPartial();
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
    
    public ProcessInterrupted_Broadcast buildPartial() {
        final ProcessInterrupted_Broadcast processInterrupted_Broadcast = new ProcessInterrupted_Broadcast((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        Label_0066: {
            try {
                if (this.threadBuilder_ == null) {
                    ProcessInterrupted_Broadcast.access$702(processInterrupted_Broadcast, this.thread_);
                    break Label_0066;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            ProcessInterrupted_Broadcast.access$702(processInterrupted_Broadcast, (Model.LLDBThread)this.threadBuilder_.build());
        }
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        Label_0115: {
            try {
                if (this.frameBuilder_ == null) {
                    ProcessInterrupted_Broadcast.access$802(processInterrupted_Broadcast, this.frame_);
                    break Label_0115;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            ProcessInterrupted_Broadcast.access$802(processInterrupted_Broadcast, (Model.LLDBFrame)this.frameBuilder_.build());
        }
        ProcessInterrupted_Broadcast.access$902(processInterrupted_Broadcast, n);
        this.onBuilt();
        return processInterrupted_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof ProcessInterrupted_Broadcast) {
                return this.mergeFrom((ProcessInterrupted_Broadcast)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ProcessInterrupted_Broadcast processInterrupted_Broadcast) {
        try {
            if (processInterrupted_Broadcast == ProcessInterrupted_Broadcast.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (processInterrupted_Broadcast.hasThread()) {
                this.mergeThread(processInterrupted_Broadcast.getThread());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (processInterrupted_Broadcast.hasFrame()) {
                this.mergeFrame(processInterrupted_Broadcast.getFrame());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        this.mergeUnknownFields(processInterrupted_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasThread()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasFrame()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.getThread().isInitialized()) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (!this.getFrame().isInitialized()) {
                return false;
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ProcessInterrupted_Broadcast processInterrupted_Broadcast = null;
        try {
            processInterrupted_Broadcast = (ProcessInterrupted_Broadcast)ProcessInterrupted_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            processInterrupted_Broadcast = (ProcessInterrupted_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (processInterrupted_Broadcast != null) {
                    this.mergeFrom(processInterrupted_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasThread() {
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
    
    public Model.LLDBThread getThread() {
        try {
            if (this.threadBuilder_ == null) {
                return this.thread_;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (Model.LLDBThread)this.threadBuilder_.getMessage();
    }
    
    public Builder setThread(final Model.LLDBThread lldbThread) {
        Label_0051: {
            Label_0042: {
                Label_0018: {
                    try {
                        if (this.threadBuilder_ != null) {
                            break Label_0042;
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
                this.thread_ = lldbThread;
                this.onChanged();
                break Label_0051;
            }
            this.threadBuilder_.setMessage((GeneratedMessage)lldbThread);
        }
        this.bitField0_ |= 0x1;
        return this;
    }
    
    public Builder setThread(final Model.LLDBThread.Builder builder) {
        Label_0038: {
            try {
                if (this.threadBuilder_ == null) {
                    this.thread_ = builder.build();
                    this.onChanged();
                    break Label_0038;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.threadBuilder_.setMessage((GeneratedMessage)builder.build());
        }
        this.bitField0_ |= 0x1;
        return this;
    }
    
    public Builder mergeThread(final Model.LLDBThread p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.threadBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //     4: ifnonnull       78
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
        //    11: iconst_1       
        //    12: iand           
        //    13: iconst_1       
        //    14: if_icmpne       66
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.thread_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
        //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
        //    31: if_acmpeq       66
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    40: athrow         
        //    41: aload_0        
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.thread_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
        //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder;
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder;
        //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
        //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.thread_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
        //    59: goto            71
        //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    65: athrow         
        //    66: aload_0        
        //    67: aload_1        
        //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.thread_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread;
        //    71: aload_0        
        //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.onChanged:()V
        //    75: goto            87
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.threadBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //    82: aload_1        
        //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
        //    86: pop            
        //    87: aload_0        
        //    88: dup            
        //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
        //    92: iconst_1       
        //    93: ior            
        //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
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
    
    public Builder clearThread() {
        Label_0033: {
            try {
                if (this.threadBuilder_ == null) {
                    this.thread_ = Model.LLDBThread.getDefaultInstance();
                    this.onChanged();
                    break Label_0033;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.threadBuilder_.clear();
        }
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Model.LLDBThread.Builder getThreadBuilder() {
        this.bitField0_ |= 0x1;
        this.onChanged();
        return (Model.LLDBThread.Builder)this.d().getBuilder();
    }
    
    public Model.LLDBThreadOrBuilder getThreadOrBuilder() {
        try {
            if (this.threadBuilder_ != null) {
                return (Model.LLDBThreadOrBuilder)this.threadBuilder_.getMessageOrBuilder();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.thread_;
    }
    
    private SingleFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder> d() {
        try {
            if (this.threadBuilder_ == null) {
                this.threadBuilder_ = (SingleFieldBuilder<Model.LLDBThread, Model.LLDBThread.Builder, Model.LLDBThreadOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.thread_, this.getParentForChildren(), this.isClean());
                this.thread_ = null;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.threadBuilder_;
    }
    
    public boolean hasFrame() {
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
    
    public Model.LLDBFrame getFrame() {
        try {
            if (this.frameBuilder_ == null) {
                return this.frame_;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (Model.LLDBFrame)this.frameBuilder_.getMessage();
    }
    
    public Builder setFrame(final Model.LLDBFrame lldbFrame) {
        Label_0051: {
            Label_0042: {
                Label_0018: {
                    try {
                        if (this.frameBuilder_ != null) {
                            break Label_0042;
                        }
                        final Model.LLDBFrame lldbFrame2 = lldbFrame;
                        if (lldbFrame2 == null) {
                            break Label_0018;
                        }
                        break Label_0018;
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    try {
                        final Model.LLDBFrame lldbFrame2 = lldbFrame;
                        if (lldbFrame2 == null) {
                            throw new NullPointerException();
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                this.frame_ = lldbFrame;
                this.onChanged();
                break Label_0051;
            }
            this.frameBuilder_.setMessage((GeneratedMessage)lldbFrame);
        }
        this.bitField0_ |= 0x2;
        return this;
    }
    
    public Builder setFrame(final Model.LLDBFrame.Builder builder) {
        Label_0038: {
            try {
                if (this.frameBuilder_ == null) {
                    this.frame_ = builder.build();
                    this.onChanged();
                    break Label_0038;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.frameBuilder_.setMessage((GeneratedMessage)builder.build());
        }
        this.bitField0_ |= 0x2;
        return this;
    }
    
    public Builder mergeFrame(final Model.LLDBFrame p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frameBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //     4: ifnonnull       78
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
        //    11: iconst_2       
        //    12: iand           
        //    13: iconst_2       
        //    14: if_icmpne       66
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frame_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
        //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
        //    31: if_acmpeq       66
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    40: athrow         
        //    41: aload_0        
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frame_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
        //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame$Builder;
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame$Builder;
        //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
        //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frame_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
        //    59: goto            71
        //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    65: athrow         
        //    66: aload_0        
        //    67: aload_1        
        //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frame_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBFrame;
        //    71: aload_0        
        //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.onChanged:()V
        //    75: goto            87
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.frameBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
        //    82: aload_1        
        //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
        //    86: pop            
        //    87: aload_0        
        //    88: dup            
        //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
        //    92: iconst_2       
        //    93: ior            
        //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$ProcessInterrupted_Broadcast$Builder.bitField0_:I
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
    
    public Builder clearFrame() {
        Label_0033: {
            try {
                if (this.frameBuilder_ == null) {
                    this.frame_ = Model.LLDBFrame.getDefaultInstance();
                    this.onChanged();
                    break Label_0033;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.frameBuilder_.clear();
        }
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
    }
    
    public Model.LLDBFrame.Builder getFrameBuilder() {
        this.bitField0_ |= 0x2;
        this.onChanged();
        return (Model.LLDBFrame.Builder)this.c().getBuilder();
    }
    
    public Model.LLDBFrameOrBuilder getFrameOrBuilder() {
        try {
            if (this.frameBuilder_ != null) {
                return (Model.LLDBFrameOrBuilder)this.frameBuilder_.getMessageOrBuilder();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.frame_;
    }
    
    private SingleFieldBuilder<Model.LLDBFrame, Model.LLDBFrame.Builder, Model.LLDBFrameOrBuilder> c() {
        try {
            if (this.frameBuilder_ == null) {
                this.frameBuilder_ = (SingleFieldBuilder<Model.LLDBFrame, Model.LLDBFrame.Builder, Model.LLDBFrameOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.frame_, this.getParentForChildren(), this.isClean());
                this.frame_ = null;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.frameBuilder_;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
