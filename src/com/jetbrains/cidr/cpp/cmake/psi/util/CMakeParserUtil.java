// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi.util;

import com.intellij.lang.LighterASTNode;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeElementTypes;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import com.intellij.lang.WhitespacesAndCommentsBinder;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;

public class CMakeParserUtil extends GeneratedParserUtilBase
{
    public static boolean bindComments(final PsiBuilder psiBuilder, final int n) {
        final LighterASTNode latestDoneMarker = psiBuilder.getLatestDoneMarker();
        if (latestDoneMarker instanceof PsiBuilder.Marker) {
            ((PsiBuilder.Marker)latestDoneMarker).setCustomEdgeTokenBinders((WhitespacesAndCommentsBinder)new WhitespacesAndCommentsBinder() {
                public int getEdgePosition(final List<IElementType> list, final boolean b, final WhitespacesAndCommentsBinder.TokenTextGetter tokenTextGetter) {
                    for (int i = 0; i < list.size(); ++i) {
                        if (!CMakeElementTypes.WHITE_SPACES.contains((IElementType)list.get(i))) {
                            return i;
                        }
                    }
                    return list.size();
                }
            }, (WhitespacesAndCommentsBinder)new WhitespacesAndCommentsBinder() {
                public int getEdgePosition(final List<IElementType> list, final boolean b, final WhitespacesAndCommentsBinder.TokenTextGetter tokenTextGetter) {
                    for (int i = list.size() - 1; i >= 0; --i) {
                        if (!CMakeElementTypes.WHITE_SPACES.contains((IElementType)list.get(i))) {
                            return i + 1;
                        }
                    }
                    return 0;
                }
            });
        }
        return true;
    }
}
