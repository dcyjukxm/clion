// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;

static final class OCFixer$1 extends PsiRecursiveElementWalkingVisitor {
    final /* synthetic */ PsiErrorElement[] val$result;
    
    public void visitErrorElement(final PsiErrorElement psiErrorElement) {
        this.val$result[0] = psiErrorElement;
        this.stopWalking();
    }
}