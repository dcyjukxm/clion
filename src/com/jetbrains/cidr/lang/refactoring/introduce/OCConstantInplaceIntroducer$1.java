// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.daemon.OCConstantExpressionVisitor;

class OCConstantInplaceIntroducer$1 extends OCConstantExpressionVisitor {
    @Override
    protected boolean isConstDeclarator(final OCReferenceExpression ocReferenceExpression, final OCSymbol ocSymbol) {
        return super.isConstDeclarator(ocReferenceExpression, ocSymbol) && ocSymbol.isGlobal();
    }
}