// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.intellij.psi.PsiElement;
import java.util.Comparator;

class OCSafeDeleteProcessorDelegate$2 implements Comparator<PsiElement> {
    private int a(final PsiElement psiElement) {
        return (psiElement instanceof OCSynthesizeProperty) ? 1 : 0;
    }
    
    @Override
    public int compare(final PsiElement psiElement, final PsiElement psiElement2) {
        return this.a(psiElement) - this.a(psiElement2);
    }
}