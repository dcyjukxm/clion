// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm;

import com.intellij.psi.TokenType;
import com.jetbrains.cidr.lang.asm.psi.AsmTypes;
import java.util.HashMap;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;
import java.util.Map;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;

public class AsmSyntaxHighlighter extends SyntaxHighlighterBase
{
    public static final TextAttributesKey LINE_COMMENT;
    public static final TextAttributesKey BLOCK_COMMENT;
    public static final TextAttributesKey BAD_CHARACTER;
    public static final TextAttributesKey COMMA;
    public static final TextAttributesKey SIGN;
    public static final TextAttributesKey PARENTHESES;
    public static final TextAttributesKey BRACES;
    public static final TextAttributesKey STAR;
    public static final TextAttributesKey PERCENT;
    public static final TextAttributesKey AT;
    public static final TextAttributesKey LABEL;
    public static final TextAttributesKey DIRECTIVE_NAME;
    public static final TextAttributesKey PREFIX;
    public static final TextAttributesKey MNEMONIC;
    public static final TextAttributesKey NUMBER;
    private static final Map<IElementType, TextAttributesKey[]> ourAttributes;
    
    @NotNull
    public Lexer getHighlightingLexer() {
        AsmLexer asmLexer;
        try {
            asmLexer = new AsmLexer();
            if (asmLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmSyntaxHighlighter", "getHighlightingLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)asmLexer;
    }
    
    @NotNull
    public TextAttributesKey[] getTokenHighlights(final IElementType elementType) {
        TextAttributesKey[] array;
        try {
            array = AsmSyntaxHighlighter.ourAttributes.getOrDefault(elementType, AsmSyntaxHighlighter.EMPTY);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmSyntaxHighlighter", "getTokenHighlights"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return array;
    }
    
    static {
        LINE_COMMENT = TextAttributesKey.createTextAttributesKey("ASM_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
        BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("ASM_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
        BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("ASM_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
        COMMA = TextAttributesKey.createTextAttributesKey("ASM_COMMA", DefaultLanguageHighlighterColors.COMMA);
        SIGN = TextAttributesKey.createTextAttributesKey("ASM_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN);
        PARENTHESES = TextAttributesKey.createTextAttributesKey("ASM_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
        BRACES = TextAttributesKey.createTextAttributesKey("ASM_BRACES", DefaultLanguageHighlighterColors.BRACES);
        STAR = TextAttributesKey.createTextAttributesKey("ASM_STAR", DefaultLanguageHighlighterColors.KEYWORD);
        PERCENT = TextAttributesKey.createTextAttributesKey("ASM_PERCENT", DefaultLanguageHighlighterColors.KEYWORD);
        AT = TextAttributesKey.createTextAttributesKey("ASM_AT", DefaultLanguageHighlighterColors.METADATA);
        LABEL = TextAttributesKey.createTextAttributesKey("ASM_LABEL", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
        DIRECTIVE_NAME = TextAttributesKey.createTextAttributesKey("ASM_DIRECTIVE_NAME", DefaultLanguageHighlighterColors.KEYWORD);
        PREFIX = TextAttributesKey.createTextAttributesKey("ASM_PREFIX", DefaultLanguageHighlighterColors.METADATA);
        MNEMONIC = TextAttributesKey.createTextAttributesKey("ASM_MNEMONIC", DefaultLanguageHighlighterColors.KEYWORD);
        NUMBER = TextAttributesKey.createTextAttributesKey("ASM_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
        (ourAttributes = new HashMap<IElementType, TextAttributesKey[]>()).put(AsmTypes.LINE_COMMENT, pack(AsmSyntaxHighlighter.LINE_COMMENT));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.BLOCK_COMMENT, pack(AsmSyntaxHighlighter.BLOCK_COMMENT));
        AsmSyntaxHighlighter.ourAttributes.put(TokenType.BAD_CHARACTER, pack(AsmSyntaxHighlighter.BAD_CHARACTER));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.COMMA, pack(AsmSyntaxHighlighter.COMMA));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.L_PAREN, pack(AsmSyntaxHighlighter.PARENTHESES));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.R_PAREN, pack(AsmSyntaxHighlighter.PARENTHESES));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.L_BRACE, pack(AsmSyntaxHighlighter.BRACES));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.R_BRACE, pack(AsmSyntaxHighlighter.BRACES));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.MINUS, pack(AsmSyntaxHighlighter.SIGN));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.STAR, pack(AsmSyntaxHighlighter.STAR));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.PERCENT, pack(AsmSyntaxHighlighter.PERCENT));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.AT, pack(AsmSyntaxHighlighter.AT));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.LABEL, pack(AsmSyntaxHighlighter.LABEL));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.DIRECTIVE_NAME, pack(AsmSyntaxHighlighter.DIRECTIVE_NAME));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.INTEGER, pack(AsmSyntaxHighlighter.NUMBER));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.MNEMONIC, pack(AsmSyntaxHighlighter.MNEMONIC));
        AsmSyntaxHighlighter.ourAttributes.put(AsmTypes.PREFIX, pack(AsmSyntaxHighlighter.PREFIX));
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
