// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveModifyableVisitor;

static final class OCBindUtil$3 extends OCRecursiveModifyableVisitor {
    final /* synthetic */ boolean val$isDotCall;
    final /* synthetic */ PsiElement val$rootElement;
    final /* synthetic */ Ref val$result;
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        if (OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocReferenceExpression)) {
            return;
        }
        super.visitReferenceExpression(ocReferenceExpression);
        final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
        Object o = null;
        if (resolveToSymbol instanceof OCInstanceVariableSymbol) {
            o = OCElementFactory.expressionFromText("self->" + resolveToSymbol.getName(), (PsiElement)ocReferenceExpression);
        }
        else if (resolveToSymbol instanceof OCSymbolWithQualifiedName && ((OCFunctionSymbol)resolveToSymbol).getParent() instanceof OCStructSymbol && !((OCFunctionSymbol)resolveToSymbol).isFriendOrStatic() && resolveToSymbol.getKind() != OCSymbolKind.ENUM_CONST && (!(resolveToSymbol instanceof OCFunctionSymbol) || !((OCFunctionSymbol)resolveToSymbol).isCppConstructor())) {
            o = OCElementFactory.expressionFromText((this.val$isDotCall ? "this." : "this->") + resolveToSymbol.getName(), (PsiElement)ocReferenceExpression);
        }
        if (o != null) {
            if (ocReferenceExpression == this.val$rootElement) {
                this.val$result.set(o);
            }
            else {
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocReferenceExpression, (PsiElement)o);
            }
        }
    }
}