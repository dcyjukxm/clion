// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerBase;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.StringEscapesTokenTypes;
import org.jetbrains.annotations.Contract;
import com.intellij.lexer.StringLiteralLexer;
import com.intellij.psi.tree.IElementType;

public class OCHighlightingLexer extends OCLexerWithDirectives
{
    public static final IElementType PREFIX_TYPE;
    public static final char STRING_START = '\"';
    public static final char CHAR_START = '\'';
    
    @Contract("_, _ -> !null")
    public static StringLiteralLexer createStringLiteralLexer(final char c, final IElementType elementType) {
        return new StringLiteralLexer(c, elementType, false, "\\?\\a\\b\\v", true, true);
    }
    
    @Contract(" -> !null")
    public static OCRawStringLexerBase createRawStringLexer() {
        return new OCRawStringLexerBase(StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN, OCTokenTypes.STRING_LITERAL);
    }
    
    public OCHighlightingLexer(@NotNull final OCLanguageKind ocLanguageKind, final boolean b, final boolean b2, final boolean b3) {
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/lexer/OCHighlightingLexer", "<init>"));
        }
        super(ocLanguageKind, true, b, b2, b3);
        this.registerSelfStoppingLayer((Lexer)new OCPrefixStringLiteralLexer('\"', OCHighlightingLexer.PREFIX_TYPE, (LexerBase)createStringLiteralLexer('\"', OCTokenTypes.STRING_LITERAL)), new IElementType[] { OCTokenTypes.STRING_LITERAL }, IElementType.EMPTY_ARRAY);
        this.registerSelfStoppingLayer((Lexer)new OCPrefixStringLiteralLexer('\'', OCHighlightingLexer.PREFIX_TYPE, (LexerBase)createStringLiteralLexer('\'', OCTokenTypes.CHARACTER_LITERAL)), new IElementType[] { OCTokenTypes.CHARACTER_LITERAL }, IElementType.EMPTY_ARRAY);
        this.registerSelfStoppingLayer((Lexer)new OCPrefixStringLiteralLexer('\"', OCHighlightingLexer.PREFIX_TYPE, createRawStringLexer()), new IElementType[] { OCTokenTypes.RAW_STRING_LITERAL, OCTokenTypes.WRONG_RAW_STRING_LITERAL }, IElementType.EMPTY_ARRAY);
    }
    
    static {
        PREFIX_TYPE = OCTokenTypes.AT;
    }
}
