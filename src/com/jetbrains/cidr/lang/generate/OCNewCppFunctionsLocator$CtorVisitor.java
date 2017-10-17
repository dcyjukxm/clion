// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCStructLike;

private static class CtorVisitor implements Visitor
{
    private final OCStructLike myStruct;
    private final OCFunctionSymbol myCtor;
    private Pair<OCFunctionDeclaration, OCVisibility> myBestConstructorToInsertAfter;
    private Pair<OCFunctionDeclaration, OCVisibility> myBestFunctionToInsertBefore;
    
    private CtorVisitor(final OCStructLike myStruct, final OCFunctionSymbol myCtor) {
        this.myStruct = myStruct;
        this.myCtor = myCtor;
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "visitFunction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "visitFunction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myCtor:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    92: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    95: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.NULL:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    98: invokestatic    com/intellij/util/ObjectUtils.notNull:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   101: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   104: astore_3       
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myCtor:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   109: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getName:()Ljava/lang/String;
        //   112: aload_1        
        //   113: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getName:()Ljava/lang/String;
        //   118: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   121: ifeq            276
        //   124: aload_0        
        //   125: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestConstructorToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   128: ifnonnull       154
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_0        
        //   139: aload_1        
        //   140: aload_2        
        //   141: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   144: putfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestConstructorToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   147: goto            418
        //   150: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_0        
        //   155: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestConstructorToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   158: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   161: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   164: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   167: aload_3        
        //   168: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   171: isub           
        //   172: invokestatic    java/lang/Math.abs:(I)I
        //   175: istore          4
        //   177: aload_2        
        //   178: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   181: aload_3        
        //   182: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   185: isub           
        //   186: invokestatic    java/lang/Math.abs:(I)I
        //   189: istore          5
        //   191: aload_1        
        //   192: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   197: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   200: istore          6
        //   202: aload_0        
        //   203: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestConstructorToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   206: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   209: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   212: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   217: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   220: istore          7
        //   222: iload           4
        //   224: iload           5
        //   226: if_icmpgt       257
        //   229: iload           4
        //   231: iload           5
        //   233: if_icmpne       273
        //   236: goto            243
        //   239: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: iload           6
        //   245: iload           7
        //   247: if_icmple       273
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: aload_0        
        //   258: aload_1        
        //   259: aload_2        
        //   260: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   263: putfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestConstructorToInsertAfter:Lcom/intellij/openapi/util/Pair;
        //   266: goto            273
        //   269: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: goto            418
        //   276: aload_0        
        //   277: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestFunctionToInsertBefore:Lcom/intellij/openapi/util/Pair;
        //   280: ifnonnull       299
        //   283: aload_0        
        //   284: aload_1        
        //   285: aload_2        
        //   286: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   289: putfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestFunctionToInsertBefore:Lcom/intellij/openapi/util/Pair;
        //   292: goto            418
        //   295: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload_0        
        //   300: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestFunctionToInsertBefore:Lcom/intellij/openapi/util/Pair;
        //   303: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   306: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   309: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   312: aload_3        
        //   313: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   316: isub           
        //   317: invokestatic    java/lang/Math.abs:(I)I
        //   320: istore          4
        //   322: aload_2        
        //   323: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   326: aload_3        
        //   327: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   330: isub           
        //   331: invokestatic    java/lang/Math.abs:(I)I
        //   334: istore          5
        //   336: aload_1        
        //   337: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   342: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   345: istore          6
        //   347: aload_0        
        //   348: getfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestFunctionToInsertBefore:Lcom/intellij/openapi/util/Pair;
        //   351: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   354: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   357: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   362: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   365: istore          7
        //   367: iload           4
        //   369: iload           5
        //   371: if_icmpgt       402
        //   374: iload           4
        //   376: iload           5
        //   378: if_icmpne       418
        //   381: goto            388
        //   384: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   387: athrow         
        //   388: iload           6
        //   390: iload           7
        //   392: if_icmpge       418
        //   395: goto            402
        //   398: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   401: athrow         
        //   402: aload_0        
        //   403: aload_1        
        //   404: aload_2        
        //   405: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   408: putfield        com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.myBestFunctionToInsertBefore:Lcom/intellij/openapi/util/Pair;
        //   411: goto            418
        //   414: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator$CtorVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   417: athrow         
        //   418: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  105    131    134    138    Ljava/lang/IllegalArgumentException;
        //  124    150    150    154    Ljava/lang/IllegalArgumentException;
        //  222    236    239    243    Ljava/lang/IllegalArgumentException;
        //  229    250    253    257    Ljava/lang/IllegalArgumentException;
        //  243    266    269    273    Ljava/lang/IllegalArgumentException;
        //  276    295    295    299    Ljava/lang/IllegalArgumentException;
        //  367    381    384    388    Ljava/lang/IllegalArgumentException;
        //  374    395    398    402    Ljava/lang/IllegalArgumentException;
        //  388    411    414    418    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0243:
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
        Label_0129: {
            try {
                if (this.myBestConstructorToInsertAfter == null) {
                    break Label_0129;
                }
                if (this.myBestFunctionToInsertBefore == null) {
                    return ((OCFunctionDeclaration)this.myBestConstructorToInsertAfter.first).getTextRange().getEndOffset();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCVisibility ocVisibility = (OCVisibility)ObjectUtils.notNull((Object)this.myCtor.getVisibility(), (Object)OCVisibility.NULL);
            final int abs = Math.abs(ocVisibility.ordinal() - ((OCVisibility)this.myBestConstructorToInsertAfter.second).ordinal());
            final int abs2 = Math.abs(ocVisibility.ordinal() - ((OCVisibility)this.myBestFunctionToInsertBefore.second).ordinal());
            try {
                if (abs2 < abs) {
                    return ((OCFunctionDeclaration)this.myBestFunctionToInsertBefore.first).getTextRange().getStartOffset();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return ((OCFunctionDeclaration)this.myBestConstructorToInsertAfter.first).getTextRange().getEndOffset();
            try {
                if (this.myBestFunctionToInsertBefore != null) {
                    return ((OCFunctionDeclaration)this.myBestFunctionToInsertBefore.first).getTextRange().getStartOffset();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return OCNewCppFunctionsLocator.getValidLocationRange(this.myStruct).getStartOffset();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
