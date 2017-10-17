// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.openapi.editor.ex.util.LexerEditorHighlighter;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.Document;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.lang.Language;
import com.intellij.openapi.project.DumbService;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.editorActions.enter.EnterBetweenBracesHandler;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.codeInsight.editorActions.enter.EnterAfterUnmatchedBraceHandler;

public class OCEnterAfterUnmatchedBraceHandler extends EnterAfterUnmatchedBraceHandler
{
    private static final Logger LOG;
    private static final EnterBetweenBracesHandler ENTER_BETWEEN_BRACES_HANDLER;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Override
    public boolean isApplicable(@NotNull final PsiFile psiFile, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "isApplicable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final Language languageAtOffset = PsiUtilCore.getLanguageAtOffset(psiFile, n);
        Label_0076: {
            try {
                if (!(languageAtOffset instanceof OCLanguage)) {
                    return false;
                }
                final PsiFile psiFile2 = psiFile;
                final Project project = psiFile2.getProject();
                final boolean b = DumbService.isDumb(project);
                if (!b) {
                    break Label_0076;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final Project project = psiFile2.getProject();
                final boolean b = DumbService.isDumb(project);
                if (!b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Override
    public EnterHandlerDelegate.Result preprocessEnter(@NotNull final PsiFile psiFile, @NotNull final Editor editor, @NotNull final Ref<Integer> ref, @NotNull final Ref<Integer> ref2, @NotNull final DataContext dataContext, final EditorActionHandler editorActionHandler) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnter"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnter"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretOffsetRef", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnter"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        try {
            if (ref2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretAdvance", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnter"));
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnter"));
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        try {
            if (a(psiFile, editor.getDocument(), ref)) {
                return a(psiFile, editor, ref, ref2, dataContext, editorActionHandler);
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
        return super.preprocessEnter(psiFile, editor, ref, ref2, dataContext, editorActionHandler);
    }
    
    private static EnterHandlerDelegate.Result a(@NotNull final PsiFile psiFile, @NotNull final Editor editor, @NotNull final Ref<Integer> ref, @NotNull final Ref<Integer> ref2, @NotNull final DataContext dataContext, final EditorActionHandler editorActionHandler) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnterInsideBraces"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnterInsideBraces"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretOffsetRef", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnterInsideBraces"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        try {
            if (ref2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretAdvance", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnterInsideBraces"));
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "preprocessEnterInsideBraces"));
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        final EnterHandlerDelegate.Result preprocessEnter = OCEnterAfterUnmatchedBraceHandler.ENTER_BETWEEN_BRACES_HANDLER.preprocessEnter(psiFile, editor, ref, ref2, dataContext, editorActionHandler);
        try {
            if (preprocessEnter == EnterHandlerDelegate.Result.Continue) {
                return EnterHandlerDelegate.Result.DefaultForceIndent;
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
        return preprocessEnter;
    }
    
    private static boolean a(@NotNull final PsiFile p0, @NotNull final Document p1, @NotNull final Ref<Integer> p2) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isFullStructLikeDeclarationWithSemicolonAtEnd"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "document"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isFullStructLikeDeclarationWithSemicolonAtEnd"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_2        
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
        //   112: ldc             "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "isFullStructLikeDeclarationWithSemicolonAtEnd"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_0        
        //   133: invokeinterface com/intellij/psi/PsiFile.getText:()Ljava/lang/String;
        //   138: astore_3       
        //   139: aload_2        
        //   140: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   143: checkcast       Ljava/lang/Integer;
        //   146: invokevirtual   java/lang/Integer.intValue:()I
        //   149: istore          4
        //   151: iload           4
        //   153: iconst_1       
        //   154: if_icmplt       179
        //   157: aload_3        
        //   158: iload           4
        //   160: iconst_1       
        //   161: isub           
        //   162: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   167: bipush          123
        //   169: if_icmpeq       185
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   178: athrow         
        //   179: iconst_0       
        //   180: ireturn        
        //   181: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   184: athrow         
        //   185: aload_0        
        //   186: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   191: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   194: aload_1        
        //   195: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   198: aload_0        
        //   199: iload           4
        //   201: iconst_1       
        //   202: isub           
        //   203: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   208: astore          5
        //   210: aload           5
        //   212: ifnull          381
        //   215: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STRUCTURE_TYPES:Lcom/intellij/psi/tree/TokenSet;
        //   218: aload           5
        //   220: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   225: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   228: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   231: ifeq            381
        //   234: goto            241
        //   237: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   240: athrow         
        //   241: aload           5
        //   243: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   248: ifnull          381
        //   251: goto            258
        //   254: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   257: athrow         
        //   258: aload           5
        //   260: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   265: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   270: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   273: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   276: if_acmpne       381
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   285: athrow         
        //   286: aload           5
        //   288: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   293: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   298: ifnull          381
        //   301: goto            308
        //   304: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   307: athrow         
        //   308: aload           5
        //   310: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   315: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   320: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   325: ifnull          381
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   334: athrow         
        //   335: aload           5
        //   337: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   342: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   347: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   352: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   357: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   360: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   363: if_acmpne       381
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   372: athrow         
        //   373: iconst_1       
        //   374: goto            382
        //   377: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   380: athrow         
        //   381: iconst_0       
        //   382: ireturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Document;Lcom/intellij/openapi/util/Ref<Ljava/lang/Integer;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  151    172    175    179    Lcom/intellij/util/IncorrectOperationException;
        //  157    181    181    185    Lcom/intellij/util/IncorrectOperationException;
        //  210    234    237    241    Lcom/intellij/util/IncorrectOperationException;
        //  215    251    254    258    Lcom/intellij/util/IncorrectOperationException;
        //  241    279    282    286    Lcom/intellij/util/IncorrectOperationException;
        //  258    301    304    308    Lcom/intellij/util/IncorrectOperationException;
        //  286    328    331    335    Lcom/intellij/util/IncorrectOperationException;
        //  308    366    369    373    Lcom/intellij/util/IncorrectOperationException;
        //  335    377    377    381    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0241:
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
    protected String generateStringToInsert(@NotNull final Editor editor, final int n, final int n2) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "generateStringToInsert"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0061: {
            try {
                if (OCEnterAfterUnmatchedBraceHandler.$assertionsDisabled) {
                    break Label_0061;
                }
                final int n3 = n2;
                if (n3 <= 0) {
                    break Label_0061;
                }
                break Label_0061;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final int n3 = n2;
                if (n3 <= 0) {
                    throw new AssertionError();
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        HighlighterIterator highlighterIterator = a((EditorEx)editor, n);
        int n4 = 0;
        final StringBuilder sb = new StringBuilder(n2);
        while (true) {
            try {
                if (((LexerEditorHighlighter.HighlighterIteratorImpl)highlighterIterator).currentIndex() < 0 || n4 >= n2) {
                    break;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            final IElementType tokenType = highlighterIterator.getTokenType();
            if (tokenType == OCTokenTypes.LBRACE) {
                final int start = highlighterIterator.getStart();
                try {
                    highlighterIterator.retreat();
                    sb.append('}');
                    if (OCTypedHandlerDelegate.needSemicolonAtBlockEnd(highlighterIterator)) {
                        sb.append(';');
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
                highlighterIterator = a((EditorEx)editor, start);
                ++n4;
            }
            else {
                Label_0250: {
                    try {
                        if (StringUtil.contains(highlighterIterator.getDocument().getCharsSequence(), highlighterIterator.getStart(), highlighterIterator.getEnd(), '\n')) {
                            break;
                        }
                        final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)tokenType;
                        final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.RBRACE;
                        if (ocPunctuatorElementType == ocPunctuatorElementType2) {
                            break Label_0250;
                        }
                        break Label_0250;
                    }
                    catch (IncorrectOperationException ex6) {
                        throw a(ex6);
                    }
                    try {
                        final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)tokenType;
                        final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.RBRACE;
                        if (ocPunctuatorElementType == ocPunctuatorElementType2) {
                            break;
                        }
                    }
                    catch (IncorrectOperationException ex7) {
                        throw a(ex7);
                    }
                }
                highlighterIterator.retreat();
            }
        }
        String s = null;
        Label_0289: {
            try {
                if (sb.length() == 0) {
                    final String string;
                    s = (string = "}");
                    break Label_0289;
                }
            }
            catch (IncorrectOperationException ex8) {
                throw a(ex8);
            }
            String string;
            s = (string = sb.toString());
            try {
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "generateStringToInsert"));
                }
            }
            catch (IncorrectOperationException ex9) {
                throw a(ex9);
            }
        }
        return s;
    }
    
    @NotNull
    private static HighlighterIterator a(@NotNull final EditorEx editorEx, final int n) {
        try {
            if (editorEx == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "createBeforeIterator"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        HighlighterIterator iterator = null;
        Label_0065: {
            EditorHighlighter highlighter;
            try {
                highlighter = editorEx.getHighlighter();
                if (n == 0) {
                    final int n2 = 0;
                    break Label_0065;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            final int n2 = n - 1;
            try {
                iterator = highlighter.createIterator(n2);
                if (iterator == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "createBeforeIterator"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return iterator;
    }
    
    @Override
    protected void formatCodeFragmentBetweenBraces(@NotNull final PsiFile psiFile, @NotNull final Document document, final int n, final int n2, final String s) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "formatCodeFragmentBetweenBraces"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "formatCodeFragmentBetweenBraces"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final Project project = psiFile.getProject();
        final CommonCodeStyleSettings commonSettings = CodeStyleSettingsManager.getInstance(project).getCurrentSettings().getCommonSettings((Language)OCLanguage.getInstance());
        Label_0130: {
            try {
                if (OCEnterAfterUnmatchedBraceHandler.$assertionsDisabled) {
                    break Label_0130;
                }
                final CommonCodeStyleSettings commonCodeStyleSettings = commonSettings;
                if (commonCodeStyleSettings == null) {
                    break Label_0130;
                }
                break Label_0130;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final CommonCodeStyleSettings commonCodeStyleSettings = commonSettings;
                if (commonCodeStyleSettings == null) {
                    throw new AssertionError();
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        final int keep_BLANK_LINES_BEFORE_RBRACE = commonSettings.KEEP_BLANK_LINES_BEFORE_RBRACE;
        commonSettings.KEEP_BLANK_LINES_BEFORE_RBRACE = 1;
        try {
            final CodeStyleManager instance = CodeStyleManager.getInstance(project);
            PsiDocumentManager.getInstance(project).commitDocument(document);
            final RangeMarker rangeMarker = document.createRangeMarker(TextRange.create(n, n2 + s.length() + 2));
            instance.reformatText(psiFile, n2 + 1, n2 + s.length() + 2);
            instance.adjustLineIndent(psiFile, TextRange.create(rangeMarker.getStartOffset(), rangeMarker.getEndOffset()));
            rangeMarker.dispose();
        }
        catch (IncorrectOperationException ex5) {
            OCEnterAfterUnmatchedBraceHandler.LOG.error((Throwable)ex5);
        }
        finally {
            document.deleteString(n, n + 1);
            commonSettings.KEEP_BLANK_LINES_BEFORE_RBRACE = keep_BLANK_LINES_BEFORE_RBRACE;
        }
    }
    
    @Override
    protected Pair<PsiElement, Integer> calculateOffsetToInsertClosingBrace(@NotNull final PsiFile psiFile, @NotNull final CharSequence charSequence, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "calculateOffsetToInsertClosingBrace"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/editor/OCEnterAfterUnmatchedBraceHandler", "calculateOffsetToInsertClosingBrace"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final Pair<PsiElement, Integer> calculateOffsetToInsertClosingBrace = super.calculateOffsetToInsertClosingBrace(psiFile, charSequence, n);
        final PsiElement psiElement = (PsiElement)calculateOffsetToInsertClosingBrace.first;
        int n2 = (int)calculateOffsetToInsertClosingBrace.second;
        if (OCElementUtil.getElementType(psiElement) == OCElementTypes.MACRO_CALL) {
            ASTNode astNode = PsiUtilCore.getElementAtOffset(psiFile, n2).getNode();
            while (true) {
                Label_0174: {
                    try {
                        if (astNode == null) {
                            break;
                        }
                        final TokenSet set = OCTokenTypes.WHITESPACES;
                        final ASTNode astNode2 = astNode;
                        final IElementType elementType = OCElementUtil.getElementType(astNode2);
                        final boolean b = set.contains(elementType);
                        if (b) {
                            break Label_0174;
                        }
                        break;
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final TokenSet set = OCTokenTypes.WHITESPACES;
                        final ASTNode astNode2 = astNode;
                        final IElementType elementType = OCElementUtil.getElementType(astNode2);
                        final boolean b = set.contains(elementType);
                        if (!b) {
                            break;
                        }
                        if (astNode instanceof OCMacroForeignLeafElement) {
                            break;
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
                astNode = astNode.getTreeNext();
            }
            try {
                if (OCElementUtil.getElementType(astNode) != OCTokenTypes.SEMICOLON || astNode instanceof OCMacroForeignLeafElement) {
                    return (Pair<PsiElement, Integer>)Pair.create((Object)psiElement, (Object)n2);
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            n2 = astNode.getTextRange().getEndOffset();
        }
        else if (OCElementUtil.getElementType(psiElement) == OCTokenTypes.RBRACE) {
            n2 = psiElement.getTextRange().getStartOffset();
        }
        return (Pair<PsiElement, Integer>)Pair.create((Object)psiElement, (Object)n2);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCEnterAfterUnmatchedBraceHandler.class.desiredAssertionStatus()) {
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
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.editor.OCEnterAfterUnmatchedBraceHandler");
        ENTER_BETWEEN_BRACES_HANDLER = new EnterBetweenBracesHandler();
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
