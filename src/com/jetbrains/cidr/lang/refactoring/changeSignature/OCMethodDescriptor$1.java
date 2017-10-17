// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCMethodDescriptor$1 extends OCRecursiveVisitor {
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        if (ocReferenceExpression.getSelfSuperToken() != null) {
            OCMethodDescriptor.access$000(OCMethodDescriptor.this).add(ocReferenceExpression);
        }
    }
}