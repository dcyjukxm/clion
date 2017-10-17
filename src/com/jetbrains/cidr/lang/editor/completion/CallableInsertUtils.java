// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.folding.CodeFoldingManager;
import com.intellij.codeInsight.completion.InsertionContext;

public class CallableInsertUtils
{
    public static void moveCaretToCallableBody(final InsertionContext insertionContext) {
        final Document document = insertionContext.getDocument();
        final Editor editor = insertionContext.getEditor();
        CodeFoldingManager.getInstance(insertionContext.getProject()).updateFoldRegions(editor);
        final int index = document.getText().indexOf("{", insertionContext.getStartOffset());
        final PsiDocumentManager instance = PsiDocumentManager.getInstance(insertionContext.getProject());
        instance.commitDocument(document);
        final PsiFile psiFile = instance.getPsiFile(document);
        if (psiFile != null) {
            OCCodeInsightUtil.selectBody(editor, (OCBlockStatement)PsiTreeUtil.getContextOfType(psiFile.findElementAt(index), false, new Class[] { OCBlockStatement.class }));
        }
    }
    
    @NotNull
    public static InsertHandler<LookupElement> createHandler(@NotNull final OCFunctionSymbol ocFunctionSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils", "createHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        FunctionInsertHandler functionInsertHandler = null;
        Label_0105: {
            FunctionDeclarationInsertHandler functionDeclarationInsertHandler = null;
            Label_0070: {
                try {
                    if (!(psiElement instanceof OCDeclarator)) {
                        break Label_0105;
                    }
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    functionDeclarationInsertHandler = new FunctionDeclarationInsertHandler(ocFunctionSymbol2);
                    if (functionDeclarationInsertHandler == null) {
                        break Label_0070;
                    }
                    return (InsertHandler<LookupElement>)functionDeclarationInsertHandler;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    functionDeclarationInsertHandler = new FunctionDeclarationInsertHandler(ocFunctionSymbol2);
                    if (functionDeclarationInsertHandler == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils", "createHandler"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return (InsertHandler<LookupElement>)functionDeclarationInsertHandler;
            try {
                functionInsertHandler = new FunctionInsertHandler(ocFunctionSymbol, psiElement);
                if (functionInsertHandler == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils", "createHandler"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return (InsertHandler<LookupElement>)functionInsertHandler;
    }
    
    public static boolean isReplaceCompletionBeforeParen(@NotNull final InsertionContext insertionContext) {
        try {
            if (insertionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils", "isReplaceCompletionBeforeParen"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        boolean b = false;
        if (isReplaceCompletion(insertionContext)) {
            final CharSequence charsSequence = insertionContext.getDocument().getCharsSequence();
            boolean b2 = false;
            Label_0107: {
                Label_0098: {
                    try {
                        if (charsSequence.length() <= insertionContext.getTailOffset()) {
                            break Label_0098;
                        }
                        final CharSequence charSequence = charsSequence;
                        final InsertionContext insertionContext2 = insertionContext;
                        final int n = insertionContext2.getTailOffset();
                        final char c = charSequence.charAt(n);
                        final char c2 = '(';
                        if (c == c2) {
                            break Label_0098;
                        }
                        break Label_0098;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final CharSequence charSequence = charsSequence;
                        final InsertionContext insertionContext2 = insertionContext;
                        final int n = insertionContext2.getTailOffset();
                        final char c = charSequence.charAt(n);
                        final char c2 = '(';
                        if (c == c2) {
                            b2 = true;
                            break Label_0107;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                b2 = false;
            }
            b = b2;
        }
        return b;
    }
    
    public static boolean isReplaceCompletion(@NotNull final InsertionContext insertionContext) {
        try {
            if (insertionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils", "isReplaceCompletion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (insertionContext.getCompletionChar() == '\t') {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    public static void addParensIfRequired(final InsertionContext insertionContext, final LookupElement lookupElement, final boolean b) {
        final CommonCodeStyleSettings codeStyleSettings = insertionContext.getCodeStyleSettings();
        boolean space_BEFORE_METHOD_CALL_PARENTHESES = false;
        boolean b3 = false;
        Label_0037: {
            Label_0028: {
                try {
                    space_BEFORE_METHOD_CALL_PARENTHESES = codeStyleSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES;
                    if (!codeStyleSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES) {
                        break Label_0028;
                    }
                    final boolean b2 = b;
                    if (b2) {
                        break Label_0028;
                    }
                    break Label_0028;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final boolean b2 = b;
                    if (b2) {
                        b3 = true;
                        break Label_0037;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            b3 = false;
        }
        ParenthesesInsertHandler.getInstance(b, space_BEFORE_METHOD_CALL_PARENTHESES, b3, true, codeStyleSettings.METHOD_PARAMETERS_LPAREN_ON_NEXT_LINE).handleInsert(insertionContext, lookupElement);
    }
    
    public static boolean shouldInsertPlaceholders(@NotNull final InsertionContext p0) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldInsertPlaceholders"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //    48: astore_1       
        //    49: aload_1        
        //    50: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    53: ifeq            72
        //    56: aload_1        
        //    57: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    60: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //    65: goto            73
        //    68: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aconst_null    
        //    73: astore_2       
        //    74: invokestatic    com/intellij/util/PlatformUtils.isAppCode:()Z
        //    77: ifeq            106
        //    80: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    83: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    88: ifne            106
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: iconst_1       
        //    99: goto            107
        //   102: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: iconst_0       
        //   107: istore_3       
        //   108: iload_3        
        //   109: ifne            146
        //   112: aload_2        
        //   113: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.OBJ_C:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   116: if_acmpeq       146
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_2        
        //   127: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.OBJ_CPP:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   130: if_acmpeq       146
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: iconst_0       
        //   141: ireturn        
        //   142: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload_0        
        //   147: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   150: astore          4
        //   152: aload_0        
        //   153: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getEditor:()Lcom/intellij/openapi/editor/Editor;
        //   156: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   161: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   166: istore          5
        //   168: iload           5
        //   170: aload           4
        //   172: invokeinterface com/intellij/openapi/editor/Document.getTextLength:()I
        //   177: if_icmpge       214
        //   180: aload           4
        //   182: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   187: iload           5
        //   189: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   194: bipush          41
        //   196: if_icmpne       214
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: iconst_1       
        //   207: goto            215
        //   210: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: iconst_0       
        //   215: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     68     68     72     Ljava/lang/IllegalArgumentException;
        //  74     91     94     98     Ljava/lang/IllegalArgumentException;
        //  80     102    102    106    Ljava/lang/IllegalArgumentException;
        //  108    119    122    126    Ljava/lang/IllegalArgumentException;
        //  112    133    136    140    Ljava/lang/IllegalArgumentException;
        //  126    142    142    146    Ljava/lang/IllegalArgumentException;
        //  168    199    202    206    Ljava/lang/IllegalArgumentException;
        //  180    210    210    214    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    
    public static void selectFirstPlaceholderIfPresent(@NotNull final InsertionContext p0) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "selectFirstPlaceholderIfPresent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getEditor:()Lcom/intellij/openapi/editor/Editor;
        //    48: astore_1       
        //    49: aload_0        
        //    50: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //    53: astore_2       
        //    54: aload_0        
        //    55: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getDocument:()Lcom/intellij/openapi/editor/Document;
        //    58: invokeinterface com/intellij/openapi/editor/Document.getText:()Ljava/lang/String;
        //    63: astore_3       
        //    64: aload_3        
        //    65: ldc             "<#"
        //    67: aload_0        
        //    68: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //    71: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;I)I
        //    74: istore          4
        //    76: aload_3        
        //    77: ldc             "#>"
        //    79: iload           4
        //    81: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;I)I
        //    84: istore          5
        //    86: aload_1        
        //    87: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //    92: astore          6
        //    94: iload           4
        //    96: iflt            147
        //    99: iload           5
        //   101: iflt            147
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: iload           4
        //   113: aload_0        
        //   114: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //   117: if_icmpge       147
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: aload           6
        //   129: iload           4
        //   131: iload           5
        //   133: iconst_2       
        //   134: iadd           
        //   135: invokeinterface com/intellij/openapi/editor/SelectionModel.setSelection:(II)V
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload_0        
        //   148: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.commitDocument:()V
        //   151: aload_0        
        //   152: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   155: aload_0        
        //   156: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   159: aload_0        
        //   160: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //   163: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.reformatTextIfNotInjected:(Lcom/intellij/psi/PsiFile;II)V
        //   166: aload_2        
        //   167: invokestatic    com/intellij/codeInsight/folding/CodeFoldingManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/codeInsight/folding/CodeFoldingManager;
        //   170: aload_1        
        //   171: invokevirtual   com/intellij/codeInsight/folding/CodeFoldingManager.updateFoldRegions:(Lcom/intellij/openapi/editor/Editor;)V
        //   174: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  94     104    107    111    Ljava/lang/IllegalArgumentException;
        //  99     120    123    127    Ljava/lang/IllegalArgumentException;
        //  111    140    143    147    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
