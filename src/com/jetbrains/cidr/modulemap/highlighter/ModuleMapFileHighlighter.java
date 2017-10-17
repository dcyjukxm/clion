// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.highlighter;

import com.jetbrains.cidr.modulemap.ModuleMapParserTypes;
import com.jetbrains.cidr.modulemap.psi.ModuleMapTokenTypes;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.lexer.ModuleMapLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.tree.IElementType;
import java.util.Map;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;

public class ModuleMapFileHighlighter extends SyntaxHighlighterBase
{
    private static final Map<IElementType, TextAttributesKey> MAIN_ATTRIBUTES;
    private static final Map<IElementType, TextAttributesKey> AUX_ATTRIBUTES;
    
    @NotNull
    public Lexer getHighlightingLexer() {
        ModuleMapLexer moduleMapLexer;
        try {
            moduleMapLexer = new ModuleMapLexer();
            if (moduleMapLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/highlighter/ModuleMapFileHighlighter", "getHighlightingLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)moduleMapLexer;
    }
    
    @NotNull
    public TextAttributesKey[] getTokenHighlights(final IElementType elementType) {
        TextAttributesKey[] pack;
        try {
            pack = pack((TextAttributesKey)ModuleMapFileHighlighter.MAIN_ATTRIBUTES.get(elementType), (TextAttributesKey)ModuleMapFileHighlighter.AUX_ATTRIBUTES.get(elementType));
            if (pack == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/highlighter/ModuleMapFileHighlighter", "getTokenHighlights"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return pack;
    }
    
    static {
        MAIN_ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();
        AUX_ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();
        final IElementType[] types = ModuleMapTokenTypes.KEYWORDS.getTypes();
        for (int length = types.length, i = 0; i < length; ++i) {
            ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(types[i], ModuleMapHighlighterColors.MODULE_MAP_KEYWORD);
        }
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.L_CURLY, ModuleMapHighlighterColors.MODULE_MAP_BRACES);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.R_CURLY, ModuleMapHighlighterColors.MODULE_MAP_BRACES);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.L_BRACKET, ModuleMapHighlighterColors.MODULE_MAP_BRACKETS);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.R_BRACKET, ModuleMapHighlighterColors.MODULE_MAP_BRACKETS);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.COMMA, ModuleMapHighlighterColors.MODULE_MAP_COMMA);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.DOT, ModuleMapHighlighterColors.MODULE_MAP_DOT);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.EXCL, ModuleMapHighlighterColors.MODULE_MAP_EXCL);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.EOL_COMMENT, ModuleMapHighlighterColors.MODULE_MAP_LINE_COMMENT);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.BLOCK_COMMENT, ModuleMapHighlighterColors.MODULE_MAP_BLOCK_COMMENT);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.STRING, ModuleMapHighlighterColors.MODULE_MAP_STRING);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.ATTRIBUTE, ModuleMapHighlighterColors.MODULE_MAP_ATTRIBUTE);
        ModuleMapFileHighlighter.MAIN_ATTRIBUTES.put(ModuleMapParserTypes.MODULE_ID, ModuleMapHighlighterColors.MODULE_MAP_MODULE_ID);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
