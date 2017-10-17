// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCExpectedTypeUtil$1 extends OCRecursiveVisitor {
    final /* synthetic */ Ref val$answer;
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
    }
    
    @Override
    public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
    }
    
    @Override
    public void visitReturnStatement(final OCReturnStatement ocReturnStatement) {
        final OCExpression expression = ocReturnStatement.getExpression();
        if (expression == null) {
            return;
        }
        final OCType resolvedType = expression.getResolvedType();
        if (resolvedType.getTerminalType().isUnknown()) {
            this.val$answer.set((Object)OCUnknownType.INSTANCE);
            return;
        }
        if (this.val$answer.isNull()) {
            this.val$answer.set((Object)resolvedType);
        }
        else {
            this.val$answer.set((Object)((OCType)this.val$answer.get()).getLeastCommonType(resolvedType, (PsiElement)ocReturnStatement));
        }
    }
}