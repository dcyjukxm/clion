// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCExtractMethodProcessor$2 extends OCRecursiveVisitor {
    final /* synthetic */ OCSymbolWithQualifiedName val$parent;
    final /* synthetic */ Ref val$canBeConst;
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        super.visitQualifiedExpression(ocQualifiedExpression);
        this.a(ocQualifiedExpression);
    }
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        super.visitReferenceExpression(ocReferenceExpression);
        this.a(ocReferenceExpression);
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        super.visitCallExpression(ocCallExpression);
        final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(ocCallExpression.getFunctionReferenceExpression());
        if (symbol instanceof OCFunctionSymbol && OCCodeInsightUtil.isMemberAccess((OCSymbolWithQualifiedName)symbol, (OCStructSymbol)this.val$parent, ocCallExpression.getFunctionReferenceExpression())) {
            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)symbol;
            if (!ocFunctionSymbol.isConst() && !ocFunctionSymbol.isCppConstructor() && !ocFunctionSymbol.resolveIsStatic() && !ocFunctionSymbol.isCppNonMemberOperator(new OCResolveContext((PsiElement)ocCallExpression))) {
                this.val$canBeConst.set((Object)false);
            }
        }
    }
    
    private void a(final OCExpression ocExpression) {
        final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(ocExpression);
        if (new OCReadWriteAccessDetector().getExpressionAccess((PsiElement)ocExpression) != ReadWriteAccessDetector.Access.Read && symbol instanceof OCDeclaratorSymbol && OCCodeInsightUtil.isNonStaticFieldAccess((OCDeclaratorSymbol)symbol, (OCStructSymbol)this.val$parent, ocExpression)) {
            this.val$canBeConst.set((Object)false);
        }
    }
}