// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.formatting.OCMultilineNodeFormatter;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.openapi.editor.Document;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter;

public class OCEnterInDirectiveAndNoncompiledHandler extends EnterHandlerDelegateAdapter
{
    private static final TokenSet WHITE_SPACE_OR_COMMENT;
    
    @Override
    public EnterHandlerDelegate.Result preprocessEnter(@NotNull final PsiFile p0, @NotNull final Editor p1, @NotNull final Ref<Integer> p2, @NotNull final Ref<Integer> p3, @NotNull final DataContext p4, final EditorActionHandler p5) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "preprocessEnter"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "editor"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "preprocessEnter"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "caretOffsetRef"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "preprocessEnter"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload           4
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "caretAdvanceRef"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "preprocessEnter"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload           5
        //   179: ifnonnull       222
        //   182: new             Ljava/lang/IllegalArgumentException;
        //   185: dup            
        //   186: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   188: ldc             3
        //   190: anewarray       Ljava/lang/Object;
        //   193: dup            
        //   194: ldc             0
        //   196: ldc             "dataContext"
        //   198: aastore        
        //   199: dup            
        //   200: ldc             1
        //   202: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler"
        //   204: aastore        
        //   205: dup            
        //   206: ldc             2
        //   208: ldc             "preprocessEnter"
        //   210: aastore        
        //   211: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   214: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   217: athrow         
        //   218: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_3        
        //   223: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   226: checkcast       Ljava/lang/Integer;
        //   229: invokevirtual   java/lang/Integer.intValue:()I
        //   232: istore          7
        //   234: aload_1        
        //   235: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   240: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   243: aload_2        
        //   244: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   249: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   252: aload_1        
        //   253: iload           7
        //   255: invokestatic    com/intellij/psi/util/PsiUtilCore.getLanguageAtOffset:(Lcom/intellij/psi/PsiFile;I)Lcom/intellij/lang/Language;
        //   258: instanceof      Lcom/jetbrains/cidr/lang/OCLanguage;
        //   261: ifne            272
        //   264: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Continue:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //   267: areturn        
        //   268: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   271: athrow         
        //   272: aload_2        
        //   273: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   278: astore          8
        //   280: aload_1        
        //   281: iload           7
        //   283: aload           8
        //   285: invokeinterface com/intellij/openapi/editor/Document.getTextLength:()I
        //   290: if_icmpne       316
        //   293: iload           7
        //   295: ifle            316
        //   298: goto            305
        //   301: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   304: athrow         
        //   305: iload           7
        //   307: iconst_1       
        //   308: isub           
        //   309: goto            318
        //   312: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: iload           7
        //   318: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   323: astore          9
        //   325: aload           9
        //   327: astore          10
        //   329: getstatic       com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.WHITE_SPACE_OR_COMMENT:Lcom/intellij/psi/tree/TokenSet;
        //   332: aload           10
        //   334: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   337: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   340: ifeq            388
        //   343: aload           10
        //   345: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   350: iconst_0       
        //   351: iload           7
        //   353: aload           9
        //   355: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   360: isub           
        //   361: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   364: ldc             "\n"
        //   366: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   369: ifne            388
        //   372: goto            379
        //   375: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   378: athrow         
        //   379: aload           9
        //   381: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   386: astore          10
        //   388: aload           10
        //   390: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   393: astore          11
        //   395: aload           11
        //   397: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   400: if_acmpne       1015
        //   403: getstatic       com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.$assertionsDisabled:Z
        //   406: ifne            440
        //   409: goto            416
        //   412: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   415: athrow         
        //   416: aload           10
        //   418: ifnonnull       440
        //   421: goto            428
        //   424: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   427: athrow         
        //   428: new             Ljava/lang/AssertionError;
        //   431: dup            
        //   432: invokespecial   java/lang/AssertionError.<init>:()V
        //   435: athrow         
        //   436: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   439: athrow         
        //   440: aload           8
        //   442: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   447: astore          12
        //   449: aload_2        
        //   450: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //   453: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //   458: iload           7
        //   460: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //   465: astore          13
        //   467: aload           13
        //   469: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   474: ifne            548
        //   477: getstatic       com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.WHITE_SPACE_OR_COMMENT:Lcom/intellij/psi/tree/TokenSet;
        //   480: aload           13
        //   482: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   487: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   490: ifeq            548
        //   493: goto            500
        //   496: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   499: athrow         
        //   500: aload           12
        //   502: aload           13
        //   504: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //   509: aload           13
        //   511: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getEnd:()I
        //   516: iload           7
        //   518: invokestatic    java/lang/Math.min:(II)I
        //   521: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.a:(Ljava/lang/CharSequence;II)Z
        //   524: ifne            548
        //   527: goto            534
        //   530: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   533: athrow         
        //   534: aload           13
        //   536: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //   541: goto            548
        //   544: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   547: athrow         
        //   548: aload           13
        //   550: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   555: ifne            601
        //   558: aload           12
        //   560: aload           13
        //   562: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //   567: aload           13
        //   569: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getEnd:()I
        //   574: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.a:(Ljava/lang/CharSequence;II)Z
        //   577: ifne            601
        //   580: goto            587
        //   583: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   586: athrow         
        //   587: aload           13
        //   589: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //   594: goto            548
        //   597: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   600: athrow         
        //   601: aload           13
        //   603: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   608: ifne            1012
        //   611: aload           13
        //   613: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.advance:()V
        //   618: aload           13
        //   620: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   625: ifeq            643
        //   628: goto            635
        //   631: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   634: athrow         
        //   635: aconst_null    
        //   636: goto            650
        //   639: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   642: athrow         
        //   643: aload           13
        //   645: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   650: astore          14
        //   652: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.FORMAT_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   655: aload           14
        //   657: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   660: ifeq            1012
        //   663: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_IF_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   666: aload           14
        //   668: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   671: ifeq            816
        //   674: goto            681
        //   677: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   680: athrow         
        //   681: aload           13
        //   683: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //   688: aload           13
        //   690: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   695: ifne            816
        //   698: goto            705
        //   701: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   704: athrow         
        //   705: aload           13
        //   707: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   712: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   715: if_acmpne       816
        //   718: goto            725
        //   721: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   724: athrow         
        //   725: aload           12
        //   727: aload           13
        //   729: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //   734: aload           13
        //   736: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getEnd:()I
        //   741: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.a:(Ljava/lang/CharSequence;II)Z
        //   744: ifeq            816
        //   747: goto            754
        //   750: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   753: athrow         
        //   754: aload_3        
        //   755: aload           13
        //   757: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getEnd:()I
        //   762: aload           8
        //   764: aload           12
        //   766: aload           13
        //   768: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //   773: aload           13
        //   775: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getEnd:()I
        //   780: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   785: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   790: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.splitIndent:(Ljava/lang/String;)Lcom/intellij/openapi/util/Pair;
        //   793: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   796: checkcast       Ljava/lang/String;
        //   799: aload           13
        //   801: aload_1        
        //   802: iconst_0       
        //   803: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getDirectiveIndentFromAnchor:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;Lcom/intellij/psi/PsiFile;Z)Ljava/lang/String;
        //   806: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.a:(Lcom/intellij/openapi/util/Ref;ILcom/intellij/openapi/editor/Document;Ljava/lang/String;Ljava/lang/String;)V
        //   809: goto            816
        //   812: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   815: athrow         
        //   816: aload           10
        //   818: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   823: astore          15
        //   825: aload           10
        //   827: aload           9
        //   829: if_acmpeq       859
        //   832: new             Ljava/lang/StringBuilder;
        //   835: dup            
        //   836: invokespecial   java/lang/StringBuilder.<init>:()V
        //   839: aload           15
        //   841: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   844: aload           9
        //   846: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   851: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   854: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   857: astore          15
        //   859: aload           15
        //   861: invokevirtual   java/lang/String.length:()I
        //   864: iload           7
        //   866: aload           10
        //   868: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   873: isub           
        //   874: invokestatic    java/lang/Math.min:(II)I
        //   877: istore          16
        //   879: new             Ljava/lang/StringBuilder;
        //   882: dup            
        //   883: invokespecial   java/lang/StringBuilder.<init>:()V
        //   886: aload           15
        //   888: iconst_0       
        //   889: iload           16
        //   891: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   894: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   897: ldc             "\n\n"
        //   899: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   902: aload           15
        //   904: iload           16
        //   906: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   909: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   912: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   915: astore          17
        //   917: iinc            16, 1
        //   920: aload           10
        //   922: aload           17
        //   924: aload           10
        //   926: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   931: iload           16
        //   933: iadd           
        //   934: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.indentAtOffsetInNoncompiledCode:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;I)Ljava/lang/String;
        //   937: astore          18
        //   939: aload           18
        //   941: ifnull          1012
        //   944: aload_3        
        //   945: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   948: checkcast       Ljava/lang/Integer;
        //   951: invokevirtual   java/lang/Integer.intValue:()I
        //   954: istore          7
        //   956: new             Ljava/lang/StringBuilder;
        //   959: dup            
        //   960: invokespecial   java/lang/StringBuilder.<init>:()V
        //   963: ldc             "\n"
        //   965: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   968: aload           18
        //   970: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   973: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   976: astore          18
        //   978: aload           8
        //   980: iload           7
        //   982: aload           18
        //   984: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   989: aload_2        
        //   990: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   995: iload           7
        //   997: aload           18
        //   999: invokevirtual   java/lang/String.length:()I
        //  1002: iadd           
        //  1003: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //  1008: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Stop:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //  1011: areturn        
        //  1012: goto            1264
        //  1015: getstatic       com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.WHITE_SPACE_OR_COMMENT:Lcom/intellij/psi/tree/TokenSet;
        //  1018: aload           10
        //  1020: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //  1023: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1026: ifeq            1060
        //  1029: aload           10
        //  1031: bipush          10
        //  1033: invokeinterface com/intellij/psi/PsiElement.textContains:(C)Z
        //  1038: ifne            1060
        //  1041: goto            1048
        //  1044: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1047: athrow         
        //  1048: aload           10
        //  1050: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //  1055: astore          10
        //  1057: goto            1015
        //  1060: aload           10
        //  1062: ifnull          1098
        //  1065: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.FORMAT_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //  1068: aload           10
        //  1070: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //  1073: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1076: ifne            1098
        //  1079: goto            1086
        //  1082: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1085: athrow         
        //  1086: aload           10
        //  1088: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1093: astore          10
        //  1095: goto            1060
        //  1098: aload           10
        //  1100: ifnull          1264
        //  1103: aload           10
        //  1105: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //  1110: ifnull          1135
        //  1113: goto            1120
        //  1116: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1119: athrow         
        //  1120: aload           10
        //  1122: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //  1127: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //  1130: astore          11
        //  1132: goto            1142
        //  1135: aload           10
        //  1137: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //  1140: astore          11
        //  1142: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_IF_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //  1145: aload           11
        //  1147: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1150: ifeq            1252
        //  1153: aload           10
        //  1155: invokestatic    com/intellij/psi/util/PsiTreeUtil.prevLeaf:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //  1158: astore          10
        //  1160: aload           10
        //  1162: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //  1165: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //  1168: if_acmpne       1252
        //  1171: aload           10
        //  1173: bipush          10
        //  1175: invokeinterface com/intellij/psi/PsiElement.textContains:(C)Z
        //  1180: ifeq            1252
        //  1183: goto            1190
        //  1186: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1189: athrow         
        //  1190: aload           10
        //  1192: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //  1197: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //  1200: istore          12
        //  1202: aload_3        
        //  1203: iload           12
        //  1205: aload           8
        //  1207: aload           10
        //  1209: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //  1214: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.splitIndent:(Ljava/lang/String;)Lcom/intellij/openapi/util/Pair;
        //  1217: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //  1220: checkcast       Ljava/lang/String;
        //  1223: aload_2        
        //  1224: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //  1227: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //  1232: aload           10
        //  1234: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //  1239: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //  1244: aload_1        
        //  1245: iconst_0       
        //  1246: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getDirectiveIndentFromAnchor:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;Lcom/intellij/psi/PsiFile;Z)Ljava/lang/String;
        //  1249: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.a:(Lcom/intellij/openapi/util/Ref;ILcom/intellij/openapi/editor/Document;Ljava/lang/String;Ljava/lang/String;)V
        //  1252: aload_1        
        //  1253: aload_2        
        //  1254: aload           8
        //  1256: aload_3        
        //  1257: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/editor/Document;Lcom/intellij/openapi/util/Ref;)V
        //  1260: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Stop:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //  1263: areturn        
        //  1264: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Continue:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //  1267: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/util/Ref<Ljava/lang/Integer;>;Lcom/intellij/openapi/util/Ref<Ljava/lang/Integer;>;Lcom/intellij/openapi/actionSystem/DataContext;Lcom/intellij/openapi/editor/actionSystem/EditorActionHandler;)Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    173    173    177    Ljava/lang/IllegalArgumentException;
        //  177    218    218    222    Ljava/lang/IllegalArgumentException;
        //  234    268    268    272    Ljava/lang/IllegalArgumentException;
        //  280    298    301    305    Ljava/lang/IllegalArgumentException;
        //  293    312    312    316    Ljava/lang/IllegalArgumentException;
        //  329    372    375    379    Ljava/lang/IllegalArgumentException;
        //  395    409    412    416    Ljava/lang/IllegalArgumentException;
        //  403    421    424    428    Ljava/lang/IllegalArgumentException;
        //  416    436    436    440    Ljava/lang/IllegalArgumentException;
        //  467    493    496    500    Ljava/lang/IllegalArgumentException;
        //  477    527    530    534    Ljava/lang/IllegalArgumentException;
        //  500    541    544    548    Ljava/lang/IllegalArgumentException;
        //  548    580    583    587    Ljava/lang/IllegalArgumentException;
        //  558    597    597    601    Ljava/lang/IllegalArgumentException;
        //  601    628    631    635    Ljava/lang/IllegalArgumentException;
        //  611    639    639    643    Ljava/lang/IllegalArgumentException;
        //  652    674    677    681    Ljava/lang/IllegalArgumentException;
        //  663    698    701    705    Ljava/lang/IllegalArgumentException;
        //  681    718    721    725    Ljava/lang/IllegalArgumentException;
        //  705    747    750    754    Ljava/lang/IllegalArgumentException;
        //  725    809    812    816    Ljava/lang/IllegalArgumentException;
        //  1015   1041   1044   1048   Ljava/lang/IllegalArgumentException;
        //  1060   1079   1082   1086   Ljava/lang/IllegalArgumentException;
        //  1098   1113   1116   1120   Ljava/lang/IllegalArgumentException;
        //  1160   1183   1186   1190   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0416:
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
    
    private static void a(@NotNull final PsiFile psiFile, @NotNull final Editor editor, @NotNull final Document document, @NotNull final Ref<Integer> ref) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler", "doEasyEnter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler", "doEasyEnter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler", "doEasyEnter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretOffsetRef", "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler", "doEasyEnter"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        int intValue = (int)ref.get();
        final int lineNumber = document.getLineNumber(intValue);
        document.insertString(intValue++, (CharSequence)"\n");
        editor.getCaretModel().moveToOffset(intValue);
        final String lineIndent = CodeStyleManager.getInstance(psiFile.getProject()).getLineIndent(psiFile, Math.max(document.getLineStartOffset(lineNumber) - 1, 0));
        if (lineIndent != null) {
            final OCMultilineNodeFormatter create = OCMultilineNodeFormatter.create(psiFile.getProject());
            final String stringIndent = create.getStringIndent(create.indent(lineIndent));
            document.insertString(intValue, (CharSequence)stringIndent);
            editor.getCaretModel().moveToOffset(intValue + stringIndent.length());
        }
    }
    
    private static void a(@NotNull final Ref<Integer> ref, final int n, @NotNull final Document document, @NotNull final String s, @Nullable final String s2) {
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretOffsetRef", "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler", "adjustLineIndent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler", "adjustLineIndent"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "originalIndent", "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler", "adjustLineIndent"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        Label_0153: {
            try {
                if (s2 == null) {
                    return;
                }
                final String s3 = s2;
                final String s4 = s;
                final boolean b = s3.equals(s4);
                if (!b) {
                    break Label_0153;
                }
                return;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                final String s3 = s2;
                final String s4 = s;
                final boolean b = s3.equals(s4);
                if (!b) {
                    document.replaceString(n - s.length(), n, (CharSequence)s2);
                    ref.set((Object)((int)ref.get() + s2.length() - s.length()));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
    }
    
    private static boolean a(@NotNull final CharSequence charSequence, final int n, final int n2) {
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/editor/OCEnterInDirectiveAndNoncompiledHandler", "containsNewLine"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return StringUtil.contains(charSequence, n, n2, '\n');
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCEnterInDirectiveAndNoncompiledHandler.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        WHITE_SPACE_OR_COMMENT = TokenSet.create(new IElementType[] { TokenType.WHITE_SPACE, OCTokenTypes.BLOCK_COMMENT, OCTokenTypes.EOL_COMMENT });
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
