// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.actions;

import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.psi.PsiElement;

public class OCConvertToMethodAction extends OCConvertAction
{
    @Override
    protected boolean isAvailableFor(final PsiElement psiElement) {
        return psiElement instanceof OCFunctionDefinition || psiElement instanceof OCBlockExpression;
    }
    
    @Override
    protected OCCallableKind getCallableKind() {
        return OCCallableKind.METHOD;
    }
}
