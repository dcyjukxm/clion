// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiElementFilter;

static final class OCEvaluator$2 implements PsiElementFilter {
    public boolean isAccepted(final PsiElement psiElement) {
        return psiElement instanceof OCExpression && !(psiElement instanceof OCLiteralExpression) && !(psiElement instanceof OCReferenceExpression) && !(psiElement instanceof OCCastExpression) && !(psiElement instanceof OCParenthesizedExpression);
    }
}