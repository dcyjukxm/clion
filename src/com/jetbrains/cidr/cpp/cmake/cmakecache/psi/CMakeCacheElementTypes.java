// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IFileElementType;

public interface CMakeCacheElementTypes extends CMakeCacheTokenTypes
{
    public static final IFileElementType CMAKE_CACHE_FILE = new CMakeCacheFileElementType();
    public static final TokenSet WHITESPACES_SET = TokenSet.create(new IElementType[] { CMakeCacheTokenTypes.WHITESPACES, CMakeCacheTokenTypes.EOL });
    public static final TokenSet COMMENTS = TokenSet.create(new IElementType[] { CMakeCacheTokenTypes.COMMENT });
    public static final TokenSet LITERALS = TokenSet.create(new IElementType[] { CMakeCacheTokenTypes.KEY, CMakeCacheTokenTypes.VALUE, CMakeCacheTokenTypes.TYPE });
}
