// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi.impl;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.doxygen.DxReference;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.doxygen.psi.DxNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public abstract class DxNamedElementImpl extends ASTWrapperPsiElement implements DxNamedElement
{
    public DxNamedElementImpl(@NotNull final ASTNode node) {
        if (node == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/doxygen/psi/impl/DxNamedElementImpl", "<init>"));
        }
        super(node);
    }
    
    public PsiReference getReference() {
        return (PsiReference)new DxReference((PsiElement)this, new TextRange(0, this.getText().length()));
    }
}
