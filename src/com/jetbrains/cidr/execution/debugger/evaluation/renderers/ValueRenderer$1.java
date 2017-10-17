// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.intellij.xdebugger.frame.XFullValueEvaluator;
import com.intellij.openapi.util.Pair;

class ValueRenderer$1 implements CachedDebuggerResult.NotNullCalculator<Pair<String, XFullValueEvaluator>> {
    @NotNull
    @Override
    public Pair<String, XFullValueEvaluator> calculate() throws ExecutionException, DebuggerCommandException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
        //     4: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.val$context:Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;
        //    11: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getVarData:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)Lcom/jetbrains/cidr/execution/debugger/backend/LLValueData;
        //    14: astore_1       
        //    15: aload_1        
        //    16: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValueData.isNullPointer:()Z
        //    19: ifeq            150
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
        //    26: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getValue:()Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //    29: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.getVar:()Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;
        //    32: astore_2       
        //    33: aload_2        
        //    34: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValue.getTypeClass:()Lcom/jetbrains/cidr/execution/debugger/backend/LLValue$TypeClass;
        //    37: getstatic       com/jetbrains/cidr/execution/debugger/backend/LLValue$TypeClass.OBJC_POINTER:Lcom/jetbrains/cidr/execution/debugger/backend/LLValue$TypeClass;
        //    40: if_acmpeq       82
        //    43: ldc             "id"
        //    45: aload_2        
        //    46: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValue.getType:()Ljava/lang/String;
        //    49: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    52: ifeq            90
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //    61: athrow         
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
        //    66: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.getValue:()Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //    69: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.isObjectiveCContext:()Z
        //    72: ifeq            90
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //    81: athrow         
        //    82: iconst_1       
        //    83: goto            91
        //    86: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //    89: athrow         
        //    90: iconst_0       
        //    91: istore_3       
        //    92: iload_3        
        //    93: ifeq            105
        //    96: ldc             "nil"
        //    98: goto            107
        //   101: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   104: athrow         
        //   105: ldc             "NULL"
        //   107: aconst_null    
        //   108: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   111: dup            
        //   112: ifnonnull       149
        //   115: new             Ljava/lang/IllegalStateException;
        //   118: dup            
        //   119: ldc             "@NotNull method %s.%s must not return null"
        //   121: ldc             2
        //   123: anewarray       Ljava/lang/Object;
        //   126: dup            
        //   127: ldc             0
        //   129: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1"
        //   131: aastore        
        //   132: dup            
        //   133: ldc             1
        //   135: ldc             "calculate"
        //   137: aastore        
        //   138: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   141: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   144: athrow         
        //   145: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   148: athrow         
        //   149: areturn        
        //   150: aload_0        
        //   151: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
        //   154: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.myValue:Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue;
        //   157: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/CidrPhysicalValue.isSwiftVoid:()Z
        //   160: ifeq            215
        //   163: ldc             ""
        //   165: aconst_null    
        //   166: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   169: dup            
        //   170: ifnonnull       214
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   179: athrow         
        //   180: new             Ljava/lang/IllegalStateException;
        //   183: dup            
        //   184: ldc             "@NotNull method %s.%s must not return null"
        //   186: ldc             2
        //   188: anewarray       Ljava/lang/Object;
        //   191: dup            
        //   192: ldc             0
        //   194: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1"
        //   196: aastore        
        //   197: dup            
        //   198: ldc             1
        //   200: ldc             "calculate"
        //   202: aastore        
        //   203: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   206: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   209: athrow         
        //   210: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   213: athrow         
        //   214: areturn        
        //   215: aload_0        
        //   216: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.this$0:Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer;
        //   219: aload_0        
        //   220: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.val$context:Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;
        //   223: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer.doComputeValueAndEvaluator:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)Lcom/intellij/openapi/util/Pair;
        //   226: astore_2       
        //   227: aload_2        
        //   228: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   231: checkcast       Ljava/lang/String;
        //   234: ldc             "{...}"
        //   236: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   239: ifeq            255
        //   242: ldc             ""
        //   244: aconst_null    
        //   245: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   248: goto            256
        //   251: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   254: athrow         
        //   255: aload_2        
        //   256: dup            
        //   257: ifnonnull       294
        //   260: new             Ljava/lang/IllegalStateException;
        //   263: dup            
        //   264: ldc             "@NotNull method %s.%s must not return null"
        //   266: ldc             2
        //   268: anewarray       Ljava/lang/Object;
        //   271: dup            
        //   272: ldc             0
        //   274: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1"
        //   276: aastore        
        //   277: dup            
        //   278: ldc             1
        //   280: ldc             "calculate"
        //   282: aastore        
        //   283: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   286: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   289: athrow         
        //   290: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/ValueRenderer$1.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   293: athrow         
        //   294: areturn        
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //  throws com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException
        //    Signature:
        //  ()Lcom/intellij/openapi/util/Pair<Ljava/lang/String;Lcom/intellij/xdebugger/frame/XFullValueEvaluator;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  33     55     58     62     Lcom/intellij/execution/ExecutionException;
        //  43     75     78     82     Lcom/intellij/execution/ExecutionException;
        //  62     86     86     90     Lcom/intellij/execution/ExecutionException;
        //  92     101    101    105    Lcom/intellij/execution/ExecutionException;
        //  107    145    145    149    Lcom/intellij/execution/ExecutionException;
        //  150    173    176    180    Lcom/intellij/execution/ExecutionException;
        //  163    210    210    214    Lcom/intellij/execution/ExecutionException;
        //  227    251    251    255    Lcom/intellij/execution/ExecutionException;
        //  256    290    290    294    Lcom/intellij/execution/ExecutionException;
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
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}