// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;

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
