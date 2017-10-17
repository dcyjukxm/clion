// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import com.intellij.lang.WhitespacesAndCommentsBinder;

class OCParsing$3 implements WhitespacesAndCommentsBinder {
    public int getEdgePosition(final List<IElementType> list, final boolean b, final WhitespacesAndCommentsBinder.TokenTextGetter tokenTextGetter) {
        if (list.size() == 0) {
            return 0;
        }
        for (int i = list.size() - 1; i >= 0; --i) {
            final IElementType elementType = list.get(i);
            if (elementType == OCTokenTypes.BLOCK_COMMENT) {
                if (b || (i > 0 && OCTokenTypes.WHITESPACES.contains((IElementType)list.get(i - 1)) && StringUtil.containsLineBreak(tokenTextGetter.get(i - 1)))) {
                    return i;
                }
            }
            else if (elementType == OCTokenTypes.EOL_COMMENT) {
                break;
            }
        }
        int size = list.size();
        for (int j = list.size() - 1; j >= 0; --j) {
            final IElementType elementType2 = list.get(j);
            if (OCTokenTypes.WHITESPACES.contains(elementType2)) {
                if (StringUtil.getLineBreakCount(tokenTextGetter.get(j)) > 1) {
                    break;
                }
            }
            else {
                if (OCTokenTypes.EOL_COMMENT != elementType2) {
                    break;
                }
                if (b || (j > 0 && OCTokenTypes.WHITESPACES.contains((IElementType)list.get(j - 1)) && StringUtil.containsLineBreak(tokenTextGetter.get(j - 1)))) {
                    size = j;
                }
            }
        }
        return size;
    }
}