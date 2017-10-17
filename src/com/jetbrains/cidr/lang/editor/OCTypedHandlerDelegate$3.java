// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.psi.PsiErrorElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCTypedHandlerDelegate$3 extends OCRecursiveVisitor {
    final /* synthetic */ boolean[] val$hasErrors;
    
    public void visitErrorElement(final PsiErrorElement psiErrorElement) {
        this.val$hasErrors[0] = true;
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        if (ocSendMessageExpression.getArguments().size() == 0) {
            this.val$hasErrors[0] = true;
        }
        super.visitSendMessageExpression(ocSendMessageExpression);
    }
}