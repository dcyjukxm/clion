// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.formatting;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.formatting.Spacing;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import com.intellij.formatting.ChildAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.formatting.Block;
import java.util.List;
import com.intellij.psi.PsiFile;
import com.intellij.formatting.Alignment;
import com.intellij.lang.ASTNode;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Wrap;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.formatter.common.AbstractBlock;

public class CMakeCodeBlock extends AbstractBlock
{
    @NotNull
    private static final TokenSet INDENT_SET;
    @NotNull
    private static final TokenSet INDENT_RESET;
    @Nullable
    CMakeCodeBlock myParentBlock;
    @NotNull
    private final CMakeSpacingProcessor myGlobalCMakeSpacingProcessor;
    @NotNull
    private final CommonCodeStyleSettings mySettings;
    @Nullable
    private final Wrap myWrap;
    @Nullable
    private final Indent myIndent;
    
    public CMakeCodeBlock(@Nullable final CMakeCodeBlock myParentBlock, @NotNull final CMakeSpacingProcessor myGlobalCMakeSpacingProcessor, @NotNull final CommonCodeStyleSettings mySettings, @NotNull final ASTNode astNode, @Nullable final Alignment alignment, @Nullable final Wrap myWrap, @Nullable final Indent myIndent) {
        if (myGlobalCMakeSpacingProcessor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeSpacingProcessor", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock", "<init>"));
        }
        if (mySettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock", "<init>"));
        }
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock", "<init>"));
        }
        super(astNode, myWrap, alignment);
        this.myParentBlock = myParentBlock;
        this.myGlobalCMakeSpacingProcessor = myGlobalCMakeSpacingProcessor;
        this.mySettings = mySettings;
        this.myWrap = myWrap;
        this.myIndent = myIndent;
    }
    
    public CMakeCodeBlock(@NotNull final PsiFile psiFile, @NotNull final CommonCodeStyleSettings commonCodeStyleSettings) {
        if (psiFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock", "<init>"));
        }
        if (commonCodeStyleSettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock", "<init>"));
        }
        this(null, new CMakeSpacingProcessor(commonCodeStyleSettings), commonCodeStyleSettings, (ASTNode)psiFile.getNode(), null, null, Indent.getNoneIndent());
    }
    
    @NotNull
    @Override
    protected List<Block> buildChildren() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.isLeaf:()Z
        //     4: ifeq            56
        //     7: getstatic       com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.EMPTY:Ljava/util/List;
        //    10: dup            
        //    11: ifnonnull       55
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: new             Ljava/lang/IllegalStateException;
        //    24: dup            
        //    25: ldc             "@NotNull method %s.%s must not return null"
        //    27: ldc             2
        //    29: anewarray       Ljava/lang/Object;
        //    32: dup            
        //    33: ldc             0
        //    35: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //    37: aastore        
        //    38: dup            
        //    39: ldc             1
        //    41: ldc             "buildChildren"
        //    43: aastore        
        //    44: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    47: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    50: athrow         
        //    51: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: areturn        
        //    56: new             Ljava/util/ArrayList;
        //    59: dup            
        //    60: invokespecial   java/util/ArrayList.<init>:()V
        //    63: astore_1       
        //    64: aload_0        
        //    65: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    68: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    73: astore_2       
        //    74: aload_2        
        //    75: ifnull          193
        //    78: aload_2        
        //    79: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    84: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //    87: if_acmpeq       183
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: aload_2        
        //    98: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   101: ifne            183
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_2        
        //   112: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   117: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   120: invokevirtual   java/lang/String.length:()I
        //   123: ifle            183
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_0        
        //   134: aload_2        
        //   135: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.a:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;Lcom/intellij/lang/ASTNode;)Lcom/intellij/formatting/Indent;
        //   138: astore_3       
        //   139: aload_0        
        //   140: aload_0        
        //   141: aload_2        
        //   142: aload_1        
        //   143: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.a:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;Lcom/intellij/lang/ASTNode;Ljava/util/List;)Lcom/intellij/formatting/Alignment;
        //   146: astore          4
        //   148: new             Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;
        //   151: dup            
        //   152: aload_0        
        //   153: aload_0        
        //   154: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.myGlobalCMakeSpacingProcessor:Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor;
        //   157: aload_0        
        //   158: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   161: aload_2        
        //   162: aload           4
        //   164: aload_0        
        //   165: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.myWrap:Lcom/intellij/formatting/Wrap;
        //   168: aload_3        
        //   169: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.<init>:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor;Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/intellij/lang/ASTNode;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Indent;)V
        //   172: astore          5
        //   174: aload_1        
        //   175: aload           5
        //   177: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   182: pop            
        //   183: aload_2        
        //   184: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   189: astore_2       
        //   190: goto            74
        //   193: aload_1        
        //   194: dup            
        //   195: ifnonnull       232
        //   198: new             Ljava/lang/IllegalStateException;
        //   201: dup            
        //   202: ldc             "@NotNull method %s.%s must not return null"
        //   204: ldc             2
        //   206: anewarray       Ljava/lang/Object;
        //   209: dup            
        //   210: ldc             0
        //   212: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   214: aastore        
        //   215: dup            
        //   216: ldc             1
        //   218: ldc             "buildChildren"
        //   220: aastore        
        //   221: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   224: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   227: athrow         
        //   228: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: areturn        
        //    Signature:
        //  ()Ljava/util/List<Lcom/intellij/formatting/Block;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      51     51     55     Ljava/lang/IllegalArgumentException;
        //  74     90     93     97     Ljava/lang/IllegalArgumentException;
        //  78     104    107    111    Ljava/lang/IllegalArgumentException;
        //  97     126    129    133    Ljava/lang/IllegalArgumentException;
        //  193    228    228    232    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0097:
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
    
    @NotNull
    @Override
    public TextRange getTextRange() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //     4: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //     9: astore_1       
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    14: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //    19: astore_2       
        //    20: aload_2        
        //    21: ifnull          51
        //    24: aload_2        
        //    25: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    30: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //    33: if_acmpne       51
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_1       
        //    44: goto            52
        //    47: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: iconst_0       
        //    52: istore_3       
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    57: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //    62: ifnonnull       73
        //    65: iconst_1       
        //    66: goto            74
        //    69: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: iconst_0       
        //    74: istore          4
        //    76: iload_3        
        //    77: ifeq            160
        //    80: iload           4
        //    82: ifne            160
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: new             Lcom/intellij/openapi/util/TextRange;
        //    95: dup            
        //    96: aload_1        
        //    97: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   100: aload_1        
        //   101: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   104: aload_2        
        //   105: invokeinterface com/intellij/lang/ASTNode.getTextLength:()I
        //   110: isub           
        //   111: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //   114: dup            
        //   115: ifnonnull       159
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: new             Ljava/lang/IllegalStateException;
        //   128: dup            
        //   129: ldc             "@NotNull method %s.%s must not return null"
        //   131: ldc             2
        //   133: anewarray       Ljava/lang/Object;
        //   136: dup            
        //   137: ldc             0
        //   139: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   141: aastore        
        //   142: dup            
        //   143: ldc             1
        //   145: ldc             "getTextRange"
        //   147: aastore        
        //   148: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   151: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   154: athrow         
        //   155: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: areturn        
        //   160: aload_1        
        //   161: dup            
        //   162: ifnonnull       199
        //   165: new             Ljava/lang/IllegalStateException;
        //   168: dup            
        //   169: ldc             "@NotNull method %s.%s must not return null"
        //   171: ldc             2
        //   173: anewarray       Ljava/lang/Object;
        //   176: dup            
        //   177: ldc             0
        //   179: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   181: aastore        
        //   182: dup            
        //   183: ldc             1
        //   185: ldc             "getTextRange"
        //   187: aastore        
        //   188: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   191: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   194: athrow         
        //   195: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  20     36     39     43     Ljava/lang/IllegalArgumentException;
        //  24     47     47     51     Ljava/lang/IllegalArgumentException;
        //  53     69     69     73     Ljava/lang/IllegalArgumentException;
        //  76     85     88     92     Ljava/lang/IllegalArgumentException;
        //  80     118    121    125    Ljava/lang/IllegalArgumentException;
        //  92     155    155    159    Ljava/lang/IllegalArgumentException;
        //  160    195    195    199    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    
    private static boolean a(@NotNull final ASTNode p0, @NotNull final ASTNode p1) {
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
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isCommandArgument"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isCommandArgument"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    94: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //    97: if_acmpne       203
        //   100: aload_1        
        //   101: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   106: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_ARGUMENT:Lcom/intellij/psi/tree/IElementType;
        //   109: if_acmpeq       195
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_1        
        //   120: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   125: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   128: if_acmpeq       195
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_1        
        //   139: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   144: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.QUOTE:Lcom/intellij/psi/tree/IElementType;
        //   147: if_acmpeq       195
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload_1        
        //   158: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   163: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.BRACKET_ARG_START:Lcom/intellij/psi/tree/IElementType;
        //   166: if_acmpeq       195
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload_1        
        //   177: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   182: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.BRACKET_ARG_END:Lcom/intellij/psi/tree/IElementType;
        //   185: if_acmpne       203
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: iconst_1       
        //   196: goto            204
        //   199: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: iconst_0       
        //   204: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     112    115    119    Ljava/lang/IllegalArgumentException;
        //  100    131    134    138    Ljava/lang/IllegalArgumentException;
        //  119    150    153    157    Ljava/lang/IllegalArgumentException;
        //  138    169    172    176    Ljava/lang/IllegalArgumentException;
        //  157    188    191    195    Ljava/lang/IllegalArgumentException;
        //  176    199    199    203    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0119:
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
    
    @NotNull
    @Override
    public ChildAttributes getChildAttributes(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_1        
        //     1: ifne            61
        //     4: new             Lcom/intellij/formatting/ChildAttributes;
        //     7: dup            
        //     8: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //    11: aconst_null    
        //    12: invokespecial   com/intellij/formatting/ChildAttributes.<init>:(Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Alignment;)V
        //    15: dup            
        //    16: ifnonnull       60
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: new             Ljava/lang/IllegalStateException;
        //    29: dup            
        //    30: ldc             "@NotNull method %s.%s must not return null"
        //    32: ldc             2
        //    34: anewarray       Ljava/lang/Object;
        //    37: dup            
        //    38: ldc             0
        //    40: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //    42: aastore        
        //    43: dup            
        //    44: ldc             1
        //    46: ldc             "getChildAttributes"
        //    48: aastore        
        //    49: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    52: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    55: athrow         
        //    56: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: areturn        
        //    61: aload_0        
        //    62: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getSubBlocks:()Ljava/util/List;
        //    65: astore_2       
        //    66: iload_1        
        //    67: iconst_1       
        //    68: isub           
        //    69: istore_3       
        //    70: aload_2        
        //    71: iload_3        
        //    72: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    77: checkcast       Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;
        //    80: astore          4
        //    82: aload_0        
        //    83: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //    86: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    91: astore          6
        //    93: getstatic       com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.INDENT_SET:Lcom/intellij/psi/tree/TokenSet;
        //    96: aload           6
        //    98: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   101: ifeq            112
        //   104: invokestatic    com/intellij/formatting/Indent.getNormalIndent:()Lcom/intellij/formatting/Indent;
        //   107: astore          5
        //   109: goto            134
        //   112: aload           6
        //   114: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   117: if_acmpne       128
        //   120: invokestatic    com/intellij/formatting/Indent.getContinuationIndent:()Lcom/intellij/formatting/Indent;
        //   123: astore          5
        //   125: goto            134
        //   128: aload_0        
        //   129: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getIndent:()Lcom/intellij/formatting/Indent;
        //   132: astore          5
        //   134: iload_3        
        //   135: iflt            202
        //   138: aload           4
        //   140: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   143: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   148: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   151: if_acmpeq       184
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aload           4
        //   163: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   166: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   171: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.SEMI:Lcom/intellij/psi/tree/IElementType;
        //   174: if_acmpne       202
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: aload_2        
        //   185: iload_3        
        //   186: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   191: checkcast       Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;
        //   194: astore          4
        //   196: iinc            3, -1
        //   199: goto            134
        //   202: aload           4
        //   204: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getAlignment:()Lcom/intellij/formatting/Alignment;
        //   207: astore          7
        //   209: aload           6
        //   211: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   214: if_acmpeq       298
        //   217: aload           6
        //   219: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   222: if_acmpeq       298
        //   225: goto            232
        //   228: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aload           4
        //   234: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   237: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   242: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   245: if_acmpne       303
        //   248: goto            255
        //   251: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: aload           6
        //   257: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.CMAKE_FILE:Lcom/intellij/psi/tree/IFileElementType;
        //   260: if_acmpne       303
        //   263: goto            270
        //   266: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   269: athrow         
        //   270: aload           4
        //   272: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   275: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //   280: getstatic       com/intellij/psi/TokenType.ERROR_ELEMENT:Lcom/intellij/psi/tree/IElementType;
        //   283: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   288: ifnull          303
        //   291: goto            298
        //   294: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: invokestatic    com/intellij/formatting/Indent.getContinuationIndent:()Lcom/intellij/formatting/Indent;
        //   301: astore          5
        //   303: new             Lcom/intellij/formatting/ChildAttributes;
        //   306: dup            
        //   307: aload           5
        //   309: aload           7
        //   311: invokespecial   com/intellij/formatting/ChildAttributes.<init>:(Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Alignment;)V
        //   314: dup            
        //   315: ifnonnull       352
        //   318: new             Ljava/lang/IllegalStateException;
        //   321: dup            
        //   322: ldc             "@NotNull method %s.%s must not return null"
        //   324: ldc             2
        //   326: anewarray       Ljava/lang/Object;
        //   329: dup            
        //   330: ldc             0
        //   332: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   334: aastore        
        //   335: dup            
        //   336: ldc             1
        //   338: ldc             "getChildAttributes"
        //   340: aastore        
        //   341: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   344: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   347: athrow         
        //   348: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   351: athrow         
        //   352: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  4      56     56     60     Ljava/lang/IllegalArgumentException;
        //  134    154    157    161    Ljava/lang/IllegalArgumentException;
        //  138    177    180    184    Ljava/lang/IllegalArgumentException;
        //  209    225    228    232    Ljava/lang/IllegalArgumentException;
        //  217    248    251    255    Ljava/lang/IllegalArgumentException;
        //  232    263    266    270    Ljava/lang/IllegalArgumentException;
        //  255    291    294    298    Ljava/lang/IllegalArgumentException;
        //  303    348    348    352    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0232:
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
    
    @NotNull
    private static Indent a(@NotNull final CMakeCodeBlock p0, @NotNull final ASTNode p1) {
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
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calculateChildIndent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "calculateChildIndent"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //    92: astore_2       
        //    93: aload_2        
        //    94: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    99: astore_3       
        //   100: aload_1        
        //   101: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   106: astore          4
        //   108: aload           4
        //   110: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   113: if_acmpne       165
        //   116: invokestatic    com/intellij/formatting/Indent.getContinuationWithoutFirstIndent:()Lcom/intellij/formatting/Indent;
        //   119: dup            
        //   120: ifnonnull       164
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: new             Ljava/lang/IllegalStateException;
        //   133: dup            
        //   134: ldc             "@NotNull method %s.%s must not return null"
        //   136: ldc             2
        //   138: anewarray       Ljava/lang/Object;
        //   141: dup            
        //   142: ldc             0
        //   144: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   146: aastore        
        //   147: dup            
        //   148: ldc             1
        //   150: ldc             "calculateChildIndent"
        //   152: aastore        
        //   153: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   156: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   159: athrow         
        //   160: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: areturn        
        //   165: aload_2        
        //   166: aload_1        
        //   167: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.a:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)Z
        //   170: ifeq            222
        //   173: invokestatic    com/intellij/formatting/Indent.getContinuationWithoutFirstIndent:()Lcom/intellij/formatting/Indent;
        //   176: dup            
        //   177: ifnonnull       221
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: new             Ljava/lang/IllegalStateException;
        //   190: dup            
        //   191: ldc             "@NotNull method %s.%s must not return null"
        //   193: ldc             2
        //   195: anewarray       Ljava/lang/Object;
        //   198: dup            
        //   199: ldc             0
        //   201: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   203: aastore        
        //   204: dup            
        //   205: ldc             1
        //   207: ldc             "calculateChildIndent"
        //   209: aastore        
        //   210: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   213: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   216: athrow         
        //   217: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: areturn        
        //   222: aload_1        
        //   223: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   228: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   231: if_acmpne       303
        //   234: aload_0        
        //   235: aload_1        
        //   236: invokestatic    com/intellij/formatting/Indent.getContinuationIndent:()Lcom/intellij/formatting/Indent;
        //   239: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   242: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.rparShouldBeIndented:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;Lcom/intellij/lang/ASTNode;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   245: checkcast       Lcom/intellij/formatting/Indent;
        //   248: astore          5
        //   250: aload           5
        //   252: ifnull          303
        //   255: aload           5
        //   257: dup            
        //   258: ifnonnull       302
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: new             Ljava/lang/IllegalStateException;
        //   271: dup            
        //   272: ldc             "@NotNull method %s.%s must not return null"
        //   274: ldc             2
        //   276: anewarray       Ljava/lang/Object;
        //   279: dup            
        //   280: ldc             0
        //   282: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   284: aastore        
        //   285: dup            
        //   286: ldc             1
        //   288: ldc             "calculateChildIndent"
        //   290: aastore        
        //   291: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   294: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   297: athrow         
        //   298: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: areturn        
        //   303: getstatic       com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.INDENT_RESET:Lcom/intellij/psi/tree/TokenSet;
        //   306: aload           4
        //   308: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   311: ifeq            363
        //   314: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   317: dup            
        //   318: ifnonnull       362
        //   321: goto            328
        //   324: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   327: athrow         
        //   328: new             Ljava/lang/IllegalStateException;
        //   331: dup            
        //   332: ldc             "@NotNull method %s.%s must not return null"
        //   334: ldc             2
        //   336: anewarray       Ljava/lang/Object;
        //   339: dup            
        //   340: ldc             0
        //   342: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   344: aastore        
        //   345: dup            
        //   346: ldc             1
        //   348: ldc             "calculateChildIndent"
        //   350: aastore        
        //   351: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   354: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   357: athrow         
        //   358: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   361: athrow         
        //   362: areturn        
        //   363: aload           4
        //   365: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_BODY_BLOCK:Lcom/intellij/psi/tree/IElementType;
        //   368: if_acmpne       437
        //   371: getstatic       com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.INDENT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   374: aload_3        
        //   375: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   378: ifeq            437
        //   381: goto            388
        //   384: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   387: athrow         
        //   388: invokestatic    com/intellij/formatting/Indent.getNormalIndent:()Lcom/intellij/formatting/Indent;
        //   391: dup            
        //   392: ifnonnull       436
        //   395: goto            402
        //   398: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   401: athrow         
        //   402: new             Ljava/lang/IllegalStateException;
        //   405: dup            
        //   406: ldc             "@NotNull method %s.%s must not return null"
        //   408: ldc             2
        //   410: anewarray       Ljava/lang/Object;
        //   413: dup            
        //   414: ldc             0
        //   416: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   418: aastore        
        //   419: dup            
        //   420: ldc             1
        //   422: ldc             "calculateChildIndent"
        //   424: aastore        
        //   425: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   428: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   431: athrow         
        //   432: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   435: athrow         
        //   436: areturn        
        //   437: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   440: dup            
        //   441: ifnonnull       478
        //   444: new             Ljava/lang/IllegalStateException;
        //   447: dup            
        //   448: ldc             "@NotNull method %s.%s must not return null"
        //   450: ldc             2
        //   452: anewarray       Ljava/lang/Object;
        //   455: dup            
        //   456: ldc             0
        //   458: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //   460: aastore        
        //   461: dup            
        //   462: ldc             1
        //   464: ldc             "calculateChildIndent"
        //   466: aastore        
        //   467: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   470: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   473: athrow         
        //   474: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  108    123    126    130    Ljava/lang/IllegalArgumentException;
        //  116    160    160    164    Ljava/lang/IllegalArgumentException;
        //  165    180    183    187    Ljava/lang/IllegalArgumentException;
        //  173    217    217    221    Ljava/lang/IllegalArgumentException;
        //  250    261    264    268    Ljava/lang/IllegalArgumentException;
        //  255    298    298    302    Ljava/lang/IllegalArgumentException;
        //  303    321    324    328    Ljava/lang/IllegalArgumentException;
        //  314    358    358    362    Ljava/lang/IllegalArgumentException;
        //  363    381    384    388    Ljava/lang/IllegalArgumentException;
        //  371    395    398    402    Ljava/lang/IllegalArgumentException;
        //  388    432    432    436    Ljava/lang/IllegalArgumentException;
        //  437    474    474    478    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0388:
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
    private Alignment a(@NotNull final CMakeCodeBlock p0, @NotNull final ASTNode p1, final List<Block> p2) {
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
        //    18: ldc             "parentNode"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calculateChildAlignment"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "node"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "calculateChildAlignment"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    92: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ALIGN_MULTILINE_PARAMETERS_IN_CALLS:Z
        //    95: ifne            104
        //    98: aconst_null    
        //    99: areturn        
        //   100: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_2        
        //   105: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   110: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_ARGUMENT:Lcom/intellij/psi/tree/IElementType;
        //   113: if_acmpeq       154
        //   116: aload_2        
        //   117: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   122: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   125: if_acmpeq       154
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_2        
        //   136: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   141: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   144: if_acmpne       358
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_1        
        //   155: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   158: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   163: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   166: if_acmpne       358
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload_3        
        //   177: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.a:(Ljava/util/List;)I
        //   180: istore          4
        //   182: iload           4
        //   184: iflt            283
        //   187: aload_3        
        //   188: iload           4
        //   190: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   195: checkcast       Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;
        //   198: astore          5
        //   200: aload_2        
        //   201: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   206: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   209: if_acmpne       242
        //   212: aload_1        
        //   213: aload_2        
        //   214: aload           5
        //   216: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getAlignment:()Lcom/intellij/formatting/Alignment;
        //   219: invokestatic    com/intellij/formatting/Alignment.createAlignment:()Lcom/intellij/formatting/Alignment;
        //   222: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.rparShouldBeIndented:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;Lcom/intellij/lang/ASTNode;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   225: checkcast       Lcom/intellij/formatting/Alignment;
        //   228: astore          6
        //   230: aload           6
        //   232: ifnull          242
        //   235: aload           6
        //   237: areturn        
        //   238: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   241: athrow         
        //   242: aload           5
        //   244: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getAlignment:()Lcom/intellij/formatting/Alignment;
        //   247: ifnull          279
        //   250: aload_2        
        //   251: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   256: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   259: if_acmpeq       279
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: aload           5
        //   271: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getAlignment:()Lcom/intellij/formatting/Alignment;
        //   274: areturn        
        //   275: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   278: athrow         
        //   279: invokestatic    com/intellij/formatting/Alignment.createAlignment:()Lcom/intellij/formatting/Alignment;
        //   282: areturn        
        //   283: aload_2        
        //   284: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   289: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.C_MAKE_ARGUMENT:Lcom/intellij/psi/tree/IElementType;
        //   292: if_acmpeq       314
        //   295: aload_2        
        //   296: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   301: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   304: if_acmpne       358
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: aload_2        
        //   315: astore          5
        //   317: aload           5
        //   319: ifnull          354
        //   322: aload           5
        //   324: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   329: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LPAR:Lcom/intellij/psi/tree/IElementType;
        //   332: if_acmpeq       354
        //   335: goto            342
        //   338: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   341: athrow         
        //   342: aload           5
        //   344: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   349: astore          5
        //   351: goto            317
        //   354: invokestatic    com/intellij/formatting/Alignment.createAlignment:()Lcom/intellij/formatting/Alignment;
        //   357: areturn        
        //   358: aconst_null    
        //   359: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;Lcom/intellij/lang/ASTNode;Ljava/util/List<Lcom/intellij/formatting/Block;>;)Lcom/intellij/formatting/Alignment;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     100    100    104    Ljava/lang/IllegalArgumentException;
        //  104    128    131    135    Ljava/lang/IllegalArgumentException;
        //  116    147    150    154    Ljava/lang/IllegalArgumentException;
        //  135    169    172    176    Ljava/lang/IllegalArgumentException;
        //  230    238    238    242    Ljava/lang/IllegalArgumentException;
        //  242    262    265    269    Ljava/lang/IllegalArgumentException;
        //  250    275    275    279    Ljava/lang/IllegalArgumentException;
        //  283    307    310    314    Ljava/lang/IllegalArgumentException;
        //  317    335    338    342    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0135:
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
    
    private static int a(final List<Block> list) {
        for (int i = list.size() - 1; i >= 0; --i) {
            final CMakeCodeBlock cMakeCodeBlock = (CMakeCodeBlock)list.get(i);
            try {
                if (cMakeCodeBlock.getNode().getElementType() == CMakeTokenTypes.C_MAKE_ARGUMENT) {
                    return i;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        }
        return -1;
    }
    
    @Nullable
    public Spacing getSpacing(@Nullable final Block block, @NotNull final Block block2) {
        try {
            if (block2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child2", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock", "getSpacing"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myGlobalCMakeSpacingProcessor.getSpacing(this, block, block2);
    }
    
    @Nullable
    @Override
    public Indent getIndent() {
        return this.myIndent;
    }
    
    public boolean isLeaf() {
        return this.myNode instanceof LeafElement;
    }
    
    static {
        INDENT_SET = TokenSet.create(new IElementType[] { CMakeTokenTypes.C_MAKE_MACRO_COMMAND, CMakeTokenTypes.C_MAKE_FUNCTION_COMMAND, CMakeTokenTypes.C_MAKE_WHILE_COMMAND, CMakeTokenTypes.C_MAKE_FOREACH_COMMAND, CMakeTokenTypes.C_MAKE_IF_COMMAND, CMakeTokenTypes.C_MAKE_ELSE_COMMAND, CMakeTokenTypes.C_MAKE_ELSE_IF_COMMAND });
        INDENT_RESET = TokenSet.create(new IElementType[] { CMakeTokenTypes.C_MAKE_END_MACRO_COMMAND, CMakeTokenTypes.C_MAKE_END_FUNCTION_COMMAND, CMakeTokenTypes.C_MAKE_END_WHILE_COMMAND, CMakeTokenTypes.C_MAKE_END_FOREACH_COMMAND, CMakeTokenTypes.C_MAKE_END_IF_COMMAND });
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
