// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.doxygen.psi.DxParamId;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.doxygen.psi.DxVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.doxygen.psi.DxParam;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class DxParamImpl extends ASTWrapperPsiElement implements DxParam
{
    public DxParamImpl(final ASTNode node) {
        super(node);
    }
    
    public void accept(@NotNull final DxVisitor dxVisitor) {
        try {
            if (dxVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/doxygen/psi/impl/DxParamImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        dxVisitor.visitParam(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/doxygen/psi/impl/DxParamImpl", "accept"));
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
    
    @Nullable
    @Override
    public DxParamId getParamId() {
        return this.findChildByClass(DxParamId.class);
    }
    
    @Override
    public String getName() {
        return DxPsiImplUtil.getName(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
