// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.doxygen.psi.DxDocTag;
import java.util.List;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.doxygen.psi.DxVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.doxygen.psi.DxDocComment;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class DxDocCommentImpl extends ASTWrapperPsiElement implements DxDocComment
{
    public DxDocCommentImpl(final ASTNode node) {
        super(node);
    }
    
    public void accept(@NotNull final DxVisitor dxVisitor) {
        try {
            if (dxVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/doxygen/psi/impl/DxDocCommentImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        dxVisitor.visitDocComment(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/doxygen/psi/impl/DxDocCommentImpl", "accept"));
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
    public List<DxDocTag> getDocTagList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)DxDocTag.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxDocCommentImpl", "getDocTagList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<DxDocTag>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<String> getOptions() {
        List<String> options;
        try {
            options = DxPsiImplUtil.getOptions((PsiElement)this);
            if (options == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxDocCommentImpl", "getOptions"));
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
