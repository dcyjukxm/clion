// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.highlighter;

import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

public class ModuleMapHighlighterColors
{
    public static final TextAttributesKey MODULE_MAP_KEYWORD;
    public static final TextAttributesKey MODULE_MAP_STRING;
    public static final TextAttributesKey MODULE_MAP_BRACES;
    public static final TextAttributesKey MODULE_MAP_BRACKETS;
    public static final TextAttributesKey MODULE_MAP_COMMA;
    public static final TextAttributesKey MODULE_MAP_DOT;
    public static final TextAttributesKey MODULE_MAP_EXCL;
    public static final TextAttributesKey MODULE_MAP_LINE_COMMENT;
    public static final TextAttributesKey MODULE_MAP_BLOCK_COMMENT;
    public static final TextAttributesKey MODULE_MAP_MODULE_ID;
    public static final TextAttributesKey MODULE_MAP_ATTRIBUTE;
    
    static {
        MODULE_MAP_KEYWORD = TextAttributesKey.createTextAttributesKey("MODULE_MAP_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
        MODULE_MAP_STRING = TextAttributesKey.createTextAttributesKey("MODULE_MAP_STRING", DefaultLanguageHighlighterColors.STRING);
        MODULE_MAP_BRACES = TextAttributesKey.createTextAttributesKey("MODULE_MAP_BRACES", DefaultLanguageHighlighterColors.BRACES);
        MODULE_MAP_BRACKETS = TextAttributesKey.createTextAttributesKey("MODULE_MAP_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
        MODULE_MAP_COMMA = TextAttributesKey.createTextAttributesKey("MODULE_MAP_COMMA", DefaultLanguageHighlighterColors.COMMA);
        MODULE_MAP_DOT = TextAttributesKey.createTextAttributesKey("MODULE_MAP_DOT", DefaultLanguageHighlighterColors.DOT);
        MODULE_MAP_EXCL = TextAttributesKey.createTextAttributesKey("MODULE_MAP_EXCL", DefaultLanguageHighlighterColors.OPERATION_SIGN);
        MODULE_MAP_LINE_COMMENT = TextAttributesKey.createTextAttributesKey("MODULE_MAP_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
        MODULE_MAP_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("MODULE_MAP_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
        MODULE_MAP_MODULE_ID = TextAttributesKey.createTextAttributesKey("MODULE_MAP_MODULE_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
        MODULE_MAP_ATTRIBUTE = TextAttributesKey.createTextAttributesKey("MODULE_MAP_ATTRIBUTE", HighlighterColors.TEXT);
    }
}
