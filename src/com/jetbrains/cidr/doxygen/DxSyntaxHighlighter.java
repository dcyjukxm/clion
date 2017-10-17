// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.jetbrains.cidr.doxygen.psi.DxTypes;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;

public class DxSyntaxHighlighter extends SyntaxHighlighterBase
{
    public static final TextAttributesKey COMMENT_TEXT;
    public static final TextAttributesKey TAG_NAME;
    public static final TextAttributesKey TAG_PARAM;
    private static final TextAttributesKey[] TAG_NAME_KEYS;
    private static final TextAttributesKey[] TAG_PARAM_KEYS;
    private static final TextAttributesKey[] COMMENT_TEXT_KEYS;
    
    @NotNull
    public Lexer getHighlightingLexer() {
        DoxygenLexerAdapter doxygenLexerAdapter;
        try {
            doxygenLexerAdapter = new DoxygenLexerAdapter();
            if (doxygenLexerAdapter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxSyntaxHighlighter", "getHighlightingLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)doxygenLexerAdapter;
    }
    
    @NotNull
    public TextAttributesKey[] getTokenHighlights(final IElementType elementType) {
        TextAttributesKey[] comment_TEXT_KEYS = null;
        Label_0118: {
            TextAttributesKey[] array2 = null;
            Label_0083: {
                Label_0059: {
                    TextAttributesKey[] array = null;
                    Label_0024: {
                        try {
                            if (!elementType.equals(DxTypes.TAG_NAME)) {
                                break Label_0059;
                            }
                            array = DxSyntaxHighlighter.TAG_NAME_KEYS;
                            if (array == null) {
                                break Label_0024;
                            }
                            return array;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            array = DxSyntaxHighlighter.TAG_NAME_KEYS;
                            if (array == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxSyntaxHighlighter", "getTokenHighlights"));
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    return array;
                    try {
                        if (!elementType.equals(DxTypes.TAG_PARAM)) {
                            break Label_0118;
                        }
                        array2 = DxSyntaxHighlighter.TAG_PARAM_KEYS;
                        if (array2 == null) {
                            break Label_0083;
                        }
                        return array2;
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    array2 = DxSyntaxHighlighter.TAG_PARAM_KEYS;
                    if (array2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxSyntaxHighlighter", "getTokenHighlights"));
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            return array2;
            try {
                comment_TEXT_KEYS = DxSyntaxHighlighter.COMMENT_TEXT_KEYS;
                if (comment_TEXT_KEYS == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxSyntaxHighlighter", "getTokenHighlights"));
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        return comment_TEXT_KEYS;
    }
    
    static {
        COMMENT_TEXT = TextAttributesKey.createTextAttributesKey("Doxygen Comment", DefaultLanguageHighlighterColors.DOC_COMMENT);
        TAG_NAME = TextAttributesKey.createTextAttributesKey("Doxygen Tag", DefaultLanguageHighlighterColors.DOC_COMMENT_TAG);
        TAG_PARAM = TextAttributesKey.createTextAttributesKey("Doxygen Parameter", DefaultLanguageHighlighterColors.DOC_COMMENT_TAG_VALUE);
        TAG_NAME_KEYS = pack(DxSyntaxHighlighter.TAG_NAME, DxSyntaxHighlighter.COMMENT_TEXT);
        TAG_PARAM_KEYS = pack(DxSyntaxHighlighter.TAG_PARAM, DxSyntaxHighlighter.COMMENT_TEXT);
        COMMENT_TEXT_KEYS = pack(DxSyntaxHighlighter.COMMENT_TEXT);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
