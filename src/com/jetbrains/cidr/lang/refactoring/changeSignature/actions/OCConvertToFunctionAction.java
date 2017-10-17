// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.actions;

import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.PsiElement;

public class OCConvertToFunctionAction extends OCConvertAction
{
    @Override
    protected boolean isAvailableFor(final PsiElement psiElement) {
        return psiElement instanceof OCMethod || psiElement instanceof OCBlockExpression;
    }
    
    @Override
    protected OCCallableKind getCallableKind() {
        return OCCallableKind.FUNCTION;
    }
}
