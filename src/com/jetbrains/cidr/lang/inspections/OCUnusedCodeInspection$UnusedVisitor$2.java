// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.refactoring.OCSafeDeleteProcessorDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.CommonProcessors;

class OCUnusedCodeInspection$UnusedVisitor$2 extends CommonProcessors.FindFirstProcessor<PsiReference> {
    final /* synthetic */ PsiElement val$struct;
    
    protected boolean accept(final PsiReference psiReference) {
        return !OCSafeDeleteProcessorDelegate.isSafeToDelete(psiReference.getElement(), this.val$struct);
    }
}