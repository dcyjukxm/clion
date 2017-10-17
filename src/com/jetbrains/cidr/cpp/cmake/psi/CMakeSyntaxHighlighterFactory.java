// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import java.util.HashMap;
import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;
import java.util.Map;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;

public class CMakeSyntaxHighlighterFactory extends SyntaxHighlighterFactory
{
    public static final TextAttributesKey KEYWORD;
    
    @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(@Nullable final Project project, @Nullable final VirtualFile virtualFile) {
        Highlighter highlighter;
        try {
            highlighter = new Highlighter();
            if (highlighter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeSyntaxHighlighterFactory", "getSyntaxHighlighter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (SyntaxHighlighter)highlighter;
    }
    
    static {
        KEYWORD = DefaultLanguageHighlighterColors.KEYWORD;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    private static class Highlighter extends SyntaxHighlighterBase
    {
        private static final Map<IElementType, TextAttributesKey> ATTRIBUTES;
        
        @NotNull
        public Lexer getHighlightingLexer() {
            CMakeLexer cMakeLexer;
            try {
                cMakeLexer = new CMakeLexer();
                if (cMakeLexer == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeSyntaxHighlighterFactory$Highlighter", "getHighlightingLexer"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return (Lexer)cMakeLexer;
        }
        
        @NotNull
        public TextAttributesKey[] getTokenHighlights(final IElementType elementType) {
            TextAttributesKey[] pack;
            try {
                pack = pack((TextAttributesKey)Highlighter.ATTRIBUTES.get(elementType));
                if (pack == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeSyntaxHighlighterFactory$Highlighter", "getTokenHighlights"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return pack;
        }
        
        static {
            (ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>()).put(CMakeTokenTypes.LPAR, DefaultLanguageHighlighterColors.PARENTHESES);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.RPAR, DefaultLanguageHighlighterColors.PARENTHESES);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.COMMENT, DefaultLanguageHighlighterColors.LINE_COMMENT);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.SEMI, DefaultLanguageHighlighterColors.SEMICOLON);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.LITERAL, DefaultLanguageHighlighterColors.STRING);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.ID, DefaultLanguageHighlighterColors.IDENTIFIER);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.IF, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.ELSE, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.ELSEIF, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.ENDIF, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.WHILE, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.ENDWHILE, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.FOREACH, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.ENDFOREACH, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.FUNCTION, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.ENDFUNCTION, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.MACRO, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.ENDMACRO, CMakeSyntaxHighlighterFactory.KEYWORD);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.BRACKET_ARG_START, DefaultLanguageHighlighterColors.BRACKETS);
            Highlighter.ATTRIBUTES.put(CMakeTokenTypes.BRACKET_ARG_END, DefaultLanguageHighlighterColors.BRACKETS);
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
