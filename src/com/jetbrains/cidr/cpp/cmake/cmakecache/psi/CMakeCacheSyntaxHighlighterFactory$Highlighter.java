// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.tree.IElementType;
import java.util.Map;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;

private static class Highlighter extends SyntaxHighlighterBase
{
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES;
    
    @NotNull
    public Lexer getHighlightingLexer() {
        CMakeCacheLexer cMakeCacheLexer;
        try {
            cMakeCacheLexer = new CMakeCacheLexer();
            if (cMakeCacheLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheSyntaxHighlighterFactory$Highlighter", "getHighlightingLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)cMakeCacheLexer;
    }
    
    @NotNull
    public TextAttributesKey[] getTokenHighlights(final IElementType elementType) {
        TextAttributesKey[] pack;
        try {
            pack = pack((TextAttributesKey)Highlighter.ATTRIBUTES.get(elementType));
            if (pack == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheSyntaxHighlighterFactory$Highlighter", "getTokenHighlights"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return pack;
    }
    
    static {
        (ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>()).put(CMakeCacheTokenTypes.COMMENT, DefaultLanguageHighlighterColors.LINE_COMMENT);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
