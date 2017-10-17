// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.refactoring.rename.UnresolvableCollisionUsageInfo;

static final class OCChangeSignatureUsageProcessor$3 extends UnresolvableCollisionUsageInfo {
    final /* synthetic */ PsiNamedElement val$oldParam;
    
    @Override
    public String getDescription() {
        return OCBundle.message("changeSignature.usages.cantBeRemoved", this.val$oldParam.getName());
    }
}