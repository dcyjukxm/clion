// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.ResolveResult;

class OCFileResourceReference$1 implements ResolveResult {
    final /* synthetic */ VirtualFile val$virtualFile;
    
    public PsiElement getElement() {
        return OCFileResourceReference.access$200(OCFileResourceReference.this, this.val$virtualFile);
    }
    
    public boolean isValidResult() {
        final PsiElement element = this.getElement();
        return element != null && element.isValid();
    }
}