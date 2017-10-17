// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCExternalReference;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.CommonProcessors;

class OCUnusedCodeInspection$UnusedVisitor$1 extends CommonProcessors.FindFirstProcessor<PsiReference> {
    final /* synthetic */ PsiElement val$accessor;
    
    protected boolean accept(final PsiReference psiReference) {
        final PsiElement element = psiReference.getElement();
        return (element == null || !(element.getParent() instanceof OCSynthesizeProperty)) && (psiReference instanceof OCExternalReference || !(this.val$accessor instanceof OCMethod) || psiReference.isReferenceTo(this.val$accessor));
    }
}