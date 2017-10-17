// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.selectWord;

import java.util.Collection;
import com.intellij.openapi.util.text.LineTokenizer;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiComment;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.editorActions.ExtendWordSelectionHandlerBase;

public class OCStatementGroupSelectioner extends ExtendWordSelectionHandlerBase
{
    @Override
    public boolean canSelect(final PsiElement psiElement) {
        return psiElement instanceof OCStatement || psiElement instanceof PsiComment;
    }
    
    @Override
    public List<TextRange> select(final PsiElement psiElement, final CharSequence charSequence, final int n, final Editor editor) {
        final ArrayList<Object> list = new ArrayList<Object>();
        if (!(psiElement.getParent() instanceof OCBlockStatement)) {
            return (List<TextRange>)list;
        }
        Object nextSibling = psiElement;
        Object prevSibling = psiElement;
        while (true) {
            final PsiElement prevSibling2 = ((PsiElement)nextSibling).getPrevSibling();
            if (prevSibling2 == null) {
                break;
            }
            if (prevSibling2.getNode().getElementType() == OCTokenTypes.LBRACE) {
                break;
            }
            if (prevSibling2 instanceof PsiWhiteSpace && LineTokenizer.tokenize(((PsiWhiteSpace)prevSibling2).getText().toCharArray(), false).length > 2) {
                break;
            }
            nextSibling = prevSibling2;
        }
        while (nextSibling instanceof PsiWhiteSpace) {
            nextSibling = ((PsiElement)nextSibling).getNextSibling();
        }
        while (true) {
            final PsiElement nextSibling2 = ((PsiElement)prevSibling).getNextSibling();
            if (nextSibling2 == null) {
                break;
            }
            if (nextSibling2.getNode().getElementType() == OCTokenTypes.RBRACE) {
                break;
            }
            if (nextSibling2 instanceof PsiWhiteSpace && LineTokenizer.tokenize(((PsiWhiteSpace)nextSibling2).getText().toCharArray(), false).length > 2) {
                break;
            }
            prevSibling = nextSibling2;
        }
        while (prevSibling instanceof PsiWhiteSpace) {
            prevSibling = ((PsiElement)prevSibling).getPrevSibling();
        }
        list.addAll(ExtendWordSelectionHandlerBase.expandToWholeLine(charSequence, new TextRange(((PsiElement)nextSibling).getTextRange().getStartOffset(), ((PsiElement)prevSibling).getTextRange().getEndOffset())));
        return (List<TextRange>)list;
    }
}
