// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.visitors;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.TextRange;

public class OCRecursiveVisitor extends OCVisitor
{
    private TextRange myRange;
    
    public OCRecursiveVisitor() {
    }
    
    public OCRecursiveVisitor(final TextRange myRange) {
        this.myRange = myRange;
    }
    
    public void visitElement(final PsiElement psiElement) {
        super.visitElement(psiElement);
        for (PsiElement psiElement2 = psiElement.getFirstChild(); psiElement2 != null; psiElement2 = psiElement2.getNextSibling()) {
            if (this.myRange == null || this.myRange.intersects(this.getTextRange(psiElement2))) {
                psiElement2.accept((PsiElementVisitor)this);
            }
        }
    }
    
    protected TextRange getTextRange(final PsiElement psiElement) {
        return OCElementUtil.getRangeWithMacros(psiElement);
    }
}
