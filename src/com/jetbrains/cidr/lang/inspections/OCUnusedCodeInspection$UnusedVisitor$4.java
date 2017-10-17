// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.intellij.psi.PsiReference;
import com.intellij.util.CommonProcessors;

class OCUnusedCodeInspection$UnusedVisitor$4 extends CommonProcessors.FindFirstProcessor<PsiReference> {
    protected boolean accept(final PsiReference psiReference) {
        return !(psiReference.getElement().getParent() instanceof OCSynthesizeProperty);
    }
}