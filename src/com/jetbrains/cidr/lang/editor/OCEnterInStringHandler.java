// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.psi.StringEscapesTokenTypes;
import com.intellij.lexer.LexerBase;
import com.jetbrains.cidr.lang.lexer.OCPrefixStringLiteralLexer;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.lexer.OCRawStringLexerBase;
import com.jetbrains.cidr.lang.lexer.OCHighlightingLexer;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter;

public class OCEnterInStringHandler extends EnterHandlerDelegateAdapter
{
    @Override
    public EnterHandlerDelegate.Result preprocessEnter(@NotNull final PsiFile psiFile, @NotNull final Editor editor, @NotNull final Ref<Integer> ref, @NotNull final Ref<Integer> ref2, @NotNull final DataContext dataContext, final EditorActionHandler editorActionHandler) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCEnterInStringHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCEnterInStringHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretOffsetRef", "com/jetbrains/cidr/lang/editor/OCEnterInStringHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (ref2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caretAdvanceRef", "com/jetbrains/cidr/lang/editor/OCEnterInStringHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/editor/OCEnterInStringHandler", "preprocessEnter"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        int n = (int)ref.get();
        PsiDocumentManager.getInstance(psiFile.getProject()).commitDocument(editor.getDocument());
        final PsiElement element = psiFile.findElementAt(n);
        try {
            if (element == null || element.getTextOffset() >= n) {
                return EnterHandlerDelegate.Result.Continue;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        final Document document = editor.getDocument();
        final String text = document.getText();
        final ASTNode node = element.getNode();
        final IElementType elementType = node.getElementType();
        if (elementType == OCTokenTypes.CHARACTER_LITERAL) {
            final int startOffset = node.getStartOffset();
            ref.set((Object)startOffset);
            ref2.set((Object)(n - startOffset));
            return EnterHandlerDelegate.Result.DefaultForceIndent;
        }
        if (OCTokenTypes.ALL_STRINGS.contains(elementType)) {
            final TextRange textRange = node.getTextRange();
            char c = '\0';
            IElementType prefix_TYPE = null;
            Object stringLiteralLexer = null;
            Label_0437: {
                try {
                    c = '\"';
                    prefix_TYPE = OCHighlightingLexer.PREFIX_TYPE;
                    if (elementType == OCTokenTypes.STRING_LITERAL) {
                        stringLiteralLexer = OCHighlightingLexer.createStringLiteralLexer('\"', node.getElementType());
                        break Label_0437;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
                stringLiteralLexer = new OCRawStringLexerBase(OCTokenTypes.STRING_LITERAL, OCTokenTypes.STRING_LITERAL);
            }
            final OCPrefixStringLiteralLexer ocPrefixStringLiteralLexer = new OCPrefixStringLiteralLexer(c, prefix_TYPE, (LexerBase)stringLiteralLexer);
            ocPrefixStringLiteralLexer.start((CharSequence)text, textRange.getStartOffset(), textRange.getEndOffset());
            while (true) {
                Label_0627: {
                    Label_0484: {
                        try {
                            if (ocPrefixStringLiteralLexer.getTokenType() == null) {
                                break;
                            }
                            final OCPrefixStringLiteralLexer ocPrefixStringLiteralLexer2 = ocPrefixStringLiteralLexer;
                            final int n2 = ocPrefixStringLiteralLexer2.getTokenStart();
                            final int n3 = n;
                            if (n2 < n3) {
                                break Label_0484;
                            }
                            break Label_0627;
                        }
                        catch (IllegalArgumentException ex8) {
                            throw b(ex8);
                        }
                        try {
                            final OCPrefixStringLiteralLexer ocPrefixStringLiteralLexer2 = ocPrefixStringLiteralLexer;
                            final int n2 = ocPrefixStringLiteralLexer2.getTokenStart();
                            final int n3 = n;
                            if (n2 >= n3) {
                                break Label_0627;
                            }
                            if (n > ocPrefixStringLiteralLexer.getTokenEnd()) {
                                break Label_0627;
                            }
                        }
                        catch (IllegalArgumentException ex9) {
                            throw b(ex9);
                        }
                    }
                    final IElementType tokenType = ocPrefixStringLiteralLexer.getTokenType();
                    if (StringEscapesTokenTypes.STRING_LITERAL_ESCAPES.contains(tokenType)) {
                        n = ocPrefixStringLiteralLexer.getTokenEnd();
                        break;
                    }
                    if (tokenType == OCHighlightingLexer.PREFIX_TYPE) {
                        final int tokenStart = ocPrefixStringLiteralLexer.getTokenStart();
                        ref.set((Object)tokenStart);
                        ref2.set((Object)(n - tokenStart));
                        return EnterHandlerDelegate.Result.DefaultForceIndent;
                    }
                    Label_0593: {
                        try {
                            if (tokenType != OCTokenTypes.STRING_LITERAL) {
                                break;
                            }
                            final OCElementType ocElementType = (OCElementType)elementType;
                            final OCElementType ocElementType2 = OCTokenTypes.RAW_STRING_LITERAL;
                            if (ocElementType == ocElementType2) {
                                break Label_0593;
                            }
                            break;
                        }
                        catch (IllegalArgumentException ex10) {
                            throw b(ex10);
                        }
                        try {
                            final OCElementType ocElementType = (OCElementType)elementType;
                            final OCElementType ocElementType2 = OCTokenTypes.RAW_STRING_LITERAL;
                            if (ocElementType == ocElementType2) {
                                document.insertString(n, (CharSequence)"\n");
                                editor.getCaretModel().moveToOffset(n + 1);
                                return EnterHandlerDelegate.Result.Stop;
                            }
                            break;
                        }
                        catch (IllegalArgumentException ex11) {
                            throw b(ex11);
                        }
                    }
                }
                ocPrefixStringLiteralLexer.advance();
            }
            final int shiftBackward = CharArrayUtil.shiftBackward((CharSequence)text, CharArrayUtil.shiftForwardUntil((CharSequence)text, n, "\n") - 1, " \t");
            if (text.charAt(shiftBackward) == '\\') {
                final int min = Math.min(n, shiftBackward);
                document.insertString(min, (CharSequence)"\\\n");
                editor.getCaretModel().moveToOffset(min + 2);
                return EnterHandlerDelegate.Result.Stop;
            }
            document.insertString(n, (CharSequence)"\"\"");
            ref2.set((Object)1);
            ref.set((Object)(n + 1));
            return EnterHandlerDelegate.Result.DefaultForceIndent;
        }
        return EnterHandlerDelegate.Result.Continue;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
