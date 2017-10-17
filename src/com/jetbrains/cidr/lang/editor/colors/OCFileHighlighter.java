// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.colors;

import com.intellij.psi.TokenType;
import com.intellij.psi.StringEscapesTokenTypes;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.HashMap;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.lexer.OCHighlightingLexer;
import com.intellij.lexer.Lexer;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.tree.IElementType;
import java.util.Map;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;

public class OCFileHighlighter extends SyntaxHighlighterBase
{
    private static final Map<IElementType, TextAttributesKey> keys1;
    private static final Map<IElementType, TextAttributesKey> keys2;
    private final OCLanguageKind myKind;
    private boolean mySupportNullability;
    private boolean myAllowGccAutoType;
    private boolean myAllowAvailabilityExpression;
    
    public OCFileHighlighter(final OCLanguageKind myKind, final boolean mySupportNullability, final boolean myAllowGccAutoType, final boolean myAllowAvailabilityExpression) {
        this.myKind = myKind;
        this.mySupportNullability = mySupportNullability;
        this.myAllowGccAutoType = myAllowGccAutoType;
        this.myAllowAvailabilityExpression = myAllowAvailabilityExpression;
    }
    
    @NotNull
    public TextAttributesKey[] getTokenHighlights(final IElementType elementType) {
        TextAttributesKey[] pack;
        try {
            pack = pack((TextAttributesKey)OCFileHighlighter.keys1.get(elementType), (TextAttributesKey)OCFileHighlighter.keys2.get(elementType));
            if (pack == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCFileHighlighter", "getTokenHighlights"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return pack;
    }
    
    @NotNull
    public Lexer getHighlightingLexer() {
        OCHighlightingLexer ocHighlightingLexer;
        try {
            ocHighlightingLexer = new OCHighlightingLexer(this.myKind, this.mySupportNullability, this.myAllowGccAutoType, this.myAllowAvailabilityExpression);
            if (ocHighlightingLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCFileHighlighter", "getHighlightingLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)ocHighlightingLexer;
    }
    
    static {
        keys1 = new HashMap<IElementType, TextAttributesKey>();
        keys2 = new HashMap<IElementType, TextAttributesKey>();
        SyntaxHighlighterBase.fillMap((Map)OCFileHighlighter.keys1, OCTokenTypes.KEYWORDS, OCHighlightingKeys.OC_KEYWORD);
        fillMap((Map)OCFileHighlighter.keys1, OCTokenTypes.DIRECTIVES, OCHighlightingKeys.OC_DIRECTIVE);
        OCFileHighlighter.keys1.put(OCTokenTypes.HEADER_PATH_LITERAL, OCHighlightingKeys.OC_HEADER_PATH);
        fillMap((Map)OCFileHighlighter.keys1, OCHighlightingKeys.OC_OPERATION_SIGN, IElementType.enumerate(elementType -> elementType instanceof OCPunctuatorElementType));
        OCFileHighlighter.keys1.put(StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN, OCHighlightingKeys.OC_VALID_STRING_ESCAPE);
        OCFileHighlighter.keys1.put(StringEscapesTokenTypes.INVALID_CHARACTER_ESCAPE_TOKEN, OCHighlightingKeys.OC_INVALID_STRING_ESCAPE);
        OCFileHighlighter.keys1.put(StringEscapesTokenTypes.INVALID_UNICODE_ESCAPE_TOKEN, OCHighlightingKeys.OC_INVALID_STRING_ESCAPE);
        OCFileHighlighter.keys1.put(OCTokenTypes.INTEGER_LITERAL, OCHighlightingKeys.OC_NUMBER);
        OCFileHighlighter.keys1.put(OCTokenTypes.FLOAT_LITERAL, OCHighlightingKeys.OC_NUMBER);
        OCFileHighlighter.keys1.put(OCTokenTypes.VERSION_LITERAL, OCHighlightingKeys.OC_NUMBER);
        OCFileHighlighter.keys1.put(OCTokenTypes.STRING_LITERAL, OCHighlightingKeys.OC_STRING);
        OCFileHighlighter.keys1.put(OCTokenTypes.AT, OCHighlightingKeys.OC_KEYWORD);
        OCFileHighlighter.keys1.put(OCTokenTypes.CHARACTER_LITERAL, OCHighlightingKeys.OC_STRING);
        OCFileHighlighter.keys1.put(OCTokenTypes.LPAR, OCHighlightingKeys.OC_PARENTHS);
        OCFileHighlighter.keys1.put(OCTokenTypes.RPAR, OCHighlightingKeys.OC_PARENTHS);
        OCFileHighlighter.keys1.put(OCTokenTypes.LBRACE, OCHighlightingKeys.OC_BRACES);
        OCFileHighlighter.keys1.put(OCTokenTypes.RBRACE, OCHighlightingKeys.OC_BRACES);
        OCFileHighlighter.keys1.put(OCTokenTypes.LBRACKET, OCHighlightingKeys.OC_BRACKETS);
        OCFileHighlighter.keys1.put(OCTokenTypes.RBRACKET, OCHighlightingKeys.OC_BRACKETS);
        OCFileHighlighter.keys1.put(OCTokenTypes.COMMA, OCHighlightingKeys.OC_COMMA);
        OCFileHighlighter.keys1.put(OCTokenTypes.DOT, OCHighlightingKeys.OC_DOT);
        OCFileHighlighter.keys1.put(OCTokenTypes.SEMICOLON, OCHighlightingKeys.OC_SEMICOLON);
        OCFileHighlighter.keys1.put(OCTokenTypes.BLOCK_COMMENT, OCHighlightingKeys.OC_BLOCK_COMMENT);
        OCFileHighlighter.keys1.put(OCTokenTypes.EOL_COMMENT, OCHighlightingKeys.OC_LINE_COMMENT);
        OCFileHighlighter.keys1.put(TokenType.BAD_CHARACTER, OCHighlightingKeys.OC_BAD_CHARACTER);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
