// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCUnresolvedReferenceRenamer$1 extends OCRecursiveVisitor {
    final /* synthetic */ String val$referenceName;
    final /* synthetic */ List val$refs;
    
    @Override
    public void visitElement(final PsiElement psiElement) {
        super.visitElement(psiElement);
        for (final PsiReference psiReference : psiElement.getReferences()) {
            if (psiReference.getCanonicalText().equals(this.val$referenceName) && psiReference.resolve() == null && !(psiReference.getElement() instanceof OCDeclarator)) {
                this.val$refs.add(psiReference);
            }
        }
    }
}