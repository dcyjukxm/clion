// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import java.util.Collection;
import java.util.ArrayList;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.UnmodifiableLazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.LazyStringArrayList;
import java.util.Collections;
import com.google.protobuf.Descriptors;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.RepeatedFieldBuilder;
import java.util.List;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements CommandLineOrBuilder
{
    private int bitField0_;
    private List<EnvParam> env_;
    private RepeatedFieldBuilder<EnvParam, EnvParam.Builder, EnvParamOrBuilder> envBuilder_;
    private Object exePath_;
    private Object workingDir_;
    private LazyStringList param_;
    private Object stdinPath_;
    private Object stdoutPath_;
    private Object stderrPath_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$1700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$1800().ensureFieldAccessorsInitialized((Class)CommandLine.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.env_ = Collections.emptyList();
        this.exePath_ = "";
        this.workingDir_ = "";
        this.param_ = LazyStringArrayList.EMPTY;
        this.stdinPath_ = "";
        this.stdoutPath_ = "";
        this.stderrPath_ = "";
        this.c();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.env_ = Collections.emptyList();
        this.exePath_ = "";
        this.workingDir_ = "";
        this.param_ = LazyStringArrayList.EMPTY;
        this.stdinPath_ = "";
        this.stdoutPath_ = "";
        this.stderrPath_ = "";
        this.c();
    }
    
    private void c() {
        try {
            if (CommandLine.access$2200()) {
                this.d();
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
        Label_0044: {
            try {
                super.clear();
                if (this.envBuilder_ == null) {
                    this.env_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    break Label_0044;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            this.envBuilder_.clear();
        }
        this.exePath_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.workingDir_ = "";
        this.bitField0_ &= 0xFFFFFFFB;
        this.param_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= 0xFFFFFFF7;
        this.stdinPath_ = "";
        this.bitField0_ &= 0xFFFFFFEF;
        this.stdoutPath_ = "";
        this.bitField0_ &= 0xFFFFFFDF;
        this.stderrPath_ = "";
        this.bitField0_ &= 0xFFFFFFBF;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Model.access$1700();
    }
    
    public CommandLine getDefaultInstanceForType() {
        return CommandLine.getDefaultInstance();
    }
    
    public CommandLine build() {
        final CommandLine buildPartial = this.buildPartial();
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
    
    public CommandLine buildPartial() {
        final CommandLine commandLine = new CommandLine((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        Label_0094: {
            Label_0082: {
                Label_0041: {
                    try {
                        if (this.envBuilder_ != null) {
                            break Label_0082;
                        }
                        final Builder builder = this;
                        final int n2 = builder.bitField0_;
                        final boolean b = true;
                        final boolean b2 = (n2 & (b ? 1 : 0)) != 0x0;
                        final boolean b3 = true;
                        if (b2 == b3) {
                            break Label_0041;
                        }
                        break Label_0041;
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    try {
                        final Builder builder = this;
                        final int n2 = builder.bitField0_;
                        final boolean b = true;
                        final boolean b2 = (n2 & (b ? 1 : 0)) != 0x0;
                        final boolean b3 = true;
                        if (b2 == b3) {
                            this.env_ = Collections.unmodifiableList((List<? extends EnvParam>)this.env_);
                            this.bitField0_ &= 0xFFFFFFFE;
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                CommandLine.access$2402(commandLine, this.env_);
                break Label_0094;
            }
            CommandLine.access$2402(commandLine, this.envBuilder_.build());
        }
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x1;
        }
        CommandLine.access$2502(commandLine, this.exePath_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x2;
        }
        try {
            CommandLine.access$2602(commandLine, this.workingDir_);
            if ((this.bitField0_ & 0x8) == 0x8) {
                this.param_ = (LazyStringList)new UnmodifiableLazyStringList(this.param_);
                this.bitField0_ &= 0xFFFFFFF7;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        CommandLine.access$2702(commandLine, this.param_);
        if ((bitField0_ & 0x10) == 0x10) {
            n |= 0x4;
        }
        CommandLine.access$2802(commandLine, this.stdinPath_);
        if ((bitField0_ & 0x20) == 0x20) {
            n |= 0x8;
        }
        CommandLine.access$2902(commandLine, this.stdoutPath_);
        if ((bitField0_ & 0x40) == 0x40) {
            n |= 0x10;
        }
        CommandLine.access$3002(commandLine, this.stderrPath_);
        CommandLine.access$3102(commandLine, n);
        this.onBuilt();
        return commandLine;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof CommandLine) {
                return this.mergeFrom((CommandLine)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final CommandLine p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;
        //     4: if_acmpne       13
        //     7: aload_0        
        //     8: areturn        
        //     9: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    12: athrow         
        //    13: aload_0        
        //    14: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //    17: ifnonnull       109
        //    20: aload_1        
        //    21: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
        //    24: invokeinterface java/util/List.isEmpty:()Z
        //    29: ifne            213
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    38: athrow         
        //    39: aload_0        
        //    40: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.env_:Ljava/util/List;
        //    43: invokeinterface java/util/List.isEmpty:()Z
        //    48: ifeq            84
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    57: athrow         
        //    58: aload_0        
        //    59: aload_1        
        //    60: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
        //    63: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.env_:Ljava/util/List;
        //    66: aload_0        
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //    71: bipush          -2
        //    73: iand           
        //    74: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //    77: goto            102
        //    80: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    83: athrow         
        //    84: aload_0        
        //    85: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.e:()V
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.env_:Ljava/util/List;
        //    92: aload_1        
        //    93: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
        //    96: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   101: pop            
        //   102: aload_0        
        //   103: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
        //   106: goto            213
        //   109: aload_1        
        //   110: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
        //   113: invokeinterface java/util/List.isEmpty:()Z
        //   118: ifne            213
        //   121: aload_0        
        //   122: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   125: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.isEmpty:()Z
        //   128: ifeq            201
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   137: athrow         
        //   138: aload_0        
        //   139: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   142: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.dispose:()V
        //   145: aload_0        
        //   146: aconst_null    
        //   147: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   150: aload_0        
        //   151: aload_1        
        //   152: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
        //   155: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.env_:Ljava/util/List;
        //   158: aload_0        
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   163: bipush          -2
        //   165: iand           
        //   166: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   169: aload_0        
        //   170: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$3200:()Z
        //   173: ifeq            194
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   182: athrow         
        //   183: aload_0        
        //   184: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.d:()Lcom/google/protobuf/RepeatedFieldBuilder;
        //   187: goto            195
        //   190: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   193: athrow         
        //   194: aconst_null    
        //   195: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   198: goto            213
        //   201: aload_0        
        //   202: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
        //   205: aload_1        
        //   206: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
        //   209: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.addAllMessages:(Ljava/lang/Iterable;)Lcom/google/protobuf/RepeatedFieldBuilder;
        //   212: pop            
        //   213: aload_1        
        //   214: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasExePath:()Z
        //   217: ifeq            249
        //   220: aload_0        
        //   221: dup            
        //   222: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   225: iconst_2       
        //   226: ior            
        //   227: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   230: aload_0        
        //   231: aload_1        
        //   232: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2500:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
        //   235: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.exePath_:Ljava/lang/Object;
        //   238: aload_0        
        //   239: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   248: athrow         
        //   249: aload_1        
        //   250: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasWorkingDir:()Z
        //   253: ifeq            285
        //   256: aload_0        
        //   257: dup            
        //   258: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   261: iconst_4       
        //   262: ior            
        //   263: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   266: aload_0        
        //   267: aload_1        
        //   268: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2600:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
        //   271: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.workingDir_:Ljava/lang/Object;
        //   274: aload_0        
        //   275: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   284: athrow         
        //   285: aload_1        
        //   286: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Lcom/google/protobuf/LazyStringList;
        //   289: invokeinterface com/google/protobuf/LazyStringList.isEmpty:()Z
        //   294: ifne            364
        //   297: aload_0        
        //   298: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.param_:Lcom/google/protobuf/LazyStringList;
        //   301: invokeinterface com/google/protobuf/LazyStringList.isEmpty:()Z
        //   306: ifeq            342
        //   309: goto            316
        //   312: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   315: athrow         
        //   316: aload_0        
        //   317: aload_1        
        //   318: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Lcom/google/protobuf/LazyStringList;
        //   321: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.param_:Lcom/google/protobuf/LazyStringList;
        //   324: aload_0        
        //   325: aload_0        
        //   326: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   329: bipush          -9
        //   331: iand           
        //   332: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   335: goto            360
        //   338: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   341: athrow         
        //   342: aload_0        
        //   343: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.a:()V
        //   346: aload_0        
        //   347: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.param_:Lcom/google/protobuf/LazyStringList;
        //   350: aload_1        
        //   351: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Lcom/google/protobuf/LazyStringList;
        //   354: invokeinterface com/google/protobuf/LazyStringList.addAll:(Ljava/util/Collection;)Z
        //   359: pop            
        //   360: aload_0        
        //   361: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
        //   364: aload_1        
        //   365: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasStdinPath:()Z
        //   368: ifeq            401
        //   371: aload_0        
        //   372: dup            
        //   373: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   376: bipush          16
        //   378: ior            
        //   379: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   382: aload_0        
        //   383: aload_1        
        //   384: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
        //   387: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.stdinPath_:Ljava/lang/Object;
        //   390: aload_0        
        //   391: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
        //   394: goto            401
        //   397: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   400: athrow         
        //   401: aload_1        
        //   402: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasStdoutPath:()Z
        //   405: ifeq            438
        //   408: aload_0        
        //   409: dup            
        //   410: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   413: bipush          32
        //   415: ior            
        //   416: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   419: aload_0        
        //   420: aload_1        
        //   421: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2900:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
        //   424: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.stdoutPath_:Ljava/lang/Object;
        //   427: aload_0        
        //   428: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
        //   431: goto            438
        //   434: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   437: athrow         
        //   438: aload_1        
        //   439: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasStderrPath:()Z
        //   442: ifeq            475
        //   445: aload_0        
        //   446: dup            
        //   447: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   450: bipush          64
        //   452: ior            
        //   453: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
        //   456: aload_0        
        //   457: aload_1        
        //   458: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$3000:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
        //   461: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.stderrPath_:Ljava/lang/Object;
        //   464: aload_0        
        //   465: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
        //   468: goto            475
        //   471: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   474: athrow         
        //   475: aload_0        
        //   476: aload_1        
        //   477: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.getUnknownFields:()Lcom/google/protobuf/UnknownFieldSet;
        //   480: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.mergeUnknownFields:(Lcom/google/protobuf/UnknownFieldSet;)Lcom/google/protobuf/GeneratedMessage$Builder;
        //   483: pop            
        //   484: aload_0        
        //   485: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      9      9      13     Ljava/lang/NullPointerException;
        //  13     32     35     39     Ljava/lang/NullPointerException;
        //  20     51     54     58     Ljava/lang/NullPointerException;
        //  39     80     80     84     Ljava/lang/NullPointerException;
        //  109    131    134    138    Ljava/lang/NullPointerException;
        //  121    176    179    183    Ljava/lang/NullPointerException;
        //  138    190    190    194    Ljava/lang/NullPointerException;
        //  213    242    245    249    Ljava/lang/NullPointerException;
        //  249    278    281    285    Ljava/lang/NullPointerException;
        //  285    309    312    316    Ljava/lang/NullPointerException;
        //  297    338    338    342    Ljava/lang/NullPointerException;
        //  364    394    397    401    Ljava/lang/NullPointerException;
        //  401    431    434    438    Ljava/lang/NullPointerException;
        //  438    468    471    475    Ljava/lang/NullPointerException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0039:
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
            if (!this.hasExePath()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasWorkingDir()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        int n = 0;
        while (true) {
            Label_0060: {
                try {
                    if (n >= this.getEnvCount()) {
                        break;
                    }
                    final Builder builder = this;
                    final int n2 = n;
                    final EnvParam envParam = builder.getEnv(n2);
                    final boolean b = envParam.isInitialized();
                    if (!b) {
                        return false;
                    }
                    break Label_0060;
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                try {
                    final Builder builder = this;
                    final int n2 = n;
                    final EnvParam envParam = builder.getEnv(n2);
                    final boolean b = envParam.isInitialized();
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
        CommandLine commandLine = null;
        try {
            commandLine = (CommandLine)CommandLine.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            commandLine = (CommandLine)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (commandLine != null) {
                    this.mergeFrom(commandLine);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    private void e() {
        try {
            if ((this.bitField0_ & 0x1) != 0x1) {
                this.env_ = new ArrayList<EnvParam>(this.env_);
                this.bitField0_ |= 0x1;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
    }
    
    public List<EnvParam> getEnvList() {
        try {
            if (this.envBuilder_ == null) {
                return Collections.unmodifiableList((List<? extends EnvParam>)this.env_);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (List<EnvParam>)this.envBuilder_.getMessageList();
    }
    
    public int getEnvCount() {
        try {
            if (this.envBuilder_ == null) {
                return this.env_.size();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return this.envBuilder_.getCount();
    }
    
    public EnvParam getEnv(final int n) {
        try {
            if (this.envBuilder_ == null) {
                return this.env_.get(n);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (EnvParam)this.envBuilder_.getMessage(n);
    }
    
    public Builder setEnv(final int n, final EnvParam envParam) {
        Label_0053: {
            Label_0018: {
                try {
                    if (this.envBuilder_ != null) {
                        break Label_0053;
                    }
                    final EnvParam envParam2 = envParam;
                    if (envParam2 == null) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    final EnvParam envParam2 = envParam;
                    if (envParam2 == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.e();
            this.env_.set(n, envParam);
            this.onChanged();
            return this;
        }
        this.envBuilder_.setMessage(n, (GeneratedMessage)envParam);
        return this;
    }
    
    public Builder setEnv(final int n, final EnvParam.Builder builder) {
        try {
            if (this.envBuilder_ == null) {
                this.e();
                this.env_.set(n, builder.build());
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.envBuilder_.setMessage(n, (GeneratedMessage)builder.build());
        return this;
    }
    
    public Builder addEnv(final EnvParam envParam) {
        Label_0052: {
            Label_0018: {
                try {
                    if (this.envBuilder_ != null) {
                        break Label_0052;
                    }
                    final EnvParam envParam2 = envParam;
                    if (envParam2 == null) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    final EnvParam envParam2 = envParam;
                    if (envParam2 == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.e();
            this.env_.add(envParam);
            this.onChanged();
            return this;
        }
        this.envBuilder_.addMessage((GeneratedMessage)envParam);
        return this;
    }
    
    public Builder addEnv(final int n, final EnvParam envParam) {
        Label_0052: {
            Label_0018: {
                try {
                    if (this.envBuilder_ != null) {
                        break Label_0052;
                    }
                    final EnvParam envParam2 = envParam;
                    if (envParam2 == null) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    final EnvParam envParam2 = envParam;
                    if (envParam2 == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            this.e();
            this.env_.add(n, envParam);
            this.onChanged();
            return this;
        }
        this.envBuilder_.addMessage(n, (GeneratedMessage)envParam);
        return this;
    }
    
    public Builder addEnv(final EnvParam.Builder builder) {
        try {
            if (this.envBuilder_ == null) {
                this.e();
                this.env_.add(builder.build());
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.envBuilder_.addMessage((GeneratedMessage)builder.build());
        return this;
    }
    
    public Builder addEnv(final int n, final EnvParam.Builder builder) {
        try {
            if (this.envBuilder_ == null) {
                this.e();
                this.env_.add(n, builder.build());
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.envBuilder_.addMessage(n, (GeneratedMessage)builder.build());
        return this;
    }
    
    public Builder addAllEnv(final Iterable<? extends EnvParam> iterable) {
        try {
            if (this.envBuilder_ == null) {
                this.e();
                GeneratedMessage.Builder.addAll((Iterable)iterable, (Collection)this.env_);
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.envBuilder_.addAllMessages((Iterable)iterable);
        return this;
    }
    
    public Builder clearEnv() {
        try {
            if (this.envBuilder_ == null) {
                this.env_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.envBuilder_.clear();
        return this;
    }
    
    public Builder removeEnv(final int n) {
        try {
            if (this.envBuilder_ == null) {
                this.e();
                this.env_.remove(n);
                this.onChanged();
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.envBuilder_.remove(n);
        return this;
    }
    
    public EnvParam.Builder getEnvBuilder(final int n) {
        return (EnvParam.Builder)this.d().getBuilder(n);
    }
    
    public EnvParamOrBuilder getEnvOrBuilder(final int n) {
        try {
            if (this.envBuilder_ == null) {
                return this.env_.get(n);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (EnvParamOrBuilder)this.envBuilder_.getMessageOrBuilder(n);
    }
    
    public List<? extends EnvParamOrBuilder> getEnvOrBuilderList() {
        try {
            if (this.envBuilder_ != null) {
                return (List<? extends EnvParamOrBuilder>)this.envBuilder_.getMessageOrBuilderList();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return Collections.unmodifiableList((List<? extends EnvParamOrBuilder>)this.env_);
    }
    
    public EnvParam.Builder addEnvBuilder() {
        return (EnvParam.Builder)this.d().addBuilder((GeneratedMessage)EnvParam.getDefaultInstance());
    }
    
    public EnvParam.Builder addEnvBuilder(final int n) {
        return (EnvParam.Builder)this.d().addBuilder(n, (GeneratedMessage)EnvParam.getDefaultInstance());
    }
    
    public List<EnvParam.Builder> getEnvBuilderList() {
        return (List<EnvParam.Builder>)this.d().getBuilderList();
    }
    
    private RepeatedFieldBuilder<EnvParam, EnvParam.Builder, EnvParamOrBuilder> d() {
        Builder builder = null;
        RepeatedFieldBuilder envBuilder_ = null;
        List<EnvParam> list = null;
        boolean b4 = false;
        Label_0042: {
            Label_0033: {
                try {
                    if (this.envBuilder_ != null) {
                        return this.envBuilder_;
                    }
                    builder = this;
                    envBuilder_ = new(com.google.protobuf.RepeatedFieldBuilder.class);
                    final Builder builder2 = this;
                    list = builder2.env_;
                    final Builder builder3 = this;
                    final int n = builder3.bitField0_;
                    final boolean b = true;
                    final boolean b2 = (n & (b ? 1 : 0)) != 0x0;
                    final boolean b3 = true;
                    if (b2 == b3) {
                        break Label_0033;
                    }
                    break Label_0033;
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    builder = this;
                    envBuilder_ = new(com.google.protobuf.RepeatedFieldBuilder.class);
                    final Builder builder2 = this;
                    list = builder2.env_;
                    final Builder builder3 = this;
                    final int n = builder3.bitField0_;
                    final boolean b = true;
                    final boolean b2 = (n & (b ? 1 : 0)) != 0x0;
                    final boolean b3 = true;
                    if (b2 == b3) {
                        b4 = true;
                        break Label_0042;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            b4 = false;
        }
        new RepeatedFieldBuilder((List)list, b4, this.getParentForChildren(), this.isClean());
        builder.envBuilder_ = (RepeatedFieldBuilder<EnvParam, EnvParam.Builder, EnvParamOrBuilder>)envBuilder_;
        this.env_ = null;
        return this.envBuilder_;
    }
    
    public boolean hasExePath() {
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
    
    public String getExePath() {
        final Object exePath_ = this.exePath_;
        if (!(exePath_ instanceof String)) {
            return (String)(this.exePath_ = ((ByteString)exePath_).toStringUtf8());
        }
        return (String)exePath_;
    }
    
    public ByteString getExePathBytes() {
        final Object exePath_ = this.exePath_;
        if (exePath_ instanceof String) {
            return (ByteString)(this.exePath_ = ByteString.copyFromUtf8((String)exePath_));
        }
        return (ByteString)exePath_;
    }
    
    public Builder setExePath(final String exePath_) {
        try {
            if (exePath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.exePath_ = exePath_;
        this.onChanged();
        return this;
    }
    
    public Builder clearExePath() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.exePath_ = CommandLine.getDefaultInstance().getExePath();
        this.onChanged();
        return this;
    }
    
    public Builder setExePathBytes(final ByteString exePath_) {
        try {
            if (exePath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.exePath_ = exePath_;
        this.onChanged();
        return this;
    }
    
    public boolean hasWorkingDir() {
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
        this.bitField0_ |= 0x4;
        this.workingDir_ = workingDir_;
        this.onChanged();
        return this;
    }
    
    public Builder clearWorkingDir() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.workingDir_ = CommandLine.getDefaultInstance().getWorkingDir();
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
        this.bitField0_ |= 0x4;
        this.workingDir_ = workingDir_;
        this.onChanged();
        return this;
    }
    
    private void a() {
        try {
            if ((this.bitField0_ & 0x8) != 0x8) {
                this.param_ = (LazyStringList)new LazyStringArrayList(this.param_);
                this.bitField0_ |= 0x8;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
    }
    
    public List<String> getParamList() {
        return Collections.unmodifiableList((List<? extends String>)this.param_);
    }
    
    public int getParamCount() {
        return this.param_.size();
    }
    
    public String getParam(final int n) {
        return (String)this.param_.get(n);
    }
    
    public ByteString getParamBytes(final int n) {
        return this.param_.getByteString(n);
    }
    
    public Builder setParam(final int n, final String s) {
        try {
            if (s == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.a();
        this.param_.set(n, (Object)s);
        this.onChanged();
        return this;
    }
    
    public Builder addParam(final String s) {
        try {
            if (s == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.a();
        this.param_.add((Object)s);
        this.onChanged();
        return this;
    }
    
    public Builder addAllParam(final Iterable<String> iterable) {
        this.a();
        GeneratedMessage.Builder.addAll((Iterable)iterable, (Collection)this.param_);
        this.onChanged();
        return this;
    }
    
    public Builder clearParam() {
        this.param_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= 0xFFFFFFF7;
        this.onChanged();
        return this;
    }
    
    public Builder addParamBytes(final ByteString byteString) {
        try {
            if (byteString == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.a();
        this.param_.add(byteString);
        this.onChanged();
        return this;
    }
    
    public boolean hasStdinPath() {
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getStdinPath() {
        final Object stdinPath_ = this.stdinPath_;
        if (!(stdinPath_ instanceof String)) {
            return (String)(this.stdinPath_ = ((ByteString)stdinPath_).toStringUtf8());
        }
        return (String)stdinPath_;
    }
    
    public ByteString getStdinPathBytes() {
        final Object stdinPath_ = this.stdinPath_;
        if (stdinPath_ instanceof String) {
            return (ByteString)(this.stdinPath_ = ByteString.copyFromUtf8((String)stdinPath_));
        }
        return (ByteString)stdinPath_;
    }
    
    public Builder setStdinPath(final String stdinPath_) {
        try {
            if (stdinPath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x10;
        this.stdinPath_ = stdinPath_;
        this.onChanged();
        return this;
    }
    
    public Builder clearStdinPath() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.stdinPath_ = CommandLine.getDefaultInstance().getStdinPath();
        this.onChanged();
        return this;
    }
    
    public Builder setStdinPathBytes(final ByteString stdinPath_) {
        try {
            if (stdinPath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x10;
        this.stdinPath_ = stdinPath_;
        this.onChanged();
        return this;
    }
    
    public boolean hasStdoutPath() {
        try {
            if ((this.bitField0_ & 0x20) == 0x20) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getStdoutPath() {
        final Object stdoutPath_ = this.stdoutPath_;
        if (!(stdoutPath_ instanceof String)) {
            return (String)(this.stdoutPath_ = ((ByteString)stdoutPath_).toStringUtf8());
        }
        return (String)stdoutPath_;
    }
    
    public ByteString getStdoutPathBytes() {
        final Object stdoutPath_ = this.stdoutPath_;
        if (stdoutPath_ instanceof String) {
            return (ByteString)(this.stdoutPath_ = ByteString.copyFromUtf8((String)stdoutPath_));
        }
        return (ByteString)stdoutPath_;
    }
    
    public Builder setStdoutPath(final String stdoutPath_) {
        try {
            if (stdoutPath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x20;
        this.stdoutPath_ = stdoutPath_;
        this.onChanged();
        return this;
    }
    
    public Builder clearStdoutPath() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.stdoutPath_ = CommandLine.getDefaultInstance().getStdoutPath();
        this.onChanged();
        return this;
    }
    
    public Builder setStdoutPathBytes(final ByteString stdoutPath_) {
        try {
            if (stdoutPath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x20;
        this.stdoutPath_ = stdoutPath_;
        this.onChanged();
        return this;
    }
    
    public boolean hasStderrPath() {
        try {
            if ((this.bitField0_ & 0x40) == 0x40) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getStderrPath() {
        final Object stderrPath_ = this.stderrPath_;
        if (!(stderrPath_ instanceof String)) {
            return (String)(this.stderrPath_ = ((ByteString)stderrPath_).toStringUtf8());
        }
        return (String)stderrPath_;
    }
    
    public ByteString getStderrPathBytes() {
        final Object stderrPath_ = this.stderrPath_;
        if (stderrPath_ instanceof String) {
            return (ByteString)(this.stderrPath_ = ByteString.copyFromUtf8((String)stderrPath_));
        }
        return (ByteString)stderrPath_;
    }
    
    public Builder setStderrPath(final String stderrPath_) {
        try {
            if (stderrPath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x40;
        this.stderrPath_ = stderrPath_;
        this.onChanged();
        return this;
    }
    
    public Builder clearStderrPath() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.stderrPath_ = CommandLine.getDefaultInstance().getStderrPath();
        this.onChanged();
        return this;
    }
    
    public Builder setStderrPathBytes(final ByteString stderrPath_) {
        try {
            if (stderrPath_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x40;
        this.stderrPath_ = stderrPath_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
