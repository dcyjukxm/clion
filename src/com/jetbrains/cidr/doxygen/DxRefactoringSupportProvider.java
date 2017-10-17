// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.jetbrains.cidr.doxygen.psi.DxParamId;
import com.intellij.psi.PsiElement;
import com.intellij.lang.refactoring.RefactoringSupportProvider;

public class DxRefactoringSupportProvider extends RefactoringSupportProvider
{
    public boolean isMemberInplaceRenameAvailable(final PsiElement psiElement, final PsiElement psiElement2) {
        return psiElement instanceof DxParamId;
    }
}
