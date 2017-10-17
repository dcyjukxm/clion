// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.actions;

import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.psi.PsiElement;

public class OCConvertToBlockAction extends OCConvertAction
{
    @Override
    protected boolean isAvailableFor(final PsiElement psiElement) {
        return psiElement instanceof OCFunctionDefinition || psiElement instanceof OCMethod;
    }
    
    @Override
    protected OCCallableKind getCallableKind() {
        return OCCallableKind.BLOCK;
    }
}
