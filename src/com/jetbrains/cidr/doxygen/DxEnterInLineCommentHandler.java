// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocCommentBase;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.editor.OCEnterInBlockCommentHandler;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter;

public class DxEnterInLineCommentHandler extends EnterHandlerDelegateAdapter
{
    @Override
    public EnterHandlerDelegate.Result preprocessEnter(@NotNull final PsiFile psiFile, @NotNull final Editor editor, @NotNull final Ref<Integer> ref, @NotNull final Ref<Integer> ref2, @NotNull final DataContext dataContext, final EditorActionHandler editorActionHandler) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretOffsetRef", "com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (ref2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretAdvance", "com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        final int intValue = (int)ref.get();
        final Document document = editor.getDocument();
        PsiDocumentManager.getInstance(psiFile.getProject()).commitDocument(document);
        final PsiElement element = psiFile.findElementAt(intValue);
        PsiElement prevSibling = null;
        Label_0290: {
            try {
                if (element instanceof PsiWhiteSpace) {
                    prevSibling = element.getPrevSibling();
                    break Label_0290;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            prevSibling = element;
        }
        final PsiElement psiElement = prevSibling;
        if (this.shouldBeProcessed(document, psiElement, intValue)) {
            final String text = document.getText();
            try {
                if (!(psiElement instanceof PsiComment) || !DoxygenUtil.isDoxygenEOLComment((PsiComment)psiElement)) {
                    return EnterHandlerDelegate.Result.Continue;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            final PsiComment psiComment = (PsiComment)psiElement;
            final String text2 = psiComment.getText();
            final String string = OCEnterInBlockCommentHandler.getFirstCommentLineIndentFromContext((PsiElement)psiComment) + text2.substring(0, 3);
            final int n = intValue - psiComment.getTextRange().getStartOffset();
            final StringBuilder sb = new StringBuilder();
            final Project project = editor.getProject();
            try {
                if (project == null) {
                    return EnterHandlerDelegate.Result.Continue;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            int n2 = intValue;
            int n3 = 0;
            Label_0662: {
                Label_0645: {
                    Label_0615: {
                        Label_0600: {
                            Label_0557: {
                                Label_0459: {
                                    try {
                                        if (text2.length() != 3) {
                                            break Label_0557;
                                        }
                                        final PsiComment psiComment2 = psiComment;
                                        final boolean b = DoxygenUtil.hasNextDocCommentSibling(psiComment2);
                                        if (!b) {
                                            break Label_0459;
                                        }
                                        break Label_0557;
                                    }
                                    catch (IllegalArgumentException ex9) {
                                        throw b(ex9);
                                    }
                                    try {
                                        final PsiComment psiComment2 = psiComment;
                                        final boolean b = DoxygenUtil.hasNextDocCommentSibling(psiComment2);
                                        if (b) {
                                            break Label_0557;
                                        }
                                        if (!(psiComment instanceof PsiDocCommentBase)) {
                                            break Label_0557;
                                        }
                                    }
                                    catch (IllegalArgumentException ex10) {
                                        throw b(ex10);
                                    }
                                }
                                final PsiElement owner = ((PsiDocCommentBase)psiComment).getOwner();
                                final String a = a(project);
                                sb.append(DxEnterInBlockCommentHandler.addBriefIfNeeded(project, a));
                                n3 = n2 + sb.length();
                                try {
                                    DxEnterInBlockCommentHandler.generateDocComment(owner, string, a, sb);
                                    if (sb.length() != 0) {
                                        sb.insert(0, ' ');
                                        ++n3;
                                    }
                                }
                                catch (IllegalArgumentException ex11) {
                                    throw b(ex11);
                                }
                                break Label_0662;
                                try {
                                    if (DxEnterInBlockCommentHandler.countWS(text2, n) >= text2.length()) {
                                        return EnterHandlerDelegate.Result.Continue;
                                    }
                                    final StringBuilder sb2 = sb;
                                    final String s = "\n";
                                    final StringBuilder sb3 = sb2.append(s);
                                    final String s2 = string;
                                    sb3.append(s2);
                                    final PsiComment psiComment3 = psiComment;
                                    final boolean b2 = DoxygenUtil.hasArrow(psiComment3);
                                    if (b2) {
                                        break Label_0600;
                                    }
                                    break Label_0615;
                                }
                                catch (IllegalArgumentException ex12) {
                                    throw b(ex12);
                                }
                            }
                            try {
                                final StringBuilder sb2 = sb;
                                final String s = "\n";
                                final StringBuilder sb3 = sb2.append(s);
                                final String s2 = string;
                                sb3.append(s2);
                                final PsiComment psiComment3 = psiComment;
                                final boolean b2 = DoxygenUtil.hasArrow(psiComment3);
                                if (b2) {
                                    sb.append("<");
                                }
                            }
                            catch (IllegalArgumentException ex13) {
                                throw b(ex13);
                            }
                        }
                        try {
                            if (text.charAt(intValue) != ' ') {
                                sb.append(" ");
                                break Label_0645;
                            }
                        }
                        catch (IllegalArgumentException ex14) {
                            throw b(ex14);
                        }
                    }
                    ++n2;
                }
                n3 = n2 + sb.length();
                try {
                    if (sb.length() == 0) {
                        return EnterHandlerDelegate.Result.Continue;
                    }
                }
                catch (IllegalArgumentException ex15) {
                    throw b(ex15);
                }
            }
            final PsiDocumentManager instance = PsiDocumentManager.getInstance(project);
            document.insertString(intValue, (CharSequence)sb.toString());
            editor.getCaretModel().moveToOffset(n3);
            instance.commitDocument(document);
            return EnterHandlerDelegate.Result.Stop;
        }
        return EnterHandlerDelegate.Result.Continue;
    }
    
    protected boolean shouldBeProcessed(final Document p0, final PsiElement p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnull          97
        //     4: aload_2        
        //     5: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //    10: iload_3        
        //    11: if_icmpge       97
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: iload_3        
        //    22: aload_1        
        //    23: invokeinterface com/intellij/openapi/editor/Document.getTextLength:()I
        //    28: if_icmpge       97
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_2        
        //    39: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    44: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    47: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PRAGMA:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    50: if_acmpeq       97
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: aload_2        
        //    61: iconst_1       
        //    62: anewarray       Ljava/lang/Class;
        //    65: dup            
        //    66: iconst_0       
        //    67: ldc             Lcom/intellij/psi/PsiWhiteSpace;.class
        //    69: aastore        
        //    70: invokestatic    com/intellij/psi/util/PsiTreeUtil.skipSiblingsBackward:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    73: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    76: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    79: if_acmpeq       97
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: iconst_1       
        //    90: goto            98
        //    93: invokestatic    com/jetbrains/cidr/doxygen/DxEnterInLineCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: iconst_0       
        //    98: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  4      31     34     38     Ljava/lang/IllegalArgumentException;
        //  21     53     56     60     Ljava/lang/IllegalArgumentException;
        //  38     82     85     89     Ljava/lang/IllegalArgumentException;
        //  60     93     93     97     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    private static String a(final Project project) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(project).getCustomSettings((Class)OCCodeStyleSettings.class);
        try {
            if (ocCodeStyleSettings.TAG_PREFIX_OF_LINE_COMMENT == OCCodeStyleSettings.DocTagPrefix.AT) {
                return "@";
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return "\\";
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
