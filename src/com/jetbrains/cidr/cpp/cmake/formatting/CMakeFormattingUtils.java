// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.formatting;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.TokenType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class CMakeFormattingUtils
{
    public static boolean textBetweenChildrenContainsLineBreak(@NotNull final CMakeCodeBlock cMakeCodeBlock, @NotNull final CMakeCodeBlock cMakeCodeBlock2) {
        try {
            if (cMakeCodeBlock == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child1", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils", "textBetweenChildrenContainsLineBreak"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeCodeBlock2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child2", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils", "textBetweenChildrenContainsLineBreak"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return textBetweenChildrenContainsLineBreak(cMakeCodeBlock.getNode(), cMakeCodeBlock2.getNode());
    }
    
    public static boolean textBetweenChildrenContainsLineBreak(@NotNull final ASTNode astNode, @NotNull final ASTNode astNode2) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child1", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils", "textBetweenChildrenContainsLineBreak"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (astNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child2", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils", "textBetweenChildrenContainsLineBreak"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement psi = astNode.getPsi();
        final int startOffset = astNode2.getTextRange().getStartOffset();
        PsiElement nextLeaf = psi;
        while (true) {
            try {
                if (nextLeaf == null || nextLeaf.getTextRange().getStartOffset() >= startOffset) {
                    break;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            nextLeaf = PsiTreeUtil.nextLeaf(nextLeaf);
            if (nextLeaf != null) {
                final ASTNode node = nextLeaf.getNode();
                try {
                    if (node.getElementType() != TokenType.WHITE_SPACE) {
                        continue;
                    }
                    final ASTNode astNode3 = node;
                    final String s = astNode3.getText();
                    final boolean b = StringUtil.containsLineBreak((CharSequence)s);
                    if (b) {
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final ASTNode astNode3 = node;
                    final String s = astNode3.getText();
                    final boolean b = StringUtil.containsLineBreak((CharSequence)s);
                    if (b) {
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
        }
        return false;
    }
    
    @Nullable
    public static <T> T rparShouldBeIndented(@NotNull final CMakeCodeBlock p0, @NotNull final ASTNode p1, @Nullable final T p2, @Nullable final T p3) {
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
        //    18: ldc             "parentNode"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "rparShouldBeIndented"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "node"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "rparShouldBeIndented"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    94: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //    97: if_acmpne       301
        //   100: aload_0        
        //   101: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   104: astore          4
        //   106: aload           4
        //   108: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_ARGUMENT:Lcom/intellij/psi/tree/IElementType;
        //   111: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   116: astore          5
        //   118: aload           4
        //   120: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   123: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   128: astore          6
        //   130: aload           5
        //   132: astore          7
        //   134: aload           5
        //   136: ifnonnull       146
        //   139: aload           6
        //   141: astore          7
        //   143: goto            188
        //   146: aload           6
        //   148: ifnull          188
        //   151: aload           5
        //   153: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   158: aload           6
        //   160: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   165: if_icmpge       184
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload           5
        //   177: goto            186
        //   180: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: aload           6
        //   186: astore          7
        //   188: aload           7
        //   190: ifnull          301
        //   193: aload           4
        //   195: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   198: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   203: astore          8
        //   205: aload           8
        //   207: ifnull          257
        //   210: aload           8
        //   212: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   217: aload           7
        //   219: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   224: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   227: if_icmpge       257
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload           8
        //   239: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   244: invokestatic    com/intellij/openapi/util/text/StringUtil.containsLineBreak:(Ljava/lang/CharSequence;)Z
        //   247: ifne            265
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: iconst_1       
        //   258: goto            266
        //   261: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: iconst_0       
        //   266: istore          9
        //   268: aload           4
        //   270: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   275: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   278: if_acmpne       299
        //   281: iload           9
        //   283: ifeq            299
        //   286: goto            293
        //   289: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: aload_2        
        //   294: areturn        
        //   295: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload_3        
        //   300: areturn        
        //   301: aconst_null    
        //   302: areturn        
        //    Signature:
        //  <T:Ljava/lang/Object;>(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;Lcom/intellij/lang/ASTNode;TT;TT;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  146    168    171    175    Ljava/lang/IllegalArgumentException;
        //  151    180    180    184    Ljava/lang/IllegalArgumentException;
        //  205    230    233    237    Ljava/lang/IllegalArgumentException;
        //  210    250    253    257    Ljava/lang/IllegalArgumentException;
        //  237    261    261    265    Ljava/lang/IllegalArgumentException;
        //  268    286    289    293    Ljava/lang/IllegalArgumentException;
        //  281    295    295    299    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0237:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
