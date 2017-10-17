// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi.util;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeElementTypes;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import com.intellij.lang.WhitespacesAndCommentsBinder;

static final class CMakeParserUtil$1 implements WhitespacesAndCommentsBinder {
    public int getEdgePosition(final List<IElementType> list, final boolean b, final WhitespacesAndCommentsBinder.TokenTextGetter tokenTextGetter) {
        for (int i = 0; i < list.size(); ++i) {
            if (!CMakeElementTypes.WHITE_SPACES.contains((IElementType)list.get(i))) {
                return i;
            }
        }
        return list.size();
    }
}