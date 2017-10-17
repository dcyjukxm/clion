// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IFileElementType;

public interface CMakeElementTypes extends CMakeTokenTypes
{
    public static final IFileElementType CMAKE_FILE = new CMakeFileElementType();
    public static final TokenSet COMMENTS = TokenSet.create(new IElementType[] { CMakeElementTypes.COMMENT });
    public static final TokenSet WHITE_SPACES = TokenSet.create(new IElementType[] { TokenType.WHITE_SPACE });
    public static final TokenSet LITERALS = TokenSet.create(new IElementType[] { CMakeElementTypes.LITERAL });
    public static final TokenSet CMAKE_BLOCK_START_TOKENS = TokenSet.create(new IElementType[] { CMakeElementTypes.C_MAKE_COMMAND, CMakeElementTypes.C_MAKE_IF_COMMAND, CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND, CMakeElementTypes.C_MAKE_ELSE_COMMAND, CMakeElementTypes.C_MAKE_WHILE_COMMAND, CMakeElementTypes.C_MAKE_FOREACH_COMMAND, CMakeElementTypes.C_MAKE_FUNCTION_COMMAND, CMakeElementTypes.C_MAKE_MACRO_COMMAND, CMakeElementTypes.COMMENT });
    public static final TokenSet CMAKE_BLOCK_END_TOKENS = TokenSet.create(new IElementType[] { CMakeElementTypes.C_MAKE_COMMAND, CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND, CMakeElementTypes.C_MAKE_ELSE_COMMAND, CMakeElementTypes.C_MAKE_END_IF_COMMAND, CMakeElementTypes.C_MAKE_END_WHILE_COMMAND, CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND, CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND, CMakeElementTypes.C_MAKE_END_MACRO_COMMAND, CMakeElementTypes.COMMENT });
    public static final TokenSet CMAKE_FUNCTION_AND_MACRO_NAME_HOLDERS = TokenSet.create(new IElementType[] { CMakeElementTypes.C_MAKE_FUNCTION_COMMAND, CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND, CMakeElementTypes.C_MAKE_MACRO_COMMAND, CMakeElementTypes.C_MAKE_END_MACRO_COMMAND });
    public static final TokenSet KEYWORDS = TokenSet.create(new IElementType[] { CMakeElementTypes.IF, CMakeElementTypes.ELSEIF, CMakeElementTypes.ELSE, CMakeElementTypes.ENDIF, CMakeElementTypes.FOREACH, CMakeElementTypes.ENDFOREACH, CMakeElementTypes.FUNCTION, CMakeElementTypes.ENDFUNCTION, CMakeElementTypes.WHILE, CMakeElementTypes.ENDWHILE, CMakeElementTypes.MACRO, CMakeElementTypes.ENDMACRO });
    public static final TokenSet COMMAND_ELEMENTS = TokenSet.orSet(new TokenSet[] { CMakeElementTypes.KEYWORDS, TokenSet.create(new IElementType[] { CMakeTokenTypes.ID }) });
    public static final TokenSet IDENTIFIERS = TokenSet.orSet(new TokenSet[] { TokenSet.create(new IElementType[] { CMakeElementTypes.C_MAKE_COMMAND_NAME, CMakeElementTypes.C_MAKE_ARGUMENT, CMakeElementTypes.C_MAKE_ROUTINE, CMakeElementTypes.LITERAL }), CMakeElementTypes.KEYWORDS });
}
