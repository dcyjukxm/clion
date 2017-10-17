// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.colors;

import com.intellij.psi.TokenType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.lexer.OCStringsLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.tree.IElementType;
import java.util.Map;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;

public class OCStringsFileHighlighter extends SyntaxHighlighterBase
{
    private static final Map<IElementType, TextAttributesKey> keys1;
    private static final Map<IElementType, TextAttributesKey> keys2;
    
    @NotNull
    public Lexer getHighlightingLexer() {
        OCStringsLexer ocStringsLexer;
        try {
            ocStringsLexer = new OCStringsLexer(true);
            if (ocStringsLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCStringsFileHighlighter", "getHighlightingLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)ocStringsLexer;
    }
    
    @NotNull
    public TextAttributesKey[] getTokenHighlights(final IElementType elementType) {
        TextAttributesKey[] pack;
        try {
            pack = pack((TextAttributesKey)OCStringsFileHighlighter.keys1.get(elementType), (TextAttributesKey)OCStringsFileHighlighter.keys2.get(elementType));
            if (pack == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCStringsFileHighlighter", "getTokenHighlights"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return pack;
    }
    
    static {
        keys1 = new HashMap<IElementType, TextAttributesKey>();
        keys2 = new HashMap<IElementType, TextAttributesKey>();
        OCStringsFileHighlighter.keys1.put(OCTokenTypes.STRING_LITERAL, OCHighlightingKeys.OC_STRING);
        OCStringsFileHighlighter.keys1.put(OCTokenTypes.BLOCK_COMMENT, OCHighlightingKeys.OC_BLOCK_COMMENT);
        OCStringsFileHighlighter.keys1.put(OCTokenTypes.EOL_COMMENT, OCHighlightingKeys.OC_LINE_COMMENT);
        OCStringsFileHighlighter.keys1.put(TokenType.BAD_CHARACTER, OCHighlightingKeys.OC_BAD_CHARACTER);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
