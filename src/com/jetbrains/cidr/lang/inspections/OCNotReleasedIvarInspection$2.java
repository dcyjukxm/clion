// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

class OCNotReleasedIvarInspection$2 extends OCVisitor {
    final /* synthetic */ Ref val$isProcessed;
    final /* synthetic */ OCVisitor val$visitor;
    
    public void visitElement(final PsiElement psiElement) {
        if (!(boolean)this.val$isProcessed.get()) {
            psiElement.getContainingFile().accept((PsiElementVisitor)this.val$visitor);
            this.val$isProcessed.set((Object)true);
        }
    }
}