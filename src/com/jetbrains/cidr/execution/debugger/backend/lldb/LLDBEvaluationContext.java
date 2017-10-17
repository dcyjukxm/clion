// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import com.intellij.openapi.util.Expirable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;

public class LLDBEvaluationContext extends EvaluationContext
{
    private final DebuggerDriver myDriver;
    
    public LLDBEvaluationContext(final DebuggerDriver myDriver, final Expirable expirable, final CidrStackFrame cidrStackFrame) {
        super(myDriver, expirable, cidrStackFrame);
        this.myDriver = myDriver;
    }
    
    @NotNull
    @Override
    public String castIDToNumber(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext", "castIDToNumber"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext", "castIDToNumber"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        String cast;
        try {
            cast = EvaluationContext.cast(s, "long");
            if (cast == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext", "castIDToNumber"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return cast;
    }
    
    @NotNull
    @Override
    public String convertToRValue(@NotNull final LLValueData p0, @NotNull final Pair<LLValue, String> p1) throws DebuggerCommandException, ExecutionException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "lValueData"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "convertToRValue"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "rValuePair"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "convertToRValue"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_2        
        //    90: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //    93: checkcast       Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;
        //    96: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.getData:(Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;)Lcom/jetbrains/cidr/execution/debugger/backend/LLValueData;
        //    99: astore_3       
        //   100: aload_3        
        //   101: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValueData.isNullPointer:()Z
        //   104: ifeq            155
        //   107: ldc             "((id)0)"
        //   109: dup            
        //   110: ifnonnull       154
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   119: athrow         
        //   120: new             Ljava/lang/IllegalStateException;
        //   123: dup            
        //   124: ldc             "@NotNull method %s.%s must not return null"
        //   126: ldc             2
        //   128: anewarray       Ljava/lang/Object;
        //   131: dup            
        //   132: ldc             0
        //   134: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext"
        //   136: aastore        
        //   137: dup            
        //   138: ldc             1
        //   140: ldc             "convertToRValue"
        //   142: aastore        
        //   143: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   146: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   149: athrow         
        //   150: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   153: athrow         
        //   154: areturn        
        //   155: aload_1        
        //   156: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValueData.isPointer:()Z
        //   159: ifeq            212
        //   162: aload_2        
        //   163: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   166: checkcast       Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;
        //   169: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValue.getType:()Ljava/lang/String;
        //   172: ldc             "int"
        //   174: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   177: ifeq            212
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   186: athrow         
        //   187: aload_3        
        //   188: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/LLValueData.intValue:()J
        //   191: lconst_0       
        //   192: lcmp           
        //   193: ifne            212
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   202: athrow         
        //   203: ldc             "0"
        //   205: goto            219
        //   208: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   211: athrow         
        //   212: aload_2        
        //   213: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   216: checkcast       Ljava/lang/String;
        //   219: dup            
        //   220: ifnonnull       257
        //   223: new             Ljava/lang/IllegalStateException;
        //   226: dup            
        //   227: ldc             "@NotNull method %s.%s must not return null"
        //   229: ldc             2
        //   231: anewarray       Ljava/lang/Object;
        //   234: dup            
        //   235: ldc             0
        //   237: ldc             "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext"
        //   239: aastore        
        //   240: dup            
        //   241: ldc             1
        //   243: ldc             "convertToRValue"
        //   245: aastore        
        //   246: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   249: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   252: athrow         
        //   253: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   256: athrow         
        //   257: areturn        
        //    Exceptions:
        //  throws com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException
        //  throws com.intellij.execution.ExecutionException
        //    Signature:
        //  (Lcom/jetbrains/cidr/execution/debugger/backend/LLValueData;Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/execution/debugger/backend/LLValue;Ljava/lang/String;>;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                    
        //  -----  -----  -----  -----  ------------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  44     84     84     88     Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  100    113    116    120    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  107    150    150    154    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  155    180    183    187    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  162    196    199    203    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  187    208    208    212    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //  219    253    253    257    Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0187:
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
    
    public long getValueAddress(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBEvaluationContext", "getValueAddress"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        return ((LLDBDriver)this.myDriver).getValueAddress(llValue);
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
