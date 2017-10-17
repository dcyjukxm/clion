// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;

class MissingClassNamespaceBodyFixer$1 extends PsiRecursiveElementWalkingVisitor {
    final /* synthetic */ int[] val$blockInsertionPos;
    
    public void visitElement(final PsiElement psiElement) {
        if (OCFixer.endsTheLine(psiElement.getNode())) {
            this.val$blockInsertionPos[0] = OCFixer.getRangeWithMacros(psiElement).getEndOffset();
            this.stopWalking();
        }
        super.visitElement(psiElement);
    }
}