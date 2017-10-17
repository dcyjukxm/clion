// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.selectWord;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.editorActions.ExtendWordSelectionHandlerBase;

public class OCTypeCastSelectioner extends ExtendWordSelectionHandlerBase
{
    @Override
    public boolean canSelect(final PsiElement psiElement) {
        return OCElementUtil.getElementType(psiElement) == OCElementTypes.CAST_EXPRESSION;
    }
    
    @Override
    public List<TextRange> select(final PsiElement psiElement, final CharSequence charSequence, final int n, final Editor editor) {
        final ArrayList<Object> list = new ArrayList<Object>();
        list.addAll(ExtendWordSelectionHandlerBase.expandToWholeLine(charSequence, psiElement.getTextRange(), false));
        PsiElement psiElement2 = null;
        PsiElement psiElement3 = null;
        for (PsiElement psiElement4 = psiElement.getFirstChild(); psiElement4 != null; psiElement4 = psiElement4.getNextSibling()) {
            if (OCElementUtil.getElementType(psiElement4) == OCTokenTypes.LPAR) {
                psiElement2 = psiElement4;
            }
            else if (OCElementUtil.getElementType(psiElement4) == OCTokenTypes.RPAR) {
                psiElement3 = psiElement4;
            }
        }
        if (psiElement2 != null && psiElement3 != null) {
            list.addAll(ExtendWordSelectionHandlerBase.expandToWholeLine(charSequence, new TextRange(psiElement2.getTextRange().getStartOffset(), psiElement3.getTextRange().getEndOffset()), false));
        }
        return (List<TextRange>)list;
    }
}
