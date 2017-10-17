// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import com.intellij.psi.TokenType;
import com.jetbrains.cidr.modulemap.ModuleMapParserTypes;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IFileElementType;

public interface ModuleMapTokenTypes
{
    public static final IFileElementType MODULE_MAP_FILE = new ModuleMapFileElementType();
    public static final TokenSet COMMENTS = TokenSet.create(new IElementType[] { ModuleMapParserTypes.EOL_COMMENT, ModuleMapParserTypes.BLOCK_COMMENT });
    public static final TokenSet WHITESPACES = TokenSet.create(new IElementType[] { TokenType.WHITE_SPACE });
    public static final TokenSet COMMENTS_WHITESPACES_ERRORS = TokenSet.orSet(new TokenSet[] { ModuleMapTokenTypes.COMMENTS, TokenSet.create(new IElementType[] { TokenType.WHITE_SPACE, TokenType.ERROR_ELEMENT, TokenType.BAD_CHARACTER }) });
    public static final TokenSet STRINGS = TokenSet.create(new IElementType[] { ModuleMapParserTypes.STRING });
    public static final TokenSet KEYWORDS = TokenSet.create(new IElementType[] { ModuleMapParserTypes.CONFIG_MACROS, ModuleMapParserTypes.EXPORT, ModuleMapParserTypes.PRIVATE, ModuleMapParserTypes.CONFLICT, ModuleMapParserTypes.FRAMEWORK, ModuleMapParserTypes.REQUIRES, ModuleMapParserTypes.EXCLUDE, ModuleMapParserTypes.HEADER, ModuleMapParserTypes.TEXTUAL, ModuleMapParserTypes.EXPLICIT, ModuleMapParserTypes.LINK, ModuleMapParserTypes.UMBRELLA, ModuleMapParserTypes.EXTERN, ModuleMapParserTypes.MODULE, ModuleMapParserTypes.USE });
    public static final TokenSet IDENTIFIERS = TokenSet.create(new IElementType[] { ModuleMapParserTypes.IDENTIFIER });
    public static final TokenSet LITERALS = TokenSet.create(new IElementType[] { ModuleMapParserTypes.STRING });
}
