// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.TokenSet;
import com.intellij.formatting.Block;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public static class OCBinaryChainTransformer extends OCRecursiveBlockTransformer
{
    @Override
    protected boolean needTransformation(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "needTransformation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (astNode.getElementType() == OCElementTypes.BINARY_EXPRESSION) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    @Override
    protected boolean needCommonWrapper(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "needCommonWrapper"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!a(astNode, astNode.getTreeParent())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    @Override
    protected boolean needKeysFromNodeBlock(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "needKeysFromNodeBlock"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!this.needCommonWrapper(astNode)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    @Override
    protected IElementType getAttrPseudotype(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "getAttrPseudotype"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCWrappingProcessor.BINARY_EXPRESSION_PSEUDOTYPE;
    }
    
    @Override
    protected boolean chainFirst(@NotNull final OCWrappingProcessor ocWrappingProcessor, @NotNull final ASTNode astNode) {
        try {
            if (ocWrappingProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerWrappingCalculator", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "chainFirst"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "chainFirst"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    @NotNull
    @Override
    protected SplitterType getSplitterType(@NotNull final OCWrappingProcessor p0, @NotNull final Block p1, @NotNull final ASTNode p2) {
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
        //    18: ldc             "ownerWrappingCalculator"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getSplitterType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "nodeSubBlock"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getSplitterType"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "node"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getSplitterType"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.getBlockType:(Lcom/intellij/formatting/Block;)Lcom/intellij/psi/tree/IElementType;
        //   136: astore          4
        //   138: aload           4
        //   140: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   143: ifeq            233
        //   146: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SHIFT_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   149: aload           4
        //   151: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   154: ifne            181
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: aload_1        
        //   165: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getSettings:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   168: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BINARY_OPERATION_SIGN_ON_NEXT_LINE:Z
        //   171: ifeq            191
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: getstatic       com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType.BlockToRight:Lcom/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType;
        //   184: goto            194
        //   187: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: getstatic       com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType.BlockToLeft:Lcom/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType;
        //   194: dup            
        //   195: ifnonnull       232
        //   198: new             Ljava/lang/IllegalStateException;
        //   201: dup            
        //   202: ldc             "@NotNull method %s.%s must not return null"
        //   204: ldc             2
        //   206: anewarray       Ljava/lang/Object;
        //   209: dup            
        //   210: ldc             0
        //   212: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
        //   214: aastore        
        //   215: dup            
        //   216: ldc             1
        //   218: ldc             "getSplitterType"
        //   220: aastore        
        //   221: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   224: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   227: athrow         
        //   228: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: areturn        
        //   233: getstatic       com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType.BlockNotSplitter:Lcom/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType;
        //   236: dup            
        //   237: ifnonnull       274
        //   240: new             Ljava/lang/IllegalStateException;
        //   243: dup            
        //   244: ldc             "@NotNull method %s.%s must not return null"
        //   246: ldc             2
        //   248: anewarray       Ljava/lang/Object;
        //   251: dup            
        //   252: ldc             0
        //   254: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
        //   256: aastore        
        //   257: dup            
        //   258: ldc             1
        //   260: ldc             "getSplitterType"
        //   262: aastore        
        //   263: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   266: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   269: athrow         
        //   270: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  138    157    160    164    Ljava/lang/IllegalArgumentException;
        //  146    174    177    181    Ljava/lang/IllegalArgumentException;
        //  164    187    187    191    Ljava/lang/IllegalArgumentException;
        //  194    228    228    232    Ljava/lang/IllegalArgumentException;
        //  233    270    270    274    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0164:
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    static class ChainOperations
    {
        private static TokenSet ADDITIVE;
        private static TokenSet MULTIPLICATIVE;
        private static TokenSet BINARY_SHIFT;
        private static TokenSet LOGICAL;
        private static TokenSet QUALIFYING;
        
        private static boolean a(@Nullable final ASTNode astNode, @Nullable final ASTNode astNode2) {
            if (astNode == null || astNode2 == null) {
                return false;
            }
            final PsiElement psi = astNode.getPsi();
            final PsiElement psi2 = astNode2.getPsi();
            if (psi == null || psi2 == null || !(psi instanceof OCBinaryExpression) || !(psi2 instanceof OCBinaryExpression)) {
                return false;
            }
            final OCElementType operationSign = ((OCBinaryExpression)psi).getOperationSign();
            final OCElementType operationSign2 = ((OCBinaryExpression)psi2).getOperationSign();
            return operationSign == operationSign2 || (ChainOperations.ADDITIVE.contains((IElementType)operationSign) && ChainOperations.ADDITIVE.contains((IElementType)operationSign2)) || (ChainOperations.MULTIPLICATIVE.contains((IElementType)operationSign) && ChainOperations.MULTIPLICATIVE.contains((IElementType)operationSign2)) || (ChainOperations.BINARY_SHIFT.contains((IElementType)operationSign) && ChainOperations.BINARY_SHIFT.contains((IElementType)operationSign2)) || (ChainOperations.LOGICAL.contains((IElementType)operationSign) && ChainOperations.LOGICAL.contains((IElementType)operationSign2)) || (ChainOperations.QUALIFYING.contains((IElementType)operationSign) && ChainOperations.QUALIFYING.contains((IElementType)operationSign2));
        }
        
        static {
            ChainOperations.ADDITIVE = TokenSet.create(new IElementType[] { OCTokenTypes.PLUS, OCTokenTypes.MINUS });
            ChainOperations.MULTIPLICATIVE = TokenSet.create(new IElementType[] { OCTokenTypes.MUL, OCTokenTypes.DIV, OCTokenTypes.PERC });
            ChainOperations.BINARY_SHIFT = TokenSet.create(new IElementType[] { OCTokenTypes.LTLT, OCTokenTypes.GTGT });
            ChainOperations.LOGICAL = TokenSet.create(new IElementType[] { OCTokenTypes.LT, OCTokenTypes.GT, OCTokenTypes.LTEQ, OCTokenTypes.GTEQ, OCTokenTypes.EQEQ, OCTokenTypes.EXCLEQ });
            ChainOperations.QUALIFYING = TokenSet.create(new IElementType[] { OCTokenTypes.DOT_MUL, OCTokenTypes.DEREF_MUL });
        }
    }
}
