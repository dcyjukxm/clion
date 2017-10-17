// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import com.jetbrains.cidr.modulemap.psi.ModuleMapUseDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapUmbrellaDirDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapRequiresDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleId;
import com.jetbrains.cidr.modulemap.psi.ModuleMapLinkDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapExportDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapConflictDeclaration;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.modulemap.psi.ModuleMapConfigMacrosDeclaration;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.modulemap.psi.ModuleMapAttributes;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.psi.ModuleMapVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleDeclaration;

public class ModuleMapModuleDeclarationGenImpl extends ModuleMapModuleDeclarationImpl implements ModuleMapModuleDeclaration
{
    public ModuleMapModuleDeclarationGenImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final ModuleMapVisitor moduleMapVisitor) {
        try {
            if (moduleMapVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        moduleMapVisitor.visitModuleDeclaration(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "accept"));
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
    public List<ModuleMapConfigMacrosDeclaration> getConfigMacrosDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapConfigMacrosDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getConfigMacrosDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapConfigMacrosDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapConflictDeclaration> getConflictDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapConflictDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getConflictDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapConflictDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapExportDeclaration> getExportDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapExportDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getExportDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapExportDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapHeaderDeclaration> getHeaderDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapHeaderDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getHeaderDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapHeaderDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapInferredSubmoduleDeclaration> getInferredSubmoduleDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapInferredSubmoduleDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getInferredSubmoduleDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapInferredSubmoduleDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapLinkDeclaration> getLinkDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapLinkDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getLinkDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapLinkDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapModuleDeclaration> getModuleDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapModuleDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getModuleDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapModuleDeclaration>)childrenOfTypeAsList;
    }
    
    @Nullable
    @Override
    public ModuleMapModuleId getModuleId() {
        return this.findChildByClass(ModuleMapModuleId.class);
    }
    
    @NotNull
    @Override
    public List<ModuleMapRequiresDeclaration> getRequiresDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapRequiresDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getRequiresDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapRequiresDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapUmbrellaDirDeclaration> getUmbrellaDirDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapUmbrellaDirDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getUmbrellaDirDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapUmbrellaDirDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapUseDeclaration> getUseDeclarationList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapUseDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapModuleDeclarationGenImpl", "getUseDeclarationList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<ModuleMapUseDeclaration>)childrenOfTypeAsList;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
