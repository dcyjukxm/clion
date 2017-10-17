// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.formatting.ChildAttributes;
import com.intellij.psi.tree.IElementType;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.jetbrains.annotations.Nullable;

class OCNormalIndentBlock extends OCCodeBlock
{
    public OCNormalIndentBlock(@Nullable final OCCodeBlock ocCodeBlock, @NotNull final CommonCodeStyleSettings commonCodeStyleSettings, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final Project project, @NotNull final ASTNode astNode, @NotNull final OCGlobalFormatterData ocGlobalFormatterData, @Nullable final Indent indent, @Nullable final Indent indent2, @Nullable final Wrap wrap, @Nullable final Alignment alignment) {
        if (commonCodeStyleSettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock", "<init>"));
        }
        if (ocCodeStyleSettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ocSettings", "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock", "<init>"));
        }
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock", "<init>"));
        }
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock", "<init>"));
        }
        if (ocGlobalFormatterData == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "globalFormatterData", "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock", "<init>"));
        }
        super(ocCodeBlock, commonCodeStyleSettings, ocCodeStyleSettings, project, astNode, ocGlobalFormatterData, indent, indent2, wrap, alignment);
    }
    
    @Nullable
    @Override
    protected Indent calcChildIndent(@NotNull final IElementType p0, @NotNull final ASTNode p1, @NotNull final IElementType p2) {
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
        //    18: ldc             "thisType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calcChildIndent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "child"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "calcChildIndent"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "childType"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "calcChildIndent"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: aload_1        
        //   134: aload_2        
        //   135: aload_3        
        //   136: invokevirtual   com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.calcChildIndentAhead:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/formatting/Indent;
        //   139: astore          4
        //   141: aload           4
        //   143: ifnull          153
        //   146: aload           4
        //   148: areturn        
        //   149: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_1        
        //   154: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isStructureOrNamespace:(Lcom/intellij/psi/tree/IElementType;)Z
        //   157: ifeq            182
        //   160: aload_3        
        //   161: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   164: if_acmpne       182
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   177: areturn        
        //   178: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload_3        
        //   183: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isControlSection:(Lcom/intellij/psi/tree/IElementType;)Z
        //   186: ifeq            197
        //   189: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   192: areturn        
        //   193: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload_0        
        //   198: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   201: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.getLabelType:(Lcom/intellij/lang/ASTNode;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE;
        //   204: astore          5
        //   206: aload_2        
        //   207: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.getLabelType:(Lcom/intellij/lang/ASTNode;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE;
        //   210: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE.LABEL_LIKE:Lcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE;
        //   213: if_acmpne       224
        //   216: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   219: areturn        
        //   220: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aload_2        
        //   225: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.getLabelType:(Lcom/intellij/lang/ASTNode;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE;
        //   228: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE.CASE_LIKE:Lcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE;
        //   231: if_acmpne       265
        //   234: aload           5
        //   236: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE.OTHER:Lcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE;
        //   239: if_acmpeq       259
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   252: goto            264
        //   255: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   258: athrow         
        //   259: aload_0        
        //   260: iconst_1       
        //   261: invokespecial   com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.a:(Z)Lcom/intellij/formatting/Indent;
        //   264: areturn        
        //   265: aload_2        
        //   266: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isDirectlyInsideSwitch:(Lcom/intellij/lang/ASTNode;)Z
        //   269: ifeq            324
        //   272: aload_2        
        //   273: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CASE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   276: iconst_0       
        //   277: anewarray       Lcom/intellij/psi/tree/IElementType;
        //   280: invokestatic    com/intellij/psi/formatter/FormatterUtil.hasPrecedingSiblingOfType:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;[Lcom/intellij/psi/tree/IElementType;)Z
        //   283: ifne            314
        //   286: goto            293
        //   289: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: aload_2        
        //   294: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LABELED_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   297: iconst_0       
        //   298: anewarray       Lcom/intellij/psi/tree/IElementType;
        //   301: invokestatic    com/intellij/psi/formatter/FormatterUtil.hasPrecedingSiblingOfType:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;[Lcom/intellij/psi/tree/IElementType;)Z
        //   304: ifeq            324
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: aload_0        
        //   315: iconst_0       
        //   316: invokespecial   com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.a:(Z)Lcom/intellij/formatting/Indent;
        //   319: areturn        
        //   320: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   323: athrow         
        //   324: aload           5
        //   326: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE.LABEL_LIKE:Lcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE;
        //   329: if_acmpne       448
        //   332: aload_0        
        //   333: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   336: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   341: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLabeledStatement;
        //   344: ifeq            448
        //   347: goto            354
        //   350: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   353: athrow         
        //   354: aload_0        
        //   355: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   358: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   363: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLabeledStatement;
        //   366: invokeinterface com/jetbrains/cidr/lang/psi/OCLabeledStatement.getLabelElement:()Lcom/intellij/psi/PsiElement;
        //   371: aload_2        
        //   372: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   377: if_acmpne       444
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: aload_0        
        //   388: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   391: invokevirtual   com/intellij/psi/codeStyle/CommonCodeStyleSettings.getIndentOptions:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings$IndentOptions;
        //   394: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings$IndentOptions.LABEL_INDENT_ABSOLUTE:Z
        //   397: ifeq            417
        //   400: goto            407
        //   403: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: invokestatic    com/intellij/formatting/Indent.getAbsoluteLabelIndent:()Lcom/intellij/formatting/Indent;
        //   410: goto            447
        //   413: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: aload_0        
        //   418: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   421: invokevirtual   com/intellij/psi/codeStyle/CommonCodeStyleSettings.getIndentOptions:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings$IndentOptions;
        //   424: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings$IndentOptions.INDENT_SIZE:I
        //   427: aload_0        
        //   428: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   431: invokevirtual   com/intellij/psi/codeStyle/CommonCodeStyleSettings.getIndentOptions:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings$IndentOptions;
        //   434: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings$IndentOptions.LABEL_INDENT_SIZE:I
        //   437: iadd           
        //   438: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.getSpaceIndentEnforcedToChildren:(I)Lcom/intellij/formatting/Indent;
        //   441: goto            447
        //   444: invokestatic    com/intellij/formatting/Indent.getNormalIndent:()Lcom/intellij/formatting/Indent;
        //   447: areturn        
        //   448: aload           5
        //   450: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE.CASE_LIKE:Lcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LABEL_TYPE;
        //   453: if_acmpne       501
        //   456: aload_2        
        //   457: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   462: ifnull          486
        //   465: goto            472
        //   468: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   471: athrow         
        //   472: aload_3        
        //   473: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isBlock:(Lcom/intellij/psi/tree/IElementType;)Z
        //   476: ifeq            496
        //   479: goto            486
        //   482: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   485: athrow         
        //   486: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   489: goto            500
        //   492: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   495: athrow         
        //   496: aload_0        
        //   497: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   500: areturn        
        //   501: aload_1        
        //   502: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IF_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   505: if_acmpne       592
        //   508: aload_3        
        //   509: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IF_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   512: if_acmpne       592
        //   515: goto            522
        //   518: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   521: athrow         
        //   522: aload_0        
        //   523: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   526: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPECIAL_ELSE_IF_TREATMENT:Z
        //   529: ifeq            592
        //   532: goto            539
        //   535: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   538: athrow         
        //   539: aload_0        
        //   540: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   543: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   548: astore          6
        //   550: aload           6
        //   552: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   555: ifeq            592
        //   558: aload           6
        //   560: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   563: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   568: aload_2        
        //   569: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   574: if_acmpne       592
        //   577: goto            584
        //   580: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   583: athrow         
        //   584: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   587: areturn        
        //   588: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   591: athrow         
        //   592: aload_1        
        //   593: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isBlock:(Lcom/intellij/psi/tree/IElementType;)Z
        //   596: ifne            635
        //   599: aload_2        
        //   600: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //   603: ifeq            635
        //   606: goto            613
        //   609: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: aload_2        
        //   614: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isInsideAnyBraces:(Lcom/intellij/lang/ASTNode;)Z
        //   617: ifne            635
        //   620: goto            627
        //   623: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   626: athrow         
        //   627: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   630: areturn        
        //   631: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   634: athrow         
        //   635: aload_1        
        //   636: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isControlStatement:(Lcom/intellij/psi/tree/IElementType;)Z
        //   639: ifeq            713
        //   642: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   645: aload_3        
        //   646: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   649: ifeq            713
        //   652: goto            659
        //   655: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   658: athrow         
        //   659: aload_3        
        //   660: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isBlock:(Lcom/intellij/psi/tree/IElementType;)Z
        //   663: ifne            713
        //   666: goto            673
        //   669: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   672: athrow         
        //   673: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FOR_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   676: aload_1        
        //   677: if_acmpne       704
        //   680: goto            687
        //   683: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   686: athrow         
        //   687: aload_2        
        //   688: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   691: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //   694: ifeq            713
        //   697: goto            704
        //   700: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   703: athrow         
        //   704: aload_0        
        //   705: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   708: areturn        
        //   709: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   712: athrow         
        //   713: aload_3        
        //   714: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isBlock:(Lcom/intellij/psi/tree/IElementType;)Z
        //   717: ifeq            749
        //   720: aload_1        
        //   721: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isBlock:(Lcom/intellij/psi/tree/IElementType;)Z
        //   724: ifeq            745
        //   727: goto            734
        //   730: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   733: athrow         
        //   734: aload_0        
        //   735: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   738: goto            748
        //   741: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   744: athrow         
        //   745: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   748: areturn        
        //   749: aload_2        
        //   750: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   755: ifnonnull       766
        //   758: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   761: areturn        
        //   762: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   765: athrow         
        //   766: aload_1        
        //   767: aload_2        
        //   768: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.a:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;)Z
        //   771: ifne            791
        //   774: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   777: aload_3        
        //   778: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   781: ifeq            802
        //   784: goto            791
        //   787: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   790: athrow         
        //   791: aload_0        
        //   792: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   795: goto            805
        //   798: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   801: athrow         
        //   802: invokestatic    com/intellij/formatting/Indent.getContinuationIndent:()Lcom/intellij/formatting/Indent;
        //   805: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  141    149    149    153    Ljava/lang/IllegalArgumentException;
        //  153    167    170    174    Ljava/lang/IllegalArgumentException;
        //  160    178    178    182    Ljava/lang/IllegalArgumentException;
        //  182    193    193    197    Ljava/lang/IllegalArgumentException;
        //  206    220    220    224    Ljava/lang/IllegalArgumentException;
        //  224    242    245    249    Ljava/lang/IllegalArgumentException;
        //  234    255    255    259    Ljava/lang/IllegalArgumentException;
        //  265    286    289    293    Ljava/lang/IllegalArgumentException;
        //  272    307    310    314    Ljava/lang/IllegalArgumentException;
        //  293    320    320    324    Ljava/lang/IllegalArgumentException;
        //  324    347    350    354    Ljava/lang/IllegalArgumentException;
        //  332    380    383    387    Ljava/lang/IllegalArgumentException;
        //  354    400    403    407    Ljava/lang/IllegalArgumentException;
        //  387    413    413    417    Ljava/lang/IllegalArgumentException;
        //  448    465    468    472    Ljava/lang/IllegalArgumentException;
        //  456    479    482    486    Ljava/lang/IllegalArgumentException;
        //  472    492    492    496    Ljava/lang/IllegalArgumentException;
        //  501    515    518    522    Ljava/lang/IllegalArgumentException;
        //  508    532    535    539    Ljava/lang/IllegalArgumentException;
        //  550    577    580    584    Ljava/lang/IllegalArgumentException;
        //  558    588    588    592    Ljava/lang/IllegalArgumentException;
        //  592    606    609    613    Ljava/lang/IllegalArgumentException;
        //  599    620    623    627    Ljava/lang/IllegalArgumentException;
        //  613    631    631    635    Ljava/lang/IllegalArgumentException;
        //  635    652    655    659    Ljava/lang/IllegalArgumentException;
        //  642    666    669    673    Ljava/lang/IllegalArgumentException;
        //  659    680    683    687    Ljava/lang/IllegalArgumentException;
        //  673    697    700    704    Ljava/lang/IllegalArgumentException;
        //  687    709    709    713    Ljava/lang/IllegalArgumentException;
        //  713    727    730    734    Ljava/lang/IllegalArgumentException;
        //  720    741    741    745    Ljava/lang/IllegalArgumentException;
        //  749    762    762    766    Ljava/lang/IllegalArgumentException;
        //  766    784    787    791    Ljava/lang/IllegalArgumentException;
        //  774    798    798    802    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0293:
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
    
    private Indent a(final boolean b) {
        Label_0040: {
            Label_0021: {
                try {
                    if (!this.mySettings.INDENT_CASE_FROM_SWITCH) {
                        break Label_0040;
                    }
                    final boolean b2 = b;
                    if (b2) {
                        break Label_0021;
                    }
                    return OCCodeBlock.getDoubleIndent(this.mySettings);
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final boolean b2 = b;
                    if (b2) {
                        return this.myChildIndent;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return OCCodeBlock.getDoubleIndent(this.mySettings);
            try {
                if (b) {
                    return Indent.getNoneIndent();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return this.myChildIndent;
    }
    
    @NotNull
    @Override
    public ChildAttributes getChildAttributes(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //     4: astore_2       
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.getSubBlocks:()Ljava/util/List;
        //     9: iload_1        
        //    10: iconst_1       
        //    11: isub           
        //    12: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    17: checkcast       Lcom/intellij/formatting/Block;
        //    20: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.extractLastNode:(Lcom/intellij/formatting/Block;)Lcom/intellij/lang/ASTNode;
        //    23: astore_3       
        //    24: aload_3        
        //    25: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    28: astore          4
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    34: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //    39: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    42: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SWITCH_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    45: if_acmpne       214
        //    48: aload           4
        //    50: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    53: if_acmpne       71
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: istore          5
        //    74: aload           4
        //    76: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CASE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    79: if_acmpeq       97
        //    82: aload           4
        //    84: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LABELED_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    87: if_acmpne       204
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: getstatic       com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.$assertionsDisabled:Z
        //   100: ifne            133
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_3        
        //   111: ifnonnull       133
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: new             Ljava/lang/AssertionError;
        //   124: dup            
        //   125: invokespecial   java/lang/AssertionError.<init>:()V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_3        
        //   134: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //   139: astore          6
        //   141: aload           6
        //   143: ifnull          204
        //   146: aload           6
        //   148: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   153: astore          7
        //   155: aload           7
        //   157: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isBlock:(Lcom/intellij/psi/tree/IElementType;)Z
        //   160: ifne            193
        //   163: aload           7
        //   165: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.RETURN_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   168: if_acmpeq       193
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload           7
        //   180: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BREAK_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   183: if_acmpne       201
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: iconst_1       
        //   194: goto            202
        //   197: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: iconst_0       
        //   202: istore          5
        //   204: aload_0        
        //   205: iload           5
        //   207: invokespecial   com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.a:(Z)Lcom/intellij/formatting/Indent;
        //   210: astore_2       
        //   211: goto            251
        //   214: aload_0        
        //   215: getfield        com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   218: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   223: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DO_WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   226: if_acmpne       251
        //   229: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   232: aload           4
        //   234: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   237: ifeq            251
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   250: astore_2       
        //   251: new             Lcom/intellij/formatting/ChildAttributes;
        //   254: dup            
        //   255: aload_2        
        //   256: aconst_null    
        //   257: invokespecial   com/intellij/formatting/ChildAttributes.<init>:(Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Alignment;)V
        //   260: dup            
        //   261: ifnonnull       298
        //   264: new             Ljava/lang/IllegalStateException;
        //   267: dup            
        //   268: ldc             "@NotNull method %s.%s must not return null"
        //   270: ldc             2
        //   272: anewarray       Ljava/lang/Object;
        //   275: dup            
        //   276: ldc             0
        //   278: ldc             "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock"
        //   280: aastore        
        //   281: dup            
        //   282: ldc             1
        //   284: ldc             "getChildAttributes"
        //   286: aastore        
        //   287: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   290: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   293: athrow         
        //   294: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  30     56     59     63     Ljava/lang/IllegalArgumentException;
        //  48     67     67     71     Ljava/lang/IllegalArgumentException;
        //  74     90     93     97     Ljava/lang/IllegalArgumentException;
        //  82     103    106    110    Ljava/lang/IllegalArgumentException;
        //  97     114    117    121    Ljava/lang/IllegalArgumentException;
        //  110    129    129    133    Ljava/lang/IllegalArgumentException;
        //  155    171    174    178    Ljava/lang/IllegalArgumentException;
        //  163    186    189    193    Ljava/lang/IllegalArgumentException;
        //  178    197    197    201    Ljava/lang/IllegalArgumentException;
        //  214    240    243    247    Ljava/lang/IllegalArgumentException;
        //  251    294    294    298    Ljava/lang/IllegalArgumentException;
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
    
    private static boolean a(@NotNull final IElementType p0, @NotNull final ASTNode p1) {
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
        //    18: ldc             "thisType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isInsideBraces"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "child"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isInsideBraces"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //    91: aload_0        
        //    92: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    95: ifne            140
        //    98: aload_0        
        //    99: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INSTANCE_VARIABLES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   102: if_acmpeq       140
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_0        
        //   113: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isStructureOrNamespace:(Lcom/intellij/psi/tree/IElementType;)Z
        //   116: ifeq            148
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_1        
        //   127: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.b:(Lcom/intellij/lang/ASTNode;)Z
        //   130: ifeq            148
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: iconst_1       
        //   141: goto            149
        //   144: invokestatic    com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: iconst_0       
        //   149: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     105    108    112    Ljava/lang/IllegalArgumentException;
        //  98     119    122    126    Ljava/lang/IllegalArgumentException;
        //  112    133    136    140    Ljava/lang/IllegalArgumentException;
        //  126    144    144    148    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
    
    private static boolean b(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "start", "com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock", "hasEarlierLBraceSibling"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final ASTNode treeParent = astNode.getTreeParent();
        Label_0082: {
            Label_0072: {
                try {
                    if (treeParent != null) {
                        break Label_0082;
                    }
                    final ASTNode astNode2 = astNode;
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.LBRACE;
                    final ASTNode astNode3 = TreeUtil.findSiblingBackward(astNode2, ocPunctuatorElementType);
                    if (astNode3 != null) {
                        break Label_0072;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                try {
                    final ASTNode astNode2 = astNode;
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.LBRACE;
                    final ASTNode astNode3 = TreeUtil.findSiblingBackward(astNode2, ocPunctuatorElementType);
                    if (astNode3 != null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            return false;
        }
        ASTNode astNode4 = treeParent.getFirstChildNode();
        while (true) {
            Label_0111: {
                try {
                    if (astNode4 == null) {
                        break;
                    }
                    final ASTNode astNode5 = astNode4;
                    final ASTNode astNode6 = astNode;
                    if (astNode5 == astNode6) {
                        return false;
                    }
                    break Label_0111;
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
                try {
                    final ASTNode astNode5 = astNode4;
                    final ASTNode astNode6 = astNode;
                    if (astNode5 == astNode6) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw c(ex5);
                }
                try {
                    if (astNode4.getElementType() == OCTokenTypes.LBRACE) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw c(ex6);
                }
            }
            astNode4 = astNode4.getTreeNext();
        }
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNormalIndentBlock.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
