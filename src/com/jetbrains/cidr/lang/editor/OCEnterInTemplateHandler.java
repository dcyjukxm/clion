// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.openapi.editor.ex.util.LexerEditorHighlighter;
import java.util.List;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Arrays;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.formatting.Indent;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter;

public class OCEnterInTemplateHandler extends EnterHandlerDelegateAdapter
{
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
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "preprocessEnter"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "preprocessEnter"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   112: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "preprocessEnter"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   157: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "preprocessEnter"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   202: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler"
        //   204: aastore        
        //   205: dup            
        //   206: ldc             2
        //   208: ldc             "preprocessEnter"
        //   210: aastore        
        //   211: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   214: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   217: athrow         
        //   218: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_3        
        //   223: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   226: checkcast       Ljava/lang/Integer;
        //   229: invokevirtual   java/lang/Integer.intValue:()I
        //   232: istore          7
        //   234: aload_2        
        //   235: invokeinterface com/intellij/openapi/editor/Editor.getProject:()Lcom/intellij/openapi/project/Project;
        //   240: astore          8
        //   242: aload_2        
        //   243: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   248: astore          9
        //   250: aload_1        
        //   251: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   256: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   259: aload           9
        //   261: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   264: aload           8
        //   266: ifnull          305
        //   269: aload           8
        //   271: invokeinterface com/intellij/openapi/project/Project.isDisposed:()Z
        //   276: ifne            305
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: aload_1        
        //   287: iload           7
        //   289: invokestatic    com/intellij/psi/util/PsiUtilCore.getLanguageAtOffset:(Lcom/intellij/psi/PsiFile;I)Lcom/intellij/lang/Language;
        //   292: instanceof      Lcom/jetbrains/cidr/lang/OCLanguage;
        //   295: ifne            313
        //   298: goto            305
        //   301: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   304: athrow         
        //   305: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Continue:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //   308: areturn        
        //   309: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: iload           7
        //   315: aload           9
        //   317: invokeinterface com/intellij/openapi/editor/Document.getTextLength:()I
        //   322: if_icmpne       345
        //   325: iload           7
        //   327: ifle            345
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: iconst_1       
        //   338: goto            346
        //   341: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: iconst_0       
        //   346: istore          10
        //   348: aload_1        
        //   349: iload           10
        //   351: ifeq            365
        //   354: iload           7
        //   356: iconst_1       
        //   357: isub           
        //   358: goto            367
        //   361: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   364: athrow         
        //   365: iload           7
        //   367: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   372: astore          11
        //   374: aload           11
        //   376: ifnonnull       387
        //   379: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Continue:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //   382: areturn        
        //   383: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: aload           11
        //   389: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   392: astore          12
        //   394: aload           11
        //   396: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   401: astore          13
        //   403: aload           13
        //   405: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   408: istore          14
        //   410: aload           13
        //   412: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   415: istore          15
        //   417: iload           14
        //   419: iload           7
        //   421: if_icmpne       432
        //   424: iconst_1       
        //   425: goto            433
        //   428: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   431: athrow         
        //   432: iconst_0       
        //   433: istore          16
        //   435: iload           15
        //   437: iload           7
        //   439: if_icmpne       450
        //   442: iconst_1       
        //   443: goto            451
        //   446: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   449: athrow         
        //   450: iconst_0       
        //   451: istore          17
        //   453: aload           12
        //   455: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   458: if_acmpne       469
        //   461: iconst_1       
        //   462: goto            470
        //   465: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   468: athrow         
        //   469: iconst_0       
        //   470: istore          18
        //   472: aconst_null    
        //   473: astore          19
        //   475: iload           18
        //   477: ifne            564
        //   480: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   483: aload           12
        //   485: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   488: ifeq            522
        //   491: goto            498
        //   494: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   497: athrow         
        //   498: iload           17
        //   500: ifne            564
        //   503: goto            510
        //   506: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   509: athrow         
        //   510: iload           16
        //   512: ifne            564
        //   515: goto            522
        //   518: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   521: athrow         
        //   522: aload           12
        //   524: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   527: if_acmpeq       552
        //   530: goto            537
        //   533: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   536: athrow         
        //   537: aload           12
        //   539: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   542: if_acmpne       600
        //   545: goto            552
        //   548: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   551: athrow         
        //   552: iload           16
        //   554: ifeq            600
        //   557: goto            564
        //   560: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   563: athrow         
        //   564: aload_2        
        //   565: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //   568: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //   573: iload           7
        //   575: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //   580: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.needEnterTreatAfterTemplateList:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)I
        //   583: istore          20
        //   585: iload           20
        //   587: iflt            600
        //   590: aload_1        
        //   591: iload           20
        //   593: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   598: astore          19
        //   600: aload           19
        //   602: ifnull          865
        //   605: aload           19
        //   607: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   612: astore          20
        //   614: aload           20
        //   616: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   619: astore          21
        //   621: aload_2        
        //   622: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //   625: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //   630: iload           14
        //   632: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //   637: iconst_1       
        //   638: anewarray       Lcom/intellij/psi/tree/IElementType;
        //   641: dup            
        //   642: iconst_0       
        //   643: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   646: aastore        
        //   647: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.a:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;[Lcom/intellij/psi/tree/IElementType;)Z
        //   650: istore          22
        //   652: iload           22
        //   654: ifne            710
        //   657: aload           21
        //   659: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   662: if_acmpeq       702
        //   665: goto            672
        //   668: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   671: athrow         
        //   672: aload           21
        //   674: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   677: if_acmpeq       702
        //   680: goto            687
        //   683: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   686: athrow         
        //   687: aload           21
        //   689: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   692: if_acmpne       710
        //   695: goto            702
        //   698: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   701: athrow         
        //   702: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Continue:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //   705: areturn        
        //   706: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   709: athrow         
        //   710: aload           8
        //   712: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   715: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   717: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   720: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   723: astore          23
        //   725: aload           23
        //   727: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_DECLARATION_STRUCT_BODY_INDENT:Z
        //   730: ifeq            756
        //   733: aload           23
        //   735: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_DECLARATION_FUNCTION_BODY_INDENT:Z
        //   738: ifeq            756
        //   741: goto            748
        //   744: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   747: athrow         
        //   748: iconst_1       
        //   749: goto            757
        //   752: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   755: athrow         
        //   756: iconst_0       
        //   757: istore          24
        //   759: iload           22
        //   761: ifeq            820
        //   764: iload           24
        //   766: aload           11
        //   768: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   771: ifeq            816
        //   774: goto            781
        //   777: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   780: athrow         
        //   781: aload           11
        //   783: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   788: bipush          10
        //   790: iload           7
        //   792: iload           15
        //   794: isub           
        //   795: invokevirtual   java/lang/String.indexOf:(II)I
        //   798: iflt            816
        //   801: goto            808
        //   804: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   807: athrow         
        //   808: iconst_1       
        //   809: goto            817
        //   812: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   815: athrow         
        //   816: iconst_0       
        //   817: iand           
        //   818: istore          24
        //   820: aload_1        
        //   821: aload_2        
        //   822: aload           4
        //   824: aload           5
        //   826: aload           6
        //   828: aload           8
        //   830: aload           9
        //   832: aload           11
        //   834: iload           24
        //   836: ifeq            849
        //   839: getstatic       com/intellij/formatting/Indent$Type.NORMAL:Lcom/intellij/formatting/Indent$Type;
        //   842: goto            852
        //   845: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   848: athrow         
        //   849: getstatic       com/intellij/formatting/Indent$Type.NONE:Lcom/intellij/formatting/Indent$Type;
        //   852: iload           7
        //   854: iload           15
        //   856: iload           18
        //   858: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/actionSystem/DataContext;Lcom/intellij/openapi/editor/actionSystem/EditorActionHandler;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Document;Lcom/intellij/psi/PsiElement;Lcom/intellij/formatting/Indent$Type;IIZ)V
        //   861: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Stop:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //   864: areturn        
        //   865: aconst_null    
        //   866: astore          20
        //   868: iload           18
        //   870: ifne            984
        //   873: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   876: aload           12
        //   878: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   881: ifeq            915
        //   884: goto            891
        //   887: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   890: athrow         
        //   891: iload           17
        //   893: ifne            984
        //   896: goto            903
        //   899: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   902: athrow         
        //   903: iload           16
        //   905: ifne            984
        //   908: goto            915
        //   911: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   914: athrow         
        //   915: aload           12
        //   917: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   920: if_acmpne       942
        //   923: goto            930
        //   926: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   929: athrow         
        //   930: iload           16
        //   932: ifne            984
        //   935: goto            942
        //   938: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   941: athrow         
        //   942: aload           12
        //   944: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   947: if_acmpeq       972
        //   950: goto            957
        //   953: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   956: athrow         
        //   957: aload           12
        //   959: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   962: if_acmpne       1077
        //   965: goto            972
        //   968: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   971: athrow         
        //   972: iload           17
        //   974: ifeq            1077
        //   977: goto            984
        //   980: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   983: athrow         
        //   984: aload_2        
        //   985: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //   988: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //   993: aload           12
        //   995: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   998: if_acmpeq       1023
        //  1001: goto            1008
        //  1004: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1007: athrow         
        //  1008: aload           12
        //  1010: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1013: if_acmpne       1034
        //  1016: goto            1023
        //  1019: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1022: athrow         
        //  1023: iload           7
        //  1025: iconst_1       
        //  1026: isub           
        //  1027: goto            1036
        //  1030: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1033: athrow         
        //  1034: iload           7
        //  1036: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //  1041: iconst_2       
        //  1042: anewarray       Lcom/intellij/psi/tree/IElementType;
        //  1045: dup            
        //  1046: iconst_0       
        //  1047: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1050: aastore        
        //  1051: dup            
        //  1052: iconst_1       
        //  1053: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1056: aastore        
        //  1057: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;[Lcom/intellij/psi/tree/IElementType;)I
        //  1060: istore          21
        //  1062: iload           21
        //  1064: iflt            1077
        //  1067: aload_1        
        //  1068: iload           21
        //  1070: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //  1075: astore          20
        //  1077: aload           20
        //  1079: ifnull          1250
        //  1082: aload           20
        //  1084: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1089: astore          21
        //  1091: aload           21
        //  1093: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //  1096: astore          22
        //  1098: aload           12
        //  1100: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1103: if_acmpeq       1166
        //  1106: aload           12
        //  1108: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1111: if_acmpeq       1166
        //  1114: goto            1121
        //  1117: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1120: athrow         
        //  1121: aload_2        
        //  1122: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //  1125: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //  1130: iload           14
        //  1132: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //  1137: iconst_2       
        //  1138: anewarray       Lcom/intellij/psi/tree/IElementType;
        //  1141: dup            
        //  1142: iconst_0       
        //  1143: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1146: aastore        
        //  1147: dup            
        //  1148: iconst_1       
        //  1149: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1152: aastore        
        //  1153: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.a:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;[Lcom/intellij/psi/tree/IElementType;)Z
        //  1156: ifeq            1174
        //  1159: goto            1166
        //  1162: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1165: athrow         
        //  1166: iconst_1       
        //  1167: goto            1175
        //  1170: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1173: athrow         
        //  1174: iconst_0       
        //  1175: istore          23
        //  1177: iload           23
        //  1179: ifeq            1212
        //  1182: aload           22
        //  1184: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1187: if_acmpeq       1220
        //  1190: goto            1197
        //  1193: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1196: athrow         
        //  1197: aload           22
        //  1199: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1202: if_acmpeq       1220
        //  1205: goto            1212
        //  1208: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1211: athrow         
        //  1212: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Continue:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //  1215: areturn        
        //  1216: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1219: athrow         
        //  1220: aload_1        
        //  1221: aload_2        
        //  1222: aload           4
        //  1224: aload           5
        //  1226: aload           6
        //  1228: aload           8
        //  1230: aload           9
        //  1232: aload           11
        //  1234: getstatic       com/intellij/formatting/Indent$Type.CONTINUATION:Lcom/intellij/formatting/Indent$Type;
        //  1237: iload           7
        //  1239: iload           15
        //  1241: iload           18
        //  1243: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/actionSystem/DataContext;Lcom/intellij/openapi/editor/actionSystem/EditorActionHandler;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Document;Lcom/intellij/psi/PsiElement;Lcom/intellij/formatting/Indent$Type;IIZ)V
        //  1246: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Stop:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //  1249: areturn        
        //  1250: getstatic       com/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result.Continue:Lcom/intellij/codeInsight/editorActions/enter/EnterHandlerDelegate$Result;
        //  1253: areturn        
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
        //  250    279    282    286    Ljava/lang/IllegalArgumentException;
        //  269    298    301    305    Ljava/lang/IllegalArgumentException;
        //  286    309    309    313    Ljava/lang/IllegalArgumentException;
        //  313    330    333    337    Ljava/lang/IllegalArgumentException;
        //  325    341    341    345    Ljava/lang/IllegalArgumentException;
        //  348    361    361    365    Ljava/lang/IllegalArgumentException;
        //  374    383    383    387    Ljava/lang/IllegalArgumentException;
        //  417    428    428    432    Ljava/lang/IllegalArgumentException;
        //  435    446    446    450    Ljava/lang/IllegalArgumentException;
        //  453    465    465    469    Ljava/lang/IllegalArgumentException;
        //  475    491    494    498    Ljava/lang/IllegalArgumentException;
        //  480    503    506    510    Ljava/lang/IllegalArgumentException;
        //  498    515    518    522    Ljava/lang/IllegalArgumentException;
        //  510    530    533    537    Ljava/lang/IllegalArgumentException;
        //  522    545    548    552    Ljava/lang/IllegalArgumentException;
        //  537    557    560    564    Ljava/lang/IllegalArgumentException;
        //  652    665    668    672    Ljava/lang/IllegalArgumentException;
        //  657    680    683    687    Ljava/lang/IllegalArgumentException;
        //  672    695    698    702    Ljava/lang/IllegalArgumentException;
        //  687    706    706    710    Ljava/lang/IllegalArgumentException;
        //  725    741    744    748    Ljava/lang/IllegalArgumentException;
        //  733    752    752    756    Ljava/lang/IllegalArgumentException;
        //  759    774    777    781    Ljava/lang/IllegalArgumentException;
        //  764    801    804    808    Ljava/lang/IllegalArgumentException;
        //  781    812    812    816    Ljava/lang/IllegalArgumentException;
        //  820    845    845    849    Ljava/lang/IllegalArgumentException;
        //  868    884    887    891    Ljava/lang/IllegalArgumentException;
        //  873    896    899    903    Ljava/lang/IllegalArgumentException;
        //  891    908    911    915    Ljava/lang/IllegalArgumentException;
        //  903    923    926    930    Ljava/lang/IllegalArgumentException;
        //  915    935    938    942    Ljava/lang/IllegalArgumentException;
        //  930    950    953    957    Ljava/lang/IllegalArgumentException;
        //  942    965    968    972    Ljava/lang/IllegalArgumentException;
        //  957    977    980    984    Ljava/lang/IllegalArgumentException;
        //  972    1001   1004   1008   Ljava/lang/IllegalArgumentException;
        //  984    1016   1019   1023   Ljava/lang/IllegalArgumentException;
        //  1008   1030   1030   1034   Ljava/lang/IllegalArgumentException;
        //  1098   1114   1117   1121   Ljava/lang/IllegalArgumentException;
        //  1106   1159   1162   1166   Ljava/lang/IllegalArgumentException;
        //  1121   1170   1170   1174   Ljava/lang/IllegalArgumentException;
        //  1177   1190   1193   1197   Ljava/lang/IllegalArgumentException;
        //  1182   1205   1208   1212   Ljava/lang/IllegalArgumentException;
        //  1197   1216   1216   1220   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0286:
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
    
    private static void a(@NotNull final PsiFile psiFile, @NotNull final Editor editor, @NotNull final Ref<Integer> ref, @NotNull final DataContext dataContext, @NotNull final EditorActionHandler editorActionHandler, @NotNull final Project project, @NotNull final Document document, @NotNull final PsiElement psiElement, @NotNull final Indent.Type type, final int n, final int n2, final boolean b) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretAdvanceRef", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (editorActionHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "originalHandler", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiAtCaret", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex8) {
            throw b(ex8);
        }
        try {
            if (type == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indent", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "insertIndent"));
            }
        }
        catch (IllegalArgumentException ex9) {
            throw b(ex9);
        }
        Label_0498: {
            try {
                if (!CodeInsightSettings.getInstance().SMART_INDENT_ON_ENTER || !b) {
                    break Label_0498;
                }
            }
            catch (IllegalArgumentException ex10) {
                throw b(ex10);
            }
            final String text = psiElement.getText();
            final int length = text.length();
            int n3 = n - n2;
        Label_0484:
            while (true) {
                Label_0474: {
                    try {
                        if (n3 >= length) {
                            break Label_0484;
                        }
                        final String s = "\t ";
                        final String s2 = text;
                        final int n4 = n3;
                        final char c = s2.charAt(n4);
                        final int n5 = s.indexOf(c);
                        if (n5 >= 0) {
                            break Label_0474;
                        }
                        break Label_0484;
                    }
                    catch (IllegalArgumentException ex11) {
                        throw b(ex11);
                    }
                    try {
                        final String s = "\t ";
                        final String s2 = text;
                        final int n4 = n3;
                        final char c = s2.charAt(n4);
                        final int n5 = s.indexOf(c);
                        if (n5 >= 0) {
                            ++n3;
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw b(ex12);
                    }
                }
                break;
            }
            document.deleteString(n, n2 + n3);
        }
        final CaretModel caretModel = editor.getCaretModel();
        editorActionHandler.execute(editor, caretModel.getCurrentCaret(), dataContext);
        if (type != Indent.Type.NONE) {
            final CommonCodeStyleSettings.IndentOptions indentOptionsByFile = CodeStyleSettingsManager.getSettings(project).getIndentOptionsByFile(psiFile);
            int n6 = 0;
            Label_0564: {
                try {
                    if (type == Indent.Type.CONTINUATION) {
                        n6 = indentOptionsByFile.CONTINUATION_INDENT_SIZE;
                        break Label_0564;
                    }
                }
                catch (IllegalArgumentException ex13) {
                    throw b(ex13);
                }
                n6 = indentOptionsByFile.INDENT_SIZE;
            }
            final int n7 = n6;
            document.insertString(caretModel.getOffset(), (CharSequence)StringUtil.repeatSymbol(' ', n7));
            ref.set((Object)((int)ref.get() + n7));
        }
    }
    
    private static boolean a(@NotNull final HighlighterIterator highlighterIterator, @NotNull final IElementType... array) {
        try {
            if (highlighterIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "nextIsStopType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopTypes", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "nextIsStopType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final List<IElementType> list = Arrays.asList(array);
        while (!highlighterIterator.atEnd()) {
            final IElementType tokenType = highlighterIterator.getTokenType();
            try {
                if (list.contains(tokenType)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                if (!OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(tokenType)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            highlighterIterator.advance();
        }
        return false;
    }
    
    private static int b(@NotNull final HighlighterIterator highlighterIterator, @NotNull final IElementType... array) {
        try {
            if (highlighterIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "offsetOfStopType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopTypes", "com/jetbrains/cidr/lang/editor/OCEnterInTemplateHandler", "offsetOfStopType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final List<IElementType> list = Arrays.asList(array);
        while (((LexerEditorHighlighter.HighlighterIteratorImpl)highlighterIterator).currentIndex() >= 0) {
            final IElementType tokenType = highlighterIterator.getTokenType();
            try {
                if (list.contains(tokenType)) {
                    return highlighterIterator.getStart();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                if (!OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(tokenType)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            highlighterIterator.retreat();
        }
        return -1;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
