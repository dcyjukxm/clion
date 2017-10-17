// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.codeInsight.editorActions.EnterHandler;
import java.util.function.Predicate;
import com.intellij.openapi.editor.Document;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.psi.PsiComment;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter;

public class OCEnterInBlockCommentHandler extends EnterHandlerDelegateAdapter
{
    private static OCCommenter COMMENTER;
    
    @Override
    public EnterHandlerDelegate.Result preprocessEnter(@NotNull final PsiFile psiFile, @NotNull final Editor editor, @NotNull final Ref<Integer> ref, @NotNull final Ref<Integer> ref2, @NotNull final DataContext dataContext, final EditorActionHandler editorActionHandler) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretOffsetRef", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (ref2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretAdvanceRef", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        int n = (int)ref.get();
        PsiDocumentManager.getInstance(psiFile.getProject()).commitDocument(editor.getDocument());
        final PsiElement element = findElementAt(psiFile, editor, n);
        if (this.shouldBeProcessed(psiFile, element, n)) {
            final String text = element.getText();
            Label_0314: {
                try {
                    if (!text.endsWith("*/")) {
                        break Label_0314;
                    }
                    final PsiElement psiElement = element;
                    final TextRange textRange = psiElement.getTextRange();
                    final int n2 = n;
                    final boolean b = textRange.contains(n2);
                    if (!b) {
                        break Label_0314;
                    }
                    break Label_0314;
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                try {
                    final PsiElement psiElement = element;
                    final TextRange textRange = psiElement.getTextRange();
                    final int n2 = n;
                    final boolean b = textRange.contains(n2);
                    if (!b) {
                        return EnterHandlerDelegate.Result.Continue;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
            final int startOffset = element.getTextRange().getStartOffset();
            final int n3 = n - startOffset;
            final String substring = text.substring(0, n3);
            final StringBuilder sb = new StringBuilder("\n");
            sb.append(getIndentLine(element));
            int intValue = -1;
            int intValue2 = -1;
            if (substring.indexOf(10) == -1) {
                final Pair<Integer, Integer> generateComment = this.generateComment(psiFile, editor, n, text, n3, sb);
                intValue2 = (int)generateComment.first;
                intValue = (int)generateComment.second;
            }
            else {
                n = a(substring, n3, text, n, sb);
            }
            this.flushChanges(psiFile, editor, n, text, startOffset, n3, intValue2, sb);
            if (intValue == -1) {
                intValue = n + sb.length();
            }
            editor.getCaretModel().moveToOffset(intValue);
            return EnterHandlerDelegate.Result.Stop;
        }
        return EnterHandlerDelegate.Result.Continue;
    }
    
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
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldBeProcessed"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: iload_3        
        //    46: invokestatic    com/intellij/psi/util/PsiUtilCore.getLanguageAtOffset:(Lcom/intellij/psi/PsiFile;I)Lcom/intellij/lang/Language;
        //    49: instanceof      Lcom/jetbrains/cidr/lang/OCLanguage;
        //    52: ifeq            147
        //    55: aload_2        
        //    56: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    59: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    62: if_acmpne       147
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aload_2        
        //    73: ifnull          147
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_2        
        //    84: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //    89: iload_3        
        //    90: if_icmpge       147
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: aload_2        
        //   101: checkcast       Lcom/intellij/psi/PsiComment;
        //   104: invokestatic    com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade.isDoxygenComment:(Lcom/intellij/psi/PsiComment;)Z
        //   107: ifne            147
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: aload_2        
        //   118: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   123: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   126: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PRAGMA:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   129: if_acmpeq       147
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: iconst_1       
        //   140: goto            148
        //   143: invokestatic    com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: iconst_0       
        //   148: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     65     68     72     Ljava/lang/IllegalArgumentException;
        //  55     76     79     83     Ljava/lang/IllegalArgumentException;
        //  72     93     96     100    Ljava/lang/IllegalArgumentException;
        //  83     110    113    117    Ljava/lang/IllegalArgumentException;
        //  100    132    135    139    Ljava/lang/IllegalArgumentException;
        //  117    143    143    147    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
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
    
    protected Pair<Integer, Integer> generateComment(@NotNull final PsiFile psiFile, @NotNull final Editor editor, final int n, @NotNull final String s, final int n2, @NotNull final StringBuilder sb) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "generateComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "generateComment"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commentText", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "generateComment"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indent", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "generateComment"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        sb.append("* ");
        final int n3 = -1;
        int n4 = -1;
        final PsiElement element = findElementAt(psiFile, editor, n);
        int n10 = 0;
        Label_0256: {
            Label_0237: {
                try {
                    final Character c2;
                    final String s2;
                    final int n5;
                    final int n6;
                    final boolean b;
                    if (!needCommentSuffixGeneration(editor, (PsiComment)element, s, c -> {
                        Label_0029_1: {
                            try {
                                if (!Character.isJavaIdentifierPart(c)) {
                                    (char)c2;
                                    s2.indexOf(n5);
                                    if (n6 >= 0) {
                                        break Label_0029_1;
                                    }
                                    else {
                                        return 0;
                                    }
                                }
                                else {
                                    break Label_0029_1;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw b(ex5);
                            }
                            try {
                                (char)c2;
                                s2.indexOf(n5);
                                if (n6 >= 0) {
                                    return b;
                                }
                            }
                            catch (IllegalArgumentException ex6) {
                                throw b(ex6);
                            }
                        }
                        return b;
                    })) {
                        return (Pair<Integer, Integer>)Pair.create((Object)n4, (Object)n3);
                    }
                    final String s3 = s;
                    final int n7 = 10;
                    final int n8 = s3.indexOf(n7);
                    final int n9 = -1;
                    if (n8 != n9) {
                        break Label_0237;
                    }
                    break Label_0237;
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
                try {
                    final String s3 = s;
                    final int n7 = 10;
                    final int n8 = s3.indexOf(n7);
                    final int n9 = -1;
                    if (n8 != n9) {
                        n10 = s.indexOf(10);
                        break Label_0256;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
            }
            n10 = s.length();
        }
        n4 = n + sb.length() + (n10 - n2);
        return (Pair<Integer, Integer>)Pair.create((Object)n4, (Object)n3);
    }
    
    protected void flushChanges(@NotNull final PsiFile psiFile, @NotNull final Editor editor, final int n, @NotNull final String s, final int n2, final int n3, int n4, @NotNull final StringBuilder sb) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "flushChanges"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "flushChanges"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commentText", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "flushChanges"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indent", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "flushChanges"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final Document document = editor.getDocument();
        final PsiElement element = findElementAt(psiFile, editor, n);
        if (CodeInsightSettings.getInstance().SMART_INDENT_ON_ENTER) {
            int n5 = n3;
        Label_0252:
            while (true) {
                Label_0239: {
                    try {
                        if (n5 >= s.length()) {
                            break Label_0252;
                        }
                        final String s2 = "\t ";
                        final String s3 = s;
                        final int n6 = n5;
                        final char c = s3.charAt(n6);
                        final int n7 = s2.indexOf(c);
                        if (n7 >= 0) {
                            break Label_0239;
                        }
                        break Label_0252;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final String s2 = "\t ";
                        final String s3 = s;
                        final int n6 = n5;
                        final char c = s3.charAt(n6);
                        final int n7 = s2.indexOf(c);
                        if (n7 >= 0) {
                            ++n5;
                            --n4;
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
                break;
            }
            document.deleteString(n, n2 + n5);
        }
        document.insertString(n, (CharSequence)sb);
        if (n4 >= 0) {
            document.insertString(n4, (CharSequence)new StringBuilder("\n").append(getIndentLine(element)).append("*/"));
        }
    }
    
    public static boolean needCommentSuffixGeneration(@NotNull final Editor editor, @Nullable final PsiComment psiComment, @NotNull final String s, @NotNull final Predicate<Character> predicate) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "needCommentSuffixGeneration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commentText", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "needCommentSuffixGeneration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (predicate == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "isNotAcceptedSymbol", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "needCommentSuffixGeneration"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (psiComment == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (EnterHandler.isCommentComplete(psiComment, (CodeDocumentationAwareCommenter)OCEnterInBlockCommentHandler.COMMENTER, editor)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            try {
                if (char1 == '\n') {
                    break;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            try {
                if (predicate.test(char1)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
        }
        return true;
    }
    
    private static int a(final String s, final int n, final String s2, int n2, final StringBuilder sb) {
        int i = s.lastIndexOf(10) + 1;
        char char1 = ' ';
        final StringBuilder sb2 = new StringBuilder("\n");
        while (i < n) {
            final String s3 = "\t ";
            final char c = char1 = s2.charAt(i);
            try {
                if (s3.indexOf(c) >= 0) {
                    ++i;
                    sb2.append(char1);
                    continue;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            break;
        }
        try {
            if (sb.length() < sb2.length()) {
                sb.setLength(0);
                sb.append((CharSequence)sb2);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    Label_0223:
        while (true) {
            Label_0131: {
                try {
                    if (char1 != '*') {
                        return n2;
                    }
                    final StringBuilder sb3 = sb;
                    final char c2 = '*';
                    sb3.append(c2);
                    final int n3 = ++i;
                    final int n4 = n;
                    if (n3 == n4) {
                        break Label_0131;
                    }
                    break Label_0182;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    final StringBuilder sb3 = sb;
                    final char c2 = '*';
                    sb3.append(c2);
                    final int n3 = ++i;
                    final int n4 = n;
                    if (n3 != n4) {
                        break Label_0182;
                    }
                    if (i >= s2.length()) {
                        break Label_0182;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            Label_0147: {
                break Label_0147;
                while (i < n) {
                    final String s4 = "\t ";
                    final char char2 = s2.charAt(i);
                    try {
                        if (s4.indexOf(char2) >= 0) {
                            ++i;
                            sb.append(char2);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    break;
                }
                break Label_0223;
            }
            final String s5 = "\t ";
            final char char3 = s2.charAt(i);
            try {
                if (s5.indexOf(char3) >= 0) {
                    ++n2;
                    sb.append(char3);
                }
                break Label_0223;
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            continue;
        }
        final int length = sb.length();
        Label_0256: {
            try {
                if (length == 0) {
                    return n2;
                }
                final StringBuilder sb4 = sb;
                final int n5 = length;
                final int n6 = 1;
                final int n7 = n5 - n6;
                final char c3 = sb4.charAt(n7);
                final char c4 = '*';
                if (c3 == c4) {
                    break Label_0256;
                }
                return n2;
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            try {
                final StringBuilder sb4 = sb;
                final int n5 = length;
                final int n6 = 1;
                final int n7 = n5 - n6;
                final char c3 = sb4.charAt(n7);
                final char c4 = '*';
                if (c3 == c4) {
                    sb.append(' ');
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
        }
        return n2;
    }
    
    @Nullable
    public static PsiElement findElementAt(@NotNull final PsiFile psiFile, @NotNull final Editor editor, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "findElementAt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "findElementAt"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        Label_0115: {
            try {
                if (n != editor.getDocument().getTextLength()) {
                    break Label_0115;
                }
                final int n2 = n;
                if (n2 > 0) {
                    break Label_0115;
                }
                break Label_0115;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final int n2 = n;
                if (n2 > 0) {
                    final int n3 = n - 1;
                    return psiFile.findElementAt(n3);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        final int n3 = n;
        return psiFile.findElementAt(n3);
    }
    
    @NotNull
    public static String getFirstCommentLineIndentFromContext(@Nullable final PsiElement psiElement) {
        String substring = "";
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (psiElement != null) {
                        break Label_0055;
                    }
                    s = "";
                    if (s == null) {
                        break Label_0020;
                    }
                    return s;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    s = "";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "getFirstCommentLineIndentFromContext"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return s;
        }
        final PsiElement prevLeaf = PsiTreeUtil.prevLeaf(psiElement);
        if (prevLeaf instanceof PsiWhiteSpace) {
            final String text = prevLeaf.getText();
            final int lastIndex = text.lastIndexOf(10);
            if (lastIndex >= 0) {
                substring = text.substring(lastIndex + 1);
            }
            else if (prevLeaf.getTextOffset() == 0) {
                substring = text;
            }
        }
        String s2;
        try {
            s2 = substring;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "getFirstCommentLineIndentFromContext"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return s2;
    }
    
    @NotNull
    public static String customSettingsIndent(@Nullable final PsiElement psiElement) {
        String s3 = null;
        Label_0123: {
            String s2 = null;
            Label_0088: {
                Label_0052: {
                    String s = null;
                    Label_0017: {
                        try {
                            if (psiElement != null) {
                                break Label_0052;
                            }
                            s = "";
                            if (s == null) {
                                break Label_0017;
                            }
                            return s;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            s = "";
                            if (s == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "customSettingsIndent"));
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                    return s;
                    try {
                        if (!((OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(psiElement.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class)).INDENT_BLOCK_COMMENT) {
                            break Label_0123;
                        }
                        s2 = " ";
                        if (s2 == null) {
                            break Label_0088;
                        }
                        return s2;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    s2 = " ";
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "customSettingsIndent"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            return s2;
            try {
                s3 = "";
                if (s3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "customSettingsIndent"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return s3;
    }
    
    @NotNull
    public static String getIndentLine(@Nullable final PsiElement psiElement) {
        String string;
        try {
            string = getFirstCommentLineIndentFromContext(psiElement) + customSettingsIndent(psiElement);
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCEnterInBlockCommentHandler", "getIndentLine"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return string;
    }
    
    static {
        OCEnterInBlockCommentHandler.COMMENTER = new OCCommenter();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
