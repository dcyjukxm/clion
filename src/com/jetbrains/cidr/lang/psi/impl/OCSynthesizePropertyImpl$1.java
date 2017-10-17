// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import javax.swing.Icon;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.navigation.ItemPresentation;

class OCSynthesizePropertyImpl$1 implements ItemPresentation {
    public String getPresentableText() {
        return OCSynthesizePropertyImpl.this.getName();
    }
    
    public String getLocationString() {
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType((PsiElement)OCSynthesizePropertyImpl.this, new Class[] { OCClassDeclaration.class });
        assert ocClassDeclaration != null;
        final ItemPresentation presentation = ocClassDeclaration.getPresentation();
        return (presentation != null) ? presentation.getPresentableText() : "";
    }
    
    public Icon getIcon(final boolean b) {
        return null;
    }
}