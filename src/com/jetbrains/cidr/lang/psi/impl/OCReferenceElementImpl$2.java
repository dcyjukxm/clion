// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.psi.meta.PsiWritableMetaData;

class OCReferenceElementImpl$2 implements PsiWritableMetaData {
    public PsiElement getDeclaration() {
        return (PsiElement)OCReferenceElementImpl.this;
    }
    
    public String getName(final PsiElement psiElement) {
        return OCReferenceElementImpl.this.getName();
    }
    
    public String getName() {
        return OCReferenceElementImpl.this.getName();
    }
    
    public void setName(final String s) throws IncorrectOperationException {
        OCReferenceElementImpl.this.handleElementRename(s);
    }
    
    public void init(final PsiElement psiElement) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @NotNull
    public Object[] getDependences() {
        throw new UnsupportedOperationException("Not implemented");
    }
}