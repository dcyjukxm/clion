// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.parser.OCElementType;

public class ForStatementFixer extends OCFixer
{
    public static final OCElementType IN_PSEUDOTYPE;
    
    @Override
    public void apply(@NotNull final Editor p0, @NotNull final OCSmartEnterProcessor p1, @NotNull final PsiElement p2) throws IncorrectOperationException {
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
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "apply"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "processor"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "apply"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //   106: ldc             "psiElement"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "apply"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_3        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //   136: ifne            153
        //   139: aload_3        
        //   140: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   143: ifeq            970
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   152: athrow         
        //   153: aload_3        
        //   154: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FOR_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   157: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasMacroBasedStatement:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   160: ifeq            175
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   169: athrow         
        //   170: return         
        //   171: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   174: athrow         
        //   175: aload_1        
        //   176: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   181: astore          4
        //   183: aload_3        
        //   184: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLoopStatement;
        //   187: astore          5
        //   189: aload           5
        //   191: invokeinterface com/jetbrains/cidr/lang/psi/OCLoopStatement.getRParenth:()Lcom/intellij/lang/ASTNode;
        //   196: astore          6
        //   198: aload           5
        //   200: invokeinterface com/jetbrains/cidr/lang/psi/OCLoopStatement.getLParenth:()Lcom/intellij/lang/ASTNode;
        //   205: astore          7
        //   207: aload           7
        //   209: ifnonnull       318
        //   212: aload           4
        //   214: aload           4
        //   216: aload           5
        //   218: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   221: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   224: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   229: invokeinterface com/intellij/openapi/editor/Document.getLineEndOffset:(I)I
        //   234: istore          8
        //   236: aload           5
        //   238: invokeinterface com/jetbrains/cidr/lang/psi/OCLoopStatement.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   243: astore          9
        //   245: aload           9
        //   247: ifnull          265
        //   250: iload           8
        //   252: aload           9
        //   254: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   257: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   260: invokestatic    java/lang/Math.min:(II)I
        //   263: istore          8
        //   265: iload           8
        //   267: aload           5
        //   269: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   272: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   275: invokestatic    java/lang/Math.min:(II)I
        //   278: istore          8
        //   280: aload           4
        //   282: aload           5
        //   284: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   287: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   290: iload           8
        //   292: ldc             "for ()"
        //   294: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //   299: aload_2        
        //   300: aload           5
        //   302: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   305: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   308: ldc             "for ("
        //   310: invokevirtual   java/lang/String.length:()I
        //   313: iadd           
        //   314: invokevirtual   com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.registerUnresolvedError:(I)V
        //   317: return         
        //   318: iconst_m1      
        //   319: istore          8
        //   321: aload           7
        //   323: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   326: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   329: istore          9
        //   331: aload_3        
        //   332: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //   335: ifeq            805
        //   338: aload           5
        //   340: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //   343: invokeinterface com/jetbrains/cidr/lang/psi/OCForStatement.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   348: astore          10
        //   350: aload_1        
        //   351: aload           10
        //   353: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.fixSemicolonAtTheEnd:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)Z
        //   356: ifeq            364
        //   359: return         
        //   360: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   363: athrow         
        //   364: aload           5
        //   366: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //   369: invokeinterface com/jetbrains/cidr/lang/psi/OCForStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   374: astore          11
        //   376: aload_1        
        //   377: aload           11
        //   379: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.fixSemicolonAtTheEnd:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)Z
        //   382: ifeq            390
        //   385: return         
        //   386: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   389: athrow         
        //   390: aload           5
        //   392: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //   395: invokeinterface com/jetbrains/cidr/lang/psi/OCForStatement.getIncrement:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   400: astore          12
        //   402: aload_3        
        //   403: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   408: astore          13
        //   410: aload           13
        //   412: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   415: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findChildBackward:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   418: astore          14
        //   420: aload           14
        //   422: ifnonnull       438
        //   425: aload           7
        //   427: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   430: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   433: istore          8
        //   435: goto            802
        //   438: aload           12
        //   440: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   443: ifeq            461
        //   446: aload           14
        //   448: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   451: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   454: goto            469
        //   457: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   460: athrow         
        //   461: aload           12
        //   463: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   466: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   469: istore          9
        //   471: aload           13
        //   473: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   476: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   481: astore          15
        //   483: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.$assertionsDisabled:Z
        //   486: ifne            513
        //   489: aload           15
        //   491: ifnonnull       513
        //   494: goto            501
        //   497: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   500: athrow         
        //   501: new             Ljava/lang/AssertionError;
        //   504: dup            
        //   505: invokespecial   java/lang/AssertionError.<init>:()V
        //   508: athrow         
        //   509: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   512: athrow         
        //   513: aload_2        
        //   514: invokevirtual   com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.isFirstFixAttempt:()Z
        //   517: ifeq            687
        //   520: aload           15
        //   522: aload           14
        //   524: if_acmpeq       687
        //   527: goto            534
        //   530: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   533: athrow         
        //   534: aload_1        
        //   535: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   540: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   545: istore          16
        //   547: iload           16
        //   549: iload           9
        //   551: if_icmple       564
        //   554: aload           7
        //   556: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   559: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   562: istore          16
        //   564: iload           16
        //   566: aload           7
        //   568: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   571: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   574: if_icmpge       605
        //   577: aload           10
        //   579: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   582: ifeq            605
        //   585: goto            592
        //   588: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   591: athrow         
        //   592: aload           15
        //   594: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   597: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   600: istore          8
        //   602: goto            684
        //   605: iload           16
        //   607: aload           15
        //   609: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   612: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   615: if_icmpgt       646
        //   618: aload           11
        //   620: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   623: ifeq            646
        //   626: goto            633
        //   629: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   632: athrow         
        //   633: aload           14
        //   635: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   638: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   641: istore          8
        //   643: goto            684
        //   646: iload           16
        //   648: aload           14
        //   650: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   653: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   656: if_icmpgt       681
        //   659: aload           12
        //   661: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   664: ifeq            681
        //   667: goto            674
        //   670: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   673: athrow         
        //   674: iload           9
        //   676: istore          8
        //   678: goto            684
        //   681: iconst_m1      
        //   682: istore          8
        //   684: goto            802
        //   687: aload           6
        //   689: ifnull          802
        //   692: aload           10
        //   694: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   697: ifeq            734
        //   700: goto            707
        //   703: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   706: athrow         
        //   707: aload           14
        //   709: aload           15
        //   711: if_acmpeq       734
        //   714: goto            721
        //   717: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   720: athrow         
        //   721: aload           15
        //   723: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   726: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   729: istore          8
        //   731: goto            802
        //   734: aload           11
        //   736: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   739: ifeq            784
        //   742: aload           14
        //   744: aload           15
        //   746: if_acmpne       771
        //   749: goto            756
        //   752: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   755: athrow         
        //   756: aload           15
        //   758: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   761: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   764: goto            779
        //   767: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   770: athrow         
        //   771: aload           14
        //   773: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   776: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   779: istore          8
        //   781: goto            802
        //   784: aload           12
        //   786: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   789: ifeq            802
        //   792: aload           6
        //   794: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   797: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   800: istore          8
        //   802: goto            918
        //   805: aload           5
        //   807: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   810: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.getVariableDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   815: astore          10
        //   817: aload           10
        //   819: ifnonnull       834
        //   822: aload           5
        //   824: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   827: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.getVariableExpression:()Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   832: astore          10
        //   834: aload           10
        //   836: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   839: ifne            918
        //   842: aload           10
        //   844: aload           5
        //   846: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   849: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.isCpp11Foreach:()Z
        //   854: ifeq            874
        //   857: goto            864
        //   860: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   863: athrow         
        //   864: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   867: goto            877
        //   870: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   873: athrow         
        //   874: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.IN_PSEUDOTYPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   877: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/intellij/psi/tree/IElementType;)I
        //   880: dup            
        //   881: istore          8
        //   883: istore          9
        //   885: aload           5
        //   887: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   890: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.getCollectionExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   895: astore          11
        //   897: aload           11
        //   899: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   902: ifne            918
        //   905: aload           11
        //   907: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   910: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   913: istore          9
        //   915: iconst_m1      
        //   916: istore          8
        //   918: aload           6
        //   920: ifnonnull       951
        //   923: aload           4
        //   925: iload           9
        //   927: aload           4
        //   929: invokeinterface com/intellij/openapi/editor/Document.getTextLength:()I
        //   934: invokestatic    java/lang/Math.min:(II)I
        //   937: ldc             ")"
        //   939: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   944: goto            951
        //   947: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   950: athrow         
        //   951: iload           8
        //   953: iconst_m1      
        //   954: if_icmpeq       970
        //   957: aload_2        
        //   958: iload           8
        //   960: invokevirtual   com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.registerUnresolvedError:(I)V
        //   963: goto            970
        //   966: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   969: athrow         
        //   970: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  132    146    149    153    Lcom/intellij/util/IncorrectOperationException;
        //  139    163    166    170    Lcom/intellij/util/IncorrectOperationException;
        //  153    171    171    175    Lcom/intellij/util/IncorrectOperationException;
        //  350    360    360    364    Lcom/intellij/util/IncorrectOperationException;
        //  376    386    386    390    Lcom/intellij/util/IncorrectOperationException;
        //  438    457    457    461    Lcom/intellij/util/IncorrectOperationException;
        //  483    494    497    501    Lcom/intellij/util/IncorrectOperationException;
        //  489    509    509    513    Lcom/intellij/util/IncorrectOperationException;
        //  513    527    530    534    Lcom/intellij/util/IncorrectOperationException;
        //  564    585    588    592    Lcom/intellij/util/IncorrectOperationException;
        //  605    626    629    633    Lcom/intellij/util/IncorrectOperationException;
        //  646    667    670    674    Lcom/intellij/util/IncorrectOperationException;
        //  687    700    703    707    Lcom/intellij/util/IncorrectOperationException;
        //  692    714    717    721    Lcom/intellij/util/IncorrectOperationException;
        //  734    749    752    756    Lcom/intellij/util/IncorrectOperationException;
        //  742    767    767    771    Lcom/intellij/util/IncorrectOperationException;
        //  834    857    860    864    Lcom/intellij/util/IncorrectOperationException;
        //  842    870    870    874    Lcom/intellij/util/IncorrectOperationException;
        //  918    944    947    951    Lcom/intellij/util/IncorrectOperationException;
        //  951    963    966    970    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0153:
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
    
    @Contract(pure = true)
    private static int a(@NotNull final OCElement p0, @NotNull final IElementType p1) {
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
        //    18: ldc             "declaration"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "moveAfter"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "type"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "moveAfter"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    94: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getNextEssentialLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    97: astore_2       
        //    98: aload_2        
        //    99: ifnull          166
        //   102: aload_1        
        //   103: aload_2        
        //   104: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   109: if_acmpeq       154
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   118: athrow         
        //   119: aload_1        
        //   120: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.IN_PSEUDOTYPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   123: if_acmpne       166
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   132: athrow         
        //   133: ldc             "in"
        //   135: aload_2        
        //   136: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   141: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   144: ifeq            166
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   153: athrow         
        //   154: aload_2        
        //   155: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   158: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   161: ireturn        
        //   162: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   165: athrow         
        //   166: aload_0        
        //   167: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/ForStatementFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   170: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   173: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  98     112    115    119    Lcom/intellij/util/IncorrectOperationException;
        //  102    126    129    133    Lcom/intellij/util/IncorrectOperationException;
        //  119    147    150    154    Lcom/intellij/util/IncorrectOperationException;
        //  133    162    162    166    Lcom/intellij/util/IncorrectOperationException;
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!ForStatementFixer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        IN_PSEUDOTYPE = new OCElementType("in");
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
