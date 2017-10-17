// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.psi.PsiElement;

class OCChangeSignatureHandlerImpl$1 extends OCChangeInfo {
    @Override
    public String getNewInheritedSignature(final OCCallable ocCallable) {
        return OCChangeSignatureHandlerImpl.this.calculateSignature(ocCallable, false);
    }
}