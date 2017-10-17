// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.impl.OCMacroReferenceElementImpl;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.util.PairProcessor;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor$1 extends OCRecursiveVisitor {
    public boolean myStopped = false;
    final /* synthetic */ PairProcessor val$processor;
    
    @Override
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        this.a(ocReferenceElement);
        super.visitReferenceElement(ocReferenceElement);
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        this.a(ocQualifiedExpression);
        super.visitQualifiedExpression(ocQualifiedExpression);
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        this.a(ocSendMessageExpression);
        super.visitSendMessageExpression(ocSendMessageExpression);
    }
    
    @Override
    public void visitExpression(final OCExpression ocExpression) {
        this.a(ocExpression);
        super.visitExpression(ocExpression);
    }
    
    private void a(final OCElement ocElement) {
        if (!this.myStopped) {
            final PsiReference reference = ocElement.getReference();
            if (reference != null) {
                final PsiElement resolve = reference.resolve();
                if (resolve instanceof OCSymbolDeclarator && !(resolve instanceof OCDefineDirective) && !(resolve instanceof OCMacroReferenceElementImpl) && !this.val$processor.process((Object)resolve, (Object)ocElement)) {
                    this.myStopped = true;
                }
            }
        }
    }
}