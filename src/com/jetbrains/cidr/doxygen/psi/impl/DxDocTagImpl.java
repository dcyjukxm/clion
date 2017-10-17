// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.doxygen.psi.DxParam;
import java.util.List;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.doxygen.psi.DxVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.doxygen.psi.DxDocTag;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class DxDocTagImpl extends ASTWrapperPsiElement implements DxDocTag
{
    public DxDocTagImpl(final ASTNode node) {
        super(node);
    }
    
    public void accept(@NotNull final DxVisitor dxVisitor) {
        try {
            if (dxVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/doxygen/psi/impl/DxDocTagImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        dxVisitor.visitDocTag(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/doxygen/psi/impl/DxDocTagImpl", "accept"));
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
    public List<DxParam> getParamList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)DxParam.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxDocTagImpl", "getParamList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<DxParam>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public String getName() {
        String name;
        try {
            name = DxPsiImplUtil.getName(this);
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxDocTagImpl", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @NotNull
    @Override
    public String getOriginalName() {
        String originalName;
        try {
            originalName = DxPsiImplUtil.getOriginalName(this);
            if (originalName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxDocTagImpl", "getOriginalName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return originalName;
    }
    
    @NotNull
    @Override
    public List<String> getOptions() {
        List<String> options;
        try {
            options = DxPsiImplUtil.getOptions((PsiElement)this);
            if (options == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxDocTagImpl", "getOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return options;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
