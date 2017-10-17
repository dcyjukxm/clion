// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.selectWord;

import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;

public class OCWordSelectionFilter implements Condition<PsiElement>
{
    public boolean value(final PsiElement psiElement) {
        if (OCSelectorWordSelectioner.isCustomSelectionContext(psiElement)) {
            return false;
        }
        final ASTNode node = psiElement.getNode();
        if (node.getElementType() == OCTokenTypes.STRING_LITERAL) {
            final ASTNode treePrev = node.getTreePrev();
            if (treePrev != null && treePrev.getElementType() == OCTokenTypes.AT) {
                return false;
            }
        }
        return true;
    }
}
