// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleMember;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.modulemap.psi.ModuleMapAttributes;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.psi.ModuleMapVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleDeclaration;

public class ModuleMapInferredSubmoduleDeclarationGenImpl extends ModuleMapInferredSubmoduleDeclarationImpl implements ModuleMapInferredSubmoduleDeclaration
{
    public ModuleMapInferredSubmoduleDeclarationGenImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final ModuleMapVisitor moduleMapVisitor) {
        try {
            if (moduleMapVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapInferredSubmoduleDeclarationGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        moduleMapVisitor.visitInferredSubmoduleDeclaration(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapInferredSubmoduleDeclarationGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (visitor instanceof ModuleMapVisitor) {
                this.accept((ModuleMapVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        super.accept(visitor);
    }
    
    @Nullable
    @Override
    public ModuleMapAttributes getAttributes() {
        return this.findChildByClass(ModuleMapAttributes.class);
    }
    
    @NotNull
    @Override
    public List<ModuleMapInferredSubmoduleMember> getInferredSubmoduleMemberList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapInferredSubmoduleMember.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapInferredSubmoduleDeclarationGenImpl", "getInferredSubmoduleMemberList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapInferredSubmoduleMember>)childrenOfTypeAsList;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
