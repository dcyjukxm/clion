// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.doxygen.psi.DxVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.doxygen.psi.DxParamId;

public class DxParamIdImpl extends DxNamedElementImpl implements DxParamId
{
    public DxParamIdImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final DxVisitor dxVisitor) {
        try {
            if (dxVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/doxygen/psi/impl/DxParamIdImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        dxVisitor.visitParamId(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/doxygen/psi/impl/DxParamIdImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (visitor instanceof DxVisitor) {
                this.accept((DxVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        super.accept(visitor);
    }
    
    @NotNull
    @Override
    public String getName() {
        String name;
        try {
            name = DxPsiImplUtil.getName(this);
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxParamIdImpl", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @NotNull
    @Override
    public PsiElement setName(final String s) {
        PsiElement setName;
        try {
            setName = DxPsiImplUtil.setName(this, s);
            if (setName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxParamIdImpl", "setName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return setName;
    }
    
    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return DxPsiImplUtil.getNameIdentifier(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
