// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.lang.BracePair;
import java.util.ArrayList;
import com.intellij.psi.impl.PsiDocumentManagerImpl;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.psi.PsiErrorElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Condition;
import com.intellij.codeInsight.AutoPopupController;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.ex.EditorEx;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCKeywordElementType;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.openapi.editor.ex.util.LexerEditorHighlighter;
import com.jetbrains.cidr.lang.editor.typing.OCHighlightingTokenIterator;
import com.intellij.psi.TokenType;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCLanguageKindCalculator;
import com.intellij.openapi.project.DumbService;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.actionSystem.ex.ActionManagerEx;
import com.jetbrains.cidr.lang.editor.typing.OCBackwardStructureDetector;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;

public class OCTypedHandlerDelegate extends TypedHandlerDelegate
{
    private static boolean DO_STAR_OVERTYPE;
    private static boolean DO_COLON_OVERTYPE;
    private boolean myMemoSetting;
    private boolean myBalanceOnRightBracket;
    public static final TokenSet STOPSET;
    private static final TokenSet OUT_OF_PAREN_STOPSET;
    private static final IElementType[] BLOCK_TYPE_STOP_SET;
    protected static final OCBackwardStructureDetector NUMBER_LITERAL_DETECTOR;
    protected static final OCBackwardStructureDetector STRING_LITERAL_DETECTOR;
    public static final OCBackwardStructureDetector ARRAY_LITERAL_DETECTOR;
    public static final OCBackwardStructureDetector BRACED_LITERAL_DETECTOR;
    public static final OCBackwardStructureDetector OBJC_LITERAL_DETECTOR;
    private static final TokenSet NON_SEMICOLON_BLOCK_OWNERS;
    
    public OCTypedHandlerDelegate() {
        ActionManagerEx.getInstanceEx().addAnActionListener((AnActionListener)new AnActionListener.Adapter() {
            public void beforeActionPerformed(final AnAction anAction, final DataContext dataContext, final AnActionEvent anActionEvent) {
                OCTypedHandlerDelegate.DO_STAR_OVERTYPE = false;
                OCTypedHandlerDelegate.DO_COLON_OVERTYPE = false;
            }
        });
    }
    
    public static void overTypeNextStar() {
        OCTypedHandlerDelegate.DO_STAR_OVERTYPE = true;
    }
    
    public static void overTypeNextColon() {
        OCTypedHandlerDelegate.DO_COLON_OVERTYPE = true;
    }
    
    public TypedHandlerDelegate.Result beforeCharTyped(final char p0, final Project p1, final Editor p2, final PsiFile p3, final FileType p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: invokeinterface com/intellij/psi/PsiFile.getFileType:()Lcom/intellij/openapi/fileTypes/FileType;
        //     7: getstatic       com/jetbrains/cidr/lang/OCFileType.INSTANCE:Lcom/jetbrains/cidr/lang/OCFileType;
        //    10: if_acmpeq       21
        //    13: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //    16: areturn        
        //    17: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: iload_1        
        //    22: bipush          34
        //    24: if_icmpne       67
        //    27: aload_3        
        //    28: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    33: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    38: istore          6
        //    40: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    43: aload_0        
        //    44: aload_2        
        //    45: aload_3        
        //    46: iload           6
        //    48: invokedynamic   run:(Lcom/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;I)Ljava/lang/Runnable;
        //    53: aload_2        
        //    54: invokeinterface com/intellij/openapi/project/Project.getDisposed:()Lcom/intellij/openapi/util/Condition;
        //    59: invokeinterface com/intellij/openapi/application/Application.invokeLater:(Ljava/lang/Runnable;Lcom/intellij/openapi/util/Condition;)V
        //    64: goto            302
        //    67: iload_1        
        //    68: bipush          93
        //    70: if_icmpne       302
        //    73: invokestatic    com/intellij/codeInsight/CodeInsightSettings.getInstance:()Lcom/intellij/codeInsight/CodeInsightSettings;
        //    76: getfield        com/intellij/codeInsight/CodeInsightSettings.AUTOINSERT_PAIR_BRACKET:Z
        //    79: istore          6
        //    81: aload_0        
        //    82: iload           6
        //    84: putfield        com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.myMemoSetting:Z
        //    87: aload_3        
        //    88: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    93: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    98: istore          7
        //   100: iload           7
        //   102: ifne            113
        //   105: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   108: areturn        
        //   109: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload           4
        //   115: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Lcom/intellij/psi/PsiFile;)Z
        //   118: ifeq            152
        //   121: iload           6
        //   123: ifeq            165
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_3        
        //   134: iload           7
        //   136: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   139: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Lcom/intellij/openapi/editor/Editor;ILcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   142: ifeq            165
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_0        
        //   153: iconst_0       
        //   154: putfield        com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.myBalanceOnRightBracket:Z
        //   157: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   160: areturn        
        //   161: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   164: athrow         
        //   165: aload_0        
        //   166: iconst_1       
        //   167: putfield        com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.myBalanceOnRightBracket:Z
        //   170: aload_3        
        //   171: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //   174: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //   179: iload           7
        //   181: iconst_1       
        //   182: isub           
        //   183: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //   188: astore          8
        //   190: iconst_0       
        //   191: istore          9
        //   193: iconst_0       
        //   194: istore          10
        //   196: aload           8
        //   198: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   203: ifne            295
        //   206: aload           8
        //   208: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   213: astore          11
        //   215: aload           11
        //   217: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   220: if_acmpne       231
        //   223: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   226: areturn        
        //   227: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   234: aload           11
        //   236: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   239: istore          12
        //   241: iload           12
        //   243: ifne            278
        //   246: iload           10
        //   248: ifeq            278
        //   251: goto            258
        //   254: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   257: athrow         
        //   258: iload           9
        //   260: iconst_2       
        //   261: if_icmplt       278
        //   264: goto            271
        //   267: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   270: athrow         
        //   271: goto            295
        //   274: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   277: athrow         
        //   278: aload           8
        //   280: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //   285: iinc            9, 1
        //   288: iload           12
        //   290: istore          10
        //   292: goto            196
        //   295: invokestatic    com/intellij/codeInsight/CodeInsightSettings.getInstance:()Lcom/intellij/codeInsight/CodeInsightSettings;
        //   298: iconst_0       
        //   299: putfield        com/intellij/codeInsight/CodeInsightSettings.AUTOINSERT_PAIR_BRACKET:Z
        //   302: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   305: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     17     21     Ljava/lang/IllegalArgumentException;
        //  100    109    109    113    Ljava/lang/IllegalArgumentException;
        //  113    126    129    133    Ljava/lang/IllegalArgumentException;
        //  121    145    148    152    Ljava/lang/IllegalArgumentException;
        //  133    161    161    165    Ljava/lang/IllegalArgumentException;
        //  215    227    227    231    Ljava/lang/IllegalArgumentException;
        //  241    251    254    258    Ljava/lang/IllegalArgumentException;
        //  246    264    267    271    Ljava/lang/IllegalArgumentException;
        //  258    274    274    278    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036_1_1_1:
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
    
    private static boolean a(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate", "isBracketBalancingSupported"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(psiFile instanceof OCFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        OCLanguageKind ocLanguageKind;
        if (DumbService.getInstance(psiFile.getProject()).isDumb()) {
            ocLanguageKind = OCLanguageKindCalculator.calculateLanguageKindFast(psiFile);
        }
        else {
            ocLanguageKind = ((OCFile)psiFile).getKind();
        }
        return ocLanguageKind.isObjC();
    }
    
    public TypedHandlerDelegate.Result charTyped(final char p0, final Project p1, @NotNull final Editor p2, @NotNull final PsiFile p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
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
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "charTyped"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload           4
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "file"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "charTyped"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload           4
        //    91: invokeinterface com/intellij/psi/PsiFile.getFileType:()Lcom/intellij/openapi/fileTypes/FileType;
        //    96: getstatic       com/jetbrains/cidr/lang/OCFileType.INSTANCE:Lcom/jetbrains/cidr/lang/OCFileType;
        //    99: if_acmpeq       110
        //   102: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   105: areturn        
        //   106: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_3        
        //   111: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   116: astore          5
        //   118: aload_3        
        //   119: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   124: astore          6
        //   126: iload_1        
        //   127: bipush          42
        //   129: if_icmpne       209
        //   132: getstatic       com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.DO_STAR_OVERTYPE:Z
        //   135: ifeq            209
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: iconst_0       
        //   146: putstatic       com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.DO_STAR_OVERTYPE:Z
        //   149: aload           6
        //   151: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   156: iconst_1       
        //   157: isub           
        //   158: istore          7
        //   160: aload           5
        //   162: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   167: astore          8
        //   169: aload           8
        //   171: iload           7
        //   173: iconst_1       
        //   174: isub           
        //   175: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   180: bipush          42
        //   182: if_icmpne       206
        //   185: aload           5
        //   187: iload           7
        //   189: iload           7
        //   191: iconst_1       
        //   192: iadd           
        //   193: invokeinterface com/intellij/openapi/editor/Document.deleteString:(II)V
        //   198: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.STOP:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   201: areturn        
        //   202: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: goto            1980
        //   209: iload_1        
        //   210: bipush          58
        //   212: if_icmpne       315
        //   215: getstatic       com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.DO_COLON_OVERTYPE:Z
        //   218: ifeq            315
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: aload           6
        //   230: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   235: iconst_1       
        //   236: isub           
        //   237: istore          7
        //   239: aload           5
        //   241: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   246: astore          8
        //   248: aload           8
        //   250: iload           7
        //   252: iconst_1       
        //   253: isub           
        //   254: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   259: bipush          58
        //   261: if_icmpne       312
        //   264: aload           8
        //   266: iload           7
        //   268: iconst_2       
        //   269: isub           
        //   270: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   275: bipush          58
        //   277: if_icmpne       312
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: aload           5
        //   289: iload           7
        //   291: iload           7
        //   293: iconst_1       
        //   294: iadd           
        //   295: invokeinterface com/intellij/openapi/editor/Document.deleteString:(II)V
        //   300: iconst_0       
        //   301: putstatic       com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.DO_COLON_OVERTYPE:Z
        //   304: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.STOP:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   307: areturn        
        //   308: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: goto            1980
        //   315: iload_1        
        //   316: bipush          59
        //   318: if_icmpne       366
        //   321: aload           6
        //   323: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   328: istore          7
        //   330: aload_3        
        //   331: iload           7
        //   333: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   336: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Lcom/intellij/openapi/editor/Editor;ILcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   339: ifeq            363
        //   342: aload           5
        //   344: iload           7
        //   346: iload           7
        //   348: iconst_1       
        //   349: iadd           
        //   350: invokeinterface com/intellij/openapi/editor/Document.deleteString:(II)V
        //   355: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.STOP:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   358: areturn        
        //   359: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: goto            1980
        //   366: iload_1        
        //   367: bipush          93
        //   369: if_icmpne       1616
        //   372: aload_0        
        //   373: getfield        com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.myBalanceOnRightBracket:Z
        //   376: ifeq            1616
        //   379: goto            386
        //   382: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   385: athrow         
        //   386: invokestatic    com/intellij/codeInsight/CodeInsightSettings.getInstance:()Lcom/intellij/codeInsight/CodeInsightSettings;
        //   389: aload_0        
        //   390: getfield        com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.myMemoSetting:Z
        //   393: putfield        com/intellij/codeInsight/CodeInsightSettings.AUTOINSERT_PAIR_BRACKET:Z
        //   396: aload           6
        //   398: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   403: iconst_1       
        //   404: isub           
        //   405: istore          7
        //   407: aload_3        
        //   408: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //   411: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //   416: iconst_0       
        //   417: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //   422: astore          8
        //   424: iconst_0       
        //   425: istore          9
        //   427: iconst_0       
        //   428: istore          10
        //   430: aload           8
        //   432: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   437: ifne            515
        //   440: aload           8
        //   442: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //   447: istore          11
        //   449: iload           11
        //   451: iload           7
        //   453: if_icmpne       460
        //   456: iload           9
        //   458: istore          10
        //   460: aload           8
        //   462: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   467: astore          12
        //   469: aload           12
        //   471: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   474: if_acmpne       487
        //   477: iinc            9, 1
        //   480: goto            487
        //   483: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: aload           12
        //   489: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   492: if_acmpne       505
        //   495: iinc            9, -1
        //   498: goto            505
        //   501: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   504: athrow         
        //   505: aload           8
        //   507: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.advance:()V
        //   512: goto            430
        //   515: iload           9
        //   517: iflt            528
        //   520: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   523: areturn        
        //   524: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   527: athrow         
        //   528: aload_3        
        //   529: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //   532: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //   537: iload           7
        //   539: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //   544: astore          11
        //   546: iconst_0       
        //   547: istore          12
        //   549: iconst_0       
        //   550: istore          13
        //   552: iconst_0       
        //   553: istore          14
        //   555: aload           11
        //   557: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   562: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   565: if_acmpne       1613
        //   568: aload           11
        //   570: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   575: ifeq            593
        //   578: goto            585
        //   581: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   584: athrow         
        //   585: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   588: areturn        
        //   589: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   592: athrow         
        //   593: aload           11
        //   595: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   600: astore          15
        //   602: aload           11
        //   604: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.b:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)Z
        //   607: ifeq            634
        //   610: iload           12
        //   612: ifne            568
        //   615: goto            622
        //   618: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: iload           13
        //   624: iconst_1       
        //   625: if_icmpne       568
        //   628: iinc            14, 1
        //   631: goto            568
        //   634: aload           15
        //   636: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   639: if_acmpne       667
        //   642: iload           12
        //   644: ifne            661
        //   647: goto            654
        //   650: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   653: athrow         
        //   654: goto            1320
        //   657: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   660: athrow         
        //   661: iinc            12, -1
        //   664: goto            1310
        //   667: aload           15
        //   669: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   672: if_acmpne       685
        //   675: iinc            12, 1
        //   678: goto            1310
        //   681: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   684: athrow         
        //   685: aload           15
        //   687: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   690: if_acmpne       751
        //   693: iinc            13, -1
        //   696: iload           12
        //   698: ifne            1310
        //   701: goto            708
        //   704: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   707: athrow         
        //   708: iload           13
        //   710: iconst_1       
        //   711: if_icmpne       1310
        //   714: goto            721
        //   717: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   720: athrow         
        //   721: aload_3        
        //   722: aload           11
        //   724: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //   729: iload           7
        //   731: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Lcom/intellij/openapi/editor/Editor;II)Z
        //   734: ifeq            1310
        //   737: goto            744
        //   740: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   743: athrow         
        //   744: goto            1320
        //   747: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   750: athrow         
        //   751: aload           15
        //   753: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   756: if_acmpne       800
        //   759: iload           12
        //   761: ifne            794
        //   764: goto            771
        //   767: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   770: athrow         
        //   771: iload           13
        //   773: iconst_1       
        //   774: if_icmpne       794
        //   777: goto            784
        //   780: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   783: athrow         
        //   784: iinc            14, 1
        //   787: goto            794
        //   790: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   793: athrow         
        //   794: iinc            13, 1
        //   797: goto            1310
        //   800: aload           15
        //   802: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   805: if_acmpeq       823
        //   808: aload           15
        //   810: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   813: if_acmpne       858
        //   816: goto            823
        //   819: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   822: athrow         
        //   823: iload           12
        //   825: ifne            1310
        //   828: goto            835
        //   831: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   834: athrow         
        //   835: iload           13
        //   837: iconst_1       
        //   838: if_icmpne       1310
        //   841: goto            848
        //   844: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   847: athrow         
        //   848: iinc            14, -1
        //   851: goto            1310
        //   854: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   857: athrow         
        //   858: aload           15
        //   860: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   863: if_acmpeq       896
        //   866: aload           15
        //   868: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   871: if_acmpeq       896
        //   874: goto            881
        //   877: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   880: athrow         
        //   881: aload           15
        //   883: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FOR_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   886: if_acmpne       926
        //   889: goto            896
        //   892: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   895: athrow         
        //   896: aload           11
        //   898: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.advance:()V
        //   903: aload_0        
        //   904: aload           11
        //   906: invokevirtual   com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.skipWhitespaceForward:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)V
        //   909: aload_0        
        //   910: aload           11
        //   912: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   915: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   918: invokevirtual   com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.skipBlock:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;)I
        //   921: istore          12
        //   923: goto            1320
        //   926: aload           15
        //   928: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   931: if_acmpne       955
        //   934: ldc             "in"
        //   936: aload_3        
        //   937: aload           11
        //   939: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)Ljava/lang/String;
        //   942: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   945: ifeq            1123
        //   948: goto            955
        //   951: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   954: athrow         
        //   955: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LITERALS:Lcom/intellij/psi/tree/TokenSet;
        //   958: aload           15
        //   960: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   963: ifne            1123
        //   966: goto            973
        //   969: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   972: athrow         
        //   973: aload           15
        //   975: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   978: if_acmpeq       1123
        //   981: goto            988
        //   984: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   987: athrow         
        //   988: aload           15
        //   990: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SELECTOR_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   993: if_acmpeq       1123
        //   996: goto            1003
        //   999: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1002: athrow         
        //  1003: aload           15
        //  1005: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ENCODE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1008: if_acmpeq       1123
        //  1011: goto            1018
        //  1014: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1017: athrow         
        //  1018: aload           15
        //  1020: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPEOF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1023: if_acmpeq       1123
        //  1026: goto            1033
        //  1029: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1032: athrow         
        //  1033: aload           15
        //  1035: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ALIGNOF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1038: if_acmpeq       1123
        //  1041: goto            1048
        //  1044: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1047: athrow         
        //  1048: aload           15
        //  1050: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ALIGNOF_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  1053: if_acmpeq       1123
        //  1056: goto            1063
        //  1059: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1062: athrow         
        //  1063: aload           15
        //  1065: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1068: if_acmpeq       1123
        //  1071: goto            1078
        //  1074: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1077: athrow         
        //  1078: aload           15
        //  1080: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_START_MARK:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1083: if_acmpeq       1123
        //  1086: goto            1093
        //  1089: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1092: athrow         
        //  1093: aload           15
        //  1095: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_STOP_MARK:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1098: if_acmpeq       1123
        //  1101: goto            1108
        //  1104: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1107: athrow         
        //  1108: aload           15
        //  1110: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SIZEOF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1113: if_acmpne       1158
        //  1116: goto            1123
        //  1119: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1122: athrow         
        //  1123: iload           12
        //  1125: ifne            1310
        //  1128: goto            1135
        //  1131: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1134: athrow         
        //  1135: iload           13
        //  1137: iconst_1       
        //  1138: if_icmpne       1310
        //  1141: goto            1148
        //  1144: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1147: athrow         
        //  1148: iinc            14, 1
        //  1151: goto            1310
        //  1154: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1157: athrow         
        //  1158: aload           15
        //  1160: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1163: if_acmpne       1203
        //  1166: iload           13
        //  1168: iconst_1       
        //  1169: if_icmpne       1203
        //  1172: goto            1179
        //  1175: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1178: athrow         
        //  1179: iload           12
        //  1181: ifne            1203
        //  1184: goto            1191
        //  1187: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1190: athrow         
        //  1191: iload           10
        //  1193: ifeq            1310
        //  1196: goto            1203
        //  1199: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1202: athrow         
        //  1203: aload           15
        //  1205: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //  1208: if_acmpne       1225
        //  1211: goto            1218
        //  1214: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1217: athrow         
        //  1218: goto            1310
        //  1221: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1224: athrow         
        //  1225: getstatic       com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.STOPSET:Lcom/intellij/psi/tree/TokenSet;
        //  1228: aload           15
        //  1230: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1233: ifne            1320
        //  1236: iload           12
        //  1238: ifne            1261
        //  1241: goto            1248
        //  1244: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1247: athrow         
        //  1248: iload           13
        //  1250: iconst_1       
        //  1251: if_icmpeq       1320
        //  1254: goto            1261
        //  1257: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1260: athrow         
        //  1261: iload           12
        //  1263: ifne            1310
        //  1266: goto            1273
        //  1269: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1272: athrow         
        //  1273: iload           13
        //  1275: ifne            1310
        //  1278: goto            1285
        //  1281: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1284: athrow         
        //  1285: getstatic       com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.OUT_OF_PAREN_STOPSET:Lcom/intellij/psi/tree/TokenSet;
        //  1288: aload           15
        //  1290: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1293: ifeq            1310
        //  1296: goto            1303
        //  1299: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1302: athrow         
        //  1303: goto            1320
        //  1306: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1309: athrow         
        //  1310: aload           11
        //  1312: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //  1317: goto            568
        //  1320: iload           12
        //  1322: ifne            1613
        //  1325: iload           13
        //  1327: iconst_1       
        //  1328: if_icmpgt       1613
        //  1331: goto            1338
        //  1334: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1337: athrow         
        //  1338: aload           11
        //  1340: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.advance:()V
        //  1345: goto            1352
        //  1348: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1351: athrow         
        //  1352: aload_3        
        //  1353: aload           11
        //  1355: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //  1360: iload           7
        //  1362: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Lcom/intellij/openapi/editor/Editor;II)Z
        //  1365: ifne            1382
        //  1368: aload           11
        //  1370: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.advance:()V
        //  1375: goto            1352
        //  1378: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1381: athrow         
        //  1382: aload_0        
        //  1383: aload           11
        //  1385: invokevirtual   com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.skipWhitespaceForward:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)V
        //  1388: aload           5
        //  1390: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //  1395: iload           7
        //  1397: iconst_1       
        //  1398: isub           
        //  1399: invokeinterface java/lang/CharSequence.charAt:(I)C
        //  1404: istore          15
        //  1406: iload           14
        //  1408: iconst_1       
        //  1409: if_icmpeq       1426
        //  1412: iload           15
        //  1414: bipush          58
        //  1416: if_icmpne       1473
        //  1419: goto            1426
        //  1422: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1425: athrow         
        //  1426: iload           15
        //  1428: bipush          32
        //  1430: if_icmpeq       1458
        //  1433: goto            1440
        //  1436: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1439: athrow         
        //  1440: aload           5
        //  1442: iload           7
        //  1444: ldc             " "
        //  1446: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //  1451: goto            1458
        //  1454: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1457: athrow         
        //  1458: aload           6
        //  1460: iconst_m1      
        //  1461: iconst_0       
        //  1462: iconst_0       
        //  1463: iconst_0       
        //  1464: iconst_0       
        //  1465: invokeinterface com/intellij/openapi/editor/CaretModel.moveCaretRelatively:(IIZZZ)V
        //  1470: goto            1561
        //  1473: iload           7
        //  1475: aload           5
        //  1477: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //  1482: invokeinterface java/lang/CharSequence.length:()I
        //  1487: iconst_1       
        //  1488: isub           
        //  1489: if_icmpge       1561
        //  1492: aload           5
        //  1494: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //  1499: iload           7
        //  1501: iconst_1       
        //  1502: iadd           
        //  1503: invokeinterface java/lang/CharSequence.charAt:(I)C
        //  1508: istore          16
        //  1510: iload           14
        //  1512: ifle            1561
        //  1515: iload           16
        //  1517: bipush          93
        //  1519: if_icmpne       1561
        //  1522: goto            1529
        //  1525: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1528: athrow         
        //  1529: aload           5
        //  1531: iload           7
        //  1533: iconst_1       
        //  1534: iadd           
        //  1535: ldc             " "
        //  1537: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //  1542: aload           6
        //  1544: iconst_1       
        //  1545: iconst_0       
        //  1546: iconst_0       
        //  1547: iconst_0       
        //  1548: iconst_0       
        //  1549: invokeinterface com/intellij/openapi/editor/CaretModel.moveCaretRelatively:(IIZZZ)V
        //  1554: goto            1561
        //  1557: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1560: athrow         
        //  1561: aload           5
        //  1563: aload           11
        //  1565: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //  1570: ldc             "["
        //  1572: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //  1577: aload           4
        //  1579: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //  1584: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //  1587: aload           5
        //  1589: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //  1592: aload           4
        //  1594: aload           11
        //  1596: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //  1601: aload           11
        //  1603: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //  1608: iconst_2       
        //  1609: iadd           
        //  1610: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.reformatTextIfNotInjected:(Lcom/intellij/psi/PsiFile;II)V
        //  1613: goto            1980
        //  1616: iload_1        
        //  1617: bipush          123
        //  1619: if_icmpne       1792
        //  1622: aload_3        
        //  1623: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //  1626: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //  1631: aload           6
        //  1633: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //  1638: iconst_1       
        //  1639: isub           
        //  1640: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //  1645: astore          7
        //  1647: aload           7
        //  1649: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //  1654: aload           7
        //  1656: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.needSemicolonAtBlockEnd:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)Z
        //  1659: ifeq            1789
        //  1662: aload_3        
        //  1663: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //  1666: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //  1671: aload           6
        //  1673: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //  1678: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //  1683: astore          7
        //  1685: aload           7
        //  1687: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.c:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)V
        //  1690: aload           7
        //  1692: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //  1697: ifne            1789
        //  1700: aload           7
        //  1702: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //  1707: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1710: if_acmpne       1789
        //  1713: goto            1720
        //  1716: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1719: athrow         
        //  1720: aload           7
        //  1722: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getEnd:()I
        //  1727: istore          8
        //  1729: aload           7
        //  1731: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.advance:()V
        //  1736: aload           7
        //  1738: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.c:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)V
        //  1741: aload           7
        //  1743: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //  1748: ifne            1771
        //  1751: aload           7
        //  1753: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //  1758: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1761: if_acmpeq       1789
        //  1764: goto            1771
        //  1767: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1770: athrow         
        //  1771: aload           5
        //  1773: iload           8
        //  1775: ldc             ";"
        //  1777: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //  1782: goto            1789
        //  1785: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1788: athrow         
        //  1789: goto            1980
        //  1792: iload_1        
        //  1793: bipush          35
        //  1795: if_icmpne       1980
        //  1798: aload_3        
        //  1799: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //  1802: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getHighlighter:()Lcom/intellij/openapi/editor/highlighter/EditorHighlighter;
        //  1807: aload           6
        //  1809: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //  1814: iconst_1       
        //  1815: isub           
        //  1816: invokeinterface com/intellij/openapi/editor/highlighter/EditorHighlighter.createIterator:(I)Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;
        //  1821: astore          7
        //  1823: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.FORMAT_PROBLEM_LEAFS_IN_NONCOMPILED:Lcom/intellij/psi/tree/TokenSet;
        //  1826: aload           7
        //  1828: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //  1833: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1836: ifne            1980
        //  1839: aload           7
        //  1841: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getIndentAtPosition:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)Ljava/lang/String;
        //  1844: astore          8
        //  1846: aload           8
        //  1848: ifnull          1980
        //  1851: aload           7
        //  1853: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //  1858: ifeq            1876
        //  1861: goto            1868
        //  1864: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1867: athrow         
        //  1868: iconst_0       
        //  1869: goto            1883
        //  1872: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1875: athrow         
        //  1876: aload           7
        //  1878: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getEnd:()I
        //  1883: istore          9
        //  1885: aload           7
        //  1887: aload           4
        //  1889: iconst_1       
        //  1890: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getDirectiveIndentFromAnchor:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;Lcom/intellij/psi/PsiFile;Z)Ljava/lang/String;
        //  1893: astore          10
        //  1895: aload           10
        //  1897: ifnonnull       1944
        //  1900: aload           4
        //  1902: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //  1907: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //  1910: aload           5
        //  1912: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //  1915: aload           4
        //  1917: iload           9
        //  1919: aload           8
        //  1921: invokevirtual   java/lang/String.length:()I
        //  1924: isub           
        //  1925: iconst_1       
        //  1926: iadd           
        //  1927: aload           6
        //  1929: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //  1934: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.reformatTextIfNotInjected:(Lcom/intellij/psi/PsiFile;II)V
        //  1937: goto            1980
        //  1940: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1943: athrow         
        //  1944: aload           10
        //  1946: aload           8
        //  1948: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1951: ifne            1980
        //  1954: aload           5
        //  1956: iload           9
        //  1958: aload           8
        //  1960: invokevirtual   java/lang/String.length:()I
        //  1963: isub           
        //  1964: iload           9
        //  1966: aload           10
        //  1968: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //  1973: goto            1980
        //  1976: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1979: athrow         
        //  1980: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //  1983: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     85     85     89     Ljava/lang/IllegalArgumentException;
        //  89     106    106    110    Ljava/lang/IllegalArgumentException;
        //  126    138    141    145    Ljava/lang/IllegalArgumentException;
        //  169    202    202    206    Ljava/lang/IllegalArgumentException;
        //  209    221    224    228    Ljava/lang/IllegalArgumentException;
        //  248    280    283    287    Ljava/lang/IllegalArgumentException;
        //  264    308    308    312    Ljava/lang/IllegalArgumentException;
        //  330    359    359    363    Ljava/lang/IllegalArgumentException;
        //  366    379    382    386    Ljava/lang/IllegalArgumentException;
        //  469    480    483    487    Ljava/lang/IllegalArgumentException;
        //  487    498    501    505    Ljava/lang/IllegalArgumentException;
        //  515    524    524    528    Ljava/lang/IllegalArgumentException;
        //  555    578    581    585    Ljava/lang/IllegalArgumentException;
        //  568    589    589    593    Ljava/lang/IllegalArgumentException;
        //  602    615    618    622    Ljava/lang/IllegalArgumentException;
        //  634    647    650    654    Ljava/lang/IllegalArgumentException;
        //  642    657    657    661    Ljava/lang/IllegalArgumentException;
        //  667    681    681    685    Ljava/lang/IllegalArgumentException;
        //  685    701    704    708    Ljava/lang/IllegalArgumentException;
        //  693    714    717    721    Ljava/lang/IllegalArgumentException;
        //  708    737    740    744    Ljava/lang/IllegalArgumentException;
        //  721    747    747    751    Ljava/lang/IllegalArgumentException;
        //  751    764    767    771    Ljava/lang/IllegalArgumentException;
        //  759    777    780    784    Ljava/lang/IllegalArgumentException;
        //  771    787    790    794    Ljava/lang/IllegalArgumentException;
        //  800    816    819    823    Ljava/lang/IllegalArgumentException;
        //  808    828    831    835    Ljava/lang/IllegalArgumentException;
        //  823    841    844    848    Ljava/lang/IllegalArgumentException;
        //  835    854    854    858    Ljava/lang/IllegalArgumentException;
        //  858    874    877    881    Ljava/lang/IllegalArgumentException;
        //  866    889    892    896    Ljava/lang/IllegalArgumentException;
        //  926    948    951    955    Ljava/lang/IllegalArgumentException;
        //  934    966    969    973    Ljava/lang/IllegalArgumentException;
        //  955    981    984    988    Ljava/lang/IllegalArgumentException;
        //  973    996    999    1003   Ljava/lang/IllegalArgumentException;
        //  988    1011   1014   1018   Ljava/lang/IllegalArgumentException;
        //  1003   1026   1029   1033   Ljava/lang/IllegalArgumentException;
        //  1018   1041   1044   1048   Ljava/lang/IllegalArgumentException;
        //  1033   1056   1059   1063   Ljava/lang/IllegalArgumentException;
        //  1048   1071   1074   1078   Ljava/lang/IllegalArgumentException;
        //  1063   1086   1089   1093   Ljava/lang/IllegalArgumentException;
        //  1078   1101   1104   1108   Ljava/lang/IllegalArgumentException;
        //  1093   1116   1119   1123   Ljava/lang/IllegalArgumentException;
        //  1108   1128   1131   1135   Ljava/lang/IllegalArgumentException;
        //  1123   1141   1144   1148   Ljava/lang/IllegalArgumentException;
        //  1135   1154   1154   1158   Ljava/lang/IllegalArgumentException;
        //  1158   1172   1175   1179   Ljava/lang/IllegalArgumentException;
        //  1166   1184   1187   1191   Ljava/lang/IllegalArgumentException;
        //  1179   1196   1199   1203   Ljava/lang/IllegalArgumentException;
        //  1191   1211   1214   1218   Ljava/lang/IllegalArgumentException;
        //  1203   1221   1221   1225   Ljava/lang/IllegalArgumentException;
        //  1225   1241   1244   1248   Ljava/lang/IllegalArgumentException;
        //  1236   1254   1257   1261   Ljava/lang/IllegalArgumentException;
        //  1248   1266   1269   1273   Ljava/lang/IllegalArgumentException;
        //  1261   1278   1281   1285   Ljava/lang/IllegalArgumentException;
        //  1273   1296   1299   1303   Ljava/lang/IllegalArgumentException;
        //  1285   1306   1306   1310   Ljava/lang/IllegalArgumentException;
        //  1320   1331   1334   1338   Ljava/lang/IllegalArgumentException;
        //  1325   1345   1348   1352   Ljava/lang/IllegalArgumentException;
        //  1352   1378   1378   1382   Ljava/lang/IllegalArgumentException;
        //  1406   1419   1422   1426   Ljava/lang/IllegalArgumentException;
        //  1412   1433   1436   1440   Ljava/lang/IllegalArgumentException;
        //  1426   1451   1454   1458   Ljava/lang/IllegalArgumentException;
        //  1510   1522   1525   1529   Ljava/lang/IllegalArgumentException;
        //  1515   1554   1557   1561   Ljava/lang/IllegalArgumentException;
        //  1685   1713   1716   1720   Ljava/lang/IllegalArgumentException;
        //  1729   1764   1767   1771   Ljava/lang/IllegalArgumentException;
        //  1751   1782   1785   1789   Ljava/lang/IllegalArgumentException;
        //  1846   1861   1864   1868   Ljava/lang/IllegalArgumentException;
        //  1851   1872   1872   1876   Ljava/lang/IllegalArgumentException;
        //  1895   1940   1940   1944   Ljava/lang/IllegalArgumentException;
        //  1944   1973   1976   1980   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0568:
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
    
    private static void c(final HighlighterIterator highlighterIterator) {
        while (true) {
            Label_0028: {
                try {
                    if (highlighterIterator.atEnd()) {
                        return;
                    }
                    final HighlighterIterator highlighterIterator2 = highlighterIterator;
                    final IElementType elementType = highlighterIterator2.getTokenType();
                    final IElementType elementType2 = TokenType.WHITE_SPACE;
                    if (elementType == elementType2) {
                        break Label_0028;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final HighlighterIterator highlighterIterator2 = highlighterIterator;
                    final IElementType elementType = highlighterIterator2.getTokenType();
                    final IElementType elementType2 = TokenType.WHITE_SPACE;
                    if (elementType == elementType2) {
                        highlighterIterator.advance();
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            break;
        }
    }
    
    private static boolean b(final HighlighterIterator highlighterIterator) {
        return OCTypedHandlerDelegate.OBJC_LITERAL_DETECTOR.tryNext(new OCHighlightingTokenIterator(highlighterIterator));
    }
    
    public static boolean needSemicolonAtBlockEnd(final HighlighterIterator p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: iconst_0       
        //     3: istore_2       
        //     4: iconst_0       
        //     5: istore_3       
        //     6: iconst_0       
        //     7: istore          4
        //     9: iconst_0       
        //    10: istore          5
        //    12: aconst_null    
        //    13: astore          6
        //    15: aload_0        
        //    16: checkcast       Lcom/intellij/openapi/editor/ex/util/LexerEditorHighlighter$HighlighterIteratorImpl;
        //    19: invokevirtual   com/intellij/openapi/editor/ex/util/LexerEditorHighlighter$HighlighterIteratorImpl.currentIndex:()I
        //    22: iflt            586
        //    25: aload_0        
        //    26: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    31: astore          7
        //    33: getstatic       com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.STOPSET:Lcom/intellij/psi/tree/TokenSet;
        //    36: aload           7
        //    38: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    41: ifeq            105
        //    44: aload           7
        //    46: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    49: if_acmpne       586
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: iload           5
        //    61: ifeq            586
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_0        
        //    72: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //    77: aload_0        
        //    78: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.needSemicolonAtBlockEnd:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)Z
        //    81: ifne            99
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: iconst_1       
        //    92: goto            100
        //    95: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_0       
        //   100: istore          5
        //   102: goto            586
        //   105: iload_2        
        //   106: ifne            378
        //   109: iload_3        
        //   110: ifne            378
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: aload           7
        //   122: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   125: if_acmpne       145
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: iinc            1, 1
        //   138: goto            285
        //   141: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload           7
        //   147: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   150: if_acmpne       163
        //   153: iinc            1, 2
        //   156: goto            285
        //   159: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload           7
        //   165: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   168: if_acmpne       181
        //   171: iinc            1, -1
        //   174: goto            285
        //   177: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload           7
        //   183: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LTLT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   186: if_acmpne       199
        //   189: iinc            1, -2
        //   192: goto            285
        //   195: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: aload           7
        //   201: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   204: if_acmpne       225
        //   207: iload           4
        //   209: ifne            225
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: iconst_1       
        //   220: istore          5
        //   222: goto            285
        //   225: aload           7
        //   227: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   230: if_acmpeq       248
        //   233: aload           7
        //   235: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   238: if_acmpne       257
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: iconst_0       
        //   249: istore          4
        //   251: iconst_0       
        //   252: istore          5
        //   254: goto            285
        //   257: iload_1        
        //   258: ifne            285
        //   261: getstatic       com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.NON_SEMICOLON_BLOCK_OWNERS:Lcom/intellij/psi/tree/TokenSet;
        //   264: aload           7
        //   266: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   269: ifeq            285
        //   272: goto            279
        //   275: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   278: athrow         
        //   279: iconst_0       
        //   280: ireturn        
        //   281: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload           7
        //   287: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   290: if_acmpeq       308
        //   293: aload           7
        //   295: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   298: if_acmpne       378
        //   301: goto            308
        //   304: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: aload           6
        //   310: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   313: if_acmpeq       353
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: aload           6
        //   325: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   328: if_acmpeq       353
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: aload           6
        //   340: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   343: if_acmpne       378
        //   346: goto            353
        //   349: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   352: athrow         
        //   353: aload           7
        //   355: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   358: if_acmpne       376
        //   361: goto            368
        //   364: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   367: athrow         
        //   368: iconst_1       
        //   369: goto            377
        //   372: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   375: athrow         
        //   376: iconst_0       
        //   377: ireturn        
        //   378: aload           7
        //   380: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   383: if_acmpne       396
        //   386: iinc            3, 1
        //   389: goto            414
        //   392: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   395: athrow         
        //   396: aload           7
        //   398: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   401: if_acmpne       414
        //   404: iinc            3, -1
        //   407: goto            414
        //   410: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   413: athrow         
        //   414: aload           7
        //   416: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   419: if_acmpne       432
        //   422: iinc            2, 1
        //   425: goto            489
        //   428: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   431: athrow         
        //   432: aload           7
        //   434: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   437: if_acmpne       463
        //   440: iload_1        
        //   441: ifne            457
        //   444: goto            451
        //   447: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   450: athrow         
        //   451: iconst_1       
        //   452: istore          4
        //   454: iconst_0       
        //   455: istore          5
        //   457: iinc            2, -1
        //   460: goto            489
        //   463: iload_2        
        //   464: ifne            489
        //   467: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   470: aload           7
        //   472: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   475: ifne            489
        //   478: goto            485
        //   481: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   484: athrow         
        //   485: aload           7
        //   487: astore          6
        //   489: iload_1        
        //   490: ifge            495
        //   493: iconst_0       
        //   494: istore_1       
        //   495: iload_2        
        //   496: ifge            501
        //   499: iconst_0       
        //   500: istore_2       
        //   501: aload           7
        //   503: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   506: if_acmpne       522
        //   509: iload_1        
        //   510: ifle            522
        //   513: goto            520
        //   516: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   519: athrow         
        //   520: iconst_0       
        //   521: istore_1       
        //   522: iload_1        
        //   523: ifne            577
        //   526: iload_2        
        //   527: ifne            577
        //   530: goto            537
        //   533: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   536: athrow         
        //   537: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMPOSITE_TYPE_SPECIFIERS:Lcom/intellij/psi/tree/TokenSet;
        //   540: aload           7
        //   542: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   545: ifeq            577
        //   548: goto            555
        //   551: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   554: athrow         
        //   555: iload           4
        //   557: ifne            575
        //   560: goto            567
        //   563: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   566: athrow         
        //   567: iconst_1       
        //   568: goto            576
        //   571: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   574: athrow         
        //   575: iconst_0       
        //   576: ireturn        
        //   577: aload_0        
        //   578: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //   583: goto            15
        //   586: iload           5
        //   588: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  33     52     55     59     Ljava/lang/IllegalArgumentException;
        //  44     64     67     71     Ljava/lang/IllegalArgumentException;
        //  59     84     87     91     Ljava/lang/IllegalArgumentException;
        //  71     95     95     99     Ljava/lang/IllegalArgumentException;
        //  105    113    116    120    Ljava/lang/IllegalArgumentException;
        //  109    128    131    135    Ljava/lang/IllegalArgumentException;
        //  120    141    141    145    Ljava/lang/IllegalArgumentException;
        //  145    159    159    163    Ljava/lang/IllegalArgumentException;
        //  163    177    177    181    Ljava/lang/IllegalArgumentException;
        //  181    195    195    199    Ljava/lang/IllegalArgumentException;
        //  199    212    215    219    Ljava/lang/IllegalArgumentException;
        //  225    241    244    248    Ljava/lang/IllegalArgumentException;
        //  257    272    275    279    Ljava/lang/IllegalArgumentException;
        //  261    281    281    285    Ljava/lang/IllegalArgumentException;
        //  285    301    304    308    Ljava/lang/IllegalArgumentException;
        //  293    316    319    323    Ljava/lang/IllegalArgumentException;
        //  308    331    334    338    Ljava/lang/IllegalArgumentException;
        //  323    346    349    353    Ljava/lang/IllegalArgumentException;
        //  338    361    364    368    Ljava/lang/IllegalArgumentException;
        //  353    372    372    376    Ljava/lang/IllegalArgumentException;
        //  378    392    392    396    Ljava/lang/IllegalArgumentException;
        //  396    407    410    414    Ljava/lang/IllegalArgumentException;
        //  414    428    428    432    Ljava/lang/IllegalArgumentException;
        //  432    444    447    451    Ljava/lang/IllegalArgumentException;
        //  463    478    481    485    Ljava/lang/IllegalArgumentException;
        //  501    513    516    520    Ljava/lang/IllegalArgumentException;
        //  522    530    533    537    Ljava/lang/IllegalArgumentException;
        //  526    548    551    555    Ljava/lang/IllegalArgumentException;
        //  537    560    563    567    Ljava/lang/IllegalArgumentException;
        //  555    571    571    575    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    private static boolean a(final HighlighterIterator highlighterIterator) {
        while (((LexerEditorHighlighter.HighlighterIteratorImpl)highlighterIterator).currentIndex() >= 0) {
            final IElementType tokenType = highlighterIterator.getTokenType();
            try {
                if (tokenType == OCTokenTypes.EQ) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (!OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(tokenType)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            highlighterIterator.retreat();
        }
        return false;
    }
    
    public static int needEnterTreatAfterTemplateList(@NotNull final HighlighterIterator highlighterIterator) {
        try {
            if (highlighterIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate", "needEnterTreatAfterTemplateList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int n = 0;
        int n2 = 0;
        while (((LexerEditorHighlighter.HighlighterIteratorImpl)highlighterIterator).currentIndex() >= 0) {
            final IElementType tokenType = highlighterIterator.getTokenType();
            try {
                if (OCTypedHandlerDelegate.STOPSET.contains(tokenType)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            Label_0256: {
                Label_0241: {
                    Label_0207: {
                        Label_0110: {
                            Label_0100: {
                                try {
                                    if (n2 != 0) {
                                        break Label_0207;
                                    }
                                    final IElementType elementType = tokenType;
                                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.GT;
                                    if (elementType == ocPunctuatorElementType) {
                                        break Label_0100;
                                    }
                                    break Label_0110;
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                                try {
                                    final IElementType elementType = tokenType;
                                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.GT;
                                    if (elementType == ocPunctuatorElementType) {
                                        ++n;
                                        break Label_0207;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                            try {
                                if (tokenType == OCTokenTypes.GTGT) {
                                    n += 2;
                                    break Label_0207;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            if (tokenType == OCTokenTypes.LT) {
                                --n;
                                break Label_0207;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        try {
                            if (tokenType == OCTokenTypes.LTLT) {
                                n -= 2;
                                break Label_0207;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                        Label_0190: {
                            Label_0179: {
                                try {
                                    if (n != 0) {
                                        break Label_0207;
                                    }
                                    final OCPunctuatorElementType ocPunctuatorElementType2 = (OCPunctuatorElementType)tokenType;
                                    final OCKeywordElementType ocKeywordElementType = OCTokenTypes.TEMPLATE_CPP_KEYWORD;
                                    if (ocPunctuatorElementType2 == ocKeywordElementType) {
                                        break Label_0179;
                                    }
                                    break Label_0190;
                                }
                                catch (IllegalArgumentException ex8) {
                                    throw a(ex8);
                                }
                                try {
                                    final OCPunctuatorElementType ocPunctuatorElementType2 = (OCPunctuatorElementType)tokenType;
                                    final OCKeywordElementType ocKeywordElementType = OCTokenTypes.TEMPLATE_CPP_KEYWORD;
                                    if (ocPunctuatorElementType2 == ocKeywordElementType) {
                                        return highlighterIterator.getStart();
                                    }
                                }
                                catch (IllegalArgumentException ex9) {
                                    throw a(ex9);
                                }
                            }
                            try {
                                if (!OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(tokenType)) {
                                    break;
                                }
                            }
                            catch (IllegalArgumentException ex10) {
                                throw a(ex10);
                            }
                        }
                        try {
                            if (tokenType == OCTokenTypes.RPAR) {
                                ++n2;
                                break Label_0241;
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw a(ex11);
                        }
                    }
                    try {
                        if (tokenType == OCTokenTypes.LPAR) {
                            --n2;
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw a(ex12);
                    }
                    try {
                        if (n < 0) {
                            break;
                        }
                        final int n3 = n2;
                        if (n3 < 0) {
                            break Label_0256;
                        }
                        break Label_0256;
                    }
                    catch (IllegalArgumentException ex13) {
                        throw a(ex13);
                    }
                }
                try {
                    final int n3 = n2;
                    if (n3 < 0) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex14) {
                    throw a(ex14);
                }
            }
            highlighterIterator.retreat();
        }
        return -1;
    }
    
    protected int skipBlock(final HighlighterIterator highlighterIterator, final OCPunctuatorElementType ocPunctuatorElementType, final OCPunctuatorElementType ocPunctuatorElementType2) {
        int n = 0;
        while (!highlighterIterator.atEnd()) {
            final IElementType tokenType = highlighterIterator.getTokenType();
            try {
                if (tokenType == null) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0064: {
                try {
                    if (tokenType == ocPunctuatorElementType) {
                        ++n;
                        break Label_0064;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    if (tokenType == ocPunctuatorElementType2) {
                        --n;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (n == 0) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            highlighterIterator.advance();
        }
        return n;
    }
    
    protected void skipWhitespaceForward(final HighlighterIterator highlighterIterator) {
        try {
            while (highlighterIterator.getTokenType() == TokenType.WHITE_SPACE) {
                highlighterIterator.advance();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private static boolean a(final Editor editor, final int n, final OCElementType ocElementType) {
        final Document document = editor.getDocument();
        try {
            if (n >= document.getTextLength()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final HighlighterIterator iterator = ((EditorEx)editor).getHighlighter().createIterator(n);
        try {
            if (iterator.getTokenType() == ocElementType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    public TypedHandlerDelegate.Result checkAutoPopup(final char c, final Project project, final Editor editor, final PsiFile psiFile) {
        if (c == '#') {
            final int offset = editor.getCaretModel().getOffset();
            final Document document = editor.getDocument();
            try {
                if (a(document.getCharsSequence().subSequence(document.getLineStartOffset(document.getLineNumber(offset)), offset))) {
                    AutoPopupController.getInstance(project).autoPopupMemberLookup(editor, null);
                    return TypedHandlerDelegate.Result.STOP;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return TypedHandlerDelegate.Result.CONTINUE;
    }
    
    public TypedHandlerDelegate.Result beforeSelectionRemoved(final char p0, final Project p1, final Editor p2, final PsiFile p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/codeInsight/CodeInsightSettings.getInstance:()Lcom/intellij/codeInsight/CodeInsightSettings;
        //     3: getfield        com/intellij/codeInsight/CodeInsightSettings.SURROUND_SELECTION_ON_QUOTE_TYPED:Z
        //     6: ifeq            48
        //     9: iload_1        
        //    10: bipush          91
        //    12: if_icmpeq       61
        //    15: goto            22
        //    18: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    21: athrow         
        //    22: iload_1        
        //    23: bipush          39
        //    25: if_icmpeq       61
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iload_1        
        //    36: bipush          34
        //    38: if_icmpeq       61
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: iload_1        
        //    49: bipush          40
        //    51: if_icmpne       124
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: aload_3        
        //    62: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //    67: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectedText:()Ljava/lang/String;
        //    72: astore          5
        //    74: aload           5
        //    76: ifnull          124
        //    79: aload           5
        //    81: ldc             "<#"
        //    83: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    86: ifeq            124
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload           5
        //    98: ldc             "#>"
        //   100: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   103: ifeq            124
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_3        
        //   114: invokestatic    com/intellij/openapi/editor/EditorModificationUtil.deleteSelectedText:(Lcom/intellij/openapi/editor/Editor;)V
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: getstatic       com/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result.CONTINUE:Lcom/intellij/codeInsight/editorActions/TypedHandlerDelegate$Result;
        //   127: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      15     18     22     Ljava/lang/IllegalArgumentException;
        //  9      28     31     35     Ljava/lang/IllegalArgumentException;
        //  22     41     44     48     Ljava/lang/IllegalArgumentException;
        //  35     54     57     61     Ljava/lang/IllegalArgumentException;
        //  74     89     92     96     Ljava/lang/IllegalArgumentException;
        //  79     106    109    113    Ljava/lang/IllegalArgumentException;
        //  96     117    120    124    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
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
    private static boolean a(@Nullable final CharSequence charSequence) {
        try {
            if (charSequence == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int n = 0;
        while (true) {
            Label_0048: {
                try {
                    if (n >= charSequence.length()) {
                        break;
                    }
                    final CharSequence charSequence2 = charSequence;
                    final int n2 = n;
                    final char c = charSequence2.charAt(n2);
                    final boolean b = Character.isWhitespace(c);
                    if (!b) {
                        return false;
                    }
                    break Label_0048;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final CharSequence charSequence2 = charSequence;
                    final int n2 = n;
                    final char c = charSequence2.charAt(n2);
                    final boolean b = Character.isWhitespace(c);
                    if (!b) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            ++n;
        }
        return true;
    }
    
    private static boolean a(final Editor editor, final int n, final int n2) {
        final StringBuilder sb = new StringBuilder(n2 - n + 2);
        final Document document = editor.getDocument();
        try {
            if (n >= n2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0067: {
            try {
                if (n >= document.getTextLength()) {
                    return true;
                }
                final int n3 = n2;
                final Document document2 = document;
                final int n4 = document2.getTextLength();
                if (n3 >= n4) {
                    return true;
                }
                break Label_0067;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final int n3 = n2;
                final Document document2 = document;
                final int n4 = document2.getTextLength();
                if (n3 >= n4) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final Project project = editor.getProject();
        Label_0101: {
            try {
                if (project == null) {
                    return false;
                }
                final Project project2 = project;
                final boolean b = FileSymbolTablesCache.areSymbolsLoaded(project2);
                if (!b) {
                    return false;
                }
                break Label_0101;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final Project project2 = project;
                final boolean b = FileSymbolTablesCache.areSymbolsLoaded(project2);
                if (!b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
        try {
            sb.append('[').append(document.getCharsSequence().subSequence(n, n2)).append(']');
            if (!a(sb, psiFile)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            sb.setLength(sb.length() - 1);
            sb.append(" a]");
            if (!a(sb, psiFile)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return false;
    }
    
    private static boolean a(final StringBuilder sb, final PsiFile psiFile) {
        final OCExpression expressionFromText = OCElementFactory.expressionFromText(sb.toString(), (PsiElement)psiFile);
        try {
            if (expressionFromText == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final boolean[] array = { false };
        expressionFromText.accept((PsiElementVisitor)new OCRecursiveVisitor() {
            public void visitErrorElement(final PsiErrorElement psiErrorElement) {
                array[0] = true;
            }
            
            @Override
            public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                if (ocSendMessageExpression.getArguments().size() == 0) {
                    array[0] = true;
                }
                super.visitSendMessageExpression(ocSendMessageExpression);
            }
        });
        return array[0];
    }
    
    private static String a(final Editor editor, final HighlighterIterator highlighterIterator) {
        return editor.getDocument().getCharsSequence().subSequence(highlighterIterator.getStart(), highlighterIterator.getEnd()).toString();
    }
    
    static {
        OCTypedHandlerDelegate.DO_STAR_OVERTYPE = false;
        OCTypedHandlerDelegate.DO_COLON_OVERTYPE = false;
        STOPSET = TokenSet.create(new IElementType[] { OCTokenTypes.LBRACE, OCTokenTypes.RBRACE, OCTokenTypes.SEMICOLON });
        OUT_OF_PAREN_STOPSET = TokenSet.create(new IElementType[] { OCTokenTypes.EQ, OCTokenTypes.EQEQ, OCTokenTypes.ANDAND, OCTokenTypes.OROR, OCTokenTypes.LT, OCTokenTypes.LTEQ, OCTokenTypes.GT, OCTokenTypes.GTEQ, OCTokenTypes.PLUS, OCTokenTypes.MINUS, OCTokenTypes.DIV, OCTokenTypes.MUL });
        final ArrayList<OCPunctuatorElementType> list = new ArrayList<OCPunctuatorElementType>();
        list.add(OCTokenTypes.XOR);
        list.add(OCTokenTypes.SEMICOLON);
        for (final BracePair bracePair : OCBraceMatcher.PAIRS) {
            list.add((OCPunctuatorElementType)bracePair.getLeftBraceType());
            list.add((OCPunctuatorElementType)bracePair.getRightBraceType());
        }
        BLOCK_TYPE_STOP_SET = list.toArray(new IElementType[list.size()]);
        NUMBER_LITERAL_DETECTOR = OCBackwardStructureDetector.sequence(OCBackwardStructureDetector.token(OCTokenTypes.INTEGER_LITERAL, OCTokenTypes.FLOAT_LITERAL), OCBackwardStructureDetector.token(OCTokenTypes.AT));
        STRING_LITERAL_DETECTOR = OCBackwardStructureDetector.sequence(OCBackwardStructureDetector.repeat(OCBackwardStructureDetector.Cardinality.ONE_OR_MORE, OCBackwardStructureDetector.token(OCTokenTypes.STRING_LITERAL)), OCBackwardStructureDetector.token(OCTokenTypes.AT));
        ARRAY_LITERAL_DETECTOR = OCBackwardStructureDetector.sequence(OCBackwardStructureDetector.block(OCTokenTypes.LBRACKET, OCTokenTypes.RBRACKET), OCBackwardStructureDetector.token(OCTokenTypes.AT));
        BRACED_LITERAL_DETECTOR = OCBackwardStructureDetector.sequence(OCBackwardStructureDetector.block(OCTokenTypes.LBRACE, OCTokenTypes.RBRACE), OCBackwardStructureDetector.or(OCBackwardStructureDetector.token(OCTokenTypes.AT), OCBackwardStructureDetector.sequence(OCBackwardStructureDetector.repeat(OCBackwardStructureDetector.Cardinality.MAYBE_ONE, OCBackwardStructureDetector.block(OCTokenTypes.LPAR, OCTokenTypes.RPAR)), OCBackwardStructureDetector.repeat(OCBackwardStructureDetector.Cardinality.ZERO_OR_MORE, OCBackwardStructureDetector.tokenExcept(OCTypedHandlerDelegate.BLOCK_TYPE_STOP_SET)), OCBackwardStructureDetector.token(OCTokenTypes.XOR))));
        OBJC_LITERAL_DETECTOR = OCBackwardStructureDetector.or(OCTypedHandlerDelegate.NUMBER_LITERAL_DETECTOR, OCTypedHandlerDelegate.STRING_LITERAL_DETECTOR, OCTypedHandlerDelegate.ARRAY_LITERAL_DETECTOR, OCTypedHandlerDelegate.BRACED_LITERAL_DETECTOR);
        NON_SEMICOLON_BLOCK_OWNERS = TokenSet.create(new IElementType[] { OCTokenTypes.DO_KEYWORD, OCTokenTypes.ELSE_KEYWORD, OCTokenTypes.FOR_KEYWORD, OCTokenTypes.IF_KEYWORD, OCTokenTypes.SWITCH_KEYWORD, OCTokenTypes.CASE_KEYWORD, OCTokenTypes.WHILE_KEYWORD, OCTokenTypes.TRY_KEYWORD, OCTokenTypes.CATCH_KEYWORD, OCTokenTypes.FINALLY_KEYWORD, OCTokenTypes.SYNCHRONIZED_KEYWORD, OCTokenTypes.INTERFACE_KEYWORD, OCTokenTypes.AUTO_RELEASE_POOL_KEYWORD, OCTokenTypes.NAMESPACE_CPP_KEYWORD, OCTokenTypes.IMPLEMENTATION_KEYWORD, OCTokenTypes.PROTOCOL_KEYWORD, OCTokenTypes.LBRACKET });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
