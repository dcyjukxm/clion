// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.ASTNode;

class OCMultilineNodeFormatter$2 implements DocumentModifier {
    final /* synthetic */ ASTNode val$node;
    final /* synthetic */ TextRange val$textRange;
    
    @Override
    public TextRange change(final boolean b, final String s, final String s2) {
        final PsiElement psi = this.val$node.getPsi();
        final ASTNode treeParent = this.val$node.getTreeParent();
        if (treeParent != null) {
            PsiElement psiElement;
            if (b) {
                psiElement = OCElementFactory.codeFragment("\n#if 0\n" + s + "\n#endif", psi.getProject(), psi, false, false).getFirstChild().getNextSibling().getNextSibling();
            }
            else {
                psiElement = OCElementFactory.codeFragment(s, psi.getProject(), psi, false, false).getFirstChild();
            }
            final ASTNode node = psiElement.getNode();
            if (node != null) {
                if (b) {
                    final Pair pair = (Pair)OCMultilineNodeFormatter.PREPROCESSOR_INFO.get((UserDataHolder)this.val$node);
                    if (pair != null && pair.first != null) {
                        OCMultilineNodeFormatter.AT_FIRST_COL_HINT.set((UserDataHolder)node, pair.first);
                    }
                    final int n = s.length() - node.getTextLength();
                    if (n != 0) {
                        CodeEditUtil.setOldIndentation((TreeElement)node, -n);
                    }
                    else {
                        OCElementFactory.initIndentFromContext(psi, psiElement);
                    }
                }
                else {
                    OCElementFactory.initIndentFromContext(psi, psiElement);
                }
                CodeEditUtil.replaceChild(treeParent, this.val$node, node);
                return new TextRange(this.val$textRange.getStartOffset(), this.val$textRange.getStartOffset() + node.getTextLength());
            }
        }
        return this.val$textRange;
    }
}