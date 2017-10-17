// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCGeneratedMethodUsage$1 extends OCRecursiveVisitor {
    final /* synthetic */ OCChangeInfo val$changeInfo;
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        if (ocReferenceExpression.getSelfSuperToken() != null && this.val$changeInfo.getSelfParameterName() != null) {
            final OCReferenceElement referenceElement = ocReferenceExpression.getReferenceElement();
            if (referenceElement != null) {
                referenceElement.setNameOfIdentifier(this.val$changeInfo.getSelfParameterName());
            }
        }
    }
}