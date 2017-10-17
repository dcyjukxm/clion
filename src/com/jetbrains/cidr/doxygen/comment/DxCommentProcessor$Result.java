// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.intellij.psi.PsiElement;

private static class Result
{
    public final PsiElement element;
    public final boolean proceed;
    
    public Result(final PsiElement element, final boolean proceed) {
        this.element = element;
        this.proceed = proceed;
    }
}
