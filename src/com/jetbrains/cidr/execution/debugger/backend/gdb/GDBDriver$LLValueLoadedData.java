// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

private static class LLValueLoadedData
{
    @NotNull
    public final String id;
    @Nullable
    public final Integer childrenCount;
    public final boolean isDynamic;
    public final boolean hasDynamicChildren;
    public final boolean isMap;
    @NotNull
    public final LLValueData data;
    
    public LLValueLoadedData(@NotNull final String p0, @Nullable final Integer p1, final boolean p2, final boolean p3, final boolean p4, @NotNull final String p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       40
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "id"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "<init>"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: aload           6
        //    42: ifnonnull       81
        //    45: new             Ljava/lang/IllegalArgumentException;
        //    48: dup            
        //    49: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    51: ldc             3
        //    53: anewarray       Ljava/lang/Object;
        //    56: dup            
        //    57: ldc             0
        //    59: ldc             "value"
        //    61: aastore        
        //    62: dup            
        //    63: ldc             1
        //    65: ldc             "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData"
        //    67: aastore        
        //    68: dup            
        //    69: ldc             2
        //    71: ldc             "<init>"
        //    73: aastore        
        //    74: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    77: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    80: athrow         
        //    81: aload_0        
        //    82: invokespecial   java/lang/Object.<init>:()V
        //    85: aload_0        
        //    86: aload_1        
        //    87: putfield        com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.id:Ljava/lang/String;
        //    90: aload_0        
        //    91: aload_2        
        //    92: putfield        com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.childrenCount:Ljava/lang/Integer;
        //    95: aload_0        
        //    96: iload_3        
        //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.isDynamic:Z
        //   100: aload_0        
        //   101: iload           4
        //   103: putfield        com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.hasDynamicChildren:Z
        //   106: aload_0        
        //   107: iload           5
        //   109: putfield        com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.isMap:Z
        //   112: aload           6
        //   114: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver.getDescriptionFromValue:(Ljava/lang/String;)Lcom/intellij/openapi/util/Pair;
        //   117: astore          7
        //   119: aload           7
        //   121: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   124: checkcast       Ljava/lang/String;
        //   127: astore          6
        //   129: aload           7
        //   131: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   134: checkcast       Ljava/lang/String;
        //   137: astore          8
        //   139: aload           8
        //   141: ifnull          170
        //   144: aload           8
        //   146: invokevirtual   java/lang/String.length:()I
        //   149: sipush          1000
        //   152: if_icmplt       170
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: iconst_1       
        //   163: goto            171
        //   166: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: iconst_0       
        //   171: istore          9
        //   173: aload_2        
        //   174: ifnull          191
        //   177: aload_2        
        //   178: invokevirtual   java/lang/Integer.intValue:()I
        //   181: ifgt            203
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: iload           4
        //   193: ifeq            211
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: iconst_1       
        //   204: goto            212
        //   207: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: iconst_0       
        //   212: istore          10
        //   214: aload_0        
        //   215: new             Lcom/jetbrains/cidr/execution/debugger/backend/LLValueData;
        //   218: dup            
        //   219: aload           6
        //   221: aload           8
        //   223: iload           9
        //   225: iload           10
        //   227: iload_3        
        //   228: invokespecial   com/jetbrains/cidr/execution/debugger/backend/LLValueData.<init>:(Ljava/lang/String;Ljava/lang/String;ZZZ)V
        //   231: putfield        com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$LLValueLoadedData.data:Lcom/jetbrains/cidr/execution/debugger/backend/LLValueData;
        //   234: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  139    155    158    162    Ljava/lang/IllegalArgumentException;
        //  144    166    166    170    Ljava/lang/IllegalArgumentException;
        //  173    184    187    191    Ljava/lang/IllegalArgumentException;
        //  177    196    199    203    Ljava/lang/IllegalArgumentException;
        //  191    207    207    211    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0191:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public boolean mayHaveChildren() {
        return this.data.mayHaveChildren();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
