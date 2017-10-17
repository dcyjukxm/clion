// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.util.OCElementsRange;
import com.intellij.codeInsight.editorActions.moveUpDown.StatementUpDownMover;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.openapi.util.Key;

public class OCStatementUpDownMover extends OCUpDownMover
{
    private static final Key<OCStatement> STATEMENT_KEY;
    
    @Override
    public boolean checkAvailable(@NotNull final Editor p0, @NotNull final StatementUpDownMover.MoveInfo p1, @NotNull final OCElementsRange p2, final boolean p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "info"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "checkAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "range"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "checkAvailable"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_3        
        //   133: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getFile:()Lcom/intellij/psi/PsiFile;
        //   136: aload_3        
        //   137: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getStartOffset:()I
        //   140: aload_3        
        //   141: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getEndOffset:()I
        //   144: iconst_0       
        //   145: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.findStatementsAtRange:(Lcom/intellij/psi/PsiFile;IIZ)[Lcom/intellij/psi/PsiElement;
        //   148: astore          5
        //   150: aload           5
        //   152: ifnull          168
        //   155: aload           5
        //   157: arraylength    
        //   158: ifne            174
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: iconst_0       
        //   169: ireturn        
        //   170: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: aload           5
        //   176: arraylength    
        //   177: iconst_1       
        //   178: if_icmpne       273
        //   181: aload           5
        //   183: iconst_0       
        //   184: aaload         
        //   185: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   188: ifeq            273
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   197: athrow         
        //   198: aload           5
        //   200: iconst_0       
        //   201: aaload         
        //   202: astore          6
        //   204: aload           6
        //   206: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   211: astore          7
        //   213: aload           7
        //   215: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   218: ifne            267
        //   221: aload           7
        //   223: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLabeledStatement;
        //   226: ifne            267
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: aload           7
        //   238: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCaseStatement;
        //   241: ifne            267
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload           7
        //   253: astore          6
        //   255: aload           6
        //   257: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   262: astore          7
        //   264: goto            213
        //   267: aload           5
        //   269: iconst_0       
        //   270: aload           6
        //   272: aastore        
        //   273: new             Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   276: dup            
        //   277: aload           5
        //   279: iconst_0       
        //   280: aaload         
        //   281: aload           5
        //   283: aload           5
        //   285: arraylength    
        //   286: iconst_1       
        //   287: isub           
        //   288: aaload         
        //   289: invokespecial   com/intellij/codeInsight/editorActions/moveUpDown/LineRange.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   292: astore          6
        //   294: aload           6
        //   296: aload           5
        //   298: iconst_0       
        //   299: aaload         
        //   300: putfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.firstElement:Lcom/intellij/psi/PsiElement;
        //   303: aload           6
        //   305: aload           5
        //   307: aload           5
        //   309: arraylength    
        //   310: iconst_1       
        //   311: isub           
        //   312: aaload         
        //   313: putfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.lastElement:Lcom/intellij/psi/PsiElement;
        //   316: aload_2        
        //   317: aload           6
        //   319: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   322: aload_2        
        //   323: aconst_null    
        //   324: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove2:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   327: aload           5
        //   329: iconst_0       
        //   330: aaload         
        //   331: iconst_2       
        //   332: anewarray       Ljava/lang/Class;
        //   335: dup            
        //   336: iconst_0       
        //   337: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //   339: aastore        
        //   340: dup            
        //   341: iconst_1       
        //   342: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   344: aastore        
        //   345: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   348: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   351: astore          7
        //   353: iload           4
        //   355: ifeq            370
        //   358: aload           6
        //   360: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.endLine:I
        //   363: goto            377
        //   366: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   369: athrow         
        //   370: aload           6
        //   372: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.startLine:I
        //   375: iconst_1       
        //   376: isub           
        //   377: istore          8
        //   379: iload           4
        //   381: ifeq            398
        //   384: aload           6
        //   386: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.endLine:I
        //   389: iconst_1       
        //   390: iadd           
        //   391: goto            405
        //   394: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   397: athrow         
        //   398: aload           6
        //   400: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.startLine:I
        //   403: iconst_1       
        //   404: isub           
        //   405: istore          9
        //   407: aload           7
        //   409: ifnonnull       418
        //   412: iconst_0       
        //   413: ireturn        
        //   414: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   417: athrow         
        //   418: aload           7
        //   420: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   425: astore          10
        //   427: aload           10
        //   429: ifnonnull       438
        //   432: iconst_0       
        //   433: ireturn        
        //   434: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   437: athrow         
        //   438: aload           10
        //   440: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getRangeWithMacros:()Lcom/intellij/openapi/util/TextRange;
        //   445: astore          11
        //   447: iload           9
        //   449: iflt            886
        //   452: iload           9
        //   454: aload_1        
        //   455: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   460: invokeinterface com/intellij/openapi/editor/Document.getLineCount:()I
        //   465: if_icmpge       886
        //   468: goto            475
        //   471: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   474: athrow         
        //   475: aload_1        
        //   476: new             Lcom/intellij/openapi/editor/LogicalPosition;
        //   479: dup            
        //   480: iload           9
        //   482: iconst_0       
        //   483: invokespecial   com/intellij/openapi/editor/LogicalPosition.<init>:(II)V
        //   486: invokeinterface com/intellij/openapi/editor/Editor.logicalPositionToOffset:(Lcom/intellij/openapi/editor/LogicalPosition;)I
        //   491: istore          12
        //   493: aload           11
        //   495: iload           12
        //   497: invokevirtual   com/intellij/openapi/util/TextRange.containsOffset:(I)Z
        //   500: ifne            510
        //   503: goto            886
        //   506: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   509: athrow         
        //   510: aload_0        
        //   511: aload_1        
        //   512: iload           12
        //   514: iload           9
        //   516: aload_3        
        //   517: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getFile:()Lcom/intellij/psi/PsiFile;
        //   520: iconst_1       
        //   521: iconst_1       
        //   522: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.firstNonMacroWhiteElement:(Lcom/intellij/openapi/editor/Editor;IILcom/intellij/psi/PsiFile;ZZ)Lcom/intellij/psi/PsiElement;
        //   525: astore          13
        //   527: iload           4
        //   529: iload           8
        //   531: iload           9
        //   533: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.calcDestRange:(ZII)Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   536: astore          14
        //   538: aload           13
        //   540: ifnull          883
        //   543: aload           13
        //   545: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   548: astore          15
        //   550: aload           13
        //   552: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   554: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   557: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   560: astore          16
        //   562: aload           16
        //   564: ifnull          616
        //   567: aload           16
        //   569: aload           5
        //   571: iconst_0       
        //   572: aaload         
        //   573: iconst_0       
        //   574: invokestatic    com/intellij/psi/util/PsiTreeUtil.isAncestor:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
        //   577: ifne            616
        //   580: goto            587
        //   583: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   586: athrow         
        //   587: iload           9
        //   589: iload           4
        //   591: ifeq            609
        //   594: goto            601
        //   597: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   600: athrow         
        //   601: iconst_1       
        //   602: goto            610
        //   605: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   608: athrow         
        //   609: iconst_m1      
        //   610: iadd           
        //   611: istore          9
        //   613: goto            447
        //   616: aload           13
        //   618: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isWhitespace:(Lcom/intellij/psi/PsiElement;)Z
        //   621: ifeq            685
        //   624: aload           15
        //   626: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   629: if_acmpeq       685
        //   632: goto            639
        //   635: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   638: athrow         
        //   639: aload           13
        //   641: instanceof      Lcom/intellij/psi/PsiComment;
        //   644: ifeq            672
        //   647: goto            654
        //   650: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   653: athrow         
        //   654: aload_1        
        //   655: aload           13
        //   657: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.elementStartLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   660: iload           9
        //   662: if_icmplt       685
        //   665: goto            672
        //   668: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   671: athrow         
        //   672: aload_2        
        //   673: aload           14
        //   675: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove2:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   678: goto            886
        //   681: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   684: athrow         
        //   685: aload           13
        //   687: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //   689: iconst_0       
        //   690: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   693: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   696: astore          17
        //   698: aload           17
        //   700: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   703: ifeq            818
        //   706: aload           17
        //   708: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   711: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //   716: aload           13
        //   718: if_acmpeq       763
        //   721: goto            728
        //   724: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   727: athrow         
        //   728: aload           15
        //   730: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPreprocessorDirectiveElementType;
        //   733: ifeq            776
        //   736: goto            743
        //   739: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   742: athrow         
        //   743: aload           13
        //   745: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   748: ldc             "#if"
        //   750: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   753: ifeq            776
        //   756: goto            763
        //   759: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   762: athrow         
        //   763: aload_2        
        //   764: aload           14
        //   766: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove2:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   769: goto            886
        //   772: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   775: athrow         
        //   776: aload           17
        //   778: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //   783: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   786: ifne            818
        //   789: iload           9
        //   791: iload           4
        //   793: ifeq            811
        //   796: goto            803
        //   799: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   802: athrow         
        //   803: iconst_1       
        //   804: goto            812
        //   807: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   810: athrow         
        //   811: iconst_m1      
        //   812: iadd           
        //   813: istore          9
        //   815: goto            447
        //   818: aload           17
        //   820: ifnull          864
        //   823: aload           17
        //   825: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getTextOffset:()I
        //   830: iload           12
        //   832: if_icmplt       864
        //   835: goto            842
        //   838: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   841: athrow         
        //   842: aload_2        
        //   843: aload           14
        //   845: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove2:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   848: aload_2        
        //   849: getstatic       com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.STATEMENT_KEY:Lcom/intellij/openapi/util/Key;
        //   852: aload           17
        //   854: invokevirtual   com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   857: goto            886
        //   860: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   863: athrow         
        //   864: iload           9
        //   866: iload           4
        //   868: ifeq            879
        //   871: iconst_1       
        //   872: goto            880
        //   875: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   878: athrow         
        //   879: iconst_m1      
        //   880: iadd           
        //   881: istore          9
        //   883: goto            447
        //   886: iconst_1       
        //   887: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  150    161    164    168    Ljava/lang/IllegalArgumentException;
        //  155    170    170    174    Ljava/lang/IllegalArgumentException;
        //  174    191    194    198    Ljava/lang/IllegalArgumentException;
        //  213    229    232    236    Ljava/lang/IllegalArgumentException;
        //  221    244    247    251    Ljava/lang/IllegalArgumentException;
        //  353    366    366    370    Ljava/lang/IllegalArgumentException;
        //  379    394    394    398    Ljava/lang/IllegalArgumentException;
        //  407    414    414    418    Ljava/lang/IllegalArgumentException;
        //  427    434    434    438    Ljava/lang/IllegalArgumentException;
        //  447    468    471    475    Ljava/lang/IllegalArgumentException;
        //  493    506    506    510    Ljava/lang/IllegalArgumentException;
        //  562    580    583    587    Ljava/lang/IllegalArgumentException;
        //  567    594    597    601    Ljava/lang/IllegalArgumentException;
        //  587    605    605    609    Ljava/lang/IllegalArgumentException;
        //  616    632    635    639    Ljava/lang/IllegalArgumentException;
        //  624    647    650    654    Ljava/lang/IllegalArgumentException;
        //  639    665    668    672    Ljava/lang/IllegalArgumentException;
        //  654    681    681    685    Ljava/lang/IllegalArgumentException;
        //  698    721    724    728    Ljava/lang/IllegalArgumentException;
        //  706    736    739    743    Ljava/lang/IllegalArgumentException;
        //  728    756    759    763    Ljava/lang/IllegalArgumentException;
        //  743    772    772    776    Ljava/lang/IllegalArgumentException;
        //  776    796    799    803    Ljava/lang/IllegalArgumentException;
        //  789    807    807    811    Ljava/lang/IllegalArgumentException;
        //  818    835    838    842    Ljava/lang/IllegalArgumentException;
        //  823    860    860    864    Ljava/lang/IllegalArgumentException;
        //  864    875    875    879    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0587:
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
    
    public void beforeMove(@NotNull final Editor p0, @NotNull final StatementUpDownMover.MoveInfo p1, final boolean p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "beforeMove"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "info"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "beforeMove"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: getstatic       com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.STATEMENT_KEY:Lcom/intellij/openapi/util/Key;
        //    92: invokevirtual   com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //    95: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    98: astore          4
        //   100: aload           4
        //   102: ifnull          119
        //   105: aload           4
        //   107: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //   112: goto            120
        //   115: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aconst_null    
        //   120: astore          5
        //   122: aload           5
        //   124: ifnull          315
        //   127: aload           5
        //   129: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   132: ifne            315
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: aload           5
        //   144: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLabeledStatement;
        //   147: ifne            315
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload           5
        //   159: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCaseStatement;
        //   162: ifne            315
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_1        
        //   173: aload           4
        //   175: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.elementStartLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   178: istore          6
        //   180: aload           4
        //   182: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.ensureParentIsBlockStatement:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   185: astore          4
        //   187: aload           4
        //   189: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getProject:()Lcom/intellij/openapi/project/Project;
        //   194: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   197: aload_1        
        //   198: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   203: invokevirtual   com/intellij/psi/PsiDocumentManager.doPostponedOperationsAndUnblockDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   206: aload_2        
        //   207: getfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   210: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.startLine:I
        //   213: istore          7
        //   215: aload_2        
        //   216: getfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   219: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.endLine:I
        //   222: istore          8
        //   224: aload_1        
        //   225: aload           4
        //   227: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.elementStartLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   230: istore          9
        //   232: iload           9
        //   234: iload           6
        //   236: isub           
        //   237: iconst_1       
        //   238: iadd           
        //   239: istore          10
        //   241: iload           7
        //   243: iload           9
        //   245: if_icmplt       255
        //   248: iload           7
        //   250: iload           10
        //   252: iadd           
        //   253: istore          7
        //   255: iload           8
        //   257: iload           9
        //   259: if_icmplt       269
        //   262: iload           8
        //   264: iload           10
        //   266: iadd           
        //   267: istore          8
        //   269: iload_3        
        //   270: ifeq            282
        //   273: iload           8
        //   275: goto            286
        //   278: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: iload           7
        //   284: iconst_1       
        //   285: isub           
        //   286: istore          11
        //   288: aload_2        
        //   289: new             Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   292: dup            
        //   293: iload           7
        //   295: iload           8
        //   297: invokespecial   com/intellij/codeInsight/editorActions/moveUpDown/LineRange.<init>:(II)V
        //   300: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   303: aload_2        
        //   304: iload_3        
        //   305: iload           11
        //   307: iload           9
        //   309: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCStatementUpDownMover.calcDestRange:(ZII)Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   312: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove2:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   315: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  100    115    115    119    Ljava/lang/IllegalArgumentException;
        //  122    135    138    142    Ljava/lang/IllegalArgumentException;
        //  127    150    153    157    Ljava/lang/IllegalArgumentException;
        //  142    165    168    172    Ljava/lang/IllegalArgumentException;
        //  269    278    278    282    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0142:
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
        STATEMENT_KEY = Key.create("OCStatementUpDownMover.STATEMENT_KEY");
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
