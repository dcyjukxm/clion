// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.intellij.psi.PsiElement;

public abstract class OCUsageWithContext extends OCUsageInfo<PsiElement>
{
    private static final int historyLength = 5;
    private PsiElement myParent;
    private List<PsiElement> myElements;
    
    public OCUsageWithContext(PsiElement prevSibling) {
        super(prevSibling);
        this.myElements = new ArrayList<PsiElement>(5);
        this.myParent = prevSibling.getParent();
        for (int n = 0; prevSibling != null && n < 5; prevSibling = prevSibling.getPrevSibling(), ++n) {
            this.myElements.add(prevSibling);
        }
    }
    
    public PsiElement getElement() {
        for (final PsiElement psiElement : this.myElements) {
            if (psiElement.isValid()) {
                return psiElement;
            }
        }
        return this.myParent;
    }
    
    protected void setElement(final PsiElement psiElement) {
        this.myElements.clear();
        this.myElements.add(psiElement);
        this.myParent = psiElement.getParent();
    }
    
    public PsiElement getParent() {
        return this.myParent;
    }
    
    protected PsiElement addElement(PsiElement psiElement) {
        for (final PsiElement psiElement2 : this.myElements) {
            if (psiElement2.isValid()) {
                psiElement = this.myParent.addAfter(psiElement, psiElement2);
                this.myElements.add(0, psiElement);
                return psiElement;
            }
        }
        psiElement = OCChangeUtil.add(this.myParent, psiElement);
        this.myElements.add(0, psiElement);
        return psiElement;
    }
}
