// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

public interface CMakeCacheTokenTypes
{
    public static final IElementType CACHE_ENTRY = new CMakeCacheElementType("CACHE_ENTRY");
    public static final IElementType ENTRY_NAME = new CMakeCacheElementType("ENTRY_NAME");
    public static final IElementType ENTRY_TYPE = new CMakeCacheElementType("ENTRY_TYPE");
    public static final IElementType ENTRY_VALUE = new CMakeCacheElementType("ENTRY_VALUE");
    public static final IElementType COMMENT = new CMakeCacheElementType("comment");
    public static final IElementType EOL = new CMakeCacheElementType("EOL");
    public static final IElementType KEY = new CMakeCacheElementType("KEY");
    public static final IElementType KEY_QUOTE = new CMakeCacheElementType("\"");
    public static final IElementType TYPE = new CMakeCacheElementType("TYPE");
    public static final IElementType TYPE_SEPARATOR = new CMakeCacheElementType(":");
    public static final IElementType VALUE = new CMakeCacheElementType("VALUE");
    public static final IElementType VALUE_QUOTE = new CMakeCacheElementType("'");
    public static final IElementType VALUE_SEPARATOR = new CMakeCacheElementType("=");
    public static final IElementType WHITESPACES = new CMakeCacheElementType("WHITESPACES");
    
    public static class Factory
    {
        public static PsiElement createElement(final ASTNode astNode) {
            final IElementType elementType = astNode.getElementType();
            if (elementType == CMakeCacheTokenTypes.CACHE_ENTRY) {
                return (PsiElement)new CacheEntryImpl(astNode);
            }
            if (elementType == CMakeCacheTokenTypes.ENTRY_NAME) {
                return (PsiElement)new EntryNameImpl(astNode);
            }
            if (elementType == CMakeCacheTokenTypes.ENTRY_TYPE) {
                return (PsiElement)new EntryTypeImpl(astNode);
            }
            if (elementType == CMakeCacheTokenTypes.ENTRY_VALUE) {
                return (PsiElement)new EntryValueImpl(astNode);
            }
            throw new AssertionError((Object)("Unknown element type: " + elementType));
        }
    }
}
