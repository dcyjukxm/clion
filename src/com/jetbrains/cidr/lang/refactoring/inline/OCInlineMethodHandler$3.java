// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import java.util.Set;
import com.intellij.util.containers.Stack;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

static final class OCInlineMethodHandler$3 extends OCVisitor {
    final /* synthetic */ List val$elements;
    final /* synthetic */ Stack val$blocksStack;
    final /* synthetic */ Set val$elementsInBlocks;
    
    public void visitElement(final PsiElement psiElement) {
        psiElement.acceptChildren((PsiElementVisitor)this);
        this.val$elements.add(psiElement);
        if (!this.val$blocksStack.isEmpty()) {
            this.val$elementsInBlocks.add(psiElement);
        }
    }
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
        this.val$blocksStack.push((Object)ocBlockExpression);
        ocBlockExpression.acceptChildren((PsiElementVisitor)this);
        this.val$blocksStack.pop();
    }
}