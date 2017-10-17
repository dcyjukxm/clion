// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.spellchecker;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.spellchecker.inspections.Splitter;
import com.intellij.spellchecker.inspections.PlainTextSplitter;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.StringEscapesTokenTypes;
import com.intellij.lexer.LexerBase;
import com.jetbrains.cidr.lang.lexer.OCPrefixStringLiteralLexer;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.lexer.OCHighlightingLexer;
import com.intellij.spellchecker.tokenizer.TokenConsumer;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.tokenizer.Tokenizer;

private static class StringLiteralTokenizer extends Tokenizer<PsiElement>
{
    @Override
    public void tokenize(@NotNull final PsiElement psiElement, @NotNull final TokenConsumer tokenConsumer) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "tokenize"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (tokenConsumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "tokenize"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final TextRange textRange = psiElement.getTextRange();
        char c = '\0';
        IElementType prefix_TYPE = null;
        Object o = null;
        Label_0136: {
            try {
                c = '\"';
                prefix_TYPE = OCHighlightingLexer.PREFIX_TYPE;
                if (OCElementUtil.getElementType(psiElement) == OCTokenTypes.STRING_LITERAL) {
                    o = OCHighlightingLexer.createStringLiteralLexer('\"', OCTokenTypes.STRING_LITERAL);
                    break Label_0136;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            o = OCHighlightingLexer.createRawStringLexer();
        }
        final OCPrefixStringLiteralLexer ocPrefixStringLiteralLexer = new OCPrefixStringLiteralLexer(c, prefix_TYPE, (LexerBase)o);
        final String text = psiElement.getText();
        ocPrefixStringLiteralLexer.start((CharSequence)text, 0, textRange.getEndOffset() - textRange.getStartOffset());
        final StringBuilder sb = new StringBuilder();
        int tokenStart = 0;
        while (ocPrefixStringLiteralLexer.getTokenType() != null) {
            final IElementType tokenType = ocPrefixStringLiteralLexer.getTokenType();
            final String substring = text.substring(ocPrefixStringLiteralLexer.getTokenStart(), ocPrefixStringLiteralLexer.getTokenEnd());
            Label_0287: {
                try {
                    if (StringEscapesTokenTypes.STRING_LITERAL_ESCAPES.contains(tokenType)) {
                        a(psiElement, tokenConsumer, sb, tokenStart);
                        break Label_0287;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                Label_0278: {
                    Label_0267: {
                        try {
                            if (tokenType != OCTokenTypes.STRING_LITERAL) {
                                break Label_0278;
                            }
                            if (sb.length() != 0) {
                                break Label_0267;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                        tokenStart = ocPrefixStringLiteralLexer.getTokenStart();
                    }
                    sb.append(substring);
                    break Label_0287;
                }
                a(psiElement, tokenConsumer, sb, tokenStart);
            }
            ocPrefixStringLiteralLexer.advance();
        }
        a(psiElement, tokenConsumer, sb, tokenStart);
    }
    
    private static void a(@NotNull final PsiElement psiElement, @NotNull final TokenConsumer tokenConsumer, @NotNull final StringBuilder sb, final int n) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "consumeCollectedText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (tokenConsumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "consumeCollectedText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sb", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "consumeCollectedText"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        if (sb.length() != 0) {
            final String string = sb.toString();
            tokenConsumer.consumeToken(psiElement, string, false, n, TextRange.allOf(string), PlainTextSplitter.getInstance());
            sb.setLength(0);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
