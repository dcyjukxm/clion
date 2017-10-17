// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.daemon.OCErrorAnnotator;
import com.intellij.lang.annotation.AnnotationSession;
import com.jetbrains.cidr.lang.daemon.OCResolveAnnotator;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCImportsOptimizer$1 extends OCRecursiveVisitor {
    final /* synthetic */ OCResolveAnnotator val$resolveAnnotator;
    final /* synthetic */ AnnotationSession val$session;
    final /* synthetic */ OCErrorAnnotator val$errorAnnotator;
    
    @Override
    public void visitElement(final PsiElement psiElement) {
        super.visitElement(psiElement);
        this.val$resolveAnnotator.annotate(psiElement, null, this.val$session);
        this.val$errorAnnotator.annotate(psiElement, null, this.val$session);
    }
}