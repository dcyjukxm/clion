// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.selectWord;

import java.util.Collection;
import com.intellij.psi.PsiWhiteSpace;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.editorActions.ExtendWordSelectionHandlerBase;

public class OCCodeBlockSelectioner extends ExtendWordSelectionHandlerBase
{
    @Override
    public boolean canSelect(final PsiElement psiElement) {
        return psiElement instanceof OCBlockStatement;
    }
    
    @Override
    public List<TextRange> select(final PsiElement psiElement, final CharSequence charSequence, final int n, final Editor editor) {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        final PsiElement openingBrace = ((OCBlockStatement)psiElement).getOpeningBrace();
        final PsiElement closingBrace = ((OCBlockStatement)psiElement).getClosingBrace();
        if (openingBrace == null || closingBrace == null) {
            return list;
        }
        PsiElement psiElement2;
        for (psiElement2 = openingBrace.getNextSibling(); psiElement2 instanceof PsiWhiteSpace; psiElement2 = psiElement2.getNextSibling()) {}
        if (psiElement2 == null) {
            return list;
        }
        final int startOffset = psiElement2.getTextRange().getStartOffset();
        PsiElement psiElement3;
        for (psiElement3 = closingBrace.getPrevSibling(); psiElement3 instanceof PsiWhiteSpace; psiElement3 = psiElement3.getPrevSibling()) {}
        if (psiElement3 == null) {
            return list;
        }
        final int endOffset = psiElement3.getTextRange().getEndOffset();
        if (startOffset >= endOffset) {
            return list;
        }
        list.add(psiElement.getTextRange());
        list.addAll((Collection<?>)ExtendWordSelectionHandlerBase.expandToWholeLine(charSequence, new TextRange(startOffset, endOffset)));
        return list;
    }
}
