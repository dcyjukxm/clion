// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.intellij.openapi.util.Pair;
import com.intellij.util.ObjectUtils;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCStructLike;

public class OCNewCppFunctionsLocator
{
    @NotNull
    public static TextRange getValidLocationRange(@NotNull final OCStructLike p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "struct"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getValidLocationRange"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: invokestatic    java/util/Optional.empty:()Ljava/util/Optional;
        //    47: astore_1       
        //    48: invokestatic    java/util/Optional.empty:()Ljava/util/Optional;
        //    51: astore_2       
        //    52: aload_0        
        //    53: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    58: astore_3       
        //    59: aload_3        
        //    60: ifnull          132
        //    63: aload_3        
        //    64: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    67: astore          4
        //    69: aload           4
        //    71: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    74: if_acmpne       96
        //    77: aload_3        
        //    78: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    83: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //    86: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    89: invokestatic    java/util/Optional.of:(Ljava/lang/Object;)Ljava/util/Optional;
        //    92: astore_1       
        //    93: goto            122
        //    96: aload           4
        //    98: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   101: if_acmpne       122
        //   104: aload_3        
        //   105: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   110: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   113: iconst_1       
        //   114: iadd           
        //   115: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   118: invokestatic    java/util/Optional.of:(Ljava/lang/Object;)Ljava/util/Optional;
        //   121: astore_2       
        //   122: aload_3        
        //   123: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   128: astore_3       
        //   129: goto            59
        //   132: aload_1        
        //   133: aload_0        
        //   134: invokedynamic   get:(Lcom/jetbrains/cidr/lang/psi/OCStructLike;)Ljava/util/function/Supplier;
        //   139: invokevirtual   java/util/Optional.orElseGet:(Ljava/util/function/Supplier;)Ljava/lang/Object;
        //   142: checkcast       Ljava/lang/Integer;
        //   145: invokevirtual   java/lang/Integer.intValue:()I
        //   148: aload_2        
        //   149: aload_0        
        //   150: invokedynamic   get:(Lcom/jetbrains/cidr/lang/psi/OCStructLike;)Ljava/util/function/Supplier;
        //   155: invokevirtual   java/util/Optional.orElseGet:(Ljava/util/function/Supplier;)Ljava/lang/Object;
        //   158: checkcast       Ljava/lang/Integer;
        //   161: invokevirtual   java/lang/Integer.intValue:()I
        //   164: invokestatic    com/intellij/openapi/util/TextRange.create:(II)Lcom/intellij/openapi/util/TextRange;
        //   167: dup            
        //   168: ifnonnull       205
        //   171: new             Ljava/lang/IllegalStateException;
        //   174: dup            
        //   175: ldc             "@NotNull method %s.%s must not return null"
        //   177: ldc             2
        //   179: anewarray       Ljava/lang/Object;
        //   182: dup            
        //   183: ldc             0
        //   185: ldc             "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator"
        //   187: aastore        
        //   188: dup            
        //   189: ldc             1
        //   191: ldc             "getValidLocationRange"
        //   193: aastore        
        //   194: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   197: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   200: athrow         
        //   201: invokestatic    com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  132    201    201    205    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    public static int locateNewFunction(@NotNull final OCStructLike ocStructLike, @NotNull final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (ocStructLike == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "struct", "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator", "locateNewFunction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newFunction", "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator", "locateNewFunction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Visitor a = a(ocStructLike, ocFunctionSymbol);
        PsiElement psiElement = ocStructLike.getFirstChild();
        while (true) {
            Label_0152: {
                try {
                    if (psiElement == null) {
                        break;
                    }
                    if (!(psiElement instanceof OCFunctionDeclaration)) {
                        break Label_0152;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                a.visitFunction((OCFunctionDeclaration)psiElement, (OCVisibility)ObjectUtils.notNull((Object)OCVisibility.getVisibilityAtOffset((PsiElement)ocStructLike, psiElement.getTextOffset()), (Object)OCVisibility.NULL));
            }
            psiElement = psiElement.getNextSibling();
        }
        return a.getResult();
    }
    
    @NotNull
    private static Visitor a(@NotNull final OCStructLike ocStructLike, @NotNull final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (ocStructLike == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "struct", "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator", "getVisitor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "function", "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator", "getVisitor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        FuncVisitor funcVisitor = null;
        Label_0151: {
            CtorVisitor ctorVisitor = null;
            Label_0116: {
                try {
                    if (!ocFunctionSymbol.isCppConstructor()) {
                        break Label_0151;
                    }
                    final OCStructLike ocStructLike2 = ocStructLike;
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final Object object = null;
                    ctorVisitor = new CtorVisitor(ocStructLike2, ocFunctionSymbol2);
                    if (ctorVisitor == null) {
                        break Label_0116;
                    }
                    return ctorVisitor;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCStructLike ocStructLike2 = ocStructLike;
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final Object object = null;
                    ctorVisitor = new CtorVisitor(ocStructLike2, ocFunctionSymbol2);
                    if (ctorVisitor == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator", "getVisitor"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return ctorVisitor;
            try {
                funcVisitor = new FuncVisitor(ocStructLike, ocFunctionSymbol);
                if (funcVisitor == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCNewCppFunctionsLocator", "getVisitor"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return funcVisitor;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
    
    private interface Visitor
    {
        void visitFunction(@NotNull final OCFunctionDeclaration p0, @NotNull final OCVisibility p1);
        
        int getResult();
    }
}
