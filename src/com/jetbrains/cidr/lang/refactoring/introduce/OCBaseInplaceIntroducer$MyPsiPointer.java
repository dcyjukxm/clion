// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;

protected static class MyPsiPointer
{
    PsiFile file;
    TextRange range;
    Class<? extends PsiElement> clazz;
    
    MyPsiPointer(final PsiElement psiElement) {
        this.file = psiElement.getContainingFile();
        this.range = OCElementUtil.getRangeWithMacros(psiElement);
        this.clazz = psiElement.getClass();
    }
    
    Class<? extends PsiElement> getElementClass() {
        return this.clazz;
    }
    
    @Nullable
    PsiElement getElement() {
        return OCCodeInsightUtil.findElementAtRange(this.file, this.range, this.clazz, true);
    }
}
