// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.colors;

import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

public class OCHighlightingKeys
{
    public static final TextAttributesKey OC_KEYWORD;
    public static final TextAttributesKey OC_DIRECTIVE;
    public static final TextAttributesKey OC_STRING;
    public static final TextAttributesKey OC_HEADER_PATH;
    public static final TextAttributesKey OC_NUMBER;
    public static final TextAttributesKey OC_LINE_COMMENT;
    public static final TextAttributesKey OC_BLOCK_COMMENT;
    public static final TextAttributesKey OC_OPERATION_SIGN;
    public static final TextAttributesKey OC_PARENTHS;
    public static final TextAttributesKey OC_BRACKETS;
    public static final TextAttributesKey OC_BRACES;
    public static final TextAttributesKey OC_COMMA;
    public static final TextAttributesKey OC_DOT;
    public static final TextAttributesKey OC_SEMICOLON;
    public static final TextAttributesKey OC_BAD_CHARACTER;
    public static final TextAttributesKey OC_FORMAT_STRING_TOKEN;
    public static final TextAttributesKey OC_VALID_STRING_ESCAPE;
    public static final TextAttributesKey OC_INVALID_STRING_ESCAPE;
    public static final TextAttributesKey SELFSUPERTHIS;
    public static final TextAttributesKey STRUCT_LIKE;
    public static final TextAttributesKey NAMESPACE_LIKE;
    public static final TextAttributesKey CLASS_REFERENCE;
    public static final TextAttributesKey PROTOCOL_REFERENCE;
    public static final TextAttributesKey GLOBAL_VARIABLE;
    public static final TextAttributesKey EXTERN_VARIABLE;
    public static final TextAttributesKey LOCAL_VARIABLE;
    public static final TextAttributesKey ENUM_CONST;
    public static final TextAttributesKey STRUCT_FIELD;
    public static final TextAttributesKey INSTANCE_VARIABLE;
    public static final TextAttributesKey FUNCTION;
    public static final TextAttributesKey OVERLOADED_OPERATOR;
    public static final TextAttributesKey PARAMETER;
    public static final TextAttributesKey MESSAGE_ARGUMENT;
    public static final TextAttributesKey PROPERTY;
    public static final TextAttributesKey PROPERTY_ATTRIBUTE;
    public static final TextAttributesKey LABEL;
    public static final TextAttributesKey TYPEDEF;
    public static final TextAttributesKey TEMPLATE_TYPE;
    public static final TextAttributesKey TEMPLATE_VALUE;
    public static final TextAttributesKey MACRONAME;
    public static final TextAttributesKey MACRO_PARAMETER;
    public static final TextAttributesKey CONDITIONALLY_NOT_COMPILED;
    public static final TextAttributesKey GENERIC_PARAMETER;
    
    static {
        OC_KEYWORD = TextAttributesKey.createTextAttributesKey("OC.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
        OC_DIRECTIVE = TextAttributesKey.createTextAttributesKey("OC.DIRECTIVE", DefaultLanguageHighlighterColors.METADATA);
        OC_STRING = TextAttributesKey.createTextAttributesKey("OC.STRING", DefaultLanguageHighlighterColors.STRING);
        OC_HEADER_PATH = TextAttributesKey.createTextAttributesKey("OC.HEADER_PATH", DefaultLanguageHighlighterColors.STRING);
        OC_NUMBER = TextAttributesKey.createTextAttributesKey("OC.NUMBER", DefaultLanguageHighlighterColors.NUMBER);
        OC_LINE_COMMENT = TextAttributesKey.createTextAttributesKey("OC.LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
        OC_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("OC.BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
        OC_OPERATION_SIGN = TextAttributesKey.createTextAttributesKey("OC.OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN);
        OC_PARENTHS = TextAttributesKey.createTextAttributesKey("OC.PARENTHS", DefaultLanguageHighlighterColors.PARENTHESES);
        OC_BRACKETS = TextAttributesKey.createTextAttributesKey("OC.BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
        OC_BRACES = TextAttributesKey.createTextAttributesKey("OC.BRACES", DefaultLanguageHighlighterColors.BRACES);
        OC_COMMA = TextAttributesKey.createTextAttributesKey("OC.COMMA", DefaultLanguageHighlighterColors.COMMA);
        OC_DOT = TextAttributesKey.createTextAttributesKey("OC.DOT", DefaultLanguageHighlighterColors.DOT);
        OC_SEMICOLON = TextAttributesKey.createTextAttributesKey("OC.SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
        OC_BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("OC.BADCHARACTER", HighlighterColors.BAD_CHARACTER);
        OC_FORMAT_STRING_TOKEN = TextAttributesKey.createTextAttributesKey("OC.FORMAT_TOKEN", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE);
        OC_VALID_STRING_ESCAPE = TextAttributesKey.createTextAttributesKey("OC.VALID_STRING_ESCAPE", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE);
        OC_INVALID_STRING_ESCAPE = TextAttributesKey.createTextAttributesKey("OC.INVALID_STRING_ESCAPE", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE);
        SELFSUPERTHIS = TextAttributesKey.createTextAttributesKey("OC.SELFSUPERTHIS", OCHighlightingKeys.OC_KEYWORD);
        STRUCT_LIKE = TextAttributesKey.createTextAttributesKey("OC.STRUCT_LIKE");
        NAMESPACE_LIKE = TextAttributesKey.createTextAttributesKey("OC.NAMESPACE_LIKE", OCHighlightingKeys.STRUCT_LIKE);
        CLASS_REFERENCE = TextAttributesKey.createTextAttributesKey("OC.CLASS_REFERENCE");
        PROTOCOL_REFERENCE = TextAttributesKey.createTextAttributesKey("OC.PROTOCOL_REFERENCE");
        GLOBAL_VARIABLE = TextAttributesKey.createTextAttributesKey("OC.GLOBAL_VARIABLE", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
        EXTERN_VARIABLE = TextAttributesKey.createTextAttributesKey("OC.EXTERN_VARIABLE", OCHighlightingKeys.GLOBAL_VARIABLE);
        LOCAL_VARIABLE = TextAttributesKey.createTextAttributesKey("OC.LOCAL_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
        ENUM_CONST = TextAttributesKey.createTextAttributesKey("OC.ENUM_CONST", DefaultLanguageHighlighterColors.CONSTANT);
        STRUCT_FIELD = TextAttributesKey.createTextAttributesKey("OC.STRUCT_FIELD");
        INSTANCE_VARIABLE = TextAttributesKey.createTextAttributesKey("OC.IVAR", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
        FUNCTION = TextAttributesKey.createTextAttributesKey("OC.FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_CALL);
        OVERLOADED_OPERATOR = TextAttributesKey.createTextAttributesKey("OC.OVERLOADED_OPERATOR");
        PARAMETER = TextAttributesKey.createTextAttributesKey("OC.PARAMETER", DefaultLanguageHighlighterColors.PARAMETER);
        MESSAGE_ARGUMENT = TextAttributesKey.createTextAttributesKey("OC.MESSAGE_ARGUMENT");
        PROPERTY = TextAttributesKey.createTextAttributesKey("OC.PROPERTY", OCHighlightingKeys.MESSAGE_ARGUMENT);
        PROPERTY_ATTRIBUTE = TextAttributesKey.createTextAttributesKey("OC.PROPERTY_ATTRIBUTE", HighlighterColors.TEXT);
        LABEL = TextAttributesKey.createTextAttributesKey("OC.LABEL", HighlighterColors.TEXT);
        TYPEDEF = TextAttributesKey.createTextAttributesKey("OC.TYPEDEF");
        TEMPLATE_TYPE = TextAttributesKey.createTextAttributesKey("OC.TEMPLATE_TYPE", OCHighlightingKeys.TYPEDEF);
        TEMPLATE_VALUE = TextAttributesKey.createTextAttributesKey("OC.TEMPLATE_VALUE", OCHighlightingKeys.ENUM_CONST);
        MACRONAME = TextAttributesKey.createTextAttributesKey("OC.MACRONAME");
        MACRO_PARAMETER = TextAttributesKey.createTextAttributesKey("OC.MACRO_PARAMETER");
        CONDITIONALLY_NOT_COMPILED = TextAttributesKey.createTextAttributesKey("OC.CONDITIONALLY_NOT_COMPILED");
        GENERIC_PARAMETER = TextAttributesKey.createTextAttributesKey("OC.GENERIC_PARAMETER", OCHighlightingKeys.TYPEDEF);
    }
}
