// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCExtractMethodProcessor$1 extends OCRecursiveVisitor {
    final /* synthetic */ TextRange val$range;
    
    @Override
    public void visitStatement(final OCStatement ocStatement) {
        super.visitStatement(ocStatement);
        if (this.val$range.contains(ocStatement.getTextRange())) {
            OCExtractMethodProcessor.access$002(OCExtractMethodProcessor.this, true);
        }
    }
}