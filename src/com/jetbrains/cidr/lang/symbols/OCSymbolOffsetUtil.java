// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.NodeStructure;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafType;
import com.intellij.lang.ForeignLeafType;
import com.intellij.psi.impl.source.tree.ForeignLeafPsiElement;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import java.util.Comparator;

public final class OCSymbolOffsetUtil
{
    private static final int BITS_IN_TEXT_OFFSET = 32;
    private static final long MAX_IN_TEXT_OFFSET = 4294967296L;
    private static final long IN_TEXT_OFFSET_MASK = 4294967295L;
    public static final long INVALID_OFFSET = 4294967296L;
    public static final Comparator<OCSymbol> SYMBOL_COMPARATOR_BY_SCOPE;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public static int getOffsetInTopMacroSubstitution(final PsiElement psiElement) {
        final PsiElement prevLeaf = OCElementUtil.getPrevLeaf(psiElement);
        if (prevLeaf instanceof OCMacroForeignLeafElement) {
            return ((OCMacroForeignLeafElement)prevLeaf).getOffsetInTopSubstitution() + 1;
        }
        if (prevLeaf instanceof ForeignLeafPsiElement) {
            return getOffsetInTopMacroSubstitution(prevLeaf) + 1;
        }
        return 0;
    }
    
    public static int getTextOffsetInTopMacroSubstitution(final PsiElement psiElement) {
        if (!(psiElement instanceof ForeignLeafPsiElement)) {
            return psiElement.getTextRange().getStartOffset();
        }
        final PsiElement prevSiblingOrParentSibling = OCElementUtil.getPrevSiblingOrParentSibling(psiElement);
        if (prevSiblingOrParentSibling != null) {
            return getTextOffsetInTopMacroSubstitution(prevSiblingOrParentSibling) + prevSiblingOrParentSibling.getTextLength();
        }
        return 0;
    }
    
    public static int getOffsetInTopMacroSubstitution(final ForeignLeafType foreignLeafType) {
        if (foreignLeafType instanceof OCMacroForeignLeafType) {
            return ((OCMacroForeignLeafType)foreignLeafType).getOffsetInTopSubstitution();
        }
        return 0;
    }
    
    public static <T> int getOffsetInTopMacroSubstitution(final NodeStructure<T> nodeStructure, final T t) {
        final IElementType tokenType = nodeStructure.getTokenType((Object)t);
        if (t instanceof OCMacroForeignLeafElement) {
            return ((OCMacroForeignLeafElement)t).getOffsetInTopSubstitution();
        }
        if (tokenType instanceof OCMacroForeignLeafType) {
            return ((OCMacroForeignLeafType)tokenType).getOffsetInTopSubstitution();
        }
        return 0;
    }
    
    public static long getComplexOffset(final int n, final int n2) {
        return n | n2 << 32;
    }
    
    public static long getComplexOffset(final PsiElement psiElement) {
        return getComplexOffset(getTextOffsetInTopMacroSubstitution(psiElement), getOffsetInTopMacroSubstitution(psiElement));
    }
    
    public static long getVirtualComplexOffset(final PsiElement psiElement) {
        return (psiElement instanceof OCElement) ? ((OCElement)psiElement).getComplexOffset() : psiElement.getTextOffset();
    }
    
    public static <T> long getComplexOffset(final NodeStructure<T> nodeStructure, final T t) {
        return getComplexOffset(nodeStructure.getStartOffset((Object)t), getOffsetInTopMacroSubstitution(nodeStructure, t));
    }
    
    public static int getTextOffset(final long n) {
        return (int)(n & 0xFFFFFFFFL);
    }
    
    public static int getSubstOffset(final long n) {
        return (int)(n >> 32);
    }
    
    public static int compare(final long n, final long n2) {
        final int textOffset = getTextOffset(n);
        final int textOffset2 = getTextOffset(n2);
        if (textOffset < textOffset2) {
            return -1;
        }
        if (textOffset > textOffset2) {
            return 1;
        }
        final int substOffset = getSubstOffset(n);
        final int substOffset2 = getSubstOffset(n2);
        if (substOffset < substOffset2) {
            return -1;
        }
        if (substOffset > substOffset2) {
            return 1;
        }
        return 0;
    }
    
    public static long adjust(final long n, final long n2) {
        final int textOffset = getTextOffset(n);
        final int textOffset2 = getTextOffset(n2);
        return getComplexOffset((textOffset2 > textOffset) ? textOffset2 : textOffset, getSubstOffset(n2));
    }
    
    static {
        final TextRange textRange;
        final TextRange textRange2;
        SYMBOL_COMPARATOR_BY_SCOPE = ((ocSymbol, ocSymbol2) -> {
            ocSymbol.getScope();
            ocSymbol2.getScope();
            if (!OCSymbolOffsetUtil.$assertionsDisabled && textRange == null) {
                throw new AssertionError(ocSymbol);
            }
            else if (!OCSymbolOffsetUtil.$assertionsDisabled && textRange2 == null) {
                throw new AssertionError(ocSymbol2);
            }
            else {
                return textRange.getStartOffset() - textRange2.getStartOffset();
            }
        });
    }
}
