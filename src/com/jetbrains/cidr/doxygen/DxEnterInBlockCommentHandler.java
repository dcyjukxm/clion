// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.codeInsight.CodeInsightSettings;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenCmd;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.PsiComment;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocCommentBase;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.editor.OCEnterInBlockCommentHandler;

public class DxEnterInBlockCommentHandler extends OCEnterInBlockCommentHandler
{
    @Override
    public EnterHandlerDelegate.Result preprocessEnter(@NotNull final PsiFile element, @NotNull final Editor editor, @NotNull final Ref<Integer> ref, @NotNull final Ref<Integer> ref2, @NotNull final DataContext dataContext, final EditorActionHandler editorActionHandler) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretOffsetRef", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (ref2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretAdvanceRef", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        final PsiFile topLevelFile = InjectedLanguageUtil.getTopLevelFile((PsiElement)element);
        final Editor topLevelEditor = InjectedLanguageUtil.getTopLevelEditor(editor);
        final int offset = topLevelEditor.getCaretModel().getOffset();
        try {
            if (topLevelFile == null) {
                return EnterHandlerDelegate.Result.Continue;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        return super.preprocessEnter(topLevelFile, topLevelEditor, (Ref<Integer>)Ref.create((Object)offset), ref2, dataContext, editorActionHandler);
    }
    
    @Override
    protected boolean shouldBeProcessed(@NotNull final PsiFile p0, @Nullable final PsiElement p1, final int p2) {
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
        //    24: ldc             "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldBeProcessed"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    48: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    51: if_acmpne       110
        //    54: aload_2        
        //    55: instanceof      Lcom/intellij/psi/PsiComment;
        //    58: ifeq            110
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aload_2        
        //    69: checkcast       Lcom/intellij/psi/PsiComment;
        //    72: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.isDoxygenBlockComment:(Lcom/intellij/psi/PsiComment;)Z
        //    75: ifeq            110
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload_2        
        //    86: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //    91: iload_3        
        //    92: if_icmpge       110
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: iconst_1       
        //   103: goto            111
        //   106: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: iconst_0       
        //   111: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     61     64     68     Ljava/lang/IllegalArgumentException;
        //  54     78     81     85     Ljava/lang/IllegalArgumentException;
        //  68     95     98     102    Ljava/lang/IllegalArgumentException;
        //  85     106    106    110    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    
    @Override
    protected Pair<Integer, Integer> generateComment(@NotNull final PsiFile psiFile, @NotNull final Editor editor, final int n, @NotNull final String s, final int n2, @NotNull final StringBuilder sb) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "generateComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "generateComment"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commentText", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "generateComment"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "generateComment"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        sb.append("* ");
        final Project project = editor.getProject();
        final String a = a(project);
        final boolean b;
        final boolean needCommentSuffixGeneration = OCEnterInBlockCommentHandler.needCommentSuffixGeneration(editor, findCommentAt(psiFile, editor, n), s, c -> {
            try {
                if ("{}".indexOf(c) >= 0) {
                    return b;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            return b;
        });
        try {
            if (needCommentSuffixGeneration) {
                sb.append(addBriefIfNeeded(project, a));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        final int n3 = n + sb.length();
        Label_0419: {
            try {
                if (!s.startsWith("/**")) {
                    if (!s.startsWith("/*!")) {
                        break Label_0419;
                    }
                }
            }
            catch (IllegalArgumentException ex7) {
                throw c(ex7);
            }
            final Document document = editor.getDocument();
            if (project != null) {
                final PsiDocumentManager instance = PsiDocumentManager.getInstance(project);
                document.insertString(n, (CharSequence)"*/");
                instance.commitDocument(document);
                final PsiComment comment = findCommentAt(psiFile, editor, n);
                Label_0401: {
                    try {
                        if (DoxygenUtil.hasNextDocCommentSibling(comment) || !(comment instanceof PsiDocCommentBase)) {
                            break Label_0401;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw c(ex8);
                    }
                    generateDocComment(((PsiDocCommentBase)comment).getOwner(), OCEnterInBlockCommentHandler.getIndentLine((PsiElement)comment) + "*", a, sb);
                }
                document.deleteString(n, n + 2);
                instance.commitDocument(document);
            }
        }
        int n4 = -1;
        if (needCommentSuffixGeneration) {
            n4 = n + sb.length();
        }
        return (Pair<Integer, Integer>)Pair.create((Object)n4, (Object)n3);
    }
    
    @NotNull
    public static String addBriefIfNeeded(@Nullable final Project project, @NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tagPrefix", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "addBriefIfNeeded"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        String s6 = null;
        Label_0142: {
            if (project != null) {
                final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(project).getCustomSettings((Class)OCCodeStyleSettings.class);
                String s5 = null;
                Label_0107: {
                    try {
                        if (!ocCodeStyleSettings.ADD_BRIEF_TAG) {
                            break Label_0142;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final String s2 = s;
                        final StringBuilder sb2 = sb.append(s2);
                        final DoxygenCmd doxygenCmd = DoxygenCmd.BRIEF;
                        final String s3 = doxygenCmd.toString();
                        final StringBuilder sb3 = sb2.append(s3);
                        final String s4 = " ";
                        final StringBuilder sb4 = sb3.append(s4);
                        s5 = sb4.toString();
                        if (s5 == null) {
                            break Label_0107;
                        }
                        return s5;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                    try {
                        final StringBuilder sb = new StringBuilder();
                        final String s2 = s;
                        final StringBuilder sb2 = sb.append(s2);
                        final DoxygenCmd doxygenCmd = DoxygenCmd.BRIEF;
                        final String s3 = doxygenCmd.toString();
                        final StringBuilder sb3 = sb2.append(s3);
                        final String s4 = " ";
                        final StringBuilder sb4 = sb3.append(s4);
                        s5 = sb4.toString();
                        if (s5 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "addBriefIfNeeded"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw c(ex3);
                    }
                }
                return s5;
            }
            try {
                s6 = "";
                if (s6 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "addBriefIfNeeded"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
        return s6;
    }
    
    public static void generateDocComment(@Nullable final PsiElement p0, @NotNull final String p1, @NotNull final String p2, @NotNull final StringBuilder p3) {
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
        //    18: ldc             "linePrefix"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "generateDocComment"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "tagPrefix"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "generateDocComment"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "result"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "generateDocComment"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: ifnonnull       141
        //   136: return         
        //   137: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: aload_0        
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload_3        
        //   145: invokedynamic   accept:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/StringBuilder;)Ljava/util/function/Consumer;
        //   150: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.traverseTemplateParametersList:(Lcom/intellij/psi/PsiElement;Ljava/util/function/Consumer;)V
        //   153: aload_0        
        //   154: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   157: ifeq            331
        //   160: aload_0        
        //   161: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   164: astore          4
        //   166: aload           4
        //   168: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getParameters:()Ljava/util/List;
        //   173: astore          5
        //   175: aload           5
        //   177: ifnull          239
        //   180: aload           5
        //   182: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   187: astore          6
        //   189: aload           6
        //   191: invokeinterface java/util/Iterator.hasNext:()Z
        //   196: ifeq            239
        //   199: aload           6
        //   201: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   206: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //   209: astore          7
        //   211: aload           7
        //   213: invokeinterface com/intellij/psi/PsiNamedElement.getName:()Ljava/lang/String;
        //   218: aload           7
        //   220: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.getName:(Ljava/lang/String;Lcom/intellij/psi/PsiNamedElement;)Ljava/lang/String;
        //   223: astore          8
        //   225: aload           8
        //   227: aload_1        
        //   228: aload_2        
        //   229: getstatic       com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd.PARAM:Lcom/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd;
        //   232: aload_3        
        //   233: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd;Ljava/lang/StringBuilder;)V
        //   236: goto            189
        //   239: aload           4
        //   241: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   246: astore          6
        //   248: aload           6
        //   250: ifnull          331
        //   253: aload           6
        //   255: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   260: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //   263: ifne            331
        //   266: goto            273
        //   269: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: aload           4
        //   275: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   280: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   283: ifne            331
        //   286: goto            293
        //   289: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: aload_3        
        //   294: ldc             "\n"
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: aload_1        
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: ldc             " "
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: aload_2        
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: getstatic       com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd.RETURN:Lcom/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd;
        //   315: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   318: ldc             " "
        //   320: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   323: pop            
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    137    137    141    Ljava/lang/IllegalArgumentException;
        //  248    266    269    273    Ljava/lang/IllegalArgumentException;
        //  253    286    289    293    Ljava/lang/IllegalArgumentException;
        //  273    324    327    331    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0273:
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
    
    @Override
    protected void flushChanges(@NotNull final PsiFile psiFile, @NotNull final Editor editor, final int n, @NotNull final String s, final int n2, final int n3, int n4, @NotNull final StringBuilder sb) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "flushChanges"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "flushChanges"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commentText", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "flushChanges"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indent", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "flushChanges"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        final Document document = editor.getDocument();
        final PsiElement element = OCEnterInBlockCommentHandler.findElementAt(psiFile, editor, n);
        final int countWS = countWS(s, n3);
        int n5 = 0;
        Label_0278: {
            Label_0248: {
                try {
                    if (CodeInsightSettings.getInstance().SMART_INDENT_ON_ENTER) {
                        document.deleteString(n, n2 + countWS);
                        break Label_0248;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw c(ex5);
                }
                if (n4 != -1) {
                    n4 += countWS - n3;
                }
                try {
                    if (s.indexOf(10) != -1) {
                        n5 = s.indexOf(10);
                        break Label_0278;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw c(ex6);
                }
            }
            n5 = s.length();
        }
        final int n6 = n5;
        document.insertString(n, (CharSequence)sb);
        if (n4 >= 0) {
            final String firstCommentLineIndentFromContext = OCEnterInBlockCommentHandler.getFirstCommentLineIndentFromContext(element);
            final StringBuilder append = new StringBuilder().append('\n').append(firstCommentLineIndentFromContext).append(OCEnterInBlockCommentHandler.customSettingsIndent(element)).append("*/");
            try {
                if (n6 > countWS) {
                    append.append('\n').append(firstCommentLineIndentFromContext);
                }
            }
            catch (IllegalArgumentException ex7) {
                throw c(ex7);
            }
            document.insertString(n4, (CharSequence)append);
        }
    }
    
    public static int countWS(final String s, int n) {
        while (true) {
            Label_0028: {
                try {
                    if (n >= s.length()) {
                        return n;
                    }
                    final String s2 = "\t ";
                    final String s3 = s;
                    final int n2 = n;
                    final char c = s3.charAt(n2);
                    final int n3 = s2.indexOf(c);
                    if (n3 >= 0) {
                        break Label_0028;
                    }
                    return n;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final String s2 = "\t ";
                    final String s3 = s;
                    final int n2 = n;
                    final char c = s3.charAt(n2);
                    final int n3 = s2.indexOf(c);
                    if (n3 >= 0) {
                        ++n;
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            break;
        }
        return n;
    }
    
    @Nullable
    public static PsiComment findCommentAt(@NotNull final PsiFile psiFile, @NotNull final Editor editor, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "findCommentAt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "findCommentAt"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return (PsiComment)PsiTreeUtil.getNonStrictParentOfType(OCEnterInBlockCommentHandler.findElementAt(psiFile, editor, n), new Class[] { PsiComment.class });
    }
    
    @NotNull
    private static String a(@Nullable final Project project) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(project).getCustomSettings((Class)OCCodeStyleSettings.class);
        String s = null;
        Label_0034: {
            try {
                if (ocCodeStyleSettings.TAG_PREFIX_OF_BLOCK_COMMENT == OCCodeStyleSettings.DocTagPrefix.AT) {
                    final String s2;
                    s = (s2 = "@");
                    break Label_0034;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            String s2;
            s = (s2 = "\\");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "getTagPrefix"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return s;
    }
    
    private static void a(@NotNull final String s, @NotNull final String s2, @NotNull final String s3, @NotNull final DoxygenCmd doxygenCmd, @NotNull final StringBuilder sb) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "appendLine"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "linePrefix", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "appendLine"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (s3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tagPrefix", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "appendLine"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (doxygenCmd == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tag", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "appendLine"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/doxygen/DxEnterInBlockCommentHandler", "appendLine"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        try {
            if (!s.equals("<unnamed>")) {
                sb.append("\n").append(s2).append(" ").append(s3).append(doxygenCmd).append(" ").append(s).append(" ");
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
