// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValue;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;

public abstract class NSEnumerableValueRenderer extends NSContainerValueRenderer
{
    private boolean myInitialized;
    
    public NSEnumerableValueRenderer(@NotNull final CidrPhysicalValue cidrPhysicalValue) {
        if (cidrPhysicalValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer", "<init>"));
        }
        super(cidrPhysicalValue);
    }
    
    @Override
    protected boolean mayHaveChildrenViaChildrenCount() {
        return true;
    }
    
    @Override
    protected final void doComputeChildren(@NotNull final EvaluationContext p0, @NotNull final XCompositeNode p1) throws ExecutionException, DebuggerCommandException {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doComputeChildren"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
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
        //    62: ldc             "container"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "doComputeChildren"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.myInitialized:Z
        //    92: ifne            112
        //    95: aload_0        
        //    96: aload_1        
        //    97: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.willExpand:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)V
        //   100: aload_0        
        //   101: iconst_1       
        //   102: putfield        com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.myInitialized:Z
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   111: athrow         
        //   112: aload_0        
        //   113: aload_1        
        //   114: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.computeChildrenCount:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)Ljava/lang/Integer;
        //   117: astore_3       
        //   118: aload_3        
        //   119: ifnonnull       162
        //   122: new             Lcom/intellij/execution/ExecutionException;
        //   125: dup            
        //   126: new             Ljava/lang/StringBuilder;
        //   129: dup            
        //   130: invokespecial   java/lang/StringBuilder.<init>:()V
        //   133: ldc             "Internal error: "
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: ldc             Lcom/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer;.class
        //   140: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: ldc             " requires children count"
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   154: invokespecial   com/intellij/execution/ExecutionException.<init>:(Ljava/lang/String;)V
        //   157: athrow         
        //   158: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   161: athrow         
        //   162: aload_3        
        //   163: invokevirtual   java/lang/Integer.intValue:()I
        //   166: istore          4
        //   168: aload_0        
        //   169: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.getBatchSize:()I
        //   172: istore          5
        //   174: new             Ljava/util/ArrayList;
        //   177: dup            
        //   178: iload           5
        //   180: invokespecial   java/util/ArrayList.<init>:(I)V
        //   183: astore          6
        //   185: iconst_0       
        //   186: istore          7
        //   188: iload           7
        //   190: iload           5
        //   192: if_icmpge       267
        //   195: aload_0        
        //   196: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.getCurrentIndex:()I
        //   199: iload           4
        //   201: if_icmpge       267
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   210: athrow         
        //   211: aload_2        
        //   212: invokeinterface com/intellij/xdebugger/frame/XCompositeNode.isObsolete:()Z
        //   217: ifeq            232
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   226: athrow         
        //   227: return         
        //   228: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   231: athrow         
        //   232: aload_0        
        //   233: aload_1        
        //   234: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.nextChild:(Lcom/jetbrains/cidr/execution/debugger/evaluation/EvaluationContext;)Lcom/jetbrains/cidr/execution/debugger/evaluation/CidrValue;
        //   237: astore          8
        //   239: aload           8
        //   241: ifnonnull       251
        //   244: goto            267
        //   247: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   250: athrow         
        //   251: aload           6
        //   253: aload           8
        //   255: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   260: pop            
        //   261: iinc            7, 1
        //   264: goto            188
        //   267: aload           6
        //   269: aload_2        
        //   270: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/CidrValue.addAllTo:(Ljava/util/Collection;Lcom/intellij/xdebugger/frame/XCompositeNode;)V
        //   273: iload           4
        //   275: aload_0        
        //   276: invokevirtual   com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.getCurrentIndex:()I
        //   279: isub           
        //   280: istore          7
        //   282: iload           7
        //   284: ifle            302
        //   287: aload_2        
        //   288: iload           7
        //   290: invokeinterface com/intellij/xdebugger/frame/XCompositeNode.tooManyChildren:(I)V
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer.a:(Lcom/intellij/execution/ExecutionException;)Lcom/intellij/execution/ExecutionException;
        //   301: athrow         
        //   302: return         
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //  throws com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  0      40     40     44     Lcom/intellij/execution/ExecutionException;
        //  44     84     84     88     Lcom/intellij/execution/ExecutionException;
        //  88     105    108    112    Lcom/intellij/execution/ExecutionException;
        //  118    158    158    162    Lcom/intellij/execution/ExecutionException;
        //  188    204    207    211    Lcom/intellij/execution/ExecutionException;
        //  195    220    223    227    Lcom/intellij/execution/ExecutionException;
        //  211    228    228    232    Lcom/intellij/execution/ExecutionException;
        //  239    247    247    251    Lcom/intellij/execution/ExecutionException;
        //  282    295    298    302    Lcom/intellij/execution/ExecutionException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0211:
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
    
    @Nullable
    protected abstract CidrValue nextChild(@NotNull final EvaluationContext p0) throws ExecutionException, DebuggerCommandException;
    
    protected void willExpand(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSEnumerableValueRenderer", "willExpand"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}
