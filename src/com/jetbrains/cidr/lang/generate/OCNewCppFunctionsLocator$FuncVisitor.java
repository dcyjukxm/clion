// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCStructLike;

private static class FuncVisitor implements Visitor
{
    private final OCStructLike myStruct;
    private final OCFunctionSymbol myFunc;
    private Pair<OCFunctionDeclaration, OCVisibility> myBestFunctionToInsertAfter;
    
    private FuncVisitor(final OCStructLike myStruct, final OCFunctionSymbol myFunc) {
        this.myStruct = myStruct;
        this.myFunc = myFunc;
    }
    
    @Override
    public void visitFunction(@NotNull final OCFunctionDeclaration p0, @NotNull final OCVisibility p1) {
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
        //    18: ldc             "func"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "visitFunction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "visibility"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "visitFunction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.myFunc:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    92: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    95: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.NULL:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    98: invokestatic    com/intellij/util/ObjectUtils.notNull:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   101: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   104: astore_3       
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.myBestFunctionToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   109: ifnonnull       128
        //   112: aload_0        
        //   113: aload_1        
        //   114: aload_2        
        //   115: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   118: putfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.myBestFunctionToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   121: goto            247
        //   124: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload_0        
        //   129: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.myBestFunctionToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   132: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   135: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   138: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   141: aload_3        
        //   142: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   145: isub           
        //   146: invokestatic    java/lang/Math.abs:(I)I
        //   149: istore          4
        //   151: aload_2        
        //   152: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   155: aload_3        
        //   156: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   159: isub           
        //   160: invokestatic    java/lang/Math.abs:(I)I
        //   163: istore          5
        //   165: aload_1        
        //   166: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   171: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   174: istore          6
        //   176: aload_0        
        //   177: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.myBestFunctionToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   180: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   183: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   186: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   191: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   194: istore          7
        //   196: iload           4
        //   198: iload           5
        //   200: if_icmpgt       231
        //   203: iload           4
        //   205: iload           5
        //   207: if_icmpne       247
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: iload           6
        //   219: iload           7
        //   221: if_icmple       247
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload_0        
        //   232: aload_1        
        //   233: aload_2        
        //   234: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   237: putfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.myBestFunctionToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$FuncVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  105    124    124    128    Ljava/lang/IllegalArgumentException;
        //  196    210    213    217    Ljava/lang/IllegalArgumentException;
        //  203    224    227    231    Ljava/lang/IllegalArgumentException;
        //  217    240    243    247    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0217:
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
    
    @Override
    public int getResult() {
        try {
            if (this.myBestFunctionToInsertAfter != null) {
                return ((OCFunctionDeclaration)this.myBestFunctionToInsertAfter.first).getTextRange().getEndOffset();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCNewCppFunctionsLocator.getValidLocationRange(this.myStruct).getStartOffset();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
